package com.lyyh.greenhouse.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.bouncycastle.util.Arrays;

public class PlcUtils {
	public static byte[] addCrc(byte[] data) {
		byte[] temdata = new byte[data.length + 2];
		System.arraycopy(data, 0, temdata, 0, data.length);
		byte[] crc = getCrc(data);
		temdata[temdata.length - 2] = crc[0];
		temdata[temdata.length - 1] = crc[1];
		return temdata;
	}

	public static int[] addCrc(int[] data) {
		int[] temdata = new int[data.length + 2];
		System.arraycopy(data, 0, temdata, 0, data.length);
		int[] crc = getCrc(data);
		temdata[temdata.length - 2] = crc[0];
		temdata[temdata.length - 1] = crc[1];
		return temdata;
	}

	public static int[] getCrc(int data[]) {
		int crc[] = new int[2];
		// unsigned char alen = *aStr – 2; //CRC16只计算前两部分
		int xda, xdapoly;
		int i, j, xdabit;
		xda = 0xFFFF;
		xdapoly = 0xA001; // (X**16 + X**15 + X**2 + 1)
		for (i = 0; i < data.length; i++) {
			xda ^= data[i];
			for (j = 0; j < 8; j++) {
				xdabit = (int) (xda & 0x01);
				xda >>= 1;
				if (xdabit == 1)
					xda ^= xdapoly;
			}
		}
		crc[0] = (int) (xda & 0xFF);
		crc[1] = (int) (xda >> 8);
		return crc;
	}

	public static byte[] getCrc(byte res[]) {
		int[] data = byteArrayToIntArray(res);
		int crc[] = new int[2];
		// unsigned char alen = *aStr – 2; //CRC16只计算前两部分
		int xda, xdapoly;
		int i, j, xdabit;
		xda = 0xFFFF;
		xdapoly = 0xA001; // (X**16 + X**15 + X**2 + 1)
		for (i = 0; i < data.length; i++) {
			xda ^= data[i];
			for (j = 0; j < 8; j++) {
				xdabit = (int) (xda & 0x01);
				xda >>= 1;
				if (xdabit == 1)
					xda ^= xdapoly;
			}
		}
		crc[0] = (int) (xda & 0xFF);
		crc[1] = (int) (xda >> 8);
		return intArrayTobyteArray(crc);
	}

	public static int[] byteArrayToIntArray(byte[] res) {
		int[] des = new int[res.length];
		for (int i = 0; i < res.length; i++) {
			des[i] = res[i] & 0xff;
		}
		return des;
	}

	public static byte[] intArrayTobyteArray(int[] res) {
		byte[] des = new byte[res.length];
		for (int i = 0; i < res.length; i++) {
			des[i] = (byte) res[i];
		}
		return des;
	}

	public static boolean verifyData(byte[] data) {
		if (data != null && data.length > 2) {
			byte[] temp = Arrays.copyOf(data, data.length - 2);
			byte[] crc = getCrc(temp);
//			System.out.println("算出来的crc : "+ crc[0]+"  "+crc[1]);
//			System.out.println("数据的crc : "+data[data.length - 2]+"  "+data[data.length - 1]);
			if (crc[0] == data[data.length - 2] && crc[1] == data[data.length - 1]) {
				return true;
			}
		}
		System.out.println("crc校验错误>>>>>, "+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss"));
		return false;
	}

	public static boolean verifyData(int[] data) {
		if (data != null && data.length != 0) {
			int[] temp = Arrays.copyOf(data, data.length - 2);
			int[] crc = getCrc(temp);
			if (crc[0] == data[data.length - 2] && crc[1] == data[data.length - 1]) {
				return true;
			}
		}
		return false;
	}

	/*
	 * public static void main(String[] args) { int b[]={0x01 ,0x03 ,0x00 ,0xC8
	 * ,0x00 ,0x32}; int[] crc = addCrc(b); for (int i : crc) {
	 * System.out.print(i+" "); } int[] crc2 = getCrc(b);
	 * System.out.print(crc2[0]+" "+crc2[1]); // 1 3 0 200 0 50 69 225 69 225 }
	 */
	
	
	public static boolean verifyPlcModbus(byte[] write,byte[] read){
		try{
			//1,读线圈,第二个字节为01
			if(write[1] == 1){
				int dateLength = convertUnsignedNumber(write[4],write[5])/8;
				if(dateLength == (read[2]&255) && (dateLength+5) == read.length){
					return true;
				}
			}
			//2,读寄存器,第二个字节为03
			if(write[1] == 3){
				int dateLength = convertUnsignedNumber(write[4],write[5])*2;
				if(dateLength == (read[2]&255) && (dateLength+5) == read.length){
					return true;
				}
			}
			//3,写线圈 第二个字节为 0F
			if(write[1] == 15){
				int writeWindingNumber = convertUnsignedNumber(write[4],write[5]);
				int readWindingNumber = convertUnsignedNumber(read[4],read[5]);
				if(writeWindingNumber == readWindingNumber && read.length == 8){
					return true;
				}
			}
			//4,写寄存器,第二个字节为10
			if(write[1] == 16){
				int writeTemporaryStorageNumber = convertUnsignedNumber(write[4],write[5]);
				int readTemporaryStorageNumber = convertUnsignedNumber(read[4],read[5]);
				if(writeTemporaryStorageNumber == readTemporaryStorageNumber && read.length == 8){
					return true;
				}
			}
			System.out.println("返回数据格式校验错误>>>>>, "+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss"));
			return false;
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("返回数据格式校验错误>>>>>, "+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss"));
			return false;
		}
	}
	
	public static int convertUnsignedNumber(byte a,byte b){
		return ((a & 255) << 8) + (b & 255);
	}
	
	public static void main(String[] args) {
		byte[] readProgramAndFormula5 = {0x01 ,0x03 ,0x01 ,(byte)0xD6 ,0x00 ,0x01};
		byte[] addCrc = addCrc(readProgramAndFormula5);
		
		System.out.println(addCrc);
		
	}
}
