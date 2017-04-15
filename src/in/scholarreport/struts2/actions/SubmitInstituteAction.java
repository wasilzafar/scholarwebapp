package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.Delegate.BaseDelegate;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class SubmitInstituteAction extends ActionSupport {
	
	static Logger logger = Logger.getLogger(SubmitInstituteAction.class);
	private String institutename;
	
	@Override
	public String execute() throws Exception {
		BaseDelegate delegate = new BaseDelegate();
		int exist = delegate.checkExistingInstitute(getInstitutename());
		if(exist > 0){
			addFieldError("institutename", getText("duplicate.entry"));
			return "input";
		}
		
		delegate.persistInstitute(getInstitutename());
		return super.execute();
	}

	public String getInstitutename() {
		return institutename;
	}

	public void setInstitutename(String institutename) {
		this.institutename = institutename;
	}
}
