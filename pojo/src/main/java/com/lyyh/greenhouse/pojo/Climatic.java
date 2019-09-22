package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.Date;

public class Climatic implements Serializable {

	//主键
	private Integer id;
	//风速
	private Double windSpeed;
	//风向
	private String windDirection;
	//雨雪
	private String rainAndSnow;
	//降水量
	private Double rainFall;
	//气压
	private Double pressure;
	//PM2.5
	private Double pm25;
	//PH
	private Double ph;
	//温度
	private Double temperature;
	//湿度
	private Double humidity;
	//光照
	private Double lighting;
	//所属区域
	private String zoneName;
	private Integer zoneId;
	//时间
	private Date datetime;
	
	
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	
	public String getRainAndSnow() {
		return rainAndSnow;
	}
	public void setRainAndSnow(String rainAndSnow) {
		this.rainAndSnow = rainAndSnow;
	}
	public Double getRainFall() {
		return rainFall;
	}
	public void setRainFall(Double rainFall) {
		this.rainFall = rainFall;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Double getPm25() {
		return pm25;
	}
	public void setPm25(Double pm25) {
		this.pm25 = pm25;
	}
	public Double getPh() {
		return ph;
	}
	public void setPh(Double ph) {
		this.ph = ph;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getLighting() {
		return lighting;
	}
	public void setLighting(Double lighting) {
		this.lighting = lighting;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	
	
	
}
