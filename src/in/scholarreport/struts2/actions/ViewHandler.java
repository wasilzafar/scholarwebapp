package in.scholarreport.struts2.actions;

import in.scholarreport.struts2.DTO.ColloquiumDTO;
import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.DTO.DistrictDTO;
import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.DTO.InstituteDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.ScholarDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;
import in.scholarreport.struts2.um.util.Authorizer;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;



public class ViewHandler implements Action, RequestAware {
	static Logger logger = Logger.getLogger(ViewHandler.class);
	static final String COLLOQUIUM = "colloquium";
	static final String INSTITUTE = "institute";
	static final String FACULTY = "faculty";
	static final String DEPARTMENT = "department";
	static final String SCHOLAR = "scholar";
	static final String SUPERVISOR = "supervisor";
	private Map requestMap;
	private String object;
	private String id;

	public void setRequest(Map requestMap) {
		this.requestMap = requestMap;
		
	}
	
	
	public String execute() throws Exception {
		logger.info("Entering inside view handler");
		Map session = ActionContext.getContext().getSession();
		SupervisorDTO user = (SupervisorDTO)session.get("user");
		String currentRole = user.getRole();
		BaseDelegate delegate;
		if(getObject() != null ){
			if(getObject().equalsIgnoreCase(COLLOQUIUM) ){
				logger.info("Inside colloquium");
				ColloquiumDTO coll;
				List scholars;
				delegate = new ScholarDelegate();
				coll = delegate.getColloquiumById((String)(CommonUtilities.getIdsFromInput(getId()).get(0)));
				scholars = ((ScholarDelegate) delegate).getScholarNamesForIds((String[]) coll.getScholarID().toArray());
				requestMap.put("colloq", coll);
				requestMap.put("scholars", scholars);
				return COLLOQUIUM;
			}else if(getObject().equalsIgnoreCase(INSTITUTE) ){
				logger.info("Inside institute");
				InstituteDTO ins;
				delegate = new BaseDelegate();
				ins = (InstituteDTO) delegate.getInstituteById(Integer.parseInt((String) CommonUtilities.getIdsFromInput(getId()).get(0)));
				requestMap.put("ins", ins);
				return INSTITUTE;
			}else if(getObject().equalsIgnoreCase(FACULTY) ){
				logger.info("Inside faculty");
				FacultyDTO fac;
				delegate = new BaseDelegate();
				List ins = new ArrayList();
				InstituteDTO insdto;
				if(currentRole.equalsIgnoreCase("SUPERADMIN")){
				ins =(ArrayList)delegate.getInstitutes();
				}else if(currentRole.equalsIgnoreCase("ADMIN") || currentRole.equalsIgnoreCase("DEAN") || currentRole.equalsIgnoreCase("HEAD")){
					insdto =(InstituteDTO)delegate.getInstituteById(user.getInstitute().getInstituteid());
					ins = new ArrayList();
					ins.add(insdto);
				}
				requestMap.put("institutes", ins);
				fac = (FacultyDTO) delegate.getFacultyById(Integer.parseInt((String) CommonUtilities.getIdsFromInput(getId()).get(0)));
				requestMap.put("fac", fac);
				return FACULTY;
			}else if(getObject().equalsIgnoreCase(DEPARTMENT) ){
				logger.info("Inside department");
				DepartmentDTO dep;
				Map fac = new HashMap();
				delegate = new BaseDelegate();
				if(currentRole.equalsIgnoreCase("SUPERADMIN")){
					fac =(HashMap)delegate.getFacultyInstitutewise();					
				}else if(currentRole.equalsIgnoreCase("ADMIN") || currentRole.equalsIgnoreCase("DEAN") || currentRole.equalsIgnoreCase("HEAD")){
					fac =(HashMap)delegate.getFacultyInstitutewise(user.getInstitute().getInstituteid());	
					}
				requestMap.put("institutesfacultiesmap", fac);
				dep = (DepartmentDTO) delegate.getDepartmentById(Integer.parseInt((String) CommonUtilities.getIdsFromInput(getId()).get(0)));
				requestMap.put("dep", dep);
				return DEPARTMENT;
			}else if(getObject().equalsIgnoreCase(SUPERVISOR) ){
				logger.info("Inside supervisor");
				SupervisorDTO sup;
				delegate = new SupervisorDelegate();
				Map statDistMap = delegate.getDistrictsStateswise();
				sup = (SupervisorDTO) ((SupervisorDelegate) delegate).getSupervisorBySupervisorID((String) CommonUtilities.getIdsFromInput(getId()).get(0));
				sup.getCorrespondenceAddressState().setStatename(CommonUtilities.getStateName(sup.getCorrespondenceAddressState().getStateid()));
				sup.getPermanentAddressState().setStatename(CommonUtilities.getStateName(sup.getPermanentAddressState().getStateid()));
				
				for(Object dist : (ArrayList)statDistMap.get(sup.getCorrespondenceAddressState().getStatename())){
					DistrictDTO district = (DistrictDTO) dist;
					if(district.getDistrictid()== sup.getCorrespondenceAddressDistrict().getDistrictid())
						sup.getCorrespondenceAddressDistrict().setDistrictname(district.getDistrictname());
					
				}
				
				for(Object dist : (ArrayList)statDistMap.get(sup.getPermanentAddressState().getStatename())){
					DistrictDTO district = (DistrictDTO) dist;
					if(district.getDistrictid()== sup.getPermanentAddressDistrict().getDistrictid())
						sup.getPermanentAddressDistrict().setDistrictname(district.getDistrictname());
					
				}
				
				List ins = null;
				InstituteDTO insdto;
				String[] availableRoles =  Authorizer.getRoles(currentRole);
				if(currentRole.equalsIgnoreCase("SUPERADMIN")){
				ins =(ArrayList)delegate.getInstitutes();
				}else if(currentRole.equalsIgnoreCase("ADMIN") || currentRole.equalsIgnoreCase("DEAN") || currentRole.equalsIgnoreCase("HEAD")){
					insdto =(InstituteDTO)delegate.getInstituteById(user.getInstitute().getInstituteid());
					ins = new ArrayList();
					ins.add(insdto);
				}
				requestMap.put("institutes", ins);
				requestMap.put("statDistMap", statDistMap);
				requestMap.put("roles", CommonUtilities.convertStringArrayToList(availableRoles));
				requestMap.put("sup", sup);
				return SUPERVISOR;
			}else if(getObject().equalsIgnoreCase(SCHOLAR) ){
				logger.info("Inside scholar");
				ScholarDTO sch;
				String qualTable;
				String empTable;
				List ins = null;
				InstituteDTO insdto;
				delegate = new ScholarDelegate();
				sch = (ScholarDTO) ((ScholarDelegate) delegate).getScholarByScholarId((String) CommonUtilities.getIdsFromInput(getId()).get(0));
				Map statDistMap = delegate.getDistrictsStateswise();
				sch.getCorrespondenceAddressState().setStatename(CommonUtilities.getStateName(sch.getCorrespondenceAddressState().getStateid()));
				sch.getPermanentAddressState().setStatename(CommonUtilities.getStateName(sch.getPermanentAddressState().getStateid()));
				
				for(Object dist : (ArrayList)statDistMap.get(sch.getCorrespondenceAddressState().getStatename())){
					DistrictDTO district = (DistrictDTO) dist;
					if(district.getDistrictid()== sch.getCorrespondenceAddressDistrict().getDistrictid())
						sch.getCorrespondenceAddressDistrict().setDistrictname(district.getDistrictname());
					
				}
				
				for(Object dist : (ArrayList)statDistMap.get(sch.getPermanentAddressState().getStatename())){
					DistrictDTO district = (DistrictDTO) dist;
					if(district.getDistrictid()== sch.getPermanentAddressDistrict().getDistrictid())
						sch.getPermanentAddressDistrict().setDistrictname(district.getDistrictname());
					
				}
				qualTable = generateQualificationTable(sch);
				empTable = generateEmployerTable(sch);
				if(currentRole.equalsIgnoreCase("SUPERADMIN")){
					ins =(ArrayList)delegate.getInstitutes();
					}else if(currentRole.equalsIgnoreCase("ADMIN") || currentRole.equalsIgnoreCase("DEAN") || currentRole.equalsIgnoreCase("HEAD")){
						insdto =(InstituteDTO)delegate.getInstituteById(user.getInstitute().getInstituteid());
						ins = new ArrayList();
						ins.add(insdto);
					}
				requestMap.put("examsTable", qualTable);
				requestMap.put("employerTable", empTable);
				requestMap.put("sch", sch);
				requestMap.put("institutes", ins);
				requestMap.put("statDistMap", statDistMap);
				return SCHOLAR;
			}
		}
		return null;
	}

