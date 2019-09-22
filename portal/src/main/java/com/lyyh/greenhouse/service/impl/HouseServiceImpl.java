package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.HouseDao;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.service.HouseService;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;
	

	@Override
	public List<House> findAllByZoneId(Integer zoneId) {
		List<House> houses = houseDao.findAllByZoneId(zoneId);
		return houses;
	}


	@Override
	public String getHouseName(Integer id) {
		return houseDao.getHouseName(id);
	}


	@Override
	public House selectById(Integer id) {
		return houseDao.selectById(id);
	}


}
