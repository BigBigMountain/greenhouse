package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.vo.SensorVo;

public interface SensorService {

	// 返回id
	int insert(Sensor sensor);

	void delete(Integer id);

	void update(Sensor sensor);

	Sensor selectById(Integer id);

	List<Sensor> selectAll();

	List<Sensor> selectByHouseId(Integer houseId);
	
	List<SensorType> selectTypeList();

	void insertType(SensorType type);

	void updateType(SensorType type);

	void deleteType(Integer id);

	List<SensorVo> selectVoByHouseId(Integer houseId);

	ClimaticSetting queryClimaticSetting(Integer zoneId);

	List<ClimaticDataType> queryClimaticDataTypes(Integer zoneId);

	void deleteClimaticNodeById(Integer id);

	String saveOrUpdateClimaticSetting(ClimaticSetting climaticSetting);
}
