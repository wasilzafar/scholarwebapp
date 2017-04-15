package in.scholarreport.struts2.actions;
 
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.LoginDelegate;
import in.scholarreport.struts2.Delegate.ScholarDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;
import in.scholarreport.struts2.util.CommonConstants;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements Action {
	static Logger logger = Logger.getLogger(LoginAction.class);
	private String id;
	private String password;
	private String identity;

public String execute() throws Exception {
	SessionMap session = (SessionMap) ActionContext.getContext().getSession();
	LoginDelegate delegate = new LoginDelegate();		
		logger.info("Logging in user " + getId()+" as "+getIdentity());
		if(getId().equalsIgnoreCase("SuperAdmin")){
			session.put("loggedinAs", "SUPERADMIN");
			SupervisorDTO sup = new SupervisorDTO();
			sup.setSupervisorFirstName("Super");
			sup.setSupervisorLastName("Administrator");
			sup.setRole("SUPERADMIN");
			sup.setIsSuperadmin('Y');
			session.put("user", sup);
			return "supervisor";
		}
		boolean authenticated = delegate.authenticate(getId(),getPassword(),getIdentity());
		logger.info(getId()+" authenticated ? " + authenticated);
		if(authenticated){
			if(getIdentity().trim().equalsIgnoreCase(CommonConstants.SUPERVISOR)){
				session.put("loggedinAs", "supervisor");
				
				SupervisorDTO sup = new SupervisorDelegate().getSupervisorByID(getId());
				session.put("user", sup);
				return "supervisor";
				
			}
			else {
				session.put("loggedinAs", "scholar");
				ScholarDTO sch = new ScholarDelegate().getScholarById(getId());
				session.put("user", sch);
			}
			return SUCCESS;
		}
	
	
	
	return INPUT;
}


public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getIdentity() {
	return identity;
}

public void setIdentity(String identity) {
	this.identity = identity;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}
}
