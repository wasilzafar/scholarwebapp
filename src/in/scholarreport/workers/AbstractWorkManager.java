package in.scholarreport.workers;

import java.util.concurrent.*;

public class AbstractWorkManager {
	ExecutorService tpe;
	public AbstractWorkManager(int crs, int mps, int kat) {
		tpe = new ThreadPoolExecutor(crs, mps, kat,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
				new WorkerFactory(), new WorkRejectionHandler());}

}
