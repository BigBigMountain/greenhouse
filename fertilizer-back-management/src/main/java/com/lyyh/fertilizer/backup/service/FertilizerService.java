package com.lyyh.fertilizer.backup.service;

import java.util.List;

import com.lyyh.fertilizer.pojo.Fertilizer;

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

}
