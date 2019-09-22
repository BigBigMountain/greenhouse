package com.lyyh.greenhouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyh.greenhouse.dao.CameraDao;
import com.lyyh.greenhouse.service.IPService;
@Service
public class IPServiceImpl implements IPService {

	@Autowired
	private CameraDao cameraDao;
	
	@Override
	public void updateIP(String zoneName ,String ip) {
		cameraDao.updateIP(zoneName,ip);

	}

}
