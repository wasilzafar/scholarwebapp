package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SearchCriteriaDTO;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SearchReportAction extends ActionSupport implements SessionAware,RequestAware,ModelDriven {
	static Logger logger = Logger.getLogger(SearchReportAction.class);
	private Map requestMap;
	private Map sessionMap;
	SearchCriteriaDTO criterion = new SearchCriteriaDTO();

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setRequest(Map requestMap) {
		this.requestMap = requestMap;
		
	}
	@Override
	public SearchCriteriaDTO getModel() {
		return criterion;
	}
	
	@Override
	public String execute() throws Exception {
		logger.info("DepID....."+getModel().getDepartmentName());
		logger.info("SupID....."+getModel().getSupervisorID());
		logger.info("SchType....."+getModel().getScholarType());
		logger.info("From ....."+getModel().getFromDate());
		logger.info("To....."+getModel().getToDate());
		return super.execute();
	}

}
