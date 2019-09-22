package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.lyyh.greenhouse.dao.EquipmentDao;
import com.lyyh.greenhouse.dao.HouseDao;
import com.lyyh.greenhouse.dao.ZoneDao;
import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.Zone;
import com.lyyh.greenhouse.service.EquipmentService;
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private ZoneDao zoneDao;
	@Autowired
	private HouseDao houseDao;
	
	@Override
	public Model equipmentList(Model model, Integer zoneId, Integer houseId) {
		List<Zone> zoneList = zoneDao.queryAll();
		model.addAttribute("zoneList",zoneList);
		
		List<House> houseList;
		
		if(zoneId == null || zoneId == 0){
			if(houseId ==null || houseId==0){
				zoneId=zoneList.get(0).getZoneId();
			}else{
				zoneId=houseDao.queryById(houseId).getZoneId();
			}
		}
		model.addAttribute("zoneId",zoneId);
		houseList = houseDao.findAllByZoneId(zoneId);
		model.addAttribute("houseList",houseList);
		if(houseId == null || houseId == 0){//TODO
			if(houseList == null || houseList.size()==0){
				houseId=null;
				model.addAttribute("msg","该区域没有温室,请先添加温室");
				return model;
			}else{
				houseId = houseList.get(0).getId();
			}
		}
		List<Equipment> equipmentList = equipmentDao.queryAllByHouseId(houseId);
		model.addAttribute("equipmentList",equipmentList);
		
		model.addAttribute("houseId",houseId);
		return model;
	}

	@Override
	public void saveOrUpdateEquipment(Equipment equipment) {
		if(equipment==null){
			return;
		}
		if(equipment.getId()==null||equipment.getId()==0){
			equipmentDao.saveEquipment(equipment);
		}else{
			equipmentDao.updateEquipment(equipment);
		}
		
	}

	@Override
	public void delEquipmentById(Integer equipmentId) {
		equipmentDao.delEquipmentById(equipmentId);
		
	}

}
