package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;

public class HouseVo implements Serializable {

	//温室编号
	private Integer houseId;
	//区域名称
	private String zoneName;
	public HouseVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseVo(Integer houseId, String zoneName) {
		super();
		this.houseId = houseId;
		this.zoneName = zoneName;
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
	
	
	
	
}
