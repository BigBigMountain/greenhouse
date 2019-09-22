package com.lyyh.greenhouse.util.serial;


import java.math.BigDecimal;

import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.Screem8;
import com.lyyh.greenhouse.util.constant.Screem8Constant;
import com.lyyh.greenhouse.util.serial.serialException.NoSuchPort;
import com.lyyh.greenhouse.util.serial.serialException.NotASerialPort;
import com.lyyh.greenhouse.util.serial.serialException.PortInUse;
import com.lyyh.greenhouse.util.serial.serialException.SerialPortParameterFailure;

import gnu.io.SerialPort;


public class SerialUtil {

	public static byte[] houseDateConvert(HouseData houseData){
//		3D 1C 01 00 00 20 03 3B 03 2B 2C 35 00 25 01 00 FB 00 00 00 40 41 40 00 00 00 00 AA
		byte serialData[] = new byte[28];
		//协议头 -----2个字节
		serialData[0] = 0x3D;
		serialData[1] = 0x1C;
		//温室编号 ----1个字节
		serialData[2] = (byte)((int)(houseData.getHouseId() == null ? 0 : houseData.getHouseId()));
		//室外光照 -------2个字节
		serialData[3] = 0;
		serialData[4] = 0;
		//温室温度 ----2个字节
		byte[] temperature = temperatureConvert(houseData.getTemperature() == null ? 0 : houseData.getTemperature());
		serialData[5] = temperature[0];
		serialData[6] = temperature[1];
		//土壤温度 -----2个字节
		byte[] soilTemperature = temperatureConvert(houseData.getSoilTemperature() == null ? 0 : houseData.getSoilTemperature());
		serialData[7] = soilTemperature[0];
		serialData[8] = soilTemperature[1];
	
		//湿度1  1个字节
		byte[] humidity = humidityConvert(houseData.getHumidity() == null ? 0 : houseData.getHumidity());
		serialData[9] = humidity[0];
		//湿度 2  1个字节
		byte[] humidity2 = humidityConvert(houseData.getHumidity2() == null ? 0 : houseData.getHumidity2());
		serialData[10] = humidity2[0];
		//室内光照 	2个字节
		byte[] light = lightConvert(houseData.getLighting() == null ? 0 : houseData.getLighting());
		serialData[11] = light[0];
		serialData[12] = light[1];
		//室内CO2 2个字节
		byte[] co2 = co2Convert(houseData.getCo2() == null ? 0 : houseData.getCo2());
		serialData[13] = co2[0];
		serialData[14] = co2[1];
		//土壤湿度  1个字节
		byte[] soilHumidity = humidityConvert(houseData.getSoilHumidity() == null ? 0 : houseData.getSoilHumidity());
		serialData[15] = soilHumidity[0];
		
		
		
		//1# 拉幕位置
		serialData[16] = 0;
		//2# 拉幕位置
		serialData[17] = 0;
		//1#通风窗位置（1-100%）
		serialData[18] = 0;
		//2#通风窗位置（1-100%）
		serialData[19] = 0;
//		风机（ACC.0:0-停止，1-打开）
		serialData[20] = 0;
//		湿帘泵（ACC.0:0-停止，1-打开）
		serialData[21] = 0;
//		灌溉加湿（ACC.0:0-关闭，1-打开）
		serialData[22] = 0;
//		温室高温报警位（ACC.0:0-无警，1-有警）
		serialData[23] = 0;
//		温室低温报警位（ACC.0:0-无警，1-有警）
		serialData[24] = 0;
//		温室高湿报警位（ACC.0:0-无警，1-有警）
		serialData[25] = 0;
//		温室低湿报警位（ACC.0:0-无警，1-有警）
		serialData[26] = 0;
//		校验字节（AA） 2位
		serialData[27] = (byte)0xAA;
		
		
		
		return serialData;
	}
	
	
	//溫度(温室温度,土壤温度,)
	public static byte[] temperatureConvert(double d){
		int v = (int)(d*10.0)+500;
		return convertToByteArray(v,2);
	}
	//光照
	public static byte[] lightConvert(double d){
		BigDecimal b = new BigDecimal(d);
		int v = b.divide(new BigDecimal(1000),0,BigDecimal.ROUND_HALF_UP).intValue();
		return convertToByteArray(v,2);
	}
	//CO2
	public static byte[] co2Convert(double d){
		int v = (int)d;
		return convertToByteArray(v,2);
	}
	//湿度
	public static byte[] humidityConvert(double d){
		int v = (int)d;
		return convertToByteArray(v, 1);
	}
	
	
	public static byte[] convertToByteArray(int v,int num){
		byte array[] = new byte[num];
		for (int i = 0 ; i < array.length ; i++) {
			array[i] = (byte)(v >>> (8*i));
//			System.out.println(array[array.length - 1 - i]);
		}
		return array;
	}
	//Screem8Constant.setSetting(screem8.getZoneName(), screem8);
	public static synchronized void closeCurrentPort(Integer zoneId){
		SerialPort currentPort = Screem8Constant.getCurrentPort(zoneId);
		SerialTool.closePort(currentPort);
		Screem8Constant.setCurrentPort(zoneId,null);	
		Screem8Constant.setSetting(zoneId,null);
	}
	
	public static synchronized SerialPort openPort(Screem8 screem8) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse{
		SerialPort openPort = SerialTool.openPort(screem8.getCurrentPortName(), screem8.getBaudRate());
		Screem8Constant.setCurrentPort(screem8.getZoneId(),openPort);
		Screem8Constant.setSetting(screem8.getZoneId(),screem8);
		return openPort;
	}
}
