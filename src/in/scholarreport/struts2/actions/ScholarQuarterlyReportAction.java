package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.ReportDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ScholarQuarterlyReportAction extends ActionSupport implements
		SessionAware {

	static Logger logger = Logger.getLogger(ScholarQuarterlyReportAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	public String execute() throws Exception {
		ReportDelegate delegate = new ReportDelegate();
		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
		ScholarDTO sch = (ScholarDTO)session.get("user");
		List reps = (ArrayList) delegate.getQuarterlyReportsByScholarID(sch.getScholarID());
		sessionMap.put("reps", reps);
		return super.execute();
	}
}
