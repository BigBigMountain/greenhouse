package com.lyyh.greenhouse.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.listenvision.led;
import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.dao.HouseDao;
import com.lyyh.greenhouse.dao.LedDao;
import com.lyyh.greenhouse.dao.SensorDataDao;
import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.House;
import com.lyyh.greenhouse.pojo.LedClimatic;
import com.lyyh.greenhouse.pojo.LedHouse;
import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.pojo.LedTable;
import com.lyyh.greenhouse.pojo.SensorData;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.LedService;
import com.lyyh.greenhouse.threadTask.LedTask;
import com.lyyh.greenhouse.threadTask.LedTaskLinkHouse;
import com.lyyh.greenhouse.util.LedUtils;
import com.lyyh.greenhouse.util.cache.CacheManager;

@Service
@Transactional
public class LedServiceImpl implements LedService {

	@Autowired
	private LedDao ledDao;

	@Autowired
	private ClimaticDao climaticDao;

	@Autowired
	private SensorDataDao sensorDataDao;

	@Autowired
	private HouseDao houseDao;

	// 增
	@Override
	public String add(LedProgram ledProgram, LedTable ledTable, LedHouse ledHouse, LedClimatic ledClimatic,Integer houseId) {
		try {
			ledDao.addLedProgram(ledProgram);

			ledTable.setP_id(ledProgram.getP_id());
			ledDao.addLedTable(ledTable);

			// ledHouse.setP_id(ledProgram.getP_id());
			// ledDao.addLedHouse(ledHouse);

			ledClimatic.setP_id(ledProgram.getP_id());
			ledDao.addLedClimatic(ledClimatic);
			if(houseId != null && houseId != 0){
				ledDao.addProgramLedLink(ledProgram.getP_id(),houseId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "保存失败!" + e.getMessage();
		}
		return "保存成功";
	}

	// 删
	@Override
	public void delProgramByPid(Integer p_id) {
		ledDao.delLedHouseByPid(p_id);
		ledDao.delLedTableByPid(p_id);
		ledDao.delLedClimaticByPid(p_id);
		ledDao.delLedProgramByPid(p_id);

	}

	// 查
	@Override
	public LedProgram findProgramByPid(Integer p_id) {
		LedProgram ledProgram = ledDao.findProgramByPid(p_id);
		return ledProgram;
	}

	@Override
	public LedTable findLedTableByPid(Integer p_id) {
		LedTable ledTable = ledDao.findLedTableByPid(p_id);
		return ledTable;
	}

	@Override
	public LedHouse findLedHouseByPid(Integer P_id) {
		LedHouse ledHouse = ledDao.findLedHouseByPid(P_id);
		return ledHouse;
	}

	@Override
	public LedClimatic findLedClimaticByPid(Integer p_id) {
		LedClimatic ledClimatic = ledDao.findLedClimaticByPid(p_id);
		return ledClimatic;
	}

	// 改
	@Override
	public String update(LedProgram ledProgram, LedTable ledTable, LedHouse ledHouse, LedClimatic ledClimatic,Integer houseId) {
		try {
			ledDao.updateProgram(ledProgram);
			ledDao.updateLedTable(ledTable);
//			ledDao.updateLedHouse(ledHouse);
			ledDao.updateLedClimatic(ledClimatic);
			ledDao.deleteProgramLedLink(ledProgram.getP_id());
			if(houseId != null && houseId != 0){
				ledDao.addProgramLedLink(ledProgram.getP_id(),houseId);
			}
		} catch (Exception e) {
			return "更改失败";
		}
		return "更改成功";
	}

	@Override
	public List<LedProgram> listAll(Integer z_id) {
		List<LedProgram> ledPrograms = ledDao.listAll(z_id);
		return ledPrograms;
	}

	@Override
	public String preview(Integer zoneId) {
		// String msg = createImageAndSendToLed(zoneId);
		// return msg;
		return null;
	}

	@Override
	public String createImageAndSendToLed(Integer zoneId, Integer p_id) {

		LedProgram ledProgram = ledDao.findProgramByPid(p_id);
		if (null != ledProgram) {
			LedTable ledTable = ledDao.findLedTableByPid(p_id);
			LedClimatic ledClimatic = ledDao.findLedClimaticByPid(p_id);
			if (null != ledTable) {

				// LedHouse ledHouse =
				// ledDao.findLedHouseByPid(ledProgram.getP_id());
				Climatic newestClimatic = climaticDao.getNewest(zoneId);
				List<House> houses = houseDao.findAllByZoneId(zoneId);

				return createImageAndSendToLed(houses, newestClimatic, ledProgram, ledTable, ledClimatic);

			} else {
				return "请添加表格设置";
			}
		} else {
			return "无节目,请添加节目";
		}

	}
	
	@Override
	public String createImageAndSendToLedLinkHouse(Integer zoneId, Integer programId) {
		LedProgram ledProgram = ledDao.findProgramByPid(programId);
		if (null != ledProgram) {
			LedTable ledTable = ledDao.findLedTableByPid(programId);
			LedClimatic ledClimatic = ledDao.findLedClimaticByPid(programId);
			if (null != ledTable) {

				// LedHouse ledHouse =
				// ledDao.findLedHouseByPid(ledProgram.getP_id());
				Climatic newestClimatic = climaticDao.getNewest(zoneId);
				List<House> houses = houseDao.findAllByLedProgram(programId);

				return createImageAndSendToLed(houses, newestClimatic, ledProgram, ledTable, ledClimatic);

			} else {
				return "请添加表格设置";
			}
		} else {
			return "无节目,请添加节目";
		}
	}

	// 创建图片并发送led屏
	@Override
	public String createImageAndSendToLed(List<House> houses, Climatic newestClimatic, LedProgram ledProgram,
			LedTable ledTable, LedClimatic ledClimatic) {
		NumberFormat nf = new DecimalFormat("0.000");
		File folder = new File("C:\\lyyh\\greenhouse");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		if (null != ledProgram) {

			Integer p_width = ledProgram.getP_width();
			Integer p_height = ledProgram.getP_height();
			Integer p_colourType = ledProgram.getP_colourType();
			// 创建一个句柄(屏幕宽,高,颜色类型)
			int hProgram;
			hProgram = led.CreateProgram(p_width, p_height, p_colourType);
			if (hProgram == 0) {
				return "创建节目对象失败,请重新设置屏幕的宽度/高度和颜色类型";
			}
			// 添加一个节目(句柄,节目号/id,节目播放时长 0.节目播放时长 非0.指定播放时长,循环播放次数
			int addProgram = led.AddProgram(hProgram, 1, 0, 1);
			if (addProgram != 0) {
				return "节目添加失败!错误代码:" + addProgram;
			}
			// 添加一个区域(句柄,节目号/id,区域号/id,区域位置(x,y),区域大小(w,h),是否为区域背景(0前景/1背景))
			int addImageTextArea = led.AddImageTextArea(hProgram, 1, 1, 0, 0, p_width, p_height, 1);
			if (addImageTextArea != 0) {
				return "温室区域添加失败,请重新设置温室区域的 位置 和 尺寸 ! ";
			}

			int imageType = BufferedImage.TYPE_INT_RGB;

			// 创建制图对象
			BufferedImage image = new BufferedImage(p_width, p_height, imageType);
			Graphics graphics = image.createGraphics();

			if (null != ledTable) {

				int tx = ledTable.getT_x();// x坐标
				int ty = ledTable.getT_y();// y坐标
				int th = ledTable.getT_h();// 高度
				int tw = ledTable.getT_w() - 1;// 宽度
				int tr = ledTable.getT_r();// 行数
				int tc = ledTable.getT_c();// 列数
				int tos = ledTable.getT_os();// 偏移量
				int ts = ledTable.getT_s();// 字体大小
				int rh = th / tr;// 行高
				int cw = tw / tc;// 列宽

				// 修正tw和th
				th = rh * tr;
				// if (null != ledHouse) {
				// 遍历温室
				int fileNo = 1;
				String str;

				if (CollectionUtils.isEmpty(houses)) {
					return "当前区域没有创建温室,请先创建温室.";
				}
				// i 当前温室索引
				for (int i = 0; i < houses.size(); i++) {
					// 画表格
					graphics.setColor(Color.red);
					graphics.drawLine(tx, ty, tx + tw, ty);
					graphics.drawLine(tx, ty, tx, ty + th);

					// 遍历行
					for (int r = 1; r <= tr; r++) {
						graphics.drawLine(tx, ty + rh * r, tx + tw, ty + rh * r);
					}
					// 遍历列

					// for (int c = 0; c < tc; c++) { //带温室名称
					// graphics.drawLine(tx + cw / 2 + tos + c * cw, ty + rh, tx
					// + cw / 2 + tos + c * cw, ty + th);
					// graphics.drawLine(tx + cw + c * cw, ty, tx + cw + c * cw,
					// ty + th);
					// }
					for (int c = 0; c < tc; c++) { // 不带温室名称
						graphics.drawLine(tx + cw / 2 + tos + c * cw, ty, tx + cw / 2 + tos + c * cw, ty + th);
						graphics.drawLine(tx + cw + c * cw, ty, tx + cw + c * cw, ty + th);
					}

					// 填数据
					graphics.setFont(new Font("宋体", Font.PLAIN, ts));
					graphics.setColor(Color.GREEN);

					int x = tx;// 当前光标
					int y = ty;

					int hnx;// 温室名光标

					int nx;// 数据name光标
					int vx;// 数据value光标
					// 遍历列
					List<SensorData> dataListInOneHouse = sensorDataDao.selectNewestDataByHouseId(houses.get(i).getId());
					if (!CollectionUtils.isEmpty(dataListInOneHouse)) {
						/*for (SensorData sensorData : dataListInOneHouse) {
							SensorData cacheData = CacheManager.newestSensorData.getCache().get(sensorData.getSensorId());
							if(cacheData != null){
								sensorData.setValue(cacheData.getValue());
							}
						}*/
						int index = 0;
						for (int j = 0; j < tc; j++/* , i++ */) {
							if (i >= houses.size()) {
								break;
							}

							int yy = y + rh / 2 + ts / 2;// 第一行位置
							for (int r = 0; r < tr; r++, index++) {
								if (index >= dataListInOneHouse.size()) {
									break;
								}
								// HouseData houseData = houseDatas.get(i);

								// // 温室名
								// str = houses.get(i).getName();
								// hnx = x + cw / 2 -
								// LedUtils.getStringLength(str) * ts / 4;//
								// 温室名光标
								//
								// graphics.drawString(str, hnx, yy);
								// yy += rh;// 当前光标下移一行

								// 温度1 TODO
								SensorData sensorData = dataListInOneHouse.get(index);
								if (sensorData.getLedShow() == 1) {
									// 文字
									str = sensorData.getSensorName();
									nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
									graphics.drawString(str, nx, yy);

									SensorData cacheData = CacheManager.newestSensorData.getCache().get(sensorData.getSensorId());
									if(cacheData != null){
										
										str = nf.format(cacheData.getValue());
									}else{
										str = sensorData.getValue().toString();
									}
									
//									str = sensorData.getValue().toString();
									// 数据
									vx = x + 3 * cw / 4 + tos / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据value光标
									// vx = x + 3 * cw / 4 - tos / 2 -
									// str.length() * ts / 2;// 数据value光标
									drawString(graphics, sensorData.getValue(), vx, yy);

									yy += rh;// 光标下移
								}
							}
							x += cw;// 光标右移一个宽度
						}

					}
					// n个温室遍历完成

					// 打印气象数据

					if (null != newestClimatic) {
						drawClimatic(newestClimatic, ledClimatic, graphics);
					}
					// 生成图片并添加到节目中
					try {
						// 生成一张图片
						String filename = "C:\\lyyh\\greenhouse\\led_" + ledProgram.getP_name() + "__house_"+  houses.get(i).getName()+"__fileNo_" + fileNo + ".bmp";
						ImageIO.write(image, "bmp", new File(filename));
						graphics.clearRect(0, 0, ledProgram.getP_width(), ledProgram.getP_height());

						// 添加一张图片到图文域(句柄,节目号/id,区域号/id,背景图片路径,入场特技,特技速度,停留时间)
						led.AddFileToImageTextArea(hProgram, 1, 1, filename, 0, 0, ledTable.getT_t());
						fileNo++;
					} catch (IOException e) {
						e.printStackTrace();
						if (fileNo > 1) {
							int netWorkSend = led.NetWorkSend(ledProgram.getP_ip(), hProgram);
							if (netWorkSend != 0) {
								System.out.println("节目发送失败,请检查IP设置是否正确,或者LED屏未接通");
								return "节目发送失败,请检查IP设置是否正确,或者LED屏未接通";
							}
						}
					}
				} // 遍历完所有温室,图片生成完毕
				// } else {
				// //当LedHouse为空时,光生成一个表格
				// // 生成一张图片
				// try {
				// String filename = "C:\\lyyh\\greenhouse\\led0.bmp";
				// ImageIO.write(image, "bmp", new File(filename));
				// // 添加一张图片到图文域(句柄,节目号/id,区域号/id,背景图片路径,入场特技,特技速度,停留时间)
				// led.AddFileToImageTextArea(hProgram, 1, 1, filename, 23, 100,
				// ledTable.getT_t());
				// // led.AddFileToImageTextArea(hProgram, 1, 1, filename,
				// // 3, 4, ledTable.getT_t());
				//
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// }
			}
			// TODO 此行文字应该设置为长期显示
			// led.AddSinglelineTextToImageTextArea(hProgram, 1, 1, 0,
			// "请在温室管理系统开启Led服务", "楷体", 20, 0xff, 0, 0, 0, 0, 4, -1);
			int netWorkSend = led.NetWorkSend(ledProgram.getP_ip(), hProgram);
			if (netWorkSend != 0) {
				System.out.println("节目发送失败,请检查IP设置是否正确,或者LED屏未接通");
				return "节目发送失败,请检查IP设置是否正确,或者LED屏未接通";
			}

			return "发送成功";
		}
		return "请设置屏幕参数";
	}

	// 启动led屏
	@Override
	public String showLed(User user, Integer p_id) {
		Integer zoneId = user.getZoneId();
		LedTable ledTable = null;
		LedProgram ledProgram = null;
		String msg = null;

		ledProgram = ledDao.findProgramByPid(p_id);
		if (null != ledProgram) {
			ledTable = ledDao.findLedTableByPid(p_id);
			if (null != ledTable) {

				// 停止当前正在运行的led线程
				stopLed(user.getUserId());
				// 新建一个线程
				String threadName = "ledThread" + p_id;
				List<House> houses = houseDao.findAllByZoneId(zoneId);
				int s = houses.size();
				int c = ledTable.getT_c();
				int n = s % c == 0 ? s / c : (s / c + 1);
				int sleepTime = n * ledTable.getT_t() * 1000;
				// System.out.println(time/1000);

				LedTask ledTask = new LedTask(this, sleepTime, zoneId, p_id);
				Thread t = new Thread(ledTask, threadName);
				t.start();

			} else {
				msg = "请添加表格设置";
				// createImageAndSendToLed(houseDatas, newestClimatic,
				// ledProgram, ledTable, ledHouse,ledClimatic);
				return msg;
			}
		} else {
			msg = "无节目,请添加节目";
			return msg;
		}

		return "发送成功";

	}

	@Override
	public String showLedLinkHouse(Integer p_id) {
		stopLedLinkHouse(p_id);
		LedTable ledTable = null;
		LedProgram ledProgram = null;
		String msg = null;

		ledProgram = ledDao.findProgramByPid(p_id);
		if (null != ledProgram) {
			ledTable = ledDao.findLedTableByPid(p_id);
			if (null != ledTable) {

				// 停止当前正在运行的led线程
				// 新建一个线程
				String threadName = "ledThread_" + p_id;
				List<House> houses = houseDao.findAllByLedProgram(p_id);
				int s = houses.size();
				int c = ledTable.getT_c();
				int n = s % c == 0 ? s / c : (s / c + 1);
				int sleepTime = n * ledTable.getT_t() * 1000;
				// System.out.println(time/1000);

				LedTaskLinkHouse ledTaskLinkHouse = new LedTaskLinkHouse(this, sleepTime, ledProgram.getZ_id(), p_id);
				Thread t = new Thread(ledTaskLinkHouse, threadName);
				t.start();

			} else {
				msg = "请添加表格设置";
				// createImageAndSendToLed(houseDatas, newestClimatic,
				// ledProgram, ledTable, ledHouse,ledClimatic);
				return msg;
			}
		} else {
			msg = "无节目,请添加节目";
			return msg;
		}

		return "发送成功";
	}

	// 结束led线程
	@Override
	public String stopLed(int userId) {

		ThreadGroup group = Thread.currentThread().getThreadGroup();
		// 激活的线程数加倍
		int estimatedSize = group.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = group.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		System.arraycopy(slackList, 0, list, 0, actualSize);
		// System.out.println("Thread list size == " + list.length);

		// TODO
		for (Thread thread : list) {
			String tname = thread.getName();
			if (tname.contains("ledThread")) {
				// System.out.println(tname);
				thread.stop();
			}
		}

		LedProgram ledProgram = ledDao.findProgramByUserId(userId);
		String p_ip = ledProgram.getP_ip();
		Integer p_width = ledProgram.getP_width();
		Integer p_height = ledProgram.getP_height();
		Integer p_colourType = ledProgram.getP_colourType();
		// 创建一个句柄(屏幕宽,高,颜色类型)
		int hProgram;
		hProgram = led.CreateProgram(p_width, p_height, p_colourType);
		if (hProgram == 0) {
			return "创建节目对象失败,请重新设置屏幕的宽度/高度和颜色类型";
		}
		// 添加一个节目(句柄,节目号/id,节目播放时长 0.节目播放时长 非0.指定播放时长,循环播放次数
		int addProgram = led.AddProgram(hProgram, 1, 0, 1);
		if (addProgram != 0) {
			return "节目添加失败!错误代码:" + addProgram;
		}
		// 添加一个区域(句柄,节目号/id,区域号/id,区域位置(x,y),区域大小(w,h),是否为区域背景(0前景/1背景))
		int addImageTextArea = led.AddImageTextArea(hProgram, 1, 1, 0, 0, p_width, p_height, 1);
		if (addImageTextArea != 0) {
			return "温室区域添加失败,请重新设置温室区域的 位置 和 尺寸 !";
		}
		led.AddSinglelineTextToImageTextArea(hProgram, 1, 1, 0, "请在温室管理系统开启Led服务", "楷体", 20, 0xff, 0, 0, 0, 0, 4, -1);

		int netWorkSend = led.NetWorkSend(p_ip, hProgram);
		if (netWorkSend != 0) {
			return "节目发送失败,请检查IP设置是否正确,或者LED屏未接通";
		}

		return "已停止led显示";

	}

	// 结束led线程
	@Override
	public String stopLedLinkHouse(Integer p_id) {
		String threadName = "ledThread_" + p_id;
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		// 激活的线程数加倍
		int estimatedSize = group.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = group.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		System.arraycopy(slackList, 0, list, 0, actualSize);
		// System.out.println("Thread list size == " + list.length);

		// TODO
		for (Thread thread : list) {
			String tname = thread.getName();
			if (threadName.equals(tname)) {
				// System.out.println(tname);
				thread.stop();
			}
		}

		LedProgram ledProgram = ledDao.findProgramByPid(p_id);
		String p_ip = ledProgram.getP_ip();
		Integer p_width = ledProgram.getP_width();
		Integer p_height = ledProgram.getP_height();
		Integer p_colourType = ledProgram.getP_colourType();
		// 创建一个句柄(屏幕宽,高,颜色类型)
		int hProgram;
		hProgram = led.CreateProgram(p_width, p_height, p_colourType);
		if (hProgram == 0) {
			return "创建节目对象失败,请重新设置屏幕的宽度/高度和颜色类型";
		}
		// 添加一个节目(句柄,节目号/id,节目播放时长 0.节目播放时长 非0.指定播放时长,循环播放次数
		int addProgram = led.AddProgram(hProgram, 1, 0, 1);
		if (addProgram != 0) {
			return "节目添加失败!错误代码:" + addProgram;
		}
		// 添加一个区域(句柄,节目号/id,区域号/id,区域位置(x,y),区域大小(w,h),是否为区域背景(0前景/1背景))
		int addImageTextArea = led.AddImageTextArea(hProgram, 1, 1, 0, 0, p_width, p_height, 1);
		if (addImageTextArea != 0) {
			return "温室区域添加失败,请重新设置温室区域的 位置 和 尺寸 !";
		}
		led.AddSinglelineTextToImageTextArea(hProgram, 1, 1, 0, "请在温室管理系统开启Led服务", "楷体", 20, 0xff, 0, 0, 0, 0, 4, -1);

		int netWorkSend = led.NetWorkSend(p_ip, hProgram);
		if (netWorkSend != 0) {
			return "节目发送失败,请检查IP设置是否正确,或者LED屏未接通";
		}

		return "已停止led显示";

	}

	private void drawClimatic(Climatic newestClimatic, LedClimatic ledClimatic, Graphics graphics) {

		String str;
		if (ledClimatic.getC_n() == 1) {
			str = "气象信息：";
			graphics.drawString(str, ledClimatic.getC_nx(), ledClimatic.getC_ny());
		}
		if (ledClimatic.getC_t() == 1) {
			drawString(graphics, newestClimatic.getTemperature(), "温度:", ledClimatic.getC_tx(), ledClimatic.getC_ty());
		}
		if (ledClimatic.getC_h() == 1) {
			drawString(graphics, newestClimatic.getHumidity(), "湿度：", ledClimatic.getC_hx(), ledClimatic.getC_hy());
		}
		if (ledClimatic.getC_l() == 1) {
			drawString(graphics, newestClimatic.getLighting(), "光照：", ledClimatic.getC_lx(), ledClimatic.getC_ly());
		}
		if (ledClimatic.getC_rs() == 1) {
			drawString(graphics, newestClimatic.getRainAndSnow(), "雨雪：", ledClimatic.getC_rsx(),
					ledClimatic.getC_rsy());
		}
		if (ledClimatic.getC_rf() == 1) {
			drawString(graphics, newestClimatic.getRainFall(), "雨量：", ledClimatic.getC_rfx(), ledClimatic.getC_rfy());
		}
		if (ledClimatic.getC_ws() == 1) {
			drawString(graphics, newestClimatic.getWindSpeed(), "风速：", ledClimatic.getC_wsx(), ledClimatic.getC_wsy());
		}
		if (ledClimatic.getC_wd() == 1) {
			drawString(graphics, newestClimatic.getWindDirection(), "风向：", ledClimatic.getC_wdx(),
					ledClimatic.getC_wdy());
		}
		if (ledClimatic.getC_p() == 1) {
			drawString(graphics, newestClimatic.getPressure(), "气压：", ledClimatic.getC_px(), ledClimatic.getC_py());
		}
		if (ledClimatic.getC_pm() == 1) {
			drawString(graphics, newestClimatic.getPm25(), "PM2.5：", ledClimatic.getC_pmx(), ledClimatic.getC_pmy());
		}
		if (ledClimatic.getC_ph() == 1) {
			drawString(graphics, newestClimatic.getPh(), "PH：", ledClimatic.getC_phx(), ledClimatic.getC_phy());
		}

	}

	void drawString(Graphics graphics, Object data, String str, int x, int y) {

		if (data == null) {
			graphics.drawString(str, x, y);
		} else {
			graphics.drawString(str + data, x, y);
		}
	}

	void drawString(Graphics graphics, Object data, int x, int y) {
		if (data == null) {
			graphics.drawString(" ", x, y);
		} else {
			graphics.drawString(data + "", x, y);
		}
	}

	@Override
	public House findHouseByPid(Integer p_id) {
		List<House> houseList = houseDao.findAllByLedProgram(p_id);
		if(!CollectionUtils.isEmpty(houseList)){
			return houseList.get(0);
		}
		return null;
	}

	@Override
	public List<LedProgram> findProgramByZoneid(Integer zoneId) {
		return ledDao.findProgramByZoneid(zoneId);
	}



	

}
