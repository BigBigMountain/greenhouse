package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;

public class ManualControlPojo implements Serializable {

	private Integer userId;
	private Integer fertilizerId;//外键,
	private String dtuCode;
	private Date time;
	private Date start;
	private Date end;
	/*
	 * 输出线圈Q0的状态
	 */
//	private int irrigWaterPump; // 灌溉水泵; 0.0
//	private int inWaterPump; // 进水水泵; 0.1
//	private int inWaterValve1; // 进水电磁阀1; 0.2
//	private int inWaterValve2; // 进水电磁阀2; 0.3
//	private int inFertValve1; // 吸肥电磁阀; 0.4
//	private int inFertValve2; // 吸肥电磁阀2； 0.5
//	private int inFertValve3; // 吸肥电磁阀3； 0.6
//	private int inFertValve4; // 吸肥电磁阀4； 0.7

	/*
	 * 输出线圈Q1的状态
	 */
	private int irrigValve1; // 灌溉阀1； 1.0
	private int irrigValve2; // 灌溉阀2； 1.1
	private int irrigValve3; // 灌溉阀3； 1.2
	private int irrigValve4; // 灌溉阀4； 1.3
	private int irrigValve5; // 灌溉阀5； 1.4
	private int irrigValve6; // 灌溉阀6； 1.5
	private int irrigValve7; // 灌溉阀7； 1.6
	private int irrigValve8; // 灌溉阀8； 1.7

	/*
	 * 62—01100010(二进制)，输出线圈Q2的状态
	 */
	private int irrigValve9; // 灌溉阀9； 2.0
	private int irrigValve10; // 灌溉阀10； 2.1
	private int irrigValve11; // 灌溉阀11； 2.2
	private int irrigValve12; // 灌溉阀12； 2.3
	private int irrigValve13; // 灌溉阀13； 2.4
	private int irrigValve14; // 灌溉阀14； 2.5
	private int irrigValve15; // 灌溉阀15； 2.6
	private int irrigValve16; // 灌溉阀16； 2.7
	
	/*
	 * 输出线圈Q3的状态
	 */
	private int irrigValve17; //灌溉阀17； 3.0
	private int irrigValve18; //灌溉阀18； 3.1
	private int irrigValve19; //灌溉阀19； 3.2
	private int irrigValve20; //灌溉阀20； 3.3
	private int irrigValve21; //灌溉阀21； 3.4
	private int irrigValve22; //灌溉阀22； 3.5
	private int irrigValve23; //灌溉阀23； 3.6
	private int irrigValve24; //灌溉阀24； 3.7
	
	//输出线圈Q4的状态
	private int irrigValve25; //灌溉阀25； 4.0
	private int irrigValve26; //灌溉阀26； 4.1
	private int irrigValve27; //灌溉阀27； 4.2
	private int irrigValve28; //灌溉阀28； 4.3
	private int irrigValve29; //灌溉阀29； 4.4
	private int irrigValve30; //灌溉阀30； 4.5
	private int irrigValve31; //灌溉阀31； 4.6
	private int irrigValve32; //灌溉阀32； 4.7

	//输出线圈Q5的状态
	private int irrigValve33; //灌溉阀33； 5.0
	private int irrigValve34; //灌溉阀34； 5.1
	private int irrigValve35; //灌溉阀35； 5.2
	private int irrigValve36; //灌溉阀36； 5.3
	private int irrigValve37; //灌溉阀37； 5.4
	private int irrigValve38; //灌溉阀38； 5.5
	private int irrigValve39; //灌溉阀39； 5.6
	private int irrigValve40; //灌溉阀40； 5.7
	
	//输出线圈Q6的状态
	private int irrigValve41; //灌溉阀41； 6.0
	private int irrigValve42; //灌溉阀42； 6.1
	private int irrigValve43; //灌溉阀43； 6.2
	private int irrigValve44; //灌溉阀44； 6.3
	private int irrigValve45; //灌溉阀45； 6.4
	private int irrigValve46; //灌溉阀46； 6.5
	private int irrigValve47; //灌溉阀47； 6.6
	private int irrigValve48; //灌溉阀48； 6.7
	
