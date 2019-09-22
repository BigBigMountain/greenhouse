package com.lyyh.greenhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/console")
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
	
	
	/*
	 * 设备页面	frame/sensor_main.do
	 */
	@RequestMapping("/frame/sensor_main.do")
	public String sensor_main(){
		return "frame/sensor_main";
	}
	@RequestMapping("/frame/sensor_left.do")
	public String sensor_left(){
		return "frame/sensor_left";
	}
	
}
