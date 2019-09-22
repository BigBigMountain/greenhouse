package com.lyyh.greenhouse.util.exception;

public class DisconnectException extends Exception {
	
	String msg;

	public DisconnectException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
