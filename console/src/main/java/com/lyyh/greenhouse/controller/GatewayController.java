package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.Gateway;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.GatewayService;
import com.lyyh.greenhouse.service.ZoneService;

@Controller
@RequestMapping("/gateway")
public class GatewayController {

	@Autowired
	private ZoneService zoneService;
	@Autowired
	private GatewayService gatewayService;
	
	@RequestMapping("listAll.do")
	public String listAll(Model model,HttpSession session,Integer zoneId){
		
		List<Zone> zoneList = zoneService.queryAll();
		model.addAttribute("zoneList", zoneList);
		if(zoneId == null){
			if(!CollectionUtils.isEmpty(zoneList)){
				zoneId = zoneList.get(0).getZoneId();
			}
		}
		model.addAttribute("zoneId", zoneId);
		List<Gateway> gatewayList = gatewayService.selectByZoneId(zoneId);
		model.addAttribute("gatewayList", gatewayList);
		return "sensor/gateway";
	}
	
	@RequestMapping("insert.do")
	public String insert(Model model,Gateway gateway){
		gatewayService.insertOne(gateway);
		model.addAttribute("zoneId", gateway.getZoneId());
		return "forward:listAll.do";
	}
	
	@RequestMapping("/update.do")
	public String update(Model model,Gateway gateway){
		gatewayService.update(gateway);
		model.addAttribute("zoneId", gateway.getZoneId());
		return "forward:listAll.do";
	}
	@RequestMapping("delete.do")
	public @ResponseBody String delete(Integer id){
		gatewayService.deleteById(id);
		return "删除成功!";
	}
}
