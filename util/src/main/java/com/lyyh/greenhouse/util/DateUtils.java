package com.lyyh.greenhouse.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

public class DateUtils {

	public static String dateFormat(Date date){
		
		/*
		 * Date.UTC(2013,5,2)
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
		String str = dateFormat.format(date);
		str="Date.UTC("+str+")";
		return str;
		
		
	}
	
	
	public static void main(String[] args) {
		Date date = new Date();
		String string = DateUtils.dateFormat(date);
		System.out.println(string);
	}
}
