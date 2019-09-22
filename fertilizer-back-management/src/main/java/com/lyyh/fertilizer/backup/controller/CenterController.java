package com.lyyh.fertilizer.backup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fertilizer-backup")
public class CenterController {


	/*
	 * 首页index.do
	 */
	@RequestMapping("/index.do")
	public String index(){
		return "index";
	}
	@RequestMapping("/top.do")
	public String top(){
		return "top";
	}
	@RequestMapping("/main.do")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/equipmentMain.do")
	public String equipmentMain(){
		return "equipment/equipment_main";
	}
	@RequestMapping("/equipmentLeft.do")
	public String equipmentLeft(){
		return "equipment/equipment_left";
	}
	
}
