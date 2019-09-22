package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.Date;

public class HouseData implements Serializable{
	//主键id;
	private Integer id;
	//温度1--温度传感器1
	private Double temperature;
	//温度2--温度传感器2
	private Double temperature2;
	//湿度1--湿度传感器1
	private Double humidity;
	//湿度2--湿度传感器2
	private Double humidity2;
	//光照
	private Integer lighting;
	//CO2
	private Integer co2;
	//土壤温度
	private Double soilTemperature;
	//土壤湿度
	private Double soilHumidity;
	//时间
	private Date timeSpan;
	//温室编号
	private Integer houseId;
	
	//温室所属区域
	private String zoneName;
	//温室名称
	private String houseName;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getTemperature() {
		return temperature;
	}


	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}


	public Double getTemperature2() {
		return temperature2;
	}


	public void setTemperature2(Double temperature2) {
		this.temperature2 = temperature2;
	}


	public Double getHumidity() {
		return humidity;
	}


	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}


	public Double getHumidity2() {
		return humidity2;
	}


	public void setHumidity2(Double humidity2) {
		this.humidity2 = humidity2;
	}


	public Integer getLighting() {
		return lighting;
	}


	public void setLighting(Double lighting) {
		if(lighting == null){
			this.lighting = null;
		} else {
			this.lighting = lighting.intValue();
		}
	}


	public Integer getCo2() {
		return co2;
	}


	public void setCo2(Double co2) {
		if(co2 == null){
			this.co2 = null;
		}else{
			this.co2 = co2.intValue();
		}
	}


	public Double getSoilTemperature() {
		return soilTemperature;
	}


	public void setSoilTemperature(Double soilTemperature) {
		this.soilTemperature = soilTemperature;
	}


	public Double getSoilHumidity() {
		return soilHumidity;
	}


	public void setSoilHumidity(Double soilHumidity) {
		this.soilHumidity = soilHumidity;
	}


	public Date getTimeSpan() {
		return timeSpan;
	}


	public void setTimeSpan(Date timeSpan) {
		this.timeSpan = timeSpan;
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


	public String getHouseName() {
		return houseName;
	}


	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}


	@Override
	public String toString() {
		return "HouseData [id=" + id + ", temperature=" + temperature + ", temperature2=" + temperature2 + ", humidity="
				+ humidity + ", humidity2=" + humidity2 + ", lighting=" + lighting + ", co2=" + co2
				+ ", soilTemperature=" + soilTemperature + ", soilHumidity=" + soilHumidity + ", timeSpan=" + timeSpan
				+ ", houseId=" + houseId + ", zoneName=" + zoneName + ", houseName=" + houseName + "]";
	}
	
}
