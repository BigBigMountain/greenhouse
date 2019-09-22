package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProgramControlPojo implements Serializable {

	private Integer userId;
	private Integer fertilizerId;//外键,
	private String dtuCode;
	private Date time;
	private Date start;
	private Date end;
	
	private int manualOrProgram;//0为程序启动,1为手动关联启动
	private int program1;
	private int program2;
	private int program3;
	private int program4;
	private int program5;
	private int program6;
	private int program7;
	private int program8;
	private int program9;
	private int program10;
	private int program11;
	private int program12;
	private int program13;
	private int program14;
	private int program15;
	private int program16;
	
	
	

	public int getManualOrProgram() {
		return manualOrProgram;
	}
	public void setManualOrProgram(int manualOrProgram) {
		this.manualOrProgram = manualOrProgram;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public int getProgram1() {
		return program1;
	}
	public void setProgram1(int program1) {
		this.program1 = program1;
	}
	public int getProgram2() {
		return program2;
	}
	public void setProgram2(int program2) {
		this.program2 = program2;
	}
	public int getProgram3() {
		return program3;
	}
	public void setProgram3(int program3) {
		this.program3 = program3;
	}
	public int getProgram4() {
		return program4;
	}
	public void setProgram4(int program4) {
		this.program4 = program4;
	}
	public int getProgram5() {
		return program5;
	}
	public void setProgram5(int program5) {
		this.program5 = program5;
	}
	public int getProgram6() {
		return program6;
	}
	public void setProgram6(int program6) {
		this.program6 = program6;
	}
	public int getProgram7() {
		return program7;
	}
	public void setProgram7(int program7) {
		this.program7 = program7;
	}
	public int getProgram8() {
		return program8;
	}
	public void setProgram8(int program8) {
		this.program8 = program8;
	}
	public int getProgram9() {
		return program9;
	}
	public void setProgram9(int program9) {
		this.program9 = program9;
	}
	public int getProgram10() {
		return program10;
	}
	public void setProgram10(int program10) {
		this.program10 = program10;
	}
	public int getProgram11() {
		return program11;
	}
	public void setProgram11(int program11) {
		this.program11 = program11;
	}
	public int getProgram12() {
		return program12;
	}
	public void setProgram12(int program12) {
		this.program12 = program12;
	}
	public int getProgram13() {
		return program13;
	}
	public void setProgram13(int program13) {
		this.program13 = program13;
	}
	public int getProgram14() {
		return program14;
	}
	public void setProgram14(int program14) {
		this.program14 = program14;
	}
	public int getProgram15() {
		return program15;
	}
	public void setProgram15(int program15) {
		this.program15 = program15;
	}
	public int getProgram16() {
		return program16;
	}
	public void setProgram16(int program16) {
		this.program16 = program16;
	}
	@Override
	public String toString() {
		return "ProgramControlPojo [userId=" + userId + ", fertilizerId=" + fertilizerId + ", dtuCode=" + dtuCode
				+ ", time=" + time + ", start=" + start + ", end=" + end + ", manualOrProgram=" + manualOrProgram
				+ ", program1=" + program1 + ", program2=" + program2 + ", program3=" + program3 + ", program4="
				+ program4 + ", program5=" + program5 + ", program6=" + program6 + ", program7=" + program7
				+ ", program8=" + program8 + ", program9=" + program9 + ", program10=" + program10 + ", program11="
				+ program11 + ", program12=" + program12 + ", program13=" + program13 + ", program14=" + program14
				+ ", program15=" + program15 + ", program16=" + program16 + "]";
	}
	
	
	
	
}
