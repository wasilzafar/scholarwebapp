package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.MonthlyReportDTO;
import in.scholarreport.struts2.DTO.QuarterlyReportDTO;
import in.scholarreport.struts2.Delegate.ReportDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewReportAction extends ActionSupport implements
		ServletRequestAware {
	static Logger logger = Logger.getLogger(ViewReportAction.class);
	private HttpServletRequest servletRequest; 
	private String type;
	private String id;
	@Override
	public String execute() throws Exception {
		MonthlyReportDTO mreport;
		QuarterlyReportDTO qreport;
		ReportDelegate delegate = new ReportDelegate();
		String type = getType();
		String id = getId();
		logger.info("Asynchronous parameters : "+type+" "+id);
		if(type.equalsIgnoreCase("monthlyreport")){
			mreport = delegate.getMonthlyReportByID(id);
			servletRequest.setAttribute("rep", mreport);
		}else if(type.equalsIgnoreCase("quarterlyreport")){
			qreport = delegate.getQuarterlyReportByID(id);
			servletRequest.setAttribute("rep", qreport);
		}
		if(getType().equalsIgnoreCase("monthlyreport"))
		servletRequest.setAttribute("reportType", "Monthly Report");
		else
			servletRequest.setAttribute("reportType", "Quarterly Report");
		return super.execute();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
		
	}
}
