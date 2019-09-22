package com.lyyh.greenhouse.service;

import com.lyyh.greenhouse.pojo.Screem8;
import com.lyyh.greenhouse.util.serial.serialException.NoSuchPort;
import com.lyyh.greenhouse.util.serial.serialException.NotASerialPort;
import com.lyyh.greenhouse.util.serial.serialException.PortInUse;
import com.lyyh.greenhouse.util.serial.serialException.SerialPortParameterFailure;

public interface Screem8Service {

	Screem8 getScreem8(Integer zoneId);

	void saveOrUpdateScreem8Setting(Screem8 screem8) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse ;

}
