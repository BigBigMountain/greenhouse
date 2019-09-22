package com.lyyh.greenhouse.util;


import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

	public static String firstToUpperCase(String str) {
		if (str.length() > 1) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		if (str.length() == 1) {
			return str.toUpperCase();
		}
		return str;
	}

	public static void foreach(Object[] array, int col) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
			if (i % col == 0) {
				System.out.println();
			}
		}
	}
	
	public static void printByteArray(String preMsg,byte[] array){
		if(StringUtils.isNotBlank(preMsg)){
			System.out.print(preMsg+" : ");
		}
		for(byte b : array){
			System.out.print(Integer.toHexString(b&0xff) + " ");
		}
		System.out.println();
	}
	
	
}
