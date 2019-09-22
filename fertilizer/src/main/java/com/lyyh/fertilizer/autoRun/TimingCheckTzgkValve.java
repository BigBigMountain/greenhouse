package com.lyyh.fertilizer.autoRun;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lyyh.fertilizer.dao.FertilizerDao;
import com.lyyh.fertilizer.dao.TvalveDao;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.threadTask.InitValve;
import com.lyyh.greenhouse.util.ThreadUtils;
import com.lyyh.greenhouse.util.exception.DisconnectException;
import com.lyyh.greenhouse.util.fertilizer.FertilizerUtils;
import com.lyyh.greenhouse.util.fertilizer.FertilizerValveStates;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;
import com.lyyh.greenhouse.util.tzgk.ValveMap;
import com.lyyh.tzgk.pojo.Tequipment;
import com.lyyh.tzgk.pojo.Tvalve;

public class TimingCheckTzgkValve {

	@Autowired
	ApplicationContext ac;

	@Autowired
	TvalveDao tvalveDao;
	
	@Autowired
	FertilizerDao fertilizerDao;

	public void compareValves() {
//		System.out.println(" ----------------正在执行TZGK");
		List<Fertilizer> queryAllHaveDtuAndOnline = fertilizerDao.queryAllHaveDtuAndOnlineAndTZGK();
		if(queryAllHaveDtuAndOnline != null && queryAllHaveDtuAndOnline.size() >= 0){
			for (Fertilizer fertilizer : queryAllHaveDtuAndOnline) {
				try {
					compareValves(fertilizer.getDtuCode());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		String dtuCode = "741852";
//		try {
//			compareValves(dtuCode);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	// 获取列表,与上次比较,不一样就发送命令,一样就不发送
	public void compareValves(String dtuCode) throws IOException {
		// 从天正高科那里获取所有设备
		List<Tequipment> tequipments = TzgkUtils.getAllTequipment();
		Integer[] tvalveStates = convertToIntegerList(tequipments,dtuCode);
//		System.out.print("TZGK-----tzgk阀状态: [");
//		for (Integer integer : tvalveStates) {
//			System.out.print(integer+" ");
//		}
//		System.out.println("]");
		// plc的阀的状态
		List<Integer> pvalveStates = null;
		try {
			pvalveStates = FertilizerUtils.readValves(dtuCode);
		} catch (DisconnectException e1) {
			fertilizerDao.setOffline(dtuCode);
//			e1.printStackTrace();
		}
//		System.out.println("TZGK-----PLC阀状态: "+pvalveStates.toString());
		//是否初始化的标记
		Boolean initialInfo = FertilizerValveStates.getInitialInfo(dtuCode);
		// 在下列情况下要进行初始化
		if (pvalveStates == null || pvalveStates.size() == 0) {
//			System.out.println(initialInfo);
			
			//判断当前天正的阀状态是否都是关闭状态,即初始化完成状态,如果都关闭,则退出当前线程,不再检查. true:都关闭了, false:还有打开的
			boolean flag = true;
			for (Integer tv : tvalveStates) {
				if(tv == null || tv != 0){
					flag = false;
					break;
				}
			}
			if(flag){
				//记录施肥机现在已经初始化状态了
				FertilizerValveStates.setInitialInfo(dtuCode, true);
				try {
					Scheduler scheduler = (Scheduler) ac.getBean("startQuertz");
					scheduler.deleteJob(new JobKey("checkTzgkValveJobDetail"));
//					System.out.println("停止tzgk了");
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			} else {
				FertilizerValveStates.setInitialInfo(dtuCode, false);
			}
			
			
			//判断是否是已经初始化了,如果没有初始化,则进行初始化
			if (initialInfo == null || !initialInfo) {
				// 初始化电磁阀状态
				Collection<Tvalve> tvalves = getValves(dtuCode).values();
//				TzgkUtils.initValve(tvalves);
				ThreadUtils.execute(new InitValve(dtuCode,tvalveDao));
//				System.out.println("TZGK-----在初始化");
			}
			
		} else {
			// 比较不同的地方,发送命令
			//记录天正和plc的状态是否一样,如果一样就停止检查
			boolean isTzgkEqualPlc = true;
//			int size = tvalveStates.length < pvalveStates.size() ? tvalveStates.length : pvalveStates.size();
			int size = tvalveStates.length;
			for (int i = 0; i < size; i++) {
				if (tvalveStates[i] == null || pvalveStates.get(i) != tvalveStates[i]) {
					isTzgkEqualPlc = false;
//					 System.out.println("TZGK  第"+(i+1)+"个阀改变为"+pvalveStates.get(i));
					Tvalve tvalve = getValves(dtuCode).get(i + 1);
					// 发送命令
					TzgkUtils.sendCommand(tvalve, pvalveStates.get(i));
				}
			}
			if(isTzgkEqualPlc){
				try {
					Scheduler scheduler = (Scheduler) ac.getBean("startQuertz");
					scheduler.deleteJob(new JobKey("checkTzgkValveJobDetail"));
//					System.out.println("停止tzgk了");
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//更改施肥机初始化状态
			if (initialInfo == null || initialInfo) {
				FertilizerValveStates.setInitialInfo(dtuCode, false);
			}
		}
	}

	private Integer[] convertToIntegerList(List<Tequipment> tequipments, String dtuCode) {
		Map<String, String> map = new HashMap<String, String>();
		// 遍历List,封装到Map<Emac,msg>
		for (Tequipment equipment : tequipments) {
			System.out.println(equipment.toString());
			map.put(equipment.getEmac(), equipment.getEqmsg());
		}
		
		// tzgk的阀的状态
		Map<Integer, Tvalve> tvalvemap = getValves(dtuCode);
		Collection<Tvalve> tvalves = tvalvemap.values();
//		List<Integer> tvalveStates = new LinkedList<Integer>();
		Integer tvalveStates[] = new Integer[tvalves.size()];
		for (Tvalve tvalve : tvalves) {
			String eqmsg = map.get(tvalve.getEmac());
			System.out.println(eqmsg);
			if (eqmsg == "null" || eqmsg == null) {
				tvalveStates[tvalve.getNumber() - 1] = null;
			} else {
				String[] split = eqmsg.split(",");
				tvalveStates[tvalve.getNumber() - 1]= Integer.parseInt(split[tvalve.getPosition() - 1].substring(2));
			}
		}
		return tvalveStates;
	}

	// 获取阀的状态列表
	public List<Integer> readValveState(String dtuCode) throws IOException {
		List<Integer> readValves = null;
		try {
			readValves = FertilizerUtils.readValves(dtuCode);
		} catch (DisconnectException e) {
			fertilizerDao.setOffline(dtuCode);
		} 
		return readValves;
	}

	// 根据dtuCode获取阀,如果获取不到,则马上去数据库中查找,把数据库中的阀按dtucode分类加载到ValveMap中
	public Map<Integer, Tvalve> getValves(String dtuCode) {
		Map<Integer, Tvalve> tvalves = ValveMap.getTvalves(dtuCode);
		if (tvalves == null || tvalves.size() == 0) {
			List<Tvalve> tvalveList = tvalveDao.queryTvalvesByDtuCode(dtuCode);
			tvalves = new HashMap<Integer, Tvalve>();
			for (Tvalve tvalve : tvalveList) {
				tvalves.put(tvalve.getNumber(), tvalve);
			}
			ValveMap.putTvalves(dtuCode, tvalves);
		}
		return tvalves;
	}

}
