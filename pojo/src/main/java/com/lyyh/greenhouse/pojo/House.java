package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class House implements Serializable {

	private static final long serialVersionUID = -4885535381378130968L;
	
	private Integer id;
	//温室编号
	private Integer number;
	//温室名称
	private String name;
	//区域id
	private Integer zoneId;
	//区域名称
	private Integer zoneName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Integer getZoneName() {
		return zoneName;
	}
	public void setZoneName(Integer zoneName) {
		this.zoneName = zoneName;
	}
	
}
