package com.lyyh.greenhouse.service;

import org.springframework.ui.Model;

import com.lyyh.greenhouse.pojo.Equipment;

public interface EquipmentService {

	Model equipmentList(Model model, Integer zoneId, Integer houseId);

	void saveOrUpdateEquipment(Equipment equipment);

	void delEquipmentById(Integer equipmentId);

}
