package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.Date;

public class Sensor implements Serializable {
	
	private static final long serialVersionUID = -3039511003028189374L;

	private Integer id;
	//传感器分类
	private Integer typeId;
	
	private SensorType type;
	//名称
	private String name;
	//温室id
	private Integer houseId;
	//区域id
	private Integer zoneId;
	
	private Integer excelSort;
	
	private Integer pageSort;
	private Date createTime;
	private Date updateTime;
	private Byte isDel;
	
	
	
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
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

	

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
