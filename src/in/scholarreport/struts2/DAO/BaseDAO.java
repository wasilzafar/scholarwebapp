package in.scholarreport.struts2.DAO;

import in.scholarreport.struts2.DTO.ColloquiumDTO;
import in.scholarreport.struts2.DTO.DepartmentDTO;
import in.scholarreport.struts2.DTO.DistrictDTO;
import in.scholarreport.struts2.DTO.FacultyDTO;
import in.scholarreport.struts2.DTO.InstituteDTO;
import in.scholarreport.struts2.util.CommonUtilities;
import in.scholarreport.struts2.util.SQLQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


public class BaseDAO implements DAO {

	static Logger logger = Logger.getLogger(BaseDAO.class);
	
	public Connection getConnection() {
		Connection conn = null;
		ServletContext ServletContext = ServletActionContext
				.getServletContext();
		DataSource dataSource = (DataSource) ServletContext
				.getAttribute("dataSource");
		
		 try {
			conn = dataSource.getConnection();
			logger.info("Connection acquired "+ conn);
		} catch (SQLException e) {
			logger.error("Unable to acquire Connection Object " + e.getMessage());
		}
		 
		 return conn;
	}
	
	public int getExistingFaculty(FacultyDTO facultyDTO) {

		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		int dupFac = 0;
		try {
			logger.info("Retrieving existing faculties for "+facultyDTO.getFacultyname());
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_EXISTING_FACULTY);
			stmnt.setString(1, facultyDTO.getFacultyname());
			stmnt.setInt(2, facultyDTO.getInstitute().getInstituteid());
			rs = stmnt.executeQuery();

			while (rs.next()) {
				dupFac = rs.getInt(1);
			}
			if(dupFac>0)
			logger.info("Found duplicate faculty for " + facultyDTO.getFacultyname());
			else
				logger.info("No duplicate faculty found for " + facultyDTO.getFacultyname());
		} catch (SQLException e) {
			logger.error("Error while getting existing faculty " + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
		return dupFac;

	}

	public boolean persistFaculty(FacultyDTO facultyDTO) {

		java.sql.Connection conn = getConnection();
		int n;
		try {
			logger.info("persisting new faculty "+facultyDTO.getFacultyname());
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.PERSIST_NEW_FACULTY);
		    stmnt.setString(1, facultyDTO.getFacultyname());
		    stmnt.setInt(2, facultyDTO.getInstitute().getInstituteid());
		    n = stmnt.executeUpdate();
		    
		    if(n > 0)
		    	logger.info("Inserted faculty successfully "+ facultyDTO);
		    else
		    	logger.info("Error while inserting faculty "+ facultyDTO);
		} catch (SQLException e) {
			logger.error("Error while persisting new faculty "+ facultyDTO + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return false;
	}
	
	public boolean updateFaculty(FacultyDTO facultyDTO) {

		java.sql.Connection conn = getConnection();
		int n = 0;
		try {
			logger.info("Update faculty "+facultyDTO.getFacultyid());
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.UPDATE_FACULTY);
		    stmnt.setString(1, facultyDTO.getFacultyname());
		    stmnt.setInt(2, facultyDTO.getInstitute().getInstituteid());
		    stmnt.setInt(3, facultyDTO.getFacultyid());
		    n = stmnt.executeUpdate();
		    
		    if(n > 0)
		    	logger.info("Updated faculty successfully "+ facultyDTO.getFacultyname());
		    else
		    	logger.info("Error while updating faculty "+ facultyDTO.getFacultyname());
		} catch (SQLException e) {
			logger.error("Error while updating  faculty "+ facultyDTO.getFacultyname() + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		if(n > 0) return true;
		return false;
	}
	
	
	public int getExistingDepartment( int facID, String dep ){


		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		int dupDep = 0;
		try {
			logger.info("Retrieving existing departments for "+dep);
			
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_EXISTING_DEPARTMENT);
			stmnt.setInt(1, facID);
			stmnt.setString(2, dep);

			rs = stmnt.executeQuery();

			while (rs.next()) {
				dupDep = rs.getInt(1);
			}
			
			if(dupDep > 0)
				logger.info("Found duplicate department for " + dep);
				else
				logger.info("No duplicate department found for " + dep);
			
		} catch (SQLException e) {
			logger.error("Unable to get existing department for "+ dep + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
		return dupDep;

	
	}
	
	public boolean persistDepartment( int facID, String dep ) {

		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		int persisted;
		try {
			logger.info("Persisting new department "+dep);
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.PERSIST_NEW_DEPARTMENT);
			stmnt.setInt(1, facID);
			stmnt.setString(2, dep);

			persisted = stmnt.executeUpdate();

			if(persisted > 0)
		    	logger.info("Inserted department successfully "+ dep);
		    else
		    	logger.info("Error while inserting department "+ dep);
		} catch (SQLException e) {
			logger.error("Unable to persist new department "+ dep + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return true;
	}
	
	
	public boolean updateDepartment( DepartmentDTO dep ) {

		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		int persisted = 0;
		try {
			logger.info("Update department " + dep);
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.UPDATE_DEPARTMENT);
			stmnt.setInt(1, dep.getFaculty().getFacultyid());
			stmnt.setString(2, dep.getDepartmentname());
			stmnt.setInt(3, dep.getDepartmentid());

			persisted = stmnt.executeUpdate();

			if(persisted > 0)
		    	logger.info("Updated department successfully " + dep);
		    else
		    	logger.info("Error while updating department " + dep);
		} catch (SQLException e) {
			logger.error("Unable to update department "+ dep + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		if(persisted > 0) return true;
		
		return false;
	}
	
	
	public List getFaculties(){
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		FacultyDTO fdto;
		InstituteDTO ins;
		List fac = new ArrayList();
		try {
			logger.info("Retrieving all faculties ");
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_FACULTIES);
			rs = stmnt.executeQuery();

			while(rs.next()){
				fdto = new FacultyDTO();
				ins = new InstituteDTO();
				ins.setInstituteid(rs.getInt(1));
				ins.setInstitutename(rs.getString(2));
				fdto.setFacultyid(rs.getInt(3));
				fdto.setFacultyname(rs.getString(4));
				fdto.setInstitute(ins);
				fac.add(fdto);
			}
			logger.info("Found faculties " + fac.size());
			
		} catch (SQLException e) {
			logger.error("Unable to get faculties " + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
		return fac;
	}
	
	public List getDepartments(){
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		DepartmentDTO depdto;
		FacultyDTO fac;
		InstituteDTO ins;
		List depfac = new ArrayList();
		try {
			
			logger.info("Retrieving all departments ");
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_DEPARTMENTS);
			rs = stmnt.executeQuery();
				
			while(rs.next()){
				ins = new InstituteDTO();
				fac = new FacultyDTO();
				ins.setInstituteid(rs.getInt(1));
				ins.setInstitutename(rs.getString(2));
				fac.setFacultyid(rs.getInt(3));
				fac.setFacultyname(rs.getString(4));
				fac.setInstitute(ins);
				depdto = new DepartmentDTO();
				depdto.setDepartmentid(rs.getInt(5));
				depdto.setDepartmentname(rs.getString(6));
				depdto.setFaculty(fac);
				depfac.add(depdto);
			}
			
			logger.info("Found departments " + depfac.size());
			
		} catch (SQLException e) {
			logger.error("Unable to get departments facultywise " + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
		return depfac;
	}

	public List getInstitutes() {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		InstituteDTO insdto;
		List ins = new ArrayList();
		try {
			logger.info("Retrieving all Institutes ");
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_INSTITUTES);
			rs = stmnt.executeQuery();

			while(rs.next()){
				insdto = new InstituteDTO();
				insdto.setInstituteid(rs.getInt(1));
				insdto.setInstitutename(rs.getString(2));
				ins.add(insdto);
			}
			logger.info("Found institutes " + ins.size());
			
		} catch (SQLException e) {
			logger.error("Unable to get institutes " + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
		return ins;
	}

	public int getExistingInstitute(String institutename) {

		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		int dupIns = 0;
		try {
			logger.info("Retrieving existing institutes for "+institutename);
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.GET_EXISTING_INSTITUTE);
			stmnt.setString(1, institutename);

			rs = stmnt.executeQuery();

			while (rs.next()) {
				dupIns = rs.getInt(1);
			}
			if(dupIns>0)
			logger.info("Found duplicate institute(s) for " + institutename);
			else
				logger.info("No duplicate faculty found for " + institutename);
		} catch (SQLException e) {
			logger.error("Error while getting existing institute " + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
		return dupIns;

	}

	public boolean persistInstitute(String institutename) {

		java.sql.Connection conn = getConnection();
		int n;
		try {
			logger.info("persisting new institute "+institutename);
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.PERSIST_NEW_INSTITUTE);
		    stmnt.setString(1, institutename);
		    n = stmnt.executeUpdate();
		    
		    if(n > 0)
		    	logger.info("Inserted institute successfully "+ institutename);
		    else
		    	logger.info("Error while inserting institute "+ institutename);
		} catch (SQLException e) {
			logger.error("Error while persisting new institute "+ institutename + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		return false;
	}
	
	public boolean updateInstitute(InstituteDTO institute) {

		java.sql.Connection conn = getConnection();
		int n = 0;
		try {
			logger.info("Update institute "+institute.getInstituteid());
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.UPDATE_INSTITUTE);
			stmnt.setString(1, institute.getInstitutename());
		    stmnt.setInt(2, institute.getInstituteid());
		    n = stmnt.executeUpdate();
		    
		    if(n > 0)
		    	logger.info("Updated institute successfully "+ institute.getInstituteid());
		    else
		    	logger.info("Error while updating institute "+ institute.getInstituteid());
		} catch (SQLException e) {
			logger.error("Error while updating institute "+ institute.getInstituteid() + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}

		if(n > 0) return true;
		
		return false;
	}

	public Map getDistrictsStateswise() {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		String state = new String();
		int distId;
		ArrayList distList;
		DistrictDTO tempDist;
		DistrictDTO distdto;
		String distName = new String();
		Map distMap = new HashMap();
		try {
			
			logger.info("Retrieving all States District wise ");
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_DISTRICTSSTATESWISE);
			rs = stmnt.executeQuery();
				
			while(rs.next()){
				state = rs.getString(1);
				distId = rs.getInt(2);
				distName = rs.getString(3);
				if(state!=null){
				if(distMap.containsKey(state)){
					if(distId!=-1 && ( distName!=null && distName != "")){
					tempDist = new DistrictDTO();
					tempDist.setDistrictid(distId);
					tempDist.setDistrictname(distName);
					distList = (ArrayList) distMap.get(state);
					distList.add(tempDist);
					distMap.remove(state);
					distMap.put(state, distList);
					}
				}else{
					distList = new ArrayList();
					tempDist = new DistrictDTO();
					tempDist.setDistrictid(distId);
					tempDist.setDistrictname(distName);
					distList.add(tempDist);
					 distMap.put(state, distList);
				}
				}
			}
			
			logger.info("State wise district map created successfully");
			
		} catch (SQLException e) {
			logger.error("Unable to create state wise district map" + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
	
		return distMap;
	}
	
	
	
	public Map getFacultyInstitutewise() {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		String ins = new String();
		int facId;
		ArrayList facList;
		FacultyDTO tempFac;
		String facName = new String();
		Map facMap = new HashMap();
		try {
			
			logger.info("Retrieving all faculties institute wise ");
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_FACULTY_INSTITUTEWISE);
			rs = stmnt.executeQuery();
				
			while(rs.next()){
				ins = rs.getString(1);
				facId = rs.getInt(2);
				facName = rs.getString(3);
				facName = facName == null ? "" : facName; 
				if(ins!=null){
				if(facMap.containsKey(ins)){
					tempFac = new FacultyDTO();
					tempFac.setFacultyid(facId);
					tempFac.setFacultyname(facName);
					facList = (ArrayList) facMap.get(ins);
					facList.add(tempFac);
					facMap.remove(ins);
					facMap.put(ins, facList);
				}else{
					facList = new ArrayList();
					tempFac = new FacultyDTO();
					tempFac.setFacultyid(facId);
					tempFac.setFacultyname(facName);
					 facList.add(tempFac);
					 facMap.put(ins, facList);
				}
				}
			}
			
			logger.info("Institute wise faculty map created successfully");
			
		} catch (SQLException e) {
			logger.error("Unable to create Institute wise faculty map" + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
	
		return facMap;
	}
	
	
	public Map getFacultiesByInstituteId(int insId) {
		java.sql.Connection conn = getConnection();
		ResultSet rs = null;
		String ins = new String();
		int facId;
		ArrayList facList;
		FacultyDTO tempFac;
		String facName = new String();
		Map facMap = new HashMap();
		try {
			
			logger.info("Retrieving all faculties by institute Id " + insId);
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_FACULTY_INSTITUTEIDWISE);
			stmnt.setInt(1,insId);
			rs = stmnt.executeQuery();
				
			while(rs.next()){
				ins = rs.getString(1);
				facId = rs.getInt(2);
				facName = rs.getString(3);
				facName = facName == null ? "" : facName; 
				if(ins!=null){
				if(facMap.containsKey(ins)){
					tempFac = new FacultyDTO();
					tempFac.setFacultyid(facId);
					tempFac.setFacultyname(facName);
					facList = (ArrayList) facMap.get(ins);
					facList.add(tempFac);
					facMap.remove(ins);
					facMap.put(ins, facList);
				}else{
					facList = new ArrayList();
					tempFac = new FacultyDTO();
					tempFac.setFacultyid(facId);
					tempFac.setFacultyname(facName);
					 facList.add(tempFac);
					 facMap.put(ins, facList);
				}
				}
			}
			
			logger.info("Institute ID wise faculty map created successfully");
			
		} catch (SQLException e) {
			logger.error("Unable to create Institute ID wise faculty map" + e.getMessage());
		}
		
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		
	
		return facMap;
	}
	
	
public Map fetchDepartmentByFacultyId(String[] id) {
		
		java.sql.Connection conn = getConnection();
		ResultSet rs;
		Map supList = new HashMap();
		try {
			
			logger.info("Fetching departments by faculty ID : "+ id[0]);
			
			PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_DEPARTMENTS_BY_FACULTYID);
			stmnt.setString(1,id[0]);
			rs = stmnt.executeQuery();
			
			while(rs.next()){
				supList.put(rs.getString(1), rs.getString(2));
			}
			logger.info("Retrieved "+ supList.size()+" departments by faculty ID = " + id[0] );
			
		} catch (SQLException e) {
			logger.info("Error while getting departments by faculty ID " + e.getMessage());
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getMessage());
		}
		return (Map)supList;
}

public Map fetchFacultyByInstituteId(String[] id) {
	
	java.sql.Connection conn = getConnection();
	ResultSet rs;
	Map supList = new HashMap();
	try {
		
		logger.info("Fetching faculty by institute ID : "+ id[0]);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_FACULTY_BY_INSTITUTEID);
		stmnt.setString(1,id[0]);
		rs = stmnt.executeQuery();
		
		while(rs.next()){
			supList.put(rs.getString(1), rs.getString(2));
		}
		logger.info("Retrieved "+ supList.size()+" faculty by institute ID = " + id[0] );
		
	} catch (SQLException e) {
		logger.info("Error while getting faculty by institute ID " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	return (Map)supList;
}


public Properties fetchStateIdProperties() {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	Properties prop = new Properties();
	try {
		logger.info("Retrieving state Ids ");
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_STATES);
		rs = stmnt.executeQuery();

		while(rs.next()){
			prop.put(rs.getString(2), rs.getInt(1));
		}
		logger.info("Found states " + prop.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get states property object " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return prop;
}

public Properties fetchInstituteIdProperties() {
	java.sql.Connection conn = getConnection();
	List insList = new ArrayList();
	insList = getInstitutes();
	InstituteDTO institute;
	ResultSet rs = null;
	Properties prop = new Properties();
	try {
		logger.info("Retrieving institutes Ids ");
		for(Object ins : insList){
			institute = (InstituteDTO)ins;
			prop.put(institute.getInstitutename(), institute.getInstituteid());
		}

		logger.info("Found institutes " + prop.size());
		
	} catch (Exception e) {
		logger.error("Unable to get institutes property object " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return prop;
}

public List getFaculties(int instituteid) {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	FacultyDTO fdto;
	InstituteDTO ins;
	List fac = new ArrayList();
	try {
		logger.info("Retrieving faculties for institute ID "+ instituteid);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_FACULTIES_ADMIN);
		stmnt.setInt(1, instituteid);
		rs = stmnt.executeQuery();

		while(rs.next()){
			fdto = new FacultyDTO();
			ins = new InstituteDTO();
			ins.setInstituteid(rs.getInt(1));
			ins.setInstitutename(rs.getString(2));
			fdto.setFacultyid(rs.getInt(3));
			fdto.setFacultyname(rs.getString(4));
			fdto.setInstitute(ins);
			fac.add(fdto);
		}
		logger.info("Found faculties " + fac.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get faculties for institute ID " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return fac;
}

public List getDepartmentsInstitutewise(int instituteid) {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	DepartmentDTO depdto;
	FacultyDTO fac;
	InstituteDTO ins;
	List depfac = new ArrayList();
	try {
		
		logger.info("Retrieving departments for institute ID "+ instituteid);
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_DEPARTMENTS_INSTITUTEWISE);
		stmnt.setInt(1, instituteid);
		rs = stmnt.executeQuery();
			
		while(rs.next()){
			ins = new InstituteDTO();
			fac = new FacultyDTO();
			ins.setInstituteid(rs.getInt(1));
			ins.setInstitutename(rs.getString(2));
			fac.setFacultyid(rs.getInt(3));
			fac.setFacultyname(rs.getString(4));
			fac.setInstitute(ins);
			depdto = new DepartmentDTO();
			depdto.setDepartmentid(rs.getInt(5));
			depdto.setDepartmentname(rs.getString(6));
			depdto.setFaculty(fac);
			depfac.add(depdto);
		}
		
		logger.info("Found departments " + depfac.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get departments facultywise " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return depfac;
}

public List getDepartmentsFacultywise(int facultyid) {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	DepartmentDTO depdto;
	FacultyDTO fac;
	InstituteDTO ins;
	List depfac = new ArrayList();
	try {
		
		logger.info("Retrieving departments for faculty ID "+ facultyid);
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_DEPARTMENTS_FACULTYWISE);
		stmnt.setInt(1, facultyid);
		rs = stmnt.executeQuery();
			
		while(rs.next()){
			ins = new InstituteDTO();
			fac = new FacultyDTO();
			ins.setInstituteid(rs.getInt(1));
			ins.setInstitutename(rs.getString(2));
			fac.setFacultyid(rs.getInt(3));
			fac.setFacultyname(rs.getString(4));
			fac.setInstitute(ins);
			depdto = new DepartmentDTO();
			depdto.setDepartmentid(rs.getInt(5));
			depdto.setDepartmentname(rs.getString(6));
			depdto.setFaculty(fac);
			depfac.add(depdto);
		}
		
		logger.info("Found departments " + depfac.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get departments facultywise " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return depfac;
}

public InstituteDTO getInstituteById(int insID) {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	InstituteDTO insdto;
	List ins = new ArrayList();
	try {
		logger.info("Retrieving all Institutes ");
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_INSTITUTE_BYID);
		stmnt.setInt(1,insID);
		rs = stmnt.executeQuery();

		while(rs.next()){
			insdto = new InstituteDTO();
			insdto.setInstituteid(rs.getInt(1));
			insdto.setInstitutename(rs.getString(2));
			ins.add(insdto);
		}
		logger.info("Found institutes " + ins.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get institutes " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return (InstituteDTO) ins.get(0);
}

public String deleteInstituteById(String[] strIDs) {
	boolean allDeleted = false;
	java.sql.Connection conn = getConnection();
	String idStr = CommonUtilities.getIntegerStringFromArray(strIDs);
	StringBuffer query = new StringBuffer(SQLQueries.DELETE_INSTITUTE_BY_ID);
	try {
		logger.info("Deleting institute(s) for IDs "+idStr);
		Statement stmnt = conn.createStatement();
		query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
		
		logger.info("Query for deletion : "+query.toString());
		if(stmnt.executeUpdate(query.toString()) == strIDs.length){
			logger.info("Deleted all institute(s) for "+idStr);
			allDeleted = true;
		}
		else
			logger.info("There was some error while institute(s) deletion "+idStr);
	} catch (SQLException e) {
		logger.error("Error while deleting institute(s) " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}

	return String.valueOf(allDeleted);
}

public String deleteFacultyById(String[] strIDs) {
	boolean allDeleted = false;
	java.sql.Connection conn = getConnection();
	String idStr = CommonUtilities.getIntegerStringFromArray(strIDs);
	StringBuffer query = new StringBuffer(SQLQueries.DELETE_FACULTY_BY_ID);
	try {
		logger.info("Deleting faculty(s) for IDs "+idStr);
		Statement stmnt = conn.createStatement();
		query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
		
		logger.info("Query for deletion : "+query.toString());
		if(stmnt.executeUpdate(query.toString()) == strIDs.length){
			logger.info("Deleted all faculty(s) for "+idStr);
			allDeleted = true;
		}
		else
			logger.info("There was some error while faculty(s) deletion "+idStr);
	} catch (SQLException e) {
		logger.error("Error while deleting faculty(s) " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}

	return String.valueOf(allDeleted);
}

public String deleteDepartmentById(String[] strIDs) {
	boolean allDeleted = false;
	java.sql.Connection conn = getConnection();
	String idStr = CommonUtilities.getIntegerStringFromArray(strIDs);
	StringBuffer query = new StringBuffer(SQLQueries.DELETE_DEPARTMENT_BY_ID);
	try {
		logger.info("Deleting department(s) for IDs "+idStr);
		Statement stmnt = conn.createStatement();
		query.replace(query.indexOf("?"), query.indexOf("?")+1, idStr);
		
		logger.info("Query for deletion : "+query.toString());
		if(stmnt.executeUpdate(query.toString()) == strIDs.length){
			logger.info("Deleted all department for "+idStr);
			allDeleted = true;
		}
		else
			logger.info("There was some error while department(s) deletion "+idStr);
	} catch (SQLException e) {
		logger.error("Error while deleting department(s) " + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}

	return String.valueOf(allDeleted);
}

public int checkExistingColloquium(ColloquiumDTO coll){

	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	int dupFac = 0;
	try {
		logger.info("Retrieving existing colloquium for "+coll.getDatetime());
		PreparedStatement stmnt = conn
				.prepareStatement(SQLQueries.CHECK_EXISTING_COLLOQUIUM);
		rs = stmnt.executeQuery();
		stmnt.setTimestamp(1, CommonUtilities.makeTimestamp(coll.getDatetime()));
		stmnt.setString(2, coll.getCreatedby());

		while (rs.next()) {
			dupFac = rs.getInt(1);
		}
		if(dupFac>0)
		logger.info("Found overlapping collquium for " + coll.getDatetime());
		else
			logger.info("No overlapping collquium found for " + coll.getDatetime());
	} catch (SQLException e) {
		logger.error("Error while finding overlapping collquium " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return dupFac;

}

public boolean persistColloquium(ColloquiumDTO coll){

	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	int persisted;
	try {
		logger.info("Persisting new colloquium "+coll.getDatetime());
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.PERSIST_NEW_COLLOQUIUM);
		stmnt.setString(1, coll.getCollID());
		stmnt.setString(2, coll.getObserverName());
		stmnt.setString(3, StringUtils.join(coll.getScholarID(), ","));
		stmnt.setTimestamp(4, CommonUtilities.makeTimestamp(coll.getDatetime()));
		stmnt.setString(5,coll.getCreatedby());
		stmnt.setString(6, coll.getStatus());
		stmnt.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
		stmnt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));

		persisted = stmnt.executeUpdate();

		if(persisted > 0)
			logger.info("Inserted colloquium successfully "+ coll.getDatetime());
	    else
	    	logger.info("Error while inserting colloquium "+ coll.getDatetime());
	} catch (SQLException e) {
		logger.error("Unable to persist new colloquium "+ coll.getDatetime() + e.getMessage());
	}
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}

	return true;
}

public List getColloquiumForAdmin(String adminId){
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	ColloquiumDTO colldto;
	List ins = new ArrayList();
	try {
		logger.info("Retrieving all colloquiums by admin "+adminId);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_COLLOQUIUM_BYDEANID);
		stmnt.setString(1,adminId);
		rs = stmnt.executeQuery();

		while(rs.next()){
			colldto = new ColloquiumDTO();
			colldto.setCollID(rs.getString(1));
			colldto.setObserverName(rs.getString(2));
			colldto.setDatetime(rs.getTimestamp(3).toString());
			colldto.setStatus(rs.getString(4));
			colldto.setDateCreated(rs.getDate(5).toString());
			colldto.setDateModified(rs.getDate(6).toString());
			ins.add(colldto);
		}
		logger.info("Found colloquiums " + ins.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get colloquiums " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return ins;
} 

public ColloquiumDTO getColloquiumById(String collId){
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	ColloquiumDTO colldto = null;
	List ins = new ArrayList();
	try {
		logger.info("Retrieving all colloquiums by admin "+collId);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_COLLOQUIUM_BYID);
		stmnt.setString(1,collId);
		rs = stmnt.executeQuery();

		while(rs.next()){
			colldto = new ColloquiumDTO();
			colldto.setScholarID(CommonUtilities.getStringArrFromString(rs.getString(1),","));
			colldto.setObserverName(rs.getString(2));
			colldto.setDatetime(rs.getTimestamp(3).toString());
			colldto.setStatus(rs.getString(4));
			colldto.setDateCreated(rs.getDate(5).toString());
			colldto.setDateModified(rs.getDate(6).toString());
			ins.add(colldto);
		}
		logger.info("Found colloquiums " + ins.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get colloquiums " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return colldto;
}

public FacultyDTO getFacultyById(int facultyid) {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	FacultyDTO fdto;
	InstituteDTO ins;
	List fac = new ArrayList();
	try {
		logger.info("Retrieving faculty  for faculty ID "+ facultyid);
		
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_FACULTY_BYID);
		stmnt.setInt(1, facultyid);
		rs = stmnt.executeQuery();

		while(rs.next()){
			fdto = new FacultyDTO();
			ins = new InstituteDTO();
			ins.setInstituteid(rs.getInt(1));
			ins.setInstitutename(rs.getString(2));
			fdto.setFacultyid(rs.getInt(3));
			fdto.setFacultyname(rs.getString(4));
			fdto.setInstitute(ins);
			fac.add(fdto);
		}
		logger.info("Found faculties " + fac.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get faculty for faculty ID " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return (FacultyDTO) fac.get(0);
}

public DepartmentDTO getDepartmentById(int departmentId) {
	java.sql.Connection conn = getConnection();
	ResultSet rs = null;
	DepartmentDTO depdto;
	FacultyDTO fac;
	InstituteDTO ins;
	List depfac = new ArrayList();
	try {
		
		logger.info("Retrieving department for ID "+departmentId);
		PreparedStatement stmnt = conn.prepareStatement(SQLQueries.GET_DEPARTMENT_BY_ID);
		stmnt.setInt(1, departmentId);
		rs = stmnt.executeQuery();
			
		while(rs.next()){
			ins = new InstituteDTO();
			fac = new FacultyDTO();
			ins.setInstituteid(rs.getInt(1));
			ins.setInstitutename(rs.getString(2));
			fac.setFacultyid(rs.getInt(3));
			fac.setFacultyname(rs.getString(4));
			fac.setInstitute(ins);
			depdto = new DepartmentDTO();
			depdto.setDepartmentid(rs.getInt(5));
			depdto.setDepartmentname(rs.getString(6));
			depdto.setFaculty(fac);
			depfac.add(depdto);
		}
		
		logger.info("Found department " + depfac.size());
		
	} catch (SQLException e) {
		logger.error("Unable to get department by ID " + e.getMessage());
	}
	
	try {
		conn.close();
		logger.info("Connection closed successfully ");
	} catch (SQLException e) {
		logger.error("Unable to close Connection Object " + e.getMessage());
	}
	
	return (DepartmentDTO) depfac.get(0);
} 

}
