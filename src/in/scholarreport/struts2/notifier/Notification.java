package in.scholarreport.struts2.notifier;


public abstract class Notification {
	int NOTIFICATION_TYPE;
	public Notification(int type) {
		NOTIFICATION_TYPE = type;
	}
abstract public void send();
}
