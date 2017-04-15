package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.ScholarDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateScholarAction extends ActionSupport implements
		ModelDriven {
	static Logger logger = Logger.getLogger(UpdateScholarAction.class);
	ScholarDTO scholar = new ScholarDTO();

	public ScholarDTO getModel() {
		return scholar;
	}

	public String execute() throws Exception {
		BaseDelegate delegate =  new ScholarDelegate();
		boolean done = ((ScholarDelegate) delegate).updateScholar(getModel());
		if(done)
		return SUCCESS;
		
		return ERROR;
	}
}
