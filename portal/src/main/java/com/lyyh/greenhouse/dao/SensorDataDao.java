package com.lyyh.greenhouse.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.fertilizer.pojo.ChartsPoint;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.vo.SensorDataVo;

public interface SensorDataDao {

	void insertList(List<SensorData> dataList);
	
	List<SensorData> selectNewestDataByHouseId(@Param("houseId")Integer houseId);

	List<SensorData> selectByVo(SensorDataVo vo);

	List<SensorData> getTodayDataBySensorId(Integer id);

	List<SensorData> getSensorDataList(@Param("sensorId")Integer sensorId, @Param("start")Date start, @Param("end")Date end);

	List<SensorData> selectWithConditionByVo(SensorDataVo vo);

	//每10分钟的数据插入到历史表,其余的数据丢弃
	void insertSensorDataToHis();

	void deleteSensorData();

	List<SensorData> selectSensorDataByOrderByExcelSort(@Param("houseId")Integer houseId, @Param("start")Date start, @Param("end")Date end);
}
