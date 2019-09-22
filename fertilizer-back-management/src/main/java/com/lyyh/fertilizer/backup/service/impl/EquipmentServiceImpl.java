package com.lyyh.fertilizer.backup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.fertilizer.backup.dao.EquipmentDao;
import com.lyyh.fertilizer.backup.service.EquipmentService;
import com.lyyh.tzgk.pojo.TgateWay;
import com.lyyh.tzgk.pojo.Tvalve;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;
	@Override
	public List<TgateWay> getTGateWayByUserId(Integer userId) {
		return equipmentDao.getTGateWayByUserId(userId);
	}

	@Override
	@Transactional
	public void addGateWay(TgateWay gateWay) {
		equipmentDao.addGateWay(gateWay);

	}

	@Override
	@Transactional
	public void updateGateWay(TgateWay gateWay) {
		//首先获取原来的网关数据,再修改阀的mac和温湿度的mac
		TgateWay oldGateWay = equipmentDao.getTGateWayById(gateWay.getGateWayId());
		equipmentDao.updateGmacOfTvalve(oldGateWay.getMacCode(), gateWay.getMacCode());
		equipmentDao.updateGmacOfTtempAndHumEquip(oldGateWay.getMacCode(), gateWay.getMacCode());
		equipmentDao.updateGateWay(gateWay);
	}

	@Override
	@Transactional
	public void deleteGateWay(Integer gateWayId) {
		equipmentDao.deleteGateWay(gateWayId);
	}

	
	
	
	
	
	@Override
	public List<Tvalve> getTvalveListByFertilizerId(Integer fertilizerId) {
		return equipmentDao.getTvalveListByFertilizerId(fertilizerId);
	}

	@Override
	@Transactional
	public void addValve(Tvalve tvalve) {
		equipmentDao.addValve(tvalve);
	}

	@Override
	@Transactional
	public void updateValve(Tvalve tvalve) {
		equipmentDao.updateValve(tvalve);
	}

	@Override
	@Transactional
	public void deleteTvalve(Integer tvalveId) {
		equipmentDao.deleteTvalve(tvalveId);
	}

}
