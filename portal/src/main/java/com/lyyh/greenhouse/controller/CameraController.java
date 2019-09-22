package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.CameraService;

@Controller
@RequestMapping("/camera")
public class CameraController {
	

	@Autowired
	private CameraService cameraService;
	
	
	/*
	 * camera/iframe.do
	 */
	 @RequestMapping("/iframe.do")
	 public String to_iframe(){
		 return "camera/iframe";
	 }
	 
	 /*
	  * 研究后的camera
	  * /camera/camera_32_test.do
	  */
	 @RequestMapping("/cameraManage_32.do")
	 public String camera_32_test(HttpSession session,Model model){
		 User user = (User)session.getAttribute("loginUser");
		 String zoneName = user.getZoneName();
		 
		 List<Nvr> nvrs = cameraService.findAllNvrByZoneName(zoneName);
		 String nvrsjson = JSONArray.toJSONString(nvrs);
		 model.addAttribute("nvrsjson",nvrsjson);
		 model.addAttribute("nvrs",nvrs);
//		 int count = cameraService.countAllCamera(zoneName);
		 int row = cameraService.getRow(zoneName);
		 model.addAttribute("row",row);
		 return "/camera/cameraManage_32";
	 }
	 
	 
	 
	 
}
