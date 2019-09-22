package com.lyyh.greenhouse.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.SensorType;

public interface SensorDao {

	Sensor selectById(@Param("id")Integer id);
	
	SensorType selectTypeByTypeId(@Param("typeId")Integer typeId);
	
	Sensor selectWithTypeById(@Param("id")Integer id);
	
	List<Sensor> selectByHouseId(@Param("houseId") Integer houseId);

	List<SensorType> selectTypeList();

	List<Sensor> selectWithTypeByHouseId(@Param("houseId")Integer houseId);
	
	List<Sensor> selectWithTypeByHouseIdOrderByPageSort(@Param("houseId")Integer houseId);
	
	List<Sensor> selectWithTypeByHouseIdOrderByExcelSort(@Param("houseId")Integer houseId);
	
}
