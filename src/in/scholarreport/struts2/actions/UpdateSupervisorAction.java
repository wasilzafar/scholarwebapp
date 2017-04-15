package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateSupervisorAction extends ActionSupport implements
		ModelDriven {
	static Logger logger = Logger.getLogger(UpdateSupervisorAction.class);
	SupervisorDTO supervisor = new SupervisorDTO();

	public SupervisorDTO getModel() {
		return supervisor;
	}

	public String execute() throws Exception {
		BaseDelegate delegate =  new SupervisorDelegate();
		boolean done = ((SupervisorDelegate) delegate).updateSupervisor(getModel());
		if(done)
		return SUCCESS;
		
		return ERROR;
	}
}
