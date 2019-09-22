package com.lyyh.fertilizer.threadTask;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "manualMode")
@Scope("prototype")
public class StartManualMode implements Runnable {
	@Autowired
	Scheduler startQuertz;

	@Autowired
	JobDetail checkTzgkValveJobDetail;
	@Autowired
	Trigger checkTzgkValveSimpleTrigger;

	@Override
	public void run() {
//		// 取消定时检测PLC阀的定时任务
//		JobKey checkPlc = new JobKey("checkPlcValveJobDetail");
//		try {
//			while (startQuertz.checkExists(checkPlc)) {
//				try {
//					startQuertz.deleteJob(checkPlc);
//					System.out.println("取消定时检查plc");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (SchedulerException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		// 开始检查TZGK
		try {
			while (!startQuertz.checkExists(new JobKey("checkTzgkValveJobDetail"))) {
				try {
					startQuertz.scheduleJob(checkTzgkValveJobDetail, checkTzgkValveSimpleTrigger);
//					System.out.println("开启定时检查TZGK");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
