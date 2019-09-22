package com.lyyh.greenhouse.quartzJob;

import org.springframework.beans.factory.annotation.Autowired;

import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.dao.SensorDataDao;

public class MoveData {

	@Autowired
	private SensorDataDao sensorDataDao;
	
	@Autowired
	private ClimaticDao climaticDao;
	
	public void moveData(){
		sensorDataDao.insertSensorDataToHis();
		sensorDataDao.deleteSensorData();
		
		climaticDao.insertToHis();
		climaticDao.deleteData();
	}
}
