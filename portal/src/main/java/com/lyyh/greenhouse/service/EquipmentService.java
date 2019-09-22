package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.vo.EquipmentVo;

public interface EquipmentService {


	List<Equipment> findAllByVo(EquipmentVo equipmentVo);

	void changeState(EquipmentVo equipmentVo);


	

}
