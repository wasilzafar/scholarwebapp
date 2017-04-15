package in.scholarreport.struts2.Delegate;

import in.scholarreport.colloquium.ColloquiumActivity;
import in.scholarreport.struts2.DAO.DAOFactory;
import in.scholarreport.struts2.DTO.ColloquiumDTO;
import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.DTO.InstituteDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class BaseDelegate {
	public Map getDistrictsStateswise(){
		return (Map) DAOFactory
				.getInstance().getBaseDAO().getDistrictsStateswise();
	}
	public int checkExistingFaculty(FacultyDTO facultyDTO){
		int existing = DAOFactory
				.getInstance().getBaseDAO().getExistingFaculty(facultyDTO);
		return existing;
	}
	
	public boolean persistFaculty(FacultyDTO facultyDTO){
		boolean persisted = DAOFactory
				.getInstance().getBaseDAO().persistFaculty(facultyDTO);
		return persisted;
	}
	
	public int checkExistingDepartment(DepartmentDTO dep){
		int existing = DAOFactory
				.getInstance().getBaseDAO().getExistingDepartment(dep.getFaculty().getFacultyid(), dep.getDepartmentname());
		return existing;
	
	}
	
	public boolean persistDepartment(DepartmentDTO dep){
		boolean persisted = DAOFactory
				.getInstance().getBaseDAO().persistDepartment(dep.getFaculty().getFacultyid(), dep.getDepartmentname());
		return persisted;
	
		
	}
	public List getFaculties(){
		return(List)DAOFactory
				.getInstance().getBaseDAO().getFaculties();
	}
	
	public List getInstitutes(){
		return(List)DAOFactory
				.getInstance().getBaseDAO().getInstitutes();
	}
	
	public InstituteDTO getInstituteById(int insID){
		return(InstituteDTO)DAOFactory
				.getInstance().getBaseDAO().getInstituteById(insID);
	}
	
	public List getDepartments(){
		return(List)DAOFactory
				.getInstance().getBaseDAO().getDepartments();
	}
	
	public DepartmentDTO getDepartmentById(int departmentId){
		return(DepartmentDTO)DAOFactory
				.getInstance().getBaseDAO().getDepartmentById(departmentId);
	}

	public int checkExistingInstitute(String institutename) {
		int existing = DAOFactory
				.getInstance().getBaseDAO().getExistingInstitute(institutename);
		return existing;
	}

	public boolean persistInstitute(String institutename) {
		boolean persisted = DAOFactory
				.getInstance().getBaseDAO().persistInstitute(institutename);
		return persisted;
	}

	public Map getFacultyInstitutewise() {
		return(Map)DAOFactory
				.getInstance().getBaseDAO().getFacultyInstitutewise();
	}
	
	public Map getFacultyInstitutewise(int insID) {
		return(Map)DAOFactory
				.getInstance().getBaseDAO().getFacultiesByInstituteId(insID);
	}

	public String fetchDepartmentByFacultyId(String[] id) {
		return CommonUtilities.convertMapToXML((Map)DAOFactory.getInstance().getBaseDAO().fetchDepartmentByFacultyId(id),"option");
	}
	
	public Map fetchDepartmentMapByFacultyId(String[] id) {
		return (Map)DAOFactory.getInstance().getBaseDAO().fetchDepartmentByFacultyId(id);
	}
	
	public String fetchFacultyByInstituteId(String[] id) {
		return CommonUtilities.convertMapToXML((Map)DAOFactory.getInstance().getBaseDAO().fetchFacultyByInstituteId(id),"option");
	}
		
	public Properties fetchStateIdProperties() {
		return DAOFactory.getInstance().getBaseDAO().fetchStateIdProperties();
	}
	
	public Properties fetchInstituteIdProperties() {
		return DAOFactory.getInstance().getBaseDAO().fetchInstituteIdProperties();
	}
	public List getFaculties(int instituteid) {
		return(List)DAOFactory
				.getInstance().getBaseDAO().getFaculties(instituteid);
	}
	public FacultyDTO getFacultyById(int facultyid) {
		return(FacultyDTO)DAOFactory
				.getInstance().getBaseDAO().getFacultyById(facultyid);
	}
	public List getDepartmentsInstitutewise(int instituteid) {
		return(List)DAOFactory
				.getInstance().getBaseDAO().getDepartmentsInstitutewise(instituteid);
	}
	public List getDepartmentsFacultywise(int facultyid) {
		return(List)DAOFactory
				.getInstance().getBaseDAO().getDepartmentsFacultywise(facultyid);
	}
	public String deleteInstituteById(String[] strIDs) {
		return DAOFactory
				.getInstance().getBaseDAO().deleteInstituteById(strIDs);
	}
	public String deleteFacultyById(String[] strIDs) {
		return DAOFactory
				.getInstance().getBaseDAO().deleteFacultyById(strIDs);
	}
	public String deleteDepartmentById(String[] strIDs) {
		return DAOFactory
				.getInstance().getBaseDAO().deleteDepartmentById(strIDs);
	}
	
	/*	 
	 *Method to get all colloquium DTOs for a particular ADMIN 
	 */
	
	public List getColloquiumForAdmin(String adminId){
		return(List)DAOFactory
				.getInstance().getBaseDAO().getColloquiumForAdmin(adminId);
	}
	
	/*	 
	 *Method to persist colloquium using colloquium DTO 
	 */
	
	public boolean persistColloquium(ColloquiumDTO coll){
		coll.setCollID(CommonUtilities.getID(12));
		coll.setStatus(ColloquiumActivity.SCHEDULED);
		return DAOFactory
				.getInstance().getBaseDAO().persistColloquium(coll);
	}
	
	/*	 
	 *Method to get colloquium using colloquium ID 
	 */
	
	public ColloquiumDTO getColloquiumById(String collId){
		return DAOFactory
				.getInstance().getBaseDAO().getColloquiumById(collId);
			
	}
	public boolean updateInstitute(InstituteDTO model) {
		return DAOFactory
				.getInstance().getBaseDAO().updateInstitute(model);
	}
	public boolean updateFaculty(FacultyDTO model) {
		return DAOFactory
				.getInstance().getBaseDAO().updateFaculty(model);
	}
	public boolean updateDepartment(DepartmentDTO model) {
		return DAOFactory
				.getInstance().getBaseDAO().updateDepartment(model);
	}
	
}
