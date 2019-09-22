package com.lyyh.tzgk.pojo;

import java.io.Serializable;

public class TgateWay implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer gateWayId;
	private String macCode;
	private String gateWayName;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGateWayId() {
		return gateWayId;
	}
	public void setGateWayId(Integer gateWayId) {
		this.gateWayId = gateWayId;
	}
	public String getMacCode() {
		return macCode;
	}
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	public String getGateWayName() {
		return gateWayName;
	}
	public void setGateWayName(String gateWayName) {
		this.gateWayName = gateWayName;
	}
	
	
}
