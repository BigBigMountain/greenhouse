package com.lyyh.fertilizer.backup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.lyyh.fertilizer.backup.service.UserService;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.Zone;


/**
 * 用户管理
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
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
				return "redirect:/fertilizer-backup/index.do";
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
	 * userList
	 */
	@RequestMapping("/userList.do")
	public String userList(Model model){
		List<User> userList = userService.queryAll();
		model.addAttribute("userList",userList);
		return "/user/userList";
	}
	
	/*
	 * saveOrUpdateUser.do
	 */
	@RequestMapping("/saveOrUpdateUser.do")
	public String saveOrUpdateZone(HttpServletRequest request,Model model,User user){
		if(user.getUserId() == null || user.getUserId() == 0){
			userService.saveUser(user);
		}else {
			userService.updateUser(user);
		}
		request.getSession().removeAttribute("userList");
		return "forward:/user/userList.do";
		
	}
	/*
	 * /delUser.do
	 */
	@RequestMapping(value="/delUser.do",produces="text/json;charset=UTF-8")
	public @ResponseBody String delUser(HttpServletRequest request,Integer userId){
		userService.delById(userId);
		request.getSession().removeAttribute("userList");
		return "删除成功";
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
