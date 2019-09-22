package com.lyyh.fertilizer.autoRun;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lyyh.fertilizer.dao.FertilizerDao;
import com.lyyh.fertilizer.dao.TvalveDao;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.threadTask.DelayStartCheckTzgk;
import com.lyyh.fertilizer.threadTask.InitValve;
import com.lyyh.greenhouse.util.fertilizer.FertilizerValveStates;
import com.lyyh.greenhouse.util.ThreadUtils;
import com.lyyh.greenhouse.util.exception.DisconnectException;
import com.lyyh.greenhouse.util.fertilizer.FertilizerUtils;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;
import com.lyyh.greenhouse.util.tzgk.ValveMap;
import com.lyyh.tzgk.pojo.Tvalve;

public class TimingCheckPlcValve {

//	@Autowired
//	ApplicationContext ac;

	// TODO 不应该从数据库里查询,应该把阀放到内存中
	@Autowired
	TvalveDao tvalveDao;
	
	@Autowired
	FertilizerDao fertilizerDao;
	
	private static long FIRSTDELAYTIME = 20 * 1000;
	
	private static int MAXEXECUTENUM = 2;

	public void controlValves() {
		// System.out.println("checkPLC ----------------正在执行PLC");
		List<Fertilizer> queryAllHaveDtuAndOnline = fertilizerDao.queryAllHaveDtuAndOnlineAndTZGK();
		if(queryAllHaveDtuAndOnline != null && queryAllHaveDtuAndOnline.size() >= 0){
			for (int i = 0;i<queryAllHaveDtuAndOnline.size();i++) {
				final Fertilizer fertilizer = queryAllHaveDtuAndOnline.get(i);
				ThreadUtils.execute(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							compareValves(fertilizer.getDtuCode());
						} catch (IOException e) {
							System.out.println("通讯错误>>>>>"+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : "+fertilizer.getDtuCode());
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
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
		List<Integer> oldState = FertilizerValveStates.getStatesByDtuCode(dtuCode);
		
		List<Integer> newState = readValveState(dtuCode);
		FertilizerValveStates.setStates(dtuCode, newState);
//		//打印plc中阀的状态的状态
//		if (null != oldState) {
//			System.out.println(dtuCode+" : PLC:====oldState:" + oldState.toString());
//		} else {
//			System.out.println(dtuCode+" : PLC:====oldState: null");
//		}
//		if (null != newState) {
//			System.out.println(dtuCode+" : PLC:====newState:" + newState.toString());
//		} else {
//			System.out.println(dtuCode+" : PLC:====newState: null");
//		}

		// 在下列情况里进行初始化操作
		Boolean initialInfo = FertilizerValveStates.getInitialInfo(dtuCode);
		if (oldState == null || oldState.size() == 0 || newState == null || newState.size() == 0) {

			if (initialInfo == null || !initialInfo) {
//				Collection<Tvalve> tvalves = getValves(dtuCode).values();
				// TODO 开启延迟检查tzgk,附带初始化操作
//				ThreadUtils.execute((DelayStartCheckTzgk) ac.getBean("delayCheckTzgk"));
//				ThreadUtils.execute(new DelayStartCheckTzgk(10000L,dtuCode,tvalveDao));
			}
		} else {
			// 如果程序不一样,则发送不一样的地方的命令
			if (!oldState.equals(newState)) {
				// 比较不同的地方,发送命令
				for (int i = 0; i < newState.size(); i++) {
					if (newState.get(i) != oldState.get(i)) {
//						System.out.println("PLC:====  第" + (i + 1) + "个阀改变为" + newState.get(i));
						
						//查询这个plc的输出对应的天正阀
						Tvalve tvalve = tvalveDao.queryTvalveByDtuCodeAndNumber(dtuCode, i+1);
						if(tvalve != null){
							// 发送命令
							TzgkUtils.sendCommand(tvalve, newState.get(i));
						}
					}
				}
				if(!DelayStartCheckTzgk.checkState(dtuCode)){
					// 延迟10秒检查天正高科的阀的状态是否改变
					ThreadUtils.execute(new DelayStartCheckTzgk(FIRSTDELAYTIME,MAXEXECUTENUM,dtuCode,tvalveDao));
				}
			}
			if (initialInfo == null || initialInfo) {
				FertilizerValveStates.setInitialInfo(dtuCode, false);
			}
		}

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

//	// 根据dtuCode获取阀,如果获取不到,则马上去数据库中查找,把数据库中的阀按dtucode分类加载到ValveMap中
//	public Map<Integer, Tvalve> getValves(String dtuCode) {
//		Map<Integer, Tvalve> tvalves = ValveMap.getTvalves(dtuCode);
//		if (tvalves == null || tvalves.size() == 0) {
//			List<Tvalve> tvalveList = tvalveDao.queryTvalvesByDtuCode(dtuCode);
//			tvalves = new HashMap<Integer, Tvalve>();
//			for (Tvalve tvalve : tvalveList) {
//				tvalves.put(tvalve.getNumber(), tvalve);
//			}
//			ValveMap.putTvalves(dtuCode, tvalves);
//		}
//		return tvalves;
//	}
}
