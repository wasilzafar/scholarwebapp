package in.scholarreport.struts2.actions;
import in.scholarreport.struts2.DAO.BaseDAO;
import in.scholarreport.struts2.DTO.MonthlyReportDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.ReportDelegate;
import in.scholarreport.struts2.Workflow.ReportWorkflowHandler;
import in.scholarreport.struts2.util.CommonUtilities;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.StreamResult;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;

public class SubmitMonthlyAction extends ActionSupport implements ModelDriven,Action {
	
	static Logger logger = Logger.getLogger(SubmitMonthlyAction.class);
	MonthlyReportDTO mReportDto = new MonthlyReportDTO();

	public MonthlyReportDTO getModel() {
		return mReportDto;
	}
	
	public String execute() throws Exception {
		ReportDelegate delegate = new ReportDelegate();
		boolean persisted = false;
		SessionMap session = (SessionMap) ActionContext.getContext()
				.getSession();
		ScholarDTO scholar = (ScholarDTO) session.get("user");
		getModel().setScholar(scholar);
		getModel().setStatus(ReportWorkflowHandler.getDefaultWorkflowStatus(scholar.getSupervisor().getRole()));
		persisted = delegate.persistMonthlyReport(getModel());
		if (persisted) {
			File savedFile = new File(System.getProperty("user.home",
					System.getProperty("user.dir")), getModel().getMreportid());
			logger.info("Attachment file name : "
					+ getModel().getAttachmentFileName() + " by "
					+ scholar.getScholarFirstName() + "  "
					+ scholar.getScholarLastName() + " persisted with name : "
					+ getModel().getMreportid());
			getModel().getAttachment().renameTo(savedFile);
			return SUCCESS;
		}

		return ERROR;
	}

}
