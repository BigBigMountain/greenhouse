package com.lyyh.fertilizer.autoRun;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.lyyh.fertilizer.pojo.FertilizerDataCollector;
import com.lyyh.fertilizer.service.FertilizerService;
import com.lyyh.greenhouse.util.dtu.DtuPool;
import com.lyyh.greenhouse.util.dtu.DtuUtils;
import com.lyyh.greenhouse.util.fertilizer.FertilizerUtils;

/*
 * 1.开机运行,
 * 2.负责把新的DTU链接放入DtuPool中,
 * 3.负责把断开的DTU链接销毁,
 */
@Component
public class DtuRegister implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	FertilizerService fertilizerService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// System.out.println("开启服务");
		if (event.getApplicationContext().getParent() == null) {
			// System.out.println("获取最大context");
			// 抓取dtu连接,放进连接池
			receiveDtu();
			// System.out.println("已开启接收");
			// 检查连接池中的连接是否在线
			checkAlive();
			// System.out.println("已开启检查");
			readPLC();
		}

	}

	/*
	 * 开启一个新线程,任务是接收新的链接,并把链接保存起来
	 */
	public void receiveDtu() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// 创建tcp服务端,端口号9000,并接受socket,放进连接池中
				ServerSocket server = null;
				try {
					// System.out.println("tcp 准备建立server");
					server = new ServerSocket(9000);
					// System.out.println("tcpServer has biuld");
				} catch (IOException e) {
					e.printStackTrace();
					// dtuService.setAllDtuOff();
				}
				while (true) {
					synchronized (server) {
						try {
							if (server == null) {
								server = new ServerSocket(9000);
							}
							System.out.println("等待抓取连接");
							Socket client = server.accept();
							System.out.println("发现新连接: "+ client);
							String dtuCode = DtuUtils.addClientToDtuPool(client);
							if(null != dtuCode){
								fertilizerService.setOnline(dtuCode);
								System.out.println("添加成功了 : "+client);
							}else{
								System.out.println("不合法连接: "+ client);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		}).start();
	}

	/*
	 * 定时检测连接池中的链接是否还有效,剔除并销毁无效链接;
	 */
	public void checkAlive() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Set<Entry<String, OutputStream>> dtuEntrys = DtuUtils.getAllDtuEntry();
					System.out.println("dtu 池的数量:" + dtuEntrys.size());
					if (null != dtuEntrys && dtuEntrys.size() != 0) {
						// 把entry复制一份遍历,在迭代的时候需要删除操作,如果发现连接不同,就把池中的链接删除,
						Set<Entry<String, OutputStream>> dtuEntrys2 = new HashSet<Entry<String, OutputStream>>(
								dtuEntrys);
						for (Entry<String, OutputStream> entry : dtuEntrys2) {
							try {
								// System.out.println("检查 "+entry.getKey()+"
								// 状态");
								OutputStream os = entry.getValue();
//								System.out.println("os : " + os);
//								System.out.println(DtuPool.getSocketByDtuCode(entry.getKey()));
								os.write((byte) 0);
								os.flush();
								System.out.println(" ---" + entry.getKey() + "---在线");
								// 测试读取数据
							} catch (IOException e) {
//								System.out.println(" +++" + entry.getKey() + "+++检查时不在线");
								if(e instanceof SocketException){
									DtuUtils.destroyDtuById(entry.getKey());
									fertilizerService.setOffline(entry.getKey());
								}
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e) {
//					System.out.println("定时任务出错:定时检测dtu连接状态时出错.");
					e.printStackTrace();
				}

			}
		}, 1000, 1000 * 60);
	}

	// 定时查询plc 中数据
	public void readPLC() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Set<Entry<String, OutputStream>> dtuEntrys = DtuUtils.getAllDtuEntry();
					if (null != dtuEntrys && dtuEntrys.size() != 0) {
						// 把entry复制一份遍历,在迭代的时候需要删除操作,如果发现连接不同,就把池中的链接删除,
						Set<Entry<String, OutputStream>> dtuEntrys2 = new HashSet<Entry<String, OutputStream>>(dtuEntrys);
						for (Entry<String, OutputStream> entry : dtuEntrys2) {
							// 读取plc数据,
							try {
								FertilizerDataCollector dataCollector = FertilizerUtils.readValue(entry.getKey());
//TODO 放入数据库中,目前是单个放,应该做成批量插入
								if (null != dataCollector) {
									fertilizerService.addData(dataCollector);
								}
//								System.out.println(dataCollector);
							} catch (IOException e) {
								System.out.println("通讯错误>>>>>"+DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss")+" : DTU编号 : "+entry.getKey());
//								System.out.println(" +++" + entry.getKey() + "+++业务层操作时不在线");
//								DtuUtils.destroyDtuById(entry.getKey());
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e) {
//					System.out.println("定时任务出错:读取plc数据时出错.");
					e.printStackTrace();
				}

			}
		}, 5000, 1000 * 60);

	}

}
