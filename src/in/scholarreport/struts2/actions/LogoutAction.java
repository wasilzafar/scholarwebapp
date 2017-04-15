package in.scholarreport.struts2.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	static Logger logger = Logger.getLogger(LogoutAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {
		sessionMap.remove("user");
		sessionMap.remove("loggedinAs");
		((SessionMap)sessionMap).invalidate();
		logger.info("Logged out successfully ");
		return super.execute();
	}
}
