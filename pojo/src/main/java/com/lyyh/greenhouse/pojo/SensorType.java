package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.Date;

public class SensorType implements Serializable {

	private Integer id;
	//名称
	private String name;
	//数据类型 0-真实值 1-电压值 2-电流值
	private Integer dataType;
	//电信号最小值
	private Integer dataMin;
	//电信号最大值
	private Integer dataMax;
	//最小量程
	private Integer rangeMin;
	//最大量程
	private Integer rangeMax;
	//计量单位
	private String unit;
	
	private String image;
	
	private Date createTime;
	private Date updateTime;
	private Byte isDel;
	
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public Integer getDataMin() {
		return dataMin;
	}
	public void setDataMin(Integer dataMin) {
		this.dataMin = dataMin;
	}
	public Integer getDataMax() {
		return dataMax;
	}
	public void setDataMax(Integer dataMax) {
		this.dataMax = dataMax;
	}
	public Integer getRangeMin() {
		return rangeMin;
	}
	public void setRangeMin(Integer rangeMin) {
		this.rangeMin = rangeMin;
	}
	public Integer getRangeMax() {
		return rangeMax;
	}
	public void setRangeMax(Integer rangeMax) {
		this.rangeMax = rangeMax;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Byte getIsDel() {
		return isDel;
	}
	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
	}
	
}
