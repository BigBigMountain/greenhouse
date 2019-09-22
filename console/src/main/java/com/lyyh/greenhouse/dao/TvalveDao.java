package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.tzgk.pojo.Tvalve;

public interface TvalveDao {

	Tvalve queryTvalveByDtuCodeAndNumber(@Param("dtuCode")String dtuCode, @Param("number")int number);

	List<Tvalve> queryTvalvesByDtuCode(String dtuCode);
	
}
