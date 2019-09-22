package com.lyyh.greenhouse.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {

	private static ExecutorService service = Executors.newFixedThreadPool(20); 
	
	public static ExecutorService getService(){
		return service;
	}
	public static void execute(Runnable task){
		service.execute(task);
	}
	
}
