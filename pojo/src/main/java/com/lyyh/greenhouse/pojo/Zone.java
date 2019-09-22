package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class Zone implements Serializable {

	private Integer zoneId;
	private String zoneName;
	private String zoneAddr;
	private String zoneIp;
	
	
	
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getZoneAddr() {
		return zoneAddr;
	}
	public void setZoneAddr(String zoneAddr) {
		this.zoneAddr = zoneAddr;
	}
	public String getZoneIp() {
		return zoneIp;
	}
	public void setZoneIp(String zoneIp) {
		this.zoneIp = zoneIp;
	}
	@Override
	public String toString() {
		return "Zone [zoneId=" + zoneId + ", zoneName=" + zoneName + ", zoneAddr=" + zoneAddr + ", zoneIp=" + zoneIp
				+ "]";
	}
	
	
}
