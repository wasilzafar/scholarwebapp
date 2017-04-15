package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateFacultyAction extends ActionSupport implements ModelDriven {
	static Logger logger = Logger.getLogger(UpdateFacultyAction.class);
	FacultyDTO faculty = new FacultyDTO();

	public FacultyDTO getModel() {
		return faculty;
	}

	public String execute() throws Exception {
		BaseDelegate delegate =  new BaseDelegate();
		boolean done = delegate.updateFaculty(getModel());
		if(done)
		return SUCCESS;
		
		return ERROR;
	}
}
