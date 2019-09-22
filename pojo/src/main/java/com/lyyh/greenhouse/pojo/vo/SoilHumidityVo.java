package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class SoilHumidityVo implements Serializable {

	private float soilHumidity;
	
	private Date timeSpan;

	public float getSoilHumidity() {
		return soilHumidity;
	}

	public void setSoilHumidity(float soilHumidity) {
		this.soilHumidity = soilHumidity;
	}

	public Date getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Date timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	
}
