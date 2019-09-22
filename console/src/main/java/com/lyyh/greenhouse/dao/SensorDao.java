package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.NodeSetting;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.vo.SensorVo;

public interface SensorDao {

	//返回id
	int insert(Sensor sensor);
	
	void delete(Integer id);
	
	void update(Sensor sensor);
	
	Sensor selectById(Integer id);
	
	List<Sensor> selectAll();

	List<Sensor> selectByHouseId(Integer houseId);

	List<SensorType> selectTypeList();

	void insertType(SensorType type);
	
	void deleteType(@Param("id") Integer id);
	
	void updateType(SensorType type);

	List<SensorVo> selectVoByHouseId(@Param("houseId")Integer houseId);

	ClimaticSetting queryClimaticSetting(Integer zoneId);

	List<ClimaticDataType> queryAllClimaticDataType();

	void deleteClimaticNodeById(@Param("id")Integer id);

	
	
	
	
	
	void insertClimaticSetting(ClimaticSetting climaticSetting);

	void insertNodeSettings(List<NodeSetting> nodes);

	void updateClimaticSetting(ClimaticSetting climaticSetting);

	void insertNodeSetting(NodeSetting node);

	void updateNodeSetting(NodeSetting node);
}
