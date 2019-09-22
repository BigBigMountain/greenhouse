package com.lyyh.greenhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.ZoneService;

@Controller
@RequestMapping("/zone")
public class ZoneController {

	@Autowired
	private ZoneService zoneService;
	
	@RequestMapping("/zoneList.do")
	public String zoneList(Model model){
		List<Zone> zoneList = zoneService.queryAll();
		model.addAttribute("zoneList",zoneList);
		return "zone/zoneList";
	}
	
	
	/*保存或修改
	 * saveOrUpdateZone.do
	 */
	@RequestMapping("/saveOrUpdateZone.do")
	public String saveOrUpdateZone(Model model,Zone zone){
		String msg ="";
		if(null == zone){
			msg = "数据不能为空";
		}else if(zone.getZoneName()==null || zone.getZoneName().trim()==""){
			msg="区域名称不能为空";
		}else{
			zoneService.saveOrUpdateZone(zone);
		}
		model.addAttribute("msg",msg);
		return "forward:/zone/zoneList.do";
		
	}
	
	/* 删除区域
	 * delZone.do
	 */
	@RequestMapping(value="/delZone.do",produces="text/json;charset=UTF-8")
	public @ResponseBody String delZone(Integer zoneId){
		zoneService.delZone(zoneId);
		return "删除成功";
		
	}
}
