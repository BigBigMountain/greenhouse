package com.lyyh.fertilizer.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.lyyh.fertilizer.pojo.vo.ValveStatistics;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.lyyh.fertilizer.pojo.PumpState;
import com.lyyh.fertilizer.pojo.SoilMoisture;
import com.lyyh.fertilizer.pojo.ValveDataVo;
import com.lyyh.fertilizer.pojo.ValveValue;
import com.lyyh.fertilizer.pojo.vo.ValveValueVo;
import com.lyyh.fertilizer.service.FertilizerService;
import com.lyyh.fertilizer.service.UserService;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.util.ResultInfo;

@Controller
@RequestMapping(value = "/fertilizer")
public class FertilizerController {

	@Autowired
	private FertilizerService fertilizerService;

	@Autowired
	private UserService userService;

	// 校验用户
	public boolean verifyUser(User user) {
		if (null != user.getUsername() && null != user.getPassword()) {
			User user2 = userService.findByUsername(user.getUsername());
			if (null != user2) {
				if (user2.getPassword().equals(user.getPassword())) {
					return true;
				}
			}
		}
		return false;

	}

	@RequestMapping(value = "/timeData.do")
	public String timeData(HttpSession session, Model model, String dtuCode, Integer fertilizerId) {
		// 添加fertilizer列表到model中
		// 从session中获取fertilizer列表,如果没有,数据库查询并放入session中
		List<Fertilizer> fertilizers = (List<Fertilizer>) session.getAttribute("fertilizers");
		if (fertilizers == null) {
			User user = (User) session.getAttribute("loginUser");
			fertilizers = fertilizerService.queryAllByUid(user.getUserId());
			session.setAttribute("fertilizers", fertilizers);
		}
		model.addAttribute("fertilizers", fertilizers);
		// 添加dtuCode到model中

		if (fertilizerId == null) {
			if (null != fertilizers && fertilizers.size() > 0) {
				fertilizerId = fertilizers.get(0).getFertilizerId();
			}
		}
		model.addAttribute("fertilizerId", fertilizerId);
		for (Fertilizer fertilizer : fertilizers) {
			// System.out.println(fertilizer);
			if (fertilizer.getFertilizerId() == fertilizerId) {
				dtuCode = fertilizer.getDtuCode();
			}
		}
		// 添加实时数据到model中
		FertilizerDataCollector timeData = null;
		if (null != dtuCode) {
			timeData = fertilizerService.getTimeData(dtuCode);
		} else {
			model.addAttribute("msg", "该设备未绑定dtu,无法获取数据");
		}
		model.addAttribute("timeData", timeData);
		return "timeData/timeData";
	}

	@RequestMapping("/appGetFertilizerList.do")
	public @ResponseBody Map<String, Object> appGetFertilizerList(User user) {
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		json.put("code", "200");
		json.put("data", "");
		if (verifyUser(user)) {
			List<Fertilizer> fertilizers = fertilizerService.queryAllByUsername(user.getUsername());
			if (null != fertilizers && fertilizers.size() > 0) {
				json.put("code", "100");
				data.put("fertilizers", fertilizers);
				json.put("data", data);
				json.put("msg", "");
			} else {
				json.put("msg", "该用户未添加施肥机,请联系管理员!");
			}
		} else {
			json.put("msg", "用户名或密码错误");
		}
		return json;
	}

