package com.lyyh.fertilizer.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

import com.lyyh.greenhouse.util.MapList;
import com.lyyh.greenhouse.util.MapTreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.lyyh.fertilizer.dao.FertilizerDao;
import com.lyyh.fertilizer.dao.TclimaticDao;
import com.lyyh.fertilizer.dao.TsoilMoistureDao;
import com.lyyh.fertilizer.dao.TvalveDao;
import com.lyyh.fertilizer.dao.ValveDao;
import com.lyyh.fertilizer.pojo.ChartsPoint;
import com.lyyh.fertilizer.pojo.Climatic;
import com.lyyh.fertilizer.pojo.Fertilizer;
import com.lyyh.fertilizer.pojo.FertilizerData;
import com.lyyh.fertilizer.pojo.FertilizerDataCharts;
import com.lyyh.fertilizer.pojo.FertilizerDataCollector;
import com.lyyh.fertilizer.pojo.IrrigationFormula;
import com.lyyh.fertilizer.pojo.IrrigationProgram;
import com.lyyh.fertilizer.pojo.ManualControlPojo;
import com.lyyh.fertilizer.pojo.ProgramControlPojo;
import com.lyyh.fertilizer.pojo.ProgramValveMapping;
import com.lyyh.fertilizer.pojo.PumpState;
import com.lyyh.fertilizer.pojo.SoilMoisture;
import com.lyyh.fertilizer.pojo.ValveData;
import com.lyyh.fertilizer.pojo.ValveDataVo;
import com.lyyh.fertilizer.pojo.ValveValue;
import com.lyyh.fertilizer.pojo.vo.ValveStatistics;
import com.lyyh.fertilizer.pojo.vo.ValveStatistics.IrriRecord;
import com.lyyh.fertilizer.service.FertilizerService;
import com.lyyh.greenhouse.util.CommonUtils;
import com.lyyh.greenhouse.util.dtu.DtuUtils;
import com.lyyh.greenhouse.util.exception.DisconnectException;
import com.lyyh.greenhouse.util.fertilizer.FertilizerUtils;
import com.lyyh.tzgk.pojo.TclimaticData;
import com.lyyh.tzgk.pojo.TsoilTempAndHum;
import com.lyyh.tzgk.pojo.TtempAndHum;
import com.lyyh.tzgk.pojo.Tvalve;

@Service
public class FertilizerServiceImpl implements FertilizerService {
	@Autowired
	TvalveDao tvalveDao;

	@Autowired
	private FertilizerDao fertilizerDao;

	@Autowired
	private TclimaticDao tclimaticDao;

	@Autowired
	private TsoilMoistureDao tsoilMoistureDao;

	@Autowired
	private ValveDao valveDao;

	@Override
	public List<Fertilizer> queryAllFertilizer() {
		return fertilizerDao.queryAll();
	}

	@Override
	public List<Fertilizer> queryAllByUid(int userId) {
		return fertilizerDao.queryAllByUid(userId);
	}

	@Override
	public List<Fertilizer> queryAllByUsername(String username) {
		return fertilizerDao.queryAllByUsername(username);
	}

	@Override
	public Fertilizer selectByDtuCode(String dtuCode) {
		return fertilizerDao.selectByDtuCode(dtuCode);
	}

	@Override
	public Fertilizer selectByFertilizerId(int fertilizerId) {
		return fertilizerDao.selectByFertilizerId(fertilizerId);
	}

	@Transactional
	@Override
	public void addFertilizer(Fertilizer fertilizer) {
		fertilizerDao.addFertilizer(fertilizer);
	}

	@Transactional
	@Override
	public void deleteByFerId(int fertilizerId) {
		fertilizerDao.deleteByFerId(fertilizerId);
	}

	@Transactional
	@Override
	public void updateFertilizer(Fertilizer fertilizer) {
		fertilizerDao.updateFertilizer(fertilizer);
	}

	/*
	 * 1:在线, 0 或其他:不在线
	 */
	@Transactional
	@Override
	public void setOnline(String dtuCode) {
		fertilizerDao.setOnline(dtuCode);
	}

	@Transactional
	@Override
	public void setOffline(String dtuCode) {
		fertilizerDao.setOffline(dtuCode);
	}

	@Transactional
	@Override
	public void setStateOn(String dtuCode) {
		fertilizerDao.setStateOn(dtuCode);
	}

