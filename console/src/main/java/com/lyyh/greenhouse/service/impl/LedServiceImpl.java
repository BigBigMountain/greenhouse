package com.lyyh.greenhouse.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.listenvision.led;
import com.lyyh.greenhouse.dao.ClimaticDao;
import com.lyyh.greenhouse.dao.HouseDataDao;
import com.lyyh.greenhouse.dao.LedDao;
import com.lyyh.greenhouse.pojo.Climatic;
import com.lyyh.greenhouse.pojo.HouseData;
import com.lyyh.greenhouse.pojo.LedClimatic;
import com.lyyh.greenhouse.pojo.LedHouse;
import com.lyyh.greenhouse.pojo.LedProgram;
import com.lyyh.greenhouse.pojo.LedTable;
import com.lyyh.greenhouse.pojo.User;
import com.lyyh.greenhouse.service.LedService;
import com.lyyh.greenhouse.util.LedUtils;
import com.lyyh.greenhouse.timerTask.LedTask;
@Service
@Transactional
public class LedServiceImpl implements LedService{

	@Autowired
	private LedDao ledDao;
	
	@Autowired
	private HouseDataDao houseDataDao;
	
	@Autowired
	private ClimaticDao climaticDao;
	
//增
	@Override
	public String add(LedProgram ledProgram,LedTable ledTable, LedHouse ledHouse,LedClimatic ledClimatic) {
		try {
			ledDao.addLedProgram(ledProgram);
			
			ledTable.setP_id(ledProgram.getP_id());
			ledDao.addLedTable(ledTable);
			
			ledHouse.setP_id(ledProgram.getP_id());
			ledDao.addLedHouse(ledHouse);
			
			ledClimatic.setP_id(ledProgram.getP_id());
			ledDao.addLedClimatic(ledClimatic);
		} catch (Exception e) {
			return "保存失败!"+e.getMessage();
		}
		return "保存成功";
	}

	
//删
	@Override
	public void delProgramByPid(Integer p_id) {
		ledDao.delLedHouseByPid(p_id);
		ledDao.delLedTableByPid(p_id);
		ledDao.delLedClimaticByPid(p_id);
		ledDao.delLedProgramByPid(p_id);
		
	}
//查	
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


	
	
//改
	@Override
	public String update(LedProgram ledProgram,LedTable ledTable, LedHouse ledHouse,LedClimatic ledClimatic) {
		try {
			ledDao.updateProgram(ledProgram);
			ledDao.updateLedTable(ledTable);
			ledDao.updateLedHouse(ledHouse);
			ledDao.updateLedClimatic(ledClimatic);
		} catch (Exception e) {
			e.printStackTrace();
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
	public String preview(String zoneName, LedProgram ledProgram,LedTable ledTable, LedHouse ledHouse,LedClimatic ledClimatic) {
		List<HouseData> houseDatas = houseDataDao.getAllNewestByZoneName(zoneName);
		Climatic newestClimatic = climaticDao.getNewest(zoneName);
		String msg = createImageAndSendToLed(houseDatas, newestClimatic, ledProgram, ledTable, ledHouse, ledClimatic);
		return msg;
	}
	/*
	public String showLed(User user,Integer p_id) {
		String zoneName = user.getZoneName();
		LedTable ledTable = null;
		LedHouse ledHouse = null;
		LedClimatic ledClimatic = null;
		List<HouseData> houseDatas =null;
		Climatic newestClimatic =null;
		LedProgram ledProgram =null;
		String msg = null;
		int time = 0;
		
		ledProgram = ledDao.findProgramByPid(p_id);
		if (null != ledProgram) {
			ledTable = ledDao.findLedTableByPid(p_id);
			ledClimatic = ledDao.findLedClimaticByPid(p_id);
			if (null != ledTable) {
				ledHouse = ledDao.findLedHouseByPid(ledProgram.getP_id());
				
				while (true) {
					houseDatas = houseDataDao.getAllNewestByZoneName(zoneName);
					newestClimatic = climaticDao.getNewest(zoneName);
					createImageAndSendToLed(houseDatas, newestClimatic, ledProgram, ledTable, ledHouse,ledClimatic);
					time = (houseDatas.size() / ledTable.getT_c() + 1) * ledTable.getT_t() * 1000;
					System.out.println(time/1000);
					try {
						
						
						if (time != 0) {
							Thread.sleep(time);
						}else {
							msg = "轮播时间不能为0";
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			} else {
				msg = "请添加表格设置";
				createImageAndSendToLed(houseDatas, newestClimatic, ledProgram, ledTable, ledHouse,ledClimatic);
				return msg;
			}
		} else {
			msg = "无节目,请添加节目";
			return msg;
		}
	}*/

	//创建图片并发送led屏
	@Override
	public String createImageAndSendToLed(List<HouseData> houseDatas,Climatic newestClimatic,LedProgram ledProgram,LedTable ledTable,LedHouse ledHouse,LedClimatic ledClimatic){
		if(null != ledProgram){
			
			Integer p_width = ledProgram.getP_width();
			Integer p_height = ledProgram.getP_height();
			// 创建一个句柄(屏幕宽,高,颜色类型)
			int hProgram;
			hProgram = led.CreateProgram(p_width, p_height,ledProgram.getP_colourType());
			if (hProgram == 0) {
				return "创建节目对象失败,请重新设置屏幕的宽度/高度和颜色类型";
			}
			// 添加一个节目(句柄,节目号/id,节目播放时长 0.节目播放时长 非0.指定播放时长,循环播放次数
			int addProgram = led.AddProgram(hProgram, 1, 0, 1);
			if (addProgram != 0) {
				return "节目添加失败!错误代码:" + addProgram;
			}
			//添加一个区域(句柄,节目号/id,区域号/id,区域位置(x,y),区域大小(w,h),是否为区域背景(0前景/1背景))
			int addImageTextArea = led.AddImageTextArea(hProgram, 1, 1,0, 0, p_width,p_height, 1);
			if (addImageTextArea != 0) {
				return "温室区域添加失败,请重新设置温室区域的 位置 和 尺寸 !";
			}
			
			
			
			int imageType=BufferedImage.TYPE_INT_RGB;
			
			//创建制图对象
			BufferedImage image = new BufferedImage(p_width, p_height, imageType);
			Graphics graphics = image.createGraphics();
			
			
			if (null != ledTable) {

				int tx = ledTable.getT_x();
				int ty = ledTable.getT_y();
				int th = ledTable.getT_h();
				int tw = ledTable.getT_w() - 1;
				int tr = ledTable.getT_r();
				int n = ledTable.getT_c();
				int tos = ledTable.getT_os();
				int ts = ledTable.getT_s();
				int rh = th / tr;
				int cw = tw / n;

				// 修正tw和th
				th = rh * tr;
				if (null != ledHouse) {
					// 遍历温室
					int fileNo = 1;
					String str;
					for (int i = 0; i < houseDatas.size();) {
						// 画表格
						graphics.setColor(Color.red);
						graphics.drawLine(tx, ty, tx + tw, ty);
						graphics.drawLine(tx, ty, tx, ty + th);

						// 遍历行
						for (int r = 1; r <= tr; r++) {
							graphics.drawLine(tx, ty + rh * r, tx + tw, ty + rh * r);
						}
						// 遍历列

						for (int c = 0; c < n; c++) {
							graphics.drawLine(tx + cw / 2 + tos + c * cw, ty + rh, tx + cw / 2 + tos + c * cw, ty + th);
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
						for (int j = 0; j < n; j++, i++) {
							if (i >= houseDatas.size()) {
								break;
							}

							int yy = y + rh / 2 + ts / 2;// 第一行位置
							HouseData houseData = houseDatas.get(i);

							// 温室名
							str = houseData.getHouseName();
							hnx = x + cw / 2 - LedUtils.getStringLength(str) * ts / 4;// 温室名光标

							graphics.drawString(str, hnx, yy);
							yy += rh;// 当前光标下移一行
							// 温度1
							if (ledHouse.getH_t1() == 1) {
								// 文字
								str = "温度1";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getTemperature() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// 温度2
							if (ledHouse.getH_t2() == 1) {
								// 文字
								str = "温度2";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getTemperature2() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// 湿度1
							if (ledHouse.getH_h1() == 1) {
								// 文字
								str = "湿度1";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getHumidity() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// 湿度2
							if (ledHouse.getH_h2() == 1) {
								// 文字
								str = "湿度2";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getHumidity2() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// 光照
							if (ledHouse.getH_l() == 1) {
								// 文字
								str = "光照";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getLighting() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// CO2
							if (ledHouse.getH_c() == 1) {
								// 文字
								str = "CO2";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getCo2() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// 土壤温度
							if (ledHouse.getH_st() == 1) {
								// 文字
								str = "土壤温度";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getSoilTemperature() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							// 土壤湿度
							if (ledHouse.getH_sh() == 1) {
								// 文字
								str = "土壤湿度";
								nx = x + (cw / 2 + tos) / 2 - LedUtils.getStringLength(str) * ts / 4;// 数据name光标
								graphics.drawString(str, nx, yy);

								// 数据
								str = houseData.getSoilHumidity() + "";
								vx = x + 3 * cw / 4 + tos / 2 - str.length() * ts / 4;// 数据value光标
								graphics.drawString(str, vx, yy);

								yy += rh;// 光标下移
							}
							x += cw;// 光标右移一个宽度

						} // n个温室遍历完成

						// 打印气象数据
						if (null != newestClimatic) {
							drawClimatic(newestClimatic, ledClimatic, graphics);
						}
						// 生成图片并添加到节目中
						try {
							// 生成一张图片
							String filename = "C:\\lyyh\\greenhouse\\led" + fileNo + ".bmp";
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
									return "节目发送失败,请检查IP设置是否正确,或者LED屏未接通";
								}
							}
						}
					} // 遍历完所有温室,图片生成完毕
				} else {
					//当LedHouse为空时,光生成一个表格
					// 生成一张图片
					try {
						String filename = "C:\\lyyh\\greenhouse\\led0.bmp";
						ImageIO.write(image, "bmp", new File(filename));
						// 添加一张图片到图文域(句柄,节目号/id,区域号/id,背景图片路径,入场特技,特技速度,停留时间)
						led.AddFileToImageTextArea(hProgram, 1, 1, filename, 23, 100, ledTable.getT_t());
						// led.AddFileToImageTextArea(hProgram, 1, 1, filename,
						// 3, 4, ledTable.getT_t());

					} catch (IOException e) {
						e.printStackTrace();

					}
				}
			}
			//TODO  此行文字应该设置为长期显示
			led.AddSinglelineTextToImageTextArea(hProgram, 1, 1, 0, "请在温室管理系统开启Led服务", "楷体", 20, 0xff, 0, 0, 0, 0, 4, -1);
			int netWorkSend = led.NetWorkSend(ledProgram.getP_ip(), hProgram);
			if (netWorkSend != 0) {
				return "节目发送失败,请检查IP设置是否正确,或者LED屏未接通";
			}
			 
			return "发送成功";
		}
		return "请设置屏幕参数";
	}


//启动led屏
	@Override
	public String showLed(User user, Integer p_id) {
		String zoneName = user.getZoneName();
		LedTable ledTable = null;
		LedHouse ledHouse = null;
		LedClimatic ledClimatic = null;
		LedProgram ledProgram =null;
		String msg = null;
		
		ledProgram = ledDao.findProgramByPid(p_id);
		if (null != ledProgram) {
			ledTable = ledDao.findLedTableByPid(p_id);
			ledClimatic = ledDao.findLedClimaticByPid(p_id);
			if (null != ledTable) {
				
				//停止当前正在运行的led线程
				stopLed();
				//新建一个线程
				ledHouse = ledDao.findLedHouseByPid(ledProgram.getP_id());
				String name = "ledThread" + p_id;
				LedTask ledTask = new LedTask(houseDataDao, climaticDao, this, ledProgram, ledTable, ledHouse, ledClimatic, zoneName);
				Thread t = new Thread(ledTask, name);
				t.start();
				
			} else {
				msg = "请添加表格设置";
//				createImageAndSendToLed(houseDatas, newestClimatic, ledProgram, ledTable, ledHouse,ledClimatic);
				return msg;
			}
		} else {
			msg = "无节目,请添加节目";
			return msg;
		}
		
		return "发送成功";

	}
	//结束led线程
	@Override
	public void stopLed(){

		ThreadGroup group = Thread.currentThread().getThreadGroup();
		// 激活的线程数加倍
		int estimatedSize = group.activeCount() * 2;
		Thread[] slackList = new Thread[estimatedSize];
		// 获取根线程组的所有线程
		int actualSize = group.enumerate(slackList);
		// copy into a list that is the exact size
		Thread[] list = new Thread[actualSize];
		System.arraycopy(slackList, 0, list, 0, actualSize);
//		System.out.println("Thread list size == " + list.length);

		// TODO
		for (Thread thread : list) {
			String tname = thread.getName();
			if (tname.contains("ledThread")) {
//				System.out.println(tname);
				thread.stop();
			}
		}
		
		
	}


	private void drawClimatic(Climatic newestClimatic, LedClimatic ledClimatic, Graphics graphics) {

		String str;
		if (ledClimatic.getC_t() == 1) {
			str = "温度：" + newestClimatic.getTemperature();
			graphics.drawString(str, ledClimatic.getC_tx(), ledClimatic.getC_ty());
		}
		if (ledClimatic.getC_h() == 1) {
			str = "湿度：" + newestClimatic.getHumidity();
			graphics.drawString(str, ledClimatic.getC_hx(), ledClimatic.getC_hy());
		}
		if (ledClimatic.getC_l() == 1) {
			str = "光照：" + newestClimatic.getLighting();
			graphics.drawString(str, ledClimatic.getC_lx(), ledClimatic.getC_ly());
		}
		if (ledClimatic.getC_rs() == 1) {
			str = "雨雪：" + newestClimatic.getRainAndSnow();
			graphics.drawString(str, ledClimatic.getC_rsx(), ledClimatic.getC_rsy());
		}
		if (ledClimatic.getC_rf() == 1) {
			str = "雨量：" + newestClimatic.getRainFall();
			graphics.drawString(str, ledClimatic.getC_rfx(), ledClimatic.getC_rfy());
		}
		if (ledClimatic.getC_ws() == 1) {
			str = "风速：" + newestClimatic.getWindSpeed();
			graphics.drawString(str, ledClimatic.getC_wsx(), ledClimatic.getC_wsy());
		}
		if (ledClimatic.getC_wd() == 1) {
			str = "风向：" + newestClimatic.getWindDirection();
			graphics.drawString(str, ledClimatic.getC_wdx(), ledClimatic.getC_wdy());
		}
		if (ledClimatic.getC_p() == 1) {
			str = "气压：" + newestClimatic.getPressure();
			graphics.drawString(str, ledClimatic.getC_px(), ledClimatic.getC_py());
		}
		if (ledClimatic.getC_pm() == 1) {
			str = "PM2.5：" + newestClimatic.getPm25();
			graphics.drawString(str, ledClimatic.getC_pmx(), ledClimatic.getC_pmy());
		}
		if (ledClimatic.getC_ph() == 1) {
			str = "PH：" + newestClimatic.getPh();
			graphics.drawString(str, ledClimatic.getC_phx(), ledClimatic.getC_phy());
		}

	}
}












