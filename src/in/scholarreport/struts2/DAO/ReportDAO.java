package in.scholarreport.struts2.DAO;

import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.DTO.MonthlyReportDTO;
import in.scholarreport.struts2.DTO.QuarterlyReportDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.util.CommonConstants;
import in.scholarreport.struts2.util.CommonUtilities;
import in.scholarreport.struts2.util.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;


public class ReportDAO extends BaseDAO {
	
	static Logger logger = Logger.getLogger(ReportDAO.class);
	
	public boolean checkExistingMonthlyReport(MonthlyReportDTO newMReport) {
		return false;
	}

	public boolean persistMonthlyReport(MonthlyReportDTO newMReport) {
		java.sql.Connection conn = getConnection();
		boolean persisted = false;
		try {
			logger.info("Persisting monthly report ");

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.PERSIST_NEW_MONTHLYREPORT);
			stmnt.setString(1, newMReport.getMreportid() );
			stmnt.setDate(2,
					CommonUtilities.getSQLDate(newMReport.getFromDate()));
			stmnt.setDate(3, CommonUtilities.getSQLDate(newMReport.getToDate()));
			stmnt.setString(4, newMReport.getWorkCompleted());
			stmnt.setInt(5, newMReport.getLeaves());
			stmnt.setString(6, newMReport.getAttachmentFileName());
			stmnt.setString(7, newMReport.getStatus());
			stmnt.setString(8, newMReport.getScholar().getScholarID());
			stmnt.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			if (stmnt.executeUpdate() > 0){
				logger.info("Monthly report persisted successfully");
				persisted = true;
			}
			else
				logger.info("There was some error while persisting monthly report ");
			persisted = true;
		} catch (Exception e) {
			logger.info("Error while persisting monthly report " + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return persisted;
	}

	public boolean checkExistingQuarterlyReport(QuarterlyReportDTO newQReport) {
		return false;
	}

	public boolean persistQuarterlyReport(QuarterlyReportDTO newQReport) {
		java.sql.Connection conn = getConnection();
		boolean persisted = false;
		try {
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.PERSIST_NEW_QUARTERLYREPORT);
			stmnt.setString(1, newQReport.getQreportid());
			stmnt.setDate(2, CommonUtilities.getSQLDate(newQReport.getFromDate()));
			stmnt.setDate(3, CommonUtilities.getSQLDate(newQReport.getToDate()));
			stmnt.setString(4, newQReport.getWorkCompleted());
			stmnt.setString(5, newQReport.getAttachmentFileName());
			stmnt.setString(6, newQReport.getStatus());
			stmnt.setString(7, newQReport.getScholar().getScholarID());
			stmnt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setDate(9, new java.sql.Date(new java.util.Date().getTime()));
			
			if (stmnt.executeUpdate() > 0){
				logger.info("Quarterly report persisted successfully");
				persisted = true;
			}
			else
				logger.info("There was some error while persisting quarterly report ");
			persisted = true;
		}catch (Exception e) {
			logger.info("Error while persisting quarterly report "+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object "
					+ e.getMessage());
		}
		return persisted;
	}


	
	public List getQuarterlyReportsBySupervisorID(String supID) {
		Connection conn = getConnection();
		ResultSet rs;
		List reps = new ArrayList();
		QuarterlyReportDTO rep;
		ScholarDTO sch;
		try {
			logger.info("Retrieving quarterly reports by supervisor ID "+ supID);
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT_BY_SUPERVISOR_ID);
			stmnt.setString(1,supID);
			rs = stmnt.executeQuery();
			
			while(rs.next()){
				rep = new QuarterlyReportDTO();
				sch = new ScholarDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				rep.setQreportid(rs.getString(5));
				rep.setFromDate(rs.getDate(6).toString());
				rep.setToDate(rs.getDate(7).toString());
				rep.setStatus(rs.getString(8));
				rep.setScholar(sch);
				reps.add(rep);
			}
			
			logger.info("Retrieved quarterly reports "+reps.size());
		} catch (SQLException e) {
			logger.error("Error while retrieving monthly reports by supervisor ID " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return reps;
	}

	public String approveMonthlyReportsById(String[] ids, String status) {
		boolean allApproved = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(SQLQueries.UPDATE_STATUS_MONTHLYREPORTS_BY_ID);
		logger.info("String of ids : "+idStr);
		try {
			logger.info("Approve monthly reports by IDs" + idStr);
			
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?")+1, "'"+status+"'");
			query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
			logger.info("Query for approval : "+query.toString());
			if(stmnt.executeUpdate(query.toString()) == ids.length){
				logger.info("All monthly reports approved for " + idStr);
				allApproved = true;
			}
			else
				logger.info("There was some error while approving monthly reports " + idStr);
			
			
		} catch (SQLException e) {
			logger.error("Error while approving monthly reports " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allApproved);
}

	public String deleteMonthlyReportsById(String[] ids) {
		boolean allDeleted = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(SQLQueries.DELETE_MONTHLYREPORTS_BY_ID);
		try {
			logger.info("Deleting monthly reports for IDs "+idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
			
			logger.info("Query for deletion : "+query.toString());
			if(stmnt.executeUpdate(query.toString()) == ids.length){
				logger.info("Deleted all monthly reports for "+idStr);
				allDeleted = true;
			}
			else
				logger.info("There was some error while monthly report deletion "+idStr);
		} catch (SQLException e) {
			logger.error("Error while deleting monthly reports " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allDeleted);
}

	public String approveQuarterlyReportsById(String[] ids, String status) {
		boolean allApproved = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(SQLQueries.UPDATE_STATUS_QUARTERLYREPORTS_BY_ID);
		try {
			logger.info("Approve quarterly reports by IDs" + idStr);
			
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?")+1, "'"+status+"'");
			query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
			logger.info("Query for approval : "+query.toString());
			if(stmnt.executeUpdate(query.toString()) == ids.length){
				logger.info("All quarterly reports approved for " + idStr);
				allApproved = true;
			}
			else
				logger.info("There was some error while approving quarterly reports " + idStr);
		} catch (SQLException e) {
			logger.error("Error while approving quarterly reports " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allApproved);
}

	public String deleteQuarterlyReportsById(String[] ids) {
		boolean allDeleted = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(SQLQueries.DELETE_QUARTERLYREPORTS_BY_ID);
		try {
			logger.info("Deleting quarterly reports for IDs "+idStr);
			
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
			
			logger.info("Query for deletion : "+query.toString());
			

			if(stmnt.executeUpdate(query.toString()) == ids.length){
				logger.info("Deleted all quarterly reports for "+idStr);
				allDeleted = true;
			}
			else
				logger.info("There was some error while quarterly report deletion "+idStr);
		} catch (SQLException e) {
			logger.error("Error while deleting quarterly reports " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allDeleted);
}
	public List getMonthlyReportsByScholarID(String schID) {
		Connection conn = getConnection();
		ResultSet rs;
		List reps = new ArrayList();
		MonthlyReportDTO rep;
		ScholarDTO sch;
		try {
			logger.info("Retrieving quarterly reports by scholar ID "+ schID);
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT_BY_SCHOLAR_ID);
			stmnt.setString(1,schID);
			rs = stmnt.executeQuery();
			
			while(rs.next()){
				rep = new MonthlyReportDTO();
				rep.setMreportid(rs.getString(1));
				rep.setFromDate(rs.getDate(2).toString());
				rep.setToDate(rs.getDate(3).toString());
				rep.setStatus(rs.getString(4));
				reps.add(rep);
			}
			
			logger.info("Retrieved monthly reports "+reps.size());
		} catch (SQLException e) {
			logger.error("Error while retrieving monthly reports by scholar ID " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return reps;
	}
public List getQuarterlyReportsByScholarID(String schID) {
	Connection conn = getConnection();
	ResultSet rs;
	List reps = new ArrayList();
	QuarterlyReportDTO rep;
	ScholarDTO sch;
	try {
		logger.info("Retrieving quarterly reports by scholar ID "+ schID);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT_BY_SCHOLAR_ID);
		stmnt.setString(1,schID);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new QuarterlyReportDTO();
			rep.setQreportid(rs.getString(1));
			rep.setFromDate(rs.getDate(2).toString());
			rep.setToDate(rs.getDate(3).toString());
			rep.setStatus(rs.getString(4));
			reps.add(rep);
		}
		
		logger.info("Retrieved quarterly reports "+reps.size());
	} catch (SQLException e) {
		logger.error("Error while retrieving quarterly reports by scholar ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public MonthlyReportDTO getMonthlyReportByID(String id) {
	Connection conn = getConnection();
	ResultSet rs;
	MonthlyReportDTO rep = null;
	ScholarDTO sch = null;
	SupervisorDTO sup = null;
	DepartmentDTO dep = null;
	try {
		logger.info("Retrieving moonthlyly report by report ID "+ id);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT_BY_ID);
		stmnt.setString(1,id);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new MonthlyReportDTO();
			sch = new ScholarDTO();
			sup = new SupervisorDTO();
			dep = new DepartmentDTO();
			sch.setScholarFirstName(rs.getString(1));
			sch.setScholarMiddleName(rs.getString(2));
			sch.setScholarLastName(rs.getString(3));
			dep.setDepartmentname(rs.getString(4));
			sup.setSupervisorFirstName(rs.getString(5));
			sup.setSupervisorMiddleName(rs.getString(6));
			sup.setSupervisorLastName(rs.getString(7));
			sch.setDateOfRegistration(rs.getDate(8).toString());
			rep.setFromDate(rs.getDate(9).toString());
			rep.setToDate(rs.getDate(10).toString());
			sch.setTopic(rs.getString(11));
			rep.setLeaves(rs.getInt(12));
			rep.setStatus(rs.getString(13));
			rep.setMreportid(rs.getString(14));
			rep.setWorkCompleted(rs.getString(15));
			rep.setAttachmentFileName(rs.getString(16));
			sch.setDepartment(dep);
			sch.setSupervisor(sup);
			rep.setScholar(sch);
			
		}
		
		logger.info("Retrieved monthly report ");
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly report by ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return rep;
}

public QuarterlyReportDTO getQuarterlyReportByID(String id) {
	Connection conn = getConnection();
	ResultSet rs;
	QuarterlyReportDTO rep = null;
	ScholarDTO sch = null;
	SupervisorDTO sup = null;
	DepartmentDTO dep = null;
	try {
		logger.info("Retrieving quarterly report by report ID "+ id);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT_BY_ID);
		stmnt.setString(1,id);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new QuarterlyReportDTO();
			sch = new ScholarDTO();
			sup = new SupervisorDTO();
			dep = new DepartmentDTO();
			sch.setScholarFirstName(rs.getString(1));
			sch.setScholarMiddleName(rs.getString(2));
			sch.setScholarLastName(rs.getString(3));
			dep.setDepartmentname(rs.getString(4));
			sup.setSupervisorFirstName(rs.getString(5));
			sup.setSupervisorMiddleName(rs.getString(6));
			sup.setSupervisorLastName(rs.getString(7));
			sch.setDateOfRegistration(rs.getDate(8).toString());
			rep.setFromDate(rs.getDate(9).toString());
			rep.setToDate(rs.getDate(10).toString());
			sch.setTopic(rs.getString(11));
			rep.setStatus(rs.getString(12));
			rep.setQreportid(rs.getString(13));
			rep.setWorkCompleted(rs.getString(14));
			rep.setAttachmentFileName(rs.getString(15));
			sch.setDepartment(dep);
			sch.setSupervisor(sup);
			rep.setScholar(sch);
			
		
		}
		
		logger.info("Retrieved quarterly report ");
	} catch (SQLException e) {
		logger.error("Error while retrieving quarterly report by ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return rep;
}

public String getMRFileContentDisposition(String id) {
	java.sql.Connection conn = getConnection();
	String fd = null;
	ResultSet rs = null;
	try {
		logger.info("File disposition for monthly report id " + id);

		PreparedStatement stmnt = conn
				.prepareStatement(SQLQueries.FILE_DISPOSITION_MONTHLYREPORT);
		 stmnt.setString(1, id);
		 rs = stmnt.executeQuery();
		 
		 while(rs.next()){
			 fd = rs.getString(1);
		 }
		 
		logger.info("Found file disposition as : " + fd);
	} catch (Exception e) {
		logger.info("Error while persisting monthly report " + e.getMessage());
	}

	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}


	
	return fd;
}

public String getQRFileContentDisposition(String id) {
	java.sql.Connection conn = getConnection();
	String fd = null;
	ResultSet rs = null;
	try {
		logger.info("File disposition for quarterly report id " + id);

		PreparedStatement stmnt = conn
				.prepareStatement(SQLQueries.FILE_DISPOSITION_QUARTERLYREPORT);
		 stmnt.setString(1, id);
		 rs = stmnt.executeQuery();
		 
		 while(rs.next()){
			 fd = rs.getString(1);
		 }
		 
		logger.info("Found file disposition as : " + fd);
	} catch (Exception e) {
		logger.info("Error while persisting quarterly report " + e.getMessage());
	}

	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}


	
	return fd;
}



public List getMonthlyReportsBySupervisorID(String supID) {
	Connection conn = getConnection();
	ResultSet rs;
	MonthlyReportDTO rep;
	List reps = new ArrayList();
	ScholarDTO sch;
	try {
		logger.info("Retrieving monthly reports by supervisor ID "+ supID);
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT_BY_SUPERVISOR_ID);
		stmnt.setString(1,supID);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new MonthlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setMreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved monthly reports "+reps.size());
		
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by supervisor ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getMonthlyReports() {
	Connection conn = getConnection();
	ResultSet rs;
	MonthlyReportDTO rep;
	List reps = new ArrayList();
	ScholarDTO sch;
	try {
		logger.info("Retrieving all monthly reports by supervisor ID ");
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new MonthlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setMreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved monthly reports "+reps.size());
		
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by supervisor ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getMonthlyReportsByInstituteId(int instituteid) {
	Connection conn = getConnection();
	ResultSet rs;
	MonthlyReportDTO rep;
	List reps = new ArrayList();
	ScholarDTO sch;
	try {
		logger.info("Retrieving all monthly reports by institute ID ");
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT_BY_INSTITUTE_ID);
		stmnt.setInt(1,instituteid);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new MonthlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setMreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved monthly reports "+reps.size());
		
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by institute ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getMonthlyReportsByFacultyId(int facultyid) {
	Connection conn = getConnection();
	ResultSet rs;
	MonthlyReportDTO rep;
	List reps = new ArrayList();
	ScholarDTO sch;
	try {
		logger.info("Retrieving all monthly reports by faculty ID ");
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT_BY_FACULTY_ID);
		stmnt.setInt(1,facultyid);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new MonthlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setMreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved monthly reports "+reps.size());
		
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by faculty ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getMonthlyReportsByDepartmentId(int departmentid) {
	Connection conn = getConnection();
	ResultSet rs;
	MonthlyReportDTO rep;
	List reps = new ArrayList();
	ScholarDTO sch;
	try {
		logger.info("Retrieving all monthly reports by department ID ");
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_MONTHLYREPORT_BY_DEPARTMENT_ID);
		stmnt.setInt(1,departmentid);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new MonthlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setMreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved monthly reports "+reps.size());
		
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by department ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getQuarterlyReports() {
	Connection conn = getConnection();
	ResultSet rs;
	List reps = new ArrayList();
	QuarterlyReportDTO rep;
	ScholarDTO sch;
	try {
		logger.info("Retrieving all quarterly reports " );
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new QuarterlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setQreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved quarterly reports "+reps.size());
	} catch (SQLException e) {
		logger.error("Error while retrieving all monthly reports " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getQuarterlyReportsByInstituteId(int instituteid) {
	Connection conn = getConnection();
	ResultSet rs;
	List reps = new ArrayList();
	QuarterlyReportDTO rep;
	ScholarDTO sch;
	try {
		logger.info("Retrieving quarterly reports by institute ID " + instituteid);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT_BY_INSTITUTE_ID);
		stmnt.setInt(1, instituteid);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new QuarterlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setQreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved quarterly reports "+reps.size());
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by institute ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getQuarterlyReportsByFacultyId(int facultyid) {
	Connection conn = getConnection();
	ResultSet rs;
	List reps = new ArrayList();
	QuarterlyReportDTO rep;
	ScholarDTO sch;
	try {
		logger.info("Retrieving quarterly reports by faculty ID " + facultyid);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT_BY_FACULTY_ID);
		stmnt.setInt(1, facultyid);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new QuarterlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setQreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved quarterly reports "+reps.size());
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by faculty ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

public List getQuarterlyReportsByDepartmentId(int departmentid) {
	Connection conn = getConnection();
	ResultSet rs;
	List reps = new ArrayList();
	QuarterlyReportDTO rep;
	ScholarDTO sch;
	try {
		logger.info("Retrieving quarterly reports by department ID " + departmentid);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.RETRIEVE_QUARTERLYREPORT_BY_DEPARTMENT_ID);
		stmnt.setInt(1, departmentid);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			rep = new QuarterlyReportDTO();
			sch = new ScholarDTO();
			sch.setScholarID(rs.getString(1));
			sch.setScholarFirstName(rs.getString(2));
			sch.setScholarMiddleName(rs.getString(3));
			sch.setScholarLastName(rs.getString(4));
			rep.setQreportid(rs.getString(5));
			rep.setFromDate(rs.getDate(6).toString());
			rep.setToDate(rs.getDate(7).toString());
			rep.setStatus(rs.getString(8));
			rep.setScholar(sch);
			reps.add(rep);
		}
		
		logger.info("Retrieved quarterly reports "+reps.size());
	} catch (SQLException e) {
		logger.error("Error while retrieving monthly reports by department ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return reps;
}

	}
