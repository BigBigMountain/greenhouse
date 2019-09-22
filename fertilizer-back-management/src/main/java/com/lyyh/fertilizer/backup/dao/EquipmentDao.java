package com.lyyh.fertilizer.backup.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyyh.tzgk.pojo.TgateWay;
import com.lyyh.tzgk.pojo.Tvalve;

public interface EquipmentDao {

	List<TgateWay> getTGateWayByUserId(Integer userId);

	TgateWay getTGateWayById(@Param("id")Integer id);
	
	void addGateWay(TgateWay gateWay);

	void updateGateWay(TgateWay gateWay);

	void deleteGateWay(Integer gateWayId);

	
	
	
	List<Tvalve> getTvalveListByFertilizerId(Integer fertilizerId);

	void addValve(Tvalve tvalve);

	void updateValve(Tvalve tvalve);

	void deleteTvalve(Integer tvalveId);

	void updateGmacOfTvalve(@Param("oldMacCode")String oldMacCode, @Param("newMacCode")String newMacCode);

	void updateGmacOfTtempAndHumEquip(@Param("oldMacCode")String oldMacCode, @Param("newMacCode")String newMacCode);


}
