package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.InstituteDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateInstituteAction extends ActionSupport implements ModelDriven {
	static Logger logger = Logger.getLogger(UpdateInstituteAction.class);
	InstituteDTO institute = new InstituteDTO();

	public InstituteDTO getModel() {
		return institute;
	}

	public String execute() throws Exception {
		BaseDelegate delegate =  new BaseDelegate();
		boolean done = delegate.updateInstitute(getModel());
		if(done)
		return SUCCESS;
		
		return ERROR;
	}
}
