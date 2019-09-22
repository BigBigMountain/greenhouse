package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -4626937616380251217L;
	//主键id
	private Integer userId;
	//用户名
	private String username;
	//密码
	private String password;
	//真实姓名
	private String realname;
	
	private String company;
	//所属区域
	private String zoneName;
	
	private Integer zoneId;

	private Integer controllType;
	
	
	

	

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getControllType() {
		return controllType;
	}

	public void setControllType(Integer controllType) {
		this.controllType = controllType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", company=" + company + ", zoneName=" + zoneName + ", zoneId=" + zoneId + "]";
	}

	
	
	
	
	
}
