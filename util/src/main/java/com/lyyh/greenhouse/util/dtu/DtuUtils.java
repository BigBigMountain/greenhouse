package com.lyyh.greenhouse.util.dtu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map.Entry;
import java.util.Set;

public class DtuUtils {

	/*
	 * 把新接收到的链接加入连接池, 如果新链接的dtuCode已经存在连接池中,则判断新链接是否与已存在的链接是否同一个对象,
	 * 如果为同一个对象,不做任何操作, 如果不是同一个对象,则替换为新链接,并把旧链接销毁;
	 */
	public static String addClientToDtuPool(Socket client) {
		InputStream is = null;
		OutputStream os = null;
		String dtuCode = null;
		String validation = null;
		try {
			is = client.getInputStream();
			os = client.getOutputStream();
			System.out.println(os);
			System.out.println(is);
			client.setKeepAlive(true);
			client.setSoTimeout(1000 * 5);
			byte read[] = new byte[200];
//			System.out.println("等待第一次read");
			int length = is.read(read);
//			System.out.println(new String(read, 0, length));
			validation = new String(read,0,length).split(" ")[0];
			if(validation.equalsIgnoreCase("sfj")){
				dtuCode = new String(read, 0, length).split(" ")[1];
				DtuPool.addOutputStream(dtuCode, os);
				DtuPool.addInputStream(dtuCode, is);
				DtuPool.addclient(dtuCode, client);
			}
		} catch (Exception e) {
			e.printStackTrace();
			closeConnection(os,is,client);
		}
		return dtuCode;

	}

	public static void destroyDtuById(String dtuCode) {
		DtuPool.destroyDtuById(dtuCode);
	}

	public static Socket getSocketById(String dtuCode) {
		return DtuPool.getSocketByDtuCode(dtuCode);
	}

	public static InputStream getInputStreamById(String dtuCode) {
		return DtuPool.getInputStreamByDtuCode(dtuCode);
	}

	public static OutputStream getOutputStreamById(String dtuCode) {
		return DtuPool.getOutStreamByDtuCode(dtuCode);
	}

	public static Set<Entry<String, OutputStream>> getAllDtuEntry() {
		return DtuPool.getOsRegisty().entrySet();
	}

	public static void closeConnection(OutputStream os,InputStream is,Socket client){
		try{
			if(null != os){
				os.close();
			}
		} catch (Exception e1){
			e1.printStackTrace();
			System.out.println("新连接的output流关闭失败");
		}
		try {
			if (null != is) {
				is.close();
			}
		} catch (Exception e2) {
			System.out.println("新连接的input流关闭失败");
			e2.printStackTrace();
		}
		try {
			if (null != client) {
				client.close();
			}
		} catch (Exception e3) {
			System.out.println("新连接的socket关闭失败");
			e3.printStackTrace();
		}
	}
}
