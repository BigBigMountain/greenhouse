package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyh.greenhouse.dao.SensorDao;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.service.SensorService;
@Service
public class SensorServiceImpl implements SensorService {

	@Autowired
	private SensorDao sensorDao;
	
	@Override
	public List<Sensor> selectByHouseId(Integer houseId) {
		return sensorDao.selectByHouseId(houseId);
	}

	@Override
	public List<SensorType> selectTypeList() {
		return sensorDao.selectTypeList();
	}

	@Override
	public List<Sensor> selectWithTypeByHouseId(Integer houseId) {
		return sensorDao.selectWithTypeByHouseId(houseId);
	}

	@Override
	public List<Sensor> selectWithTypeByHouseIdOrderByPageSort(Integer houseId) {
		return sensorDao.selectWithTypeByHouseIdOrderByPageSort(houseId);
	}

	@Override
	public List<Sensor> selectWithTypeByHouseIdOrderByExcelSort(Integer houseId) {
		return sensorDao.selectWithTypeByHouseIdOrderByExcelSort(houseId);
	}

}
