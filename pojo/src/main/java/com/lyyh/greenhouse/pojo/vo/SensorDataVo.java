package com.lyyh.greenhouse.pojo.vo;

import java.io.Serializable;
import java.util.Date;

public class SensorDataVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer sensorId;
	
	private Integer houseId;
	
	private Date start;
	
	private Date end;
	
	private String order;
	
	private String limit;
	
	

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getSensorId() {
		return sensorId;
	}

	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
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
