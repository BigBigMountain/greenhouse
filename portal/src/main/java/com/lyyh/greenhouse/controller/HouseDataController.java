package com.lyyh.greenhouse.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lyyh.fertilizer.pojo.ChartsPoint;
import com.lyyh.greenhouse.dao.SensorDataDao;
import com.lyyh.greenhouse.dao.WaterSensorDataDao;
import com.lyyh.greenhouse.pojo.Camera_GH;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.HouseData_highcharts;
import com.lyyh.greenhouse.pojo.Nvr;
import com.lyyh.greenhouse.pojo.Sensor;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.SensorType;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;
import com.lyyh.greenhouse.pojo.vo.SensorDataVo;
import com.lyyh.greenhouse.service.CameraService;
import com.lyyh.greenhouse.service.HouseDataService;
import com.lyyh.greenhouse.service.HouseService;
import com.lyyh.greenhouse.service.SensorDataService;
import com.lyyh.greenhouse.service.SensorService;
import com.lyyh.greenhouse.service.WaterSensorDataService;
import com.lyyh.greenhouse.util.FileUtils;

@Controller
@RequestMapping(value="/houseData")
public class HouseDataController {
	
	@Autowired
	private SensorService sensorService;
	
	@Autowired
	private SensorDataService sensorDataService;
	
	@Autowired
	private HouseDataService houseDataService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private CameraService cameraService;
	
	@Autowired
	private WaterSensorDataService waterSensorDataService;
	
//	public HouseDataVo getHouseDataVo(HttpSession session,Integer houseId){
//		User user = (User) session.getAttribute("loginUser");
//		String zoneName = user.getZoneName();
//		
//		if(houseId==null){
//			houseId=1;
//		}
//		HouseDataVo houseDataVo = new HouseDataVo(houseId, zoneName);
//		return houseDataVo;
//	}
	
	/*
	 * 封装查询条件HouseDataVo
	 */
	public HouseDataVo getHouseDataVo(HttpSession session,Integer houseId,Date start,Date end){
		User user = (User) session.getAttribute("loginUser");
		String zoneName = user.getZoneName();
		
		if(null == houseId){
			houseId=1;
		}
		HouseDataVo houseDataVo = new HouseDataVo(houseId, zoneName,start,end);
		return houseDataVo;
	}

	
	/*
	 * getIndexList.do
	 * 温度,湿度,光照,co2,混合表
	 */
//	@RequestMapping(value="/getIndexList.do")
//	public @ResponseBody HouseData_highcharts getIndexList(HttpSession session,Integer houseId,Date start,Date end){
//		HouseDataVo houseDataVo = getHouseDataVo(session,houseId,start,end);
//		HouseData_highcharts houseData_highcharts = houseDataService.getIndexList(houseDataVo);
//		
//		return houseData_highcharts;
//	}
	
	/*
	 * 室内数据首页
	 * 单独访问摄像头视频
	 */
	@RequestMapping(value="/listAll2.do")
	public String listAll(Model model,HttpSession session,Integer houseId,Date start,Date end){
		housesToSession(session);
		
		HouseDataVo houseDataVo = getHouseDataVo(session,houseId,start,end);
		HouseData houseData =houseDataService.getNewest(houseDataVo);
		
		Camera_GH camera = cameraService.findCameraByHouse(houseDataVo);
		model.addAttribute("camera",camera);
		
	/*	User user = (User)session.getAttribute("loginUser");
		String zoneName = user.getZoneName();
		List<Nvr> nvrs = cameraService.findAllNvrByZoneName(zoneName);
		String nvrsjson = JSONArray.toJSONString(nvrs);
		model.addAttribute("nvrsjson",nvrsjson);
		model.addAttribute("nvrs",nvrs);
		
		*/
		model.addAttribute("houseData",houseData);
		model.addAttribute("houseId",houseDataVo.getHouseId());
		
		return "houseData/list";
//		return "houseData/list2";
	}
	
