package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.ScholarDelegate;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SignUpAction extends ActionSupport implements ModelDriven {
	
	static Logger logger = Logger.getLogger(SignUpAction.class);
	ScholarDTO scholar = new ScholarDTO();

	public ScholarDTO getModel() {
		return scholar;
	}
	
	@Override
	public String execute() throws Exception {
		boolean persisted,allSet = false;
		ScholarDelegate delegate = new ScholarDelegate();
		int duplicateCount = delegate.checkExistingScholar(getModel());
		if(duplicateCount > 0){
			addFieldError("scholarFirstName",getText("duplicate.entry"));
			addFieldError("scholarMiddleName",getText("duplicate.entry"));
			addFieldError("scholarLastName",getText("duplicate.entry"));
			addFieldError("emailID",getText("duplicate.entry"));
			addFieldError("enrollmentNumber",getText("duplicate.entry"));
			return "input";
		}
		
		persisted = delegate.persistScholar(getModel());
		
		if(persisted)
			allSet = delegate.setScholar(getModel());
		return super.execute();
	}
	
}
