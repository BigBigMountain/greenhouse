package com.lyyh.greenhouse.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyh.greenhouse.dao.WaterSensorDataDao;
import com.lyyh.greenhouse.pojo.WaterSensorData;
import com.lyyh.greenhouse.service.WaterSensorDataService;
import com.lyyh.greenhouse.util.cache.CacheManager;

@Service
public class WaterSensorDataServiceImpl implements WaterSensorDataService {

	@Autowired
	private WaterSensorDataDao waterSensorDataDao;
	@Override
	public void resetPriodValue(Integer sensorId) {
		WaterSensorData waterData = CacheManager.waterMeter.getCache().get(sensorId);
		if(waterData == null){
			waterData = waterSensorDataDao.getNewestDataBySensorId(sensorId);
			if(waterData == null){
				waterData = new WaterSensorData();
				waterData.setSensorId(sensorId);
				waterData.setPeriodValue(0.0);
				waterData.setSumValue(0.0);
				CacheManager.waterMeter.getCache().put(sensorId, waterData);
			}
			CacheManager.waterMeter.getCache().put(sensorId, waterData);
		}
		waterData.setPeriodValue(0.0);
		waterData.setDateTime(new Date());
		
		ArrayList<WaterSensorData> list = new ArrayList<>();
		list.add(waterData);
		waterSensorDataDao.insertList(list);
	}
}
