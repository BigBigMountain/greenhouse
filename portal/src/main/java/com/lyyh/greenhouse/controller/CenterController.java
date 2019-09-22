package com.lyyh.greenhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal")
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
//		return "main";
		return "right";
	}
/*	@RequestMapping("/left.do")
	public String left(){
		return "left";
	}
	@RequestMapping("/right.do")
	public String right(){
		return "right";
	}*/
	
	
	
	/*
	 * 设备页面	/frame/equipment_main.do
	 */
	@RequestMapping("/frame/equipment_main.do")
	public String equipment_main(){
		return "frame/equipment_main";
	}
	@RequestMapping("/frame/equipment_left.do")
	public String equipment_left(){
		return "frame/equipment_left";
	}
	
	/*
	 * 气象页面
	 */
	@RequestMapping("/frame/climatic_main.do")
	public String climaticData_main(){
		return "frame/climatic_main";
	}
	@RequestMapping("/frame/climatic_left.do")
	public String climaticData_left(){
		return "frame/climatic_left";
	}
	
	
	/*
	 * 室内数据页面
	 */
	@RequestMapping("/frame/houseData_main.do")
	public String houseData_main(){
		return "frame/houseData_main";
	}
	@RequestMapping("/frame/houseData_left.do")
	public String houseData_left(){
		return "frame/houseData_left";
	}


	/*
	 * 监控管理
	 */
	@RequestMapping("/frame/cameraManage_32.do")
	public String camera_32_Test(){
		//return "camera/camera_multi";
		return "forward:/camera/cameraManage_32.do";
	}
	
	/*
	 * 实时数据
	 */
	@RequestMapping("/frame/timeData_main.do")
	public String timeData(){
		return "timeData/listAll";
	}
	
	/*
	 * 系统设置
	 */
	@RequestMapping("/frame/setting_main.do")
	public String setting_main(){
		return "frame/setting_main";
	}
	@RequestMapping("/frame/setting_left.do")
	public String setting_left(){
		return "frame/setting_left";
	}
	/*
	 * 控制器设置
	 */
	@RequestMapping("/frame/controllerSetting_main.do")
	public String controllerSetting_main(){
		return "frame/controllerSetting_main";
	}
	@RequestMapping("/frame/controllerSetting_left.do")
	public String controllerSetting_left(){
		return "frame/controllerSetting_left";
	}
	
	
	/*
	 * 施肥机
	 */
	@RequestMapping("/frame/fertilizer_main.do")
	public String fertilizer_main(){
		return "frame/fertilizer_main";
	}
	@RequestMapping("/frame/fertilizer_left.do")
	public String fertilizer_left(){
		return "frame/fertilizer_left";
	}
}
