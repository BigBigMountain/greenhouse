package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ValveData implements Serializable {

	private Long id;
	
	private Integer fertilizerId;
	
	private Integer fertilizerDataId;
	
	private Integer number;
	
	private Integer value;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date datetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFertilizerId() {
		return fertilizerId;
	}

	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}

	public Integer getFertilizerDataId() {
		return fertilizerDataId;
	}

	public void setFertilizerDataId(Integer fertilizerDataId) {
		this.fertilizerDataId = fertilizerDataId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	
}
