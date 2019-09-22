package com.lyyh.fertilizer.threadTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyyh.fertilizer.dao.TvalveDao;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;
import com.lyyh.greenhouse.util.tzgk.ValveMap;
import com.lyyh.tzgk.pojo.Tequipment;
import com.lyyh.tzgk.pojo.Tvalve;

public class InitValve implements Runnable {

	String dtuCode;
	
	TvalveDao tvalveDao;
	
	public InitValve() {
		super();
	}

	public InitValve(String dtuCode,TvalveDao tvalveDao) {
		super();
		this.dtuCode = dtuCode;
		this.tvalveDao = tvalveDao;
	}

	@Override
	public void run() {
		List<Tequipment> tequipments = TzgkUtils.getAllTequipment();
		Map<String, String> map = new HashMap<String, String>();
		// 遍历List,封装到Map<Emac,msg>
		for (Tequipment equipment : tequipments) {
			map.put(equipment.getEmac(), equipment.getEqmsg());
		}
		// tzgk的阀的状态
		Map<Integer, Tvalve> tvalvemap = getValves(dtuCode);
		Collection<Tvalve> tvalves = tvalvemap.values();
//		Integer tvalveStates[] = new Integer[tvalves.size()];
		for (Tvalve tvalve : tvalves) {
			String eqmsg = map.get(tvalve.getEmac());
			if (eqmsg == "null" || eqmsg == null) {
				TzgkUtils.sendCommand(tvalve, 0);
			} else {
				String[] split = eqmsg.split(",");
				if(!split[tvalve.getPosition()-1].substring(2).equals("00")){
					TzgkUtils.sendCommand(tvalve, 0);
				}
			}
		}
	}

	public Map<Integer, Tvalve> getValves(String dtuCode) {
		Map<Integer, Tvalve> tvalves = ValveMap.getTvalves(dtuCode);
		if (tvalves == null || tvalves.size() == 0) {
			List<Tvalve> tvalveList = tvalveDao.queryTvalvesByDtuCode(dtuCode);
			tvalves = new HashMap<Integer, Tvalve>();
			for (Tvalve tvalve : tvalveList) {
				tvalves.put(tvalve.getNumber(), tvalve);
			}
			ValveMap.putTvalves(dtuCode, tvalves);
		}
		return tvalves;
	}

	public String getDtuCode() {
		return dtuCode;
	}

	public void setDtuCode(String dtuCode) {
		this.dtuCode = dtuCode;
	}

	public TvalveDao getTvalveDao() {
		return tvalveDao;
	}

	public void setTvalveDao(TvalveDao tvalveDao) {
		this.tvalveDao = tvalveDao;
	}
	
	
}
