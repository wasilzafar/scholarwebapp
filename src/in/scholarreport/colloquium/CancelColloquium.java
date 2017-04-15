package in.scholarreport.colloquium;

import java.util.List;

public class CancelColloquium implements ColloquiumActivity {
	ColloquiumManager manager;
	public CancelColloquium(ColloquiumManager manager) {
		this.manager = manager;
	}
	public String prepareNotificationTemplate() {
		return null;
	}

	public String getParticipants(int depid) {
		return null;

	}

	public void notifyParticipants(String template, String participants) {
		
	}
	public boolean persist() {
		return false;
	}

}
