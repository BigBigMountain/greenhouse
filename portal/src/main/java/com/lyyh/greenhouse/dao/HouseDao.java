package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.House;


public interface HouseDao {

	List<House> findAllByZoneId(@Param("zoneId")Integer zoneId);

	String getHouseName(@Param("id")Integer id);

	House selectById(@Param("id")Integer id);

	List<House> findAllByLedProgram(@Param("programId")Integer programId);
}
