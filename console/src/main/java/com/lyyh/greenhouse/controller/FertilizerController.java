package com.lyyh.greenhouse.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.FertilizerService;
import com.lyyh.greenhouse.service.UserService;

/**
 * 施肥机是属于 用户的,
 * 阀属于施肥机下
 * 用户账号 --> 施肥机 --> 阀(网关等设备)
 * @author lt
 *
 */
@Controller
@RequestMapping("/fertilizer")
public class FertilizerController {

	@Autowired
	private FertilizerService fertilizerService;
	
	@Autowired
	private UserService userService;
	
	public List<User> getUserList(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<User> userList = (List<User>)session.getAttribute("userList");
		if(null == userList){
			userList = userService.queryAll();
			session.setAttribute("userList", userList);
		}
		return userList;
	}
	
	/*
	 *  /equipment/equipmentList.do?zoneId=zoneId&houseId=houseId;
	 */
	@RequestMapping("/fertilizerList.do")
	public String fertilizerList(Model model,HttpServletRequest request,Integer userId,Integer houseId){
		List<User> userList = getUserList(request);
		model.addAttribute("userList", userList);
		if(null == userId || userId == 0){
			if(userList.get(0) != null){
				userId = userList.get(0).getUserId();
			}
		}
		List<Fertilizer> fertilizerList = fertilizerService.queryAllByUid(userId);
		model.addAttribute("fertilizerList", fertilizerList);
		return "fertilizer/fertilizerList";
	}
	
	
}
