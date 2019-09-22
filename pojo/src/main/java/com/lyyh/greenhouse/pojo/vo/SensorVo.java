package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

import com.lyyh.greenhouse.pojo.SensorType;

public class SensorVo implements Serializable {
	
	private static final long serialVersionUID = -3039511003028189374L;

	private Integer id;
	//传感器分类
	private Integer typeId;
	
	private String name;
	//温室id
	private Integer houseId;
	//区域id
	private Integer zoneId;
	
	private Integer gatewayId;
	
	private Integer position;
	
	private Integer channel;
	
	private Integer excelSort;
	
	private Integer pageSort;
	

	
	public Integer getExcelSort() {
		return excelSort;
	}

	public void setExcelSort(Integer excelSort) {
		this.excelSort = excelSort;
	}

	public Integer getPageSort() {
		return pageSort;
	}

	public void setPageSort(Integer pageSort) {
		this.pageSort = pageSort;
	}

	public Integer getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(Integer gatewayId) {
		this.gatewayId = gatewayId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