	//输出线圈Q7的状态
	private int irrigValve49; //灌溉阀49； 7.0
	private int irrigValve50; //灌溉阀50； 7.1
	private int irrigValve51; //灌溉阀51； 7.2
	private int irrigValve52; //灌溉阀52； 7.3
	private int irrigValve53; //灌溉阀53； 7.4
	private int irrigValve54; //灌溉阀54； 7.5
	private int irrigValve55; //灌溉阀55； 7.6
	private int irrigValve56; //灌溉阀56； 7.7
	
	//输出线圈Q8的状态
	private int irrigValve57; //灌溉阀57； 8.0
	private int irrigValve58; //灌溉阀58； 8.1
	private int irrigValve59; //灌溉阀59； 8.2
	private int irrigValve60; //灌溉阀60； 8.3
	private int irrigValve61; //灌溉阀61； 8.4
	private int irrigValve62; //灌溉阀62； 8.5
	private int irrigValve63; //灌溉阀63； 8.6
	private int irrigValve64; //灌溉阀64； 8.7
	
	
	
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
	public int getIrrigValve1() {
		return irrigValve1;
	}
	public void setIrrigValve1(int irrigValve1) {
		this.irrigValve1 = irrigValve1;
	}
	public int getIrrigValve2() {
		return irrigValve2;
	}
	public void setIrrigValve2(int irrigValve2) {
		this.irrigValve2 = irrigValve2;
	}
	public int getIrrigValve3() {
		return irrigValve3;
	}
	public void setIrrigValve3(int irrigValve3) {
		this.irrigValve3 = irrigValve3;
	}
	public int getIrrigValve4() {
		return irrigValve4;
	}
	public void setIrrigValve4(int irrigValve4) {
		this.irrigValve4 = irrigValve4;
	}
	public int getIrrigValve5() {
		return irrigValve5;
	}
	public void setIrrigValve5(int irrigValve5) {
		this.irrigValve5 = irrigValve5;
	}
	public int getIrrigValve6() {
		return irrigValve6;
	}
	public void setIrrigValve6(int irrigValve6) {
		this.irrigValve6 = irrigValve6;
	}
	public int getIrrigValve7() {
		return irrigValve7;
	}
	public void setIrrigValve7(int irrigValve7) {
		this.irrigValve7 = irrigValve7;
	}
	public int getIrrigValve8() {
		return irrigValve8;
	}
	public void setIrrigValve8(int irrigValve8) {
		this.irrigValve8 = irrigValve8;
	}
	public int getIrrigValve9() {
		return irrigValve9;
	}
	public void setIrrigValve9(int irrigValve9) {
		this.irrigValve9 = irrigValve9;
	}
	public int getIrrigValve10() {
		return irrigValve10;
	}
	public void setIrrigValve10(int irrigValve10) {
		this.irrigValve10 = irrigValve10;
	}
	public int getIrrigValve11() {
		return irrigValve11;
	}
	public void setIrrigValve11(int irrigValve11) {
		this.irrigValve11 = irrigValve11;
	}
	public int getIrrigValve12() {
		return irrigValve12;
	}
	public void setIrrigValve12(int irrigValve12) {
		this.irrigValve12 = irrigValve12;
	}
	public int getIrrigValve13() {
		return irrigValve13;
	}
	public void setIrrigValve13(int irrigValve13) {
		this.irrigValve13 = irrigValve13;
	}
	public int getIrrigValve14() {
		return irrigValve14;
	}
	public void setIrrigValve14(int irrigValve14) {
		this.irrigValve14 = irrigValve14;
	}
	public int getIrrigValve15() {
		return irrigValve15;
	}
	public void setIrrigValve15(int irrigValve15) {
		this.irrigValve15 = irrigValve15;
	}
	public int getIrrigValve16() {
		return irrigValve16;
	}
	public void setIrrigValve16(int irrigValve16) {
		this.irrigValve16 = irrigValve16;
	}
	public int getIrrigValve17() {
		return irrigValve17;
	}
	public void setIrrigValve17(int irrigValve17) {
		this.irrigValve17 = irrigValve17;
	}
	public int getIrrigValve18() {
		return irrigValve18;
	}
	public void setIrrigValve18(int irrigValve18) {
		this.irrigValve18 = irrigValve18;
	}
	public int getIrrigValve19() {
		return irrigValve19;
	}
	public void setIrrigValve19(int irrigValve19) {
		this.irrigValve19 = irrigValve19;
	}
	public int getIrrigValve20() {
		return irrigValve20;
	}
	public void setIrrigValve20(int irrigValve20) {
		this.irrigValve20 = irrigValve20;
	}
	public int getIrrigValve21() {
		return irrigValve21;
	}
	public void setIrrigValve21(int irrigValve21) {
		this.irrigValve21 = irrigValve21;
	}
	public int getIrrigValve22() {
		return irrigValve22;
	}
	public void setIrrigValve22(int irrigValve22) {
		this.irrigValve22 = irrigValve22;
	}
	public int getIrrigValve23() {
		return irrigValve23;
	}
	public void setIrrigValve23(int irrigValve23) {
		this.irrigValve23 = irrigValve23;
	}
	public int getIrrigValve24() {
		return irrigValve24;
	}
	public void setIrrigValve24(int irrigValve24) {
		this.irrigValve24 = irrigValve24;
	}
	public int getIrrigValve25() {
		return irrigValve25;
	}
	public void setIrrigValve25(int irrigValve25) {
		this.irrigValve25 = irrigValve25;
	}
	public int getIrrigValve26() {
		return irrigValve26;
	}
	public void setIrrigValve26(int irrigValve26) {
		this.irrigValve26 = irrigValve26;
	}
	public int getIrrigValve27() {
		return irrigValve27;
	}
	public void setIrrigValve27(int irrigValve27) {
		this.irrigValve27 = irrigValve27;
	}
	public int getIrrigValve28() {
		return irrigValve28;
	}
	public void setIrrigValve28(int irrigValve28) {
		this.irrigValve28 = irrigValve28;
	}
	public int getIrrigValve29() {
		return irrigValve29;
	}
	public void setIrrigValve29(int irrigValve29) {
		this.irrigValve29 = irrigValve29;
	}
	public int getIrrigValve30() {
		return irrigValve30;
	}
	public void setIrrigValve30(int irrigValve30) {
		this.irrigValve30 = irrigValve30;
	}
	public int getIrrigValve31() {
		return irrigValve31;
	}
	public void setIrrigValve31(int irrigValve31) {
		this.irrigValve31 = irrigValve31;
	}
	public int getIrrigValve32() {
		return irrigValve32;
	}
	public void setIrrigValve32(int irrigValve32) {
		this.irrigValve32 = irrigValve32;
	}
	public int getIrrigValve33() {
		return irrigValve33;
	}
	public void setIrrigValve33(int irrigValve33) {
		this.irrigValve33 = irrigValve33;
	}
	public int getIrrigValve34() {
		return irrigValve34;
	}
	public void setIrrigValve34(int irrigValve34) {
		this.irrigValve34 = irrigValve34;
	}
	public int getIrrigValve35() {
		return irrigValve35;
	}
	public void setIrrigValve35(int irrigValve35) {
		this.irrigValve35 = irrigValve35;
	}
	public int getIrrigValve36() {
		return irrigValve36;
	}
	public void setIrrigValve36(int irrigValve36) {
		this.irrigValve36 = irrigValve36;
	}
	public int getIrrigValve37() {
		return irrigValve37;
	}
	public void setIrrigValve37(int irrigValve37) {
		this.irrigValve37 = irrigValve37;
	}
	public int getIrrigValve38() {
		return irrigValve38;
	}
	public void setIrrigValve38(int irrigValve38) {
		this.irrigValve38 = irrigValve38;
	}
	public int getIrrigValve39() {
		return irrigValve39;
	}
	public void setIrrigValve39(int irrigValve39) {
		this.irrigValve39 = irrigValve39;
	}
	public int getIrrigValve40() {
		return irrigValve40;
	}
	public void setIrrigValve40(int irrigValve40) {
		this.irrigValve40 = irrigValve40;
	}
	public int getIrrigValve41() {
		return irrigValve41;
	}
	public void setIrrigValve41(int irrigValve41) {
		this.irrigValve41 = irrigValve41;
	}
	public int getIrrigValve42() {
		return irrigValve42;
	}
	public void setIrrigValve42(int irrigValve42) {
		this.irrigValve42 = irrigValve42;
	}
	public int getIrrigValve43() {
		return irrigValve43;
	}
	public void setIrrigValve43(int irrigValve43) {
		this.irrigValve43 = irrigValve43;
	}
	public int getIrrigValve44() {
		return irrigValve44;
	}
	public void setIrrigValve44(int irrigValve44) {
		this.irrigValve44 = irrigValve44;
	}
	public int getIrrigValve45() {
		return irrigValve45;
	}
	public void setIrrigValve45(int irrigValve45) {
		this.irrigValve45 = irrigValve45;
	}
	public int getIrrigValve46() {
		return irrigValve46;
	}
	public void setIrrigValve46(int irrigValve46) {
		this.irrigValve46 = irrigValve46;
	}
	public int getIrrigValve47() {
		return irrigValve47;
	}
	public void setIrrigValve47(int irrigValve47) {
		this.irrigValve47 = irrigValve47;
	}
	public int getIrrigValve48() {
		return irrigValve48;
	}
	public void setIrrigValve48(int irrigValve48) {
		this.irrigValve48 = irrigValve48;
	}
	public int getIrrigValve49() {
		return irrigValve49;
	}
	public void setIrrigValve49(int irrigValve49) {
		this.irrigValve49 = irrigValve49;
	}
	public int getIrrigValve50() {
		return irrigValve50;
	}
	public void setIrrigValve50(int irrigValve50) {
		this.irrigValve50 = irrigValve50;
	}
	public int getIrrigValve51() {
		return irrigValve51;
	}
	public void setIrrigValve51(int irrigValve51) {
		this.irrigValve51 = irrigValve51;
	}
	public int getIrrigValve52() {
		return irrigValve52;
	}
	public void setIrrigValve52(int irrigValve52) {
		this.irrigValve52 = irrigValve52;
	}
	public int getIrrigValve53() {
		return irrigValve53;
	}
	public void setIrrigValve53(int irrigValve53) {
		this.irrigValve53 = irrigValve53;
	}
	public int getIrrigValve54() {
		return irrigValve54;
	}
	public void setIrrigValve54(int irrigValve54) {
		this.irrigValve54 = irrigValve54;
	}
	public int getIrrigValve55() {
		return irrigValve55;
	}
	public void setIrrigValve55(int irrigValve55) {
		this.irrigValve55 = irrigValve55;
	}
	public int getIrrigValve56() {
		return irrigValve56;
	}
	public void setIrrigValve56(int irrigValve56) {
		this.irrigValve56 = irrigValve56;
	}
	public int getIrrigValve57() {
		return irrigValve57;
	}
	public void setIrrigValve57(int irrigValve57) {
		this.irrigValve57 = irrigValve57;
	}
	public int getIrrigValve58() {
		return irrigValve58;
	}
	public void setIrrigValve58(int irrigValve58) {
		this.irrigValve58 = irrigValve58;
	}
	public int getIrrigValve59() {
		return irrigValve59;
	}
	public void setIrrigValve59(int irrigValve59) {
		this.irrigValve59 = irrigValve59;
	}
	public int getIrrigValve60() {
		return irrigValve60;
	}
	public void setIrrigValve60(int irrigValve60) {
		this.irrigValve60 = irrigValve60;
	}
	public int getIrrigValve61() {
		return irrigValve61;
	}
	public void setIrrigValve61(int irrigValve61) {
		this.irrigValve61 = irrigValve61;
	}
	public int getIrrigValve62() {
		return irrigValve62;
	}
	public void setIrrigValve62(int irrigValve62) {
		this.irrigValve62 = irrigValve62;
	}
	public int getIrrigValve63() {
		return irrigValve63;
	}
	public void setIrrigValve63(int irrigValve63) {
		this.irrigValve63 = irrigValve63;
	}
	public int getIrrigValve64() {
		return irrigValve64;
	}
	public void setIrrigValve64(int irrigValve64) {
		this.irrigValve64 = irrigValve64;
	}
	@Override
	public String toString() {
		return "ManualControlPojo [userId=" + userId + ", fertilizerId=" + fertilizerId + ", dtuCode=" + dtuCode
				+ ", time=" + time + ", start=" + start + ", end=" + end + ", irrigValve1=" + irrigValve1
				+ ", irrigValve2=" + irrigValve2 + ", irrigValve3=" + irrigValve3 + ", irrigValve4=" + irrigValve4
				+ ", irrigValve5=" + irrigValve5 + ", irrigValve6=" + irrigValve6 + ", irrigValve7=" + irrigValve7
				+ ", irrigValve8=" + irrigValve8 + ", irrigValve9=" + irrigValve9 + ", irrigValve10=" + irrigValve10
				+ ", irrigValve11=" + irrigValve11 + ", irrigValve12=" + irrigValve12 + ", irrigValve13=" + irrigValve13
				+ ", irrigValve14=" + irrigValve14 + ", irrigValve15=" + irrigValve15 + ", irrigValve16=" + irrigValve16
				+ ", irrigValve17=" + irrigValve17 + ", irrigValve18=" + irrigValve18 + ", irrigValve19=" + irrigValve19
				+ ", irrigValve20=" + irrigValve20 + ", irrigValve21=" + irrigValve21 + ", irrigValve22=" + irrigValve22
				+ ", irrigValve23=" + irrigValve23 + ", irrigValve24=" + irrigValve24 + ", irrigValve25=" + irrigValve25
				+ ", irrigValve26=" + irrigValve26 + ", irrigValve27=" + irrigValve27 + ", irrigValve28=" + irrigValve28
				+ ", irrigValve29=" + irrigValve29 + ", irrigValve30=" + irrigValve30 + ", irrigValve31=" + irrigValve31
				+ ", irrigValve32=" + irrigValve32 + ", irrigValve33=" + irrigValve33 + ", irrigValve34=" + irrigValve34
				+ ", irrigValve35=" + irrigValve35 + ", irrigValve36=" + irrigValve36 + ", irrigValve37=" + irrigValve37
				+ ", irrigValve38=" + irrigValve38 + ", irrigValve39=" + irrigValve39 + ", irrigValve40=" + irrigValve40
				+ ", irrigValve41=" + irrigValve41 + ", irrigValve42=" + irrigValve42 + ", irrigValve43=" + irrigValve43
				+ ", irrigValve44=" + irrigValve44 + ", irrigValve45=" + irrigValve45 + ", irrigValve46=" + irrigValve46
				+ ", irrigValve47=" + irrigValve47 + ", irrigValve48=" + irrigValve48 + ", irrigValve49=" + irrigValve49
				+ ", irrigValve50=" + irrigValve50 + ", irrigValve51=" + irrigValve51 + ", irrigValve52=" + irrigValve52
				+ ", irrigValve53=" + irrigValve53 + ", irrigValve54=" + irrigValve54 + ", irrigValve55=" + irrigValve55
				+ ", irrigValve56=" + irrigValve56 + ", irrigValve57=" + irrigValve57 + ", irrigValve58=" + irrigValve58
				+ ", irrigValve59=" + irrigValve59 + ", irrigValve60=" + irrigValve60 + ", irrigValve61=" + irrigValve61
				+ ", irrigValve62=" + irrigValve62 + ", irrigValve63=" + irrigValve63 + ", irrigValve64=" + irrigValve64
				+ "]";
	}
	

	

}
