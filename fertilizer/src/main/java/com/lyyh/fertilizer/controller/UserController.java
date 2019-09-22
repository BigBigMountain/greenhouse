package com.lyyh.fertilizer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.service.FertilizerService;
import com.lyyh.fertilizer.service.UserService;
import com.lyyh.greenhouse.pojo.User;


/**
 * 用户管理
 */
@Controller
@RequestMapping(value="/userController")
public class UserController {
	
	@Autowired
	private FertilizerService fertilizerService;
	
	@Autowired
	private UserService userService;
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
//				System.out.println(user);
				session.setAttribute("loginUser", user);
				return "redirect:/fertilizer/index.do";
			}catch(UnknownAccountException e){
//				System.out.println("用户不存在");
				//认证失败,因为输入的username不存在
				return "redirect:/";
			}catch (IncorrectCredentialsException e) {
//				System.out.println("用户密码错误");
				e.printStackTrace();
				//认证失败,因为密码输入错误
				return "redirect:/";
			}catch(Exception e){
				return "redirect:/";
			}
	}
	@RequestMapping(value="/appLogin.do")
	public @ResponseBody 
	Map<String,Object> appLogin(String username,String password){
		String msg = "";
		String code="100";
		Map<String, Object> data = new HashMap<String, Object>();
		//获得shiro的Subject对象，代表当前用户
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
			User user = (User) subject.getPrincipal();
			String company = user.getCompany();
			data.put("company", company);
			code="200";
			msg="登陆成功";
		}catch(UnknownAccountException e){
			//认证失败,因为输入的username不存在
			msg="该用户不存在";
		}catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			//认证失败,因为密码输入错误
			msg="用户密码错误";
		}catch(Exception e){
			msg="登录错误,请重新登录";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", msg);
		map.put("code", code);
		if(data.isEmpty()){
			map.put("data", "");
		}else{
			map.put("data", data);
		}
		return map;
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
	
	/*
	 * 注册
	 */
	@RequestMapping("/register.do")
	public @ResponseBody 
	Map<String,Object> register(String username,String password,String registerCode){
		String msg = "";
		String code="100";
		String data ="";
		if(username == null || username==""){
			msg= "用户名不能为空";
		}else if(password == null || password == ""){
			msg= "密码不能为空";
		}else if(registerCode == null || registerCode == ""){
			msg= "注册码不能为空";
		}else{
			User user2 = userService.findByUsername(username);
			if(user2 != null){
				msg="该用户名已经存在,请重新输入";
			}else{
				Fertilizer fertilizer = fertilizerService.selectByDtuCode(registerCode);
				if(fertilizer == null){
					msg= "注册码不正确,请重新输入";
				}else{
					try{
						User user = new User();
						user.setUsername(username);
						user.setPassword(password);
						userService.insertOne(user);
					} catch(Exception e){
						msg= "系统繁忙,注册失败,请稍后重新注册";
					}
					msg= "注册成功,请返回登录!";
					code="200";
				}
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg", msg);
		map.put("code", code);
		map.put("data", data);
		return map;
	}
/*	@RequestMapping("/register.do")
	public void register(String username,String password,String registerCode,HttpServletResponse response) throws Exception{
		String msg = "";
		String code="100";
		String data ="";
		if(username == null || username==""){
			msg= "用户名不能为空";
		}else if(password == null || password == ""){
			msg= "密码不能为空";
		}else if(registerCode == null || registerCode == ""){
			msg= "注册码不能为空";
		}else{
			User user2 = userService.findByUsername(username);
			if(user2 != null){
				msg="该用户名已经存在,请重新输入";
			}else{
				Fertilizer fertilizer = fertilizerService.selectByDtuCode(registerCode);
				if(fertilizer == null){
					msg= "注册码不正确,请重新输入";
				}else{
					try{
						User user = new User();
						user.setUsername(username);
						user.setPassword(password);
						userService.insertOne(user);
					} catch(Exception e){
						msg= "系统繁忙,注册失败,请稍后重新注册";
					}
					msg= "恭喜您,注册成功!";
					code="200";
				}
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("msg", msg);
		jo.put("code", code);
		jo.put("data", data);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
*/}
