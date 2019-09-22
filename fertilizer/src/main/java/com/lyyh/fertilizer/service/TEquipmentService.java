package com.lyyh.fertilizer.service;

import java.util.List;

import com.lyyh.tzgk.pojo.Tequipment;

public interface TEquipmentService {

	//增
	public void add(Tequipment tequipment);
	
	//改
	public void update(Tequipment tequipment);
	
	//查
	public Tequipment queryOne(String emac);
	
	public List<Tequipment> queryByUser(String Username);
	
	public List<Tequipment> queryByUser(int uid);
	
	public List<Tequipment> queryAll();
}
