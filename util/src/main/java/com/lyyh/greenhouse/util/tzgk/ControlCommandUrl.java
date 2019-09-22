package com.lyyh.greenhouse.util.tzgk;

public class ControlCommandUrl {

	private static String url = "http://" + TzgkConstant.host + "/API/control/offon";
	
	private int uid;
	
	private String tokey;
	
	private String EMac;
	
	private String GMac;
	
	private String Msg;
	
	private int Instructions;


	

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

	public String getEMac() {
		return EMac;
	}

	public void setEMac(String eMac) {
		EMac = eMac;
	}

	public String getGMac() {
		return GMac;
	}

	public void setGMac(String gMac) {
		GMac = gMac;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public int getInstructions() {
		return Instructions;
	}

	public void setInstructions(int instructions) {
		Instructions = instructions;
	}

	@Override
	public String toString() {
		return url +"?"+ "uid=" + uid + "&"+"tokey=" + tokey + "&"+"EMac=" + EMac + "&"+"GMac=" + 
				GMac + "&"+"Msg=" + Msg + "&"+"Instructions=" + Instructions;
	}
	
	
	
}
