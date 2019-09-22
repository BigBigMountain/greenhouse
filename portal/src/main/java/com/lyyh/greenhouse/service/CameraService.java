package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Camera_GH;
import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;

public interface CameraService {



	/*
	 * 查询该用户下所有的摄像头
	 */
	List<Camera_GH> findAllCameras(String zoneName);

	//根据zonename查询该地区的所有nvr
	List<Nvr> findAllNvrByZoneName(String zoneName);

	//获取一个区域的摄像头的个数
	int countAllCamera(String zoneName);

	//获取摄像头的行列数
	int getRow(String zoneName);

	//根据温室查找摄像头
	Camera_GH findCameraByHouse(HouseDataVo houseDataVo);
	
}
