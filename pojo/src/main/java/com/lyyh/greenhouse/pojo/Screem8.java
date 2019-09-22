package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.List;

public class Screem8 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5371822467121308524L;

	private Integer id;
	
	private String currentPortName;
	
	//波特率
	private Integer  baudRate;
	
	private List<String> portNames;
	
	//间隔时间
	private Integer intervalTime;
	
	private String zoneName;
	private Integer zoneId;
	
	private Integer status;

	
	
	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrentPortName() {
		return currentPortName;
	}

	public void setCurrentPortName(String currentPortName) {
		this.currentPortName = currentPortName;
	}

	public Integer getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(Integer baudRate) {
		this.baudRate = baudRate;
	}

	public List<String> getPortNames() {
		return portNames;
	}

	public void setPortNames(List<String> portNames) {
		this.portNames = portNames;
	}

	public Integer getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baudRate == null) ? 0 : baudRate.hashCode());
		result = prime * result + ((currentPortName == null) ? 0 : currentPortName.hashCode());
		result = prime * result + ((intervalTime == null) ? 0 : intervalTime.hashCode());
		result = prime * result + ((portNames == null) ? 0 : portNames.hashCode());
		result = prime * result + ((zoneName == null) ? 0 : zoneName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screem8 other = (Screem8) obj;
		if (baudRate == null) {
			if (other.baudRate != null)
				return false;
		} else if (!baudRate.equals(other.baudRate))
			return false;
		if (currentPortName == null) {
			if (other.currentPortName != null)
				return false;
		} else if (!currentPortName.equals(other.currentPortName))
			return false;
		if (intervalTime == null) {
			if (other.intervalTime != null)
				return false;
		} else if (!intervalTime.equals(other.intervalTime))
			return false;
		if (portNames == null) {
			if (other.portNames != null)
				return false;
		} else if (!portNames.equals(other.portNames))
			return false;
		if (zoneName == null) {
			if (other.zoneName != null)
				return false;
		} else if (!zoneName.equals(other.zoneName))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
