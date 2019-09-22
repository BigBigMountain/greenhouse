package com.lyyh.fertilizer.dao;

import java.util.List;

import com.lyyh.tzgk.pojo.TsoilTempAndHum;

public interface TsoilMoistureDao {

	List<TsoilTempAndHum> queryNewestSoilMoisture(String username);

}
