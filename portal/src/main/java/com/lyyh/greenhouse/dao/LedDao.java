package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.LedClimatic;
import com.lyyh.greenhouse.pojo.LedHouse;
import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.pojo.LedTable;

public interface LedDao {
	
	
//获取用户下所有节目
	List<LedProgram> listAll(Integer z_id);

/*
 * 插入五个数据模型
 */
	void addLedProgram(LedProgram ledProgram);
	
	void addLedTable(LedTable ledTable);
	
	void addLedHouse(LedHouse ledHouse);
	
	void addLedClimatic(LedClimatic ledClimatic);
//查	
	LedProgram findProgramByPid(Integer p_id);
	
	LedTable findLedTableByPid(Integer p_id);
	
	LedHouse findLedHouseByPid(Integer P_id);
	
	LedClimatic findLedClimaticByPid(Integer p_id);
	
	List<LedProgram> findProgramByZoneid(@Param("zoneId") Integer zoneId);
	
//改	
	void updateProgram(LedProgram ledProgram);
	
	void updateLedHouse(LedHouse ledHouse);
	
	void updateLedTable(LedTable ledTable);

	void updateLedClimatic(LedClimatic ledClimatic);
//删
	void delLedHouseByPid(Integer p_id);

	void delLedTableByPid(Integer p_id);

	void delLedClimaticByPid(Integer p_id);

	void delLedProgramByPid(Integer p_id);

	LedProgram findProgramByUserId(int userId);

	void deleteProgramLedLink(@Param("p_id")Integer p_id);

	void addProgramLedLink(@Param("p_id")Integer p_id, @Param("houseId")Integer houseId);




}
