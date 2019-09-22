package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class HouseDataVo implements Serializable {
	private static final long serialVersionUID = -6585044659269308891L;
	
	//温室编号
	private Integer houseId;
	//区域名称
	private String zoneName;
	private Integer zoneId;
	//起始时间
	private Date start;
	//截止时间
	private Date end;
	
	public HouseDataVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseDataVo(Integer houseId, String zoneName) {
		super();
		this.houseId = houseId;
		this.zoneName = zoneName;
	}
	
	public HouseDataVo(Integer houseId, String zoneName, Date start, Date end) {
		super();
		this.houseId = houseId;
		this.zoneName = zoneName;
		this.start = start;
		this.end = end;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
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
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	
	
	
	
}
