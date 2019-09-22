package com.lyyh.fertilizer.threadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.CollectionUtils;

import com.lyyh.fertilizer.service.FertilizerService;
import com.lyyh.greenhouse.util.ThreadUtils;
import com.lyyh.greenhouse.util.fertilizer.FertilizerValveStates;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;
import com.lyyh.tzgk.pojo.Tequipment;
import com.lyyh.tzgk.pojo.Tvalve;

//@Component("delayCheckTzgk")
//@Scope("prototype")
public class DelayStartCheckTzgk2 implements Runnable {
	
	private static long DELAYTIME = 20 * 1000;
	
	private static Map<String,Boolean> taskStateMap = new HashMap<String,Boolean>();
	
	private Long delayTime;
	
	private String dtuCode;
	
	private FertilizerService fertilizerService;
	
	//执行数  最多执行3次
	private int maxExecuteNum;
	//在比对plc阀时,不同的阀以及对应的值
	private Map<Integer,Integer> diffValves;
	
	
	
	public DelayStartCheckTzgk2(Long delayTime,int maxExecuteNum,String dtuCode,FertilizerService fertilizerService,Map<Integer,Integer> diffValves) {
		super();
		this.delayTime = delayTime;
		this.dtuCode = dtuCode;
//		this.tvalveDao = tvalveDao;
		this.fertilizerService = fertilizerService;
		this.maxExecuteNum = maxExecuteNum;
		this.diffValves = diffValves;
		setStateOn(dtuCode);
		
	}

	public DelayStartCheckTzgk2() {
		super();
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
			Thread.sleep(delayTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			// plc的阀的状态
			List<Integer> pvalveStatusList = FertilizerValveStates.getStatesByDtuCode(dtuCode);
			//查询天正高科所有设备状态信息
			List<Tequipment> tequipmentStatusList = TzgkUtils.getAllTequipment();
			Map<String,Tequipment> tequipmentStatusMap = new HashMap<String,Tequipment>();
			if(tequipmentStatusList != null && tequipmentStatusList.size() > 0){
				for (Tequipment tequipment : tequipmentStatusList) {
					tequipmentStatusMap.put(tequipment.getEmac(), tequipment);
				}
			}
			//查询指定施肥机下数据库中天正高科阀列表
			List<Tvalve> tvalveList = fertilizerService.queryTvalvesByDtuCode(dtuCode);
			Map<Integer,Integer> hasChangedValves = new HashMap<Integer,Integer>();
			if(tvalveList != null && tvalveList.size() >0){
				for (Tvalve tvalve : tvalveList) {
					String eqmsg = tequipmentStatusMap.get(tvalve.getEmac()).getEqmsg();
					if (eqmsg == null || eqmsg.equalsIgnoreCase("null")) {
						TzgkUtils.sendCommand(tvalve, pvalveStatusList.get(tvalve.getNumber()-1));
						diffValves.put(tvalve.getNumber(), pvalveStatusList.get(tvalve.getNumber()-1));
						flag = true;
					} else {
						String[] split = eqmsg.split(",");
						int parseInt = Integer.parseInt(split[tvalve.getPosition() - 1].substring(2));
						if(parseInt != pvalveStatusList.get(tvalve.getNumber()-1)){
							TzgkUtils.sendCommand(tvalve, pvalveStatusList.get(tvalve.getNumber()-1));
							diffValves.put(tvalve.getNumber(), pvalveStatusList.get(tvalve.getNumber()-1));
							flag = true;
							System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss ")+"dtu编号 : "+dtuCode+" , 阀号 : "+tvalve.getNumber()+" , 第_"+(3-maxExecuteNum)+"_次比对plc与天正阀状态不一样,并发送命令");
						}else{
							Integer remove = diffValves.remove(tvalve.getNumber());
							if(remove != null){
								hasChangedValves.put(tvalve.getNumber(), remove);
							}
						}
					}
				}
			}
			//对已经改变的阀记录实时数据和阀的状态
			if(!CollectionUtils.isEmpty(hasChangedValves)){
				fertilizerService.insertTimeDataAndValveStatus(dtuCode, hasChangedValves);
			}
			if(flag){
				if(maxExecuteNum > 0){
					ThreadUtils.execute(new DelayStartCheckTzgk2(DELAYTIME,maxExecuteNum-1,dtuCode,fertilizerService,diffValves));
				}else{
					setStateOff(dtuCode);
				}
			}
		} catch (Exception e) {
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
