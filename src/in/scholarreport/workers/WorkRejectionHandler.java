package in.scholarreport.workers;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

public class WorkRejectionHandler implements RejectedExecutionHandler {
	static Logger logger = Logger.getLogger(WorkRejectionHandler.class);
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.error(r.toString() + " is rejected");

	}

}
