package com.lyyh.greenhouse.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lyyh.greenhouse.pojo.ClimaticCollector;
import com.lyyh.greenhouse.pojo.NodeSetting;
import com.lyyh.greenhouse.pojo.HouseData;

public class ModbusUtil {

	private static Map<String, Socket> sm = new HashMap<String, Socket>();
	private static Map<String, OutputStream> osm = new HashMap<String, OutputStream>();
	private static Map<String, InputStream> ism = new HashMap<String, InputStream>();
	private static byte m[] = { 0x15, 0x01, 0x00, 0x00, 0x00, 0x06 };
	private static byte n[] = { 0x03, 0x00, 0x00, 0x00, 0x10 };
	private static List<Field> channelFields = new ArrayList<Field>();

	public static byte[] getModbusQueryStatement(int node) {
		byte b[] = { (byte) node };
		byte mqs[] = new byte[12];
		System.arraycopy(m, 0, mqs, 0, 6);
		System.arraycopy(b, 0, mqs, 6, 1);
		System.arraycopy(n, 0, mqs, 7, 5);
		return mqs;
	}

	/*
	 * 6:设备地址 9:第一通道的标识符和通道数值 数据类型 数据解析定义 高位 低位 第几字节 9 10 11 12 循环变量角标i i i+1
	 * i+2 i+3 字节数字的值 1 129 0 226
	 * 
	 */
	public static HouseData parseData(byte[] result, HouseData houseData) {
		for (int i = 9; i < (result[8] & 255);) {
			if (i >= result[8] + 8) {
				return houseData;
			}
			int k = result[i] & 255;
			Double value = null;
			switch (k) {
			case 1:// 温度
				value = ((result[i+2] << 8)+(result[i+3]&255))/10.0;
				if (null == houseData.getTemperature()) {
					houseData.setTemperature(value);
				} else {
					houseData.setTemperature2(value);
				}
				break;
			case 2:
				// "湿度";
				value = (((result[i+2]&255) << 8)+(result[i+3]&255))/10.0;
				if(null == houseData.getHumidity()){
					houseData.setHumidity(value);
				} else {
					houseData.setHumidity2(value);
				}
				break;
			case 3:
				// "光照";
				value = ((result[i + 2] & 255) <<24 + (result[i + 3] & 255) <<16
						+ (result[i + 6] & 255) <<8 + (result[i + 7] & 255)) * 1.0;
				i += 4;
				houseData.setLighting(value);
				break;
			case 4:
				// "土壤温度";
				value = (((result[i+2]&255) << 8)+(result[i+3]&255))/10.0;
				houseData.setSoilTemperature(value);
				break;
			case 5:
				// "土壤湿度"; TODO
				value = ((result[i + 2] & 255) <<8 + (result[i + 3] & 255)) / 1000.0;
				houseData.setSoilHumidity(value);
				break;
			case 20:
				// "CO2";
				value = (((result[i + 2] & 255) <<8) + (result[i + 3] & 255)) / 1.0;
				houseData.setCo2(value);
				break;
			}
			i += 4;
		}
		return houseData;
	}

	public static void parseData(byte[] result) {
		KLModbusData klmd;
		for (int i = 9; i < (result[8] & 255);) {
			if (i >= 8 + result[8]) {
				return;
			}
			if (result[i] == 0) {
				i += 4;
				continue;
			}
			klmd = KLModbusUtils.parseData(i, result);
			// System.out.println(klmd.getType()+" "+klmd.getValue());
			i = klmd.getI();
		}
	}

