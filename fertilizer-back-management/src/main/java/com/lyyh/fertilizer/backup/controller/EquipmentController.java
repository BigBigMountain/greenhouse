package com.lyyh.fertilizer.backup.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.fertilizer.backup.service.EquipmentService;
import com.lyyh.fertilizer.backup.service.FertilizerService;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.util.ResultInfo;
import com.lyyh.tzgk.pojo.TgateWay;
import com.lyyh.tzgk.pojo.Tvalve;
import com.lyyh.tzgk.pojo.vo.TvalveList;

@Controller
@RequestMapping("/equipment")
public class EquipmentController extends BaseController {
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	FertilizerService fertilizerService;
	
	@RequestMapping("/gateWayList.do")
	public String gateWayList(HttpServletRequest request,Model model,Integer userId){
		List<User> userList = getUserList(request);
		if(null == userId || userId == 0){
			if(userList.get(0) != null){
				userId = userList.get(0).getUserId();
			}
		}
		List<TgateWay> gateWayList = equipmentService.getTGateWayByUserId(userId);
		
		model.addAttribute("userId",userId);
		model.addAttribute("gateWayList",gateWayList);
		return "/equipment/gateWayList";
	}
	
	@RequestMapping("/saveOrUpdateGateWay.do")
	public String saveOrUpdateGateWay (Model model,HttpServletRequest request,TgateWay gateWay){
		
		if(gateWay.getGateWayId() == null || gateWay.getGateWayId() == 0){
			equipmentService.addGateWay(gateWay);
		}else{
			equipmentService.updateGateWay(gateWay);
		}
		
		model.addAttribute("userId", gateWay.getUserId());
		return "forward:/equipment/gateWayList.do";
		
	}
	
	@RequestMapping("/delGateWay.do")
	public @ResponseBody String delGateWay (Model model,HttpServletRequest request,Integer gateWayId){
		
		if(gateWayId != null){
			equipmentService.deleteGateWay(gateWayId);
			return null;
		}
		
		return "参数不对!";
	}
	
	
	
	@RequestMapping("/valveList.do")
	public String valveList(Model model,HttpServletRequest request,Integer userId,Integer fertilizerId){
		List<User> userList = getUserList(request);
		List<Fertilizer> fertilizerList = null;
		List<Tvalve> valveList = null;
		List<TgateWay> gateWayList = null;
		if(null == userId || userId == 0){
			if(userList.get(0) != null && userList.size() > 0){
				userId = userList.get(0).getUserId();
			}
		}
		if(userId != null && userId != 0){
			fertilizerList = fertilizerService.queryAllByUid(userId);
			gateWayList = equipmentService.getTGateWayByUserId(userId);
		}
		if(fertilizerId == null || fertilizerId == 0){
			if(fertilizerList != null && fertilizerList.size() > 0){
				fertilizerId = fertilizerList.get(0).getFertilizerId();
			}
		}
		fertilizerId = fertilizerId == null ? 0 : fertilizerId;
		if(fertilizerId != null && fertilizerId != 0){
			valveList = equipmentService.getTvalveListByFertilizerId(fertilizerId);
		}
		
		
		model.addAttribute("userList", userList == null ? 0 : userList);
		model.addAttribute("userId", userId == null ? 0 : userId);
		model.addAttribute("fertilizerList", fertilizerList == null ? new ArrayList<Fertilizer>() : fertilizerList);
		model.addAttribute("fertilizerId", fertilizerId == null ? 0 : fertilizerId);
		model.addAttribute("valveList", valveList == null ? new ArrayList<Tvalve>() : valveList);
		model.addAttribute("gateWayList", gateWayList == null ? new ArrayList<Tvalve>() : gateWayList);
		return "equipment/valveList";
	}
	
	
	@RequestMapping("/saveOrUpdateValve.do")
	public String saveOrUpdateValve (Model model,HttpServletRequest request,Integer userId,Tvalve tvalve){
		
		if(tvalve.getTvalveId() == null || tvalve.getTvalveId() == 0){
			equipmentService.addValve(tvalve);
		}else{
			equipmentService.updateValve(tvalve);
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("fertilizerId", tvalve.getFertilizerId());
		return "forward:/equipment/valveList.do";
		
	}
	
	@RequestMapping("/delValve.do")
	public @ResponseBody String delValve (Model model,HttpServletRequest request,Integer tvalveId){
		
		if(tvalveId != null){
			equipmentService.deleteTvalve(tvalveId);
			return null;
		}
		return "参数不对!";
		
	}
	
	@RequestMapping("saveOrUpdateValveList.do")
	public @ResponseBody ResultInfo saveOrUpdateValveList(TvalveList valveList){ 
		
		System.out.println(valveList.toString());
		return ResultInfo.success();
	}
	
}
