package com.lyyh.greenhouse.util.dtu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class DtuPool {

	private static HashMap<String, Socket> clientRegisty = new HashMap<String, Socket>();
	private static HashMap<String, InputStream> isRegisty = new HashMap<String, InputStream>();
	private static HashMap<String, OutputStream> osRegisty = new HashMap<String, OutputStream>();

	public static HashMap<String, OutputStream> getOsRegisty() {
		return osRegisty;
	}

	public static synchronized void addclient(String dtuCode, Socket client) {
		Socket socketOld = clientRegisty.put(dtuCode, client);
		if(null != socketOld){
			try{
				socketOld.close();
			} catch(IOException e){
				System.out.println("socketOld: "+ dtuCode+ " 关闭异常");
				e.printStackTrace();
			}
		}
	}

	public static synchronized void addInputStream(String dtuCode, InputStream is) {
		InputStream isOld = isRegisty.put(dtuCode, is);
		if(null != isOld){
			try {
				isOld.close();
			} catch (IOException e) {
				System.out.println("isOld: "+ dtuCode+ " 关闭异常");
				e.printStackTrace();
			}
		}
	}

	public static synchronized void addOutputStream(String dtuCode, OutputStream os) {
		OutputStream osOld = osRegisty.put(dtuCode, os);
		if(null != osOld){
			try {
				osOld.close();
			} catch (IOException e) {
				System.out.println("osOld: "+ dtuCode+ " 关闭异常");
				e.printStackTrace();
			}
		}
	}

	public static synchronized Socket getSocketByDtuCode(String dtuCode) {
		return clientRegisty.get(dtuCode);
	}

	public static synchronized InputStream getInputStreamByDtuCode(String dtuCode) {
		return isRegisty.get(dtuCode);
	}

	public static synchronized OutputStream getOutStreamByDtuCode(String dtuCode) {
		return osRegisty.get(dtuCode);
	}

	public static synchronized void destroyDtuById(String dtuCode) {
		OutputStream os = osRegisty.remove(dtuCode);
		InputStream is = isRegisty.remove(dtuCode);
		Socket client = clientRegisty.remove(dtuCode);
		try {
			if (null != os) {
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (null != is) {
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (null != client) {
			try {
				client.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}

}
