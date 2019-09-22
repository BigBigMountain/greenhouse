package com.lyyh.greenhouse.threadTask;

import java.util.Date;
import java.util.Scanner;

import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class Myjob {
//	private static String[] ctx = new String[] { "/applicationContext.xml" };

	public void work() {
		System.out.println("Quartz的任务调度！！！" + (new Date()).toString());
	}

	/**
	 * @param args
	 * @throws SchedulerException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		System.out.println("Test start.");
		ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
		// 如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
		System.out.println("请输入信息：");
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		StdScheduler schedule = (StdScheduler) ApplicationContext.getBean("startQuertz");
		schedule.start();
		System.out.println(x);
		System.out.print("任务开始..");
		Thread.sleep(10000);
		schedule.pauseAll();
		System.out.println("关闭了");
		Thread.sleep(10000);
		schedule.resumeAll();
		System.out.println("又开启了");
		Thread.sleep(10000);
		schedule.pauseAll();
		System.out.println("又关闭了,,,,");
		

	}
}
