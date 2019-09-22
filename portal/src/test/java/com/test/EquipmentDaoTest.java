package com.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyyh.greenhouse.dao.EquipmentDao;
import com.lyyh.greenhouse.dao.HouseDao;
import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.vo.EquipmentVo;

public class EquipmentDaoTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EquipmentDao equipmentDao = context.getBean(EquipmentDao.class);
		EquipmentVo vo = new EquipmentVo();
		vo.setHouseId(1);
		vo.setZoneName("济南安信种苗");
		List<Equipment> equipments = equipmentDao.findAllByVo(vo);
		System.out.println(equipments.size());
	}

}
