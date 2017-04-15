package in.scholarreport.spring3;

import org.apache.log4j.Logger;
	
public class Juggler implements Performer {
	static Logger logger = Logger.getLogger(Juggler.class);
	private int beanBags = 3;

	public Juggler() {
	}

	public Juggler(int beanBags) {
		this.beanBags = beanBags;

	}

	public void perform() {
		logger.info("JUGGLING " + beanBags + "BEANBAGS");
		System.out.println("JUGGLING " + beanBags + "BEANBAGS");

	}
}
