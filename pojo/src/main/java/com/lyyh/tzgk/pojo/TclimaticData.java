package com.lyyh.tzgk.pojo;

import java.util.Date;

public class TclimaticData {

	private long id;
	private String gmac;
	private String emac;
	private String msg;
	
	private Integer userId;//外键
	private String username;//用户名
	private Integer fertilizerId;//外键,但不是所有的施肥机都接气象站,所以不应以此键来查找气象信息,建议用userId字段查找
	private String dtuCode;
	private Date time;
	private Date start;
	private Date end;
	private String type;
	
	private String temperature;//温度
	private String humidity; //湿度
	private String lighting;//光照
	private String pressure;//气压
	private String windSpeed;//风速
	private String windDirection;//风向
	private String rainFall;//降雨量
	private String soilTemperature;//土壤温度
	private String soilHumidity;//土壤湿度
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGmac() {
		return gmac;
	}
	public void setGmac(String gmac) {
		this.gmac = gmac;
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
		this.windSpeed = split[0];
//		this.windDirection = split[1];
		double angle = Double.parseDouble(split[1]);
		if(angle<23 || angle>=337){
			this.windDirection = "北";
		}
		if(angle >= 23 && angle < 68){
			this.windDirection = "东北";
		}
		if(angle >= 68 && angle < 112){
			this.windDirection = "东";
		}
		if(angle >= 112 && angle < 157){
			this.windDirection = "东南";
		}
		if(angle >= 157 && angle < 202){
			this.windDirection = "南";
		}
		if(angle >= 202 && angle < 247){
			this.windDirection = "西南";
		}
		if(angle >= 247 && angle < 292){
			this.windDirection = "西";
		}
		if(angle >= 292 && angle < 337){
			this.windDirection = "西北";
		}
		this.temperature = split[2];
		this.humidity = split[3];
		this.pressure = split[4];
		this.rainFall = split[5];
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getLighting() {
		return lighting;
	}
	public void setLighting(String lighting) {
		this.lighting = lighting;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getRainFall() {
		return rainFall;
	}
	public void setRainFall(String rainFall) {
		this.rainFall = rainFall;
	}
	public String getSoilTemperature() {
		return soilTemperature;
	}
	public void setSoilTemperature(String soilTemperature) {
		this.soilTemperature = soilTemperature;
	}
	public String getSoilHumidity() {
		return soilHumidity;
	}
	public void setSoilHumidity(String soilHumidity) {
		this.soilHumidity = soilHumidity;
	}
	
	
}
