package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.Delegate.BaseDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class PopulateRegisterScholarAction extends ActionSupport implements
		SessionAware {
	
	static Logger logger = Logger.getLogger(PopulateRegisterScholarAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Override
	public String execute() throws Exception {
		BaseDelegate delegate = new BaseDelegate();
		Map statDistMap = delegate.getDistrictsStateswise();
		List ins =(ArrayList)delegate.getInstitutes();
		sessionMap.put("loggedinAs", "Scholar");
		sessionMap.put("institutes", ins);
		sessionMap.put("statDistMap", statDistMap);
		return super.execute();
	}

}
