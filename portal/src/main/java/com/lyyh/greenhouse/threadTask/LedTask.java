package com.lyyh.greenhouse.threadTask;

import com.lyyh.greenhouse.service.LedService;

public class LedTask  implements Runnable{
	
	private LedService ledService;
	private int sleepTime;
	private Integer zoneId;
	private Integer programId;
	
	
	
	public LedTask(LedService ledService,int sleepTime,Integer zoneId,Integer programId) {
		super();
		this.ledService = ledService;
		this.sleepTime = sleepTime;
		this.zoneId = zoneId;
		this.programId = programId;
	}
	@Override
	public void run() {
		while(true){
			System.out.println("=======开始创建图片并发送plc======");
			
			ledService.createImageAndSendToLed(zoneId, programId);
			
			System.out.println("=======发送完成======");
//			List<House> houses,Climatic newestClimatic,LedProgram ledProgram,LedTable ledTable,LedHouse ledHouse,LedClimatic ledClimatic
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
