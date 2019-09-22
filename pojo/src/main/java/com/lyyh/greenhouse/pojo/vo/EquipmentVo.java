package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;

public class EquipmentVo implements Serializable{

	//设备id
	private Integer id;
	//设备状态
	private Integer state;
	//温室编号
	private Integer houseId;
	//温室所在区域
	private String zoneName;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "EquipmentVo [id=" + id + ", state=" + state + ", houseId=" + houseId + ", zoneName=" + zoneName + "]";
	}
	
	
}
