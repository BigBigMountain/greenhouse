package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class SoilTemperatureVo implements Serializable {

	private float soilTemperature;
	
	private Date timeSpan;

	public float getSoilTemperature() {
		return soilTemperature;
	}

	public void setSoilTemperature(float soilTemperature) {
		this.soilTemperature = soilTemperature;
	}

	public Date getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Date timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	
}
