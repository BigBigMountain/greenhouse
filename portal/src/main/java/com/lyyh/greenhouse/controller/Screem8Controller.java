package com.lyyh.greenhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyyh.greenhouse.pojo.Screem8;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.Screem8Service;
import com.lyyh.greenhouse.util.serial.serialException.NoSuchPort;
import com.lyyh.greenhouse.util.serial.serialException.NotASerialPort;
import com.lyyh.greenhouse.util.serial.serialException.PortInUse;
import com.lyyh.greenhouse.util.serial.serialException.SerialPortParameterFailure;

@Controller
@RequestMapping("/screem8/")
public class Screem8Controller {

	@Autowired
	private Screem8Service screem8Service;
	
	@RequestMapping("listAll.do")
	public String listAll(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		
		Screem8 screem8 = screem8Service.getScreem8(zoneId);
		if(screem8 == null){
			return "setting/screen8";
		}
		if(screem8.getBaudRate() == null){
			screem8.setBaudRate(9600);
		}
		model.addAttribute("screem8", screem8);
		
		return "setting/screen8";
	}
	
	
	@RequestMapping("saveOrUpdateScreem8Setting.do")
	public String saveOrUpdateScreem8Setting(HttpSession session, Model model,Screem8 screem8){
		User user = (User) session.getAttribute("loginUser");
		String zoneName = user.getZoneName();
		screem8.setZoneName(zoneName);
		String errMsg = "";
		try {
			screem8Service.saveOrUpdateScreem8Setting(screem8);
		} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse e) {
			errMsg = e.toString();
		}
		model.addAttribute("errMsg", errMsg);
		model.addAttribute("screem8", screem8);
		return "forward:/screem8/listAll.do";
	}
}
