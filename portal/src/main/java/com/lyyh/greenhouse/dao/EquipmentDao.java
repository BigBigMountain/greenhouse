package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.pojo.vo.EquipmentVo;

public interface EquipmentDao {

	List<Equipment> findAllByVo(EquipmentVo equipmentVo);

	void changeSatate(EquipmentVo equipmentVo);

}
