package com.lyyh.greenhouse.util;

public class KLModbusData {
	//字节数组长度,解析数据时,当前数据所占字节长度
	private Integer length;//字节数组长度
	//指针
	private int i;
	//当前数据是第几通道
	private Integer channel;
	//数据类型代码,详情请参考 《KL-H1100 物联网网关通讯协议》 表一
	private String type;
	//小数 类型数据
	private Double doubleVal;
	//整数类型数据
	private Integer intVal;
	//比尔类型数据
	private Boolean boolVal;
	
	
	
	
	
	
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Double getDoubleVal() {
		return doubleVal;
	}

	public void setDoubleVal(Double doubleVal) {
		this.doubleVal = doubleVal;
	}

	public Integer getIntVal() {
		return intVal;
	}

	public void setIntVal(Integer intVal) {
		this.intVal = intVal;
	}

	public Boolean getBoolVal() {
		return boolVal;
	}


	public void setBoolVal(Boolean boolVal) {
		this.boolVal = boolVal;
	}
	
	
}
