package com.lyyh.greenhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.HouseDataDao;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.HouseData_highcharts;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;
import com.lyyh.greenhouse.service.HouseDataService;
@Service
@Transactional
public class HouseDataServiceImpl implements HouseDataService {

	@Autowired
	private HouseDataDao houseDataDao;
	@Override
	public HouseData getNewest(HouseDataVo houseDataVo) {
		
		return houseDataDao.getNewest(houseDataVo);
	}

	

	@Override
	public HouseData_highcharts getIndexList(HouseDataVo houseDataVo) {
		List<HouseData> houseDataList = houseDataDao.getHouseDataList(houseDataVo);
		HouseData_highcharts houseData_highcharts = new HouseData_highcharts();
		if(null != houseDataList){
			for (HouseData houseData : houseDataList) {
				houseData_highcharts.getTemperatures().add(houseData.getTemperature());
//				houseData_highcharts.getTemperatures2().add(houseData.getTemperature2());
				houseData_highcharts.getHumiditys().add(houseData.getHumidity());
//				houseData_highcharts.getHumiditys2().add(houseData.getHumidity2());
				houseData_highcharts.getLightings().add(houseData.getLighting());
				houseData_highcharts.getCo2s().add(houseData.getCo2());
//				houseData_highcharts.getSoilTemperatures().add(houseData.getSoilTemperature());
//				houseData_highcharts.getSoilHumiditys().add(houseData.getSoilHumidity());
				houseData_highcharts.getTimeSpan().add(houseData.getTimeSpan());
			}
		}
		return houseData_highcharts;
	}

	@Override
	public List<HouseData> downloadData(HouseDataVo houseDataVo) {
		List<HouseData> houseDataList = houseDataDao.getHouseDataList(houseDataVo);
		return houseDataList;
	}

}
