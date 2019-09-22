package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Camera2;
import com.lyyh.greenhouse.pojo.Nvr2;


public interface CameraService {

	List<Camera2> queryAllByHouseId(int houseId);

	List<Camera2> queryAllByZoneId(Integer zoneId);

	List<Nvr2> getAllNvrByZoneId(Integer zoneId);

	void saveOrUpdateCamera(Camera2 camera);

	void delCameraByCameraId(Integer cameraId);

}
