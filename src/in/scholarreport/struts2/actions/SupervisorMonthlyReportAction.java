package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.ReportDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupervisorMonthlyReportAction extends ActionSupport implements SessionAware {
	static Logger logger = Logger.getLogger(SupervisorMonthlyReportAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Override
	public String execute() throws Exception {
		List reps;
		ReportDelegate delegate = new ReportDelegate();
		SupervisorDTO sup = (SupervisorDTO)sessionMap.get("user");
		String currentRole = (String) sessionMap.get("loggedinAs");
		
		
		
		
		
		if (currentRole.equalsIgnoreCase("SUPERADMIN")) {
			reps = delegate.getMonthlyReports();
		} else if (currentRole.equalsIgnoreCase("ADMIN")) {
			reps = delegate.getMonthlyReportsByInstituteId(sup.getInstitute().getInstituteid());
		} else if (currentRole.equalsIgnoreCase("DEAN")) {
			reps = delegate.getMonthlyReportsByFacultyId(sup.getFaculty().getFacultyid());
		}else if (currentRole.equalsIgnoreCase("HEAD")){
			reps = delegate.getMonthlyReportsByDepartmentId(sup.getDepartment().getDepartmentid());
		}else {
			reps = delegate.getMonthlyReportsBySupervisorID(sup.getSupervisorID());
		}
		sessionMap.put("reps", reps);
		return super.execute();
	}

}
