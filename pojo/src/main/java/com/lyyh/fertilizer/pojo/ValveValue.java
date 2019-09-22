package com.lyyh.fertilizer.pojo;

import java.io.Serializable;

public class ValveValue implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer valveNum;
	
	private Integer value;

	public Integer getValveNum() {
		return valveNum;
	}

	public void setValveNum(Integer valveNum) {
		this.valveNum = valveNum;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	
}
