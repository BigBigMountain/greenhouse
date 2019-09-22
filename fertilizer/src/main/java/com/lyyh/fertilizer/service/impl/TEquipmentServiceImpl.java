package com.lyyh.fertilizer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.fertilizer.dao.TEquipmentDao;
import com.lyyh.fertilizer.service.TEquipmentService;
import com.lyyh.tzgk.pojo.Tequipment;

@Service
@Transactional
public class TEquipmentServiceImpl implements TEquipmentService{

	@Autowired
	TEquipmentDao tequipmentDao;
	
	@Override
	public void add(Tequipment tequipment) {
		tequipmentDao.add(tequipment);
	}

	@Override
	public void update(Tequipment tequipment) {
		tequipmentDao.update(tequipment);
		
	}

	@Override
	public Tequipment queryOne(String emac) {
		
		return tequipmentDao.queryByEmac(emac);
	}

	@Override
	public List<Tequipment> queryByUser(String username) {
		return tequipmentDao.queryByUser(username);
	}

	@Override
	public List<Tequipment> queryByUser(int uid) {
		return tequipmentDao.queryByUser(uid);
	}

	@Override
	public List<Tequipment> queryAll() {
		return tequipmentDao.queryAll();
	}

}
