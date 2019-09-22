package com.lyyh.greenhouse.autoRun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.service.LedService;

public class SendLedStart implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private LedService ledService;

	private Integer zoneId;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			String osName = System.getProperty("os.name");
			System.out.println("系统版本: " + osName);
//			System.out.println(osName.toLowerCase().contains("Windows".toLowerCase()));
			if(osName.toLowerCase().contains("Windows".toLowerCase())){
				List<LedProgram> programList = ledService.listAll(zoneId);
				if (!CollectionUtils.isEmpty(programList)) {
					for (LedProgram ledProgram : programList) {
						try {
							String msg = ledService.showLedLinkHouse(ledProgram.getP_id());
							System.out.println(msg);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
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
