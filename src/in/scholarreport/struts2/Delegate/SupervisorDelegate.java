package in.scholarreport.struts2.Delegate;

import in.scholarreport.struts2.DAO.DAOFactory;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.util.CommonConstants;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class SupervisorDelegate extends BaseDelegate{
	static Logger logger = Logger.getLogger(SupervisorDelegate.class);
	
	public int checkExistingSupervisor(SupervisorDTO newSuprvisor) {
		int existing = DAOFactory
				.getInstance()
				.getSupervisorDAO()
				.checkExistingSupervisor(newSuprvisor.getSupervisorFirstName(),
						newSuprvisor.getSupervisorMiddleName(),
						newSuprvisor.getSupervisorLastName(),
						newSuprvisor.getDob(), newSuprvisor.getEmailID());
		return existing;
	}

	public boolean persistSupervisor(SupervisorDTO newSuprvisor) {
		boolean persisted = false;
		String Id = CommonUtilities.getID(32);
		logger.info("Generated Id for supervisor : "+ Id);
		newSuprvisor.setSupervisorID(Id);
		setAdminIdentifier(newSuprvisor);
		persisted = DAOFactory.getInstance().getSupervisorDAO()
				.persistSupervisor(newSuprvisor);
		return persisted;
	}

	private void setAdminIdentifier(SupervisorDTO newSuprvisor) {
		if (newSuprvisor.getRole().equalsIgnoreCase(CommonConstants.SUPERVISOR)) {
			newSuprvisor.setIsSuperadmin('N');
			newSuprvisor.setIsAdmin('N');
			newSuprvisor.setIsDean('N');
			newSuprvisor.setIsHead('N');
		} else if (newSuprvisor.getRole().equalsIgnoreCase(
				CommonConstants.SUPERADMIN)) {
			newSuprvisor.setIsSuperadmin('Y');
			newSuprvisor.setIsAdmin('N');
			newSuprvisor.setIsDean('N');
			newSuprvisor.setIsHead('N');
		} else if (newSuprvisor.getRole().equalsIgnoreCase(
				CommonConstants.ADMIN)) {
			newSuprvisor.setIsSuperadmin('N');
			newSuprvisor.setIsAdmin('Y');
			newSuprvisor.setIsDean('N');
			newSuprvisor.setIsHead('N');
		} else if (newSuprvisor.getRole()
				.equalsIgnoreCase(CommonConstants.DEAN)) {
			newSuprvisor.setIsSuperadmin('N');
			newSuprvisor.setIsAdmin('N');
			newSuprvisor.setIsDean('Y');
			newSuprvisor.setIsHead('N');
		} else if (newSuprvisor.getRole()
				.equalsIgnoreCase(CommonConstants.HEAD)) {
			newSuprvisor.setIsSuperadmin('N');
			newSuprvisor.setIsAdmin('N');
			newSuprvisor.setIsDean('N');
			newSuprvisor.setIsHead('Y');
		}
	}

	public List getSupevisors() {
		List supList = DAOFactory.getInstance().getSupervisorDAO()
				.getSupervisors();
		return supList;
	}
	
	public SupervisorDTO getSupervisorByID(String id){
		SupervisorDTO sup = DAOFactory.getInstance().getSupervisorDAO().getSupervisorByID(id);
		 return sup;
	}
	
	public SupervisorDTO getSupervisorBySupervisorID(String id){
		SupervisorDTO sup = DAOFactory.getInstance().getSupervisorDAO().getSupervisorBySupervisorID(id);
		 return sup;
	}

	public String activateSupervisorsById(String[] ids) {
		return DAOFactory.getInstance().getSupervisorDAO().activateSupervisorsById(ids);
	}

	public String inactivateSupervisorsById(String[] ids) {
		return DAOFactory.getInstance().getSupervisorDAO().inactivateSupervisorsById(ids);
	}

	public String deleteSupervisorsById(String[] ids) {
		return DAOFactory.getInstance().getSupervisorDAO().deleteSupervisorsById(ids);
	}
	public String fetchSupervisorsByDepartmentId(String[] id) {
		return CommonUtilities.convertMapToXML((Map)DAOFactory.getInstance().getSupervisorDAO().fetchSupervisorsByDepartmentId(id),"option");
	}
	
	public boolean setSupervisor(SupervisorDTO newSupervisor) {
		boolean supSet = false;
		String cred = CommonUtilities.getID(6);
		supSet = DAOFactory.getInstance().getSupervisorDAO()
				.setSupervisor(newSupervisor,cred);
		String subject = CommonConstants.EMAIL_NOTIFICATION_SUBJECT_TEMPLATE;
		String body = CommonConstants.EMAIL_NOTIFICATION_TEMPLATE.replace("@user@", newSupervisor.getSupervisorFirstName()+" "+newSupervisor.getSupervisorLastName()).replace("@emailid@", newSupervisor.getEmailID()).replace("@credential@", cred);
		logger.info("Email notification body : "+ body);
		CommonUtilities.sendMail("smtp.gmail.com", "wasil.zafar@gmail.com", "wasil.zafar@gmail.com", "wasil.zafar", "welcome12#", subject, body);
		return supSet;
	}

	public List getSupevisorsInstitutewise(int instituteid) {
		List supList = DAOFactory.getInstance().getSupervisorDAO()
				.getSupevisorsInstitutewise(instituteid);
		return supList;
	}

	public List getSupevisorsFacultywise(int facultyid) {
		List supList = DAOFactory.getInstance().getSupervisorDAO()
				.getSupevisorsFacultywise(facultyid);
		return supList;
	}

	public Map getSupevisorsDepartmentMap(int facultyid) {
		Map supMap = DAOFactory.getInstance().getSupervisorDAO()
				.getSupevisorsDepartmentMap(facultyid);
		return supMap;
	}

	
	public List getSupevisorsDepartmentwise(int departmentid) {
		List supList = DAOFactory.getInstance().getSupervisorDAO()
				.getSupevisorsDepartmentwise(departmentid);
		return supList;
	}
	
	public boolean updateSupervisor(SupervisorDTO model) {
		setAdminIdentifier(model);
		return DAOFactory
				.getInstance().getSupervisorDAO().updateSupervisor(model);
	}
}
