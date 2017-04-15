package in.scholarreport.colloquium;

import java.util.List;

import org.apache.log4j.Logger;

import in.scholarreport.struts2.DTO.ColloquiumDTO;
import in.scholarreport.struts2.actions.ColloquiumJSONAction;

public class ColloquiumManager {
	static Logger logger = Logger.getLogger(ColloquiumManager.class);
	ColloquiumActivity newColloquium;
	ColloquiumActivity updateColloquium;
	ColloquiumActivity cancelColloquium;
	ColloquiumDTO colloquium;
	ColloquiumActivity currentActivity;

	public ColloquiumManager(ColloquiumDTO colloquium) {
		logger.info("Constructing Colloquium manager ... ");
		this.colloquium = colloquium;
		newColloquium = new NewColloquium(this);
		updateColloquium = new UpdateColloquium(this);
		cancelColloquium = new CancelColloquium(this);
		currentActivity = newColloquium;
		logger.info("Constructed Colloquium manager ");
	}
	
	public ColloquiumActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ColloquiumActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public ColloquiumDTO getColloquium() {
		return colloquium;
	}
	
	public String prepareNotificationTemplate(){
		return currentActivity.prepareNotificationTemplate();
	}
	
	public String getParticipants(int depid){
		return currentActivity.getParticipants(depid);
	}
	
	public void notifyParticipants(String template,String participants){
		currentActivity.notifyParticipants(template, participants);
	}
	public boolean persist(){
		return currentActivity.persist();
	}

	public ColloquiumActivity getNewColloquium() {
		return newColloquium;
	}

	public ColloquiumActivity getUpdateColloquium() {
		return updateColloquium;
	}

	public ColloquiumActivity getCancelColloquium() {
		return cancelColloquium;
	}
}
