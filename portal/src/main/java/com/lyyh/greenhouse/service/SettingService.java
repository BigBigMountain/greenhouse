package com.lyyh.greenhouse.service;

import java.util.List;
import java.util.Map;

import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.HouseSetting;

public interface SettingService {

//	//获取所有温室设置
//	Map<String, Object> queryAllSettings(String zoneName);

	//更新设置
	String saveOrUpdateHouseSetting(HouseSetting houseSetting);

	//使制定温室设置失效
	void setOffHouseSetting(int id);

	//查询气象设置
	ClimaticSetting queryClimaticSetting(Integer zoneId);

	//保存或更新气象设置
	String saveOrUpdateClimaticSetting(ClimaticSetting climaticSetting);

	//查询所有温室设置
	List<HouseSetting> queryAllHouseSettings(Integer zoneId);

	//查询气象数据种类
	List<ClimaticDataType> queryClimaticDataTypes(Integer zoneId);

}