	@Transactional
	@Override
	public void setStateOff(String dtuCode) {
		fertilizerDao.setStateOff(dtuCode);
	}

	@Transactional
	@Override
	public int addData(FertilizerDataCollector data) {
		fertilizerDao.addData(data);
		return data.getFertilizerDataId();
	}

	@Override
	public int addData(String dtuCode) {
		// TODO Auto-generated method stub
		FertilizerDataCollector timeData = getTimeData(dtuCode);
		fertilizerDao.addData(timeData);
		return timeData.getFertilizerDataId();
	}

	@Transactional
	@Override
	public void addDatas(List<FertilizerDataCollector> datas) {
		fertilizerDao.addDatas(datas);
	}

	@Transactional
	@Override
	public void addDatas() {
		List<FertilizerDataCollector> datas = new ArrayList<FertilizerDataCollector>();
		try {
			Set<Entry<String, OutputStream>> dtuEntrys = DtuUtils.getAllDtuEntry();
			if (null != dtuEntrys && dtuEntrys.size() != 0) {
				// 把entry复制一份遍历,在迭代的时候可能会需要删除Iterator的操作,如果发现连接不通,就把池中的链接删除,
				Set<Entry<String, OutputStream>> dtuEntrys2 = new HashSet<Entry<String, OutputStream>>(dtuEntrys);
				for (Entry<String, OutputStream> entry : dtuEntrys2) {
					FertilizerDataCollector timeData = getTimeData(entry.getKey());
					datas.add(timeData);
				}
				fertilizerDao.addDatas(datas);
			}
		} catch (Exception e) {
			// System.out.println("批量插入数据时出错");
			e.printStackTrace();
		}
	}

	/*
	 * 获取实时数据
	 */
	@Override
	public FertilizerDataCollector getTimeData(String dtuCode) {
		FertilizerDataCollector dataCollector = null;
		try {
			dataCollector = FertilizerUtils.readValue(dtuCode);
			// System.out.println(dataCollector);
			// } catch (SocketTimeoutException e) {
			// try {
			// dataCollector = FertilizerUtils.readValue(dtuCode);
			// } catch (IOException e1) {
			// e1.printStackTrace();
			//// System.out.println(" +++" + dtuCode + "+++业务层操作时不在线--1");
			// // TODO 操作数据库,设置离线,什么时候设置在线呢?
			// // fertilizerDao.setOffline(dtuCode);
			// // 销毁连接
			// // DtuUtils.destroyDtuById(dtuCode);
			// e.printStackTrace();
			// }
		} catch (IOException e) {
			// System.out.println(" +++" + dtuCode + "+++业务层操作时不在线--2");
			// TODO 操作数据库,设置离线,什么时候设置在线呢?
			// fertilizerDao.setOffline(dtuCode);
			// 销毁连接
			// DtuUtils.destroyDtuById(dtuCode);
			e.printStackTrace();
		}

		return dataCollector;
	}

	@Override
	public FertilizerData selectOneDataByDataId(int dataId) {
		return fertilizerDao.selectOneDataByDataId(dataId);
	}

	@Override
	public List<FertilizerData> queryHistoryDatasByVo(FertilizerData vo) {
		return fertilizerDao.queryDatasByVo(vo);
	}

	@Override
	public List<FertilizerData> queryOneDayDatasByFertilizerId(int fertilizerId) {
		FertilizerData vo = new FertilizerData();
		Date end = new Date();
		Date start = new Date(end.getTime() - 1000 * 3600 * 24);
		vo.setStart(start);
		vo.setEnd(end);
		vo.setFertilizerId(fertilizerId);
		return fertilizerDao.queryDatasByVo(vo);
	}

	@Override
	public FertilizerDataCharts getDataCharts(FertilizerData vo) {

		List<FertilizerData> datas = fertilizerDao.queryDatasByVo(vo);
		FertilizerDataCharts dataCharts = null;
		if (datas != null && datas.size() > 0) {
			dataCharts = new FertilizerDataCharts();

			for (FertilizerData data : datas) {
				dataCharts.getPh().add(data.getPh());
				dataCharts.getEc().add(data.getEc());
				dataCharts.getLiquidLevel().add(data.getLiquidLevel());
				dataCharts.getRateFlow().add(data.getRateFlow());
				dataCharts.getSoilHumidity1().add(data.getSoilHumidity1());
				dataCharts.getSoilHumidity2().add(data.getSoilHumidity2());
				dataCharts.getSoilHumidity3().add(data.getSoilHumidity3());
				dataCharts.getSoilHumidity4().add(data.getSoilHumidity4());
				dataCharts.getTime().add(data.getTime());
				dataCharts.getMainFlow().add(data.getMainFlow());
				dataCharts.getTotalIrrigations().add(data.getTotalIrrigation());
			}
		}
		return dataCharts;
	}

