package com.lyyh.greenhouse.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.dao.HouseDataDao;
import com.lyyh.greenhouse.dao.NodeConfigDao;
import com.lyyh.greenhouse.dao.Screem8Dao;
import com.lyyh.greenhouse.dao.SensorDataDao;
import com.lyyh.greenhouse.dao.SettingDao;
import com.lyyh.greenhouse.dao.WaterSensorDataDao;
import com.lyyh.greenhouse.pojo.ClimaticCollector;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.NodeConfig;
import com.lyyh.greenhouse.pojo.NodeSetting;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.WaterSensorData;
import com.lyyh.greenhouse.service.CollectionDataService;
import com.lyyh.greenhouse.threadTask.DataCollectionTask;
import com.lyyh.greenhouse.util.KLModbusData;
import com.lyyh.greenhouse.util.KLModbusUtils;
import com.lyyh.greenhouse.util.ModbusUtil;
import com.lyyh.greenhouse.util.cache.CacheManager;

@Service
@Transactional
public class CollectionDataServiceImpl implements CollectionDataService {

	@Autowired
	private NodeConfigDao nodeConfigDao;
	
	@Autowired
	private SensorDataDao sensorDataDao;
	@Autowired
	private SettingDao settingDao;
	
	@Autowired
	private HouseDataDao houseDataDao;
	
	@Autowired
	private ClimaticDao climaticDao;
	
	@Autowired
	private Screem8Dao screem8Dao;
	
	@Autowired
	private WaterSensorDataDao waterSensorDataDao;
	
	@Override
	public void stopCollect() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		// 激活的线程数加倍
		int estimatedSize = group.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = group.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		System.arraycopy(slackList, 0, list, 0, actualSize);
//		System.out.println("Thread list size == " + list.length);

		// TODO
		for (Thread thread : list) {
			String threadName = thread.getName();
			if (threadName.contains("CollectData") || threadName.contains("Screem8")) {
//				System.out.println(tname);
				thread.stop();
//				thread.destroy();
			}
		}
	}

	@Override
	public void startCollect(Integer zoneId) {
		//先结束已有的DataCollect线程 TODO
		stopCollect();
		//再重新开启线程    线程"采集数据"
		String threadName = "CollectData"+zoneId;
		DataCollectionTask dataCollectionTask = new DataCollectionTask(zoneId,sensorDataDao, climaticDao,nodeConfigDao, settingDao);
		Thread dataCollectionThread = new Thread(dataCollectionTask,threadName);
		dataCollectionThread.start();
		System.out.println("采集数据已经启动");
		
//		//开启线程    8字屏显示
//		String threadName2 = "Screem8"+zoneId;
//		Screem8Task screem8Task = new Screem8Task(houseDataDao,screem8Dao, zoneId);
//		Thread screem8Thread = new Thread(screem8Task,threadName2);
//		screem8Thread.start();
		
	}

	@Override
	public void collectData(Integer zoneId) {
		List<NodeConfig> configs = nodeConfigDao.queryAllActiviedByZoneId(zoneId);
		ClimaticSetting climaticSetting = settingDao.queryClimaticSetting(zoneId);
		if (!CollectionUtils.isEmpty(configs)) {
			collectHouseData(configs);
		}
		if (climaticSetting != null) {
			collectClimaticData(climaticSetting);
		}
		
	}

	@Override
	public void collectHouseData(List<NodeConfig> nodeConfigs) {
		NumberFormat nf = new DecimalFormat("0.0");
	       
		if (nodeConfigs == null || nodeConfigs.size() == 0) {
			return;
		}
		List<SensorData> dataList = new ArrayList<SensorData>();
		List<WaterSensorData> waterDataList = new ArrayList<WaterSensorData>();
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
					
					if(data.getSensorTypeId() == 7){//水表需要转换成累加值
						WaterSensorData waterData = handleWaterData(data);
						data.setValue(waterData.getPeriodValue());
						waterDataList.add(waterData);
					}
					dataList.add(data);
					CacheManager.newestSensorData.getCache().put(data.getSensorId(), data);
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
			waterSensorDataDao.insertList(waterDataList);
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
		NumberFormat nf = new DecimalFormat("0.000");
		deltaV = Double.valueOf(nf.format(deltaV));
		waterData.setPeriodValue(waterData.getPeriodValue() + deltaV);
		waterData.setSumValue(waterData.getSumValue() + deltaV);
		BeanUtils.copyProperties(data, waterData);
		return waterData;
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

	@Override
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
				Double todayRainfall = climaticDao.getRainFallByDate(zoneId,DateFormatUtils.format(now, "yyyy-MM-dd"));
				Double yestodayRainFall = climaticDao.getRainFallByDate(zoneId,DateFormatUtils.format(new Date(now.getTime()-1000*3600*24),"yyyy-MM-dd"));
//				Double lastOneDayAgoRainFall = climaticDao.getLastOneDayAgoRainFall(zoneId,DateFormatUtils.format(now, "yyyy-MM-dd"));
				lastRainFall = lastRainFall == null ? 0:lastRainFall;
				if(yestodayRainFall == null){
					climaticDao.insertRainFallLog(zoneId,DateFormatUtils.format(new Date(now.getTime()-1000*3600*24), "yyyy-MM-dd"), nowRainFall);
					yestodayRainFall = nowRainFall;
				}
				
				while(nowRainFall + ClimaticCollector.RF_URV/2 < lastRainFall){
					nowRainFall += ClimaticCollector.RF_URV;
				}
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
