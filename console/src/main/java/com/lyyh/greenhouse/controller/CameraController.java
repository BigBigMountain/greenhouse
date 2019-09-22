package com.lyyh.greenhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.Camera2;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.Nvr2;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.CameraService;
import com.lyyh.greenhouse.service.HouseService;
import com.lyyh.greenhouse.service.ZoneService;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	private HouseService houseService;
	
	
	@RequestMapping("/cameraList.do")
	public String cameraList(Model model,Integer zoneId){
		List<Zone> zoneList = zoneService.queryAll();
		if(zoneId == null || zoneId ==0){
			zoneId = zoneList.get(0).getZoneId();
		}
		List<Camera2> cameraList = cameraService.queryAllByZoneId(zoneId);
		List<Nvr2> nvrList = cameraService.getAllNvrByZoneId(zoneId);
		List<House> houseList = houseService.findAllByZoneId(zoneId);
		
		model.addAttribute("NvrList",nvrList);
		model.addAttribute("houseList",houseList);
		model.addAttribute("cameraList",cameraList);
		model.addAttribute("zoneList",zoneList);
		model.addAttribute("zoneId",zoneId);
		
		return "camera/cameraList";
	}
	
	@RequestMapping("/saveOrUpdateCamera.do")
	public String saveOrUpdateCamera(Model model,Camera2 camera){
		Integer zoneId = camera.getZoneId();
		
		cameraService.saveOrUpdateCamera(camera);
		model.addAttribute("zoneId",zoneId);
		
		return "forward:/camera/cameraList.do";
		
	}
	
	@RequestMapping("/delCamera.do")
	public @ResponseBody String delCamera(Integer cameraId){
		cameraService.delCameraByCameraId(cameraId);
		return "删除成功";
		
	}
	
	
}
