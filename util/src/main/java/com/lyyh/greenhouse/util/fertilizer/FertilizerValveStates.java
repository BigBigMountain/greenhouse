package com.lyyh.greenhouse.util.fertilizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FertilizerValveStates {

	public static Map<String,List<Integer>> statesMap = new HashMap<String,List<Integer>>();
	public static Map<String,Boolean> isInitialMap = new HashMap<String,Boolean>();
	
	
	public static Boolean getInitialInfo(String dtuCode){
		return isInitialMap.get(dtuCode);
	}
	
	public static void setInitialInfo(String dtuCode,boolean initial){
		isInitialMap.put(dtuCode, initial);
	}
	
	public static List<Integer> getStatesByDtuCode(String dtuCode){
		return statesMap.get(dtuCode);
	}
	
	public static List<Integer> setStates(String dtuCode,List<Integer> states){
		return statesMap.put(dtuCode, states);
	}
}
