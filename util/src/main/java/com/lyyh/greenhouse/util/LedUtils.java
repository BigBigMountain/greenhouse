package com.lyyh.greenhouse.util;

public class LedUtils {

	public static int getStringLength(String str){
		int length=0;
		String trim = str.trim();
		char[] charArray = trim.toCharArray();
		for (char c : charArray) {
			if(c<128 && c>0){
				length++;
			}else {
				length+=2;
			}
			
		}
		return length;
		
	}
	
	public static void main(String[] args) {
		System.out.println(getStringLength("温度1"));;
	}
}
