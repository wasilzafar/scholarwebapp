package in.scholarreport.struts2.interceptors;

import in.scholarreport.struts2.actions.LoginAction;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RequestInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		Map session = invocation.getInvocationContext().getSession();
		String loggedIn = (String) session.get("loggedinAs");
		Action action = (Action) invocation.getAction();
		if (action instanceof LoginAction) {
			invocation.invoke();
		} else if (loggedIn == null) {
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
