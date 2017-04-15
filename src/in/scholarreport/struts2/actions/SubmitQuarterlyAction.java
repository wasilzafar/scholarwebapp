package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.MonthlyReportDTO;
import in.scholarreport.struts2.DTO.QuarterlyReportDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.ReportDelegate;
import in.scholarreport.struts2.Workflow.ReportWorkflowHandler;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SubmitQuarterlyAction extends ActionSupport implements ModelDriven {
	static Logger logger = Logger.getLogger(SubmitQuarterlyAction.class);
	
	
	QuarterlyReportDTO qReportDto = new QuarterlyReportDTO();

	public QuarterlyReportDTO getModel() {
		return qReportDto;
	}
	
	public String execute() throws Exception {
		ReportDelegate delegate = new ReportDelegate();
		boolean persisted = false;
		SessionMap session = (SessionMap) ActionContext.getContext().getSession();
		ScholarDTO scholar = (ScholarDTO) session.get("user");
		getModel().setScholar(scholar);
		getModel().setStatus(ReportWorkflowHandler.getDefaultWorkflowStatus(scholar.getSupervisor().getRole()));
		persisted = delegate.persistQuarterlyReport(getModel());
		if(persisted){
		File savedFile = new File(System.getProperty("user.home", System.getProperty("user.dir")), getModel().getQreportid());
		logger.info("Attachment file name : "+getModel().getAttachmentFileName() + " by " + scholar.getScholarFirstName() + "  " +scholar.getScholarLastName() +" persisted with name : "+ getModel().getQreportid());
		getModel().getAttachment().renameTo(savedFile);
		return SUCCESS;
		}
		
		return ERROR;
	}
}
