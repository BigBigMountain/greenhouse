package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.CameraDao;
import com.lyyh.greenhouse.pojo.Camera_GH;
import com.lyyh.greenhouse.pojo.Camera2;
import com.lyyh.greenhouse.pojo.Nvr2;
import com.lyyh.greenhouse.service.CameraService;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {

	@Autowired
	private CameraDao cameraDao;
	@Override
	public List<Camera2> queryAllByHouseId(int houseId) {
		
		return cameraDao.queryAllByHouseId(houseId);
	}
	
	@Override
	public List<Camera2> queryAllByZoneId(Integer zoneId) {
		List<Camera2> cameraList = cameraDao.queryAllByZoneId(zoneId);
		return cameraList;
	}

	@Override
	public List<Nvr2> getAllNvrByZoneId(Integer zoneId) {
		return cameraDao.getAllNvrByZoneId(zoneId);
	}

	@Override
	public void saveOrUpdateCamera(Camera2 camera) {
		Integer cameraId = camera.getCameraId();
		if(cameraId == null || cameraId == 0){
			cameraDao.saveCamera(camera);
		}else{
			cameraDao.updateCamera(camera);
		}
		
	}

	@Override
	public void delCameraByCameraId(Integer cameraId) {
		cameraDao.delCameraByCameraId(cameraId);
		
	}

}
