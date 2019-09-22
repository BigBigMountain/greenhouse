package com.lyyh.greenhouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyh.greenhouse.dao.NodeConfigDao;
import com.lyyh.greenhouse.pojo.NodeConfig;
import com.lyyh.greenhouse.service.NodeConfigService;

@Service
public class NodeConfigServiceImpl implements NodeConfigService {

	@Autowired
	private NodeConfigDao nodeConfigDao;
	
	@Override
	public void replace(NodeConfig config) {
		nodeConfigDao.replace(config);
	}

}
