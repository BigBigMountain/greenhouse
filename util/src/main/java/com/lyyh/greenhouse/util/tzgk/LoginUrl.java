package com.lyyh.greenhouse.util.tzgk;

public class LoginUrl {

	private static String url = "http://" + TzgkConstant.host+"/API/user/login";
	private String name;
	private String pwd;
	
	private int id;
	private String msg;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		LoginUrl.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return url+"?"+"name=" + name + "&"+"pwd=" + pwd;
	}
	
	
}