	@Override
	public Map<String, List> getSingleCharts(String type, FertilizerData vo) {
		List<FertilizerData> queryDatas = fertilizerDao.queryDatasByVo(vo);
		List<Object> datas = new ArrayList<>();
		List<Date> times = new ArrayList<>();
		Map<String, List> map = new HashMap<String, List>();

		if (queryDatas != null && queryDatas.size() > 0) {
			try {
				Method method = FertilizerData.class.getMethod("get" + CommonUtils.firstToUpperCase(type));
				for (FertilizerData fertilizerData : queryDatas) {
					Object object = method.invoke(fertilizerData);
					datas.add(object);
					times.add(fertilizerData.getTime());
				}
				map.put("values", datas);
				map.put("time", times);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public List<ChartsPoint> getChartsPoints(String type, FertilizerData vo) {
		List<FertilizerData> queryDatas = fertilizerDao.queryDatasByVo(vo);

		List<ChartsPoint> data = new ArrayList<ChartsPoint>();
		if (queryDatas != null && queryDatas.size() > 0) {
			try {
				Method method = FertilizerData.class.getMethod("get" + CommonUtils.firstToUpperCase(type));
				for (FertilizerData fertilizerData : queryDatas) {
					ChartsPoint point = new ChartsPoint();
					point.setX(fertilizerData.getTime());
					point.setY(method.invoke(fertilizerData));
					data.add(point);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	@Override
	public List<Object[]> getSingleCharts2(String type, FertilizerData vo) {
		List<FertilizerData> queryDatas = fertilizerDao.queryDatasByVo(vo);

		List<Object[]> data = null;
		if (queryDatas != null && queryDatas.size() > 0) {
			data = new ArrayList<Object[]>();
			try {
				Method method = FertilizerData.class.getMethod("get" + CommonUtils.firstToUpperCase(type));
				for (FertilizerData fertilizerData : queryDatas) {
					Object value = method.invoke(fertilizerData);
					if (value == null) {
						continue;
					}
					Date time = fertilizerData.getTime();
					data.add(new Object[] { time, value });
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	// @Override
	// public List<Map<Date, Object>> getSingleCharts2(String type,
	// FertilizerData vo) {
	// List<FertilizerData> queryDatas = fertilizerDao.queryDatasByVo(vo);
	//
	// List<Map<Date,Object>> data = null;
	// if(queryDatas != null && queryDatas.size()>0){
	// data = new ArrayList<Map<Date,Object>>();
	// try {
	// Method method =
	// FertilizerData.class.getMethod("get"+CommonUtils.firstToUpperCase(type));
	// for (FertilizerData fertilizerData : queryDatas) {
	// Map<Date,Object> map = new HashMap<Date,Object>();
	// Object object = method.invoke(fertilizerData);
	// map.put(fertilizerData.getTime(), object);
	// data.add(map);
	// }
	// } catch (NoSuchMethodException e) {
	// e.printStackTrace();
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	// }
	// return data;
	// }

	@Override
	public ManualControlPojo getManualControlData(int fertilizerId) throws IOException {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			return getManualControlData(fertilizer.getDtuCode());
		}
		return null;
	}

	@Override
	public ManualControlPojo getManualControlData(String dtuCode) throws IOException {
		ManualControlPojo manualControlData = null;
		manualControlData = FertilizerUtils.readManualControl(dtuCode);
		return manualControlData;
	}

	@Override
	public ProgramControlPojo getProgramControlData(Integer fertilizerId) {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			return getProgramControlData(fertilizer.getDtuCode());
		}
		return null;
	}

	@Override
	public ProgramControlPojo getProgramControlData(String dtuCode) {
		ProgramControlPojo readProgramControl = null;
		try {
			readProgramControl = FertilizerUtils.readProgramControl(dtuCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readProgramControl;
	}

	@Override
	public String OpenOrCloseValves(Integer fertilizerId, List<ValveValue> valveValues) throws IOException {
		String msg = null;
		// 1.获取现在的阀的状态,
		ManualControlPojo manualControlData = getManualControlData(fertilizerId);
		if (manualControlData == null) {
			msg = "该用户下没有设置可远程控制的施肥机";
		}
		// 2.组织阀控数据
		try {
			for (ValveValue valveValue : valveValues) {
				Field declaredField = ManualControlPojo.class.getDeclaredField("irrigValve" + valveValue.getValveNum());
				declaredField.setAccessible(true);
				declaredField.set(manualControlData, valveValue.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3,发送数据
		FertilizerUtils.writeManualControl(manualControlData);
		return msg;
	}

	@Override
	public String OpenOrCloseValve(Integer fertilizerId, ValveValue valveValue) throws IOException {
		String msg = null;
		// 1.获取现在的阀的状态,
		ManualControlPojo manualControlData = getManualControlData(fertilizerId);
		if (manualControlData == null) {
			msg = "该用户下没有设置可远程控制的施肥机";
		}
		// 2.组织阀控数据
		try {
			Field declaredField = ManualControlPojo.class.getDeclaredField("irrigValve" + valveValue.getValveNum());
			declaredField.setAccessible(true);
			declaredField.set(manualControlData, valveValue.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3,发送数据
		FertilizerUtils.writeManualControl(manualControlData);
		return msg;
	}

	@Override
	public String setManualControl(ManualControlPojo manualControlData, int valveNum, int value) throws IOException {
		boolean flag = false;// 标志着是否找到当前阀所对应的程序,
		String msg = null;
		ProgramControlPojo programControlData = new ProgramControlPojo();
		programControlData.setManualOrProgram(1);
		// 把阀名 转成 阀号
		// 获取施肥机的dtu
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(manualControlData.getFertilizerId());
		String dtuCode = fertilizer.getDtuCode();
		programControlData.setDtuCode(dtuCode);
		manualControlData.setDtuCode(fertilizer.getDtuCode());

		if (value == 1) {
			// 获取施肥机的pvm设置
			ProgramValveMapping pvm = FertilizerUtils.getProgramValveMapping(dtuCode);
			// 遍历pvm设置,找到当前阀的程序名
			Field[] pvmFields = ProgramValveMapping.class.getDeclaredFields();
			try {

				// 遍历所有程序号所对应的 阀组
				// 寻找阀所在的程序号,若没找到对应的程序,返回提示信息"无对应程序"
				for (Field pvmField$ : pvmFields) {
					pvmField$.setAccessible(true);
					// 获取一个程序所对应的阀组
					byte[] pvmValue$ = (byte[]) pvmField$.get(pvm);
					// 遍历阀组,寻找是否有这个阀,
					for (byte valve$ : pvmValue$) {
						// 如果找到了该阀的程序号,则下发程序号
						if (valve$ == (byte) valveNum) {
							// 设置手动控制程序,dtu,启动的程序号
							programControlData.setManualOrProgram(1);
							Field program$ = ProgramControlPojo.class.getDeclaredField(pvmField$.getName());
							program$.setAccessible(true);
							program$.set(programControlData, 1);
							flag = true;
							// System.out.println(field2.getName());
							break;
						}
					}
					if (flag) {// 如果找到了,就退出整个循环
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (flag) {
				// 向plc发送程序数据
				FertilizerUtils.writeProgramControl(programControlData);
				// 向plc发送阀数据
				FertilizerUtils.writeManualControl(manualControlData);
				// 开启一个线程,开启手动模式,立即开始执行检查Tzgk,
				// ThreadUtils.execute((StartManualMode)ac.getBean("manualMode"));
			} else {
				// TODO 测试时放开注解
				FertilizerUtils.writeProgramControl(programControlData);
				FertilizerUtils.writeManualControl(manualControlData);
				msg = "当前阀无对应程序";
			}
		} else {// 如果点击的阀的值为0,表示关闭阀,即关闭程序和阀
			// 向plc发送阀数据(发数据是关所有)
			FertilizerUtils.writeManualControl(manualControlData);
			// 开启一个线程,开启手动模式,立即开始执行检查Tzgk,
			// ThreadUtils.execute((StartManualMode)ac.getBean("manualMode"));
			// 向plc发送程序数据(程序是关所有)
			FertilizerUtils.writeProgramControl(programControlData);
		}
		return msg;
	}

	@Override
	public void setProgramControl(ProgramControlPojo programControlData) throws IOException {
		String dtuCode = fertilizerDao.selectByFertilizerId(programControlData.getFertilizerId()).getDtuCode();
		// 开启程序模式,执行plc检查,
		// ThreadUtils.execute((StartProgramMode)ac.getBean("programMode"));
		// 获取pvm值,查找当前程序是否有阀控,如果没有阀控,则不执行当前控制,返回提示"当前程序无对应阀,请设置程序"
		ProgramValveMapping pvm = FertilizerUtils.getProgramValveMapping(dtuCode);
		// 设置程序控制
		programControlData.setDtuCode(dtuCode);
		programControlData.setManualOrProgram(0);
		FertilizerUtils.writeProgramControl(programControlData);
	}

	@Override
	public String setProgramControl(Integer fertilizerId, Integer programNum, Integer value) throws IOException {
		String msg = null;
		String dtuCode = fertilizerDao.selectByFertilizerId(fertilizerId).getDtuCode();
		try {

			if (value == 1) {// 如果是开启程序,则检查有没有对应的阀控
				// 获取pvm值,查找当前程序是否有阀控,如果没有阀控,则不执行当前控制,返回提示"当前程序无对应阀,请设置程序"
				ProgramValveMapping pvm = FertilizerUtils.getProgramValveMapping(dtuCode);
				Field declaredField = ProgramValveMapping.class.getDeclaredField("program" + programNum);
				declaredField.setAccessible(true);
				byte[] valves = (byte[]) declaredField.get(pvm);
				boolean flag = false;
				for (byte b : valves) {
					if (b != 0) {
						flag = true;
						break;
					}
				}
				if (flag) {
					// 设置程序控制
					FertilizerUtils.writeProgramControl(dtuCode, programNum, value, 0);
				} else {
					// TODO 测试时放开注解
					FertilizerUtils.writeProgramControl(dtuCode, programNum, value, 0);
					msg = "当前选定程序无对应阀,请设置程序!";
				}
			} else if (value == 0) {// 如果是关闭程序,则直接执行
				FertilizerUtils.writeProgramControl(dtuCode, programNum, value, 0);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public Climatic getClimaticByUserName(String userName) {
		TclimaticData data = tclimaticDao.selectNewestByUserName(userName);
		TtempAndHum tAndh = tclimaticDao.selectNewestTempAndHumByUserName(userName);
		Climatic climatic = new Climatic();
		climatic.setTemperature(Double.parseDouble(data.getTemperature()));
		climatic.setHumidity(Double.parseDouble(data.getHumidity()));
		climatic.setLighting(null);
		climatic.setPressure(Double.parseDouble(data.getPressure()));
		climatic.setWindSpeed(Double.parseDouble(data.getWindSpeed()));
		climatic.setWindDirection(data.getWindDirection());
		climatic.setRainFall(Double.parseDouble(data.getRainFall()));
		climatic.setSoilTemperature(tAndh.getSoilTemperature());
		climatic.setSoilHumidity(tAndh.getSoilHumidity());
		return climatic;
	}

	@Override
	public List<ChartsPoint> getClimaticChartsPoints(String type, Climatic vo) {

		List<ChartsPoint> chartsPoints = new ArrayList<ChartsPoint>();
		try {
			if (type.equalsIgnoreCase("soilTemperature") || type.equalsIgnoreCase("soilHumidity")) {
				List<TtempAndHum> ttempAndHums = tclimaticDao.queryTAndHByVo(vo);
				Field field = TtempAndHum.class.getDeclaredField(type);
				field.setAccessible(true);
				for (TtempAndHum data : ttempAndHums) {
					ChartsPoint point = new ChartsPoint();
					point.setX(data.getTime());
					point.setY(Float.parseFloat(field.get(data).toString()));
					chartsPoints.add(point);
				}
			} else {
				vo.setType(CommonUtils.firstToUpperCase(type));
				List<TclimaticData> queryClimaticByVo = tclimaticDao.queryClimaticByVo(vo);
				Field field = TclimaticData.class.getDeclaredField(type);
				field.setAccessible(true);
				for (TclimaticData tclimaticData : queryClimaticByVo) {
					String value = (String) field.get(tclimaticData);
					ChartsPoint point = new ChartsPoint();
					point.setX(tclimaticData.getTime());
					point.setY(Float.parseFloat(value));
					chartsPoints.add(point);
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return chartsPoints;
	}

	@Override
	public List<Integer> getValveData(Integer fertilizerId) {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			return getValveData(fertilizer.getDtuCode());
		}
		return null;
	}

	@Override
	public List<Integer> getValveData(String dtuCode) {
		List<Integer> valves = null;
		try {
			valves = FertilizerUtils.readValves(dtuCode);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (DisconnectException e) {
			fertilizerDao.setOffline(dtuCode);
			return null;
		}
		return valves;
	}

	@Override
	public List<Integer> getProgramData(Integer fertilizerId) {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			return getProgramData(fertilizer.getDtuCode());
		}
		return null;
	}

	@Override
	public List<Integer> getProgramData2(Integer fertilizerId) {
		List<Integer> programs = new ArrayList<Integer>();

		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			try {
				int currentProgram = FertilizerUtils.readCurrentProgram(fertilizer.getDtuCode());
				for (int i = 0; i < 16; i++) {
					programs.add(0);
				}
				if (currentProgram > 0 && currentProgram <= programs.size()) {
					programs.set(currentProgram - 1, 1);
				}
				// System.out.println(currentProgram);
				// System.out.println(programs);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return programs;
		}
		return null;
	}

	@Override
	public List<Integer> getProgramData(String dtuCode) {
		List<Integer> programs = null;
		try {
			programs = FertilizerUtils.readPrograms(dtuCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return programs;
	}

	@Override
	public SoilMoisture getSoilMoisture(String username) {
		SoilMoisture sm = new SoilMoisture();
		//对于浩源门头沟(hymtg)用户单独处理,他用的土壤湿度是施肥机上接过来的
		if("hymtg".equals(username)){
			FertilizerData fd = fertilizerDao.getNewestDataByUserName(username);
			TsoilTempAndHum tah = new TsoilTempAndHum();
			tah.setSiteNum(1);
			tah.setLayerNum(1);
			tah.setHumidity(fd.getSoilHumidity1()*1.0);
			
			sm.addSoilTempAndHum(tah);
		}else{
			List<TsoilTempAndHum> tahs = tsoilMoistureDao.queryNewestSoilMoisture(username);
			if (tahs.size() > 0) {
				for (TsoilTempAndHum tah : tahs) {
					sm.addSoilTempAndHum(tah);
				}
			} else {
				sm.addNull();
			}
		}
		return sm;
	}

	@Override
	public IrrigationProgram getProgramByIdAndNum(Integer fertilizerId, Integer programNum) throws IOException {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			return getProgramInfo(fertilizer.getDtuCode(), programNum);
		}
		return null;
	}

	public IrrigationProgram getProgramInfo(String dtuCode, Integer programNum) throws IOException {
		IrrigationProgram program = null;
		program = FertilizerUtils.getProgramInfo(dtuCode, programNum);
		return program;
	}

	@Override
	public void setProgram(Integer fertilizerId, IrrigationProgram irrigationProgram) throws IOException {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			setProgramInfo(fertilizer.getDtuCode(), irrigationProgram);
		}
	}

	private void setProgramInfo(String dtuCode, IrrigationProgram irrigationProgram) throws IOException {
		FertilizerUtils.setProgramInfo(dtuCode, irrigationProgram);
	}

	@Override
	public IrrigationFormula getFormulaByIdAndNum(Integer fertilizerId, Integer formulaNum) throws IOException {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			return getFormulaInfo(fertilizer.getDtuCode(), formulaNum);
		}
		return null;
	}

	private IrrigationFormula getFormulaInfo(String dtuCode, Integer formulaNum) throws IOException {
		IrrigationFormula formula = null;
		formula = FertilizerUtils.getFormulaInfo(dtuCode, formulaNum);
		return formula;
	}

	@Override
	public void setFormula(Integer fertilizerId, IrrigationFormula irrigationFormula) throws IOException {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);
		if (null != fertilizer && null != fertilizer.getDtuCode()) {
			setFormulaInfo(fertilizer.getDtuCode(), irrigationFormula);
		}
	}

	private void setFormulaInfo(String dtuCode, IrrigationFormula irrigationFormula) throws IOException {
		FertilizerUtils.setFormulaInfo(dtuCode, irrigationFormula);
	}

	@Override
	public List<Fertilizer> queryAllHaveDtuAndOnline() {
		return fertilizerDao.queryAllHaveDtuAndOnline();
	}

	@Override
	public List<Tvalve> queryTvalvesByDtuCode(String dtuCode) {

		return tvalveDao.queryTvalvesByDtuCode(dtuCode);
	}

	@Override
	public Tvalve queryTvalveByDtuCodeAndNumber(String dtuCode, int valveNumber) {
		return tvalveDao.queryTvalveByDtuCodeAndNumber(dtuCode, valveNumber);
	}

	@Override
	public void insertTimeDataAndValveStatus(String dtuCode, Map<Integer, Integer> valveStatus) {
		// TODO 先查询出实时数据,再把阀的状态存储起来
		FertilizerDataCollector timeData = getTimeData(dtuCode);
		int timeDataId = addData(timeData);
		Fertilizer fertilizer = selectByDtuCode(dtuCode);
		List<ValveData> dataList = new ArrayList<ValveData>();
		for (Map.Entry<Integer, Integer> entry : valveStatus.entrySet()) {
			ValveData data = new ValveData();
			data.setFertilizerId(fertilizer.getFertilizerId());
			data.setFertilizerDataId(timeDataId);
			data.setNumber(entry.getKey());
			data.setValue(entry.getValue());
			dataList.add(data);
		}
		if (!CollectionUtils.isEmpty(dataList)) {
			valveDao.insertValveDataList(dataList);
		}
	}

	@Override
	public List<ValveDataVo> getValveRecord(Integer fertilizerId) {
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		Date start = calendar.getTime();
		return getValveRecord(fertilizerId, start, end);
	}

	@Override
	public List<ValveDataVo> getValveRecord(Integer fertilizerId, Date start, Date end) {
		// List<ValveData> valveDatas = valveDao.queryByPeriod(fertilizerId,
		// start, end);
		List<ValveDataVo> valveDataVos = valveDao.queryVoByPeriod(fertilizerId, start, end);
		// System.out.println(valveDatas);
		// System.out.println(valveDataVos);
		return valveDataVos;
	}

	@Override
	public PumpState getPumpStatus(Integer fertilizerId) throws IOException {
		Fertilizer fertilizer = fertilizerDao.selectByFertilizerId(fertilizerId);

		PumpState pumpStatus = FertilizerUtils.getPumpStatus(fertilizer.getDtuCode());
		return pumpStatus;

	}

	@Override
	public List<ValveStatistics> getIrrigationStatistics(Integer fertilizerId, Integer valveNum, Date start, Date end) {
		if (end == null)
			end = new Date();
		if (start == null)
			start = new Date(end.getTime() - 1000 * 3600 * 24);
		if (start.getTime() > end.getTime()) {
			Date temp;
			temp = start;
			start = end;
			end = temp;
		}

		// 1, 获取灌溉记录
		List<ValveDataVo> valveStatusList = valveDao.queryVoByVo(fertilizerId, valveNum, start, end);
		// 2, 获取施肥机实时数据
		List<FertilizerData> timeDataList = fertilizerDao.queryDatas(fertilizerId, start, end);
		MapList<Integer, ValveDataVo> mapList = new MapList<>(new Comparator<ValveDataVo>() {
			@Override
			public int compare(ValveDataVo o1, ValveDataVo o2) {
				return o1.getDatetime().getTime() - o2.getDatetime().getTime() >= 0 ? 1 : -1;
			}
		});
		for (ValveDataVo valveDataVo : valveStatusList) {
			mapList.put(valveDataVo.getNumber(), valveDataVo);
		}

		// 3,获取每个阀的灌溉量
		Map<Integer, ValveStatistics> statistics = statistics(timeDataList, mapList);
		ArrayList<ValveStatistics> valvestatisticsList = new ArrayList<>();

		Set<Integer> integers = statistics.keySet();
		for (Integer integer : integers) {
			valvestatisticsList.add(statistics.get(integer));
		}
		Collections.sort(valvestatisticsList, new Comparator<ValveStatistics>() {
			@Override
			public int compare(ValveStatistics o1, ValveStatistics o2) {
				return o1.getValveNum() - o2.getValveNum();
			}
		});

		return valvestatisticsList;
	}

	public Map<Integer, ValveStatistics> statistics(List<FertilizerData> timeDataList, MapList<Integer, ValveDataVo> mapList) {
		Map<Integer, ValveStatistics> map = new HashMap<>();
		for (Integer integer : mapList.keySet()) {
			List<ValveDataVo> valveDataVos = mapList.get(integer);
			if(CollectionUtils.isEmpty(valveDataVos))  continue;
			// 循环时用于记录上次的阀状态信息
			ValveDataVo lastValve = null;
			Date start = valveDataVos.get(0).getDatetime();
			Date end = valveDataVos.get(valveDataVos.size()-1).getDatetime();
			
			for (int i = 0; i < valveDataVos.size(); i++) {
				
				ValveDataVo thisValve = valveDataVos.get(i);
				int value = thisValve.getValue();
				int valveNumber = thisValve.getNumber();

				/**
				 * 如果当前阀是第一个阀,且当前阀状态是关 或当前阀和上一个阀是同一个阀,且不是最后一个阀,且状态一样, 则跳过循环
				 */
				if ((lastValve == null && value == 0) || (lastValve != null && valveNumber == lastValve.getNumber()
						&& i < valveDataVos.size() - 1 && value == lastValve.getValue())) {
					continue;
				}
				// 如果当前阀和上一个阀不是同一个阀
				if (lastValve != null && valveNumber != lastValve.getNumber()) {
					// 如果上一个阀的状态是开,需要给上一个阀补一个记录
					if (lastValve.getValue() == 1) {
						List<IrriRecord> irriRecords = map.get(lastValve.getNumber()).getIrriRecords();
						IrriRecord irriRecord = irriRecords.get(irriRecords.size() - 1);
						irriRecord.setEnd(end);
						double irrigationVolume = getIrrigationVolume(timeDataList, lastValve.getDatetime(), end,
								lastValve.getCount());
						irriRecord.setIrrigationVolume(irrigationVolume);
					}
					if (value == 0) {
						continue;
					}
				}

				// 如果没有统计过,则创建一个统计信息
				if (!map.containsKey(valveNumber)) {
					ValveStatistics valveStatistics = new ValveStatistics(valveNumber);
					map.put(valveNumber, valveStatistics);
				}
				ValveStatistics valveStatistics = map.get(valveNumber);
				// 如果阀的状态是开,新建一个记录
				if (value == 1) {
					IrriRecord irriRecord = new IrriRecord(thisValve.getDatetime());
					valveStatistics.getIrriRecords().add(irriRecord);
				} else {
					List<IrriRecord> irriRecords = valveStatistics.getIrriRecords();
					IrriRecord irriRecord = irriRecords.get(irriRecords.size() - 1);
					irriRecord.setEnd(thisValve.getDatetime());
					double irrigationVolume = getIrrigationVolume(timeDataList, lastValve.getDatetime(),
							thisValve.getDatetime(), thisValve.getCount());
					irriRecord.setIrrigationVolume(irrigationVolume);
				}

				// 如果当前阀是最后一个阀,且当前阀的状态是开,需要给自己补一个关的记录
				if (i == valveDataVos.size() - 1 && value == 1) {
					List<IrriRecord> irriRecords = map.get(valveNumber).getIrriRecords();
					IrriRecord irriRecord = irriRecords.get(irriRecords.size() - 1);
					irriRecord.setEnd(end);
					double irrigationVolume = getIrrigationVolume(timeDataList, lastValve.getDatetime(), end,
							thisValve.getCount());
					irriRecord.setIrrigationVolume(irrigationVolume);
				}

				lastValve = thisValve;

			}
		}

		return map;

	}

	private double getIrrigationVolume(List<FertilizerData> timeDataList, Date start, Date end, int count) {

		if (start.getTime() > end.getTime()) {
			Date d = start;
			start = end;
			end = d;
		}
		int c = 0;
		double sum = 0;
		double irrigationVolume = 0;
		for (int i = 0; i < timeDataList.size(); i++) {
			FertilizerData fertilizerData = timeDataList.get(i);
			long time = fertilizerData.getTime().getTime();
			if (time >= start.getTime() && time <= end.getTime()) {
				sum += fertilizerData.getRateFlow();
				c++;
			}

		}
		if (c != 0) {
			irrigationVolume = sum / c / count * (end.getTime() - start.getTime()) / 1000 / 3600;
		}
		return irrigationVolume;
	}

}
