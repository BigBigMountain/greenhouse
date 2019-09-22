package com.lyyh.greenhouse.util.constant;

import java.util.HashMap;
import java.util.Map;

import com.lyyh.greenhouse.pojo.Screem8;

import gnu.io.SerialPort;

public class Screem8Constant {

	//<zoneName,setting>
	private static Map<Integer,Screem8> screem8Map = new HashMap<>();
	
	//<zoneName,currentPort>
	private static Map<Integer,SerialPort> currentPortMap = new HashMap<>();

	public static Screem8 getSetting(Integer zoneId) {
		
//		return screem8Map.get(zoneName) == null ? new Screem8() : screem8Map.get(zoneName);
		return screem8Map.get(zoneId);
	}

	public static void setSetting(Integer zoneId,Screem8 setting) {
		screem8Map.put(zoneId, setting);
	}

	public static SerialPort getCurrentPort(Integer zoneId) {
		return currentPortMap.get(zoneId);
	}

	public static void setCurrentPort(Integer zoneId,SerialPort currentPort) {
		currentPortMap.put(zoneId, currentPort);
	}
	
	
}
