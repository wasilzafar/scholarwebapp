package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.ReportDelegate;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupervisorQuarterlyReportAction extends ActionSupport implements
		SessionAware {
	static Logger logger = Logger.getLogger(SupervisorQuarterlyReportAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Override
	public String execute() throws Exception {
		List reps;
		ReportDelegate delegate = new ReportDelegate();
		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
		SupervisorDTO sup = (SupervisorDTO)session.get("user");
		String currentRole = (String) sessionMap.get("loggedinAs");
		
		if (currentRole.equalsIgnoreCase("SUPERADMIN")) {
			reps = delegate.getQuarterlyReports();
		} else if (currentRole.equalsIgnoreCase("ADMIN")) {
			reps = delegate.getQuarterlyReportsByInstituteId(sup.getInstitute().getInstituteid());
		} else if (currentRole.equalsIgnoreCase("DEAN")) {
			reps = delegate.getQuarterlyReportsByFacultyId(sup.getFaculty().getFacultyid());
		}else if (currentRole.equalsIgnoreCase("HEAD")){
			reps = delegate.getQuarterlyReportsByDepartmentId(sup.getDepartment().getDepartmentid());
		}else {
			reps = delegate.getQuarterlyReportsBySuperviosrID(sup.getSupervisorID());
		}
		session.put("reps", reps);		
		return super.execute();
	}

}
