package com.lyyh.fertilizer.backup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.lyyh.fertilizer.backup.service.UserService;
import com.lyyh.greenhouse.pojo.User;

public class BaseController {

	@Autowired
	protected UserService userService;

	public List<User> getUserList(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<User> userList = (List<User>)session.getAttribute("userList");
		if(null == userList){
			userList = userService.queryAll();
			session.setAttribute("userList", userList);
		}
		return userList;
	}
}
