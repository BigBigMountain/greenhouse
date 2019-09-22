package com.lyyh.fertilizer.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.fertilizer.pojo.Climatic;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.pojo.FertilizerData;
import com.lyyh.fertilizer.pojo.FertilizerDataCharts;
import com.lyyh.fertilizer.pojo.FertilizerDataCollector;

public interface FertilizerDao {
	//无条件查找所有施肥机
	public List<Fertilizer> queryAll();
	
	//查找某用户下所有的施肥机
	public List<Fertilizer> queryAllByUid(int userId);
	

	public List<Fertilizer> queryAllByUsername(String username);
	
	//根据dtuCode查找施肥机
	public Fertilizer selectByDtuCode(String dtuCode);
	
	//根据fertilizerId查找施肥机
	public Fertilizer selectByFertilizerId(int fertilizerId);
	
	//添加一个施肥机
	public void addFertilizer(Fertilizer fertilizer);
	
	//删除一个施肥机
	public void deleteByFerId(int fertilizerId);
	
	//修改一个施肥机
	public void updateFertilizer(Fertilizer fertilizer);
	
	//设置在线
	public void setOnline(String dtuCode);
	//设置离线
	public void setOffline(String dtuCode);
	
	//设置启动
	public void setStateOn(String dtuCode);
	//设置停止
	public void setStateOff(String dtuCode);
	
	//添加一条数据
	public void addData(FertilizerDataCollector collector);
	
	//添加一堆数据
	public void addDatas(List<FertilizerDataCollector> collectors);
	
	//查询一条数据
	public FertilizerData selectOneDataByDataId(int dataId);
	
	//查询一个施肥机下两个时间点之间的所有数据
	//条件查询,比如一段时间内某台施肥机或某个用户的所有数据
	public List<FertilizerData> queryDatasByVo(FertilizerData vo);
	
	public List<FertilizerData> queryDatas(@Param("fertilizerId")Integer fertilizerId, @Param("start")Date start, @Param("end")Date end);
/*	
	//查询一个施肥机下当天的所有数据
	public List<FertilizerData> queryOneDayDatasByFertilizerId(int fertilizerId);
*/

//	public List<Climatic> queryClimaticByVo(Climatic vo);
	
	
	//获取所有带dtu的,并且在线的施肥机
	public List<Fertilizer> queryAllHaveDtuAndOnlineAndTZGK();

	public List<Fertilizer> queryAllHaveDtuAndOnline();

	//查询最新一条数据
	public FertilizerData getNewestDataByUserName(@Param("username") String username);


}
