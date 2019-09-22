package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.HouseDao;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.service.HouseService;
@Service
@Transactional
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;
	
	@Override
	public List<House> findAllByZoneId(int zoneId) {
		List<House> houses = houseDao.findAllByZoneId(zoneId);
		return houses;
	}

	@Override
	public String saveOrUpdateHouse(House house) {
		String msg=null;
		if(house.getId()==null || house.getId()==0){
			houseDao.saveHouse(house);
			msg="添加成功!";
		}else{
			houseDao.updateHouse(house);
			msg="更新成功!";
		}
		return msg;
	}

	@Override
	public void delHouseById(Integer houseId) {
		houseDao.delHouseById(houseId);
		
	}

}
