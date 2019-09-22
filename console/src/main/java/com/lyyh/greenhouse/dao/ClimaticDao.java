package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.vo.ClimaticVo;
import com.lyyh.greenhouse.pojo.vo.TemperatureVo;

public interface ClimaticDao {

	/*
	 * 查询最新天气信息
	 */
	public Climatic getNewest(String zoneName);

	/*
	 * 温度
	 */
	public List<Climatic> getTemperatureList(ClimaticVo climaticVo);

	/*
	 * 湿度
	 */
	public List<Climatic> getHumidityList(ClimaticVo climaticVo);

	//光照
	public List<Climatic> getLightingList(ClimaticVo climaticVo);

	//气压
	public List<Climatic> getPressureList(ClimaticVo climaticVo);

	//风速
	public List<Climatic> getWindSpeedList(ClimaticVo climaticVo);

	//降雨量
	public List<Climatic> getRainFallList(ClimaticVo climaticVo);

	//PH
	public List<Climatic> getPHList(ClimaticVo climaticVo);

	//PM2.5
	public List<Climatic> getPM25List(ClimaticVo climaticVo);

}
