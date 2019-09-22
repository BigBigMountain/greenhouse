package com.lyyh.fertilizer.backup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.fertilizer.backup.dao.FertilizerDao;
import com.lyyh.fertilizer.pojo.Fertilizer;

@Service
public class FertilizerService implements com.lyyh.fertilizer.backup.service.FertilizerService {

	@Autowired
	private FertilizerDao fertilizerDao;
	
	@Override
	public List<Fertilizer> queryAllFertilizer() {
		return fertilizerDao.queryAll();
	}

	@Override
	public List<Fertilizer> queryAllByUid(int userId) {
		return fertilizerDao.queryAllByUid(userId);
	}

	@Override
	public List<Fertilizer> queryAllByUsername(String username) {
		return fertilizerDao.queryAllByUsername(username);
	}

	@Override
	public Fertilizer selectByDtuCode(String dtuCode) {
		return fertilizerDao.selectByDtuCode(dtuCode);
	}

	@Override
	public Fertilizer selectByFertilizerId(int fertilizerId) {
		return fertilizerDao.selectByFertilizerId(fertilizerId);
	}

	@Override
	@Transactional
	public void addFertilizer(Fertilizer fertilizer) {
		fertilizerDao.addFertilizer(fertilizer);
	}

	@Override
	@Transactional
	public void deleteByFerId(int fertilizerId) {
		fertilizerDao.deleteByFerId(fertilizerId);
	}

	@Override
	@Transactional
	public void updateFertilizer(Fertilizer fertilizer) {
		fertilizerDao.updateFertilizer(fertilizer);
	}

}
