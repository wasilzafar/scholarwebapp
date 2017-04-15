package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SearchCriteriaAction extends ActionSupport implements RequestAware,SessionAware {
	static Logger logger = Logger.getLogger(SearchCriteriaAction.class);
	private Map requestMap;
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setRequest(Map requestMap) {
		this.requestMap = requestMap;
		
	}

	@Override
	public String execute() throws Exception {
		SupervisorDelegate supdel = new SupervisorDelegate();
		SupervisorDTO user = (SupervisorDTO)sessionMap.get("user");
		//List sup = (List) supdel.getSupevisorsFacultywise(user.getFaculty().getFacultyid());
		//Map dep = ((BaseDelegate)supdel).fetchDepartmentMapByFacultyId(new String[]{ Integer.toString(user.getFaculty().getFacultyid()) });
		//requestMap.put("supervisors", sup);
		//requestMap.put("departments", dep);
		Map supMap = supdel.getSupevisorsDepartmentMap(user.getFaculty().getFacultyid());
		requestMap.put("depSupMap", supMap);
		return super.execute();
	}


}
