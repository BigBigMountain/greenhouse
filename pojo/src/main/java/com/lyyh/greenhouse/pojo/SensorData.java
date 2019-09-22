package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.Date;

public class SensorData implements Serializable {

	private Integer id;
	//传感器id
	private Integer sensorId;
	private Integer sensorTypeId;
	//温室id
	private Integer houseId;
	//区域id
	private Integer zoneId;
	//数据值
	private Double value;
	//单位
	private String unit;
	//时间
	private Date dateTime;
	//数据类型
	private String dataType;
	//传感器类型
	private String sensorType;
	
	private String sensorName;
	
	private Integer ledShow;
	
	private String image;
	
	private Integer pageSort;
	
	private Integer excelSort;
	
	

	public Integer getPageSort() {
		return pageSort;
	}
	public void setPageSort(Integer pageSort) {
		this.pageSort = pageSort;
	}
	public Integer getExcelSort() {
		return excelSort;
	}
	public void setExcelSort(Integer excelSort) {
		this.excelSort = excelSort;
	}
	public Integer getSensorTypeId() {
		return sensorTypeId;
	}
	public void setSensorTypeId(Integer sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getLedShow() {
		return ledShow;
	}
	public void setLedShow(Integer ledShow) {
		this.ledShow = ledShow;
	}
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSensorId() {
		return sensorId;
	}
	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
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
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getSensorType() {
		return sensorType;
	}
	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
	
	
}
