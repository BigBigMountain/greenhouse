package com.lyyh.greenhouse.util.cache;

import java.util.Hashtable;

import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.WaterSensorData;

public class CacheManager<K, V> {
	
	private final Hashtable<K, V> cache = new Hashtable<K,V>();
	
	public static final CacheManager<Integer,WaterSensorData> waterMeter = new CacheManager<Integer,WaterSensorData>();
	
	public static final CacheManager<Integer,SensorData> newestSensorData = new CacheManager<Integer,SensorData>();
	
	public Hashtable<K, V> getCache(){
		return this.cache;
	}
	
	
	
}
