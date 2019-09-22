package com.lyyh.greenhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.CollectionDataService;

@Controller
@RequestMapping("/dataCollection")
public class CollectionDataController {

	@Autowired
	private CollectionDataService dataCollectionService;
	
	@RequestMapping(value="/startCollect.do",produces="text/json;charset=UTF-8")
	public @ResponseBody String startCollect(HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		
		dataCollectionService.startCollect(user.getZoneId());
		return "已开启!  ^_^";
	}
	
	@RequestMapping(value="/stopCollect.do",produces="text/json;charset=UTF-8")
	public @ResponseBody String stopCollect(){
		dataCollectionService.stopCollect();
		return "已停止";
	}
}
