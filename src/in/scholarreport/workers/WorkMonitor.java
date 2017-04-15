package in.scholarreport.workers;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

public class WorkMonitor implements Runnable {

	static Logger logger = Logger.getLogger(WorkMonitor.class);
	
	private ThreadPoolExecutor executor;
    
    private int seconds;
     
    private boolean run=true;
 
    public WorkMonitor(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds=delay;
    }
     
    public void shutdown(){
        this.run=false;
    }
 
    public void run()
    {
        while(run){
        	logger.info(
                    String.format("[ Monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(),
                        this.executor.getCorePoolSize(),
                        this.executor.getActiveCount(),
                        this.executor.getCompletedTaskCount(),
                        this.executor.getTaskCount(),
                        this.executor.isShutdown(),
                        this.executor.isTerminated()));
                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
             
    }

}
