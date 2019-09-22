package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.EquipmentDao;
import com.lyyh.greenhouse.pojo.Equipment;
import com.lyyh.greenhouse.pojo.vo.EquipmentVo;
import com.lyyh.greenhouse.service.EquipmentService;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	private EquipmentDao equipmentDao;

	@Override
	public List<Equipment> findAllByVo(EquipmentVo equipmentVo) {
		
		if(null != equipmentVo && null != equipmentVo.getHouseId() && null != equipmentVo.getZoneName()){
			List<Equipment> equipments = equipmentDao.findAllByVo(equipmentVo);
			return equipments;
		}
		return null;
	}

	@Override
	public void changeState(EquipmentVo equipmentVo) {
		equipmentDao.changeSatate(equipmentVo);
		
	}

}
