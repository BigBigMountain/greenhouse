package com.lyyh.greenhouse.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyh.greenhouse.dao.ControllerSettingDao;
import com.lyyh.greenhouse.pojo.GhController;
import com.lyyh.greenhouse.service.ControllerSettingService;
import com.lyyh.greenhouse.util.LGModbusUtils;

@Service
public class ControllerSettingServiceImpl implements ControllerSettingService {

	@Autowired
	private ControllerSettingDao controllerSettingDao;
	
	@Override
	public List<GhController> queryHouseLeftJoinControllerByZoneId(Integer zoneId) {
		return controllerSettingDao.queryHouseLeftJoinControllerByZoneId(zoneId);
	}
	
	@Override
	public List<GhController> queryControllersByZoneId(Integer zoneId) {
		return controllerSettingDao.queryControllersByZoneId(zoneId);
	}

	@Override
	public GhController queryControllerById(Integer controllerId) {
		return controllerSettingDao.queryControllerById(controllerId);
	}

	@Override
	public String saveController(GhController controller) {
		String msg =null;
		try{
			controllerSettingDao.saveController(controller);
			msg="保存成功";
		}catch(Exception e){
			msg="保存失败!";
		}
		return msg;
	}

	@Override
	public String updateController(GhController controller) {
		String msg =null;
		try{
			controllerSettingDao.updateController(controller);
			msg="更新成功!";
		}catch(Exception e){
			msg="更新失败!";
		}
		return msg;
	}

	@Override
	public List<Integer> readFromController(Integer controllerId,byte readmbz[],byte type[]) throws Exception{
		GhController controller = controllerSettingDao.queryControllerById(controllerId);
		String controllerIp = controller.getIp();
		int controllerPort = controller.getPort();
//		System.out.println(controllerIp+"   "+controllerPort);
		Socket client = null;
		InputStream is = null ;
		OutputStream os = null;
		try {
			client = new Socket(controllerIp, controllerPort);
			os = client.getOutputStream();
			os.write(readmbz);
			is = client.getInputStream();
			byte result[] = new byte[1024];
			is.read(result);
//		for (byte b : result) {
//			System.out.print(b + " ");
//		}
//		System.out.println();
			List<Integer> values = LGModbusUtils.parse(result, type);
			return values;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(null != is){
					is.close();
				}
				if(null != os){
					os.close();
				}
				if(null != client){
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeToController(Integer controllerId, int[] Values,byte[] type, byte[] writehead) throws UnknownHostException, IOException {
//		for (int i : Values) {
//			System.out.println(i+" ");
//		}
//		System.out.println();
		byte[] write = LGModbusUtils.compile(Values, type, writehead);
//		System.out.println("写的长度: "+write.length);
//		for (byte b : write) {
//			System.out.print(Integer.toHexString(b&255)+" ");
//		}
//		System.out.println();
		GhController controller =controllerSettingDao.queryControllerById(controllerId);
		String controllerIp = controller.getIp();
		int controllerPort = controller.getPort();
		Socket client;
		client = new Socket(controllerIp, controllerPort);
		OutputStream os = client.getOutputStream();
		os.write(write);
		
		byte read [] = new byte[20];
		InputStream is = client.getInputStream();
		int length = is.read(read);
//		for (byte b : read) {
//			System.out.print(Integer.toHexString(b&255) + ",");
//			
//		}
//		System.out.println();
		os.close();
		client.close();
	}
}
