package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.vo.EquipmentVo;
import com.lyyh.greenhouse.service.EquipmentService;
import com.lyyh.greenhouse.service.HouseService;

@Controller
@RequestMapping(value = "/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private HouseService houseService;

	/*
	 * 列出该用户制定温室id的所有设备
	 */
	@RequestMapping(value = "/listAll.do")
	public String listAll(Model model, HttpSession session,EquipmentVo equipmentVo ) {
		User user = (User) session.getAttribute("loginUser");
		List<House> houses = (List<House>) session.getAttribute("houses");
		if (null == houses) {
			Integer zoneId = user.getZoneId();
			houses = houseService.findAllByZoneId(zoneId);
			session.setAttribute("houses", houses);
		}
		//默认查询1号温室
		if(equipmentVo.getHouseId()==null || equipmentVo.getHouseId()==0){
			equipmentVo.setHouseId(1);
		}
		equipmentVo.setZoneName(user.getZoneName());
		
		List<Equipment> equipments = equipmentService.findAllByVo(equipmentVo);
		model.addAttribute("equipments", equipments);
		model.addAttribute("houseId", equipmentVo.getHouseId());
		return "/equipment/list";

	}
	
	/*
	 * 更改设备状态
	 * /equipment/changeState.do
	 */
	
	@RequestMapping(value="/changeState.do")
	public void changeState(EquipmentVo equipmentVo){
//		System.out.println(equipmentVo);
		equipmentService.changeState(equipmentVo);
	}
	
	
	
	
}
