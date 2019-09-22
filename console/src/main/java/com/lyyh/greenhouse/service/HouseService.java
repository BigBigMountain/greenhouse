package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.House;

public interface HouseService {
	List<House> findAllByZoneId(int zoneId);

	String saveOrUpdateHouse(House house);

	void delHouseById(Integer houseId);
}
