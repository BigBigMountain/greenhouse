package com.lyyh.fertilizer.threadTask;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.lyyh.fertilizer.dao.TEquipmentDao;
import com.lyyh.tzgk.pojo.TequipmentDataCollector;

public class SaveEqmsg implements Runnable {

	TEquipmentDao tEquipmentDao;
	
	String jsonString;
	
	public SaveEqmsg(TEquipmentDao tEquipmentDao, String jsonString) {
		super();
		this.tEquipmentDao = tEquipmentDao;
		this.jsonString = jsonString;
	}

	@Override
	public void run() {
		TequipmentDataCollector data = JSONObject.parseObject(jsonString, TequipmentDataCollector.class);
		data.setDate(new Date());
		tEquipmentDao.addData(data);
	}
}
