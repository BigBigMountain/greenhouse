package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.Gateway;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.NodeConfig;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.pojo.vo.SensorVo;
import com.lyyh.greenhouse.service.GatewayService;
import com.lyyh.greenhouse.service.HouseService;
import com.lyyh.greenhouse.service.NodeConfigService;
import com.lyyh.greenhouse.service.SensorService;
import com.lyyh.greenhouse.service.ZoneService;

@Controller
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	SensorService sensorService;

	@Autowired
	private HouseService houseService;

	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private GatewayService gatewayService;
	
	@Autowired
	private NodeConfigService nodeConfigService;

	@RequestMapping("/list.do")
	public String list(Model model,Integer zoneId, Integer houseId) {
		List<Zone> zoneList = null;
		List<House> houseList = null;
		zoneList = zoneService.queryAll();
		if (CollectionUtils.isEmpty(zoneList)) {
			return "sensor/list";
		}
		if(null == zoneId){
			zoneId = zoneList.get(0).getZoneId();
		}
		houseList = houseService.findAllByZoneId(zoneId);
		if (CollectionUtils.isEmpty(houseList)) {
			return "sensor/list";
		}
		if (null == houseId) {
			houseId = houseList.get(0).getId();
		}

		model.addAttribute("houseId",houseId);
		model.addAttribute("houseList", houseList);
		model.addAttribute("zoneList", zoneList);
		model.addAttribute("zoneId",zoneId);
//		List<Sensor> sensorList = sensorService.selectByHouseId(houseId);
		List<SensorVo> sensorVoList = sensorService.selectVoByHouseId(houseId);
		model.addAttribute("sensorVoList",sensorVoList);
		
		List<Gateway> gatewayList = gatewayService.selectByZoneId(zoneId);
		model.addAttribute("gatewayList", gatewayList);
		
		List<SensorType> typeList = sensorService.selectTypeList();
		model.addAttribute("typeList", typeList);
		return "sensor/list";
	}

	@RequestMapping("/insert.do")
	public String insert(Sensor sensor,NodeConfig config) {
		sensorService.insert(sensor);
		config.setSensorId(sensor.getId());
		nodeConfigService.replace(config);
//		return "forward:/sensor/list.do";
		return "forward:/sensor/list.do";
	}

	@RequestMapping("/delete.do")
	public @ResponseBody String delete(Integer id) {
		sensorService.delete(id);
		return "删除成功!";
	}

	@RequestMapping("/update.do")
	public String update(Sensor sensor,NodeConfig config) {
		sensorService.update(sensor);
		config.setSensorId(sensor.getId());
		nodeConfigService.replace(config);
		return "forward:/sensor/list.do";
	}

	@RequestMapping("/selectById.do")
	public @ResponseBody Sensor selectById(Integer id) {
		return sensorService.selectById(id);
	}

	@RequestMapping("/selectAll.do")
	public @ResponseBody List<Sensor> selectAll() {
		return sensorService.selectAll();
	}
	
	@RequestMapping("typeList.do")
	public String sensorTypeList(Model model){
		List<SensorType> typeList = sensorService.selectTypeList();
		model.addAttribute("typeList", typeList);
		return "sensor/sensorType";
	}
	
	@RequestMapping("insertType.do")
	public String insertType(Model model,SensorType type){
		sensorService.insertType(type);
		return "forward:typeList.do";
	}
	@RequestMapping("updateType.do")
	public String updateType(Model model,SensorType type){
		sensorService.updateType(type);
		return "forward:typeList.do";
	}
	@RequestMapping("deleteType.do")
	public String deleteType(Model model,Integer id){
		sensorService.deleteType(id);
		return "forward:typeList.do";
	}
	
	/*
	 * 气象设置页面
	 */
	@RequestMapping("/climaticSettingList.do")
	public String climaticSettingList(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		
		ClimaticSetting climaticSetting = sensorService.queryClimaticSetting(zoneId);
		String climaticSettingJson = JSONArray.toJSONString(climaticSetting);
		List<ClimaticDataType> climaticDataTypes = sensorService.queryClimaticDataTypes(zoneId);
		
		model.addAttribute("user",user);
		model.addAttribute("climaticSettingJson",climaticSettingJson);
		model.addAttribute("climaticDataTypes",climaticDataTypes);
		model.addAttribute("climaticSetting",climaticSetting);
		return "/sensor/climaticSetting";
		
	}
	/*
	 * saveOrUpdateClimaticSetting.do
	 * 保存或更新气象设置
	 */
	@RequestMapping("/saveOrUpdateClimaticSetting.do")
	public String saveOrUpdateClimaticSetting(Model model,HttpSession session ,ClimaticSetting climaticSetting){
		
		String msg = sensorService.saveOrUpdateClimaticSetting(climaticSetting);
		model.addAttribute("msg",msg);
		return "forward:/sensor/climaticSettingList.do";
	}
	
	@RequestMapping("deleteClimaticNode.do")
	public @ResponseBody String deleteClimaticNode(Integer id){
		System.out.println("11111111111111111");
		sensorService.deleteClimaticNodeById(id);
		return "删除成功!";
	}
}
