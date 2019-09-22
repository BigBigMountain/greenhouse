package com.lyyh.fertilizer.threadTask;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lyyh.fertilizer.dao.TvalveDao;
import com.lyyh.greenhouse.util.ThreadUtils;
import com.lyyh.greenhouse.util.fertilizer.FertilizerUtils;
import com.lyyh.greenhouse.util.fertilizer.FertilizerValveStates;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;
import com.lyyh.greenhouse.util.tzgk.ValveMap;
import com.lyyh.tzgk.pojo.Tequipment;
import com.lyyh.tzgk.pojo.Tvalve;

import sun.tools.tree.EqualExpression;

//@Component("delayCheckTzgk")
//@Scope("prototype")
public class DelayStartCheckTzgk implements Runnable {
	
	private static long DELAYTIME = 20 * 1000;
	
	private static Map<String,Boolean> taskStateMap = new HashMap<String,Boolean>();
	
	private Long delayTime;
	
	private String dtuCode;
	
	private TvalveDao tvalveDao;
	
	private int maxExecuteNum;
	
	public DelayStartCheckTzgk(Long delayTime,int maxExecuteNum,String dtuCode,TvalveDao tvalveDao) {
		super();
		this.delayTime = delayTime;
		this.dtuCode = dtuCode;
		this.tvalveDao = tvalveDao;
		this.maxExecuteNum = maxExecuteNum;
		setStateOn(dtuCode);
		
	}

	public DelayStartCheckTzgk() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	@Autowired
	ApplicationContext ac;

	@Autowired
	Scheduler startQuertz;

	@Autowired
	JobDetail checkTzgkValveJobDetail;

	@Autowired
	Trigger checkTzgkValveSimpleTrigger;
*/
	@Override
	public void run() {
		//用于判断是否要把施肥机状态置为初始化状态
//		boolean initialFlag = false;
		
		boolean flag = false;
		try {
//			System.out.println("延迟时间1: " + new Date());
			Thread.sleep(delayTime);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			// plc的阀的状态
			List<Integer> pvalveStates = FertilizerValveStates.getStatesByDtuCode(dtuCode);
			
			List<Tequipment> tequipments = TzgkUtils.getAllTequipment();
			Map<String,Tequipment> map = new HashMap<String,Tequipment>();
			if(tequipments != null && tequipments.size() > 0){
				for (Tequipment tequipment : tequipments) {
					map.put(tequipment.getEmac(), tequipment);
				}
			}
			
			List<Tvalve> tvalveList = tvalveDao.queryTvalvesByDtuCode(dtuCode);
			
			if(tvalveList != null && tvalveList.size() >0){
				for (Tvalve tvalve : tvalveList) {
					String eqmsg = map.get(tvalve.getEmac()).getEqmsg();
					if (eqmsg == null || eqmsg == "null") {
						TzgkUtils.sendCommand(tvalve, pvalveStates.get(tvalve.getNumber()-1));
						flag = true;
					} else {
						String[] split = eqmsg.split(",");
						int parseInt = Integer.parseInt(split[tvalve.getPosition() - 1].substring(2));
						if(parseInt != pvalveStates.get(tvalve.getNumber()-1)){
							TzgkUtils.sendCommand(tvalve, pvalveStates.get(tvalve.getNumber()-1));
							flag = true;
							System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")+"dtu编号 : "+dtuCode+", 阀号 : "+tvalve.getNumber()+", 第"+(3-maxExecuteNum)+"次比对plc与天正阀状态不一样,并发送命令");
						}
					}
				}
			}
			
			if(flag){
				if(maxExecuteNum > 0){
					ThreadUtils.execute(new DelayStartCheckTzgk(DELAYTIME,maxExecuteNum-1,dtuCode,tvalveDao));
				}else{
					setStateOff(dtuCode);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setStateOn(String dtuCode){
		taskStateMap.put(dtuCode, true);
	}
	
	public static void setStateOff(String dtuCode){
		taskStateMap.put(dtuCode, false);
	}
	
	public static boolean checkState(String dtuCode){
		return taskStateMap.get(dtuCode) == null ? false : taskStateMap.get(dtuCode);
	}
	
	
}
