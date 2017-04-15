package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateDepartmentAction extends ActionSupport implements
		ModelDriven {
	static Logger logger = Logger.getLogger(UpdateDepartmentAction.class);
	DepartmentDTO department = new DepartmentDTO();

	public DepartmentDTO getModel() {
		return department;
	}

	public String execute() throws Exception {
		BaseDelegate delegate =  new BaseDelegate();
		boolean done = delegate.updateDepartment(getModel());
		if(done)
		return SUCCESS;
		
		return ERROR;
	}
}
