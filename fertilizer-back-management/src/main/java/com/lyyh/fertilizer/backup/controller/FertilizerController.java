package com.lyyh.fertilizer.backup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.fertilizer.backup.service.FertilizerService;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.greenhouse.pojo.User;

@Controller
@RequestMapping("/fertilizer")
public class FertilizerController extends BaseController{
	
	@Autowired
	FertilizerService fertilizerService;
	
	/*
	 *  /equipment/equipmentList.do?zoneId=zoneId&houseId=houseId;
	 */
	@RequestMapping("/fertilizerList.do")
	public String fertilizerList(Model model,HttpServletRequest request,Integer userId){
		List<User> userList = getUserList(request);
		model.addAttribute("userList", userList);
		if(null == userId || userId == 0){
			if(userList.get(0) != null){
				userId = userList.get(0).getUserId();
			}
		}
		List<Fertilizer> fertilizerList = fertilizerService.queryAllByUid(userId);
		model.addAttribute("fertilizerList", fertilizerList);
		model.addAttribute("userId", userId);
		return "fertilizer/fertilizerList";
	}
	
	
	@RequestMapping("/saveOrUpdateFertilizer.do")
	public String saveOrUpdateFertilizer (Model model,HttpServletRequest request,Fertilizer fertilizer){
		
		if(fertilizer.getFertilizerId() == null || fertilizer.getFertilizerId() == 0){
			fertilizerService.addFertilizer(fertilizer);
		}else{
			fertilizerService.updateFertilizer(fertilizer);
		}
		
		model.addAttribute("userId", fertilizer.getUserId());
		return "forward:/fertilizer/fertilizerList.do";
		
	}
	
	@RequestMapping("/delFertilizer.do")
	public @ResponseBody String delFertilizer (Model model,HttpServletRequest request,Integer fertilizerId){
		
		if(fertilizerId != null){
			fertilizerService.deleteByFerId(fertilizerId);
			return null;
		}
		
//		model.addAttribute("userId", userId);
//		return "forward:/fertilizer/fertilizerList.do";
		return "参数不对!";
		
	}
	
}
