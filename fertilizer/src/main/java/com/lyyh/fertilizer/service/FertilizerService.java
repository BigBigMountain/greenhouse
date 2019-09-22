package com.lyyh.fertilizer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lyyh.fertilizer.pojo.ChartsPoint;
import com.lyyh.fertilizer.pojo.Climatic;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.pojo.FertilizerData;
import com.lyyh.fertilizer.pojo.FertilizerDataCharts;
import com.lyyh.fertilizer.pojo.FertilizerDataCollector;
import com.lyyh.fertilizer.pojo.IrrigationFormula;
import com.lyyh.fertilizer.pojo.IrrigationProgram;
import com.lyyh.fertilizer.pojo.ManualControlPojo;
import com.lyyh.fertilizer.pojo.ProgramControlPojo;
import com.lyyh.fertilizer.pojo.PumpState;
import com.lyyh.fertilizer.pojo.SoilMoisture;
import com.lyyh.fertilizer.pojo.ValveDataVo;
import com.lyyh.fertilizer.pojo.ValveValue;
import com.lyyh.fertilizer.pojo.vo.ValveStatistics;
import com.lyyh.tzgk.pojo.Tvalve;

public interface FertilizerService {

	// 无条件查找所有施肥机
	public List<Fertilizer> queryAllFertilizer();

	// 查找某用户下所有的施肥机
	public List<Fertilizer> queryAllByUid(int userId);

	public List<Fertilizer> queryAllByUsername(String username);

	// 根据dtuCode查找施肥机
	public Fertilizer selectByDtuCode(String dtuCode);

	// 根据fertilizerId查找施肥机
	public Fertilizer selectByFertilizerId(int fertilizerId);

	// 添加一个施肥机
	public void addFertilizer(Fertilizer fertilizer);

	// 删除一个施肥机
	public void deleteByFerId(int fertilizerId);

	// 修改一个施肥机
	public void updateFertilizer(Fertilizer fertilizer);

	// 设置在线
	public void setOnline(String dtuCode);

	// 设置离线
	public void setOffline(String dtuCode);

	// 设置启动/停止
	public void setStateOn(String dtuCode);

	// 设置启动/停止
	public void setStateOff(String dtuCode);

	// 添加一条数据
	public int addData(FertilizerDataCollector collector);
	
	// 添加一条数据
	public int addData(String dtuCode);

	// 批量插入数据
	public void addDatas(List<FertilizerDataCollector> collectors);

	// 批量插入数据
	public void addDatas();

	// 获取实时数据
	public FertilizerDataCollector getTimeData(String dtuCode);

	// 查询一条数据
	public FertilizerData selectOneDataByDataId(int dataId);

	// 查询一个施肥机下两个时间点之间的所有数据
	// 条件查询,比如一段时间内某台施肥机或某个用户的所有数据
	public List<FertilizerData> queryHistoryDatasByVo(FertilizerData vo);

	// 查询一个施肥机下当天的所有数据
	public List<FertilizerData> queryOneDayDatasByFertilizerId(int fertilizerId);

	// 获取highcharts图形用的数据格式
	public FertilizerDataCharts getDataCharts(FertilizerData vo);

	// 获取单种数据曲线值
	public Map<String, List> getSingleCharts(String type, FertilizerData vo);

	public List<Object[]> getSingleCharts2(String type, FertilizerData vo);

	// 获取曲线坐标点的集合
	public List<ChartsPoint> getChartsPoints(String type, FertilizerData vo);

	// 获取plc输出线圈
	public ManualControlPojo getManualControlData(String dtuCode) throws IOException;

	public ManualControlPojo getManualControlData(int fertilizerId) throws IOException;

	// 获取plc的程序状态
	public ProgramControlPojo getProgramControlData(Integer fertilizerId);

	public ProgramControlPojo getProgramControlData(String dtuCode);

	public String OpenOrCloseValves(Integer fertilizerId, List<ValveValue> valves) throws IOException;
	
	public String setManualControl(ManualControlPojo controlData, int valveNum, int value) throws IOException;

	public void setProgramControl(ProgramControlPojo programControlData) throws IOException;

	public String setProgramControl(Integer fertilizerId, Integer programNum, Integer value) throws IOException;

	public Climatic getClimaticByUserName(String userName);

	public List<ChartsPoint> getClimaticChartsPoints(String type, Climatic vo);

	public List<Integer> getValveData(Integer fertilizerId);

	public List<Integer> getValveData(String dtuCode);

	public List<Integer> getProgramData(Integer fertilizerId);

	public List<Integer> getProgramData(String dtuCode);

	public List<Integer> getProgramData2(Integer fertilizerId);

	public SoilMoisture getSoilMoisture(String username);

	public IrrigationProgram getProgramByIdAndNum(Integer fertilizerId, Integer programNum) throws IOException;

	public void setProgram(Integer fertilizerId, IrrigationProgram irrigationProgram) throws IOException;

	public IrrigationFormula getFormulaByIdAndNum(Integer fertilizerId, Integer formulaNum) throws IOException;

	public void setFormula(Integer fertilizerId, IrrigationFormula irrigationFormula) throws IOException;

	public List<Fertilizer> queryAllHaveDtuAndOnline();

	public List<Tvalve> queryTvalvesByDtuCode(String dtuCode);

	public Tvalve queryTvalveByDtuCodeAndNumber(String dtuCode, int i);
	
	public void insertTimeDataAndValveStatus(String dtuCode,Map<Integer,Integer> valveStatus);

	public List<ValveDataVo> getValveRecord(Integer fertilizerId);

	public List<ValveDataVo> getValveRecord(Integer fertilizerId, Date start, Date end);

	public String OpenOrCloseValve(Integer fertilizerId, ValveValue valveValue) throws IOException;

	public PumpState getPumpStatus(Integer fertilizerId) throws IOException;

	public List<ValveStatistics> getIrrigationStatistics(Integer fertilizerId, Integer valveNum, Date start, Date end);

}
