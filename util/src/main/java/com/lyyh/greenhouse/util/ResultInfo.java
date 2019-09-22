package com.lyyh.greenhouse.util;

import java.util.HashMap;
import java.util.Map;


public class ResultInfo {

	public final static int SUCCESS_CODE = 200;
	public final static int ERROR_CODE = 100;
	
	public final static String DEFAULT_MSG = "";
	public final static String DEFAULT_SUCCESS_MSG = "success";
	public final static String DEFAULT_ERROR_MSG = "error";
	
	public Map<String , Object> data = new HashMap<String , Object>();
	
	public int code;
	public String msg;
	

	public ResultInfo() {
		super();
	}

	public ResultInfo(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public static ResultInfo success(){
		return new ResultInfo(SUCCESS_CODE,DEFAULT_MSG);
	}
	
	public static ResultInfo success(String msg){
		return new ResultInfo(SUCCESS_CODE,msg);
	}
	
	public static ResultInfo success(String key,Object value){
		ResultInfo resultInfo = new ResultInfo(SUCCESS_CODE,DEFAULT_MSG);
		resultInfo.getData().put(key, value);
		return resultInfo;
	}
	
	
	public ResultInfo put(String  key , Object value){
		this.getData().put(key, value);
		return this;
	}
	
	
	public static ResultInfo error(String msg){
		return new ResultInfo(ERROR_CODE,msg);
	}
	

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
