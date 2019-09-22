package com.lyyh.tzgk.pojo;

import java.io.Serializable;

public class Tequipment implements Serializable{
	
	private String emac;//设备mac
	private String gmac;//网关mac
	private String ename;//设备名称
	private String eqmsg;//设备的状态或值
	private Long updatetime;//更新时间,时间戳
	private Integer collection;//采集间隔
	private Integer eid;//设备类别id 9:双控电磁阀  6:单控电磁阀 2:气象站
	private String eqtypename;//设备类别名
	private Integer rid;//区域id
	private String rname;//区域名称
	
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
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
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
		return "Equipment [emac=" + emac + ", gmac=" + gmac + ", ename=" + ename + ", eid=" + eid + ", eqmsg=" + eqmsg
				+ ", updatetime=" + updatetime + ", collection=" + collection + ", eqtypename=" + eqtypename + ", rid="
				+ rid + ", rname=" + rname + "]";
	}
	
	
}
