package com.lyyh.tzgk.pojo;

import java.io.Serializable;
import java.util.Date;

public class TequipmentDataCollector implements Serializable {

	private String gmac;
	private String dictate;
	private String emac;
	private String msg;
	private Date date;
	
	public String getGmac() {
		return gmac;
	}
	public void setGmac(String gmac) {
		this.gmac = gmac;
	}
	public String getDictate() {
		return dictate;
	}
	public void setDictate(String dictate) {
		this.dictate = dictate;
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
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TequipmentData [gmac=" + gmac + ", dictate=" + dictate + ", emac=" + emac + ", msg=" + msg + ", date="
				+ date + "]";
	}
	
}
