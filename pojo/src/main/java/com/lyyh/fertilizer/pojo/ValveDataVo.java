package com.lyyh.fertilizer.pojo;

import java.io.Serializable;

public class ValveDataVo extends ValveData implements Serializable{

	private static final long serialVersionUID = 1L;
	//记录同一台施肥机,同一时刻,同一个状态的阀的数量,用于统计灌溉量
	private int count;
	
	private  FertilizerData fertilizerData;

	public FertilizerData getFertilizerData() {
		return fertilizerData;
	}

	public void setFertilizerData(FertilizerData fertilizerData) {
		this.fertilizerData = fertilizerData;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
}
