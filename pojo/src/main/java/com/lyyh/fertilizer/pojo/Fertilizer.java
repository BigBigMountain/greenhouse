package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.datetime.DateFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Fertilizer implements Serializable {

	private Integer fertilizerId;
	private String  fertilizerName;
	private String  addr;			//地址
	private String coordinate;		//坐标   x,y   或  经,纬
	private Integer state;			//状态  1:启动  2:未启动
	private Integer isOnline;		//是否在线,1:在线,    0或其他,离线
	private String  desc;	//简单描述
	
	@DateTimeFormat(pattern="yyyy-MM-dd",iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date prodDate;			//生产日期
	private String dtuCode;		//dtu编号
	private Integer zoneId;			//区域编号,可有可无
	private Integer userId;			//外键,用户id
	private Integer valveType;			//施肥机接的阀的类型,0-一般有线连接  1-天正阀
	
	
	
	public Integer getValveType() {
		return valveType;
	}
	public void setValveType(Integer valveType) {
		this.valveType = valveType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getProdDate() {
		if(prodDate != null){
			return new SimpleDateFormat("yyyy-MM-dd").format(prodDate);
		}
		return "";
	}
	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}
	public String getDtuCode() {
		return dtuCode;
	}
	public void setDtuCode(String dtuCode) {
		this.dtuCode = dtuCode;
	}
	public Integer getFertilizerId() {
		return fertilizerId;
	}
	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}
	public String getFertilizerName() {
		return fertilizerName;
	}
	public void setFertilizerName(String fertilizerName) {
		this.fertilizerName = fertilizerName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	@Override
	public String toString() {
		return "Fertilizer [fertilizerId=" + fertilizerId + ", fertilizerName=" + fertilizerName + ", addr=" + addr
				+ ", coordinate=" + coordinate + ", state=" + state + ", isOnline=" + isOnline + ", desc=" + desc
				+ ", prodDate=" + prodDate + ", dtuCode=" + dtuCode + ", zoneId=" + zoneId + ", userId=" + userId + "]";
	}
	
	
	
}
