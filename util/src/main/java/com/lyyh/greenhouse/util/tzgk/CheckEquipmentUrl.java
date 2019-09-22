package com.lyyh.greenhouse.util.tzgk;

public class CheckEquipmentUrl {

	private static String url = "http://" + TzgkConstant.host + "/API/App/checkEquipment";
	
	private int uid;
	private String tokey;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTokey() {
		return tokey;
	}
	public void setTokey(String tokey) {
		this.tokey = tokey;
	}
	@Override
	public String toString() {
		return url+"?"+"uid=" + uid + "&"+"tokey=" + tokey;
	}
	
	
}
