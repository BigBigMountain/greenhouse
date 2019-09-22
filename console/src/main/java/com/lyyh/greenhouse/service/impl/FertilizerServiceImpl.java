package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.greenhouse.dao.FertilizerDao;
import com.lyyh.greenhouse.dao.TvalveDao;
import com.lyyh.greenhouse.service.FertilizerService;


@Service
public class FertilizerServiceImpl implements FertilizerService {
	@Autowired
	TvalveDao tvalveDao;

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

	@Transactional
	@Override
	public void addFertilizer(Fertilizer fertilizer) {
		fertilizerDao.addFertilizer(fertilizer);
	}

	@Transactional
	@Override
	public void deleteByFerId(int fertilizerId) {
		fertilizerDao.deleteByFerId(fertilizerId);
	}

	@Transactional
	@Override
	public void updateFertilizer(Fertilizer fertilizer) {
		fertilizerDao.updateFertilizer(fertilizer);
	}

	

}
