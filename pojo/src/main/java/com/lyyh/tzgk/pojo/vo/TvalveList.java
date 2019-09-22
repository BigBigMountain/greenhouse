package com.lyyh.tzgk.pojo.vo;

import java.io.Serializable;
import java.util.List;

import com.lyyh.tzgk.pojo.Tvalve;

public class TvalveList implements Serializable {

	private static final long serialVersionUID = -2934733296593532390L;
	
	private List<Tvalve> tvalveList;

	public List<Tvalve> getTvalveList() {
		return tvalveList;
	}

	public void setTvalveList(List<Tvalve> tvalveList) {
		this.tvalveList = tvalveList;
	}
	
}
