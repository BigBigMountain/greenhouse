package com.lyyh.greenhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.service.EquipmentService;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	
	/*
	 *  /equipment/equipmentList.do?zoneId=zoneId&houseId=houseId;
	 */
	@RequestMapping("/equipmentList.do")
	public String equipmentList(Model model,Integer zoneId,Integer houseId){
		
		model = equipmentService.equipmentList(model,zoneId,houseId);
		return "equipment/equipmentList";
	}
	
	/*
	 *  /saveOrUpdateEquipment.do
	 */
	@RequestMapping("/saveOrUpdateEquipment.do")
	public String saveOrUpdateEquipment(Model model,Equipment equipment){
		equipmentService.saveOrUpdateEquipment(equipment);
		Integer houseId = equipment.getHouseId();
		Integer zoneId = equipment.getZoneId();
		String url = equipmentList(model, zoneId, houseId);
		return url;
	}
	
	@RequestMapping(value="/delEquipment.do",produces="text/json;charset=UTF-8")
	public @ResponseBody String delEquipment(Integer equipmentId){
		equipmentService.delEquipmentById(equipmentId);
		
		return "删除成功";
	}
	
}
