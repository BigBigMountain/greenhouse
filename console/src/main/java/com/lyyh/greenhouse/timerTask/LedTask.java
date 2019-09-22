package com.lyyh.greenhouse.timerTask;

import java.util.List;


import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.dao.HouseDataDao;
import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.LedClimatic;
import com.lyyh.greenhouse.pojo.LedHouse;
import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.pojo.LedTable;
import com.lyyh.greenhouse.service.LedService;

public class LedTask  implements Runnable{
	private HouseDataDao houseDataDao;
	private ClimaticDao climaticDao;
	private LedService ledService;
	
	private LedProgram ledProgram=null;
	private LedTable ledTable = null;
	private LedHouse ledHouse = null;
	private LedClimatic ledClimatic = null;
	
	
	
	
	public LedTask(HouseDataDao houseDataDao, ClimaticDao climaticDao, LedService ledService, LedProgram ledProgram,
			LedTable ledTable, LedHouse ledHouse, LedClimatic ledClimatic, String zoneName) {
		super();
		this.houseDataDao = houseDataDao;
		this.climaticDao = climaticDao;
		this.ledService = ledService;
		this.ledProgram = ledProgram;
		this.ledTable = ledTable;
		this.ledHouse = ledHouse;
		this.ledClimatic = ledClimatic;
		this.zoneName = zoneName;
	}
	String zoneName;
	@Override
	public void run() {
		while(true){
//			System.out.println(Thread.currentThread().getName()+"正在执行");

			List<HouseData> houseDatas = houseDataDao.getAllNewestByZoneName(zoneName);
			Climatic newestClimatic = climaticDao.getNewest(zoneName);
			ledService.createImageAndSendToLed(houseDatas, newestClimatic, ledProgram, ledTable, ledHouse,ledClimatic);
			int s = houseDatas.size();
			int c = ledTable.getT_c();
			int n = s%c==0?s/c:(s/c+1);
			int time = n * ledTable.getT_t() * 1000;
//			System.out.println(time/1000);

			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public HouseDataDao getHouseDataDao() {
		return houseDataDao;
	}
	public void setHouseDataDao(HouseDataDao houseDataDao) {
		this.houseDataDao = houseDataDao;
	}
	public ClimaticDao getClimaticDao() {
		return climaticDao;
	}
	public void setClimaticDao(ClimaticDao climaticDao) {
		this.climaticDao = climaticDao;
	}
	public LedService getLedService() {
		return ledService;
	}
	public void setLedService(LedService ledService) {
		this.ledService = ledService;
	}
	public LedProgram getLedProgram() {
		return ledProgram;
	}
	public void setLedProgram(LedProgram ledProgram) {
		this.ledProgram = ledProgram;
	}
	public LedTable getLedTable() {
		return ledTable;
	}
	public void setLedTable(LedTable ledTable) {
		this.ledTable = ledTable;
	}
	public LedHouse getLedHouse() {
		return ledHouse;
	}
	public void setLedHouse(LedHouse ledHouse) {
		this.ledHouse = ledHouse;
	}
	public LedClimatic getLedClimatic() {
		return ledClimatic;
	}
	public void setLedClimatic(LedClimatic ledClimatic) {
		this.ledClimatic = ledClimatic;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	
//TODO
	
}
