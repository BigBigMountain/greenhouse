package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.NvrDao;
import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.service.NvrService;

@Service
@Transactional
public class NvrServiceImpl implements NvrService {

	@Autowired
	private NvrDao nvrDao;
	@Override
	public List<Nvr> getAllNvrByZoneId(Integer zoneId) {
		
		return nvrDao.getAllNvrByZoneId(zoneId);
	}
	@Override
	public void saveOrUpdateNvr(Nvr nvr) {
		if(null != nvr){
			if(nvr.getNvr_id() == null || nvr.getNvr_id() == 0){
				nvrDao.saveNvr(nvr);
			}else{
				nvrDao.updateNvr(nvr);
			}
		}
	}
	@Override
	public void delnvr(Integer nvr_id) {
		nvrDao.delnvr(nvr_id);
	}
	
	
}
