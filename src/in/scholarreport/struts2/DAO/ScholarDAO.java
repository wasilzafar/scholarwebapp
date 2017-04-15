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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

public class ScholarDAO extends BaseDAO {
	static Logger logger = Logger.getLogger(ScholarDAO.class);

	public int checkExistingScholar(String fName, String mName, String lName,
			String dob, String email, int enrollno) {
		java.sql.Connection conn = getConnection();
		ResultSet rs;
		int dupSup = 0;
		try {
			logger.info("Checking existing scholar for " + fName + " " + mName
					+ " " + lName + " " + dob + " " + email + " " + enrollno);
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_EXISTING_SCHOLAR);
			stmnt.setString(1, fName);
			stmnt.setString(2, mName);
			stmnt.setString(3, lName);
			if(dob.equalsIgnoreCase("") || dob != null )
			stmnt.setDate(4, CommonUtilities.getSQLDate(dob));
			stmnt.setString(5, email);
			stmnt.setInt(6, enrollno);

			rs = stmnt.executeQuery();
			while (rs.next()) {
				dupSup = rs.getInt(1);
			}
			logger.info("Existing Scholar : " + dupSup);
		} catch (SQLException e) {
			logger.error("Error while checking existing scholar "
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

	public boolean persistScholar(ScholarDTO schdto) {
		boolean persisted = false;
		java.sql.Connection conn = getConnection();
		try {
			logger.info("Persisting scholar : " + schdto.getScholarFirstName()
					+ " " + schdto.getScholarMiddleName() + " "
					+ schdto.getScholarLastName());

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.PERSIST_NEW_SCHOLAR);
			stmnt.setString(2, schdto.getScholarFirstName());
			stmnt.setString(3, schdto.getScholarMiddleName());
			stmnt.setString(4, schdto.getScholarLastName());
			stmnt.setString(5, schdto.getScholarScreenName());
			stmnt.setString(6, schdto.getScholarFatherFirstName());
			stmnt.setString(7, schdto.getScholarFatherLastName());
			stmnt.setString(8, schdto.getScholarSpouseFirstName());
			stmnt.setString(9, schdto.getScholarSpouseLastName());
			if(schdto.getDob() != null || schdto.getDob().equalsIgnoreCase(""))
			stmnt.setDate(10, CommonUtilities.getSQLDate(schdto.getDob()));
			stmnt.setString(11, schdto.getGender());
			stmnt.setString(12, schdto.getNationality());
			stmnt.setString(13, schdto.getMobileNumber());
			stmnt.setString(14, schdto.getLandlineNumber());
			stmnt.setString(15, schdto.getEmailID());
			stmnt.setString(16, schdto.getCorrespondenceAddress());
			stmnt.setInt(17, CommonUtilities.getStateId(schdto
					.getCorrespondenceAddressState().getStatename()));
			stmnt.setInt(18, schdto.getCorrespondenceAddressDistrict()
					.getDistrictid());
			stmnt.setString(19, schdto.getCorrespondenceAddressZipCode());
			stmnt.setString(20, schdto.getPermanentAddress());
			stmnt.setInt(21, CommonUtilities.getStateId(schdto
					.getPermanentAddressState().getStatename()));
			stmnt.setInt(22, schdto.getPermanentAddressDistrict()
					.getDistrictid());
			stmnt.setString(23, schdto.getPermanentAddressZipCode());
			stmnt.setString(24, schdto.getCategory());
			stmnt.setString(25, schdto.getCategorycode());
			stmnt.setString(26, schdto.getCoursename());
			stmnt.setInt(27, schdto.getInstitute().getInstituteid());
			stmnt.setInt(28, schdto.getFaculty().getFacultyid());
			stmnt.setInt(29, schdto.getDepartment().getDepartmentid());
			stmnt.setInt(30, schdto.getEnrollmentNumber());
			if(schdto.getDateOfRegistration() != null || schdto.getDateOfRegistration().equalsIgnoreCase(""))
			stmnt.setDate(31,
					CommonUtilities.getSQLDate(schdto.getDateOfRegistration()));
			stmnt.setString(32, schdto.getTopic());
			stmnt.setString(33, schdto.getLanguagesKnown());
			stmnt.setString(34, "INACTIVE");
			stmnt.setString(35, schdto.getSupervisor().getSupervisorID());
			stmnt.setString(36, schdto.getCoSupervisor().getSupervisorID());
			stmnt.setInt(37, schdto.getTotalExperience());
			stmnt.setString(38, schdto.getExamsPassed());
			stmnt.setString(39, schdto.getInstitutionsName());
			stmnt.setString(40, schdto.getYearsOfPassing());
			stmnt.setString(41, schdto.getPercentages());
			stmnt.setString(42, schdto.getSubjects());
			stmnt.setString(43, "N");
			stmnt.setString(44, schdto.getUn_rno());
			stmnt.setString(45, schdto.getUn_xam_yr());
			stmnt.setString(46, schdto.getUn_ref_no());
			stmnt.setString(47, schdto.getUn_ecert_no());
			stmnt.setString(48, "N");
			stmnt.setString(49, schdto.getSl_rno());
			stmnt.setString(50, schdto.getSl_xam_yr());
			stmnt.setString(51, schdto.getSl_ref_no());
			stmnt.setString(52, schdto.getSl_ecert_no());
			stmnt.setString(53, "N");
			stmnt.setString(54, schdto.getCun_rno());
			stmnt.setString(55, schdto.getCun_xam_yr());
			stmnt.setString(56, schdto.getCun_ref_no());
			stmnt.setString(57, schdto.getCun_ecert_no());
			stmnt.setString(58, schdto.getEmployersname());
			stmnt.setString(59, schdto.getPositionsHeld());
			stmnt.setString(60, schdto.getEmployedfrom());
			stmnt.setString(61, schdto.getEmployedto());
			stmnt.setDate(62, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setDate(63, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setString(64, schdto.getTopicModified());
			if(schdto.getDateOfTopicModification() != null || schdto.getDateOfTopicModification().equalsIgnoreCase(""))
			stmnt.setDate(65, CommonUtilities.getSQLDate(schdto
					.getDateOfTopicModification()));
			if (stmnt.executeUpdate() > 0)
				persisted = true;

			logger.info("Persisted successfully ? " + persisted);
		} catch (SQLException e) {
			logger.info("Error while persisting scholar " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return persisted;
	}
	
	
	public boolean updateScholar(ScholarDTO schdto) {
		boolean updated = false;
		java.sql.Connection conn = getConnection();
		try {
			logger.info("Updating scholar : " + schdto.getScholarFirstName()
					+ " " + schdto.getScholarMiddleName() + " "
					+ schdto.getScholarLastName());

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.UPDATE_SCHOLAR);
			stmnt.setString(1, schdto.getScholarID());
			stmnt.setString(1, schdto.getScholarFirstName());
			stmnt.setString(2, schdto.getScholarMiddleName());
			stmnt.setString(3, schdto.getScholarLastName());
			stmnt.setString(4, schdto.getScholarScreenName());
			stmnt.setString(5, schdto.getScholarFatherFirstName());
			stmnt.setString(6, schdto.getScholarFatherLastName());
			stmnt.setString(7, schdto.getScholarSpouseFirstName());
			stmnt.setString(8, schdto.getScholarSpouseLastName());
			if(schdto.getDob() != null || schdto.getDob().equalsIgnoreCase(""))
			stmnt.setDate(9, CommonUtilities.getSQLDate(schdto.getDob()));
			stmnt.setString(10, schdto.getGender());
			stmnt.setString(11, schdto.getNationality());
			stmnt.setString(12, schdto.getMobileNumber());
			stmnt.setString(13, schdto.getLandlineNumber());
			stmnt.setString(14, schdto.getEmailID());
			stmnt.setString(15, schdto.getCorrespondenceAddress());
			stmnt.setInt(16, CommonUtilities.getStateId(schdto
					.getCorrespondenceAddressState().getStatename()));
			stmnt.setInt(17, schdto.getCorrespondenceAddressDistrict()
					.getDistrictid());
			stmnt.setString(18, schdto.getCorrespondenceAddressZipCode());
			stmnt.setString(19, schdto.getPermanentAddress());
			stmnt.setInt(20, CommonUtilities.getStateId(schdto
					.getPermanentAddressState().getStatename()));
			stmnt.setInt(21, schdto.getPermanentAddressDistrict()
					.getDistrictid());
			stmnt.setString(22, schdto.getPermanentAddressZipCode());
			stmnt.setString(23, schdto.getCategory());
			stmnt.setString(24, schdto.getCategorycode());
			stmnt.setString(25, schdto.getCoursename());
			stmnt.setInt(26, schdto.getInstitute().getInstituteid());
			stmnt.setInt(27, schdto.getFaculty().getFacultyid());
			stmnt.setInt(28, schdto.getDepartment().getDepartmentid());
			stmnt.setInt(29, schdto.getEnrollmentNumber());
			if(schdto.getDateOfRegistration() != null || schdto.getDateOfRegistration().equalsIgnoreCase(""))
			stmnt.setDate(30,
					CommonUtilities.getSQLDate(schdto.getDateOfRegistration()));
			stmnt.setString(31, schdto.getTopic());
			stmnt.setString(32, schdto.getLanguagesKnown());
			stmnt.setString(33, schdto.getStatus());
			stmnt.setString(34, schdto.getSupervisor().getSupervisorID());
			stmnt.setString(35, schdto.getCoSupervisor().getSupervisorID());
			stmnt.setInt(36, schdto.getTotalExperience());
			stmnt.setString(37, schdto.getExamsPassed());
			stmnt.setString(38, schdto.getInstitutionsName());
			stmnt.setString(39, schdto.getYearsOfPassing());
			stmnt.setString(40, schdto.getPercentages());
			stmnt.setString(41, schdto.getSubjects());
			
			stmnt.setString(42, "N");
			stmnt.setString(43, schdto.getUn_rno());
			stmnt.setString(44, schdto.getUn_xam_yr());
			stmnt.setString(45, schdto.getUn_ref_no());
			stmnt.setString(46, schdto.getUn_ecert_no());
			
			stmnt.setString(47, "N");
			stmnt.setString(48, schdto.getSl_rno());
			stmnt.setString(49, schdto.getSl_xam_yr());
			stmnt.setString(50, schdto.getSl_ref_no());
			stmnt.setString(51, schdto.getSl_ecert_no());
			
			stmnt.setString(52, "N");
			stmnt.setString(53, schdto.getCun_rno());
			stmnt.setString(54, schdto.getCun_xam_yr());
			stmnt.setString(55, schdto.getCun_ref_no());
			stmnt.setString(56, schdto.getCun_ecert_no());
			
			stmnt.setString(57, schdto.getEmployersname());
			stmnt.setString(58, schdto.getPositionsHeld());
			stmnt.setString(59, schdto.getEmployedfrom());
			stmnt.setString(60, schdto.getEmployedto());
			stmnt.setDate(61, CommonUtilities.getSQLDate(schdto.getDateCreated()));
			stmnt.setDate(62, new java.sql.Date(new java.util.Date().getTime()));
			stmnt.setString(63, schdto.getTopicModified());
			if(schdto.getDateOfTopicModification() != null || schdto.getDateOfTopicModification().equalsIgnoreCase(""))
			stmnt.setDate(64, CommonUtilities.getSQLDate(schdto
					.getDateOfTopicModification()));
			stmnt.setString(65, schdto.getScholarID());
			if (stmnt.executeUpdate() > 0)
				updated = true;

			logger.info("Updated successfully ? " + updated);
		} catch (SQLException e) {
			logger.info("Error while persisting scholar " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return updated;
	}

	public ScholarDTO getScholarByID(String id) {

		java.sql.Connection conn = getConnection();
		ScholarDTO sch = null;
		SupervisorDTO sup;
		ResultSet rs;
		DepartmentDTO dep;
		FacultyDTO fac;
		InstituteDTO ins;
		try {
			logger.info("Fetching scholars by ID : " + id);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.RETRIEVE_SCHOLAR_BY_SCHOLAR_EMAILID);
			stmnt.setString(1, id);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sch.setDateOfRegistration(rs.getDate(6).toString());
				sch.setTopic(rs.getString(7));
				dep.setDepartmentname(rs.getString(8));
				fac.setFacultyname(rs.getString(9));
				ins.setInstitutename(rs.getString(10));
				sup.setSupervisorID(rs.getString(11));
				sup.setSupervisorFirstName(rs.getString(12));
				sup.setSupervisorMiddleName(rs.getString(13));
				sup.setSupervisorLastName(rs.getString(14));
				sup.setSupervisorScreenName(rs.getString(15));
				sup.setRole(rs.getString(16));
				sch.setInstitute(ins);
				sch.setFaculty(fac);
				sch.setDepartment(dep);
				sch.setSupervisor(sup);
			}

			logger.info("Retrieved scholar by id = " + id + " ? ");
			if (sch == null)
				logger.info("null");
			else
				logger.info(sch.toString());
		} catch (SQLException e) {
			logger.info("Error while getting scholar by ID " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return sch;
	}

	
	public ScholarDTO getScholarByScholarID(String id) {

		java.sql.Connection conn = getConnection();
		ScholarDTO sch = null;
		SupervisorDTO sup;
		SupervisorDTO cosup;
		ResultSet rs;
		DepartmentDTO dep;
		FacultyDTO fac;
		InstituteDTO ins;
		DistrictDTO cdist;
		StateDTO cstate;
		DistrictDTO pdist;
		StateDTO pstate;
		try {
			logger.info("Fetching scholars by ID : " + id);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.RETRIEVE_SCHOLAR_BY_SCHOLAR_ID);
			stmnt.setString(1, id);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				cosup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				cdist = new DistrictDTO();
				cstate = new StateDTO();
				pdist = new DistrictDTO();
				pstate = new StateDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sch.setScholarFatherFirstName(rs.getString(6));
				sch.setScholarFatherLastName(rs.getString(7));
				sch.setScholarSpouseFirstName(rs.getString(8));
				sch.setScholarSpouseLastName(rs.getString(9));
				sch.setDob(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(10)));
				sch.setGender(rs.getString(11));
				sch.setNationality(rs.getString(12));
				sch.setMobileNumber(rs.getString(13));
				sch.setLandlineNumber(rs.getString(14));
				sch.setEmailID(rs.getString(15));
				sch.setCorrespondenceAddress(rs.getString(16));
				cstate.setStateid(rs.getInt(17));
				sch.setCorrespondenceAddressState(cstate);
				cdist.setDistrictid(rs.getInt(18));
				sch.setCorrespondenceAddressDistrict(cdist);
				sch.setCorrespondenceAddressZipCode(rs.getString(19));
				sch.setPermanentAddress(rs.getString(20));
				pstate.setStateid(rs.getInt(21));
				sch.setPermanentAddressState(pstate);
				pdist.setDistrictid(rs.getInt(22));
				sch.setPermanentAddressDistrict(pdist);
				sch.setPermanentAddressZipCode(rs.getString(23));
				sch.setCategory(rs.getString(24));
				sch.setCategorycode(rs.getString(25));
				sch.setCoursename(rs.getString(26));
				ins.setInstituteid(rs.getInt(27));
				ins.setInstitutename(rs.getString(28));
				fac.setFacultyid(rs.getInt(29));
				fac.setFacultyname(rs.getString(30));
				dep.setDepartmentid(rs.getInt(31));
				dep.setDepartmentname(rs.getString(32));
				sch.setInstitute(ins);
				sch.setFaculty(fac);
				sch.setDepartment(dep);
				sch.setEnrollmentNumber(rs.getInt(33));
				sch.setDateOfRegistration(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(34)));
				sch.setTopic(rs.getString(35));
				sch.setLanguagesKnown(rs.getString(36));
				sch.setStatus(rs.getString(37));
				sup.setSupervisorID(rs.getString(38));
				sup.setSupervisorFirstName(rs.getString(39));
				sup.setSupervisorMiddleName(rs.getString(40));
				sup.setSupervisorLastName(rs.getString(41));
				sup.setSupervisorScreenName(rs.getString(42));
				cosup.setSupervisorID(rs.getString(43));
				cosup.setSupervisorFirstName(rs.getString(44));
				cosup.setSupervisorMiddleName(rs.getString(45));
				cosup.setSupervisorLastName(rs.getString(46));
				cosup.setSupervisorScreenName(rs.getString(47));
				sch.setSupervisor(sup);
				sch.setCoSupervisor(cosup);
				sch.setTotalExperience(rs.getInt(48));
				sch.setExamsPassed(rs.getString(49));
				sch.setInstitutionsName(rs.getString(50));
				sch.setYearsOfPassing(rs.getString(51));
				sch.setPercentages(rs.getString(52));
				sch.setSubjects(rs.getString(53));
				if (rs.getString(54).equalsIgnoreCase("N"))
					sch.setUn(false);
				else
					sch.setUn(true);
				sch.setUn_rno(rs.getString(55));
				sch.setUn_xam_yr(rs.getString(56));
				sch.setUn_ref_no(rs.getString(57));
				sch.setUn_ecert_no(rs.getString(58));
				
				if (rs.getString(59).equalsIgnoreCase("N"))
					sch.setSl(false);
				else
					sch.setSl(true);
				sch.setSl_rno(rs.getString(60));
				sch.setSl_xam_yr(rs.getString(61));
				sch.setSl_ref_no(rs.getString(62));
				sch.setSl_ecert_no(rs.getString(63));
				
				if (rs.getString(64).equalsIgnoreCase("N"))
					sch.setCun(false);
				else
					sch.setCun(true);
				sch.setCun_rno(rs.getString(65));
				sch.setCun_xam_yr(rs.getString(66));
				sch.setCun_ref_no(rs.getString(67));
				sch.setCun_ecert_no(rs.getString(68));
				
				sch.setEmployersname(rs.getString(69));
				sch.setPositionsHeld(rs.getString(70));
				sch.setEmployedfrom(rs.getString(71));
				sch.setEmployedto(rs.getString(72));
				sch.setDateCreated(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(73)));
				sch.setDateModified(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(74)));
				sch.setTopicModified(rs.getString(75));
				sch.setDateOfTopicModification(CommonUtilities.getFormattedDateFromSQLDate(rs.getDate(76)));
			}

			logger.info("Retrieved scholar by id = " + id + " ? ");
			if (sch == null)
				logger.info("null");
			else
				logger.info(sch.toString());
		} catch (SQLException e) {
			logger.info("Error while getting scholar by ID " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return sch;
	}
	
	public List getScholars() {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ScholarDTO sch;
		SupervisorDTO sup;
		InstituteDTO ins;
		FacultyDTO fac;
		DepartmentDTO dep;
		ResultSet rs;
		try {

			logger.info("Fetching scholars  ");

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SCHOLARS);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sup.setSupervisorID(rs.getString(6));
				sup.setSupervisorFirstName(rs.getString(7));
				sup.setSupervisorMiddleName(rs.getString(8));
				sup.setSupervisorLastName(rs.getString(9));
				sch.setSupervisor(sup);
				ins.setInstitutename(rs.getString(10));
				sch.setInstitute(ins);
				fac.setFacultyname(rs.getString(11));
				sch.setFaculty(fac);
				dep.setDepartmentname(rs.getString(12));
				sch.setDepartment(dep);
				sch.setStatus(rs.getString(13));

				schList.add(sch);

			}

			logger.info("Scholar fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while fetching scholars " + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public boolean setScholar(ScholarDTO newScholar, String cred) {
		java.sql.Connection conn = getConnection();
		boolean inserted = false;
		try {
			logger.info("Setting scholar details for "
					+ newScholar.getEmailID());
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.SET_SCHOLAR);
			stmnt.setString(1, newScholar.getScholarID());
			stmnt.setString(2, newScholar.getEmailID());
			stmnt.setString(3, cred);

			if (stmnt.executeUpdate() > 0)
				inserted = true;

			logger.info("Scholar set " + inserted);
		} catch (SQLException e) {
			logger.info("Error while setting scholar details " + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return inserted;
	}

	public String activateScholarsById(String[] ids) {
		boolean allActivated = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(
				SQLQueries.UPDATE_STATUS_SCHOLARS_BY_ID);
		try {
			logger.info("Activate scholars by IDs" + idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, "'"
					+ CommonConstants.STATUS_ACTIVE + "'");
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);
			logger.info("Query for activation : " + query.toString());
			if (stmnt.executeUpdate(query.toString()) == ids.length) {
				logger.info("All scholars activated for " + idStr);
				allActivated = true;
			} else
				logger.info("There was some error while activating scholars "
						+ idStr);
		} catch (SQLException e) {
			logger.error("Error while activating scholars  " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allActivated);
	}

	public String inactivateScholarsById(String[] ids) {
		boolean allDeactivated = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);
		StringBuffer query = new StringBuffer(
				SQLQueries.UPDATE_STATUS_SCHOLARS_BY_ID);
		try {
			logger.info("Inactivate scholars by IDs" + idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, "'"
					+ CommonConstants.STATUS_INACTIVE + "'");
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);
			logger.info("Query for activation : " + query.toString());
			if (stmnt.executeUpdate(query.toString()) == ids.length) {
				logger.info("All scholars inactivated for " + idStr);
				allDeactivated = true;
			} else
				logger.info("There was some error while inactivating scholars "
						+ idStr);
		} catch (SQLException e) {
			logger.error("Error while inactivating scholars  " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allDeactivated);
	}

	public String deleteScholarsById(String[] ids) {
		boolean allDeleted = false;
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(ids);

		try {
			StringBuffer initquery = new StringBuffer(
					SQLQueries.DELETE_SCHOLARLOGIN_BY_ID);
			logger.info("Deleting scholars by IDs" + idStr);
			Statement stmnt = conn.createStatement();
			initquery.replace(initquery.indexOf("?"),
					initquery.indexOf("?") + 1, idStr);

			logger.info("Query for scholar login deletion : "
					+ initquery.toString());

			if (stmnt.executeUpdate(initquery.toString()) == ids.length) {
				logger.info("Deleted all scholar login for " + idStr);
				allDeleted = true;
			} else
				logger.info("There was some error while deleting scholars login "
						+ idStr);

			StringBuffer query = new StringBuffer(
					SQLQueries.DELETE_SCHOLARS_BY_ID);
			stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);

			logger.info("Query for deletion : " + query.toString());

			if (stmnt.executeUpdate(query.toString()) == ids.length) {
				logger.info("Deleted all scholars for " + idStr);
				allDeleted = true;
			} else
				logger.info("There was some error while deleting scholars "
						+ idStr);

		} catch (SQLException e) {
			logger.error("Error while deleting scholars " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return String.valueOf(allDeleted);
	}

	public List getScholarsInstituteWise(int instituteid) {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ScholarDTO sch;
		SupervisorDTO sup;
		InstituteDTO ins;
		FacultyDTO fac;
		DepartmentDTO dep;
		ResultSet rs;
		try {

			logger.info("Fetching scholars by Institute ID " + instituteid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SCHOLARS_INSTITUTEWISE);
			stmnt.setInt(1, instituteid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sup.setSupervisorID(rs.getString(6));
				sup.setSupervisorFirstName(rs.getString(7));
				sup.setSupervisorMiddleName(rs.getString(8));
				sup.setSupervisorLastName(rs.getString(9));
				sch.setSupervisor(sup);
				ins.setInstitutename(rs.getString(10));
				sch.setInstitute(ins);
				fac.setFacultyname(rs.getString(11));
				sch.setFaculty(fac);
				dep.setDepartmentname(rs.getString(12));
				sch.setDepartment(dep);
				sch.setStatus(rs.getString(13));

				schList.add(sch);

			}

			logger.info("Scholar fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while fetching scholars by Institute ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List getScholarsFacultyWise(int facultyid) {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ScholarDTO sch;
		SupervisorDTO sup;
		InstituteDTO ins;
		FacultyDTO fac;
		DepartmentDTO dep;
		ResultSet rs;
		try {

			logger.info("Fetching scholars by faculty ID " + facultyid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SCHOLARS_FACULTYWISE);
			stmnt.setInt(1, facultyid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sup.setSupervisorID(rs.getString(6));
				sup.setSupervisorFirstName(rs.getString(7));
				sup.setSupervisorMiddleName(rs.getString(8));
				sup.setSupervisorLastName(rs.getString(9));
				sch.setSupervisor(sup);
				ins.setInstitutename(rs.getString(10));
				sch.setInstitute(ins);
				fac.setFacultyname(rs.getString(11));
				sch.setFaculty(fac);
				dep.setDepartmentname(rs.getString(12));
				sch.setDepartment(dep);
				sch.setStatus(rs.getString(13));

				schList.add(sch);

			}

			logger.info("Scholar fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while fetching scholars by faculty ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List getScholarsDepartmentWise(int departmentid) {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ScholarDTO sch;
		SupervisorDTO sup;
		InstituteDTO ins;
		FacultyDTO fac;
		DepartmentDTO dep;
		ResultSet rs;
		try {

			logger.info("Fetching scholars by department ID " + departmentid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SCHOLARS_DEPARTMENTWISE);
			stmnt.setInt(1, departmentid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sup.setSupervisorID(rs.getString(6));
				sup.setSupervisorFirstName(rs.getString(7));
				sup.setSupervisorMiddleName(rs.getString(8));
				sup.setSupervisorLastName(rs.getString(9));
				sch.setSupervisor(sup);
				ins.setInstitutename(rs.getString(10));
				sch.setInstitute(ins);
				fac.setFacultyname(rs.getString(11));
				sch.setFaculty(fac);
				dep.setDepartmentname(rs.getString(12));
				sch.setDepartment(dep);
				sch.setStatus(rs.getString(13));

				schList.add(sch);

			}

			logger.info("Scholar fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while fetching scholars by department ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List getScholarsBySupervisorID(String supervisorID) {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ScholarDTO sch;
		SupervisorDTO sup;
		InstituteDTO ins;
		FacultyDTO fac;
		DepartmentDTO dep;
		ResultSet rs;
		try {

			logger.info("Fetching scholars by supervisor ID " + supervisorID);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SCHOLARS_BY_SUPERVISORID);
			stmnt.setString(1, supervisorID);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				dep = new DepartmentDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sch.setScholarScreenName(rs.getString(5));
				sup.setSupervisorID(rs.getString(6));
				sup.setSupervisorFirstName(rs.getString(7));
				sup.setSupervisorMiddleName(rs.getString(8));
				sup.setSupervisorLastName(rs.getString(9));
				sch.setSupervisor(sup);
				ins.setInstitutename(rs.getString(10));
				sch.setInstitute(ins);
				fac.setFacultyname(rs.getString(11));
				sch.setFaculty(fac);
				dep.setDepartmentname(rs.getString(12));
				sch.setDepartment(dep);
				sch.setStatus(rs.getString(13));

				schList.add(sch);

			}

			logger.info("Scholar fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while fetching scholars by supervisor ID "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List searchScholarByNameForDepartment(String q, int departmentid) {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ScholarDTO sch;
		SupervisorDTO sup;
		SupervisorDTO cosup;
		ResultSet rs;
		try {

			logger.info("Searching scholars by supervisor ID " + departmentid);

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.SEARCH_SCHOLARS_BY_NAME_QUERY);
			stmnt.setString(1, "%"+q+"%");
			stmnt.setString(2, "%"+q+"%");
			stmnt.setString(3, "%"+q+"%");
			stmnt.setInt(4, departmentid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				sch = new ScholarDTO();
				sup = new SupervisorDTO();
				cosup = new SupervisorDTO();
				sch.setScholarID(rs.getString(1));
				sch.setScholarFirstName(rs.getString(2));
				sch.setScholarMiddleName(rs.getString(3));
				sch.setScholarLastName(rs.getString(4));
				sup.setSupervisorFirstName(rs.getString(5));
				sup.setSupervisorLastName(rs.getString(6));
				cosup.setSupervisorFirstName(rs.getString(7));
				cosup.setSupervisorLastName(rs.getString(8));
				sch.setTopic(rs.getString(9));
				sch.setSupervisor(sup);
				sch.setCoSupervisor(cosup);
				schList.add(sch);

			}

			logger.info("Scholar fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while searching scholars "
					+ e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List getScholarsEmailIDforDepartment(int depid) {

		java.sql.Connection conn = getConnection();
		List schList = new ArrayList();
		ResultSet rs;
		try {

			logger.info("Fetching scholars email ids ");

			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_SCHOLARS_EMAILID_FOR_DEPARTMENT);
			stmnt.setInt(1, depid);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				schList.add(rs.getString(1));
			}

			logger.info("Scholar email ID fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.info("Error while fetching scholars email ids" + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List getSupervisorsEmailIDforScholars(String[] schids) {

		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(schids);
		
		List schList = new ArrayList();
		ResultSet rs;
		StringBuffer query = new StringBuffer(
				SQLQueries.GET_SUPERVISORS_EMAILID_SCHOLARID);
		try {
			logger.info("Get supervisor email IDs" + idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);
			logger.info("Query for execution : " + query.toString());
			rs = stmnt.executeQuery(query.toString());
			while (rs.next()) {
				schList.add(rs.getString(1));
			}
			logger.info("Supervisor email ID fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.error("Error while activating scholars  " + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

	public List getScholarNamesForIds(String[] schids) {
		java.sql.Connection conn = getConnection();
		String idStr = CommonUtilities.getStringFromArray(schids);
		
		List schList = new ArrayList();
		ResultSet rs;
		StringBuffer query = new StringBuffer(
				SQLQueries.GET_SCHOLARNAMES_FOR_SCHOLARID);
		try {
			logger.info("Get scholar names for IDs" + idStr);
			Statement stmnt = conn.createStatement();
			query.replace(query.indexOf("?"), query.indexOf("?") + 1, idStr);
			logger.info("Query for execution : " + query.toString());
			rs = stmnt.executeQuery(query.toString());
			while (rs.next()) {
				StringBuffer temp = new StringBuffer("");
				String tempStr = rs.getString(1);
				if(tempStr != null) temp.append(tempStr).append(" ");

				tempStr = rs.getString(2);
				if(tempStr != null) temp.append(tempStr).append(" ");

				tempStr = rs.getString(3);
				if(tempStr != null) temp.append(tempStr);
				
				schList.add(temp.toString());
			}
			logger.info("Supervisor names for IDs fetched  : " + schList.size());
		} catch (SQLException e) {
			logger.error("Error while fetching scholar names  " + e.getMessage());
		}

		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return schList;
	}

}
