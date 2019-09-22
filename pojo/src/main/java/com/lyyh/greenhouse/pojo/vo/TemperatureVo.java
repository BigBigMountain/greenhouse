package com.lyyh.greenhouse.pojo.vo;

import java.util.Date;

public class TemperatureVo {

	private float temperature;
	
	private Date datetime;
	
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
}
