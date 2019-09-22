package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.House;

public interface HouseDao {

	List<House> findAllByZoneId(int zoneId);

	void saveHouse(House house);

	void updateHouse(House house);

	void delHouseById(int houseId);

	House queryById(int houseId);

}
