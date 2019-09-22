package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.Gateway;

public interface GatewayDao {

	List<Gateway> selectByZoneId(@Param("zoneId")Integer zoneId);
	
	void insertOne(Gateway gateway);

	void deleteById(@Param("id") Integer id);
	
	void update(Gateway gateway);
}