	@RequestMapping(value = "/appTimeData.do")
	public @ResponseBody Map<String, Object> appTimeData(User user, Integer fertilizerId) {
//		System.out.println("--appTimeData.do--");
		Map<String, Object> json = null;
		// 校验用户
		if (verifyUser(user)) {
			String code = "100";
			String msg = "";
			Map<String, Object> data = new HashMap<String, Object>();

			json = new HashMap<String, Object>();

			// 查询该用户下所有施肥机列表
			List<Fertilizer> fertilizers = fertilizerService.queryAllByUsername(user.getUsername());
			if (fertilizers == null || fertilizers.size() == 0) {
				msg = "您还没有添加施肥机";
				json.put("msg", msg);
				json.put("code", code);
				json.put("data", data);
				return json;
			}
			data.put("fertilizers", fertilizers);
			// 默认查询第一个施肥机
			if (fertilizerId == null) {
				if (null != fertilizers && fertilizers.size() > 0) {
					fertilizerId = fertilizers.get(0).getFertilizerId();
				}
			}
			Fertilizer fertilizer = fertilizerService.selectByFertilizerId(fertilizerId);
			String fertilizerName = fertilizer.getFertilizerName();
			Integer isOnline = fertilizer.getIsOnline();
			data.put("fertilizerId", fertilizerId);
			data.put("fertilizerName", fertilizerName);
			data.put("isOnline", isOnline);

			String dtuCode = fertilizer.getDtuCode();
			// 查询实时数据

			if (dtuCode == null || dtuCode == "") {
				msg = "该施肥机无法联网,没有安装dtu";
				json.put("msg", msg);
				json.put("code", code);
				json.put("data", data);
				return json;
			}
			if (fertilizer.getIsOnline() != 1) {
				msg = "该施肥机离线,请检查后再查询";
				json.put("msg", msg);
				json.put("code", code);
				json.put("data", data);
				return json;
			}
			FertilizerDataCollector timeData = fertilizerService.getTimeData(dtuCode);
			if (timeData == null) {
				data.put("timeData", "");
			} else {
				code = "200";
				data.put("timeData", timeData);
			}
			json.put("msg", msg);
			json.put("code", code);
			json.put("data", data);
		}
		return json;

	}

	@RequestMapping("indexCharts.do")
	public @ResponseBody FertilizerDataCharts indexCharts(Integer fertilizerId) {
		// FertilizerDataCharts chartsData = new FertilizerDataCharts();
		FertilizerData vo = new FertilizerData();
		Date end = new Date();
		Date start = new Date(end.getTime() - 1000 * 3600 * 12);
		vo.setStart(start);
		vo.setEnd(end);
		vo.setFertilizerId(fertilizerId);
		return fertilizerService.getDataCharts(vo);
	}

