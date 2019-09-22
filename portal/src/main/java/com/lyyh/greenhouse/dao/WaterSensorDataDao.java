package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.WaterSensorData;
import com.lyyh.greenhouse.pojo.vo.SensorDataVo;

public interface WaterSensorDataDao {

	void insertList(List<WaterSensorData> dataList);
	
	WaterSensorData getNewestDataBySensorId(@Param("sensorId") Integer sensorId);
	
	List<WaterSensorData> getDataByVo(SensorDataVo vo);
	
	void resetPriodValue(@Param("sensorId") Integer sensorId);
}
