package com.lyyh.fertilizer.threadTask;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("programMode")
@Scope("prototype")
public class StartCheckPlc implements Runnable {

	@Autowired
	Scheduler startQuertz;

	@Autowired
	JobDetail checkPlcValveJobDetail;

	@Autowired
	Trigger checkPlcValveCronTrigger;

	@Override
	public void run() {
		try {
			while (!startQuertz.checkExists(new JobKey("checkPlcValveJobDetail"))) {
				try {
					startQuertz.scheduleJob(checkPlcValveJobDetail, checkPlcValveCronTrigger);
//					System.out.println("开启定时检查PLC");
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
