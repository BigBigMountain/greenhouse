package com.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyyh.greenhouse.dao.HouseDao;
import com.lyyh.greenhouse.pojo.House;

public class HouseDaoTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HouseDao houseDao = context.getBean(HouseDao.class);
//		List<House> houses = houseDao.findAllByZoneName("济南安信种苗");
//		System.out.println(houses.size());
	}

}
