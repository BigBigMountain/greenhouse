package com.lyyh.greenhouse.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.ClimaticCollector;
import com.lyyh.greenhouse.pojo.vo.ClimaticVo;

public interface ClimaticDao {

	/*
	 * 查询最新天气信息
	 */
	public Climatic getNewest(@Param("zoneId")Integer zoneId);

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

	//插入一条气象数据
	public void insertOne(ClimaticCollector climaticCollector);

	public void insertToHis();

	public void deleteData();
	//获取当前的总降雨量,最后一条降雨量的值
	public Double getLastRainFall(@Param("zoneId") Integer zoneId);
	//指定日期的降雨量累计值
	public Double getRainFallByDate(@Param("zoneId") Integer zoneId,@Param("day") String day);
	//插入降雨量记录
	public void insertRainFallLog(@Param("zoneId")Integer zoneId,@Param("day")String day,@Param("value")Double value);
	//更新降雨量记录
	public void updateRainFallLog(@Param("zoneId")Integer zoneId,@Param("day")String day,@Param("value")Double value);

	public List<Climatic> getClimaticData(@Param("zoneId")Integer zoneId,@Param("start") Date start, @Param("end")Date end);

	public Double getLastOneDayAgoRainFall(Integer zoneId, String format);
}
