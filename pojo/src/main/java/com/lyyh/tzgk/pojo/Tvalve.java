package com.lyyh.tzgk.pojo;

public class Tvalve {

	private Integer id;
	private String emac;//设备mac
	private String gmac;//网关mac
	private Integer eid;//设备类别id 9:双控电磁阀  6:单控电磁阀 2:气象站
	private Integer position;//在阀控器的第几个位置;
	private Integer number;//第几个阀
	private String ename;//--设备名称
	private String eqmsg;//--设备的状态或值 
	private Long updatetime;//--更新时间,时间戳
	private Integer collection;//--采集间隔
	private String eqtypename;//--设备类别名
	private Integer rid;//--区域id
	private String rname;//--区域名称
	private Integer fertilizerId;
	private String dtuCode;
	private Integer state;
	
	
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getFertilizerId() {
		return fertilizerId;
	}
	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}
	public String getDtuCode() {
		return dtuCode;
	}
	public void setDtuCode(String dtuCode) {
		this.dtuCode = dtuCode;
	}
	
	
	public Integer getTvalveId() {
		return id;
	}
	public void setTvalveId(Integer tvalveId) {
		this.id = tvalveId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmac() {
		return emac;
	}
	public void setEmac(String emac) {
		this.emac = emac;
	}
	public String getGmac() {
		return gmac;
	}
	public void setGmac(String gmac) {
		this.gmac = gmac;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEqmsg() {
		return eqmsg;
	}
	public void setEqmsg(String eqmsg) {
		this.eqmsg = eqmsg;
	}
	public Long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getCollection() {
		return collection;
	}
	public void setCollection(Integer collection) {
		this.collection = collection;
	}
	public String getEqtypename() {
		return eqtypename;
	}
	public void setEqtypename(String eqtypename) {
		this.eqtypename = eqtypename;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "Tvalve [id=" + id + ", emac=" + emac + ", gmac=" + gmac + ", eid=" + eid + ", position=" + position
				+ ", number=" + number + ", ename=" + ename + ", eqmsg=" + eqmsg + ", updatetime=" + updatetime
				+ ", collection=" + collection + ", eqtypename=" + eqtypename + ", rid=" + rid + ", rname=" + rname
				+ ", fertilizerId=" + fertilizerId + ", dtuCode=" + dtuCode + ", state=" + state + "]";
	}
	
	
	
	
}
