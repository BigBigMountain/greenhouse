package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class FertilizerDataCollector implements Serializable {

	private Integer fertilizerDataId;

	private Integer programNo;// 运行程序号
	private Integer valve1;// 灌溉阀1
	private Integer valve2;// 灌溉阀2
	private Integer valve3;// 灌溉阀3
	private Float totalIrrigation;// 累计灌溉量

	private Integer soilHumidity1;// 土壤湿度1
	private Integer soilHumidity2;// 土壤湿度2
	private Integer soilHumidity3;// 土壤湿度3
	private Integer soilHumidity4;// 土壤湿度4
	private Float mainFlow;// 主管路流量
	private Float liquidLevel;// 液位
	private Float ph;
	private Integer ec;
	private Float rateFlow;// 实时流量
	private Date time;

	private Integer fertilizerId;// 外键,

	private String dtuCode;
	// public Integer getFertilizerDataId() {
	// return fertilizerDataId;
	// }
	//
	// public void setFertilizerDataId(Integer fertilizerDataId) {
	// this.fertilizerDataId = fertilizerDataId;
	// }


	
	public Integer getFertilizerDataId() {
		return fertilizerDataId;
	}

	public void setFertilizerDataId(Integer fertilizerDataId) {
		this.fertilizerDataId = fertilizerDataId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public Integer getFertilizerId() {
		return fertilizerId;
	}

	public void setFertilizerId(Integer fertilizerId) {
		this.fertilizerId = fertilizerId;
	}


	public String getDtuCode() {
		return dtuCode;
	}

	public void setDtuCode(String dtuCode) {
		this.dtuCode = dtuCode;
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

	public void setTotalIrrigation(Integer value) {
		Float temp = Float.intBitsToFloat(value);
		this.totalIrrigation = new BigDecimal(temp.toString()).setScale(2, RoundingMode.UP).floatValue();
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

	public void setMainFlow(Integer value) {
		Float temp = Float.intBitsToFloat(value);
		this.mainFlow = new BigDecimal(temp.toString()).setScale(2, RoundingMode.UP).floatValue();
	}

	public Float getLiquidLevel() {
		return liquidLevel;
	}

	public void setLiquidLevel(Integer value) {
		Float temp = Float.intBitsToFloat(value);
		this.liquidLevel = new BigDecimal(temp.toString()).setScale(2, RoundingMode.UP).floatValue();
	}

	public Float getPh() {
		return ph;
	}

	public void setPh(Integer value) {
		Float temp = Float.intBitsToFloat(value);
		this.ph = new BigDecimal(temp.toString()).setScale(2, RoundingMode.UP).floatValue();
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

	public void setRateFlow(Integer value) {
		Float temp = Float.intBitsToFloat(value);
		this.rateFlow = new BigDecimal(temp.toString()).setScale(2, RoundingMode.UP).floatValue();
	}

	@Override
	public String toString() {
		return "FertilizerDataCollector [programNo=" + programNo + ", valve1=" + valve1 + ", valve2=" + valve2
				+ ", valve3=" + valve3 + ", totalIrrigation=" + totalIrrigation + ", soilHumidity1=" + soilHumidity1
				+ ", soilHumidity2=" + soilHumidity2 + ", soilHumidity3=" + soilHumidity3 + ", soilHumidity4="
				+ soilHumidity4 + ", mainFlow=" + mainFlow + ", liquidLevel=" + liquidLevel + ", ph=" + ph + ", ec="
				+ ec + ", rateFlow=" + rateFlow + ", dtuCode=" + dtuCode + "]";
	}

}
