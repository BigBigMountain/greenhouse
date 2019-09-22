package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class Camera_GH implements Serializable {

	//主键id
	private Integer ca_id;
	//编号
	private String ca_code;
	//摄像头名称
	private String ca_name;
	//摄像头ip地址
	private String ca_ip;
	//摄像头端口号
	private String ca_port;
	//摄像头用户名
	private String ca_username;
	//摄像头密码
	private String ca_password;
	//温室编号
	private Integer houseId;
	//温室所属区域
	private String zoneName;
	//http端口号
	private String ca_httpPort;
	//图片
	private String ca_pic;
	public Integer getCa_id() {
		return ca_id;
	}
	public void setCa_id(Integer ca_id) {
		this.ca_id = ca_id;
	}
	public String getCa_code() {
		return ca_code;
	}
	public void setCa_code(String ca_code) {
		this.ca_code = ca_code;
	}
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}
	public String getCa_ip() {
		return ca_ip;
	}
	public void setCa_ip(String ca_ip) {
		this.ca_ip = ca_ip;
	}
	public String getCa_port() {
		return ca_port;
	}
	public void setCa_port(String ca_port) {
		this.ca_port = ca_port;
	}
	public String getCa_username() {
		return ca_username;
	}
	public void setCa_username(String ca_username) {
		this.ca_username = ca_username;
	}
	public String getCa_password() {
		return ca_password;
	}
	public void setCa_password(String ca_password) {
		this.ca_password = ca_password;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getCa_httpPort() {
		return ca_httpPort;
	}
	public void setCa_httpPort(String ca_httpPort) {
		this.ca_httpPort = ca_httpPort;
	}
	public String getCa_pic() {
		return ca_pic;
	}
	public void setCa_pic(String ca_pic) {
		this.ca_pic = ca_pic;
	}
	@Override
	public String toString() {
		return "Camera [ca_id=" + ca_id + ", ca_code=" + ca_code + ", ca_name=" + ca_name + ", ca_ip=" + ca_ip
				+ ", ca_port=" + ca_port + ", ca_username=" + ca_username + ", ca_password=" + ca_password
				+ ", houseId=" + houseId + ", zoneName=" + zoneName + ", ca_httpPort=" + ca_httpPort + ", ca_pic="
				+ ca_pic + "]";
	}
	
	
	
	
	
}
