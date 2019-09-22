package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.vo.CO2Vo;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;
import com.lyyh.greenhouse.pojo.vo.HumidityVo;
import com.lyyh.greenhouse.pojo.vo.LightingVo;
import com.lyyh.greenhouse.pojo.vo.SoilHumidityVo;
import com.lyyh.greenhouse.pojo.vo.SoilTemperatureVo;
import com.lyyh.greenhouse.pojo.vo.TemperatureVo;

public interface HouseDataDao {

	/*
	 * 获取最新室内数据
	 */
	HouseData getNewest(HouseDataVo houseDataVo);

	/*
	 * 室内温度1,2
	 */
	List<TemperatureVo> getTemperature1List(HouseDataVo houseDataVo);

	List<TemperatureVo> getTemperature2List(HouseDataVo houseDataVo);

	/*
	 * 室内湿度1,2
	 */
	List<HumidityVo> getHumidity1List(HouseDataVo houseDataVo);

	List<HumidityVo> getHumidity2List(HouseDataVo houseDataVo);

	/*
	 * 光照
	 */
	List<LightingVo> getLightingList(HouseDataVo houseDataVo);

	/*
	 * CO2
	 */
	List<CO2Vo> getCO2List(HouseDataVo houseDataVo);

	/*
	 * 土壤温度
	 */
	List<SoilTemperatureVo> getSoilTemperatureList(HouseDataVo houseDataVo);

	/*
	 * 土壤湿度
	 */
	List<SoilHumidityVo> getSoilHumidityList(HouseDataVo houseDataVo);

	/*
	 * 获取所有数据于一个表
	 */
	List<HouseData> getHouseDataList(HouseDataVo houseDataVo);

	//批量插入数据
	void insertList(List<HouseData> houseDatas);

	/*
	 * 获取该区域下所有温室的最新数据
	 */
	List<HouseData> getAllNewestByZoneId(Integer zoneId);

}
