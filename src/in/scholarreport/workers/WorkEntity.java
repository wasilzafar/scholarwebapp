package in.scholarreport.workers;

public abstract class WorkEntity implements Runnable {
	
	public abstract void execute();
	
	public void run() {
		execute();
	}

}
