package com.lyyh.greenhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.HouseService;
import com.lyyh.greenhouse.service.ZoneService;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@Autowired
	private ZoneService zoneService;
	
	
	/* 温室管理首页
	 * /house/houseList.do
	 */
	@RequestMapping("/houseList.do")
	public String houseList(Model model,Integer zoneId){
		List<Zone> zoneList = zoneService.queryAll();
		if(zoneList.isEmpty()){
			return "house/houseList";
		}
		if(zoneId ==null || zoneId ==0){
			zoneId=zoneList.get(0).getZoneId();
		}
		List<House> houseList = houseService.findAllByZoneId(zoneId);
		model.addAttribute("zoneId",zoneId);
		model.addAttribute("zoneList",zoneList);
		model.addAttribute("houseList",houseList);
		return "house/houseList";
	}
	
	/* 保存或更新温室
	 * saveOrUpdateHouse
	 */
	@RequestMapping("/saveOrUpdateHouse.do")
	public String saveOrUpdateHouse(Model model,House house){
		if(null != house){
			String msg = houseService.saveOrUpdateHouse(house);
			model.addAttribute("msg",msg);
		}
		
		return "forward:/house/houseList.do";
	}
	
	/*
	 * /house/delHouse.do
	 */
	@RequestMapping("/delHouse.do")
	public String delHouse(Integer houseId){
		houseService.delHouseById(houseId);
		return "forward:/house/houseList.do";
	}
	
	
	
	
	
	
	
	
}
