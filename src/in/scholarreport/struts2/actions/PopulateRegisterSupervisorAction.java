package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.InstituteDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.um.util.Authorizer;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PopulateRegisterSupervisorAction extends ActionSupport implements
		SessionAware {
	static Logger logger = Logger.getLogger(PopulateRegisterSupervisorAction.class);
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() throws Exception {
		BaseDelegate delegate = new BaseDelegate();
		List ins = null;
		InstituteDTO insdto;
		Map session = ActionContext.getContext().getSession();
		Map statDistMap = delegate.getDistrictsStateswise();
		SupervisorDTO user = (SupervisorDTO)session.get("user");
		String currentRole = user.getRole();
		String[] availableRoles =  Authorizer.getRoles(currentRole);
		if(currentRole.equalsIgnoreCase("SUPERADMIN")){
		ins =(ArrayList)delegate.getInstitutes();
		}else if(currentRole.equalsIgnoreCase("ADMIN") || currentRole.equalsIgnoreCase("DEAN") || currentRole.equalsIgnoreCase("HEAD")){
			insdto =(InstituteDTO)delegate.getInstituteById(user.getInstitute().getInstituteid());
			if(insdto != null ){
				ins = new ArrayList();
			ins.add(insdto);
			}
		}
		sessionMap.put("institutes", ins);
		sessionMap.put("statDistMap", statDistMap);
		sessionMap.put("roles", CommonUtilities.convertStringArrayToList(availableRoles));
		return super.execute();
	}


}