	/*
	 * 室内数据首页
	 * nvr的方式获取视频
	 */
	@RequestMapping(value="/listAllWithNvr.do")
	public String listAllWithNvr(Model model,HttpSession session,Integer houseId,Date start,Date end){
		
		List<House> houseList = housesToSession(session);	//获取所有温室列表\
		if(houseId == null){
			houseId = houseList.get(0).getId();
		}
		
//		HouseDataVo houseDataVo = getHouseDataVo(session,houseId,start,end);
//		HouseData houseData =houseDataService.getNewest(houseDataVo);
		List<Sensor> sensorList = sensorService.selectWithTypeByHouseIdOrderByPageSort(houseId);
		//获取温室数据
		List<SensorData> dataListInOneHouse = sensorDataService.selectNewestDataByHouseId(houseId);
		User user = (User) session.getAttribute("loginUser");
		String houseName = houseService.getHouseName(houseId);
		model.addAttribute("houseName", houseName);
		
//		Camera_GH camera = cameraService.findCameraByHouse(houseDataVo);
//		model.addAttribute("camera",camera);
		
//		String zoneName = user.getZoneName();
//		List<Nvr> nvrs = cameraService.findAllNvrByZoneName(zoneName);
//		String nvrsjson = JSONArray.toJSONString(nvrs);
//		model.addAttribute("nvrsjson",nvrsjson);
//		model.addAttribute("nvrs",nvrs);
		
		model.addAttribute("sensorList",sensorList);
		model.addAttribute("sensorDataList",dataListInOneHouse);
		model.addAttribute("houseId",houseId);
		
//		return "houseData/list_novideo";
		return "houseData/list_nvr";
//		return "houseData/list2";
	}
	
	@RequestMapping(value="/listAll.do")
	public String listAll2(Model model,HttpSession session,Integer houseId,Date start,Date end){
		
		List<House> houseList = housesToSession(session);	//获取所有温室列表\
		if(houseId == null){
			houseId = houseList.get(0).getId();
		}
		
		List<Sensor> sensorList = sensorService.selectWithTypeByHouseIdOrderByPageSort(houseId);
		//获取温室数据
		List<SensorData> dataListInOneHouse;
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().contains("Windows".toLowerCase())){
			dataListInOneHouse = sensorDataService.selectNewestDataFromCacheByHouseId(houseId);
		}else{
			dataListInOneHouse = sensorDataService.selectNewestDataByHouseId(houseId);
		}
		String houseName = houseService.getHouseName(houseId);
		
		model.addAttribute("osName",osName);
		model.addAttribute("houseName", houseName);
		model.addAttribute("sensorList",sensorList);
		model.addAttribute("sensorDataList",dataListInOneHouse);
		model.addAttribute("houseId",houseId);
		
