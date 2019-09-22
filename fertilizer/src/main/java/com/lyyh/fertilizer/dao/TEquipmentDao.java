package com.lyyh.fertilizer.dao;

import java.util.List;

import com.lyyh.tzgk.pojo.Tequipment;
import com.lyyh.tzgk.pojo.TequipmentDataCollector;

public interface TEquipmentDao {

	void add(Tequipment tequipment);

	void update(Tequipment tequipment);

	Tequipment queryByEmac(String emac);

	List<Tequipment> queryByUser(String username);

	List<Tequipment> queryByUser(int uid);

	List<Tequipment> queryAll();

	void addData(TequipmentDataCollector data);


}
