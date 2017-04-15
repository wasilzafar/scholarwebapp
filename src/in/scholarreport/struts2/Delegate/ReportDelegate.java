package in.scholarreport.struts2.Delegate;

import in.scholarreport.struts2.DAO.DAOFactory;
import in.scholarreport.struts2.DTO.MonthlyReportDTO;
import in.scholarreport.struts2.DTO.QuarterlyReportDTO;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.List;

import org.apache.log4j.Logger;


public class ReportDelegate extends BaseDelegate {
	static Logger logger = Logger.getLogger(ReportDelegate.class);
	public boolean checkExistingMonthlyReport(MonthlyReportDTO newMReport){
		boolean persisted = false;
		persisted = DAOFactory.getInstance().getReportDAO().checkExistingMonthlyReport(newMReport);
		return persisted;
	}
	
	public boolean persistMonthlyReport(MonthlyReportDTO newMReport){
		boolean persisted = false;
		String Id = CommonUtilities.getID(24);
		logger.info("Generated Id for scholar monthly report : "+ Id);
		newMReport.setMreportid(Id);
		persisted = DAOFactory.getInstance().getReportDAO().persistMonthlyReport(newMReport);
		return persisted;
	}
	
	public boolean checkExistingQuarterlyReport(QuarterlyReportDTO newQReport){
		boolean persisted = false;
		persisted = DAOFactory.getInstance().getReportDAO().checkExistingQuarterlyReport(newQReport);
		return persisted;
	}
	
	public boolean persistQuarterlyReport(QuarterlyReportDTO newQReport){
		boolean persisted = false;
		String Id = CommonUtilities.getID(24);
		logger.info("Generated Id for scholar quarterly report : "+ Id);
		newQReport.setQreportid(Id);
		persisted = DAOFactory.getInstance().getReportDAO().persistQuarterlyReport(newQReport);
		return persisted;
	}
	
	public List getMonthlyReportsBySupervisorID(String scholarID){
		List repList = DAOFactory.getInstance().getReportDAO().getMonthlyReportsBySupervisorID(scholarID);
		return repList;
	}
	
	public List getQuarterlyReportsBySuperviosrID(String scholarID){
		List repList = DAOFactory.getInstance().getReportDAO().getQuarterlyReportsBySupervisorID(scholarID);
		return repList;
	}

	public String approveMonthlyReportsById(String[] ids, String next) {
		return DAOFactory.getInstance().getReportDAO().approveMonthlyReportsById(ids, next);
	}

	public String deleteMonthlyReportsById(String[] ids) {
		return DAOFactory.getInstance().getReportDAO().deleteMonthlyReportsById(ids);
	}

	public String approveQuarterlyReportsById(String[] ids, String next) {
		return DAOFactory.getInstance().getReportDAO().approveQuarterlyReportsById(ids, next);
	}

	public String deleteQuarterlyReportsById(String[] ids) {
		return DAOFactory.getInstance().getReportDAO().deleteQuarterlyReportsById(ids);
	}
	public List getMonthlyReportsByScholarID(String scholarID){
		List repList = DAOFactory.getInstance().getReportDAO().getMonthlyReportsByScholarID(scholarID);
		return repList;
	}
	public List getQuarterlyReportsByScholarID(String scholarID){
		List repList = DAOFactory.getInstance().getReportDAO().getQuarterlyReportsByScholarID(scholarID);
		return repList;
	}

	public MonthlyReportDTO getMonthlyReportByID(String id) {
		return DAOFactory.getInstance().getReportDAO().getMonthlyReportByID(id);
	}

	public QuarterlyReportDTO getQuarterlyReportByID(String id) {
		return DAOFactory.getInstance().getReportDAO().getQuarterlyReportByID(id);
	}

	public String getContentDisposition(String object, String input) {
		if(object.equalsIgnoreCase("monthlyreport"))
			return DAOFactory.getInstance().getReportDAO().getMRFileContentDisposition(input);
		else
			return DAOFactory.getInstance().getReportDAO().getQRFileContentDisposition(input);
	}

	public List getMonthlyReports() {
		List repList = DAOFactory.getInstance().getReportDAO().getMonthlyReports();
		return repList;
	}

	public List getMonthlyReportsByInstituteId(int instituteid) {
		List repList = DAOFactory.getInstance().getReportDAO().getMonthlyReportsByInstituteId(instituteid);
		return repList;
	}

	public List getMonthlyReportsByFacultyId(int facultyid) {
		List repList = DAOFactory.getInstance().getReportDAO().getMonthlyReportsByFacultyId(facultyid);
		return repList;
	}

	public List getMonthlyReportsByDepartmentId(int departmentid) {
		List repList = DAOFactory.getInstance().getReportDAO().getMonthlyReportsByDepartmentId(departmentid);
		return repList;
	}

	public List getQuarterlyReports() {
		List repList = DAOFactory.getInstance().getReportDAO().getQuarterlyReports();
		return repList;
	}

	public List getQuarterlyReportsByInstituteId(int instituteid) {
		List repList = DAOFactory.getInstance().getReportDAO().getQuarterlyReportsByInstituteId(instituteid);
		return repList;
	}

	public List getQuarterlyReportsByFacultyId(int facultyid) {
		List repList = DAOFactory.getInstance().getReportDAO().getQuarterlyReportsByFacultyId(facultyid);
		return repList;
	}

	public List getQuarterlyReportsByDepartmentId(int departmentid) {
		List repList = DAOFactory.getInstance().getReportDAO().getQuarterlyReportsByDepartmentId(departmentid);
		return repList;
	}
}
