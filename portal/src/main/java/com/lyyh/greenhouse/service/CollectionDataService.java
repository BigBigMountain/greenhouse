package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.NodeConfig;

public interface CollectionDataService {

//	//采集
//	public String collectData(List<HouseSetting> houseSettings);
	//停止采集
	public void stopCollect();
	//启动采集
	public void startCollect(Integer zoneId);
	
	public void collectData(Integer zoneId);
	
	public void collectHouseData(List<NodeConfig> houseNodeConfigs);
	
	public void collectClimaticData(ClimaticSetting climaticSetting);
}
