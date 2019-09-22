package com.lyyh.greenhouse.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LGModbusUtils {

	public static List<Integer> parse(byte result[], byte type[]) {
		List<Integer> values = new ArrayList<Integer>();
		int total = (result[8] & 255) + 9;
		int i = 9;// result数组中的索引
		for (byte b : type) {
			if (i >= total) {
				break;
			}
			if (b == 0) {
				i += 2;
				continue;
			}
			Integer value = null;
			if (b == 1) {// 16位无符号
				value = ((result[i] & 255) << 8) + (result[i + 1] & 255);
				i += 2;
			}
			if (b == 2) {// 16位有符号
				value = (result[i] << 8) + (result[i + 1] & 255);
				i += 2;
			}
			if (b == 3) {// 32位无符号
				value = ((result[i] & 255) << 24) + ((result[i + 1] & 255) << 16) + ((result[i + 2] & 255) << 8)
						+ (result[i + 3] & 255);
				i += 4;
			}
			if (b == 4) {// 32位有符号
				value = (result[i] << 24) + ((result[i + 1] & 255) << 16) + ((result[i + 2] & 255) << 8)
						+ (result[i + 3] & 255);
				i += 4;
			}

			if (null != value) {
				values.add(value);
			}
		}
		return values;
	}

	public static byte[] compile(int values[], byte type[], byte MBhead[]) {
		byte[] write = Arrays.copyOf(MBhead, (MBhead[12] & 255) + 13);
//		System.out.println(write.length);
		int i = 13; // write 的索引
		int j = 0; // values 的索引
		for (byte b : type) {
			if (b == 0) {
				write[i] = (byte) 0;
				write[i + 1] = (byte) 0;
				i += 2;
			}
			if (b == 1 || b == 2) {
				write[i] = (byte) (values[j] >> 8);
				write[i + 1] = (byte) values[j];
				i += 2;
				j++;
			}
			if (b == 3 || b == 4) {
				write[i] = (byte) (values[j] >> 24);
				write[i + 1] = (byte) (values[j] >> 16);
				write[i + 2] = (byte) (values[j] >> 8);
				write[i + 3] = (byte) values[j];
				i += 4;
				j++;
			}
		}
		return write;

	}

}
