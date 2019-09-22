package com.lyyh.greenhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeData")
public class TimeData {
	
	/*
	 * /timeData/listAll.do
	 */
	@RequestMapping("/listAll.do")
	public String listAll(){
		return "/timeData/listAll";
	}

}