		return "houseData/list_novideo";
	}
	
	/*
	 * 历史数据首页
	 * historyList.do
	 */
	@RequestMapping("/historyList.do")
	public String historyList(Model model,HttpSession session,Integer houseId,Date start,Date end){
		Calendar calendar = Calendar.getInstance();
		if(end == null){
			end = new Date();
		}
		if(start == null){
			calendar.add(Calendar.DATE, -30);
			start = calendar.getTime();
		}
		List<House> houseList = housesToSession(session);	//获取所有温室列表\
		if(houseId == null){
			houseId = houseList.get(0).getId();
		}
		//获取温室数据
		List<Sensor> sensorList = sensorService.selectWithTypeByHouseIdOrderByPageSort(houseId);
		
		model.addAttribute("sensorList",sensorList);
//		model.addAttribute("typeList",typeList);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		model.addAttribute("houseId",houseId);
		return "houseData/historyList";
	}
	
	@RequestMapping("/tableList.do")
	//TODO
	public String tableList(Model model,HttpSession session,Integer houseId,Date start,Date end){
		Calendar calendar = Calendar.getInstance();
		if(end == null){
			calendar.add(Calendar.DATE, 1);
			end = calendar.getTime();
		}else{
			end = new Date(end.getTime() + 1000*3600*24);
		}
		if(start == null){
			calendar.add(Calendar.DATE, -2);
			start = calendar.getTime();
		}
		List<House> houseList = housesToSession(session);	//获取所有温室列表\
		if(houseId == null){
			houseId = houseList.get(0).getId();
		}
		//获取温室数据
		List<Sensor> sensorList = sensorService.selectWithTypeByHouseIdOrderByExcelSort(houseId);

		List<SensorData> dataList = sensorDataService.selectSensorDataByOrderByExcelSort(houseId,start,end);
		List tableData = sensorDataService.translateToTableVo(dataList,sensorList.size());
		model.addAttribute("tableData",tableData);
		model.addAttribute("colSize",sensorList.size());
		model.addAttribute("sensorList",sensorList);
		model.addAttribute("start",start);
		model.addAttribute("end",new Date(end.getTime() - 1000*3600*24));
		model.addAttribute("houseId",houseId);
		return "houseData/tableList";
	}
	/*
	 * 获取温室列表  		??是否要提取到工具类??
	 */
	public List<House> housesToSession(HttpSession session){
		//session中获取
		List<House> houses = (List<House>) session.getAttribute("houses");
		//如果没有,先查后放
		if (null == houses) {
			User user = (User) session.getAttribute("loginUser");
			
			houses = houseService.findAllByZoneId(user.getZoneId());
			session.setAttribute("houses", houses);
		}
		return houses;
	}
	
	@RequestMapping("/changeCharts.do")
	public @ResponseBody Map<String, List>  changeCharts(Integer sensorId,Date start, Date end){
		return sensorDataService.changeCharts(sensorId,start,end);
	}
	
	/*	下载
	 * /houseData/downloadData.do
	 */
	@RequestMapping("/downloadData.do")
	public void downloadData(HttpServletRequest request,HttpServletResponse response,HttpSession session,Integer houseId,Date start,Date end) throws IOException{
		HouseDataVo houseDataVo = getHouseDataVo(session, houseId, start, end);
		List<HouseData> houseDataList = houseDataService.downloadData(houseDataVo);
		HSSFWorkbook excel = new HSSFWorkbook();
		HSSFSheet sheet = excel.createSheet("温室数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("温度1");
		headRow.createCell(1).setCellValue("温度2");
		headRow.createCell(2).setCellValue("湿度1");
		headRow.createCell(3).setCellValue("湿度2");
		headRow.createCell(4).setCellValue("光照");
		headRow.createCell(5).setCellValue("CO2");
		headRow.createCell(6).setCellValue("土壤温度");
		headRow.createCell(7).setCellValue("土壤湿度");
		if(houseDataList==null || houseDataList.size()==0){
			HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
			row.createCell(0).setCellValue("该温室暂无数据");
		}else{
			for (HouseData houseData : houseDataList) {
				HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
				row.createCell(0).setCellValue(houseData.getTemperature()==null ? "" : houseData.getTemperature()+"");
				row.createCell(1).setCellValue(houseData.getTemperature2()==null ? "" : houseData.getTemperature2()+"");
				row.createCell(2).setCellValue(houseData.getHumidity()==null ? "" : houseData.getHumidity()+"");
				row.createCell(3).setCellValue(houseData.getHumidity2()==null ? "" : houseData.getHumidity2()+"");
				row.createCell(4).setCellValue(houseData.getLighting()==null ? "" : houseData.getLighting()+"");
				row.createCell(5).setCellValue(houseData.getCo2()==null ? "" : houseData.getCo2()+"");
				row.createCell(6).setCellValue(houseData.getSoilTemperature()==null ? "" : houseData.getSoilTemperature()+"");
				row.createCell(7).setCellValue(houseData.getSoilHumidity()==null ? "" : houseData.getSoilHumidity()+"");
			}
			if(null == start){
				start=houseDataList.get(0).getTimeSpan();
			}
			if(null == end){
				end = houseDataList.get(houseDataList.size()-1).getTimeSpan();
			}
		}
		excel.close();
		response.setContentType("application/vnd.ms-excel");
		String filename=houseId+"号温室("+(start.getMonth()+1)+"月"+start.getDate()+"日-"+(end.getMonth()+1)+"月"+end.getDate()+"日).xls";
//		System.out.println(filename);
		//根据浏览器的不同,设置不同的文件名字符格式
		String agent = request.getHeader("User-Agent");
		String downloadName = FileUtils.encodeDownloadFilename(filename, agent);
		
		response.setHeader("content-disposition", "attachment;filename="+downloadName);
		
		ServletOutputStream os = response.getOutputStream();
		excel.write(os);
	}
	
	@RequestMapping("/downloadSensorData.do")
	public void downloadSensorData(Integer houseId,Date start , Date end,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1,查数据
		House house = houseService.selectById(houseId);
		
		List<Sensor> sensorList = sensorService.selectWithTypeByHouseIdOrderByExcelSort(houseId);
		Map<Integer,Integer> map = new HashMap<>();
		//先按时间排序,再按传感器类别排序,再按传感器
		//2,创建excell
		HSSFWorkbook excel = new HSSFWorkbook();
		HSSFSheet sheet = excel.createSheet(house.getName());
		HSSFRow headRow = sheet.createRow(0);
		int num = 0;
		if(!CollectionUtils.isEmpty(sensorList)){
			List<SensorData> dataList = sensorDataService.selectSensorData(houseId,start,end);
			for (int i=0; i< sensorList.size();i++) {
				map.put(sensorList.get(i).getId(), i+1);
				headRow.createCell(i+1).setCellValue(sensorList.get(i).getName() + "/" + sensorList.get(i).getType().getUnit());
			}
			headRow.createCell(0).setCellValue("时间");
			if(!CollectionUtils.isEmpty(dataList)){
				SensorData temp = dataList.get(0);
				HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
				row.createCell(0).setCellValue(DateFormatUtils.format(temp.getDateTime(), "yyyy/MM/dd HH:mm:ss"));
				SensorData data;
				for (int i = 0;i< dataList.size();i++ ) {
					
					try {
						num++;
						data = dataList.get(i);
						if(temp != null && !data.getDateTime().equals(temp.getDateTime())){
							row = sheet.createRow(sheet.getLastRowNum()+1);
							row.createCell(0).setCellValue(DateFormatUtils.format(data.getDateTime(), "yyyy/MM/dd HH:mm:ss"));
						}
						Integer sensorId = data.getSensorId();
						Integer cellNum = map.get(sensorId);
						if(cellNum != null){
							row.createCell(map.get(sensorId)).setCellValue(data.getValue());
						}
						temp = data;
					} catch (Exception e) {
						System.out.println("下载数据出错,第_"+ num +"_条数据,Excel 行 : "+(sheet.getLastRowNum()+1));
//						e.printStackTrace();
					}
				}
				start = dataList.get(0).getDateTime();
				end = dataList.get(dataList.size()-1).getDateTime();
			}
		}
		
		excel.close();
		response.setContentType("application/vnd.ms-excel");
		String filename;
		if(start != null && end != null){
			filename=house.getName()+"("+(start.getMonth()+1)+"月"+start.getDate()+"日-"+(end.getMonth()+1)+"月"+end.getDate()+"日).xls";
		}else{
			filename=house.getName()+".xls";
		}
//		System.out.println(filename);
		//根据浏览器的不同,设置不同的文件名字符格式
		String agent = request.getHeader("User-Agent");
		String downloadName = FileUtils.encodeDownloadFilename(filename, agent);
		
		response.setHeader("content-disposition", "attachment;filename="+downloadName);
		
		ServletOutputStream os = response.getOutputStream();
		excel.write(os);
	}
	
	@RequestMapping("/getSensorDataList.do")
	public @ResponseBody List<Object[]> getSensorDataList(Integer sensorId,Date start , Date end){
		return sensorDataService.getSensorDataList(sensorId,start,end);
	}
	
	@RequestMapping(value = "/resetWater.do")
	public @ResponseBody String resetWater(Integer sensorId){
		waterSensorDataService.resetPriodValue(sensorId);
		return "success";
	}

}
