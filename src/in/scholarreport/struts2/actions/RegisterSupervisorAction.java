package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterSupervisorAction extends ActionSupport implements ModelDriven {
	
	static Logger logger = Logger.getLogger(RegisterSupervisorAction.class);
	SupervisorDTO supervisor = new SupervisorDTO();

	public SupervisorDTO getModel() {
		return supervisor;
	}
	
	@Override
	public String execute() throws Exception {
		boolean persisted,allSet = false;		
		SupervisorDelegate delegate = new SupervisorDelegate();
		int duplicateCount = delegate.checkExistingSupervisor(getModel());
		if(duplicateCount > 0){
			addFieldError("supervisorFirstName",getText("supervisor.duplicateEntry.exception"));
			addFieldError("supervisorMiddleName",getText("supervisor.duplicateEntry.exception"));
			addFieldError("supervisorLastName",getText("supervisor.duplicateEntry.exception"));
			addFieldError("emailID",getText("supervisor.duplicateEntry.exception"));
			return "input";
		}
		persisted = delegate.persistSupervisor(getModel());
		if(persisted)
			allSet = delegate.setSupervisor(getModel());
		return super.execute();
	}

	public SupervisorDTO getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(SupervisorDTO supervisor) {
		this.supervisor = supervisor;
	}
}
