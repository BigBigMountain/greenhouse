package com.lyyh.greenhouse.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.vo.SensorDataVo;

public interface SensorDataService {

	List<SensorData> selectNewestDataByHouseId(Integer houseId);
	
	List<SensorData> selectByVo(SensorDataVo vo);

	List<List<SensorData>> getTodayChartdata(Integer houseId);

	Map<String, List> changeCharts(Integer sensorId, Date start, Date end);

	List<Object[]> getSensorDataList(Integer sensorId, Date start, Date end);

	List<SensorData> selectSensorData(Integer houseId, Date start, Date end);

	List<SensorData> selectSensorDataByOrderByExcelSort(Integer houseId, Date start, Date end);

	List translateToTableVo(List<SensorData> dataList, int rowSize);

	List<SensorData> selectNewestDataFromCacheByHouseId(Integer houseId);

}
