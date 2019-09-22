package com.lyyh.greenhouse.util.tzgk;

public class LoginToken {

//	public static String name = "BJLY1";
//	public static String pwd = "123456";
	
	private static Integer uid = null;
	private static String tokey = null;
	
	public static void setToken(int uid,String tokey){
		LoginToken.uid = uid;
		LoginToken.tokey = tokey;
	}
	
	public static int getUid(){
		return uid;
	}
	
	public static String getTokey(){
		return tokey;
	}
}
