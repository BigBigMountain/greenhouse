package com.lyyh.fertilizer.autoRun;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.service.FertilizerService;
import com.lyyh.fertilizer.threadTask.DelayStartCheckTzgk;
import com.lyyh.fertilizer.threadTask.DelayStartCheckTzgk2;
import com.lyyh.greenhouse.util.ThreadUtils;
import com.lyyh.greenhouse.util.exception.DisconnectException;
import com.lyyh.greenhouse.util.fertilizer.FertilizerConstant;
import com.lyyh.greenhouse.util.fertilizer.FertilizerUtils;
import com.lyyh.greenhouse.util.fertilizer.FertilizerValveStates;
import com.lyyh.greenhouse.util.tzgk.TzgkUtils;
import com.lyyh.tzgk.pojo.Tvalve;

public class TimingCheckPlcValve2 {

//	@Autowired
//	ApplicationContext ac;

	// TODO 不应该从数据库里查询,应该把阀放到内存中
	@Autowired
	private FertilizerService fertilizerService;
	
	private static long FIRSTDELAYTIME = 20 * 1000;
	
	private static int MAXEXECUTENUM = 2;

	public void controlValves() {
		// System.out.println("checkPLC ----------------正在执行PLC");
		List<Fertilizer> onlineFertilizers = fertilizerService.queryAllHaveDtuAndOnline();
		if(!CollectionUtils.isEmpty(onlineFertilizers)){
			for (int i = 0;i<onlineFertilizers.size();i++) {
				final Fertilizer fertilizer = onlineFertilizers.get(i);
				ThreadUtils.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							Integer valveType = fertilizer.getValveType();
							compareValves(fertilizer.getDtuCode(),valveType == null ? 0 : valveType);
						} catch (IOException e) {
							System.out.println("通讯错误>>>>>"+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : "+fertilizer.getDtuCode());
							e.printStackTrace();
						}
					}
				});
			}
		}
	}

	// 获取列表,与上次比较,不一样就发送命令,一样就不发送
	public void compareValves(String dtuCode, Integer valveType) throws IOException {
		List<Integer> oldState = FertilizerValveStates.getStatesByDtuCode(dtuCode);
		
		List<Integer> newState = readValveState(dtuCode);
		FertilizerValveStates.setStates(dtuCode, newState);

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
				//TODO 查询施肥机实时数据
				
				
				// 比较不同的地方,发送命令
				Map<Integer,Integer> diffValves = new HashMap<Integer,Integer>();
				
				switch (valveType){
				//天正高科的阀
				case FertilizerConstant.TZGK_VALVE_TYPE:
					for (int i = 0; i < newState.size(); i++) {
						if (newState.get(i) != oldState.get(i)) {
								//查询这个plc的输出对应的天正阀
								Tvalve tvalve = fertilizerService.queryTvalveByDtuCodeAndNumber(dtuCode, i+1);
								if(tvalve != null){
									// 发送命令
									TzgkUtils.sendCommand(tvalve, newState.get(i));
									Map<Integer, Integer> map = new HashMap<Integer,Integer>();
									diffValves.put(i+1,newState.get(i));
								}
//							System.out.println("PLC:====  第" + (i + 1) + "个阀改变为" + newState.get(i));
						}
					}
					if(!DelayStartCheckTzgk.checkState(dtuCode)){
						// 延迟10秒检查天正高科的阀的状态是否改变
						ThreadUtils.execute(new DelayStartCheckTzgk2(FIRSTDELAYTIME,MAXEXECUTENUM,dtuCode,fertilizerService,diffValves));
					}
					break;

				//普通阀
				case FertilizerConstant.GENERAL_VALVE_TYPE:
					for (int i = 0; i < newState.size(); i++) {
						if (newState.get(i) != oldState.get(i)) {
							diffValves.put(i+1,newState.get(i));
//							System.out.println("PLC:====  第" + (i + 1) + "个阀改变为" + newState.get(i));
						}
					}
					fertilizerService.insertTimeDataAndValveStatus(dtuCode,diffValves);
					break;
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
			fertilizerService.setOffline(dtuCode);
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
