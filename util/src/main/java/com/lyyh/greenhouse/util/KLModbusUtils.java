package com.lyyh.greenhouse.util;

import java.util.ArrayList;
import java.util.List;

import com.lyyh.greenhouse.pojo.HouseData;

/**
 * 昆仑海岸网关通讯modbus协议工具类
 * 
 * @author lt
 *
 */
public class KLModbusUtils {

	public static List<KLModbusData> parseData(byte[] result) {
		ArrayList<KLModbusData> dataList = new ArrayList<>();
		int channel = 1;
		for (int i = 9; i < (result[8] & 255);) {
			if (i >= result[8] + 8) {
				return dataList;
			}

			KLModbusData klmd = parseData(i, result);
			klmd.setChannel(channel);
			i += klmd.getLength();
			channel += (i - 9) / 4;
		}
		return dataList;
	}

	public static KLModbusData parseData(byte[] result, int channel) {
		int index = (channel-1) * 4 + 9;
		if (index >= result[8] + 8) {
			return null;
		}

		KLModbusData klmd = parseData(index, result);
		klmd.setChannel(channel);
		return klmd;

	}

	public static KLModbusData parseData(int i, byte[] result) {
		KLModbusData klmd = new KLModbusData();
		double divisor = 1;// 除数
		boolean sign = false;// 有无符号,false 无符号, true 有符号
		boolean valueType = false;// false 数值, true 开关量
		boolean longdata = false;// false 双字节, true 四字节
		boolean high = false;// 高地位 false 低2字节 ,true 高2字节

		// 获取数据类型代码
		String hexString = Integer.toHexString(result[i] & 255);
		char[] hexchar = hexString.toCharArray();
		char type[] = new char[] { '0', '0' };
		System.arraycopy(hexchar, 0, type, 2 - hexchar.length, hexchar.length);
		klmd.setType(new String(type));

		// 获取数据解析的定义
		int def = result[i + 1] & 255;
		if (def >= 128) {
			sign = true;
			def -= 128;
		}
		if (def >= 64) {
			valueType = true;
			def -= 64;
		}
		if (def >= 32) {
			longdata = true;
			def -= 32;
		}
		if (def >= 16) {
			high = true;
			def -= 16;
		}
		if (def <= 7) {
			divisor = Math.pow(10, def);
		}

		if (valueType) {// 开关量
			if (result[i + 3] == 0) {
				klmd.setDoubleVal(0.0);
			} else {
				klmd.setDoubleVal(1.0);
			}
		} else {// 述职量
			if (longdata) {// 四字节
				if (sign) {// 有符号
					klmd.setDoubleVal(((result[i + 2] << 24) + ((result[i + 3] & 255) << 16)
							+ ((result[i + 6] & 255) << 8) + (result[i + 7] & 255)) / divisor);
				} else {// 无符号
					klmd.setDoubleVal((((result[i + 2] & 255) << 24) + ((result[i + 3] & 255) << 16)
							+ ((result[i + 6] & 255) << 8) + (result[i + 7] & 255)) / divisor);
				}
				klmd.setLength(8);
				klmd.setI(i + 8);
				return klmd;
			} else {// 双字节
				if (sign) {// 有符号
					klmd.setDoubleVal(((result[i + 2] << 8) + (result[i + 3] & 255)) / divisor);

				} else {// 无符号
					klmd.setDoubleVal((((result[i + 2] & 255) << 8) + (result[i + 3] & 255)) / divisor);
				}
			}
		}
		klmd.setLength(4);
		klmd.setI(i + 4);
		return klmd;
	}
}
