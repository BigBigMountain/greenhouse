package com.lyyh.greenhouse.quartzJob;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.service.LedService;

public class SendLedJob {

	@Autowired
	private LedService ledService;
	
	private Integer zoneId;
	
	public void startLed(){
		List<LedProgram> programList = ledService.findProgramByZoneid(zoneId);
		sendLedList(zoneId,programList);
	}
	
	
	public void sendLedList(Integer zoneId, List<LedProgram> programList){
		if(zoneId != null && !CollectionUtils.isEmpty(programList)){
			for (LedProgram program : programList) {
				ledService.createImageAndSendToLedLinkHouse(zoneId, program.getP_id());
			}
		}
	}


	public Integer getZoneId() {
		return zoneId;
	}


	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	
	
	
}
