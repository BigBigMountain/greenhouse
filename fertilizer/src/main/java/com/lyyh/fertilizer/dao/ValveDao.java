package com.lyyh.fertilizer.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.fertilizer.pojo.ValveData;
import com.lyyh.fertilizer.pojo.ValveDataVo;

public interface ValveDao {

	public void insertValveData(ValveData data);
	
	public void insertValveDataList(List<ValveData> dataList);
	
	public List<ValveData> queryByFertilizerId(Integer fertilizerId);
	
	public List<ValveData> queryByPeriod(@Param("fertilizerId") Integer fertilizerId,@Param("start") Date start,@Param("end") Date end);
	
	public List<ValveDataVo> queryVoByPeriod (@Param("fertilizerId") Integer fertilizerId,@Param("start") Date start,@Param("end") Date end);
	
	public List<ValveDataVo> queryVoByVo (@Param("fertilizerId") Integer fertilizerId,@Param("number") Integer number, @Param("start") Date start,@Param("end") Date end);
	
	
}
