package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class Nvr2 implements Serializable{
	
	private Integer nvrId;
	private String nvrName;
	private String nvrIp;
	private Integer nvrPort;
	private String nvrUsername;
	private String nvrPassword;
	private Integer nvrState;
	private Integer zoneId;
	
	
	public Integer getNvrState() {
		return nvrState;
	}
	public void setNvrState(Integer nvrState) {
		this.nvrState = nvrState;
	}
	public Integer getNvrId() {
		return nvrId;
	}
	public void setNvrId(Integer nvrId) {
		this.nvrId = nvrId;
	}
	public String getNvrName() {
		return nvrName;
	}
	public void setNvrName(String nvrName) {
		this.nvrName = nvrName;
	}
	public String getNvrIp() {
		return nvrIp;
	}
	public void setNvrIp(String nvrIp) {
		this.nvrIp = nvrIp;
	}
	public Integer getNvrPort() {
		return nvrPort;
	}
	public void setNvrPort(Integer nvrPort) {
		this.nvrPort = nvrPort;
	}
	public String getNvrUsername() {
		return nvrUsername;
	}
	public void setNvrUsername(String nvrUsername) {
		this.nvrUsername = nvrUsername;
	}
	public String getNvrPassword() {
		return nvrPassword;
	}
	public void setNvrPassword(String nvrPassword) {
		this.nvrPassword = nvrPassword;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	
	
	
	
	
	

}
