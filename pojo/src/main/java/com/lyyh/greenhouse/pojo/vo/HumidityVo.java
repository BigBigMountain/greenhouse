package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class HumidityVo implements Serializable {

	private float humidity;
	
	private Date timeSpan;

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public Date getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Date timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	
}
