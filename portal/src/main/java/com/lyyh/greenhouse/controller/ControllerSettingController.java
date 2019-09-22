package com.lyyh.greenhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyyh.greenhouse.pojo.GhController;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.ControllerSettingService;

@Controller
@RequestMapping("/controllerSetting")
public class ControllerSettingController {

	public static byte mbzType[]={	1,1,1,1,0,2,	1,1,1,1,0,2,	1,1,1,1,0,2,	1,1,1,1,0,2,
									1,1,1,1,0,1,	1,1,1,1,0,1,	1,1,1,1,0,1,	1,1,1,1,0,1,
									1,1,1,1,0,1,	1,1,1,1,0,1,	1,1,1,1,0,1,	1,1,1,1,0,1,
									0,2,0,2,0,1,0,1};
	public static byte warningType[]={	1,
											1,0,2,0,2,1,	1,0,2,0,2,1,
											1,0,1,0,1,1,	1,0,1,0,1,1,
											1,0,1,0,1,1,	1,0,1,0,1,1,
										1};
	public static byte windowType[]={1,1,1,1,1,	1,1,0,2,1,	0,2,1,0,2,	1,0,1,0,1,	1,1,1,1,1,	1,1,1};
	
	public static byte exSunshadeType[]={1,1,1,1,1,	3,1,3,1,3,	1,3,1,1,1,	0,1,1,1,1,	1,1};
	
	public static byte inSunshadeType[]={	1,1,1,1,1,	3,3,1,1,1,	
											1,1,1,1,1,	1,1,1,1,0,	1,0,1,1,3,	3,1};
	
	public static byte fanAndPadType[]={1,1,1,1,1,	1,0,2,1,1,
										1,1,1,1,1,	1,1,1,1,1,
										1,1,1,1,1,	1,1,1,1,1,
										1,1,1,1,1,	1,1,1,1,1,
										1,1,1,1,1,	1,0,2,0,2,
										1,1,1,1,1,	1,1,1,1};
	
	public static byte sirculatingFanType[]={	1,1,1,1,1,	1,1,1,1,1,
												1,1,1,1,1,	1,1,1,1,1,
												1,1,1,0,2,	0,2,1};
	
	public static byte lightType[]={1,1,1,1,1,	1,1,1,1,1,	1,1,1,1,1,	1,1,1,1,1,
									1,1,1,1,1,	1,1,1,1,1,	1,1,1,1,1,	1,1,1,1,1,
									1,1,1,1,1,	1,1,1,1,1,	1,1,1,1,1,	1,1,1,3,3,
									1,1,1,1,1,	1,1,3,3,1,	1,1,1,1,1,	1,3,3,1};
	
	public static byte CO2Type[]={	1,1,0,1,0,	1,1,1,1,1,
									0,2,0,2,1,	1,3,3,1};
	
	public static byte irrigationType[]={	1,1,1,1,1,	1,1,1,1,1,
											1,0,1,1,1,	0,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1};
	
	public static byte sIrrigationType[]={	1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1,1,1,
											1,1,1,1,1,	1,1,1};
	
	public static byte upSprayType[] = {1,1,0,2,0,	2,1,1,3,3,
										1,1,1,0,2,	0,2,1,1,1};
	
	public static byte warmingType[] = {2,2,0,2,0,	2,1,1,1};
	
	public static byte manualControlType[] = {	1,1,1,1,1,	1,1,1,1,1,
												1,1,1,1,1,	1,1,1,1,1,
												1,1,1,1,1,	1,1,1,1,1,
												1,1,1,1,1,	1,1,1,1,1,
												1,1,1,1,1,	1,1,1,1,1,
												1,1,1,1,1,	1,1,1,1};
	
	public static byte fogType[] = {1,1,1,1,0,	1,1,0,1,1,
									0,1,1};
	
	public static byte timeType[] = {1,1,1,1,1,	1,1};
	@Autowired
	ControllerSettingService controllerSettingService;
	
