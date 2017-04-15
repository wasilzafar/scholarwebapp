package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SubmitDepartmentAction extends ActionSupport implements
		ModelDriven {
	static Logger logger = Logger.getLogger(SubmitDepartmentAction.class);
	
	DepartmentDTO dep = new DepartmentDTO();

	public DepartmentDTO getModel() {
		return dep;
	}
	@Override
	public String execute() throws Exception {
		boolean persisted = false;
		logger.info("Adding department "+ getModel().getDepartmentname()+" with faculty "+ getModel().getFaculty().getFacultyid());
		BaseDelegate delegate = new BaseDelegate();
		int exist = delegate.checkExistingDepartment(getModel());
		if(exist > 0){
			addFieldError("departmentname", getText("duplicate.entry"));
			return "input";
		}
		
		persisted = delegate.persistDepartment(getModel());
		return persisted ? super.execute() : "input";
	}
}
