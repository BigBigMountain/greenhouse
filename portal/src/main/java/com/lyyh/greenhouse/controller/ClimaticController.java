package com.lyyh.greenhouse.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.pojo.vo.ClimaticVo;
import com.lyyh.greenhouse.pojo.vo.HouseDataVo;
import com.lyyh.greenhouse.service.ClimaticService;
import com.lyyh.greenhouse.util.FileUtils;

@Controller
@RequestMapping(value="/climatic")
public class ClimaticController {

	@Autowired
	private ClimaticService climaticService;

	public ClimaticVo getClimaticVo(HttpSession session,Date start,Date end){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		ClimaticVo vo = new ClimaticVo();
		vo.setZoneId(zoneId);
		vo.setStart(start);
		vo.setEnd(end);
		return vo;
	}
	
	/*
	 * 天气信息首页,
	 * /climaticData/listAll.do
	 */
	@RequestMapping("/listAll.do")
	public String listAll(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		//获取最新天气信息
		Climatic climatic =climaticService.getNewest(zoneId);
		model.addAttribute("zoneId", zoneId);
		model.addAttribute("climatic",climatic);
		return "climatic/list";
	}
	@RequestMapping("/historylist.do")
	public String historylist(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		Integer zoneId = user.getZoneId();
		
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DATE, -1);
		Date end = ca.getTime();
		ca.add(Calendar.DATE, -6);
		Date start = ca.getTime();
		model.addAttribute("zoneId",zoneId);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		return "climatic/historyList";
	}
	
	/*
	 * 气温
	 */
	@RequestMapping(value="/getTemperatureList.do")
	public @ResponseBody List<Object[]> getTemperatureList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> temperatures = climaticService.getTemperatureList(climaticVo);
		return temperatures;
	}
	/*
	 * 湿度
	 */
	@RequestMapping(value="/getHumidityList.do")
	public @ResponseBody List<Object[]> getHumidityList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> humiditys = climaticService.getHumidityList(climaticVo);
		return humiditys;
	}
	/*
	 * 光照
	 */
	@RequestMapping(value="/getLightingList.do")
	public @ResponseBody List<Object[]> getLightingList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> lightings = climaticService.getLightingList(climaticVo);
		return lightings;
	}
	/*
	 * 气压
	 */
	@RequestMapping(value="/getPressureList.do")
	public @ResponseBody List<Object[]> getPressureList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> pressures = climaticService.getPressureList(climaticVo);
		return pressures;
	}
	/*
	 * 风速
	 */
	@RequestMapping(value="/getWindSpeedList.do")
	public @ResponseBody List<Object[]> getWindSpeedList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> windSpeeds = climaticService.getWindSpeedList(climaticVo);
		return windSpeeds;
	}
	/*
	 * 降雨量
	 */
	@RequestMapping(value="/getRainFallList.do")
	public @ResponseBody List<Object[]> getRainFallList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> rainFalls = climaticService.getRainFallList(climaticVo);
		return rainFalls;
	}
	/*
	 * PH
	 */
	@RequestMapping(value="/getPHList.do")
	public @ResponseBody List<Object[]> getPHList(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> phs = climaticService.getPHList(climaticVo);
		return phs;
	}
	/*
	 * PM2.5
	 */
	@RequestMapping(value="/getPM25List.do")
	public @ResponseBody List<Object[]> getPM25List(HttpSession session,Date start,Date end){
		ClimaticVo climaticVo = getClimaticVo(session, start, end);
		List<Object[]> pm25s = climaticService.getPM25List(climaticVo);
		return pm25s;
	}
	
	
	/*	下载
	 * /houseData/downloadData.do
	 */
	@RequestMapping("/downloadClimaticData.do")
	public void downloadData(HttpServletRequest request,HttpServletResponse response,Integer zoneId,Date start,Date end) throws IOException{
		List<Climatic> ClimaticList = climaticService.getClimaticData(zoneId,start,end);
		HSSFWorkbook excel = new HSSFWorkbook();
		HSSFSheet sheet = excel.createSheet("气象数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("时间");
		headRow.createCell(1).setCellValue("温度");
		headRow.createCell(2).setCellValue("湿度");
		headRow.createCell(3).setCellValue("光照");
		headRow.createCell(4).setCellValue("气压");
		
		headRow.createCell(5).setCellValue("风速");
		headRow.createCell(6).setCellValue("风向");
		headRow.createCell(7).setCellValue("雨雪");
		headRow.createCell(8).setCellValue("降雨量");
		headRow.createCell(9).setCellValue("PH值");
		headRow.createCell(10).setCellValue("PM2.5指数");
		if(ClimaticList==null || ClimaticList.size()==0){
			HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
			row.createCell(0).setCellValue("该区域暂无数据");
		}else{
			for (Climatic data : ClimaticList) {
				HSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
				row.createCell(0).setCellValue(DateFormatUtils.format(data.getDatetime(),"yyyy-MM-dd HH:mm:ss"));
				row.createCell(1).setCellValue(data.getTemperature()==null ? "" : data.getTemperature()+"");
				row.createCell(2).setCellValue(data.getTemperature()==null ? "" : data.getTemperature()+"");
				row.createCell(3).setCellValue(data.getLighting()==null ? "" : data.getLighting()+"");
				row.createCell(4).setCellValue(data.getPressure()==null ? "" : data.getPressure()+"");
				row.createCell(5).setCellValue(data.getWindSpeed()==null ? "" : data.getWindSpeed()+"");
				row.createCell(6).setCellValue(data.getWindDirection()==null ? "" : data.getWindDirection()+"");
				row.createCell(7).setCellValue(data.getRainAndSnow()==null ? "" : data.getRainAndSnow()+"");
				row.createCell(8).setCellValue(data.getRainFall()==null ? "" : data.getRainFall()+"");
				row.createCell(9).setCellValue(data.getPh()==null ? "" : data.getPh()+"");
				row.createCell(10).setCellValue(data.getPm25()==null ? "" : data.getPm25()+"");
			}
			if(null == start){
				start=ClimaticList.get(0).getDatetime();
			}
			if(null == end){
				end = ClimaticList.get(ClimaticList.size()-1).getDatetime();
			}
		}
		excel.close();
		response.setContentType("application/vnd.ms-excel");
		String filename=(start.getMonth()+1)+"月"+start.getDate()+"日-"+(end.getMonth()+1)+"月"+end.getDate()+"日气象数据.xls";
//		System.out.println(filename);
		//根据浏览器的不同,设置不同的文件名字符格式
		String agent = request.getHeader("User-Agent");
		String downloadName = FileUtils.encodeDownloadFilename(filename, agent);
		
		response.setHeader("content-disposition", "attachment;filename="+downloadName);
		
		ServletOutputStream os = response.getOutputStream();
		excel.write(os);
	}
	
}