	/*
	 * 控制器列表
	 */
	@RequestMapping("/controllerList.do")
	public String controllerList(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		List<GhController> controllers = controllerSettingService.queryHouseLeftJoinControllerByZoneId(zoneId);
		model.addAttribute("controllers",controllers);
		
		return "controllerSetting/controllerList";
	}
	
	/*
	 * 保存或更新控制器参数
	 */
	@RequestMapping("/saveOrUpdateController.do")
	public String saveOrUpdateController(Model model,HttpSession session,GhController controller){
		Integer id = controller.getId();
		if(id == null || id == 0){
			String msg = controllerSettingService.saveController(controller);
			model.addAttribute("msg",msg);
		}else{
			String msg = controllerSettingService.updateController(controller);
			model.addAttribute("msg",msg);
		}
		User user = (User) session.getAttribute("loginUser");
		List<GhController> controllers = controllerSettingService.queryControllersByZoneId(user.getZoneId());
		session.setAttribute("controllers", controllers);
		return "forward:/controllerSetting/controllerList.do";
	}
	
	
	private List<GhController> controllersToSession(HttpSession session){
		List<GhController> controllers = (List<GhController>) session.getAttribute("controllers");
		if(controllers == null){
			User user = (User) session.getAttribute("loginUser");
			controllers = controllerSettingService.queryControllersByZoneId(user.getZoneId());
			session.setAttribute("controllers", controllers);
		}
		return controllers;
	}
	
	private Integer controllerIdToSession(HttpSession session,Integer controllerId){
		if(controllerId == null){
			controllerId = (Integer) session.getAttribute("controllerId");
			if(controllerId == null){
				List<GhController> controllers = controllersToSession(session);
				if(controllers!=null && controllers.size()!=0){
					controllerId = controllers.get(0).getId();
				}
			}
		}
		session.setAttribute("controllerId", controllerId);
		return controllerId;
	}
	
	private void toPage(Model model,HttpSession session,Integer controllerId,byte read[],byte type[]){
		controllerId = controllerIdToSession(session,controllerId);
		if(controllerId != null){
			try {
				List<Integer> values = controllerSettingService.readFromController(controllerId,read,type);
//				System.out.println(values);
				model.addAttribute("values",values);
			} catch (Exception e) {
				String msg = "连接中断,请检查控制器ip/端口号/通讯状态是否正确";
				model.addAttribute("msg",msg);
			} 
		}
	}
	
	private void toUpdate(Model model,Integer controllerId,int Values[],byte type[],byte writehead[]){
		if(null != controllerId){
			String msg = null;
			try {
				controllerSettingService.writeToController(controllerId,Values,type,writehead);
				msg="修改成功";
			} catch (IOException e) {
				msg = "修改失败:连接中断,请检查控制器ip/端口号/通讯状态是否正确";
			}
			model.addAttribute("msg",msg);
		}
	}
	
	/*
	 * 目标值页面
	 */
	@RequestMapping("/toTargetValue.do")
	public String toTargetValue(Model model,HttpSession session,Integer controllerId){
		byte mbzRead[]={0x15,0x01,0x00,0x00,0x00,0x06,0x01,0x03,0x00,0x64,0x00,0x50};
		toPage(model, session, controllerId, mbzRead, mbzType);
		return "controllerSetting/targetValue";
	}
	@RequestMapping("/updateTargetValue.do")
	public String updateTargetValue(Model model,Integer controllerId,int targetValues[]){
		byte mbzWriteHead[] = {0x15, 0x01, 0x00, 0x00, 0x00, (byte)0xA7, 0x01, 0x10, 0x00, 0x64, 0x00, 0x50, (byte)0xA0};
		toUpdate(model, controllerId, targetValues, mbzType, mbzWriteHead);
		return "forward:/controllerSetting/toTargetValue.do";
	}
	
//	
//	/*
//	 * 目标值页面
//	 */
//	@RequestMapping("/toTime.do")
//	public String toTime(Model model,HttpSession session,Integer controllerId){
////		byte timeRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x00 ,(byte)0xB4 ,0x00 ,0x07};
////		toPage(model, session, controllerId, timeRead, timeType);
//		return "controllerSetting/time";
//	}
//	@RequestMapping("/updateTime.do")
//	public String updateTime(Model model,Integer controllerId,int timeValues[]){
//		byte timeWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x15 ,0x01 ,0x10 ,0x00 ,(byte)0xB4 ,0x00 ,0x07 ,0x0E};
//		toUpdate(model, controllerId, timeValues, timeType, timeWriteHead);
//		return "forward:/controllerSetting/toTime.do";
//	}
//	
	
