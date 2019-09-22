package com.lyyh.fertilizer.pojo;

import java.io.Serializable;

public class PumpState implements Serializable {

	private int irrigWaterPump; // 灌溉水泵; 0.0
	private int inWaterPump; // 进水水泵; 0.1
	
	private int inWaterValve1; // 进水电磁阀1; 0.2
	private int inWaterValve2; // 进水电磁阀2; 0.3
	
	private int inFertValve1; // 吸肥电磁阀; 0.4
	private int inFertValve2; // 吸肥电磁阀2； 0.5
	private int inFertValve3; // 吸肥电磁阀3； 0.6
	private int inFertValve4; // 吸肥电磁阀4； 0.7
	
	
	
	
	
	public int getIrrigWaterPump() {
		return irrigWaterPump;
	}
	public void setIrrigWaterPump(int irrigWaterPump) {
		this.irrigWaterPump = irrigWaterPump;
	}
	public int getInWaterPump() {
		return inWaterPump;
	}
	public void setInWaterPump(int inWaterPump) {
		this.inWaterPump = inWaterPump;
	}
	public int getInWaterValve1() {
		return inWaterValve1;
	}
	public void setInWaterValve1(int inWaterValve1) {
		this.inWaterValve1 = inWaterValve1;
	}
	public int getInWaterValve2() {
		return inWaterValve2;
	}
	public void setInWaterValve2(int inWaterValve2) {
		this.inWaterValve2 = inWaterValve2;
	}
	public int getInFertValve1() {
		return inFertValve1;
	}
	public void setInFertValve1(int inFertValve1) {
		this.inFertValve1 = inFertValve1;
	}
	public int getInFertValve2() {
		return inFertValve2;
	}
	public void setInFertValve2(int inFertValve2) {
		this.inFertValve2 = inFertValve2;
	}
	public int getInFertValve3() {
		return inFertValve3;
	}
	public void setInFertValve3(int inFertValve3) {
		this.inFertValve3 = inFertValve3;
	}
	public int getInFertValve4() {
		return inFertValve4;
	}
	public void setInFertValve4(int inFertValve4) {
		this.inFertValve4 = inFertValve4;
	}
	
	
	
	
}
