package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorType;

public interface SensorService {

	List<Sensor> selectByHouseId(Integer houseId);

	List<SensorType> selectTypeList();

	List<Sensor> selectWithTypeByHouseId(Integer houseId);

	List<Sensor> selectWithTypeByHouseIdOrderByPageSort(Integer houseId);

	List<Sensor> selectWithTypeByHouseIdOrderByExcelSort(Integer houseId);

}
