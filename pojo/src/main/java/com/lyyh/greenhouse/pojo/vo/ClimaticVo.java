package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class ClimaticVo implements Serializable {

	//区域名称
	private String zoneName;
	
	private Integer zoneId;
	//起始时间
	private Date start;
	//截止时间
	private Date end;
	
	public ClimaticVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClimaticVo(String zoneName) {
		super();
		this.zoneName = zoneName;
	}

	public ClimaticVo(String zoneName, Date start, Date end) {
		super();
		this.zoneName = zoneName;
		this.start = start;
		this.end = end;
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
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	
	
}
