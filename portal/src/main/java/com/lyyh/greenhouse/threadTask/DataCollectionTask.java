package com.lyyh.greenhouse.threadTask;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.ClassToInstanceMap;
import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.dao.HouseDataDao;
import com.lyyh.greenhouse.dao.NodeConfigDao;
import com.lyyh.greenhouse.dao.SensorDataDao;
import com.lyyh.greenhouse.dao.SettingDao;
import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.ClimaticCollector;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.Gateway;
import com.lyyh.greenhouse.pojo.NodeSetting;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.HouseSetting;
import com.lyyh.greenhouse.pojo.NodeConfig;
import com.lyyh.greenhouse.util.KLModbusData;
import com.lyyh.greenhouse.util.KLModbusUtils;
import com.lyyh.greenhouse.util.ModbusUtil;

public class DataCollectionTask implements Runnable {
	private Integer zoneId;
	private SensorDataDao sensorDataDao;
	private ClimaticDao climaticDao;
	private NodeConfigDao nodeConfigDao;
	private SettingDao settingDao;

	public DataCollectionTask() {
		super();
	}

	public DataCollectionTask(Integer zoneId, SensorDataDao sensorDataDao, ClimaticDao climaticDao,
			NodeConfigDao nodeConfigDao, SettingDao settingDao) {
		super();
		this.zoneId = zoneId;
		this.sensorDataDao = sensorDataDao;
		this.climaticDao = climaticDao;
		this.nodeConfigDao = nodeConfigDao;
		this.settingDao = settingDao;
	}

	@Override
	public void run() {
		try {
			while (true) {
				List<NodeConfig> configs = nodeConfigDao.queryAllActiviedByZoneId(zoneId);
				ClimaticSetting climaticSetting = settingDao.queryClimaticSetting(zoneId);
				if (!CollectionUtils.isEmpty(configs)) {
					collectData(configs);
				}
				if (climaticSetting != null) {
					collectClimaticData(climaticSetting);
				}
				Thread.sleep(1000 * 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public void collectData(List<NodeConfig> nodeConfigs) {
		if (nodeConfigs == null || nodeConfigs.size() == 0) {
			return;
		}
		List<SensorData> dataList = new ArrayList<SensorData>();
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

					data.setSensorId(config.getSensorId());
					data.setSensorTypeId(config.getSensorTypeId());
					data.setHouseId(config.getHouseId());
					data.setZoneId(config.getZoneId());
					data.setValue(showData);
					data.setDateTime(now);
					dataList.add(data);
				} catch (Exception e) {
					e.printStackTrace();
					ModbusUtil.closeConnection(ip, port);
				}
			} else {
				System.out.println("无法创建连接,请检查网关是否在线");
			}
		} // 所有温室数据封装完成
		if (dataList.size() != 0) {
//			System.out.println(dataList.size());
			sensorDataDao.insertList(dataList);
		}

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

	public void collectClimaticData(ClimaticSetting climaticSetting) {
		if (climaticSetting == null) {
			return;
		}
		ClimaticCollector climaticCollector = new ClimaticCollector();

		String ip = climaticSetting.getIp();
		int port = climaticSetting.getPort();

		// TODO 从网关查询数据
		// 与网关建立连接并获取socket输出流
		OutputStream os = ModbusUtil.getOutputStream(ip, port);
		// System.out.print(os + " ");
		if (null != os) {
			// 一个温室的数据
//			String zoneName = climaticSetting.getZoneName();
			Integer zoneId = climaticSetting.getZoneId();
			byte result[] = new byte[1024];
			List<NodeSetting> nodes = climaticSetting.getNodes();

			try {
				for (NodeSetting node : nodes) {
					if (node != null && node.getNodePosition() != 0) {
						byte[] mqs = ModbusUtil.getModbusQueryStatement(node.getNodePosition());
						os.write(mqs);
						InputStream is = ModbusUtil.getInputStream(ip, port);
						is.read(result);
						ModbusUtil.parseData(result, climaticCollector, node);
					}
				}
				Date now = new Date();
				Double nowRainFall = climaticCollector.getRainFall();
				Double lastRainFall = climaticDao.getLastRainFall(zoneId);
				Double yestodayRainFall = climaticDao.getRainFallByDate(zoneId,DateFormatUtils.format(new Date(now.getTime()-3600*24),"yyyy-MM-dd"));
				lastRainFall = lastRainFall == null ? 0:lastRainFall;
				yestodayRainFall = yestodayRainFall == null ? 0:yestodayRainFall;
				while(nowRainFall + ClimaticCollector.RF_URV/2 < lastRainFall){
					nowRainFall += ClimaticCollector.RF_URV;
				}
				Double todayRainfall = climaticDao.getRainFallByDate(zoneId,DateFormatUtils.format(now, "yyyy-MM-dd"));
				if(todayRainfall == null){
					climaticDao.insertRainFallLog(zoneId,DateFormatUtils.format(now, "yyyy-MM-dd"), nowRainFall);
				}else {
					climaticDao.updateRainFallLog(zoneId,DateFormatUtils.format(now, "yyyy-MM-dd"), nowRainFall);
				}
				climaticCollector.setRainFallReal(nowRainFall - yestodayRainFall); 
				climaticCollector.setZoneId(zoneId);
				climaticCollector.setDatetime(now);
				
				// System.out.println(climaticCollector);
				climaticDao.insertOne(climaticCollector);// TODO 换climatic插入的对象,
			} catch (Exception e) {
				e.printStackTrace();
				ModbusUtil.closeConnection(ip, port);
			}
		} else {
			System.out.println("无法创建连接,请检查网关是否在线");
		}
	}

}
