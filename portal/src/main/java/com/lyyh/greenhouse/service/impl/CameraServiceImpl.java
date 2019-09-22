package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.CameraDao;
import com.lyyh.greenhouse.pojo.Camera_GH;
import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;
import com.lyyh.greenhouse.service.CameraService;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {

	@Autowired
	private CameraDao cameraDao;
	
	@Override
	public List<Camera_GH> findAllCameras(String zoneName) {
		List<Camera_GH> cameras = cameraDao.findAllCameras(zoneName);
		return cameras;
	}

	//获取一个地区的所有nvr
	@Override
	public List<Nvr> findAllNvrByZoneName(String zoneName) {
		List<Nvr> nvrs = cameraDao.findAllNvrByZoneName(zoneName);
		return nvrs;
	}

	@Override
	public int countAllCamera(String zoneName) {
		int count = cameraDao.countAllCamera(zoneName);
		return count;
	}

	@Override
	public int getRow(String zoneName) {
		int count = cameraDao.countAllCamera(zoneName);
		if(count <=1)
			return 1;
		if(count <=4)
			return 2;
		if(count <=9)
			return 3;
		if(count <=16)
			return 4;
		if(count<=25)
			return 5;
		if(count<=36)
			return 6;
		return 1;
	}

	@Override
	public Camera_GH findCameraByHouse(HouseDataVo houseDataVo) {
		Camera_GH camera = cameraDao.findCameraByHouse(houseDataVo);
		return camera;
	}

}
