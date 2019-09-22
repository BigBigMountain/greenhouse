package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class LedHouse implements Serializable {

	private Integer h_id;
	private Integer p_id;
	private int h_t1;//1有,0没有
	private int h_t2;//1有,0没有
	private int h_h1;//1有,0没有
	private int h_h2;//1有,0没有
	private int h_l;//1有,0没有
	private int h_c;//1有,0没有
	private int h_st;//1有,0没有
	private int h_sh;//1有,0没有
	
	
	
	
	
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getH_id() {
		return h_id;
	}
	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}
	public int getH_t1() {
		return h_t1;
	}
	public void setH_t1(int h_t1) {
		this.h_t1 = h_t1;
	}
	public int getH_t2() {
		return h_t2;
	}
	public void setH_t2(int h_t2) {
		this.h_t2 = h_t2;
	}
	public int getH_h1() {
		return h_h1;
	}
	public void setH_h1(int h_h1) {
		this.h_h1 = h_h1;
	}
	public int getH_h2() {
		return h_h2;
	}
	public void setH_h2(int h_h2) {
		this.h_h2 = h_h2;
	}
	public int getH_l() {
		return h_l;
	}
	public void setH_l(int h_l) {
		this.h_l = h_l;
	}
	public int getH_c() {
		return h_c;
	}
	public void setH_c(int h_c) {
		this.h_c = h_c;
	}
	public int getH_st() {
		return h_st;
	}
	public void setH_st(int h_st) {
		this.h_st = h_st;
	}
	public int getH_sh() {
		return h_sh;
	}
	public void setH_sh(int h_sh) {
		this.h_sh = h_sh;
	}
	@Override
	public String toString() {
		return "LedHouse [h_id=" + h_id + ", p_id=" + p_id + ", h_t1=" + h_t1 + ", h_t2=" + h_t2 + ", h_h1=" + h_h1
				+ ", h_h2=" + h_h2 + ", h_l=" + h_l + ", h_c=" + h_c + ", h_st=" + h_st + ", h_sh=" + h_sh + "]";
	}
	
	
	
}
