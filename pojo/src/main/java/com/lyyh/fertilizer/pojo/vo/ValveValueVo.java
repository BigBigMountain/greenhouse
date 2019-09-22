package com.lyyh.fertilizer.pojo.vo;

import java.util.List;

import com.lyyh.fertilizer.pojo.ValveValue;

public class ValveValueVo{

	private String username;
	
	private String password;
	
	private Integer fertilizerId;
	
	private List<ValveValue> valves;

	
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getFertilizerId() {
		return fertilizerId;
	}

	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}

	public List<ValveValue> getValves() {
		return valves;
	}

	public void setValves(List<ValveValue> valves) {
		this.valves = valves;
	}
	
	
	
}
