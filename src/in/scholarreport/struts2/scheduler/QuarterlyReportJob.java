package in.scholarreport.struts2.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuarterlyReportJob implements Job {
	static Logger logger = Logger.getLogger(QuarterlyReportJob.class);
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Scheduling Quarterly Report");

	}

}
