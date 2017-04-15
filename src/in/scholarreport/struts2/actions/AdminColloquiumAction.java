package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminColloquiumAction extends ActionSupport implements SessionAware {
	static Logger logger = Logger.getLogger(AdminColloquiumAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Override
	public String execute() throws Exception {
		List colls;
		BaseDelegate delegate = new BaseDelegate();
		String role = (String) sessionMap.get("loggedinAs");
		SupervisorDTO user = (SupervisorDTO) sessionMap.get("user");
		colls = (ArrayList) delegate.getColloquiumForAdmin(user.getSupervisorID());
		sessionMap.put("colls", colls);
		return super.execute();
	}
}
