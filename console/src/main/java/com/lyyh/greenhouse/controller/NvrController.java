package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.NvrService;
import com.lyyh.greenhouse.service.ZoneService;


@Controller
@RequestMapping("/nvr")
public class NvrController {
	
	@Autowired
	private NvrService nvrService;
	
	@Autowired
	private ZoneService zoneService;
	
	@RequestMapping("/nvrList.do")
	public String toNvrList(Model model,Integer zoneId){
		
		List<Zone> zoneList = zoneService.queryAll();
		if(zoneId == null || zoneId ==0){
			zoneId = zoneList.get(0).getZoneId();
		}
		List<Nvr> nvrList = nvrService.getAllNvrByZoneId(zoneId);
		model.addAttribute("nvrList",nvrList);
		model.addAttribute("zoneList",zoneList);
		model.addAttribute("zoneId",zoneId);
		
		return "nvr/nvrList";
	}
	
	@RequestMapping("/saveOrUpdateNvr.do")
	public String saveOrUpdateNvr(Model model,Nvr nvr){
		if(nvr != null && nvr.getZone_id() != null){
			nvrService.saveOrUpdateNvr(nvr);
			return "forward:/nvr/nvrList.do?zoneId="+nvr.getZone_id();
		}
		return "forward:/nvr/nvrList.do";
	}
	
	@RequestMapping("/delnvr.do")
	
	public String delnvr(Integer nvr_id){
		System.out.println(nvr_id);
		nvrService.delnvr(nvr_id);
		return "";
	}

}
