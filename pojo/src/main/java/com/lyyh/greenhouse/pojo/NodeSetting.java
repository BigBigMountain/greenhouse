package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class NodeSetting implements Serializable {
	private static final long serialVersionUID = -6332161224945819294L;
	private Integer id;
	//节点地址
	private Integer nodePosition;
	private String channel1;
	private String channel2;
	private String channel3;
	private String channel4;
	private Integer csId;//climaticSettingId

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNodePosition() {
		return nodePosition;
	}
	public void setNodePosition(Integer nodePosition) {
		this.nodePosition = nodePosition;
	}
	public String getChannel1() {
		return channel1;
	}
	public void setChannel1(String channel1) {
		this.channel1 = channel1;
	}
	public String getChannel2() {
		return channel2;
	}
	public void setChannel2(String channel2) {
		this.channel2 = channel2;
	}
	public String getChannel3() {
		return channel3;
	}
	public void setChannel3(String channel3) {
		this.channel3 = channel3;
	}
	public String getChannel4() {
		return channel4;
	}
	public void setChannel4(String channel4) {
		this.channel4 = channel4;
	}
	public Integer getCsId() {
		return csId;
	}
	public void setCsId(Integer csId) {
		this.csId = csId;
	}

}
