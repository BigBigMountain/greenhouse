package com.lyyh.tzgk.pojo;

import java.io.Serializable;
import java.util.List;

public class Tuser implements Serializable {

	private int statu;
	private String id;
	private String msg;
	
	
	
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Tuser [statu=" + statu + ", id=" + id + ", msg=" + msg + "]";
	}
	
}