	public static void parseData(byte[] result, ClimaticCollector climaticCollector, NodeSetting node) {
		KLModbusData klmd;
		// 通过node.class 反射得到各属性的的,如temperature,huminity等
		Field[] fields = node.getClass().getDeclaredFields();

		int i = 9;
		for (Field field : fields) {
			if (field.getName().contains("channel")) {
				if (i >= 8 + result[8]) {
					return;
				}
				if (result[i] == 0) {
					i += 4;
					continue;
				}
				klmd = KLModbusUtils.parseData(i, result);
				i = klmd.getI();
				// System.out.println(klmd.getValue());
				try {
					field.setAccessible(true);
					String propertyName = (String) field.get(node);
					if (propertyName == null) {
						continue;
					}
					String setProperty = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
					Method climaticSetMethod = climaticCollector.getClass().getMethod(setProperty, Double.class);
					climaticSetMethod.invoke(climaticCollector, klmd.getDoubleVal());

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	public static void parseData2(byte[] result, HouseData houseData) {
		KLModbusData klmd;
		for (int i = 9; i < (result[8] & 255);) {
			if (i >= 8 + result[8]) {
				return;
			}
			if (result[i] == 0) {
				i += 4;
				continue;
			}

			klmd = KLModbusUtils.parseData(i, result);
			i = klmd.getI();
			String type = klmd.getType();
			double value = klmd.getDoubleVal();
			switch (type) {
			case "01":// 温度

				if (null == houseData.getTemperature()) {
					houseData.setTemperature(value);
				} else {
					houseData.setTemperature2(value);
				}
				break;
			case "02":
				// "湿度";
				if (houseData.getHumidity() == null) {
					houseData.setHumidity(value);
				} else {
					houseData.setHumidity2(value);
				}
				break;
			case "03":
				// "光照";
				houseData.setLighting(value);
				break;
			case "04":
				// "土壤温度";
				houseData.setSoilTemperature(value);
				break;
			case "05":
				// "土壤湿度"; TODO 湿度解析
				Double v = null;
				if (value < 0) {
					v = null;
				} else if (value <= 0.096) {
					v = 0.0;
				} else if (value <= 0.191) {
					v = 5.0;
				} else if (value <= 0.267) {
					v = 10.0;
				} else if (value <= 0.346) {
					v = 15.0;
				} else if (value <= 0.420) {
					v = 20.0;
				} else if (value <= 0.485) {
					v = 25.0;
				} else if (value <= 0.544) {
					v = 30.0;
				} else if (value <= 0.602) {
					v = 35.0;
				} else if (value <= 0.650) {
					v = 40.0;
				} else if (value <= 0.701) {
					v = 45.0;
				} else if (value <= 0.751) {
					v = 50.0;
				} else if (value <= 0.797) {
					v = 55.0;
				} else if (value <= 0.841) {
					v = 60.0;
				} else if (value <= 0.878) {
					v = 65.0;
				} else if (value <= 0.914) {
					v = 70.0;
				} else if (value <= 0.943) {
					v = 75.0;
				} else if (value <= 0.965) {
					v = 80.0;
				} else if (value <= 0.981) {
					v = 85.0;
				} else if (value <= 0.993) {
					v = 90.0;
				} else if (value < 1) {
					v = 95.0;
				} else if (value == 1) {
					v = 100.0;
				}
				houseData.setSoilHumidity(v);
				break;
			case "14":
				// "CO2";
				houseData.setCo2(value);
				break;
			}

		}
	}

	public static String verifyIp(String strIp) {
		String result = "";
		String[] split = strIp.split(".");
		if (split.length != 4) {
			return null;
		}
		for (int i = 0; i < 4; i++) {
			String trim = StringUtils.trim(split[i]);
			if (trim == null || trim == "") {
				return null;
			}
			if (i == 3) {
				result += trim;
			} else {
				result = result + trim + ".";
			}
		}
		return result;

	}

	// 获取 输出流
	public static OutputStream getOutputStream(String ip, int port) {
		String key = ip + ":" + port;
		if (osm.get(key) == null) {
			Socket socket = getSocket(ip, port);
			if (null != socket) {
				try {
					OutputStream os = socket.getOutputStream();
					osm.put(key, os);
					return os;
				} catch (IOException e) {
					System.out.println("创建--OUT--流失败");
					e.printStackTrace();
				}
			}
		}
		return osm.get(key);
	}

	// 获取 socket
	public static Socket getSocket(String ip, int port) {
		String key = ip + ":" + port;
		if (sm.get(key) == null) {
			try {
				Socket socket = new Socket(ip, port);
				sm.put(key, socket);
				return socket;
			} catch (Exception e) {
				System.out.println("创建--socket--失败");
				e.printStackTrace();
			}
		}
		return sm.get(key);
	}

	// 获取 输入流
	public static InputStream getInputStream(String ip, int port) {
		String key = ip + ":" + port;
		InputStream is = ism.get(key);
		if (is == null) {
			Socket socket = getSocket(ip, port);
			if (null != socket) {
				try {
					is = socket.getInputStream();
					ism.put(key, is);
					return is;
				} catch (IOException e) {
					System.out.println("创建--IN--流失败");
					e.printStackTrace();
				}
			}
		}

		return ism.get(key);
	}

	// 关闭连接
	public static void closeConnection(String ip, int port) {
		String key = ip + ":" + port;
		InputStream is = ism.get(key);
		OutputStream os = osm.get(key);
		Socket socket = sm.get(key);
		try {
			is.close();
			os.close();
			socket.close();
			ism.remove(key);
			osm.remove(key);
			sm.remove(key);
		} catch (IOException e) {
			System.out.println("资源关闭失败");
			e.printStackTrace();
		}
	}

	public static void test1(String ip, int port, byte[] ml, byte result[]) {

		OutputStream os = getOutputStream(ip, port);
//		System.out.print(os + "	");
		if (null != os) {
			try {
				os.write(ml);
				os.write("哈哈".getBytes());
				InputStream is = getInputStream(ip, port);
//				System.out.println(is + "	");
				is.read(result);
				parseData(result);
			} catch (Exception e) {
				e.printStackTrace();
				closeConnection(ip, port);
			}
		} else {
			System.out.println("无法创建连接,请检查网关是否在线");
		}
	}

	public static void main(String[] args) {
		byte ml[] = { 0x15, 0x01, 0x00, 0x00, 0x00, 0x06, 0x05, 0x03, 0x00, 0x00, 0x00, 0x10 };
		String ip = "192.168.0.222";
		int port = 502;
		byte result[] = new byte[1024];
		while (true) {
			test1(ip, port, ml, result);
//			System.out.println("");
			// 线程休眠10秒
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(1000);
//					System.out.print(i + 1 + "	");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			System.out.println();
		}

	}

}
