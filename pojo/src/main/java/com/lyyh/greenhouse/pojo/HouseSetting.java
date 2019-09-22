package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class HouseSetting implements Serializable {
	private static final long serialVersionUID = -6045941933580039744L;

	public static final String regexIp ="^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
								+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
								+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
								+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";  
	
	private Integer id;
	private String houseName;
	private Integer houseId;
	private int state;
	private String ip;
	private int port;
	private String node;
	private Integer zoneId;
	private String zoneName;
	
	
	public static String verifyIp(String strIp){
		String result = "";
		if (strIp != null) {
			String[] split = strIp.split("\\.");
			if (split.length != 4) {
				return null;
			}
			for (int i = 0; i < 4; i++) {
				String trim = StringUtils.trim(split[i]);
				if (trim == null || trim == "") {
					return null;
				}
				if (i == 3) {
					result += trim;
				} else {
					result = result + trim + ".";
				}
			}
			return result;
		}
		return null;
	}
	
	
	
	
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	public void setIp(String ip) {
		String verifyIp = verifyIp(ip);
		if(verifyIp != null){

			if (verifyIp.matches(regexIp)) {
				this.ip = verifyIp;
			} 
			else {
				this.ip = null;
			}
		}else{
			this.ip = null;
		}
	}
	public void setPort(int port) {
		if(port==0){
			this.port = 502;
		}else{
			
			this.port = port;
		}
	}

	public String getNode() {
		return node;
	}
	public void setNode(String hs_node) {
		if(StringUtils.isNotBlank(hs_node)){
			this.node = hs_node;
		}else{
			this.node=null;
		}
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
}
