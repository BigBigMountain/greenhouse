package com.lyyh.greenhouse.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyyh.greenhouse.pojo.User;


/**
 * 用户管理
 */
@Controller
@RequestMapping(value="/userController")
public class UserController {
	
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
				System.out.println(user);
				session.setAttribute("loginUser", user);
				return "redirect:/portal/index.do";
			}catch(UnknownAccountException e){
				System.out.println("用户不存在");
				//认证失败,因为输入的username不存在
				return "redirect:/";
			}catch (IncorrectCredentialsException e) {
				System.out.println("用户密码错误");
				e.printStackTrace();
				//认证失败,因为密码输入错误
				return "redirect:/";
			}catch(Exception e){
				e.printStackTrace();
				return "redirect:/";
			}
		
	}
	
	
	/*
	 * 推出登录
	 * /userController/logout.do
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession session){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
//		session.invalidate();
		return "redirect:/";
	}
}
