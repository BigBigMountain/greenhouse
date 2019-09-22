package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;

public class Climatic implements Serializable {

	private Integer userId;//外键
	private String username;//用户名
	private Integer fertilizerId;//外键,但不是所有的施肥机都接气象站,所以不应以此键来查找气象信息,建议用userId字段查找
	private String dtuCode;
	private Date time;
	private Date start;
	private Date end;
	private String type;
	
	private Double temperature;//温度
	private Double humidity; //湿度
	private Integer lighting;//光照
	private Double pressure;//气压
	private Double windSpeed;//风速
	private String windDirection;//风向
	private Double rainFall;//降雨量
	private Double soilTemperature;//土壤温度
	private Double soilHumidity;//土壤湿度
	
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFertilizerId() {
		return fertilizerId;
	}
	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}
	public String getDtuCode() {
		return dtuCode;
	}
	public void setDtuCode(String dtuCode) {
		this.dtuCode = dtuCode;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
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
	public Integer getLighting() {
		return lighting;
	}
	public void setLighting(Integer lighting) {
		this.lighting = lighting;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
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
	public Double getRainFall() {
		return rainFall;
	}
	public void setRainFall(Double rainFall) {
		this.rainFall = rainFall;
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
	@Override
	public String toString() {
		return "Climatic [userId=" + userId + ", username=" + username + ", fertilizerId=" + fertilizerId + ", dtuCode="
				+ dtuCode + ", time=" + time + ", start=" + start + ", end=" + end + ", temperature=" + temperature
				+ ", humidity=" + humidity + ", lighting=" + lighting + ", pressure=" + pressure + ", windSpeed="
				+ windSpeed + ", windDirection=" + windDirection + ", rainFall=" + rainFall + ", soilTemperature="
				+ soilTemperature + ", soilHumidity=" + soilHumidity + "]";
	}
	
	
}
