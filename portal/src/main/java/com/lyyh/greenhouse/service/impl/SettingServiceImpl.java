package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.SettingDao;
import com.lyyh.greenhouse.pojo.ClimaticDataType;
import com.lyyh.greenhouse.pojo.ClimaticSetting;
import com.lyyh.greenhouse.pojo.NodeSetting;
import com.lyyh.greenhouse.pojo.HouseSetting;
import com.lyyh.greenhouse.service.SettingService;

@Service
@Transactional
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private SettingDao settingDao;

	@Override
	public String saveOrUpdateHouseSetting(HouseSetting houseSetting) {
		String msg = null;
		if (houseSetting.getIp() != null) {
			if (houseSetting.getPort() != 0) {
				if (StringUtils.isNotBlank(houseSetting.getNode())) {
					if (null == houseSetting.getId()) {
						settingDao.insertHouseSetting(houseSetting);
					} else {
						settingDao.updateHouseSetting(houseSetting);
					}
				} else {
					msg = "节点不能为空";
				}
			} else {
				msg = "端口号不能为0";
			}
		} else {
			msg = "ip不正确";
		}
		return msg;
		
	}

	@Override
	public void setOffHouseSetting(int id) {
		settingDao.setOffhouseSetting(id);
	}

	@Override
	public ClimaticSetting queryClimaticSetting(Integer zoneId) {
		
		return settingDao.queryClimaticSetting(zoneId);
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
						settingDao.insertClimaticSetting(climaticSetting);
						List<NodeSetting> nodes = climaticSetting.getNodes();
						for (NodeSetting node : nodes) {
							node.setCsId(climaticSetting.getId());
						}
						settingDao.insertNodeSettings(nodes);
					} else {
						settingDao.updateClimaticSetting(climaticSetting);
						for (NodeSetting node : climaticSetting.getNodes()) {
							if(node.getId()==null ||node.getId() == 0){
								node.setCsId(climaticSetting.getId());
								settingDao.insertNodeSetting(node);
							}else{
								settingDao.updateNodeSetting(node);
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

	@Override
	public List<HouseSetting> queryAllHouseSettings(Integer zoneId) {
		List<HouseSetting> houseSettings = settingDao.queryAllHouseSettings(zoneId);
		return houseSettings;
	}

	@Override
	public List<ClimaticDataType> queryClimaticDataTypes(Integer zoneId) {
	
		List<ClimaticDataType> climaticDataTypes = settingDao.queryAllClimaticDataType();
		
		return climaticDataTypes;
	}

}
