package com.lyyh.greenhouse.util.tzgk;

import java.util.HashMap;
import java.util.Map;

import com.lyyh.tzgk.pojo.Tvalve;
public class ValveMap {

	private static Map<String,Map<Integer,Tvalve>> tvMap= new HashMap<String,Map<Integer,Tvalve>>();
	
	public static Map<Integer,Tvalve> getTvalves(String dtuCode){
		return tvMap.get(dtuCode);
	}
	
	public static Map<Integer,Tvalve> putTvalves(String dtuCode,Map<Integer,Tvalve> tvalves){
		return tvMap.put(dtuCode, tvalves);
	}
	
	public static Map<Integer,Tvalve> removeTvalves(String dtuCode){
		return tvMap.remove(dtuCode);
	}
}
