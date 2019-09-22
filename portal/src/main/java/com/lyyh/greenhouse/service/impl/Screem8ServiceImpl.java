package com.lyyh.greenhouse.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.Screem8Dao;
import com.lyyh.greenhouse.pojo.Screem8;
import com.lyyh.greenhouse.service.Screem8Service;
import com.lyyh.greenhouse.util.constant.Screem8Constant;
import com.lyyh.greenhouse.util.serial.SerialTool;
import com.lyyh.greenhouse.util.serial.SerialUtil;
import com.lyyh.greenhouse.util.serial.serialException.NoSuchPort;
import com.lyyh.greenhouse.util.serial.serialException.NotASerialPort;
import com.lyyh.greenhouse.util.serial.serialException.PortInUse;
import com.lyyh.greenhouse.util.serial.serialException.SerialPortParameterFailure;

import gnu.io.SerialPort;

@Service
@Transactional
public class Screem8ServiceImpl implements Screem8Service {

	@Autowired
	private Screem8Dao screem8Dao;

	@Override
	public Screem8 getScreem8(Integer zoneId) {
		Screem8 screem8 = Screem8Constant.getSetting(zoneId);
		if (null == screem8) {
			screem8 = screem8Dao.selectByZoneId(zoneId);
		}
		if (null != screem8) {
			ArrayList<String> portNames = SerialTool.findPort();

			screem8.setPortNames(portNames);
		}

		return screem8;
	}

	@Override
	public synchronized void saveOrUpdateScreem8Setting(Screem8 screem8)
			throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse {
		SerialUtil.closeCurrentPort(screem8.getZoneId());
		SerialPort openPort = SerialUtil.openPort(screem8);
		Screem8Constant.setCurrentPort(screem8.getZoneId(), openPort);
		if (null == screem8.getId()) {
			screem8Dao.saveScreem8Setting(screem8);
		} else {
			screem8Dao.updateScreem8Setting(screem8);
		}

	}

}
