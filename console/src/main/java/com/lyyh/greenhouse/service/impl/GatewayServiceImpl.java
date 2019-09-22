package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyh.greenhouse.dao.GatewayDao;
import com.lyyh.greenhouse.pojo.Gateway;
import com.lyyh.greenhouse.service.GatewayService;

@Service
public class GatewayServiceImpl implements GatewayService{

	@Autowired
	private GatewayDao gatewayDao;
	
	@Override
	public List<Gateway> selectByZoneId(Integer zoneId) {
		return gatewayDao.selectByZoneId(zoneId);
	}

	@Override
	public void insertOne(Gateway gateway) {
		gatewayDao.insertOne(gateway);
	}

	@Override
	public void update(Gateway gateway) {
		gatewayDao.update(gateway);
	}

	@Override
	public void deleteById(Integer id) {
		gatewayDao.deleteById(id);
	}

}
