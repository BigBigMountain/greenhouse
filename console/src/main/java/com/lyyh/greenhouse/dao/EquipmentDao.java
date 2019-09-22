package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.Equipment;

public interface EquipmentDao {

	List<Equipment> queryAllByHouseId(int houseId);

	void saveEquipment(Equipment equipment);

	void updateEquipment(Equipment equipment);

	void delEquipmentById(int equipmentId);

}
