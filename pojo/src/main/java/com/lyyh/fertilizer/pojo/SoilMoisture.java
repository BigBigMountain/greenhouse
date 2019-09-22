package com.lyyh.fertilizer.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SoilMoisture implements Serializable {

	private Map<String, Map<String, SoilTempAndHum>> sites = new HashMap<String, Map<String, SoilTempAndHum>>();

	public SoilMoisture() {
		super();
	}

	public void addSoilTempAndHum(SoilTempAndHum tah) {
		Map<String, SoilTempAndHum> site = this.sites.get(tah.getSiteNum() + "#");
		if (site == null) {
			site = new HashMap<String, SoilTempAndHum>();
			this.sites.put(tah.getSiteNum() + "#", site);
		}
		site.put(tah.getLayerNum() + "层", tah);
	}
	
	public void addNull() {
		this.sites.put("1#", null);
	}
	
	public int size(){
		return this.sites.size();
	}

	public Map<String, Map<String, SoilTempAndHum>> getSites() {
		return sites;
	}

	public void setSites(Map<String, Map<String, SoilTempAndHum>> sites) {
		this.sites = sites;
	}

	// 内部类
	public static class SoilTempAndHum {
		protected int id;
		protected Double temperature;
		protected Double humidity;
		protected int siteNum;
		protected String siteName;
		protected int layerNum;
		protected Date time;
		
		
		public String getSiteName() {
			return siteName;
		}
		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Double getTemperature() {
			return temperature;
		}
		public void setTemperature(Double temperature) {
			this.temperature = temperature;
		}
		public Double getHumidity() {
			return humidity;
		}
		public void setHumidity(Double humidity) {
			this.humidity = humidity;
		}
		public int getSiteNum() {
			return siteNum;
		}
		public void setSiteNum(int siteNum) {
			this.siteNum = siteNum;
		}
		public int getLayerNum() {
			return layerNum;
		}
		public void setLayerNum(int layerNum) {
			this.layerNum = layerNum;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		
	}

	public static void main(String[] args) {
		SoilTempAndHum tah = new SoilTempAndHum();
		tah.setSiteNum(1);
		tah.setLayerNum(1);
		tah.setTemperature(11.0);
		tah.setHumidity(11.5);

		SoilTempAndHum tah2 = new SoilTempAndHum();
		tah2.setSiteNum(1);
		tah2.setLayerNum(2);
		tah2.setTemperature(12.0);
		tah2.setHumidity(12.5);

		SoilTempAndHum tah3 = new SoilTempAndHum();
		tah3.setSiteNum(1);
		tah3.setLayerNum(3);
		tah3.setTemperature(13.0);
		tah3.setHumidity(13.5);

		SoilTempAndHum tah4 = new SoilTempAndHum();
		tah4.setSiteNum(2);
		tah4.setLayerNum(1);
		tah4.setTemperature(21.0);
		tah4.setHumidity(21.5);

		SoilMoisture sm = new SoilMoisture();
		sm.addSoilTempAndHum(tah3);
		sm.addSoilTempAndHum(tah2);
		sm.addSoilTempAndHum(tah4);
		sm.addSoilTempAndHum(tah);
		String jsonString = JSONObject.toJSONString(sm);
		System.out.println(jsonString);
		SoilMoisture sm2 = JSONObject.parseObject(jsonString, SoilMoisture.class);
		System.out.println(sm2);
	}
}
