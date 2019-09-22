package com.lyyh.greenhouse.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.lyyh.greenhouse.pojo.GhController;

public interface ControllerSettingService {


	List<GhController> queryControllersByZoneId(Integer zoneId);

	GhController queryControllerById(Integer id);

	List<GhController> queryHouseLeftJoinControllerByZoneId(Integer zoneId);

	String saveController(GhController controller);

	String updateController(GhController controller);

	List<Integer> readFromController(Integer controllerId,byte read[],byte type[]) throws Exception;

	void writeToController(Integer controllerId, int[] Values,byte[] type, byte[] writehead) throws UnknownHostException, IOException;
}
