package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.Zone;

public interface ZoneDao {

	List<Zone> queryAll();

	void save(Zone zone);

	void update(Zone zone);

	void delZone(int zoneId);

}
