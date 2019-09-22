package com.lyyh.greenhouse.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.vo.ClimaticVo;
import com.lyyh.greenhouse.service.ClimaticService;

@Service
@Transactional
public class ClimaticServiceImpl implements ClimaticService {

	@Autowired
	private ClimaticDao climaticDao;

	
	
	@Override
	public Climatic getNewest(Integer zoneId) {
		// TODO Auto-generated method stub
		return climaticDao.getNewest(zoneId);
	}

	/*
	 * 温度
	 */
	@Override
	public List<Object[]> getTemperatureList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getTemperatureList(climaticVo);
		List<Object[]> temperatures = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			temperatures.add(new Object[]{climatic.getDatetime(),climatic.getTemperature()});
		}
		return temperatures;
	}

	@Override
	public List<Object[]> getHumidityList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getHumidityList(climaticVo);
		List<Object[]> humiditys = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			humiditys.add(new Object[]{climatic.getDatetime(),climatic.getHumidity()});
		}
		return humiditys;
	}

	@Override
	public List<Object[]> getLightingList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getLightingList(climaticVo);
		List<Object[]> lightings = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			lightings.add(new Object[]{climatic.getDatetime(),climatic.getLighting()});
		}
		return lightings;
	}

	@Override
	public List<Object[]> getPressureList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getPressureList(climaticVo);
		List<Object[]> pressures = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			pressures.add(new Object[]{climatic.getDatetime(),climatic.getPressure()});
		}
		return pressures;
	}

	@Override
	public List<Object[]> getWindSpeedList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getWindSpeedList(climaticVo);
		List<Object[]> windSpeeds = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			windSpeeds.add(new Object[]{climatic.getDatetime(),climatic.getWindSpeed()});
		}
		return windSpeeds;
	}

	@Override
	public List<Object[]> getRainFallList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getRainFallList(climaticVo);
		List<Object[]> rainFalls = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			rainFalls.add(new Object[]{climatic.getDatetime(),climatic.getRainFall()});
		}
		return rainFalls;
	}

	@Override
	public List<Object[]> getPHList(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getPHList(climaticVo);
		List<Object[]> phs = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			phs.add(new Object[]{climatic.getDatetime(),climatic.getPh()});
		}
		return phs;
	}

	@Override
	public List<Object[]> getPM25List(ClimaticVo climaticVo) {
		List<Climatic> climatics = climaticDao.getPM25List(climaticVo);
		List<Object[]> pm25s = new ArrayList<Object[]>();
		for (Climatic climatic : climatics) {
			pm25s.add(new Object[]{climatic.getDatetime(),climatic.getPm25()});
		}
		return pm25s;
	}

	@Override
	public List<Climatic> getClimaticData(Integer zoneId, Date start, Date end) {
		
		return climaticDao.getClimaticData(zoneId,start,end);
	}
	
	

}
