package in.scholarreport.struts2.DAO;

import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.DTO.DistrictDTO;
import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.DTO.InstituteDTO;
import in.scholarreport.struts2.DTO.ScholarDTO;
import in.scholarreport.struts2.DTO.StateDTO;
import in.scholarreport.struts2.DTO.SupervisorDTO;
import in.scholarreport.struts2.util.CommonConstants;
import in.scholarreport.struts2.util.CommonUtilities;
import in.scholarreport.struts2.util.SQLQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

public class SupervisorDAO extends BaseDAO {
	static Logger logger = Logger.getLogger(SupervisorDAO.class);

	public int checkExistingSupervisor(String fName, String mName,
			String lName, String dob, String email) {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		int dupSup = 0;
		try {
			logger.info("Checking existing supervisor for " + fName + " "
					+ mName + " " + lName + " " + dob + " " + email);
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_EXISTING_SUPERVISOR);
			stmnt.setString(1, fName);
			stmnt.setString(2, mName);
			stmnt.setString(3, lName);
			stmnt.setDate(4, CommonUtilities.getSQLDate(dob));
			stmnt.setString(5, email);

			rs = stmnt.executeQuery();

			while (rs.next()) {
				dupSup = rs.getInt(1);
			}
			logger.info("Existing supervisor : " + dupSup);
		} catch (SQLException e) {
			logger.error("Error while checking existing supervisor "
					+ e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return dupSup;
	}

	public boolean persistSupervisor(SupervisorDTO supdto) {
		boolean persisted = false;
		java.sql.Connection conn = getConnection();
		try {
			logger.info("Persisting supervisor : "
					+ supdto.getSupervisorFirstName() + " "
					+ supdto.getSupervisorMiddleName() + " "
					+ supdto.getSupervisorLastName());
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.PERSIST_NEW_SUPERVISOR);
			stmnt.setString(1, supdto.getSupervisorID());
			stmnt.setString(2, supdto.getSupervisorFirstName());
			stmnt.setString(3, supdto.getSupervisorMiddleName());
			stmnt.setString(4, supdto.getSupervisorLastName());
			stmnt.setString(5, "");
			stmnt.setString(6, supdto.getSupervisorFatherFirstName());
			stmnt.setString(7, supdto.getSupervisorFatherLastName());
			stmnt.setString(8, supdto.getSupervisorSpouseFirstName());
			stmnt.setString(9, supdto.getSupervisorSpouseLastName());
			stmnt.setDate(10, CommonUtilities.getSQLDate(supdto.getDob()));
			stmnt.setString(11, supdto.getMobileNumber());
			stmnt.setString(12, supdto.getLandlineNumber());
			stmnt.setString(13, String.valueOf(supdto.getIsSuperadmin()));
			stmnt.setString(14, String.valueOf(supdto.getIsAdmin()));
			stmnt.setString(15, String.valueOf(supdto.getIsDean()));
			stmnt.setString(16, String.valueOf(supdto.getIsHead()));
			stmnt.setString(17, supdto.getRole());
			stmnt.setString(18, null);
			stmnt.setString(19, supdto.getEmailID());
			stmnt.setString(20, supdto.getCorrespondenceAddress());
			stmnt.setInt(21, CommonUtilities.getStateId(supdto
					.getCorrespondenceAddressState().getStatename()));
			stmnt.setInt(22, supdto.getCorrespondenceAddressDistrict()
					.getDistrictid());
			stmnt.setString(23, supdto.getCorrespondenceAddressZipCode());
			stmnt.setString(24, supdto.getPermanentAddress());
			stmnt.setInt(25, CommonUtilities.getStateId(supdto
					.getPermanentAddressState().getStatename()));
			stmnt.setInt(26, supdto.getPermanentAddressDistrict()
					.getDistrictid());
			stmnt.setString(27, supdto.getPermanentAddressZipCode());
			stmnt.setString(28, "INACTIVE");
			stmnt.setInt(29, supdto.getInstitute().getInstituteid());
			if (supdto.getFaculty() != null)
				stmnt.setInt(30, supdto.getFaculty().getFacultyid());
			else
				stmnt.setInt(30, 0);
			if (supdto.getDepartment() != null)
				stmnt.setInt(31, supdto.getDepartment().getDepartmentid());
			else
				stmnt.setInt(31, 0);
			stmnt.setString(32, supdto.getDesignation());
			stmnt.setDate(33, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setDate(34, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setString(35, supdto.getSalutation());

			if (stmnt.executeUpdate() > 0)
				persisted = true;

			logger.info("Persisted successfully ? " + persisted);
		} catch (SQLException e) {
			logger.error("Error while persisting supervisor " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return persisted;
	}
	
	public boolean updateSupervisor(SupervisorDTO supdto) {
		boolean persisted = false;
		java.sql.Connection conn = getConnection();
		try {
			logger.info("Updating supervisor : "
					+ supdto.getSupervisorFirstName() + " "
					+ supdto.getSupervisorMiddleName() + " "
					+ supdto.getSupervisorLastName());
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.UPDATE_SUPERVISOR);
			stmnt.setString(1, supdto.getSupervisorFirstName());
			stmnt.setString(2, supdto.getSupervisorMiddleName());
			stmnt.setString(3, supdto.getSupervisorLastName());
			stmnt.setString(4, "");
			stmnt.setString(5, supdto.getSupervisorFatherFirstName());
			stmnt.setString(6, supdto.getSupervisorFatherLastName());
			stmnt.setString(7, supdto.getSupervisorSpouseFirstName());
			stmnt.setString(8, supdto.getSupervisorSpouseLastName());
			stmnt.setDate(9, CommonUtilities.getSQLDate(supdto.getDob()));
			stmnt.setString(10, supdto.getMobileNumber());
			stmnt.setString(11, supdto.getLandlineNumber());
			stmnt.setString(12, String.valueOf(supdto.getIsSuperadmin()));
			stmnt.setString(13, String.valueOf(supdto.getIsAdmin()));
			stmnt.setString(14, String.valueOf(supdto.getIsDean()));
			stmnt.setString(15, String.valueOf(supdto.getIsHead()));
			stmnt.setString(16, supdto.getRole());
			stmnt.setString(17, null);
			stmnt.setString(18, supdto.getEmailID());
			stmnt.setString(19, supdto.getCorrespondenceAddress());
			stmnt.setInt(20, CommonUtilities.getStateId(supdto
					.getCorrespondenceAddressState().getStatename()));
			stmnt.setInt(21, supdto.getCorrespondenceAddressDistrict()
					.getDistrictid());
			stmnt.setString(22, supdto.getCorrespondenceAddressZipCode());
			stmnt.setString(23, supdto.getPermanentAddress());
			stmnt.setInt(24, CommonUtilities.getStateId(supdto
					.getPermanentAddressState().getStatename()));
			stmnt.setInt(25, supdto.getPermanentAddressDistrict()
					.getDistrictid());
			stmnt.setString(26, supdto.getPermanentAddressZipCode());
			stmnt.setString(27, supdto.getStatus());
			stmnt.setInt(28, supdto.getInstitute().getInstituteid());
			if (supdto.getFaculty() != null)
				stmnt.setInt(29, supdto.getFaculty().getFacultyid());
			else
				stmnt.setInt(29, 0);
			if (supdto.getDepartment() != null)
				stmnt.setInt(30, supdto.getDepartment().getDepartmentid());
			else
				stmnt.setInt(30, 0);
			stmnt.setString(31, supdto.getDesignation());
			stmnt.setDate(32, CommonUtilities.getSQLDate(supdto.getDateCreated()));
			stmnt.setDate(33, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setString(34, supdto.getSalutation());
			stmnt.setString(35, supdto.getSupervisorID());

			if (stmnt.executeUpdate() > 0)
				persisted = true;

			logger.info("Updated successfully ? " + persisted);
		} catch (SQLException e) {
			logger.error("Error while updating supervisor " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return persisted;
	}

	public List getSupervisors() {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		SupervisorDTO supdto;
		SupervisorDTO reptodto;
		InstituteDTO idto;
		FacultyDTO fdto;
		DepartmentDTO ddto;
		List supList = new ArrayList();
		try {

			logger.info("Fetching supervisors  ");

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SUPERVISORS);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				supdto = new SupervisorDTO();
				reptodto = new SupervisorDTO();
				idto = new InstituteDTO();
				fdto = new FacultyDTO();
				ddto = new DepartmentDTO();
				supdto.setSupervisorID(rs.getString(1));
				supdto.setSupervisorFirstName(rs.getString(2));
				supdto.setSupervisorMiddleName(rs.getString(3));
				supdto.setSupervisorLastName(rs.getString(4));
				supdto.setSupervisorScreenName(rs.getString(5));
				ddto.setDepartmentname(rs.getString(6));
				fdto.setFacultyname(rs.getString(7));
				idto.setInstitutename(rs.getString(8));
				supdto.setStatus(rs.getString(9));
				reptodto.setSupervisorID(rs.getString(10));
				supdto.setRole(rs.getString(10));
				supdto.setReportto(reptodto);
				supdto.setInstitute(idto);
				supdto.setFaculty(fdto);
				supdto.setDepartment(ddto);
				supList.add(supdto);
			}
			logger.info("Supervisor fetched  : " + supList.size());
		} catch (SQLException e) {
			logger.error("Error while fetching supervisors " + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return supList;
	}

	public boolean setSupervisor(SupervisorDTO newSupervisor, String cred) {
		java.sql.Connection conn = getConnection();
		boolean inserted = false;
		try {
			logger.info("Setting supervisor details for "
					+ newSupervisor.getEmailID());

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.SET_SUPERVISOR);
			stmnt.setString(1, newSupervisor.getSupervisorID());
			stmnt.setString(2, newSupervisor.getEmailID());
			stmnt.setString(3, cred);

			if (stmnt.executeUpdate() > 0)
				inserted = true;

			logger.info("Scholar set " + inserted);
		} catch (SQLException e) {
			logger.error("Error while setting supervisor details "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return inserted;
	}

	public SupervisorDTO getSupervisorByID(String id) {

		java.sql.Connection conn = getConnection();
		SupervisorDTO sup = null;
		ResultSet rs;
		DepartmentDTO dep;
		FacultyDTO fac;
		InstituteDTO ins;
		String isAd;
		try {

			logger.info("Fetching supervisor by email ID : " + id);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.RETRIEVE_SUPERVISOR_BY_SUPERVISOR_ID);
			stmnt.setString(1, id);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sup = new SupervisorDTO();
				dep = new DepartmentDTO();
				fac = new FacultyDTO();
				ins = new InstituteDTO();
				sup.setSupervisorID(rs.getString(1));
				sup.setSupervisorFirstName(rs.getString(2));
				sup.setSupervisorMiddleName(rs.getString(3));
				sup.setSupervisorLastName(rs.getString(4));
				sup.setSupervisorScreenName(rs.getString(5));
				dep.setDepartmentname(rs.getString(6));
				fac.setFacultyname(rs.getString(7));
				ins.setInstitutename(rs.getString(8));
				sup.setIsSuperadmin(rs.getString(9).charAt(0));
				sup.setIsAdmin(rs.getString(10).charAt(0));
				sup.setIsDean(rs.getString(11).charAt(0));
				sup.setIsHead(rs.getString(12).charAt(0));
				sup.setRole(rs.getString(13));
				dep.setDepartmentid(rs.getInt(14));
				fac.setFacultyid(rs.getInt(15));
				ins.setInstituteid(rs.getInt(16));
				sup.setInstitute(ins);
				sup.setFaculty(fac);
				sup.setDepartment(dep);
			}
			logger.info("Retrieved supervisor by email id = " + id);
			if (sup == null)
				logger.warn("Fetched supervisor as null ! ");
		} catch (SQLException e) {
			logger.error("Error while getting supervisor by email ID "
					+ e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return sup;
	}
	
	public SupervisorDTO getSupervisorBySupervisorID(String id) {

		java.sql.Connection conn = getConnection();
		SupervisorDTO sup = null;
		ResultSet rs;
		DepartmentDTO dep;
		FacultyDTO fac;
		InstituteDTO ins;
		String isAd;
		StateDTO cState;
		StateDTO pState;
		DistrictDTO cDistrict;
		DistrictDTO pDistrict;
		try {

			logger.info("Fetching supervisor by supervisor ID : " + id);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.RETRIEVE_SUPERVISOR_BY_SUPERVISORID);
			stmnt.setString(1, id);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sup = new SupervisorDTO();
				dep = new DepartmentDTO();
				fac = new FacultyDTO();
				ins = new InstituteDTO();
				cState = new StateDTO();
				pState = new StateDTO();
				cDistrict = new DistrictDTO();
				pDistrict = new DistrictDTO();
				sup.setSupervisorID(rs.getString(1));
				sup.setSupervisorFirstName(rs.getString(2));
				sup.setSupervisorMiddleName(rs.getString(3));
				sup.setSupervisorLastName(rs.getString(4));
				sup.setSupervisorScreenName(rs.getString(5));
				dep.setDepartmentname(rs.getString(6));
				fac.setFacultyname(rs.getString(7));
				ins.setInstitutename(rs.getString(8));
				sup.setIsSuperadmin(rs.getString(9).charAt(0));
				sup.setIsAdmin(rs.getString(10).charAt(0));
				sup.setIsDean(rs.getString(11).charAt(0));
				sup.setIsHead(rs.getString(12).charAt(0));
				sup.setRole(rs.getString(13));
				dep.setDepartmentid(rs.getInt(14));
				fac.setFacultyid(rs.getInt(15));
				ins.setInstituteid(rs.getInt(16));
				sup.setSupervisorFatherFirstName(rs.getString(17));
				sup.setSupervisorFatherLastName(rs.getString(18));
				sup.setSupervisorSpouseFirstName(rs.getString(19));
				sup.setSupervisorSpouseLastName(rs.getString(20));
				sup.setDob(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(21)));
				sup.setMobileNumber(rs.getString(22));
				sup.setLandlineNumber(rs.getString(23));
				sup.setRole(rs.getString(24));
				sup.setEmailID(rs.getString(25));
				sup.setCorrespondenceAddress(rs.getString(26));
				cState.setStateid(rs.getInt(27));
				sup.setCorrespondenceAddressState(cState);
				cDistrict.setDistrictid(rs.getInt(28));
				sup.setCorrespondenceAddressDistrict(cDistrict);
				sup.setCorrespondenceAddressZipCode(rs.getString(29));
				sup.setPermanentAddress(rs.getString(30));
				pState.setStateid(rs.getInt(31));
				sup.setPermanentAddressState(pState);
				pDistrict.setDistrictid(rs.getInt(32));
				sup.setPermanentAddressDistrict(pDistrict);
				sup.setPermanentAddressZipCode(rs.getString(33));
				sup.setStatus(rs.getString(34));
				sup.setDateCreated(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(35)));
				sup.setDateModified(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(36)));
				sup.setInstitute(ins);
				sup.setFaculty(fac);
				sup.setDepartment(dep);
			}
			logger.info("Retrieved supervisor by supervisor id = " + id);
			if (sup == null)
				logger.warn("Fetched supervisor as null ! ");
		} catch (SQLException e) {
			logger.error("Error while getting supervisor by ID "
					+ e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return sup;
	}

	public String activateSupervisorsById(String[] ids) {
		boolean allActivated = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(
				SQLQueries.UPDATE_STATUS_SUPERVISORS_BY_ID);
		try {
			logger.info("Activate scholars by IDs" + idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, "'"
					+ CommonConstants.STATUS_ACTIVE + "'");
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);
			logger.info("Query for activation : " + query.toString());
			if (stmnt.executeUpdate(query.toString()) == ids.length) {
				logger.info("All supervisors activated for " + idStr);
				allActivated = true;
			} else
				logger.info("There was some error while activating scholars "
						+ idStr);
		} catch (SQLException e) {
			logger.error("Error while activating supervisors  "
					+ e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allActivated);
	}

	public String inactivateSupervisorsById(String[] ids) {
		boolean allDeactivated = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(
				SQLQueries.UPDATE_STATUS_SUPERVISORS_BY_ID);
		try {
			logger.info("Deactivate scholars by IDs" + idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, "'"
					+ CommonConstants.STATUS_INACTIVE + "'");
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);
			logger.info("Query for deactivation : " + query.toString());
			if (stmnt.executeUpdate(query.toString()) == ids.length) {
				logger.info("All supervisors deactivated for " + idStr);
				allDeactivated = true;
			} else
				logger.info("There was some error while deactivating scholars "
						+ idStr);
		} catch (SQLException e) {
			logger.error("Error while deactivating supervisors  "
					+ e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allDeactivated);
	}

	public String deleteSupervisorsById(String[] ids) {
		boolean allDeleted = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);

		try {
			logger.info("Deleting supervisors by IDs" + idStr);
			StringBuffer initquery = new StringBuffer(
					SQLQueries.DELETE_SUPERVISORLOGIN_BY_ID);
			Statement stmnt = conn.createStatement();
			initquery.replace(initquery.indexOf("?"),
					initquery.indexOf("?") + 1, idStr);

			logger.info("Query for initial deletion : " + initquery.toString());

			if (stmnt.executeUpdate(initquery.toString()) == ids.length) {
				logger.info("Deleted all supervisors login for " + idStr);
				allDeleted = true;
			} else
				logger.info("There was some error while deleting supervisors login "
						+ idStr);
			StringBuffer query = new StringBuffer(
					SQLQueries.DELETE_SUPERVISORS_BY_ID);
			stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);

			logger.info("Query for deletion : " + query.toString());

			if (stmnt.executeUpdate(query.toString()) == ids.length) {
				logger.info("Deleted all supervisors for " + idStr);
				allDeleted = true;
			} else
				logger.info("There was some error while deleting supervisors "
						+ idStr);

		} catch (SQLException e) {
			logger.error("Error while deleting supervisors " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allDeleted);
	}

	public Map fetchSupervisorsByDepartmentId(String[] id) {

		java.sql.Connection conn = getConnection();
		StringBuffer name;
		String supId = new String();
		String first, middle, last;
		ResultSet rs;
		Map supList = new HashMap();
		try {

			logger.info("Fetching supervisor by department ID : " + id[0]);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SUPERVISORS_BY_ALLID);
			stmnt.setInt(1, CommonUtilities.convertNulltoZeroInt(id[0]));
			stmnt.setInt(2, CommonUtilities.convertNulltoZeroInt(id[1]));
			stmnt.setInt(3, CommonUtilities.convertNulltoZeroInt(id[2]));
			rs = stmnt.executeQuery();

			while (rs.next()) {
				name = new StringBuffer();
				supId = rs.getString(1);
				first = rs.getString(2);
				middle = rs.getString(3);
				last = rs.getString(4);
				first = first == null ? "" : first;
				middle = middle == null ? "" : middle;
				last = last == null ? "" : last;
				name.append(first + " " + middle + " " + last);
				supList.put(supId, name);
			}
			logger.info("Retrieved " + supList.size()
					+ " supervisor by department ID = " + id[0]);

		} catch (SQLException e) {
			logger.error("Error while getting supervisor by department ID "
					+ e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return (Map) supList;
	}

	public List getSupevisorsInstitutewise(int instituteid) {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		SupervisorDTO supdto;
		SupervisorDTO reptodto;
		InstituteDTO idto;
		FacultyDTO fdto;
		DepartmentDTO ddto;
		List supList = new ArrayList();
		try {

			logger.info("Fetching supervisors by institute ID " + instituteid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SUPERVISORS_INSTITUTEWISE);
			stmnt.setInt(1, instituteid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				supdto = new SupervisorDTO();
				reptodto = new SupervisorDTO();
				idto = new InstituteDTO();
				fdto = new FacultyDTO();
				ddto = new DepartmentDTO();
				supdto.setSupervisorID(rs.getString(1));
				supdto.setSupervisorFirstName(rs.getString(2));
				supdto.setSupervisorMiddleName(rs.getString(3));
				supdto.setSupervisorLastName(rs.getString(4));
				supdto.setSupervisorScreenName(rs.getString(5));
				ddto.setDepartmentname(rs.getString(6));
				fdto.setFacultyname(rs.getString(7));
				idto.setInstitutename(rs.getString(8));
				supdto.setStatus(rs.getString(9));
				reptodto.setSupervisorID(rs.getString(10));
				supdto.setRole(rs.getString(10));
				supdto.setReportto(reptodto);
				supdto.setInstitute(idto);
				supdto.setFaculty(fdto);
				supdto.setDepartment(ddto);
				supList.add(supdto);
			}
			logger.info("Supervisor fetched  : " + supList.size());
		} catch (SQLException e) {
			logger.error("Error while fetching supervisors by institute ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return supList;
	}

	public List getSupevisorsFacultywise(int facultyid) {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		SupervisorDTO supdto;
		SupervisorDTO reptodto;
		InstituteDTO idto;
		FacultyDTO fdto;
		DepartmentDTO ddto;
		List supList = new ArrayList();
		try {

			logger.info("Fetching supervisors by faculty ID " + facultyid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SUPERVISORS_FACULTYWISE);
			stmnt.setInt(1, facultyid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				supdto = new SupervisorDTO();
				reptodto = new SupervisorDTO();
				idto = new InstituteDTO();
				fdto = new FacultyDTO();
				ddto = new DepartmentDTO();
				supdto.setSupervisorID(rs.getString(1));
				supdto.setSupervisorFirstName(rs.getString(2));
				supdto.setSupervisorMiddleName(rs.getString(3));
				supdto.setSupervisorLastName(rs.getString(4));
				supdto.setSupervisorScreenName(rs.getString(5));
				ddto.setDepartmentname(rs.getString(6));
				fdto.setFacultyname(rs.getString(7));
				idto.setInstitutename(rs.getString(8));
				supdto.setStatus(rs.getString(9));
				supdto.setRole(rs.getString(10));
				supdto.setReportto(reptodto);
				supdto.setInstitute(idto);
				supdto.setFaculty(fdto);
				supdto.setDepartment(ddto);
				supList.add(supdto);
			}
			logger.info("Supervisor fetched  : " + supList.size());
		} catch (SQLException e) {
			logger.error("Error while fetching supervisors by faculty ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return supList;
	}

	public List getSupevisorsDepartmentwise(int departmentid) {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		SupervisorDTO supdto;
		SupervisorDTO reptodto;
		InstituteDTO idto;
		FacultyDTO fdto;
		DepartmentDTO ddto;
		List supList = new ArrayList();
		try {

			logger.info("Fetching supervisors by department ID " + departmentid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SUPERVISORS_DEPARTMENTWISE);
			stmnt.setInt(1, departmentid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				supdto = new SupervisorDTO();
				reptodto = new SupervisorDTO();
				idto = new InstituteDTO();
				fdto = new FacultyDTO();
				ddto = new DepartmentDTO();
				supdto.setSupervisorID(rs.getString(1));
				supdto.setSupervisorFirstName(rs.getString(2));
				supdto.setSupervisorMiddleName(rs.getString(3));
				supdto.setSupervisorLastName(rs.getString(4));
				supdto.setSupervisorScreenName(rs.getString(5));
				ddto.setDepartmentname(rs.getString(6));
				fdto.setFacultyname(rs.getString(7));
				idto.setInstitutename(rs.getString(8));
				supdto.setStatus(rs.getString(9));
				supdto.setRole(rs.getString(10));
				supdto.setReportto(reptodto);
				supdto.setInstitute(idto);
				supdto.setFaculty(fdto);
				supdto.setDepartment(ddto);
				supList.add(supdto);
			}
			logger.info("Supervisor fetched  : " + supList.size());
		} catch (SQLException e) {
			logger.error("Error while fetching supervisors by department ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return supList;
	}

	public Map getSupevisorsDepartmentMap(int facultyid) {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		String dep = new String();
		ArrayList supList;
		SupervisorDTO tempSup;
		String supId = new String();
		String supName = new String();
		Map depMap = new HashMap();
		try {
			
			logger.info("Retrieving department supervisors map by faculty Id " + facultyid);
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_SUPERVISORS_DEPARTMENTWISEMAP);
			stmnt.setInt(1,facultyid);
			rs = stmnt.executeQuery();
				
			while(rs.next()){
				dep = rs.getString(1);
				supId = rs.getString(2);
				supName = new StringBuffer().append(rs.getString(3)).append(rs.getString(4)).append(rs.getString(5)).toString();
				supName = supName == null ? "" : supName; 
				if(dep!=null){
				if(depMap.containsKey(dep)){
					tempSup = new SupervisorDTO();
					tempSup.setSupervisorID(supId);
					tempSup.setSupervisorFirstName(supName);
					supList = (ArrayList) depMap.get(dep);
					supList.add(tempSup);
					depMap.remove(dep);
					depMap.put(dep, supList);
				}else{
					supList = new ArrayList();
					tempSup = new SupervisorDTO();
					tempSup.setSupervisorID(supId);
					tempSup.setSupervisorFirstName(supName);
					supList.add(tempSup);
					depMap.put(dep, supList);
				}
				}
			}
			
			logger.info("Department wise supervisor map created successfully");
			
		} catch (SQLException e) {
			logger.error("Unable to create department wise supervisor map" + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
	
		return depMap;
	}

}
