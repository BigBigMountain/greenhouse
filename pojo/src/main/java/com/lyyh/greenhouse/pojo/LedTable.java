package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class LedTable implements Serializable {

	private Integer t_id;
	private Integer p_id;
	private int t_x;//xAxis
	private int t_y;//yAxis
	private int t_w;//width
	private int t_h;//height
	private int t_os;//offset,正数表示右移,负数表示左移
	
	private int t_r;//row
	private int t_c;//colom
	private int t_s;//fontsize
	private int t_t;//time
	
	
	
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public int getT_x() {
		return t_x;
	}
	public void setT_x(int t_x) {
		this.t_x = t_x;
	}
	public int getT_y() {
		return t_y;
	}
	public void setT_y(int t_y) {
		this.t_y = t_y;
	}
	public int getT_w() {
		return t_w;
	}
	public void setT_w(int t_w) {
		this.t_w = t_w;
	}
	public int getT_h() {
		return t_h;
	}
	public void setT_h(int t_h) {
		this.t_h = t_h;
	}
	public int getT_r() {
		return t_r;
	}
	public void setT_r(int t_r) {
		this.t_r = t_r;
	}
	
	public int getT_s() {
		return t_s;
	}
	public void setT_s(int t_s) {
		this.t_s = t_s;
	}
	public int getT_t() {
		return t_t;
	}
	public void setT_t(int t_t) {
		this.t_t = t_t;
	}
	public int getT_os() {
		return t_os;
	}
	public void setT_os(int t_os) {
		this.t_os = t_os;
	}
	public int getT_c() {
		return t_c;
	}
	public void setT_c(int t_c) {
		this.t_c = t_c;
	}
	@Override
	public String toString() {
		return "LedTable [t_id=" + t_id + ", p_id=" + p_id + ", t_x=" + t_x + ", t_y=" + t_y + ", t_w=" + t_w + ", t_h="
				+ t_h + ", t_os=" + t_os + ", t_r=" + t_r + ", t_c=" + t_c + ", t_s=" + t_s + ", t_t=" + t_t + "]";
	}
	
	
	
}
