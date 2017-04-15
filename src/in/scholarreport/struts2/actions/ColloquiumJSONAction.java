package in.scholarreport.struts2.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.ScholarDelegate;

import com.opensymphony.xwork2.ActionSupport;

public class ColloquiumJSONAction extends ActionSupport implements SessionAware{
	static Logger logger = Logger.getLogger(ColloquiumJSONAction.class);
	private List scholars;
	private Map sessionMap;

	public void setSession(Map sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	private String q;
	public List<ScholarDTO> getScholars() {
		return scholars;
	}

	public void setScholars(List<ScholarDTO> scholars) {
		this.scholars = scholars;
	}
	@Override
	public String execute() throws Exception {
		scholars = new ArrayList();
		SupervisorDTO user = (SupervisorDTO) sessionMap.get("user");
		ScholarDelegate schDel = new ScholarDelegate();
		List tempList = schDel.searchScholarByNameForDepartment(getQ(),user.getDepartment().getDepartmentid());
		for(Object sch : tempList){
			StringBuffer name =  new StringBuffer("");
			ScholarDTO scholar = (ScholarDTO)sch;
			Map tempMap = new HashMap();
			if(scholar.getScholarFirstName()!= null)
			name.append(scholar.getScholarFirstName()).append(" ");
			if(scholar.getScholarMiddleName()!= null)
				name.append(scholar.getScholarMiddleName()).append(" ");
			if(scholar.getScholarLastName()!= null)
				name.append(scholar.getScholarLastName());
			tempMap.put("scholarname", name.toString());
			name = new StringBuffer("");
			if(scholar.getSupervisor().getSupervisorFirstName()!= null)
				name.append(scholar.getSupervisor().getSupervisorFirstName()).append(" ");
			if(scholar.getSupervisor().getSupervisorLastName()!= null)
				name.append(scholar.getSupervisor().getSupervisorLastName());
			tempMap.put("supervisorname", name.toString());
			name = new StringBuffer("");
			if(scholar.getCoSupervisor().getSupervisorFirstName()!= null)
				name.append(scholar.getCoSupervisor().getSupervisorFirstName()).append(" ");
			if(scholar.getSupervisor().getSupervisorLastName()!= null)
				name.append(scholar.getCoSupervisor().getSupervisorLastName());
			tempMap.put("cosupervisorname", name.toString());
			tempMap.put("topic", scholar.getTopic());
			tempMap.put("id", scholar.getScholarID());
			scholars.add(tempMap);
			tempMap = null;
		}
		return super.execute();
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
