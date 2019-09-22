package com.lyyh.greenhouse.service;

import java.util.Date;
import java.util.List;

import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.vo.ClimaticVo;

public interface ClimaticService {

	/*
	 * 获取最新天气信息
	 */
	Climatic getNewest(Integer zoneId);
	/*
	 * 获取温度列表
	 */
	List<Object[]> getTemperatureList(ClimaticVo climaticVo);
	/*
	 * 湿度
	 */
	List<Object[]> getHumidityList(ClimaticVo climaticVo);
	/*
	 * 光照
	 */
	List<Object[]> getLightingList(ClimaticVo climaticVo);
	/*
	 * 气压
	 */
	List<Object[]> getPressureList(ClimaticVo climaticVo);
	/*
	 * 风速
	 */
	List<Object[]> getWindSpeedList(ClimaticVo climaticVo);
	/*
	 * 降雨量
	 */
	List<Object[]> getRainFallList(ClimaticVo climaticVo);
	/*
	 * PH值
	 */
	List<Object[]> getPHList(ClimaticVo climaticVo);
	/*
	 * PM2.5
	 */
	List<Object[]> getPM25List(ClimaticVo climaticVo);
	
	List<Climatic> getClimaticData(Integer zoneId, Date start, Date end);

	
}
