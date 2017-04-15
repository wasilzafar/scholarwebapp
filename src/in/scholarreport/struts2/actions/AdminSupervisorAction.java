package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminSupervisorAction extends ActionSupport implements
		SessionAware {
	static Logger logger = Logger.getLogger(AdminSupervisorAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		SupervisorDelegate delegate = new SupervisorDelegate();
		String role = (String) sessionMap.get("loggedinAs");
		SupervisorDTO user = (SupervisorDTO) sessionMap.get("user");
		if (role.equalsIgnoreCase("SUPERADMIN")) {
			List sup = (ArrayList) delegate.getSupevisors();
			sessionMap.put("supervisors", sup);
		} else if (role.equalsIgnoreCase("ADMIN")) {
			List sup = (ArrayList) delegate.getSupevisorsInstitutewise(user
					.getInstitute().getInstituteid());
			sessionMap.put("supervisors", sup);
		} else if (role.equalsIgnoreCase("DEAN")) {
			List sup = (ArrayList) delegate.getSupevisorsFacultywise(user
					.getFaculty().getFacultyid());
			sessionMap.put("supervisors", sup);
		} else if (role.equalsIgnoreCase("HEAD")) {
			List sup = (ArrayList) delegate.getSupevisorsDepartmentwise(user
					.getDepartment().getDepartmentid());
			sessionMap.put("supervisors", sup);
		}
		return super.execute();
	}
}
