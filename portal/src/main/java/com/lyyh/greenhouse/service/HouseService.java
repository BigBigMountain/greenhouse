package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.House;

public interface HouseService {


	List<House> findAllByZoneId(Integer zoneId);

	String getHouseName(Integer id);

	House selectById(Integer id);
}
