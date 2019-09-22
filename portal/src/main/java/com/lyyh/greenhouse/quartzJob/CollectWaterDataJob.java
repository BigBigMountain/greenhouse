package com.lyyh.greenhouse.quartzJob;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyyh.greenhouse.dao.NodeConfigDao;
import com.lyyh.greenhouse.dao.WaterSensorDataDao;
import com.lyyh.greenhouse.pojo.NodeConfig;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.WaterSensorData;
import com.lyyh.greenhouse.util.KLModbusData;
import com.lyyh.greenhouse.util.KLModbusUtils;
import com.lyyh.greenhouse.util.ModbusUtil;
import com.lyyh.greenhouse.util.cache.CacheManager;


public class CollectWaterDataJob {

	@Autowired
	private NodeConfigDao nodeConfigDao;
	
	@Autowired
	private WaterSensorDataDao waterSensorDataDao;

	private Integer zoneId;

	public void collectWaterData() {
		System.out.println("this collect water data start");

		// 获取水表的列表
		List<NodeConfig> nodeConfigs = nodeConfigDao.queryAllWaterByZoneId(zoneId);

		NumberFormat nf = new DecimalFormat("0.0");

		if (nodeConfigs == null || nodeConfigs.size() == 0) {
			return;
		}
//		List<SensorData> dataList = new ArrayList<SensorData>();
//		List<WaterSensorData> waterDataList = new ArrayList<WaterSensorData>();
		Date now = new Date();
		// 遍历温室
		byte result[] = new byte[1024];
		for (NodeConfig config : nodeConfigs) {
			String ip = config.getGateway().getIp();
			int port = config.getGateway().getPort();

			// 从网关查询数据
			// 与网关建立连接并获取socket输出流
			OutputStream os = ModbusUtil.getOutputStream(ip, port);
			// System.out.print(os + " ");
			if (null != os) {
				SensorData data = new SensorData();

				try {
					int node = config.getPosition();
					Integer channel = config.getChannel();
					byte[] mqs = ModbusUtil.getModbusQueryStatement(node);
					os.write(mqs);
					InputStream is = ModbusUtil.getInputStream(ip, port);
					// System.out.print(is + " ");
					is.read(result);
					KLModbusData klmd = KLModbusUtils.parseData(result, channel);
					Double doubleVal = klmd.getDoubleVal();
					// 把从昆仑海岸网关读出来的数据转换成真实数据
					Double showData = convertToShowData(doubleVal, config);
					showData = Double.parseDouble(nf.format(showData));
					data.setSensorId(config.getSensorId());
					data.setSensorTypeId(config.getSensorTypeId());
					data.setHouseId(config.getHouseId());
					data.setZoneId(config.getZoneId());
					data.setValue(showData);
					data.setDateTime(now);

					if (data.getSensorTypeId() == 7) {// 水表需要转换成累加值
						WaterSensorData waterData = handleWaterData(data);
						data.setValue(waterData.getPeriodValue());
//						waterDataList.add(waterData);
					}
//					dataList.add(data);
					CacheManager.newestSensorData.getCache().put(data.getSensorId(), data);
				} catch (Exception e) {
					e.printStackTrace();
					ModbusUtil.closeConnection(ip, port);
				}
			} else {
				System.out.println("无法创建连接,请检查网关是否在线");
			}
		} // 所有温室数据封装完成
//		if (dataList.size() != 0) {
//			// System.out.println(dataList.size());
//			sensorDataDao.insertList(dataList);
//			waterSensorDataDao.insertList(waterDataList);
//		}

		System.out.println("this collect water data end");
	}
	
	private Double convertToShowData(Double doubleVal, NodeConfig config) {
		SensorType type = config.getSensor().getType();
		int dataType = type.getDataType();

		if (dataType == 0) {// 真实值
			return doubleVal;
		} 
		Integer dataMax = type.getDataMax();
		Integer dataMin = type.getDataMin();
		Integer rangeMax = type.getRangeMax();
		Integer rangeMin = type.getRangeMin();
		
		if (dataType == 1) {//电压值  --> 显示值
			if(doubleVal <= dataMin){
				return 0.0;
			}
			if(doubleVal >= dataMax){
				return rangeMax*1.0;
			}
			return (doubleVal - dataMin) * (rangeMax - rangeMin) / (dataMax - dataMin) + rangeMin;
		} else if (dataType == 2) {//电流值 --> 显示值
			if(doubleVal <= dataMin){
				return 0.0;
			}
			if(doubleVal >= dataMax){
				return rangeMax*1.0;
			}
			return (doubleVal - dataMin) * (rangeMax - rangeMin) / (dataMax - dataMin) + rangeMin;
		} else {
			return doubleVal;
		}

	}
	
	private WaterSensorData handleWaterData(SensorData data) {
		WaterSensorData waterData = CacheManager.waterMeter.getCache().get(data.getSensorId());
		if(waterData == null){
			waterData = waterSensorDataDao.getNewestDataBySensorId(data.getSensorId());
			if(waterData == null){
				waterData = new WaterSensorData();
				BeanUtils.copyProperties(data, waterData);
				waterData.setPeriodValue(0.0);
				waterData.setSumValue(0.0);
				CacheManager.waterMeter.getCache().put(waterData.getSensorId(), waterData);
				return waterData;
			}
			CacheManager.waterMeter.getCache().put(waterData.getSensorId(), waterData);
		}
		
		long deltaT = data.getDateTime().getTime() - waterData.getDateTime().getTime();
		if(deltaT > 1000*60*5){
			//如果与上次记录值的时差超过5分钟,则本次累加按一分钟计算
			deltaT = 1000*60;
		}
		Double deltaV = (data.getValue() + waterData.getValue())/2 * deltaT/ 1000 / 3600;
		NumberFormat nf= new DecimalFormat("0.000");
		deltaV = Double.valueOf(nf.format(deltaV));
		waterData.setPeriodValue(waterData.getPeriodValue() + deltaV);
		waterData.setSumValue(waterData.getSumValue() + deltaV);
		BeanUtils.copyProperties(data, waterData);
		return waterData;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}


}
