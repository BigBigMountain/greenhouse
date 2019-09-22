package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Zone;

public interface ZoneService {

	List<Zone> queryAll();

	void saveOrUpdateZone(Zone zone);

	void delZone(Integer zoneId);

}
