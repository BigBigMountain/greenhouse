package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class AboutUs implements Serializable{
	
	private String zoneCode;
	private String aboutUsHtml;
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getAboutUsHtml() {
		return aboutUsHtml;
	}
	public void setAboutUsHtml(String aboutUsHtml) {
		this.aboutUsHtml = aboutUsHtml;
	}
	

}
