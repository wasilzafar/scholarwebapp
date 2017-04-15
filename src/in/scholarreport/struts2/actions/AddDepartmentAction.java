package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AddDepartmentAction extends ActionSupport implements SessionAware {
	static Logger logger = Logger.getLogger(AddDepartmentAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {
		Map fac = new HashMap();
		SupervisorDTO user = (SupervisorDTO)sessionMap.get("user");
		String currentRole = user.getRole();
		BaseDelegate delegate = new BaseDelegate();
		if(currentRole.equalsIgnoreCase("SUPERADMIN")){
			fac =(HashMap)delegate.getFacultyInstitutewise();					
		}else if(currentRole.equalsIgnoreCase("ADMIN") || currentRole.equalsIgnoreCase("DEAN") || currentRole.equalsIgnoreCase("HEAD")){
			fac =(HashMap)delegate.getFacultyInstitutewise(user.getInstitute().getInstituteid());	
			}
		sessionMap.put("institutesfacultiesmap", fac);
		return super.execute();
	}

}
