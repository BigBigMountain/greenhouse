package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FertilizerDataCharts implements Serializable {

	private List<Float> totalIrrigations;
	private List<Integer> soilHumidity1;
	private List<Integer> soilHumidity2;
	private List<Integer> soilHumidity3;
	private List<Integer> soilHumidity4;
	private List<Float> mainFlow;
	private List<Float> liquidLevel;
	private List<Float> ph;
	private List<Integer> ec;
	private List<Float> rateFlow;
	private List<Date> time;
	
	public FertilizerDataCharts() {
		super();
		this.totalIrrigations = new ArrayList<Float>();
		this.soilHumidity1 = new ArrayList<Integer>();
		this.soilHumidity2 = new ArrayList<Integer>();
		this.soilHumidity3 = new ArrayList<Integer>();
		this.soilHumidity4 = new ArrayList<Integer>();
		this.mainFlow = new ArrayList<Float>();
		this.liquidLevel = new ArrayList<Float>();
		this.ph = new ArrayList<Float>();
		this.ec = new ArrayList<Integer>();
		this.rateFlow = new ArrayList<Float>();
		this.time = new ArrayList<Date>();
	}

	public List<Date> getTime() {
		return time;
	}

	public void setTime(List<Date> time) {
		this.time = time;
	}

	public List<Float> getTotalIrrigations() {
		return totalIrrigations;
	}

	public void setTotalIrrigations(List<Float> totalIrrigations) {
		this.totalIrrigations = totalIrrigations;
	}

	public List<Integer> getSoilHumidity1() {
		return soilHumidity1;
	}

	public void setSoilHumidity1(List<Integer> soilHumidity1) {
		this.soilHumidity1 = soilHumidity1;
	}

	public List<Integer> getSoilHumidity2() {
		return soilHumidity2;
	}

	public void setSoilHumidity2(List<Integer> soilHumidity2) {
		this.soilHumidity2 = soilHumidity2;
	}

	public List<Integer> getSoilHumidity3() {
		return soilHumidity3;
	}

	public void setSoilHumidity3(List<Integer> soilHumidity3) {
		this.soilHumidity3 = soilHumidity3;
	}

	public List<Integer> getSoilHumidity4() {
		return soilHumidity4;
	}

	public void setSoilHumidity4(List<Integer> soilHumidity4) {
		this.soilHumidity4 = soilHumidity4;
	}

	public List<Float> getMainFlow() {
		return mainFlow;
	}

	public void setMainFlow(List<Float> mainFlow) {
		this.mainFlow = mainFlow;
	}

	public List<Float> getLiquidLevel() {
		return liquidLevel;
	}

	public void setLiquidLevel(List<Float> liquidLevel) {
		this.liquidLevel = liquidLevel;
	}

	public List<Float> getPh() {
		return ph;
	}

	public void setPh(List<Float> ph) {
		this.ph = ph;
	}

	public List<Integer> getEc() {
		return ec;
	}

	public void setEc(List<Integer> ec) {
		this.ec = ec;
	}

	public List<Float> getRateFlow() {
		return rateFlow;
	}

	public void setRateFlow(List<Float> rateFlow) {
		this.rateFlow = rateFlow;
	}
	
	
	
}
