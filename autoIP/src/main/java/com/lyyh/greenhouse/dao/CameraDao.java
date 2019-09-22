package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.Camera_GH;
import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;

public interface CameraDao {

	/*
	 * 获取指定用户的所有摄像头
	 */
	List<Camera_GH> findAllCameras(String zoneName);

	List<Nvr> findAllNvrByZoneName(String zoneName);

	int countAllCamera(String zoneName);

	Camera_GH findCameraByHouse(HouseDataVo houseDataVo);

	void updateIP(@Param("zoneName") String zoneName,@Param("ip") String ip);
//	void updateIP(String zoneName,String ip);

/*	void updateIP(@Param("zoneName") String zoneName,@Param("ip") String ip);*/

}
