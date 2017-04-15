package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SubmitFacultyAction extends ActionSupport implements ModelDriven {
	
	static Logger logger = Logger.getLogger(SubmitFacultyAction.class);
	FacultyDTO faculty = new FacultyDTO();
	
	public FacultyDTO getModel() {
		return faculty;
	}
	@Override
	public String execute() throws Exception {
		BaseDelegate delegate = new BaseDelegate();
		logger.info("Persisting faculty for "+getModel().getFacultyname()+"  "+getModel().getInstitute().getInstituteid());
		int exist = delegate.checkExistingFaculty(getModel());
		if(exist > 0){
			addFieldError("facultyname", getText("duplicate.entry"));
			return "input";
		}
		
		delegate.persistFaculty(getModel());
		return super.execute();
	}
}
