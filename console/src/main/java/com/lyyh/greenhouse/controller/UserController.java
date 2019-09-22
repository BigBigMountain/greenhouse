package com.lyyh.greenhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.UserService;
import com.lyyh.greenhouse.service.ZoneService;


/**
 * 用户管理
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ZoneService zoneService;
	
	
	/**
	 * 基于shiro框架完成当前用户的认证操作
	 */
	@RequestMapping(value="/login.do")
	public String login(String username,String password,HttpSession session){
		
			//获得shiro的Subject对象，代表当前用户
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(username, password);
			try{
				subject.login(token);
				User user = (User) subject.getPrincipal();
				session.setAttribute("loginUser", user);
	//			System.out.println(user);
//				return "redirect:/index.jsp";
				return "redirect:/console/index.do";
			}catch(UnknownAccountException e){
				e.printStackTrace();
				//认证失败,因为输入的username不存在
//				return "login";
				return "redirect:/";
			}catch (IncorrectCredentialsException e) {
				e.printStackTrace();
				//认证失败,因为密码输入错误
//				return "login";
				return "redirect:/";
			}
		
	}
	
	
	/*
	 * 推出登录
	 * /user/logout.do
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
//		session.invalidate();
//		return "login";
		return "redirect:/console/index.do";
	}
	
	/*
	 * userList
	 */
	@RequestMapping("/userList.do")
	public String userList(Model model){
		List<Zone> zoneList = zoneService.queryAll();
		List<User> userList = userService.queryAll();
		model.addAttribute("userList",userList);
		model.addAttribute("zoneList",zoneList);
		return "/user/userList";
	}
	
	/*
	 * saveOrUpdateUser.do
	 */
	@RequestMapping("/saveOrUpdateUser.do")
	public String saveOrUpdateZone(Model model,User user){
		if(user.getUserId() == null || user.getUserId() == 0){
			userService.saveUser(user);
		}else {
			userService.updateUser(user);
		}
		return "forward:/user/userList.do";
		
	}
	
	/*
	 * /delUser.do
	 */
	@RequestMapping(value="/delUser.do",produces="text/json;charset=UTF-8")
	public @ResponseBody String delUser(Integer userId){
		userService.delById(userId);
		return "删除成功";
	}
}