	private String generateEmployerTable(ScholarDTO sch) {
		StringBuffer table = new StringBuffer("<table class='employerTable'>"+
				"<thead><tr><th>Name of employer</th><th>Position held</th><th>From</th><th>To</th></tr></thead>" +
				"<tbody>");
		if(sch.getEmployersname() != null || sch.getPositionsHeld() != null || sch.getEmployedfrom() != null || sch.getEmployedto() != null){
		String[] empName = sch.getEmployersname().split("@");
		String[] postHeld = sch.getPositionsHeld().split("@");
		String[] empFrom = sch.getEmployedfrom().split("@");
		String[] empTo = sch.getEmployedto().split("@");
		
		for(int i=0;i<empName.length;i++){
			table.append("<tr><td><input type='text' value='"+empName[i]+"'></input></td>" +
					"<td><input type='text' value='"+postHeld[i]+"'></input></td>" +
					"<td><input type='text' value='"+empFrom[i]+"'></input></td>" +
					"<td><input type='text' value='"+empTo[i]+"'></input></td></tr>");
		}
		
		}
		return table.append("</tbody></table>").toString();
	}

	private String generateQualificationTable(ScholarDTO sch) {
		StringBuffer table = new StringBuffer("<table class='examsTable'>"+
			"<thead><tr><th>Exam Passed</th><th>Name of university</th><th>Year of passing</th><th>Percentage</th><th>Subjects</th></tr></thead>"+
			"<tbody>");
		if(sch.getExamsPassed() != null || sch.getInstitutionsName() != null || sch.getYearsOfPassing() != null || sch.getPercentages() != null || sch.getSubjects() != null){
			String[] examsPassed = sch.getExamsPassed().split("@");
			String[] insName = sch.getInstitutionsName().split("@");
			String[] yrPass = sch.getYearsOfPassing().split("@");
			String[] percents = sch.getPercentages().split("@");
			String[] subs = sch.getSubjects().split("@");
			
			for(int i=0;i<examsPassed.length;i++){
				table.append("<tr><td><input type='text' value='"+examsPassed[i]+"'></input></td>" +
						"<td><input type='text'  value='"+insName[i]+"'></input></td>" +
						"<td><input type='text'  value='"+yrPass[i]+"'></input></td>" +
						"<td><input type='text'  value='"+percents[i]+"'></input></td>" +
						"<td><input type='text'  value='"+subs[i]+"'></input></td></tr>");
			}
			
			}
		return table.append("</tbody></table>").toString();
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
