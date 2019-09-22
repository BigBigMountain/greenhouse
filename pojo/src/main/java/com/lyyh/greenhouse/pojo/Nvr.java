package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class Nvr implements Serializable{
	
	private Integer nvr_id;
	private String nvr_name;
	private String nvr_ipv4;
	private String nvr_ipv6;
	private Integer nvr_port;
	private String nvr_username;
	private String nvr_password;
	private Integer zone_id;
	private String zoneIp;
	
	
	
	public String getZoneIp() {
		return zoneIp;
	}
	public void setZoneIp(String zoneIp) {
		this.zoneIp = zoneIp;
	}
	public Integer getNvr_id() {
		return nvr_id;
	}
	public void setNvr_id(Integer nvr_id) {
		this.nvr_id = nvr_id;
	}
	public String getNvr_name() {
		return nvr_name;
	}
	public void setNvr_name(String nvr_name) {
		this.nvr_name = nvr_name;
	}
	public String getNvr_ipv4() {
		return nvr_ipv4;
	}
	public void setNvr_ipv4(String nvr_ipv4) {
		this.nvr_ipv4 = nvr_ipv4;
	}
	public String getNvr_ipv6() {
		return nvr_ipv6;
	}
	public void setNvr_ipv6(String nvr_ipv6) {
		this.nvr_ipv6 = nvr_ipv6;
	}
	public Integer getNvr_port() {
		return nvr_port;
	}
	public void setNvr_port(Integer nvr_port) {
		this.nvr_port = nvr_port;
	}
	public String getNvr_username() {
		return nvr_username;
	}
	public void setNvr_username(String nvr_username) {
		this.nvr_username = nvr_username;
	}
	public String getNvr_password() {
		return nvr_password;
	}
	public void setNvr_password(String nvr_password) {
		this.nvr_password = nvr_password;
	}
	public Integer getZone_id() {
		return zone_id;
	}
	public void setZone_id(Integer zone_id) {
		this.zone_id = zone_id;
	}
	@Override
	public String toString() {
		return "Nvr [nvr_id=" + nvr_id + ", nvr_name=" + nvr_name + ", nvr_ipv4=" + nvr_ipv4 + ", nvr_ipv6=" + nvr_ipv6
				+ ", nvr_port=" + nvr_port + ", nvr_username=" + nvr_username + ", nvr_password=" + nvr_password
				+ ", zone_id=" + zone_id + "]";
	}
	
	
	
	

}
