package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.HouseSetting;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.SettingService;

@Controller
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	private SettingService settingService;
	
	/*
	 * 温室设置页面
	 */
	@RequestMapping("/houseSettingList.do")
	public String houseSettingList(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		
		List<HouseSetting> houseSettings = settingService.queryAllHouseSettings(zoneId);
		
		model.addAttribute("houseSettings",houseSettings);
		return "/setting/houseSetting";
	}
	
	/*
	 * 气象设置页面
	 */
	@RequestMapping("/climaticSettingList.do")
	public String climaticSettingList(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		
		ClimaticSetting climaticSetting = settingService.queryClimaticSetting(zoneId);
		String climaticSettingJson = JSONArray.toJSONString(climaticSetting);
		List<ClimaticDataType> climaticDataTypes = settingService.queryClimaticDataTypes(zoneId);
		
		model.addAttribute("user",user);
		model.addAttribute("climaticSettingJson",climaticSettingJson);
		model.addAttribute("climaticDataTypes",climaticDataTypes);
		model.addAttribute("climaticSetting",climaticSetting);
		return "/setting/climaticSetting";
		
	}
	
	/*
	 * 更新温室设置
	 */
	@RequestMapping("/saveOrUpdateHouseSetting.do")
	public String saveOrUpdateHouseSetting(Model model,HttpSession session,HouseSetting houseSetting){
		String msg=settingService.saveOrUpdateHouseSetting(houseSetting);
		model.addAttribute("msg",msg);
		return "forward:/setting/houseSettingList.do";
		
	}
	
	/*
	 * setOffHouseSetting.do
	 */
	@RequestMapping("/setOffHouseSetting.do")
	public String setOffHouseSetting(Integer id){
		settingService.setOffHouseSetting(id);
		return "forward:/setting/houseSettingList.do";
	}
	
	
	/*
	 * saveOrUpdateClimaticSetting.do
	 * 保存或更新气象设置
	 */
	@RequestMapping("/saveOrUpdateClimaticSetting.do")
	public String saveOrUpdateClimaticSetting(Model model,HttpSession session ,ClimaticSetting climaticSetting){
		
		String msg = settingService.saveOrUpdateClimaticSetting(climaticSetting);
		model.addAttribute("msg",msg);
		return "forward:/setting/climaticSettingList.do";
	}
//	@RequestMapping("/saveOrUpdateClimaticSetting.do")
//	public String saveOrUpdateClimaticSetting(HttpServletRequest request){
//		String cs_ip = request.getParameter("cs_ip");
//		System.out.println(cs_ip);
//		return "forward:/setting/climaticSettingList.do";
//	}
	

}
