package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SwitchLoginAction extends ActionSupport implements SessionAware {

	static Logger logger = Logger.getLogger(SwitchLoginAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Override
	public String execute() throws Exception {
		String current;
		String role;
		SupervisorDTO sup;
		current = (String)sessionMap.get("loggedinAs");
		sup = (SupervisorDTO)sessionMap.get("user");
		role = sup.getRole();
		if(current.equalsIgnoreCase("supervisor")){
			sessionMap.remove("loggedinAs");
			sessionMap.put("loggedinAs", role);
		}else{
			sessionMap.remove("loggedinAs");
			sessionMap.put("loggedinAs", "supervisor");
		}
		
		return super.execute();
	}

}
