package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.fertilizer.pojo.Fertilizer;

public interface FertilizerDao {

	List<Fertilizer> queryAll();

	List<Fertilizer> queryAllByUid(int userId);

	List<Fertilizer> queryAllByUsername(String username);

	Fertilizer selectByDtuCode(String dtuCode);

	Fertilizer selectByFertilizerId(int fertilizerId);

	void addFertilizer(Fertilizer fertilizer);

	void deleteByFerId(int fertilizerId);

	void updateFertilizer(Fertilizer fertilizer);
	

}
