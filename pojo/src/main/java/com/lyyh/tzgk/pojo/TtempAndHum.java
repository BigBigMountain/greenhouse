package com.lyyh.tzgk.pojo;

import java.util.Date;

public class TtempAndHum {

	private int id;
	private String emac;
	private String msg;
	private Date time;
	
	private Double soilTemperature;
	private Double soilHumidity;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmac() {
		return emac;
	}
	public void setEmac(String emac) {
		this.emac = emac;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
		String[] split = msg.split(",");
		this.soilHumidity = Double.parseDouble(split[0]);
		this.soilTemperature = Double.parseDouble(split[1]);
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	
	
	
	
}
