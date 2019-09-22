package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.NodeConfig;

public interface NodeConfigDao {

	List<NodeConfig> queryAllActiviedByZoneId(@Param("zoneId")Integer zoneId);

	List<NodeConfig> queryAllWaterByZoneId(Integer zoneId);

}