	/*
	 * 报警
	 */
	@RequestMapping("/toWarning.do")
	public String toWarning(Model model,HttpSession session,Integer controllerId){
		byte warningRead[]={0x15, 0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x00 ,(byte)0xC8 ,0x00 ,0x26};
		toPage(model, session, controllerId, warningRead, warningType);
		return "controllerSetting/warning";
	}
	@RequestMapping("/updateWarning.do")
	public String updateWarning(Model model,Integer controllerId,int warningValues[]){
		byte warningWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x53 ,0x01 ,0x10 ,0x00 ,(byte)0xC8 ,0x00 ,0x26 ,0x4C};
		toUpdate(model, controllerId, warningValues, warningType, warningWriteHead);
		return "forward:/controllerSetting/toWarning.do";
	}
	
	/*
	 * 通风窗1,2,侧
	 */
	@RequestMapping("/toWindow1.do")
	public String toWindow1(Model model,HttpSession session,Integer controllerId){
		byte windowRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x00 ,(byte)0xFA ,0x00 ,0x1C};
		toPage(model, session, controllerId, windowRead, windowType);
		return "controllerSetting/window1";
	}
	
	@RequestMapping("/updateWindow1.do")
	public String updateWindow1(Model model,Integer controllerId,int windowValues[]){
		byte windowWriteHead[] = {0x15, 0x01, 0x00 ,0x00 ,0x00 ,0x3F ,0x01 ,0x10 ,0x00 ,(byte)0xFA ,0x00 ,0x1C ,0x38};
		toUpdate(model, controllerId, windowValues, windowType, windowWriteHead);
		return "forward:/controllerSetting/toWindow1.do";
	}
	@RequestMapping("/toWindow2.do")
	public String toWindow2(Model model,HttpSession session,Integer controllerId){
		byte windowRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x01 ,0x22 ,0x00 ,0x1C};
		toPage(model, session, controllerId, windowRead, windowType);
		return "controllerSetting/window2";
	}
	
	@RequestMapping("/updateWindow2.do")
	public String updateWindow2(Model model,Integer controllerId,int windowValues[]){
		byte windowWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x3F ,0x01 ,0x10 ,0x01 ,0x22  ,0x00 ,0x1C ,0x38};
		toUpdate(model, controllerId, windowValues, windowType, windowWriteHead);
		return "forward:/controllerSetting/toWindow2.do";
	}
	@RequestMapping("/toWindowSide.do")
	public String toWindowSide(Model model,HttpSession session,Integer controllerId){
		byte windowRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x01 ,0x4A ,0x00 ,0x1C};
		toPage(model, session, controllerId, windowRead, windowType);
		return "controllerSetting/windowSide";
	}
	@RequestMapping("/updateWindowSide.do")
	public String updateWindowSide(Model model,Integer controllerId,int windowValues[]){
		byte windowWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x3F ,0x01 ,0x10 ,0x01 ,0x4A  ,0x00 ,0x1C ,0x38};
		toUpdate(model, controllerId, windowValues, windowType, windowWriteHead);
		return "forward:/controllerSetting/toWindowSide.do";
	}
	
	/*
	 * 外遮阳
	 */
	@RequestMapping("/toExSunshade.do")
	public String toExSunshade(Model model,HttpSession session,Integer controllerId){
		byte exSunshadeRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x01 ,0x72 ,0x00 ,0x1A};
		toPage(model, session, controllerId, exSunshadeRead, exSunshadeType);
		return "controllerSetting/exSunshade";
	}
	@RequestMapping("/updateExSunshade.do")
	public String updateExSunshade(Model model,Integer controllerId,int exSunshadeValues[]){
		byte exSunshadeWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x3B ,0x01 ,0x10 ,0x01 ,0x72  ,0x00 ,0x1A ,0x34};
		toUpdate(model, controllerId, exSunshadeValues, exSunshadeType, exSunshadeWriteHead);
		return "forward:/controllerSetting/toExSunshade.do";
	}
	
	/*
	 * 内遮阳
	 */
	@RequestMapping("/toInSunshade.do")
	public String toInSunshade(Model model,HttpSession session,Integer controllerId){
		byte inSunshadeRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x01 ,(byte)0x9A ,0x00 ,0x1F};
		toPage(model, session, controllerId, inSunshadeRead, inSunshadeType);
		return "controllerSetting/inSunshade";
	}
	@RequestMapping("/updateInSunshade.do")
	public String updateInSunshade(Model model,Integer controllerId,int inSunshadeValues[]){
		byte inSunshadeWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x45 ,0x01 ,0x10 ,0x01 ,(byte)0x9A ,0x00 ,0x1F ,0x3E};
		toUpdate(model, controllerId, inSunshadeValues, inSunshadeType, inSunshadeWriteHead);
		return "forward:/controllerSetting/toInSunshade.do";
	}
	
	/*
	 * 内遮阳
	 */
	@RequestMapping("/toFanAndPad.do")
	public String toFanAndPad(Model model,HttpSession session,Integer controllerId){
		byte fanAndPadRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x01 ,(byte)0xCC ,0x00 ,0x3B};
		toPage(model, session, controllerId, fanAndPadRead, fanAndPadType);
		return "controllerSetting/fanAndPad";
	}
	@RequestMapping("/updateFanAndPad.do")
	public String updateFanAndPad(Model model,Integer controllerId,int fanAndPadValues[]){
		byte fanAndPadWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x7D ,0x01 ,0x10 ,0x01 ,(byte)0xCC  ,0x00 ,0x3B ,0x76};
		toUpdate(model, controllerId, fanAndPadValues, fanAndPadType, fanAndPadWriteHead);
		return "forward:/controllerSetting/toFanAndPad.do";
	}
	
	/*
	 * 内遮阳
	 */
	@RequestMapping("/toSirculatingFan.do")
	public String toSirculatingFan(Model model,HttpSession session,Integer controllerId){
		byte sirculatingFanRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x02 ,(byte)0xB2 ,0x00 ,0x1C};
		toPage(model, session, controllerId, sirculatingFanRead, sirculatingFanType);
		return "controllerSetting/sirculatingFan";
	}
	@RequestMapping("/updateSirculatingFan.do")
	public String updateSirculatintFan(Model model,Integer controllerId,int sirculatingFanValues[]){
		byte sirculatingFanWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x3F ,0x01 ,0x10 ,0x02 ,(byte)0xB2  ,0x00 ,0x1C ,0x38};
		toUpdate(model, controllerId, sirculatingFanValues, sirculatingFanType, sirculatingFanWriteHead);
		return "forward:/controllerSetting/toSirculatingFan.do";
	}
	
	/*
	 * 补光灯
	 */
	@RequestMapping("/toLight.do")
	public String toLight(Model model,HttpSession session,Integer controllerId){
		byte lightRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x02 ,0x1C ,0x00 ,0x55};
		toPage(model, session, controllerId, lightRead, lightType);
		return "controllerSetting/light";
	}
	@RequestMapping("/updateLight.do")
	public String updateLight(Model model,Integer controllerId,int lightValues[]){
		byte lightWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,(byte)0xB1 ,0x01 ,0x10 ,0x02 ,0x1C  ,0x00 ,0x55 ,(byte)0xAA};
		toUpdate(model, controllerId, lightValues, lightType, lightWriteHead);
		return "forward:/controllerSetting/toLight.do";
	}
	
	/*
	 * CO2
	 */
	@RequestMapping("/toCO2.do")
	public String toCO2(Model model,HttpSession session,Integer controllerId){
		byte CO2Read[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x02 ,(byte)0x8A ,0x00 ,0x15};
		toPage(model, session, controllerId, CO2Read, CO2Type);
		return "controllerSetting/co2";
	}
	@RequestMapping("/updateCO2.do")
	public String updateCO2(Model model,Integer controllerId,int CO2Values[]){
		byte CO2WriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x31 ,0x01 ,0x10 ,0x02 ,(byte)0x8A ,0x00 ,0x15 ,0x2A};
		toUpdate(model, controllerId, CO2Values, CO2Type, CO2WriteHead);
		return "forward:/controllerSetting/toCO2.do";
	}
	
	/*
	 * 灌溉控制1
	 */
	@RequestMapping("/toIrrigation1.do")
	public String toIrrigation1(Model model,HttpSession session,Integer controllerId){
		byte irrigationRead1[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x02 ,(byte)0xDA ,0x00 ,0x21};
		toPage(model, session, controllerId, irrigationRead1, irrigationType);
		return "controllerSetting/irrigation1";
	}
	@RequestMapping("/updateIrrigation1.do")
	public String updateIrrigation1(Model model,Integer controllerId,int irrigationValues[]){
		byte irrigationWriteHead1[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x49 ,0x01 ,0x10 ,0x02 ,(byte)0xDA  ,0x00 ,0x21 ,0x42};
		toUpdate(model, controllerId, irrigationValues, irrigationType, irrigationWriteHead1);
		return "forward:/controllerSetting/toIrrigation1.do";
	}
	
	/*
	 * 灌溉控制2
	 */
	@RequestMapping("/toIrrigation2.do")
	public String toIrrigation2(Model model,HttpSession session,Integer controllerId){
		byte irrigationRead2[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x03 ,0x0C ,0x00 ,0x21};
		toPage(model, session, controllerId, irrigationRead2, irrigationType);
		return "controllerSetting/irrigation2";
	}
	@RequestMapping("/updateIrrigation2.do")
	public String updateIrrigation2(Model model,Integer controllerId,int irrigationValues[]){
		byte irrigationWriteHead2[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x49 ,0x01 ,0x10 ,0x03 ,0x0C ,0x00 ,0x21 ,0x42};
		toUpdate(model, controllerId, irrigationValues, irrigationType, irrigationWriteHead2);
		return "forward:/controllerSetting/toIrrigation2.do";
	}
	
	/*
	 * 灌溉控制3
	 */
	@RequestMapping("/toIrrigation3.do")
	public String toIrrigation3(Model model,HttpSession session,Integer controllerId){
		byte irrigationRead3[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x03 ,0x3E ,0x00 ,0x21};
		toPage(model, session, controllerId, irrigationRead3, irrigationType);
		return "controllerSetting/irrigation3";
	}
	@RequestMapping("/updateIrrigation3.do")
	public String updateIrrigation3(Model model,Integer controllerId,int irrigationValues[]){
		byte irrigationWriteHead3[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x49 ,0x01 ,0x10 ,0x03 ,0x3E  ,0x00 ,0x21 ,0x42};
		toUpdate(model, controllerId, irrigationValues, irrigationType, irrigationWriteHead3);
		return "forward:/controllerSetting/toIrrigation3.do";
	}
	
	/*
	 * 灌溉控制4
	 */
	@RequestMapping("/toIrrigation4.do")
	public String toIrrigation4(Model model,HttpSession session,Integer controllerId){
		byte irrigationRead4[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x03 ,0x70 ,0x00 ,0x21};
		toPage(model, session, controllerId, irrigationRead4, irrigationType);
		return "controllerSetting/irrigation4";
	}
	@RequestMapping("/updateIrrigation4.do")
	public String updateIrrigation4(Model model,Integer controllerId,int irrigationValues[]){
		byte irrigationWriteHead4[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x49 ,0x01 ,0x10 ,0x03 ,0x70  ,0x00 ,0x21 ,0x42};
		toUpdate(model, controllerId, irrigationValues, irrigationType, irrigationWriteHead4);
		return "forward:/controllerSetting/toIrrigation4.do";
	}
	
	/*
	 * 轮灌控制
	 */
	@RequestMapping("/toIrrigationR.do")
	public String toIrrigationR(Model model,HttpSession session,Integer controllerId){
		byte irrigationReadR[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x03 ,(byte)0xA2 ,0x00 ,0x58};
		toPage(model, session, controllerId, irrigationReadR, sIrrigationType);
		return "controllerSetting/irrigationR";
	}
	@RequestMapping("/updateIrrigationR.do")
	public String updateIrrigationR(Model model,Integer controllerId,int irrigationValues[]){
		byte irrigationWriteHeadR[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,(byte)0xB7 ,0x01 ,0x10 ,0x03 ,(byte)0xA2 ,0x00 ,0x58 ,(byte)0xB0};
		toUpdate(model, controllerId, irrigationValues, sIrrigationType, irrigationWriteHeadR);
		return "forward:/controllerSetting/toIrrigationR.do";
	}
	
	/*
	 * 上喷喷淋
	 */
	@RequestMapping("/toUpSpray.do")
	public String toUpSpray(Model model,HttpSession session,Integer controllerId){
		byte upSprayRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x04 ,0x06 ,0x00 ,0x16};
		toPage(model, session, controllerId, upSprayRead, upSprayType);
		return "controllerSetting/upSpray";
	}
	@RequestMapping("/updateUpSpray.do")
	public String updateUpSpray(Model model,Integer controllerId,int upSprayValues[]){
		byte upSprayWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x33 ,0x01 ,0x10 ,0x04 ,0x06  ,0x00 ,0x16 ,0x2C};
		toUpdate(model, controllerId, upSprayValues, upSprayType, upSprayWriteHead);
		return "forward:/controllerSetting/toUpSpray.do";
	}
	
	
	/*
	 * 微雾
	 */
	@RequestMapping("/toFog.do")
	public String toFog(Model model,HttpSession session,Integer controllerId){
		byte fogRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x04 ,0x2E ,0x00 ,0x0D};
		toPage(model, session, controllerId, fogRead, fogType);
		return "controllerSetting/fog";
	}
	@RequestMapping("/updateFog.do")
	public String updateFog(Model model,Integer controllerId,int fogValues[]){
		byte fogWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x21 ,0x01 ,0x10 ,0x04 ,0x2E ,0x00 ,0x0D ,0x1A};
		toUpdate(model, controllerId, fogValues, fogType, fogWriteHead);
		return "forward:/controllerSetting/toFog.do";
	}
	
	
	/*
	 * 加热控制
	 */
	@RequestMapping("/toWarming.do")
	public String toWarming(Model model,HttpSession session,Integer controllerId){
		byte warmingRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x04 ,0x60 ,0x00 ,0x09};
		toPage(model, session, controllerId, warmingRead, warmingType);
		return "controllerSetting/warming";
	}
	@RequestMapping("/updateWarming.do")
	public String updateWarming(Model model,Integer controllerId,int warmingValues[]){
		byte warmingWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x19 ,0x01 ,0x10 ,0x04 ,0x60  ,0x00 ,0x09 ,0x12};
		toUpdate(model, controllerId, warmingValues, warmingType, warmingWriteHead);
		return "forward:/controllerSetting/toWarming.do";
	}
	
	/*
	 * 手动控制
	 */
	@RequestMapping("/toManualControl.do")
	public String toManualControl(Model model,HttpSession session,Integer controllerId){
		byte manualControlRead[]={0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x06 ,0x01 ,0x03 ,0x04 ,0x74 ,0x00 ,0x3B};
		toPage(model, session, controllerId, manualControlRead, manualControlType);
		return "controllerSetting/manualControl";
	}
	@RequestMapping("/updateManualControl.do")
	public String updateManualControl(Model model,Integer controllerId,int manualControlValues[]){
		byte manualControlWriteHead[] = {0x15 ,0x01 ,0x00 ,0x00 ,0x00 ,0x7D ,0x01 ,0x10 ,0x04 ,0x74 ,0x00 ,0x3B ,0x76};
		toUpdate(model, controllerId, manualControlValues, manualControlType, manualControlWriteHead);
		return "forward:/controllerSetting/toManualControl.do";
	}
	
	
	
	
	
	
	
}
