package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class DepartmentAdminAction extends ActionSupport implements
		SessionAware {
	static Logger logger = Logger.getLogger(DepartmentAdminAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {
		List dep;
		BaseDelegate delegate = new BaseDelegate();
		String role = (String) sessionMap.get("loggedinAs");
		SupervisorDTO user = (SupervisorDTO) sessionMap.get("user");
		if (role.equalsIgnoreCase("SUPERADMIN")) {
			dep = (ArrayList) delegate.getDepartments();
			sessionMap.put("departments", dep);
		} else if (role.equalsIgnoreCase("ADMIN")) {
			dep = (ArrayList) delegate.getDepartmentsInstitutewise(user
					.getInstitute().getInstituteid());
			sessionMap.put("departments", dep);
		} else if (role.equalsIgnoreCase("DEAN")) {
			dep = (ArrayList) delegate.getDepartmentsFacultywise(user
					.getFaculty().getFacultyid());
			sessionMap.put("departments", dep);
		}

		return super.execute();
	}

}
