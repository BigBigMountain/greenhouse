package com.lyyh.greenhouse.pojo;

import java.io.Serializable;

public class LedProgram implements Serializable {

	private Integer p_id;
	private Integer z_id;
	private String p_name;
	private String p_info;
	private Integer p_state;
	private String p_ip;
	private Integer p_width;
	private Integer p_height;
	private Integer p_colourType;// 1.单色  2.双基色  3.七彩  4.全彩,默认是2;
	
	
	
	public String getP_info() {
		return p_info;
	}
	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	
	public Integer getZ_id() {
		return z_id;
	}
	public void setZ_id(Integer z_id) {
		this.z_id = z_id;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Integer getP_state() {
		return p_state;
	}
	public void setP_state(Integer p_state) {
		this.p_state = p_state;
	}
	public String getP_ip() {
		return p_ip;
	}
	public void setP_ip(String p_ip) {
		this.p_ip = p_ip;
	}
	public Integer getP_width() {
		return p_width;
	}
	public void setP_width(Integer p_width) {
		this.p_width = p_width;
	}
	public Integer getP_height() {
		return p_height;
	}
	public void setP_height(Integer p_height) {
		this.p_height = p_height;
	}
	public Integer getP_colourType() {
		return p_colourType;
	}
	public void setP_colourType(Integer p_colourType) {
		this.p_colourType = p_colourType;
	}
	@Override
	public String toString() {
		return "LedProgram [p_id=" + p_id + ", z_id=" + z_id + ", p_name=" + p_name + ", p_info=" + p_info
				+ ", p_state=" + p_state + ", p_ip=" + p_ip + ", p_width=" + p_width + ", p_height=" + p_height
				+ ", p_colourType=" + p_colourType + "]";
	}
	
	
	
	
	
	
	
}
