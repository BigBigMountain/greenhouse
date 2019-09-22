package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class Equipment implements Serializable {

	//主键id
	private Integer id;
	//设备编号
	private String equipmentCode;
	//设备名称
	private String equipmentName;
	
	//设备状态     0:关闭	1:开/展开	2:合拢	4:停止
	private Integer state;
	//温室编号
	private int houseId;
	//温室所在区域
	private Integer zoneId;
	private String zoneName;
	
	

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
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public int getHouseId() {
		return houseId;
	}
	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	@Override
	public String toString() {
		return "Equipment [id=" + id + ", equipmentCode=" + equipmentCode + ", equipmentName=" + equipmentName
				+ ", state=" + state + ", houseId=" + houseId + ", zoneName=" + zoneName + "]";
	}
	
	
}
