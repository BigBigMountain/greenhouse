package com.lyyh.greenhouse.dao;

import com.lyyh.greenhouse.pojo.Screem8;

public interface Screem8Dao {
	
	
	void saveScreem8Setting(Screem8 screem8);

	void updateScreem8Setting(Screem8 screem8);

	Screem8 selectByZoneId(Integer zoneId);

}
