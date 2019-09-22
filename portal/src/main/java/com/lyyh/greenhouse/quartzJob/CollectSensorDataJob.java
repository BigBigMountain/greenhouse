package com.lyyh.greenhouse.quartzJob;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyyh.greenhouse.service.CollectionDataService;

public class CollectSensorDataJob {

	@Autowired
	private CollectionDataService dataCollectionService;
	
	private Integer zoneId;
	
	public void collectSensorData(){
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().contains("Windows".toLowerCase())){
			try {
				dataCollectionService.collectData(zoneId);
				System.out.println("----------采集数据成功----------" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			} catch (Exception e) {
				System.out.println(">>>>>>>>>>>>采集数据失败<<<<<<<<<<<<" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				e.printStackTrace();
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
