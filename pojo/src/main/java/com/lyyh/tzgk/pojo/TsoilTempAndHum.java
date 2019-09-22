package com.lyyh.tzgk.pojo;

import java.math.BigDecimal;

import com.lyyh.fertilizer.pojo.SoilMoisture.SoilTempAndHum;

public class TsoilTempAndHum extends SoilTempAndHum {

	private String emac;
	private int userId;
	private String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
		String[] split = msg.split(",");
		this.humidity = new BigDecimal(split[0]).multiply(new BigDecimal(100)).setScale(2).doubleValue();
//		 = Double.parseDouble(split[0])*100;
		this.temperature = Double.parseDouble(split[1]);
	}
	public String getEmac() {
		return emac;
	}
	public void setEmac(String emac) {
		this.emac = emac;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
