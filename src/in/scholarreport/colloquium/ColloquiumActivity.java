package in.scholarreport.colloquium;

import java.util.List;

public interface ColloquiumActivity {
	public static final String SCHEDULED = "scheduled";
	public String prepareNotificationTemplate();
	public String getParticipants(int depid);
	public void notifyParticipants(String template,String participants);
	public boolean persist();
}
