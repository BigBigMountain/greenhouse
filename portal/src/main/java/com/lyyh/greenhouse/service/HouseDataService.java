package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.HouseData_highcharts;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;

public interface HouseDataService {
	/*
	 * 获取最新温室信息
	 */
	HouseData getNewest(HouseDataVo houseDataVo);

	
	/*
	 * 温度,湿度,光照,co2
	 */
	HouseData_highcharts getIndexList(HouseDataVo houseDataVo);

	List<HouseData> downloadData(HouseDataVo houseDataVo);



}
