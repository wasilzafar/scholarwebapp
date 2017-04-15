package in.scholarreport.struts2.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import in.scholarreport.colloquium.ColloquiumManager;
import in.scholarreport.struts2.DTO.ColloquiumDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SubmitColloquiumAction extends ActionSupport implements ModelDriven,SessionAware {
	static Logger logger = Logger.getLogger(SubmitColloquiumAction.class);
	ColloquiumDTO coll = new ColloquiumDTO();
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public ColloquiumDTO getModel() {
		return coll;
	}
	
	@Override
	public String execute() throws Exception {
		SupervisorDTO user = (SupervisorDTO) sessionMap.get("user");
		logger.info("Entering new colloquium");
		logger.info("Scholar Id length : "+getModel().getScholarID().toArray().length);
		logger.info("Notify flag : "+getModel().isNotifySupervisors());
		getModel().setCreatedby(user.getSupervisorID());
		ColloquiumManager manager = new ColloquiumManager(getModel());
		String template = manager.prepareNotificationTemplate();
		logger.info("Email template "+ template);
		String allParticipants = manager.getParticipants(user.getDepartment().getDepartmentid());
		logger.info("All participants : "+allParticipants);
		manager.notifyParticipants(template, allParticipants);
		manager.persist();
		return super.execute();
	}
}
