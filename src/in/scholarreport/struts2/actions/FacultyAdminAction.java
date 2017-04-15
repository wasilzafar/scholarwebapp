package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FacultyAdminAction extends ActionSupport implements SessionAware {
	
	static Logger logger = Logger.getLogger(FacultyAdminAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {
		BaseDelegate delegate = new BaseDelegate();
		List fac;
		String role = (String)sessionMap.get("loggedinAs");
		SupervisorDTO user = (SupervisorDTO)sessionMap.get("user");
		if(role.equalsIgnoreCase("SUPERADMIN")){
			fac = (ArrayList) delegate.getFaculties();
			sessionMap.put("faculties", fac);
		}
		else if(role.equalsIgnoreCase("ADMIN")){
			fac = (ArrayList)delegate.getFaculties(user.getInstitute().getInstituteid());
		sessionMap.put("faculties", fac);
		}
		return super.execute();
	}

}
