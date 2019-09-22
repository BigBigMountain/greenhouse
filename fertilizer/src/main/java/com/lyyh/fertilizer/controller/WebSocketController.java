package com.lyyh.fertilizer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.fertilizer.webSocket.SocketHandler;


@RequestMapping("/socket")
@Controller
public class WebSocketController {
	@Autowired
	SocketHandler socketHandler;

	/**
	 * 对单个用户推送消息
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/sendMessageByUser")
	@ResponseBody
	public String sendMessageByUser(@RequestParam Map<String, Object> contents){
		return "success";
	}

	/**
	 * 对所有用户推送消息
	 * 
	 * @return
	 */
	@RequestMapping("/sendMessageByAll")
	@ResponseBody
	public String sendMessageByAll(@RequestParam Map<String, Object> contents) {

		socketHandler.sendMessageToAllUsers(contents.get("msg").toString());
		return "success";
	}
}