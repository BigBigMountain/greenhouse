package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ClimaticSetting implements Serializable {
	private static final long serialVersionUID = 1718528069250170532L;

	public static final String regexIp ="^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
			+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";  
	
	private Integer id;
	private String zoneName;
	private Integer zoneId;
	private String ip;
	private Integer port;
	List<NodeSetting> nodes;
	
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

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getIp() {
		return ip;
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

	public Integer getPort() {
		return port;
	}

	public void setCs_port(Integer port) {
		if(port == null || port==0){
			this.port = 502;
		}else{
			this.port = port;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public List<NodeSetting> getNodes() {
		return nodes;
	}

	public void setNodes(List<NodeSetting> nodes) {
		this.nodes = nodes;
	}

}
