package com.lyyh.greenhouse.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class HouseData_highcharts implements Serializable{
	
	private ArrayList<Object> temperatures=new ArrayList<Object>();
	private ArrayList<Object> temperatures2=new ArrayList<Object>();
	private ArrayList<Object> humiditys=new ArrayList<Object>();
	private ArrayList<Object> humiditys2=new ArrayList<Object>();
	private ArrayList<Object> lightings=new ArrayList<Object>();
	private ArrayList<Object> co2s=new ArrayList<Object>();
	private ArrayList<Object> soilTemperatures=new ArrayList<Object>();
	private ArrayList<Object> soilHumiditys=new ArrayList<Object>();
	private ArrayList<Object> timeSpan=new ArrayList<>();
	public ArrayList<Object> getTimeSpan() {
		return timeSpan;
	}
	public void setTimeSpan(ArrayList<Object> timeSpan) {
		this.timeSpan = timeSpan;
	}
	public ArrayList<Object> getTemperatures() {
		return temperatures;
	}
	public void setTemperatures(ArrayList<Object> temperatures) {
		this.temperatures = temperatures;
	}
	public ArrayList<Object> getTemperatures2() {
		return temperatures2;
	}
	public void setTemperatures2(ArrayList<Object> temperatures2) {
		this.temperatures2 = temperatures2;
	}
	public ArrayList<Object> getHumiditys() {
		return humiditys;
	}
	public void setHumiditys(ArrayList<Object> humiditys) {
		this.humiditys = humiditys;
	}
	public ArrayList<Object> getHumiditys2() {
		return humiditys2;
	}
	public void setHumiditys2(ArrayList<Object> humiditys2) {
		this.humiditys2 = humiditys2;
	}
	public ArrayList<Object> getLightings() {
		return lightings;
	}
	public void setLightings(ArrayList<Object> lightings) {
		this.lightings = lightings;
	}
	public ArrayList<Object> getCo2s() {
		return co2s;
	}
	public void setCo2s(ArrayList<Object> co2s) {
		this.co2s = co2s;
	}
	public ArrayList<Object> getSoilTemperatures() {
		return soilTemperatures;
	}
	public void setSoilTemperatures(ArrayList<Object> soilTemperatures) {
		this.soilTemperatures = soilTemperatures;
	}
	public ArrayList<Object> getSoilHumiditys() {
		return soilHumiditys;
	}
	public void setSoilHumiditys(ArrayList<Object> soilHumiditys) {
		this.soilHumiditys = soilHumiditys;
	}
	

}
