package com.lyyh.greenhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.GhController;

public interface ControllerSettingDao {

	List<GhController> queryControllersByZoneId(@Param("zoneId")Integer zoneId);

	List<GhController> queryHouseLeftJoinControllerByZoneId(@Param("zoneId")Integer zoneId);

	void saveController(GhController controller);

	void updateController(GhController controller);

	GhController queryControllerById(Integer id);

}
