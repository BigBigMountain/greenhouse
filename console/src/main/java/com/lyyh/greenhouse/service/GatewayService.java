package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Gateway;

public interface GatewayService {

	List<Gateway> selectByZoneId(Integer zoneId);

	void insertOne(Gateway gateway);

	void update(Gateway gateway);

	void deleteById(Integer id);

}
