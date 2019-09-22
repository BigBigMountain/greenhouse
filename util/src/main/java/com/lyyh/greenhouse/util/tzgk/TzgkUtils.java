package com.lyyh.greenhouse.util.tzgk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyyh.tzgk.pojo.Tequipment;
import com.lyyh.tzgk.pojo.Tvalve;

public class TzgkUtils {

	public static synchronized void initValve(Collection<Tvalve> tvalves) {
		for (Tvalve tvalve : tvalves) {
			// 组织命令
			String httpUrl = getControlUrl(tvalve, 0);
			// 发送命令
			sendHttpUrl(httpUrl);
		}
	}
	
	public static String getControlUrl(Tvalve tvalve, Integer state) {
		Integer uid = LoginToken.getUid();
		String tokey = LoginToken.getTokey();
		//如果没有登录token,则马上登录
		if (uid == null || tokey == null) {
			String jsonString = loginTzgk();
//			System.out.println(jsonString);
			LoginUrl login = JSONObject.parseObject(jsonString, LoginUrl.class);
			uid = login.getId();
			tokey = login.getMsg();
			LoginToken.setToken(uid, tokey);
		}
		ControlCommandUrl command = new ControlCommandUrl();
		String msg = "";
		Integer eid = tvalve.getEid();
		Integer position = tvalve.getPosition();
		if (eid == 6) {
			msg += "00";
		}
		if (eid == 9) {
			if (position == 1) {
				msg += "10";
			}
			if (position == 2) {
				msg += "11";
			}
		}
		if (state == 0) {
			msg += "00";
		}
		if (state == 1) {
			msg += "01";
		}
		command.setUid(uid);
		command.setTokey(tokey);
		command.setEMac(tvalve.getEmac());
		command.setGMac(tvalve.getGmac());
		command.setMsg(msg);
		command.setInstructions(58);
		return command.toString();
	}

	// TODO 发送http命令,没有写返回数据是否成功
	public static StringBuilder sendHttpUrl(String httpUrl) {
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(httpUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			while ((read = in.readLine()) != null) {
				inputLine.append(read + "\r\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return inputLine;
	}
	
	//登录tzgk
	public static String loginTzgk() {
		String loginUrl = getLoginUrl();
//		System.out.println(loginUrl);
		StringBuilder sb = sendHttpUrl(loginUrl);
		String jsonString = sb.toString();
		LoginUrl login = JSONObject.parseObject(jsonString, LoginUrl.class);
		int uid = login.getId();
		String tokey = login.getMsg();
		LoginToken.setToken(uid, tokey);
		return jsonString;
	}
	
	//获取login的url
	public static String getLoginUrl() {
		LoginUrl loginUrl = new LoginUrl();
		loginUrl.setName(TzgkConstant.username);
		loginUrl.setPwd(TzgkConstant.password);
		return loginUrl.toString();
	}
	
	//获取所有设备的url
	public static String getCheckEquipmentUrl(){
		Integer uid = LoginToken.getUid();
		String tokey = LoginToken.getTokey();
		//如果没有登录token,则马上登录
		if (uid == null || tokey == null) {
			String jsonString = loginTzgk();
//			System.out.println(jsonString);
			LoginUrl login = JSONObject.parseObject(jsonString, LoginUrl.class);
			uid = LoginToken.getUid();
			tokey = LoginToken.getTokey();
		}
		CheckEquipmentUrl checkEquipmentUrl = new CheckEquipmentUrl();
		checkEquipmentUrl.setUid(uid);
		checkEquipmentUrl.setTokey(tokey);
		return checkEquipmentUrl.toString();
	}
	
	//发送控制指令
	public static void sendCommand(Tvalve tvalve, Integer state) {
		// 组织命令
		String controlUrl = getControlUrl(tvalve, state);
		System.out.println(controlUrl);
		// 发送命令
		StringBuilder jsonString = sendHttpUrl(controlUrl);
	}
	

	/*
	 * 从天正高科那里获取全部设备,封装为List<TEquipment>
	 */
	public static List<Tequipment> getAllTequipment(){
		List<Tequipment> allTequipment = null;
		//组织获取设备的url
		String checkEquipmentUrl = TzgkUtils.getCheckEquipmentUrl();
		//发送url,得到返回结果
		StringBuilder sb = TzgkUtils.sendHttpUrl(checkEquipmentUrl);
		String jsonString = sb.toString();
		if(StringUtils.isBlank(jsonString)){
//			System.out.println("=============================jsonString: 空");
			loginTzgk();
			allTequipment = getAllTequipment();
		} else {
			//封装结果到List<Tequipment>中
			allTequipment = JSONArray.parseArray(jsonString, Tequipment.class);
		}
		return allTequipment;
	}
	
	
	
}
