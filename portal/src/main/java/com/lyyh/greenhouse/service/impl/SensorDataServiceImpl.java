package com.lyyh.greenhouse.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lyyh.greenhouse.dao.SensorDao;
import com.lyyh.greenhouse.dao.SensorDataDao;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.vo.SensorDataVo;
import com.lyyh.greenhouse.service.SensorDataService;
import com.lyyh.greenhouse.util.cache.CacheManager;
@Service
public class SensorDataServiceImpl implements SensorDataService{

	
	@Autowired
	SensorDao sensorDao;
	
	@Autowired
	SensorDataDao sensorDataDao;
	
	
	
	@Override
	public List<SensorData> selectNewestDataByHouseId(Integer houseId) {
		return sensorDataDao.selectNewestDataByHouseId(houseId);
	}

	@Override
	public List<SensorData> selectByVo(SensorDataVo vo) {
		return sensorDataDao.selectByVo(vo);
	}

	@Override
	public List<List<SensorData>> getTodayChartdata(Integer houseId) {
		List<Sensor> sensorList = sensorDao.selectByHouseId(houseId);
		List<List<SensorData>> dataList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(sensorList)){
			for (Sensor sensor : sensorList) {
				List<SensorData> sensorDataList = sensorDataDao.getTodayDataBySensorId(sensor.getId());
				dataList.add(sensorDataList);
			}
		}
		return dataList;
	}

	@Override
	public Map<String, List> changeCharts(Integer sensorId, Date start, Date end) {
		List<Object> datas = new ArrayList<>();
		List<Date> times = new ArrayList<>();
		Map<String, List> map = new HashMap<String, List>();
		List<SensorData> dataList;
		if(start != null && end != null){
			SensorDataVo vo = new SensorDataVo();
			vo.setSensorId(sensorId);
			vo.setStart(start);
			vo.setEnd(end);
			dataList = sensorDataDao.selectByVo(vo);
		}else{
			dataList = sensorDataDao.getTodayDataBySensorId(sensorId);
		}
		if(!CollectionUtils.isEmpty(dataList)){
			for (SensorData data : dataList) {
				datas.add(data.getValue());
				times.add(data.getDateTime());
			}
		}
		map.put("values", datas);
		map.put("time", times);
		return map;
	}

	@Override
	public List<Object[]> getSensorDataList(Integer sensorId, Date start, Date end) {
		List<SensorData> dataList = sensorDataDao.getSensorDataList(sensorId,start,end);
		List<Object[]> list = new ArrayList<>();
		if(!CollectionUtils.isEmpty(dataList)){
			for (SensorData sensorData : dataList) {
				list.add(new Object[]{sensorData.getDateTime(),sensorData.getValue()});
			}
		}
		return list;
	}

	@Override
	public List<SensorData> selectSensorData(Integer houseId, Date start, Date end) {
		if(end != null){
			end = new Date(end.getTime()+1000*3600*24);
		}
		SensorDataVo vo = new SensorDataVo();
		vo.setHouseId(houseId);
		vo.setStart(start);
		vo.setEnd(end);
		vo.setOrder("order by datetime ,sensor_type_id,sensor_id");
		return sensorDataDao.selectWithConditionByVo(vo);
	}

	@Override
	public List<SensorData> selectSensorDataByOrderByExcelSort(Integer houseId, Date start, Date end) {
		return sensorDataDao.selectSensorDataByOrderByExcelSort(houseId,start,end);
	}

	@Override
	public List translateToTableVo(List<SensorData> dataList, int rowSize) {
		ArrayList tableData = new ArrayList();
		SensorData temp = null;
		if(!CollectionUtils.isEmpty(dataList)){
			for (SensorData data : dataList) {
				if(temp == null || !data.getDateTime().equals(temp.getDateTime())){
					Object[] row = new Object[rowSize+1];
					row[0] = DateFormatUtils.format(data.getDateTime(), "yyyy-MM-dd HH:mm");
					tableData.add(row);
				}
				Object[] row = (Object[])tableData.get(tableData.size() - 1);
				row[data.getExcelSort()] = data.getValue();
				temp = data;
			}
		}

		return tableData;
	}

	@Override
	public List<SensorData> selectNewestDataFromCacheByHouseId(Integer houseId) {
		NumberFormat nf = new DecimalFormat( "0.000");
		List<SensorData> list = new ArrayList<SensorData>();
		Hashtable<Integer, SensorData> cache = CacheManager.newestSensorData.getCache();
		Set<Entry<Integer, SensorData>> set = cache.entrySet();
		for (Entry<Integer, SensorData> entry : set) {
			SensorData data = entry.getValue();
			if(houseId == data.getHouseId()){
				if(data.getSensorTypeId() == 7){
					data.setValue(Double.valueOf(nf.format(data.getValue())));
				}
				list.add(data);
			}
		}
		return list;
	}

}
