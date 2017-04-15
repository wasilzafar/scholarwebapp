package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.ScholarDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminScholarAction extends ActionSupport implements SessionAware {
	static Logger logger = Logger.getLogger(AdminScholarAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public String execute() throws Exception {
		ScholarDelegate delegate = new ScholarDelegate();
		List sch ;
		String role = (String) sessionMap.get("loggedinAs");
		SupervisorDTO user = (SupervisorDTO) sessionMap.get("user");
		
		
		if (role.equalsIgnoreCase("SUPERADMIN")) {
			sch = (ArrayList) delegate.getScholars();
			sessionMap.put("scholars", sch);
		} else if (role.equalsIgnoreCase("ADMIN")) {
			sch = (ArrayList) delegate.getScholarsInstituteWise(user.getInstitute().getInstituteid());
			sessionMap.put("scholars", sch);
		} else if (role.equalsIgnoreCase("DEAN")) {
			sch = (ArrayList) delegate.getScholarsFacultyWise(user.getFaculty().getFacultyid());
			sessionMap.put("scholars", sch);
		} else if (role.equalsIgnoreCase("HEAD")) {
			sch = (ArrayList) delegate.getScholarsDepartmentWise(user.getDepartment().getDepartmentid());
			sessionMap.put("scholars", sch);
		}else if (role.equalsIgnoreCase("Supervisor")) {
			sch = (ArrayList) delegate.getScholarsBySupervisorID(user.getSupervisorID());
			sessionMap.put("scholars", sch);
		}
		
		
		return super.execute();
	}

}
