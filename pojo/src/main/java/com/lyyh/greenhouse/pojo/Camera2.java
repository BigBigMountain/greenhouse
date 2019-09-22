package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class Camera2 implements Serializable {

	//主键id
	private Integer cameraId;
	//编号
	private Integer cameraNumber;
	//摄像头名称
	private String cameraName;
	//摄像头ip地址
	private String cameraIp;
	//摄像头端口号
	private Integer cameraHttpPort;
	//摄像头用户名
	private String cameraUsername;
	//摄像头密码
	private String cameraPassword;
	//未知 TODO 不知此物为何方妖孽
	private Integer cameraPort;
	//nvr
	private Integer nvrId;
	//温室id
	private Integer houseId;
	//区域id
	private Integer zoneId;
	
	
	public Integer getCameraId() {
		return cameraId;
	}
	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}
	public String getCameraName() {
		return cameraName;
	}
	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}
	public String getCameraIp() {
		return cameraIp;
	}
	public void setCameraIp(String cameraIp) {
		this.cameraIp = cameraIp;
	}
	public String getCameraUsername() {
		return cameraUsername;
	}
	public void setCameraUsername(String cameraUsername) {
		this.cameraUsername = cameraUsername;
	}
	public String getCameraPassword() {
		return cameraPassword;
	}
	public void setCameraPassword(String cameraPassword) {
		this.cameraPassword = cameraPassword;
	}
	public Integer getNvrId() {
		return nvrId;
	}
	public void setNvrId(Integer nvrId) {
		this.nvrId = nvrId;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Integer getCameraNumber() {
		return cameraNumber;
	}
	public void setCameraNumber(Integer cameraNumber) {
		this.cameraNumber = cameraNumber;
	}
	public Integer getCameraHttpPort() {
		return cameraHttpPort;
	}
	public void setCameraHttpPort(Integer cameraHttpPort) {
		this.cameraHttpPort = cameraHttpPort;
	}
	public Integer getCameraPort() {
		return cameraPort;
	}
	public void setCameraPort(Integer cameraPort) {
		this.cameraPort = cameraPort;
	}
	@Override
	public String toString() {
		return "Camera2 [cameraId=" + cameraId + ", cameraNumber=" + cameraNumber + ", cameraName=" + cameraName
				+ ", cameraIp=" + cameraIp + ", cameraHttpPort=" + cameraHttpPort + ", cameraUsername=" + cameraUsername
				+ ", cameraPassword=" + cameraPassword + ", cameraPort=" + cameraPort + ", nvrId=" + nvrId
				+ ", houseId=" + houseId + ", zoneId=" + zoneId + "]";
	}
	
	
	
}
