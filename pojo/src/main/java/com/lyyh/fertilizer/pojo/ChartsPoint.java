package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;

public class ChartsPoint implements Serializable {

	private Date x;
	private Object y;
	
	
	public Date getX() {
		return x;
	}
	public void setX(Date x) {
		this.x = x;
	}
	public Object getY() {
		return y;
	}
	public void setY(Object y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "ChartsPoint [x=" + x + ", y=" + y + "]";
	}
	
	
}
