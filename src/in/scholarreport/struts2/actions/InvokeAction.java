package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.ReportDelegate;
import in.scholarreport.struts2.Workflow.ReportWorkflowHandler;
import in.scholarreport.struts2.um.util.Authorizer;
import in.scholarreport.struts2.util.CommonUtilities;
import in.scholarreport.struts2.util.InvocationRouter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.StreamResult;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

public class InvokeAction implements Action,SessionAware {
	static Logger logger = Logger.getLogger(InvokeAction.class);
	private String object;
	private String operation;
	private String input;
	private InputStream inputStream;
	private String content;
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	public InputStream getInputStream() throws Exception {
		Result result = ActionContext.getContext().getActionInvocation()
				.getResult();
		if (result != null && result instanceof StreamResult) {
			logger.info("Stream result available .");
			StreamResult streamResult = (StreamResult) result;
			if (!operation.equalsIgnoreCase("view"))
				streamResult.setContentType(content);
			else {
				logger.info("Setting file properties ");
				streamResult.setContentDisposition("attachment;filename="+content);
				streamResult.setContentType(CommonUtilities.getContentTypeMapping(content));
			}
		}
		return new BufferedInputStream(inputStream);
	}

	public String execute() throws Exception {
		String invoke = null;
		String role;
		StringBuffer sb = new StringBuffer("<response>");
		boolean permitted = false;
		ValueStack vs = ActionContext.getContext().getValueStack();
		String object = (String) vs.findValue("object");
		String operation = (String) vs.findValue("operation");
		String input = (String) vs.findValue("input");
		logger.info("Asynchronous parameters : " + getObject() + " "
				+ getOperation() + " " + getInput());
		String loggedInUser = (String) sessionMap.get("loggedinAs");
		if(!loggedInUser.equalsIgnoreCase("Scholar"))
			role = ((SupervisorDTO) sessionMap.get("user")).getRole();
		else
			role="Scholar";
		if(operation.equalsIgnoreCase("approve") || operation.equalsIgnoreCase("revise")){
			operation = ReportWorkflowHandler.invoke(ReportWorkflowHandler.getDefaultWorkflowStatus(role));
		}
		String permit = object+" "+operation;
		logger.info("Permit : " + permit);
		permitted = Authorizer.authorize(permit,role);
		if(permitted)
		if (!operation.equalsIgnoreCase("view")) {
			invoke = new InvocationRouter().routeInvocation(object, operation,
					input);
			logger.info("Invocation response : " + invoke);
			try {
				if (invoke.equalsIgnoreCase("true")
						&& operation.equalsIgnoreCase("approve"))
					sb.append("<status>APPROVED</status>");
				else if (invoke.equalsIgnoreCase("true")
						&& operation.equalsIgnoreCase("activate"))
					sb.append("<status>ACTIVE</status>");
				else if (invoke.equalsIgnoreCase("true")
						&& operation.equalsIgnoreCase("inactivate"))
					sb.append("<status>INACTIVE</status>");
				else
					sb.append(invoke);
				sb.append("</response>");
				logger.info("Output String : "+sb.toString());
				content = "text/xml";
				inputStream = new ByteArrayInputStream(sb.toString().getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}else {
			File attachment = new File(System.getProperty("user.home", System.getProperty("user.dir")),getInput());
			logger.info("Found file : " +attachment.getAbsolutePath());
			content = new ReportDelegate().getContentDisposition(getObject(),getInput());
			inputStream = new FileInputStream(attachment);
			return SUCCESS;
		}else{
			logger.warn("Insufficient permission.");
		}
		return SUCCESS;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
