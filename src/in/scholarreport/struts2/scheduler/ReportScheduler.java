package in.scholarreport.struts2.scheduler;

import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;


public class ReportScheduler {
	static Logger logger = Logger.getLogger(ReportScheduler.class);
	static Scheduler scheduler;

	public static void start() {
		logger.info("Inside Scheduler start()");
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error("Error while starting scheduler " + e);
		}
		JobKey jobKeyM = new JobKey("Monthly Report Job",
				"Scholar Report Group");
		JobDetail jobM = JobBuilder.newJob(MonthlyReportJob.class)
				.withIdentity(jobKeyM).build();

		JobKey jobKeyQ = new JobKey("Quarterly Report Job",
				"Scholar Report Group");
		JobDetail jobQ = JobBuilder.newJob(QuarterlyReportJob.class)
				.withIdentity(jobKeyQ).build();

		// configure the scheduler time
		Trigger triggerM = newTrigger()
		        .withIdentity("Repeat Forever Monthly", "Scholar Report Group")
		        .startNow()
		        .withSchedule(simpleSchedule()
		                .withIntervalInSeconds(30)
		                .repeatForever())            
		        .build();

		Trigger triggerQ = newTrigger()
		        .withIdentity("Repeat Forever Quarterly", "Scholar Report Group")
		        .startNow()
		        .withSchedule(simpleSchedule()
		                .withIntervalInSeconds(30)
		                .repeatForever())            
		        .build();

		// schedule it

		try {
			scheduler.scheduleJob(jobM, triggerM);
			scheduler.scheduleJob(jobQ, triggerQ);
		} catch (SchedulerException e) {
			logger.error("Error while scheduling " + e);
		}
		logger.info("End Scheduler start()");
	}
	
	public static void stop(){
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			logger.error("Error stopping scheduler " + e);
		}
	}
}
