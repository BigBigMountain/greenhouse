package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.Camera2;
import com.lyyh.greenhouse.pojo.Nvr2;

public interface CameraDao {

	List<Camera2> queryAllByHouseId(int houseId);

	List<Camera2> queryAllByZoneId(int zoneId);

	List<Nvr2> getAllNvrByZoneId(int zoneId);

	void saveCamera(Camera2 camera);

	void updateCamera(Camera2 camera);

	void delCameraByCameraId(int cameraId);
	

}
