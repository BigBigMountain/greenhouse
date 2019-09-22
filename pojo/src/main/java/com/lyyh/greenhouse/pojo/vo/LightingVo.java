package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class LightingVo implements Serializable {

	private float lighting;
	
	private Date timeSpan;

	
	public float getLighting() {
		return lighting;
	}

	public void setLighting(float lighting) {
		this.lighting = lighting;
	}

	public Date getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(Date timeSpan) {
		this.timeSpan = timeSpan;
	}
	
	
}
