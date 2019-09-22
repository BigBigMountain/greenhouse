package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;

public class FertilizerData implements Serializable {

	private Integer fertilizerDataId;
	
	private Integer programNo;//运行程序号
	private Integer valve1;//灌溉阀1
	private Integer valve2;//灌溉阀2
	private Integer valve3;//灌溉阀3
	private Float totalIrrigation;//累计灌溉量  √
	
	private Integer soilHumidity1;//土壤湿度1
	private Integer soilHumidity2;//土壤湿度2
	private Integer soilHumidity3;//土壤湿度3
	private Integer soilHumidity4;//土壤湿度4
	private Float mainFlow;//主管路流量  √
	private Float liquidLevel;//液位  √
	private Float ph; // √
	private Integer ec;// √
	private Float rateFlow;//实时流量  √
	private Float pipePressure;//管道压力  √
	private Date time;//时间;
	
	private Date start;//查询时使用的开始时间
	private Date end;//查询时使用的结束时间
	
	private Integer fertilizerId;//外键,
	private String dtuCode;//dtu编号
	private Integer userId;

	
	public Float getPipePressure() {
		return pipePressure;
	}

	public void setPipePressure(Float pipePressure) {
		this.pipePressure = pipePressure;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDtuCode() {
		return dtuCode;
	}

	public void setDtuCode(String dtuCode) {
		this.dtuCode = dtuCode;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public Integer getFertilizerDataId() {
		return fertilizerDataId;
	}

	public void setFertilizerDataId(Integer fertilizerDataId) {
		this.fertilizerDataId = fertilizerDataId;
	}

	public Integer getProgramNo() {
		return programNo;
	}

	public void setProgramNo(Integer programNo) {
		this.programNo = programNo;
	}

	public Integer getValve1() {
		return valve1;
	}

	public void setValve1(Integer valve1) {
		this.valve1 = valve1;
	}

	public Integer getValve2() {
		return valve2;
	}

	public void setValve2(Integer valve2) {
		this.valve2 = valve2;
	}

	public Integer getValve3() {
		return valve3;
	}

	public void setValve3(Integer valve3) {
		this.valve3 = valve3;
	}

	public Float getTotalIrrigation() {
		return totalIrrigation;
	}

	public void setTotalIrrigation(Float totalIrrigation) {
		this.totalIrrigation = totalIrrigation;
	}

	public Integer getSoilHumidity1() {
		return soilHumidity1;
	}

	public void setSoilHumidity1(Integer soilHumidity1) {
		this.soilHumidity1 = soilHumidity1;
	}

	public Integer getSoilHumidity2() {
		return soilHumidity2;
	}

	public void setSoilHumidity2(Integer soilHumidity2) {
		this.soilHumidity2 = soilHumidity2;
	}

	public Integer getSoilHumidity3() {
		return soilHumidity3;
	}

	public void setSoilHumidity3(Integer soilHumidity3) {
		this.soilHumidity3 = soilHumidity3;
	}

	public Integer getSoilHumidity4() {
		return soilHumidity4;
	}

	public void setSoilHumidity4(Integer soilHumidity4) {
		this.soilHumidity4 = soilHumidity4;
	}

	public Float getMainFlow() {
		return mainFlow;
	}

	public void setMainFlow(Float mainFlow) {
		this.mainFlow = mainFlow;
	}

	public Float getLiquidLevel() {
		return liquidLevel;
	}

	public void setLiquidLevel(Float liquidLevel) {
		this.liquidLevel = liquidLevel;
	}

	public Float getPh() {
		return ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	public Integer getEc() {
		return ec;
	}

	public void setEc(Integer ec) {
		this.ec = ec;
	}

	public Float getRateFlow() {
		return rateFlow;
	}

	public void setRateFlow(Float rateFlow) {
		this.rateFlow = rateFlow;
	}

	public Integer getFertilizerId() {
		return fertilizerId;
	}

	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}

	@Override
	public String toString() {
		return "FertilizerData [fertilizerDataId=" + fertilizerDataId + ", programNo=" + programNo + ", valve1="
				+ valve1 + ", valve2=" + valve2 + ", valve3=" + valve3 + ", totalIrrigation=" + totalIrrigation
				+ ", soilHumidity1=" + soilHumidity1 + ", soilHumidity2=" + soilHumidity2 + ", soilHumidity3="
				+ soilHumidity3 + ", soilHumidity4=" + soilHumidity4 + ", mainFlow=" + mainFlow + ", liquidLevel="
				+ liquidLevel + ", ph=" + ph + ", ec=" + ec + ", rateFlow=" + rateFlow + ", pipePressure="
				+ pipePressure + ", time=" + time + ", start=" + start + ", end=" + end + ", fertilizerId="
				+ fertilizerId + ", dtuCode=" + dtuCode + ", userId=" + userId + "]";
	}

	

	

	
	
	
	
}
