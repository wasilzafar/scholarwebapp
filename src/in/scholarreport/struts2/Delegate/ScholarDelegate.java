package in.scholarreport.struts2.Delegate;

import in.scholarreport.struts2.DAO.DAOFactory;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.util.CommonConstants;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class ScholarDelegate extends BaseDelegate{
	static Logger logger = Logger.getLogger(ScholarDelegate.class);

	public  int checkExistingScholar(ScholarDTO newScholar){
		int existing = DAOFactory.getInstance().getScholarDAO().checkExistingScholar(newScholar.getScholarFirstName(),newScholar.getScholarMiddleName(), newScholar.getScholarLastName(), newScholar.getDob(), newScholar.getEmailID(), newScholar.getEnrollmentNumber());
		return existing;
	}
	
	public boolean persistScholar(ScholarDTO newScholar){
		boolean persisted = false;
		String Id = CommonUtilities.getID(32);
		logger.info("Generated Id for scholar : "+ Id);
		newScholar.setScholarID(Id);
		persisted = DAOFactory.getInstance().getScholarDAO().persistScholar(newScholar);
		return persisted;
	}
	
	public boolean setScholar(ScholarDTO newScholar){
		boolean set = false;
		String cred = CommonUtilities.getID(6);
		set = DAOFactory.getInstance().getScholarDAO().setScholar(newScholar,cred);
		String subject = CommonConstants.EMAIL_NOTIFICATION_SUBJECT_TEMPLATE;
		String body = CommonConstants.EMAIL_NOTIFICATION_TEMPLATE.replace("@user@", newScholar.getScholarFirstName()+" "+newScholar.getScholarLastName()).replace("@emailid@", newScholar.getEmailID()).replace("@credential@", cred);
		logger.info("Email notification body : "+ body);
		CommonUtilities.sendMail("smtp.gmail.com", "wasil.zafar@gmail.com", "wasil.zafar@gmail.com", "wasil.zafar", "welcome12#", subject, body);
		return set;
	}
	
	public List getScholars(){
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().getScholars();
		return schList;
	}
	
	public ScholarDTO getScholarById(String id){
		ScholarDTO sch = DAOFactory.getInstance().getScholarDAO().getScholarByID(id);
		return sch;
	}
	
	public ScholarDTO getScholarByScholarId(String id){
		ScholarDTO sch = DAOFactory.getInstance().getScholarDAO().getScholarByScholarID(id);
		return sch;
	}

	public String activateScholarsById(String[] ids) {
		return DAOFactory.getInstance().getScholarDAO().activateScholarsById(ids);
	}

	public String inactivateScholarsById(String[] ids) {
		return DAOFactory.getInstance().getScholarDAO().activateScholarsById(ids);
	}

	public String deleteScholarsById(String[] ids) {
		return DAOFactory.getInstance().getScholarDAO().deleteScholarsById(ids);
	}

	public List getScholarsInstituteWise(int instituteid) {
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().getScholarsInstituteWise(instituteid);
		return schList;
	}

	public List getScholarsFacultyWise(int facultyid) {
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().getScholarsFacultyWise(facultyid);
		return schList;
	}

	public List getScholarsDepartmentWise(int departmentid) {
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().getScholarsDepartmentWise(departmentid);
		return schList;
	}

	public List getScholarsBySupervisorID(String supervisorID) {
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().getScholarsBySupervisorID(supervisorID);
		return schList;
	}

	public List searchScholarByNameForDepartment(String q, int departmentid) {
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().searchScholarByNameForDepartment(q,departmentid);
		return schList;		
	}
	
	public List getScholarsEmailIDforDepartment(int depid){
		List schList;
		schList = DAOFactory.getInstance().getScholarDAO().getScholarsEmailIDforDepartment(depid);
		return schList;
	}
	
	public List getSupervisorsEmailIDforScholars(String[] schids){
		List supList;
		supList = DAOFactory.getInstance().getScholarDAO().getSupervisorsEmailIDforScholars(schids);
		return supList;
	}
	
	public List getScholarNamesForIds(String[] schids){
		List supList;
		supList = DAOFactory.getInstance().getScholarDAO().getScholarNamesForIds(schids);
		return supList;
	}

	public boolean updateScholar(ScholarDTO model) {
		return DAOFactory.getInstance().getScholarDAO().updateScholar(model);
	}
}
