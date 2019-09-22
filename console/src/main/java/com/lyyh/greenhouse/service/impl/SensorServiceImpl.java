package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.SensorDao;
import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.NodeSetting;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.vo.SensorVo;
import com.lyyh.greenhouse.service.SensorService;
@Service
public class SensorServiceImpl implements SensorService {

	@Autowired
	SensorDao sensorDao; 
	@Override
	@Transactional
	public int insert(Sensor sensor) {
		return sensorDao.insert(sensor);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		sensorDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Sensor sensor) {
		sensorDao.update(sensor);
	}

	@Override
	public Sensor selectById(Integer id) {
		return sensorDao.selectById(id);
	}

	@Override
	public List<Sensor> selectAll() {
		return sensorDao.selectAll();
	}

	@Override
	public List<Sensor> selectByHouseId(Integer houseId) {
		return sensorDao.selectByHouseId(houseId);
	}

	@Override
	public List<SensorType> selectTypeList() {
		return sensorDao.selectTypeList();
	}

	@Override
	public void insertType(SensorType type) {
		sensorDao.insertType(type);
	}

	@Override
	public void updateType(SensorType type) {
		sensorDao.updateType(type);
		
	}

	@Override
	public void deleteType(Integer id) {
		sensorDao.deleteType(id);
	}

	@Override
	public List<SensorVo> selectVoByHouseId(Integer houseId) {
		return sensorDao.selectVoByHouseId(houseId);
	}

	@Override
	public ClimaticSetting queryClimaticSetting(Integer zoneId) {
		
		return sensorDao.queryClimaticSetting(zoneId);
	}
	
	@Override
	public List<ClimaticDataType> queryClimaticDataTypes(Integer zoneId) {
	
		List<ClimaticDataType> climaticDataTypes = sensorDao.queryAllClimaticDataType();
		
		return climaticDataTypes;
	}

	@Override
	public void deleteClimaticNodeById(Integer id) {
		sensorDao.deleteClimaticNodeById(id);
	}
	
	@Override
	public String saveOrUpdateClimaticSetting(ClimaticSetting climaticSetting) {
		String msg = null;
		if (climaticSetting.getIp() != null) {
			if (climaticSetting.getPort() != 0) {
				if (climaticSetting.getNodes() == null || climaticSetting.getNodes().size() == 0) {
					msg = "节点不能为空";
				} else {
					if (null == climaticSetting.getId() || climaticSetting.getId()==0) {
						sensorDao.insertClimaticSetting(climaticSetting);
						List<NodeSetting> nodes = climaticSetting.getNodes();
						for (NodeSetting node : nodes) {
							node.setCsId(climaticSetting.getId());
						}
						sensorDao.insertNodeSettings(nodes);
					} else {
						sensorDao.updateClimaticSetting(climaticSetting);
						for (NodeSetting node : climaticSetting.getNodes()) {
							if(node.getId()==null ||node.getId() == 0){
								node.setCsId(climaticSetting.getId());
								sensorDao.insertNodeSetting(node);
							}else{
								sensorDao.updateNodeSetting(node);
							}
						}
					}
					
				}
			} else {
				msg = "端口号不能为0";
			}
		} else {
			msg = "ip不正确";
		}
		
		
		return msg;
	}
}
