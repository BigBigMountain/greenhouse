package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.ZoneDao;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.ZoneService;
@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

	@Autowired
	private ZoneDao zoneDao;
	@Override
	public List<Zone> queryAll() {
		return zoneDao.queryAll();
	}
	@Override
	public void saveOrUpdateZone(Zone zone) {
		if(null != zone){
			if(zone.getZoneId()==null ||zone.getZoneId()==0){
				zoneDao.save(zone);
			}else{
				zoneDao.update(zone);
			}
		}
		
	}
	@Override
	public void delZone(Integer zoneId) {
		zoneDao.delZone(zoneId);
	}

}
