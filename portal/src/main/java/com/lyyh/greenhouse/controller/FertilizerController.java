package com.lyyh.greenhouse.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.HouseData_highcharts;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;
import com.lyyh.greenhouse.service.CameraService;
import com.lyyh.greenhouse.service.HouseDataService;
import com.lyyh.greenhouse.service.HouseService;
import com.lyyh.greenhouse.util.FileUtils;

@Controller
@RequestMapping(value="/fertilizer")
public class FertilizerController {

	@Autowired
	private HouseDataService houseDataService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private CameraService cameraService;

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
	@RequestMapping(value="/getIndexList.do")
	public @ResponseBody HouseData_highcharts getIndexList(HttpSession session,Integer houseId,Date start,Date end){
		HouseDataVo houseDataVo = getHouseDataVo(session,houseId,start,end);
		HouseData_highcharts houseData_highcharts = houseDataService.getIndexList(houseDataVo);
		
		return houseData_highcharts;
	}
	
	/*
	 * 室内数据首页
	 */
	@RequestMapping(value="/listAll.do")
	public String listAll(Model model,HttpSession session,Integer houseId,Date start,Date end){
//		housesToSession(session);
//		HouseDataVo houseDataVo = getHouseDataVo(session,houseId,start,end);
//		HouseData houseData =houseDataService.getNewest(houseDataVo);
//		
//		Camera_GH camera = cameraService.findCameraByHouse(houseDataVo);
//		
//		model.addAttribute("camera",camera);
//		model.addAttribute("houseData",houseData);
//		model.addAttribute("houseId",houseDataVo.getHouseId());
		
		return "fertilizer/list";
	}
	
	/*
	 * 历史数据首页
	 * historyList.do
	 */
	@RequestMapping("/historyList.do")
	public String historyList(Model model,HttpSession session){
		housesToSession(session);
		model.addAttribute("houseId",1);
		return "houseData/historyList";
		
	}
	
	/*
	 * 获取温室列表  		??是否要提取到工具类??
	 */
	public void housesToSession(HttpSession session){
		//session中获取
		List<House> houses = (List<House>) session.getAttribute("houses");
		//如果没有,先查后放
		if (null == houses) {
			User user = (User) session.getAttribute("loginUser");
			
			houses = houseService.findAllByZoneId(user.getZoneId());
			session.setAttribute("houses", houses);
		}
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
}