	@RequestMapping("appChangeCharts.do")
	public @ResponseBody Map<String, Object> appChangeCharts(User user, Integer fertilizerId, String type, Date start,
			Date end) {
//		System.out.println("====");
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "100";
			List<ChartsPoint> points = null;
			Map<String, List<ChartsPoint>> data = new HashMap<String, List<ChartsPoint>>();

			json = new HashMap<String, Object>();
			json.put("code", code);
			code = "200";
			json.put("code", code);
			if (end == null) {
				end = new Date();
			}
			if (start == null) {
				start = new Date(end.getTime() - 1000 * 3600 * 24);
			}
			FertilizerData vo = new FertilizerData();
			vo.setStart(start);
			vo.setEnd(end);
			vo.setFertilizerId(fertilizerId);
			// type = type.toLowerCase();
			points = fertilizerService.getChartsPoints(type, vo);
			if (points == null || points.size() == 0) {
				json.put("data", "");
				json.put("msg", "暂无数据");
			} else {
				json.put("msg", "");
				data.put("points", points);
				json.put("data", data);
			}
		}
		return json;
	}

	@RequestMapping("appChangeCharts2.do")
	public @ResponseBody Map<String, Object> appChangeCharts(User user, Integer fertilizerId, String type, String start,
			String end) {
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "100";
			List<ChartsPoint> points = null;
			Map<String, List<ChartsPoint>> data = new HashMap<String, List<ChartsPoint>>();

			json = new HashMap<String, Object>();
			json.put("code", code);
			code = "200";
			json.put("code", code);
			Date endTime = null;
			Date startTime = null;
			if (StringUtils.isBlank(end)) {
				endTime = new Date();
			} else {
				endTime = new Date(Long.parseLong(end));
			}
			if (StringUtils.isBlank(start)) {
				startTime = new Date(endTime.getTime() - 1000 * 3600 * 24);
			} else {
				startTime = new Date(Long.parseLong(start));
			}
			FertilizerData vo = new FertilizerData();
			vo.setStart(startTime);
			vo.setEnd(endTime);
			vo.setFertilizerId(fertilizerId);
			// type = type.toLowerCase();
			points = fertilizerService.getChartsPoints(type, vo);
			if (points == null || points.size() == 0) {
				json.put("data", "");
				json.put("msg", "暂无数据");
			} else {
				json.put("msg", "");
				data.put("points", points);
				json.put("data", data);
			}
		}
		return json;
	}

	@RequestMapping("changeCharts.do")
	public @ResponseBody Map<String, List> changeCharts(Integer fertilizerId, String type, Date start, Date end) {
		if (end == null) {
			end = new Date();
		}
		if (start == null) {
			start = new Date(end.getTime() - 1000 * 3600 * 24);
		}
		FertilizerData vo = new FertilizerData();
		vo.setStart(start);
		vo.setEnd(end);
		vo.setFertilizerId(fertilizerId);
		return fertilizerService.getSingleCharts(type, vo);
	}

	@RequestMapping("changeCharts2.do")
	public @ResponseBody List<Object[]> changeCharts2(Integer fertilizerId, String type, Date start, Date end) {
		if (end == null) {
			end = new Date();
		}
		if (start == null) {
			start = new Date(end.getTime() - 1000 * 3600 * 24);
		}
		FertilizerData vo = new FertilizerData();
		vo.setStart(start);
		vo.setEnd(end);
		vo.setFertilizerId(fertilizerId);
		return fertilizerService.getSingleCharts2(type, vo);
	}

	@RequestMapping("historyData.do")
	public String historyData(HttpSession session, Model model, String dtuCode, Integer fertilizerId) {
		// List<Fertilizer> fertilizers = (List<Fertilizer>)
		// session.getAttribute("fertilizers");
		// if (fertilizers == null) {
		// User user = (User) session.getAttribute("loginUser");
		// fertilizers = fertilizerService.queryAllByUid(user.getUserId());
		// session.setAttribute("fertilizers", fertilizers);
		// }
		List<Fertilizer> fertilizers = getFertilizers(session);
		model.addAttribute("fertilizers", fertilizers);
		if (dtuCode == null) {
			if (null != fertilizers && fertilizers.size() > 0) {
				dtuCode = fertilizers.get(0).getDtuCode();
			}
		}
		if (fertilizerId == null) {
			if (null != fertilizers && fertilizers.size() > 0) {
				fertilizerId = fertilizers.get(0).getFertilizerId();
			}
		}
		model.addAttribute("dtuCode", dtuCode);
		model.addAttribute("fertilizerId", fertilizerId);
		return "historyData/historyData";
	}

	@RequestMapping("/toControl.do")
	public String toControl(HttpSession session, Model model, Integer fertilizerId) {
		// TODO 去控制界面
		// 从session中获取fertilizer列表,如果没有,数据库查询并放入session中
		List<Fertilizer> fertilizers = getFertilizers(session);
		model.addAttribute("fertilizers", fertilizers);

		// 添加fertilizerId到model中
		if (null != fertilizers && fertilizers.size() > 0) {
			if (fertilizerId == null) {
				fertilizerId = fertilizers.get(0).getFertilizerId();
			}
			model.addAttribute("fertilizerId", fertilizerId);

			// 获取阀状态
			List<Integer> irrigValves = fertilizerService.getValveData(fertilizerId);
			// System.out.println("irrigValves: " + irrigValves);
			model.addAttribute("irrigValves", irrigValves);

			// 获取程序状态
			List<Integer> programs = fertilizerService.getProgramData2(fertilizerId);
			// System.out.println(programs);
			model.addAttribute("programs", programs);
		}
		return "control/control";
	}

	// TODO
	//
	// @RequestMapping("/appToControl.do")
	// public @ResponseBody Map<String, Object> appToControl_bak(User user,
	// Integer fertilizerId) {
	// Map<String, Object> json = null;
	// if (verifyUser(user)) {
	// String code = "200";
	// String
	// msg = ""; Map<String, Object> data = new HashMap<String, Object>(); json
	// = new HashMap<String, Object>();
	//
	// List<Fertilizer> fertilizers =
	// fertilizerService.queryAllByUsername(user.getUsername()); //
	// 添加fertilizerId到model中
	// if (null != fertilizers && fertilizers.size() > 0)
	// { if (fertilizerId == null) { fertilizerId =
	// fertilizers.get(0).getFertilizerId(); } // 添加plc输出线圈状态值
	// List<Integer>
	// programs = fertilizerService.getValveData(fertilizerId);
	// ProgramControlPojo programControlData =
	// fertilizerService.getProgramControlData(fertilizerId); if
	// (programControlData == null) { code = "100"; msg += "暂无程序数据,";
	// data.put("programs", ""); } else { List<Integer> programs = new
	// ArrayList<Integer>(); for (int i = 1; i <= 16; i++) { try { Field field =
	// ProgramControlPojo.class.getDeclaredField("program" + i);
	// field.setAccessible(true); Integer value = (Integer)
	// field.get(programControlData); programs.add(value); } catch (Exception e)
	// { e.printStackTrace(); } } data.put("programs", programs); }
	// if (manualControlData == null) { code = "100"; msg += "暂无阀控数据,";
	// data.put("irrigValves", ""); } else { List<Integer> irrigValves = new
	// ArrayList<Integer>(); for (int i = 1; i <= 64; i++) { try { Field field =
	// ManualControlPojo.class.getDeclaredField("irrigValve" + i);
	// field.setAccessible(true); Integer value = (Integer)
	// field.get(manualControlData); irrigValves.add(value); } catch (Exception
	// e) { e.printStackTrace(); } } data.put("irrigValves", irrigValves); }
	// Fertilizer fertilizer =
	// fertilizerService.selectByFertilizerId(fertilizerId); String
	// fertilizerName = fertilizer.getFertilizerName(); Integer isOnline =
	// fertilizer.getIsOnline(); data.put("fertilizerId", fertilizerId);
	// data.put("fertilizerName", fertilizerName); data.put("isOnline",
	// isOnline);
	//
	// data.put("fertilizers", fertilizers);
	//
	// } else { code = "100"; msg = "该用户还未添加施肥机"; data.put("programs", "");
	// data.put("irrigValves", ""); data.put("fertilizers", "");
	// data.put("fertilizerId", ""); data.put("fertilizerName", "");
	// data.put("isOnline", ""); } json.put("code", code); json.put("msg", msg);
	// json.put("data", data); } return json; }
	//
	//
	@RequestMapping("/appToControl.do")
	public @ResponseBody Map<String, Object> appToControl(User user, Integer fertilizerId) {
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "100";
			String msg = "";
			Map<String, Object> data = new HashMap<String, Object>();
			json = new HashMap<String, Object>();

			List<Fertilizer> fertilizers = fertilizerService.queryAllByUsername(user.getUsername());

			// 添加fertilizerId到model中
			if (null != fertilizers && fertilizers.size() > 0) {
				if (fertilizerId == null) {
					fertilizerId = fertilizers.get(0).getFertilizerId();
				}

				List<Integer> programs = fertilizerService.getProgramData2(fertilizerId);

				List<Integer> irrigValves = fertilizerService.getValveData(fertilizerId);

				// 添加plc输出线圈状态值
				// ManualControlPojo manualControlData =
				// fertilizerService.getManualControlData(fertilizerId);
				// ProgramControlPojo programControlData =
				// fertilizerService.getProgramControlData(fertilizerId);
				if (programs == null) {
					msg += "暂无程序数据,";
					data.put("programs", "");
					code = "100";
				} else {
					data.put("programs", programs);
					code = "200";
				}
				if (irrigValves == null) {
					code = "100";
					msg += "暂无阀控数据,";
					data.put("irrigValves", "");
				} else {
					code = "200";
					data.put("irrigValves", irrigValves);
				}
				Fertilizer fertilizer = fertilizerService.selectByFertilizerId(fertilizerId);
				String fertilizerName = fertilizer.getFertilizerName();
				Integer isOnline = fertilizer.getIsOnline();
				data.put("fertilizerId", fertilizerId);
				data.put("fertilizerName", fertilizerName);
				data.put("isOnline", isOnline);

				data.put("fertilizers", fertilizers);

			} else {
				msg = "该用户还未添加施肥机";
				data.put("programs", "");
				data.put("irrigValves", "");
				data.put("fertilizers", "");
				data.put("fertilizerId", "");
				data.put("fertilizerName", "");
				data.put("isOnline", "");
			}
			json.put("code", code);
			json.put("msg", msg);
			json.put("data", data);
		}
		return json;
	}

	@RequestMapping("/ManualControl.do")
	public @ResponseBody Map<String, Object> Manualcontrol(HttpSession session, Model model, Integer fertilizerId,
			Integer valveNum, Integer value) {
		// TODO 手动控制
		Map<String, Object> json = new HashMap<String, Object>();
		ManualControlPojo manualControlData = new ManualControlPojo();
		manualControlData.setFertilizerId(fertilizerId);
		String msg = null;

		User user = (User) session.getAttribute("loginUser");
		String username = (user.getUsername());
		try {
			User user2 = userService.findByUsername(user.getUsername());
			if (user2.getControllType() == 1) {
				ValveValue vv = new ValveValue();
				vv.setValveNum(valveNum);
				vv.setValue(value);
				msg = fertilizerService.OpenOrCloseValve(fertilizerId, vv);
			} else {
				if (value == 1) {
					Field declaredField = ManualControlPojo.class.getDeclaredField("irrigValve" + valveNum);
					declaredField.setAccessible(true);
					declaredField.set(manualControlData, value);
				}
				msg = fertilizerService.setManualControl(manualControlData, valveNum, value);
			}
		} catch (IOException e) {
			msg = "通讯异常,请稍后重试";
			model.addAttribute("msg", msg);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("msg", msg);
		return json;
	}

	@RequestMapping("/appManualControl.do")
	public @ResponseBody Map<String, Object> appManualControl(User user, Integer fertilizerId, Integer valveNum,
			Integer value) {
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			User user2 = userService.findByUsername(user.getUsername());
			if (user2.getControllType() == 1) {
				ValveValue vv = new ValveValue();
				vv.setValveNum(valveNum);
				vv.setValue(value);
				return appOpenOrCloseValve(user, fertilizerId, vv);
			} else {
				String code = "200";
				String msg = "";
				json = new HashMap<String, Object>();
				// TODO app手动控制
				ManualControlPojo manualControlData = new ManualControlPojo();
				manualControlData.setFertilizerId(fertilizerId);
				try {
					if (value == 1) {
						Field declaredField = ManualControlPojo.class.getDeclaredField("irrigValve" + valveNum);
						declaredField.setAccessible(true);
						declaredField.set(manualControlData, value);
					}
					msg = fertilizerService.setManualControl(manualControlData, valveNum, value);
					if (StringUtils.isNotBlank(msg)) {
						// code = "100";
					}
				} catch (IOException e) {
					e.printStackTrace();
					code = "100";
					msg = "通讯异常,请稍后重试";
				} catch (Exception e) {
					e.printStackTrace();
					code = "100";
					msg = "未知错误,请重试";
				}
				json.put("code", code);
				json.put("msg", msg);
				json.put("data", "");
			}
		}
		return json;
	}

	@RequestMapping("/appOpenOrCloseValves.do")
	public @ResponseBody Map<String, Object> appOpenOrCloseValves2(@RequestBody ValveValueVo vo) {
		Map<String, Object> json = null;
		User user = new User();
		user.setUsername(vo.getUsername());
		user.setPassword(vo.getPassword());
		if (verifyUser(user)) {
			String code = "200";
			String msg = "";
			json = new HashMap<String, Object>();

			try {
				msg = fertilizerService.OpenOrCloseValves(vo.getFertilizerId(), vo.getValves());
				if (msg != null) {
					code = "100";
				}
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = "通讯异常,请稍后重试";
			} catch (Exception e) {
				e.printStackTrace();
				code = "100";
				msg = "未知错误,请重试";
			}
			json.put("code", code);
			json.put("msg", msg);
			json.put("data", "");
		}
		return json;
	}

	@RequestMapping("/appOpenOrCloseValve.do")
	public @ResponseBody Map<String, Object> appOpenOrCloseValve(User user, Integer fertilizerId,
			ValveValue valveValue) {
//		System.out.println("=================");
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "200";
			String msg = "";
			json = new HashMap<String, Object>();

			try {
				msg = fertilizerService.OpenOrCloseValve(fertilizerId, valveValue);
				if (msg != null) {
					code = "100";
				}
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = "通讯异常,请稍后重试";
			} catch (Exception e) {
				e.printStackTrace();
				code = "100";
				msg = "未知错误,请重试";
			}
			json.put("code", code);
			json.put("msg", msg);
			json.put("data", "");
		}
		return json;
	}

	@RequestMapping(value = "/programControl.do")
	public @ResponseBody Map<String, Object> programControl(Integer fertilizerId, Integer programNum, Integer value) {
		// TODO 程序控制
		String msg = null;
		// ProgramControlPojo programControlData = new ProgramControlPojo();
		// programControlData.setFertilizerId(fertilizerId);

		Map<String, Object> json = new HashMap<String, Object>();
		try {
			msg = fertilizerService.setProgramControl(fertilizerId, programNum, value);
		} catch (IOException e) {
			msg = "通讯异常,请稍后重试";
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("msg", msg);
		return json;
	}

	@RequestMapping("/appProgramControl.do")
	public @ResponseBody Map<String, Object> appProgramControl(User user, Integer programNum, Integer value,
			Integer fertilizerId) {
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "200";
			String msg = "";
			String data = "";

			json = new HashMap<String, Object>();
			ProgramControlPojo programControlData = new ProgramControlPojo();
			programControlData.setFertilizerId(fertilizerId);
			try {
				/*
				 * if (value == 1) { Field declaredField =
				 * ProgramControlPojo.class.getDeclaredField("program" +
				 * programNum); declaredField.setAccessible(true);
				 * declaredField.set(programControlData, value); }
				 */
				// fertilizerService.setProgramControl(programControlData);
				msg = fertilizerService.setProgramControl(fertilizerId, programNum, value);
				if (msg != null) {
					code = "100";
				}
			} catch (IOException e) {
				msg = "通讯异常,请稍后重试";
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			json.put("code", code);
			json.put("msg", msg);
			json.put("data", data);
		}
		return json;
	}

	@RequestMapping("/appGetClimatic.do")
	public @ResponseBody Map<String, Object> appGetClimatic(User user) {
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "200";
			String msg = "";
			Map<String, Object> data = new HashMap<String, Object>();

			json = new HashMap<String, Object>();
			// 获取气象对象
			Climatic climatic = fertilizerService.getClimaticByUserName(user.getUsername());
			if (climatic == null) {
				data.put("climatic", "");
				msg = "暂无数据";
			} else {
				data.put("climatic", climatic);
			}
			json.put("code", code);
			json.put("msg", msg);
			json.put("data", data);
		}
		return json;
	}

	@RequestMapping("/appChangeClimaticCharts.do")
	public @ResponseBody Map<String, Object> appChangeClimaticCharts(User user, String type, Date start, Date end) {
//		System.out.println("--appChangeClimaticCharts.do--");
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String code = "100";
			List<ChartsPoint> points = null;
			Map<String, Object> data = new HashMap<String, Object>();

			json = new HashMap<String, Object>();
			if (end == null) {
				end = new Date();
			}
			if (start == null) {
				start = new Date(end.getTime() - 1000 * 3600 * 24);
			}
			Climatic vo = new Climatic();
			vo.setStart(start);
			vo.setEnd(end);
			vo.setUsername(user.getUsername());
			points = fertilizerService.getClimaticChartsPoints(type, vo);
			if (points == null || points.size() == 0) {
				json.put("data", "");
				json.put("code", code);
				json.put("msg", "暂无数据");
			} else {
				json.put("msg", "");
				json.put("code", "200");
				data.put("points", points);
				json.put("data", data);
			}
		}
		return json;
	}

	@RequestMapping("/appGetSoilMoisture.do")
	public @ResponseBody Map<String, Object> getSoilMoisture(User user) {
		Map<String, Object> json = null;
		if (verifyUser(user)) {
			String msg = "";
			SoilMoisture sm;
			String code = "200";
			Map<String, Object> data = new HashMap<String, Object>();
			json = new HashMap<String, Object>();

			sm = fertilizerService.getSoilMoisture(user.getUsername());
			json.put("data", sm);
			json.put("code", code);
			json.put("msg", msg);
		}
		return json;
	}

	@RequestMapping("/appGetProgram.do")
	public @ResponseBody Map<String, Object> appGetProgram(User user, Integer fertilizerId, Integer programNum) {
		Map<String, Object> json = new HashMap<String, Object>();
		String code = "100";
		String msg = "";
		Map<String, Object> data = new HashMap<String, Object>();
		if (verifyUser(user)) {
			try {
				IrrigationProgram IrrigationProgram = fertilizerService.getProgramByIdAndNum(fertilizerId, programNum);
				data.put("IrrigationProgram", IrrigationProgram);
				code = "200";
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = e.getMessage();
				if (StringUtils.isBlank(msg)) {
					msg = "通讯异常,请稍后重试";
				}
			}
			json.put("data", data);
			json.put("code", code);
			json.put("msg", msg);
		}
		return json;
	}

	@RequestMapping("/appSetProgram.do")
	public @ResponseBody Map<String, Object> appSetProgram(User user, Integer fertilizerId,
			IrrigationProgram irrigationProgram) {
		Map<String, Object> json = new HashMap<String, Object>();
		String code = "100";
		String msg = "";
		Map<String, Object> data = new HashMap<String, Object>();
		if (verifyUser(user)) {
			try {
				fertilizerService.setProgram(fertilizerId, irrigationProgram);
				code = "200";
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = e.getMessage();
				if (StringUtils.isBlank(msg)) {
					msg = "通讯异常,请稍后重试";
				}
			}
			json.put("data", "");
			json.put("code", code);
			json.put("msg", msg);
		}
		return json;
	}

	@RequestMapping("/appGetIrrigationFormula.do")
	public @ResponseBody Map<String, Object> appGetIrrigationFormula(User user, Integer fertilizerId,
			Integer formulaNum) {
		Map<String, Object> json = new HashMap<String, Object>();
		String code = "100";
		String msg = "";
		Map<String, Object> data = new HashMap<String, Object>();
		if (verifyUser(user)) {
			try {
				IrrigationFormula formula = fertilizerService.getFormulaByIdAndNum(fertilizerId, formulaNum);
				data.put("IrrigationFormula", formula);
				code = "200";
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = e.getMessage();
				if (StringUtils.isBlank(msg)) {
					msg = "通讯异常,请稍后重试";
				}
			}
			json.put("data", data);
			json.put("code", code);
			json.put("msg", msg);
		}
		return json;
	}

	@RequestMapping("/appSetIrrigationFormula.do")
	public @ResponseBody Map<String, Object> appSetIrrigationFormula(User user, Integer fertilizerId,
			IrrigationFormula irrigationFormula) {
		Map<String, Object> json = new HashMap<String, Object>();
		String code = "100";
		String msg = "";
		Map<String, Object> data = new HashMap<String, Object>();
		if (verifyUser(user)) {
			try {
				fertilizerService.setFormula(fertilizerId, irrigationFormula);
				code = "200";
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = e.getMessage();
				if (StringUtils.isBlank(msg)) {
					msg = "通讯异常,请稍后重试";
				}
			}
			json.put("data", "");
			json.put("code", code);
			json.put("msg", msg);
		}
		return json;
	}

	@RequestMapping("/appGetValveRecord.do")
	public @ResponseBody ResultInfo appGetValveRecord(User user, Integer fertilizerId, String start, String end) {
		List<ValveDataVo> valveRecords;

		List<Fertilizer> fertilizers = fertilizerService.queryAllByUsername(user.getUsername());
		if (CollectionUtils.isEmpty(fertilizers)) {
			return ResultInfo.error("当前用户还未添加施肥机");
		} else {
			if (fertilizerId == null || fertilizerId == 0) {
				fertilizerId = fertilizers.get(0).getFertilizerId();
			}

			if (start == null || end == null) {
				valveRecords = fertilizerService.getValveRecord(fertilizerId);
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date s = null;
				Date e = null;
				try {
					s = df.parse(start);
					e = df.parse(end);
				} catch (ParseException e1) {
					e1.printStackTrace();
					return ResultInfo.error("时间格式错误");
				}
				valveRecords = fertilizerService.getValveRecord(fertilizerId, s, e);
			}
			return ResultInfo.success().put("valveRecords", valveRecords);
		}
	}

	@RequestMapping("/getValveRecord.do")
	public String getValveRecord(HttpSession session, Model model, Integer fertilizerId, String start, String end)
			throws ParseException {
		List<ValveDataVo> valveRecords;

		User user = (User) session.getAttribute("loginUser");
		List<Fertilizer> fertilizers = fertilizerService.queryAllByUid(user.getUserId());
		if (CollectionUtils.isEmpty(fertilizers)) {
			model.addAttribute("msg", "当前用户还未添加施肥机");
		} else {
			if (fertilizerId == null || fertilizerId == 0) {
				fertilizerId = fertilizers.get(0).getFertilizerId();
			}

			if (start == null || end == null) {
				valveRecords = fertilizerService.getValveRecord(fertilizerId);
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date s = df.parse(start);
				Date e = df.parse(end);
				valveRecords = fertilizerService.getValveRecord(fertilizerId, s, e);
				model.addAttribute("start", s);
				model.addAttribute("end", e);
			}

			model.addAttribute("valveRecords", valveRecords);
			model.addAttribute("fertilizers", fertilizers);
			model.addAttribute("fertilizerId", fertilizerId);
		}
		return "record/recordList";
	}

	@RequestMapping("/appGetPumpStatus.do")
	public @ResponseBody Map<String, Object> appGetPumpStatus(User user, Integer fertilizerId) {
		Map<String, Object> json = new HashMap<String, Object>();
		String code = "100";
		String msg = "";
		Map<String, Object> data = new HashMap<String, Object>();
		if (verifyUser(user)) {
			try {

				PumpState pumpStatus = fertilizerService.getPumpStatus(fertilizerId);
				data.put("pumpStatus", pumpStatus);
				code = "200";
			} catch (IOException e) {
				e.printStackTrace();
				code = "100";
				msg = e.getMessage();
				if (StringUtils.isBlank(msg)) {
					msg = "通讯异常,请稍后重试";
				}
			}
			json.put("data", data);
			json.put("code", code);
			json.put("msg", msg);
		}
		return json;
	}

	@RequestMapping("/getIrrigationStatistics.do")
	public String getIrrigationStatistics(HttpSession session, Model model, Integer fertilizerId, Integer valveNum,
			String start, String end) {
		List<Fertilizer> fertilizers = getFertilizers(session);
		if (fertilizers == null) {
			model.addAttribute("msg", "当前用户还未添加施肥机");
			return "record/recordList";
		}
		if (fertilizerId == null || fertilizerId == 0) {
			fertilizerId = fertilizers.get(0).getFertilizerId();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s = null, e = null;
		try {
			if (end == null) {
				e = new Date();
			} else {
				e = df.parse(end);
			}
			if (start == null) {
				s = new Date(e.getTime() - 1000 * 3600 * 24);
			} else {
				s = df.parse(start);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List<ValveStatistics> irrigationStatistics = fertilizerService.getIrrigationStatistics(fertilizerId, valveNum,
				s, e);

		model.addAttribute("valveNum", valveNum);
		model.addAttribute("start", s);
		model.addAttribute("end", e);
		model.addAttribute("fertilizerId", fertilizerId);
		model.addAttribute("fertilizers", fertilizers);
		model.addAttribute("irrigationStatistics", irrigationStatistics);

		return "statistics/irrigationStatistics";
	}

	private List<Fertilizer> getFertilizers(HttpSession session) {
		List<Fertilizer> fertilizers = (List<Fertilizer>) session.getAttribute("fertilizers");
		if (fertilizers == null) {
			User user = (User) session.getAttribute("loginUser");
			fertilizers = fertilizerService.queryAllByUid(user.getUserId());
			session.setAttribute("fertilizers", fertilizers);
		}
		return fertilizers;
	}
}
