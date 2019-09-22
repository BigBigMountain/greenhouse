package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.LedClimatic;
import com.lyyh.greenhouse.pojo.LedHouse;
import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.pojo.LedTable;
import com.lyyh.greenhouse.pojo.User;

public interface LedService {


	List<LedProgram> listAll(Integer z_id);

	
	//增
	String add(LedProgram ledProgram, LedTable ledTable, LedHouse ledHouse, LedClimatic ledClimatic);
	//改
	String update(LedProgram ledProgram, LedTable ledTable, LedHouse ledHouse, LedClimatic ledClimatic);
	//查
	LedProgram findProgramByPid(Integer p_id);

	LedTable findLedTableByPid(Integer p_id);
	
	LedHouse findLedHouseByPid(Integer p_id);

	LedClimatic findLedClimaticByPid(Integer p_id);

	//删
	void delProgramByPid(Integer p_id);
	
	//预览led
	public String preview(String zoneName, LedProgram ledProgram,LedTable ledTable, LedHouse ledHouse ,LedClimatic ledClimatic);

	//启动led
	String showLed(User user, Integer p_id);
	
	String createImageAndSendToLed(List<HouseData> houseDatas, Climatic newestClimatic, LedProgram ledProgram,
			LedTable ledTable, LedHouse ledHouse, LedClimatic ledClimatic);

	//停止led显示
	void stopLed();







}
