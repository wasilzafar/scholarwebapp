package in.scholarreport.struts2.util;

public interface SQLQueries {
	public static final String GET_EXISTING_SCHOLAR = " select count(*) from scholar where scholarFirstName = ? and scholarMiddleName = ? "
			+ "and scholarLastName = ? and dob = ? or emailID = ? or enrollmentNumber = ? ";
	public static final String PERSIST_NEW_SCHOLAR = " insert into scholar (scholarID, scholarFirstName, scholarMiddleName, scholarLastName, scholarScreenName,"
			+ " scholarFatherFirstName, scholarFatherLastName, scholarSpouseFirstName, scholarSpouseLastName, "
			+ " dob, gender, nationality, mobileNumber, landlineNumber, emailID, correspondenceAddress, cState, cDistrict, cZipCode, permanentAddress, pState, pDistrict, pZipCode, "
			+ " category , categorycode, coursename, institute, faculty, department, enrollmentNumber, dateOfRegistration, topic, languagesKnown, status,"
			+ " supervisor, coSupervisor, totalExperience, examsPassed, institutionsName, yearsOfPassing, percentages, subjects, "
			+ " un , un_rno, un_xam_yr, un_ref_no, un_ecert_no, sl, sl_rno, sl_xam_yr, sl_ref_no, sl_ecert_no, cun, cun_rno, cun_xam_yr, cun_ref_no, cun_ecert_no, "
			+ " employersname, positionsHeld, employedfrom, employedto, dateCreated, dateModified,topicModified,dateOfTopicModification ) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, "
			+ " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";

	public static final String RETRIEVE_SCHOLAR_BY_SCHOLAR_EMAILID = " select scholarID, scholarFirstName, scholarMiddleName, scholarLastName, scholarScreenName, dateOfRegistration,topic,departmentname, facultyname,institutename, supervisorID,supervisorFirstName, "
			+ " supervisorMiddleName,supervisorLastName, supervisorScreenName,role from scholar INNER JOIN supervisor "
			+ " ON ( scholar.supervisor = supervisor.supervisorID ) INNER JOIN institute ON (scholar.institute = institute.instituteid) "
			+ " INNER JOIN faculty ON ( faculty.facultyid = scholar.faculty) INNER JOIN department ON ( department.departmentid = scholar.department ) "
			+ " where scholar.emailID = ? ";
	public static final String RETRIEVE_SUPERVISOR_BY_SUPERVISOR_ID = "  select supervisorID, supervisorFirstName, supervisorMiddleName, "
			+ "  supervisorLastName, supervisorScreenName, departmentname, facultyname, institutename, issuperadmin, isadmin, isdean, ishead, role, department, faculty, institute from supervisor "
			+ " LEFT OUTER JOIN institute ON (supervisor.institute = institute.instituteid)  LEFT OUTER JOIN faculty ON ( faculty.facultyid = supervisor.faculty ) "
			+ " LEFT OUTER JOIN department ON ( department.departmentid = supervisor.department ) where emailID = ? ";
	public static final String RETRIEVE_SUPERVISOR_BY_SUPERVISORID = "  select supervisorID, supervisorFirstName, supervisorMiddleName, "
			+ "  supervisorLastName, supervisorScreenName, departmentname, facultyname, institutename, issuperadmin, isadmin, isdean, ishead, role, department, faculty, institute,supervisorFatherFirstName,supervisorFatherLastName,supervisorSpouseFirstName,supervisorSpouseLastName,dob,mobileNumber,landlineNumber,role,emailID,correspondenceAddress,cState,cDistrict,cZipCode,permanentAddress,pState,pDistrict,pZipCode,status,dateCreated,dateModified from supervisor "
			+ " LEFT OUTER JOIN institute ON (supervisor.institute = institute.instituteid)  LEFT OUTER JOIN faculty ON ( faculty.facultyid = supervisor.faculty ) "
			+ " LEFT OUTER JOIN department ON ( department.departmentid = supervisor.department ) where supervisorID = ? ";
	public static final String RETRIEVE_SCHOLAR_BY_SUPERVISOR_ID = "  select * from scholarinfo where supervisor = ?  ";
	public static final String DELETE_SCHOLARS_BY_ID = "delete from scholar where scholarID IN ( ? )";
	public static final String DELETE_SCHOLARLOGIN_BY_ID = "delete from scholarlogin where scholarID IN ( ? )";
	public static final String GET_EXISTING_SUPERVISOR = "select count(*) from supervisor where supervisorFirstName = ? and supervisorMiddleName = ? and supervisorLastName = ? "
			+ "and dob = ? or emailID = ? ";
	public static final String PERSIST_NEW_SUPERVISOR = "insert into supervisor (supervisorID, supervisorFirstName, supervisorMiddleName, supervisorLastName, "
			+ "supervisorScreenName,supervisorFatherFirstName, supervisorFatherLastName, supervisorSpouseFirstName, supervisorSpouseLastName, "
			+ "dob, mobileNumber, landlineNumber, issuperadmin, isadmin, isdean, ishead, role, reportto,emailID, correspondenceAddress, cState, cDistrict,cZipCode, "
			+ "permanentAddress, pState, pDistrict,pZipCode,status, institute, faculty,department, designation,dateCreated, dateModified, salutation ) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static final String DELETE_SUPERVISORS_BY_ID = "delete from supervisor where supervisorID IN ( ? )";
	public static final String DELETE_SUPERVISORLOGIN_BY_ID = "delete from supervisorlogin where supervisorID IN ( ? )";
	public static final String GET_EXISTING_INSTITUTE = "select * from institute where institutename = ? ";
	public static final String GET_EXISTING_FACULTY = "select * from faculty where facultyname = ? and instituteid = ?";
	public static final String PERSIST_NEW_INSTITUTE = " insert into institute ( institutename ) values ( ? ) ";
	public static final String PERSIST_NEW_FACULTY = "insert into faculty ( facultyname,instituteid ) values ( ?,? ) ";
	public static final String GET_EXISTING_DEPARTMENT = " select * from department where facultyid = ? and departmentname = ? ";
	public static final String PERSIST_NEW_DEPARTMENT = " insert into department ( facultyid,departmentname ) values ( ? , ? )";
	public static final String GET_FACULTIES = "  select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname "
			+ " from institute right outer join faculty on faculty.instituteid = institute.instituteid order by faculty.facultyid;  ";
	public static final String GET_FACULTIES_ADMIN = "  select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname "
			+ " from institute right outer join faculty on faculty.instituteid = institute.instituteid where institute.instituteid = ? order by faculty.facultyid  ";
	public static final String GET_INSTITUTES = " select * from institute ";
	public static final String GET_INSTITUTE_BYID = " select * from institute where instituteid = ? ";
	public static final String GET_DEPARTMENTS = "  select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname, department.departmentid, department.departmentname "
			+ " from faculty right outer join department on department.facultyid = faculty.facultyid inner join institute on faculty.instituteid = institute.instituteid "
			+ " order by department.departmentid ";
	public static final String GET_DEPARTMENTS_INSTITUTEWISE = " select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname, department.departmentid, department.departmentname "
			+ " from faculty right outer join department on department.facultyid = faculty.facultyid inner join institute on faculty.instituteid = institute.instituteid "
			+ " where institute.instituteid = ? order by faculty.facultyid  ";
	public static final String GET_DEPARTMENTS_FACULTYWISE = " select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname, department.departmentid, department.departmentname "
			+ " from faculty right outer join department on department.facultyid = faculty.facultyid inner join institute on faculty.instituteid = institute.instituteid "
			+ " where faculty.facultyid = ? order by  department.departmentid ";
	public static final String GET_SUPERVISORS = " select p.supervisorID,p.supervisorFirstName,p.supervisorMiddleName,p.supervisorLastName,p.supervisorScreenName,departmentname,facultyname,institutename,p.status, "
			+ " p.role "
			+ "  from supervisor AS p INNER JOIN institute ON (p.institute = institute.instituteid ) LEFT OUTER JOIN faculty ON "
			+ " (p.faculty = faculty.facultyid) LEFT OUTER JOIN department ON (p.department = department.departmentid)";
	public static final String GET_SUPERVISORS_INSTITUTEWISE = " select p.supervisorID,p.supervisorFirstName,p.supervisorMiddleName,p.supervisorLastName,p.supervisorScreenName,departmentname,facultyname,institutename,p.status, "
			+ " p.role "
			+ "  from supervisor AS p INNER JOIN institute ON (p.institute = institute.instituteid ) LEFT OUTER JOIN faculty ON "
			+ " (p.faculty = faculty.facultyid) LEFT OUTER JOIN department ON (p.department = department.departmentid)  where p.issuperadmin='N' and p.isadmin='N' and institute.instituteid = ? ";
	public static final String GET_SUPERVISORS_FACULTYWISE = " select p.supervisorID,p.supervisorFirstName,p.supervisorMiddleName,p.supervisorLastName,p.supervisorScreenName,departmentname,facultyname,institutename,p.status, "
			+ " p.role "
			+ "  from supervisor AS p INNER JOIN institute ON (p.institute = institute.instituteid ) LEFT OUTER JOIN faculty ON "
			+ " (p.faculty = faculty.facultyid) LEFT OUTER JOIN department ON (p.department = department.departmentid) where p.issuperadmin='N' and p.isadmin='N' and p.isdean='N' and faculty.facultyid = ? ";
	public static final String GET_SUPERVISORS_DEPARTMENTWISE = " select p.supervisorID,p.supervisorFirstName,p.supervisorMiddleName,p.supervisorLastName,p.supervisorScreenName,departmentname,facultyname,institutename,p.status, "
			+ " p.role from supervisor AS p INNER JOIN institute ON (p.institute = institute.instituteid ) LEFT OUTER JOIN faculty ON "
			+ " (p.faculty = faculty.facultyid) LEFT OUTER JOIN department ON (p.department = department.departmentid) where p.issuperadmin='N' and p.isadmin='N' and p.isdean='N' and p.ishead='N' and department.departmentid = ? ";

	public static final String GET_SCHOLARS = " select scholarID, scholarFirstName, scholarMiddleName, scholarLastName,scholarScreenName,supervisorID,supervisorFirstName, "
			+ " supervisorMiddleName,supervisorLastName,institutename,facultyname,departmentname,scholar.status as status from scholar INNER JOIN supervisor ON "
			+ " ( scholar.supervisor = supervisor.supervisorID ) INNER JOIN institute ON ( scholar.institute = institute.instituteid) "
			+ " INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid) INNER JOIN department ON (scholar.department = department.departmentid ) ";

	public static final String GET_SCHOLARS_INSTITUTEWISE = " select scholarID, scholarFirstName, scholarMiddleName, scholarLastName,scholarScreenName,supervisorID,supervisorFirstName, "
			+ " supervisorMiddleName,supervisorLastName,institutename,facultyname,departmentname,scholar.status as status from scholar INNER JOIN supervisor ON "
			+ " ( scholar.supervisor = supervisor.supervisorID ) INNER JOIN institute ON ( scholar.institute = institute.instituteid) "
			+ " INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid) INNER JOIN department ON (scholar.department = department.departmentid ) where institute.instituteid = ? ";

	public static final String GET_SCHOLARS_FACULTYWISE = " select scholarID, scholarFirstName, scholarMiddleName, scholarLastName,scholarScreenName,supervisorID,supervisorFirstName, "
			+ " supervisorMiddleName,supervisorLastName,institutename,facultyname,departmentname,scholar.status as status from scholar INNER JOIN supervisor ON "
			+ " ( scholar.supervisor = supervisor.supervisorID ) INNER JOIN institute ON ( scholar.institute = institute.instituteid) "
			+ " INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid) INNER JOIN department ON (scholar.department = department.departmentid ) where faculty.facultyid = ? ";

	public static final String GET_SCHOLARS_DEPARTMENTWISE = " select scholarID, scholarFirstName, scholarMiddleName, scholarLastName,scholarScreenName,supervisorID,supervisorFirstName, "
			+ " supervisorMiddleName,supervisorLastName,institutename,facultyname,departmentname,scholar.status as status from scholar INNER JOIN supervisor ON "
			+ " ( scholar.supervisor = supervisor.supervisorID ) INNER JOIN institute ON ( scholar.institute = institute.instituteid) "
			+ " INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid) INNER JOIN department ON (scholar.department = department.departmentid ) where department.departmentid = ? ";
	public static final String GET_SCHOLARS_BY_SUPERVISORID = "select scholarID, scholarFirstName, scholarMiddleName, scholarLastName,scholarScreenName,supervisorID,supervisorFirstName, "
			+ " supervisorMiddleName,supervisorLastName,institutename,facultyname,departmentname,scholar.status as status from scholar INNER JOIN supervisor ON "
			+ " ( scholar.supervisor = supervisor.supervisorID ) INNER JOIN institute ON ( scholar.institute = institute.instituteid) "
			+ " INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid) INNER JOIN department ON (scholar.department = department.departmentid ) where scholar.supervisor =  ? ";
	public static final String AUTHENTICATE_SCHOLAR = " select count(*) from scholarlogin where scholarEmailID = ? and credential = ? ";
	public static final String AUTHENTICATE_SUPERVISOR = "  select count(*) from supervisorlogin where supervisorEmailID = ? and credential = ?  ";
	public static final String SET_SCHOLAR = "insert into scholarlogin ( scholarID,scholarEmailID,credential ) values (?, ? , ? )";
	public static final String SET_SUPERVISOR = "insert into supervisorlogin ( supervisorID,supervisorEmailID,credential ) values (?, ? , ? )";
	public static final String PERSIST_NEW_MONTHLYREPORT = " insert into monthlyreport ( mreportid,fromdate,todate,workCompleted,leaves,attachment,status,scholarid,dateCreated,dateModified ) "
			+ "values (?,?,?,?,?,?,?,?,?,?) ";
	public static final String PERSIST_NEW_QUARTERLYREPORT = " insert into quarterlyreport ( qreportid,fromdate,todate,workCompleted,attachment,status,scholarid,dateCreated,dateModified )"
			+ "values (?,?,?,?,?,?,?,?,?) ";
	public static final String RETRIEVE_MONTHLYREPORT_BY_INSTITUTE_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,monthlyreport.mreportid, "
			+ " monthlyreport.fromdate,monthlyreport.todate,monthlyreport.status from scholar INNER JOIN monthlyreport ON "
			+ " ( scholar.scholarID = monthlyreport.scholarid ) INNER JOIN institute ON ( scholar.institute = institute.instituteid )where scholar.institute = ?  ";
	public static final String RETRIEVE_MONTHLYREPORT_BY_FACULTY_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,monthlyreport.mreportid, "
			+ " monthlyreport.fromdate,monthlyreport.todate,monthlyreport.status from scholar INNER JOIN monthlyreport ON "
			+ " ( scholar.scholarID = monthlyreport.scholarid ) INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid )where scholar.faculty = ?  ";
	public static final String RETRIEVE_MONTHLYREPORT_BY_DEPARTMENT_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,monthlyreport.mreportid, "
			+ " monthlyreport.fromdate,monthlyreport.todate,monthlyreport.status from scholar INNER JOIN monthlyreport ON "
			+ " ( scholar.scholarID = monthlyreport.scholarid ) INNER JOIN department ON ( scholar.department = department.departmentid )where scholar.department = ?  ";
	public static final String RETRIEVE_MONTHLYREPORT_BY_SUPERVISOR_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,monthlyreport.mreportid, "
			+ " monthlyreport.fromdate,monthlyreport.todate,monthlyreport.status from scholar INNER JOIN monthlyreport ON "
			+ " ( scholar.scholarID = monthlyreport.scholarid ) where scholar.supervisor = ?  ";
	public static final String RETRIEVE_QUARTERLYREPORT_BY_INSTITUTE_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,quarterlyreport.qreportid, "
			+ " quarterlyreport.fromdate,quarterlyreport.todate,quarterlyreport.status from scholar INNER JOIN quarterlyreport ON "
			+ " ( scholar.scholarID = quarterlyreport.scholarid )  INNER JOIN institute ON ( scholar.institute = institute.instituteid )where scholar.institute = ?  ";

	public static final String RETRIEVE_QUARTERLYREPORT_BY_FACULTY_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,quarterlyreport.qreportid, "
			+ " quarterlyreport.fromdate,quarterlyreport.todate,quarterlyreport.status from scholar INNER JOIN quarterlyreport ON "
			+ " ( scholar.scholarID = quarterlyreport.scholarid ) INNER JOIN faculty ON ( scholar.faculty = faculty.facultyid )where scholar.faculty = ?  ";

	public static final String RETRIEVE_QUARTERLYREPORT_BY_DEPARTMENT_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,quarterlyreport.qreportid, "
			+ " quarterlyreport.fromdate,quarterlyreport.todate,quarterlyreport.status from scholar INNER JOIN quarterlyreport ON "
			+ " ( scholar.scholarID = quarterlyreport.scholarid ) INNER JOIN department ON ( scholar.department = department.departmentid )where scholar.department = ?  ";

	public static final String RETRIEVE_QUARTERLYREPORT_BY_SUPERVISOR_ID = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,quarterlyreport.qreportid, "
			+ " quarterlyreport.fromdate,quarterlyreport.todate,quarterlyreport.status from scholar INNER JOIN quarterlyreport ON "
			+ " ( scholar.scholarID = quarterlyreport.scholarid ) where scholar.supervisor = ? ";
	public static final String UPDATE_STATUS_SUPERVISORS_BY_ID = "update supervisor set status = ? where supervisorID in ( ? )";
	public static final String UPDATE_STATUS_SCHOLARS_BY_ID = "update scholar set status = ? where scholarID in ( ? )";
	public static final String UPDATE_STATUS_MONTHLYREPORTS_BY_ID = "update monthlyreport set status = ? where mreportid in ( ? )";
	public static final String UPDATE_STATUS_QUARTERLYREPORTS_BY_ID = "update quarterlyreport set status = ? where qreportid in ( ? )";
	public static final String DELETE_MONTHLYREPORTS_BY_ID = "delete from monthlyreport where mreportid IN ( ? )";
	public static final String DELETE_INSTITUTE_BY_ID = "delete from institute where instituteid IN ( ? )";
	public static final String DELETE_FACULTY_BY_ID = "delete from faculty where facultyid IN ( ? )";
	public static final String DELETE_DEPARTMENT_BY_ID = "delete from department where departmentid IN ( ? )";
	public static final String DELETE_QUARTERLYREPORTS_BY_ID = "delete from quarterlyreport where qreportid IN ( ? )";
	public static final String RETRIEVE_MONTHLYREPORT_BY_SCHOLAR_ID = " select mreportid, fromdate,todate,status from monthlyreport where monthlyreport.scholarid = ? ";
	public static final String RETRIEVE_QUARTERLYREPORT_BY_SCHOLAR_ID = " select qreportid, fromdate,todate,status from quarterlyreport where quarterlyreport.scholarid = ? ";
	public static final String RETRIEVE_MONTHLYREPORT_BY_ID = " select scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName, "
			+ " department.departmentname,supervisor.supervisorFirstName,supervisor.supervisorMiddleName, "
			+ " supervisor.supervisorLastName,scholar.dateOfRegistration,monthlyreport.fromdate,monthlyreport.todate, "
			+ " scholar.topic,monthlyreport.leaves,monthlyreport.status,monthlyreport.mreportid,monthlyreport.workCompleted,monthlyreport.attachment "
			+ " from monthlyreport INNER JOIN scholar ON ( monthlyreport.scholarid = scholar.scholarID) "
			+ " INNER JOIN department ON (scholar.department = department.departmentid) INNER JOIN "
			+ " supervisor ON ( scholar.supervisor = supervisor.supervisorID) where monthlyreport.mreportid = ? ";
	public static final String RETRIEVE_QUARTERLYREPORT_BY_ID = "  select scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName, "
			+ "department.departmentname,supervisor.supervisorFirstName,supervisor.supervisorMiddleName, "
			+ "supervisor.supervisorLastName,scholar.dateOfRegistration,quarterlyreport.fromdate,quarterlyreport.todate, "
			+ "scholar.topic,quarterlyreport.status,quarterlyreport.qreportid,quarterlyreport.workCompleted,quarterlyreport.attachment from quarterlyreport INNER JOIN scholar ON "
			+ " (quarterlyreport.scholarid = scholar.scholarID) INNER JOIN department ON (scholar.department = department.departmentid) INNER JOIN "
			+ "  supervisor ON ( scholar.supervisor = supervisor.supervisorID) where quarterlyreport.qreportid = ? ";
	public static final String FILE_DISPOSITION_MONTHLYREPORT = " select attachment from monthlyreport where mreportid = ? ";
	public static final String FILE_DISPOSITION_QUARTERLYREPORT = " select attachment from quarterlyreport where qreportid = ? ";
	public static final String GET_FACULTY_INSTITUTEWISE = " select institute.institutename,faculty.facultyid,faculty.facultyname "
			+ " from faculty right outer join institute on faculty.instituteid = institute.instituteid order by institute.institutename ";
	public static final String GET_FACULTY_INSTITUTEIDWISE = " select institute.institutename,faculty.facultyid,faculty.facultyname "
			+ " from faculty right outer join institute on faculty.instituteid = institute.instituteid where institute.instituteid = ? order by institute.institutename ";
	public static final String GET_DISTRICTSSTATESWISE = " select states.statename,districts.districtid,districts.districtname "
			+ "from districts right outer join states on districts.stateid = states.stateid order by states.statename ";
	public static final String GET_SUPERVISORS_BY_ALLID = " select supervisorID,supervisorFirstName,supervisorMiddleName,supervisorLastName from supervisor where institute in ( 0, ? ) and faculty in ( 0, ? ) and department in ( 0, ? ) ";
	public static final String GET_DEPARTMENTS_BY_FACULTYID = " select departmentid,departmentname from department where facultyid = ? ";
	public static final String GET_FACULTY_BY_INSTITUTEID = " select facultyid,facultyname from faculty where instituteid = ? ";
	public static final String GET_STATES = " select * from states ";
	public static final String RETRIEVE_MONTHLYREPORT = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,monthlyreport.mreportid, "
			+ " monthlyreport.fromdate,monthlyreport.todate,monthlyreport.status from scholar INNER JOIN monthlyreport ON "
			+ " ( scholar.scholarID = monthlyreport.scholarid ) INNER JOIN institute ON ( scholar.institute = institute.instituteid ) ";
	public static final String RETRIEVE_QUARTERLYREPORT = " select scholar.scholarID,scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName,quarterlyreport.qreportid, "
			+ " quarterlyreport.fromdate,quarterlyreport.todate,quarterlyreport.status from scholar INNER JOIN quarterlyreport ON "
			+ " ( scholar.scholarID = quarterlyreport.scholarid ) ";
	public static final String SEARCH_SCHOLARS_BY_NAME_QUERY = "select sch.scholarid,sch.scholarFirstName,sch.scholarMiddleName,sch.scholarLastName,sup.supervisorFirstName,sup.supervisorLastName,cosup.supervisorFirstName,cosup.supervisorLastName,topic "
+ " from scholar sch INNER join supervisor sup on (sch.supervisor = sup.supervisorID) inner join supervisor cosup on (sch.coSupervisor = cosup.supervisorID) "
 +" where (sch.scholarFirstName like ? ) or (sch.scholarMiddleName like ? ) or (sch.scholarLastName like ? ) and sch.department = ? LIMIT 10";
	public static final String GET_SCHOLARS_EMAILID_FOR_DEPARTMENT = " select emailID from scholar where department = ? ";
	public static final String GET_SUPERVISORS_EMAILID_SCHOLARID = " select supervisor.emailID from scholar inner join supervisor on (scholar.supervisor = supervisor.supervisorID ) where scholarid in ( ? )";
	public static final String CHECK_EXISTING_COLLOQUIUM = "select count(*) from colloquium where colldatetime = ? and createdby = ? ";
	public static final String PERSIST_NEW_COLLOQUIUM = "insert into colloquium (collid,observername,scholarIDs,colldatetime,createdby,status,datecreated,dateModified)" 
			+"values (?,?,?,?,?,?,?,?)";
	public static final String GET_COLLOQUIUM_BYDEANID = " select collid,observername,colldatetime,status,dateCreated,dateModified from colloquium where createdby = ?  ";
	public static final String GET_COLLOQUIUM_BYID = " select scholarIDs,observername,colldatetime,status,dateCreated,dateModified from colloquium where collid = ?  ";
	public static final String GET_SCHOLARNAMES_FOR_SCHOLARID = " select scholar.scholarFirstName,scholar.scholarMiddleName,scholar.scholarLastName from scholar where scholarid in ( ? )";
	public static final String GET_FACULTY_BYID = "  select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname "
			+ " from institute right outer join faculty on faculty.instituteid = institute.instituteid where faculty.facultyid = ? ;  ";
	public static final String GET_DEPARTMENT_BY_ID = "  select institute.instituteid,institute.institutename,faculty.facultyid,faculty.facultyname, department.departmentid, department.departmentname "
			+ " from faculty right outer join department on department.facultyid = faculty.facultyid inner join institute on faculty.instituteid = institute.instituteid "
			+ " where department.departmentid = ? ";
	public static final String UPDATE_INSTITUTE = "update institute set institutename = ? where instituteid = ? ";
	public static final String UPDATE_FACULTY = " update faculty set facultyname = ? , instituteid = ? where facultyid = ? ";
	public static final String UPDATE_DEPARTMENT = " update department set facultyid = ?, departmentname = ? where departmentid = ? ";
	public static final String UPDATE_SUPERVISOR = "update supervisor set supervisorFirstName = ?, supervisorMiddleName = ?, supervisorLastName = ?, "
			+ "supervisorScreenName = ?,supervisorFatherFirstName = ?, supervisorFatherLastName = ?, supervisorSpouseFirstName = ?, supervisorSpouseLastName = ?, "
			+ "dob = ?, mobileNumber = ?, landlineNumber = ?, issuperadmin = ?, isadmin = ?, isdean = ?, ishead = ?, role = ?, reportto = ?,emailID = ?, correspondenceAddress = ?, "
			+ " cState = ?, cDistrict = ?,cZipCode = ?, permanentAddress = ?, pState = ?, pDistrict = ?,pZipCode = ?,status = ?, institute = ?, faculty = ?,department = ?, "
			+ " designation = ?,dateCreated = ?, dateModified = ?, salutation = ? where supervisorID = ? ";
	
	public static final String UPDATE_SCHOLAR = " update scholar set scholarFirstName = ?, scholarMiddleName = ?, scholarLastName = ?, scholarScreenName = ?,"
			+ " scholarFatherFirstName = ?, scholarFatherLastName = ?, scholarSpouseFirstName = ?, scholarSpouseLastName = ?, "
			+ " dob = ?, gender = ?, nationality = ?, mobileNumber = ?, landlineNumber = ?, emailID = ?, correspondenceAddress = ?, cState = ?, cDistrict = ?, cZipCode = ?, permanentAddress = ?, pState = ?, pDistrict = ?, pZipCode = ?, "
			+ " category = ?, categorycode = ?, coursename = ?, institute = ?, faculty = ?, department = ?, enrollmentNumber = ?, dateOfRegistration = ?, topic = ?, languagesKnown = ?, status = ?,"
			+ " supervisor = ?, coSupervisor = ?, totalExperience = ?, examsPassed = ?, institutionsName = ?, yearsOfPassing = ?, percentages = ?, subjects = ?, "
			+ " un = ? , un_rno = ?, un_xam_yr = ?, un_ref_no = ?, un_ecert_no = ?, sl = ?, sl_rno = ?, sl_xam_yr = ?, sl_ref_no = ?, sl_ecert_no = ?, cun = ?, cun_rno = ?, cun_xam_yr = ?, cun_ref_no = ?, cun_ecert_no = ?, "
			+ " employersname = ?, positionsHeld = ?, employedfrom = ?, employedto = ?, dateCreated = ?, dateModified = ?,topicModified = ?,dateOfTopicModification = ? where scholarID = ? ";
	public static final String RETRIEVE_SCHOLAR_BY_SCHOLAR_ID = "select scholarID, scholarFirstName, scholarMiddleName, scholarLastName, scholarScreenName,"
			+" scholarFatherFirstName, scholarFatherLastName, scholarSpouseFirstName, scholarSpouseLastName, "
			+" scholar.dob, scholar.gender, scholar.nationality, scholar.mobileNumber, scholar.landlineNumber, scholar.emailID, scholar.correspondenceAddress,"
			+" scholar.cState, scholar.cDistrict, scholar.cZipCode, scholar.permanentAddress, scholar.pState,"
			+" scholar.pDistrict, scholar.pZipCode,category , categorycode, coursename, scholar.institute, institutename,scholar.faculty, facultyname, scholar.department, departmentname, enrollmentNumber, dateOfRegistration, topic, languagesKnown, scholar.status,"
			+" supervisor,sup.supervisorFirstName,sup.supervisorMiddleName,sup.supervisorLastName, sup.supervisorScreenName, "
			+" coSupervisor,cosup.supervisorFirstName,cosup.supervisorMiddleName,cosup.supervisorLastName, cosup.supervisorScreenName, "
			+" totalExperience, examsPassed, institutionsName, yearsOfPassing, percentages, subjects, "
			+" un , un_rno, un_xam_yr, un_ref_no, un_ecert_no, sl, sl_rno, sl_xam_yr, sl_ref_no, sl_ecert_no, cun, cun_rno, cun_xam_yr, cun_ref_no, cun_ecert_no, "
			+" employersname, positionsHeld, employedfrom, employedto, scholar.dateCreated, scholar.dateModified,topicModified,dateOfTopicModification "
			+" from scholar INNER JOIN supervisor sup ON ( scholar.supervisor = sup.supervisorID ) "
			+" INNER JOIN supervisor cosup ON ( scholar.coSupervisor = cosup.supervisorID ) "
			+" INNER JOIN institute ON (scholar.institute = institute.instituteid) "
			+" INNER JOIN faculty ON ( faculty.facultyid = scholar.faculty) "
			+" INNER JOIN department ON ( department.departmentid = scholar.department ) "
			+" where scholar.scholarID = ? ";
	public static final String GET_SUPERVISORS_DEPARTMENTWISEMAP = " select departmentname,p.supervisorID,p.supervisorFirstName,p.supervisorMiddleName,p.supervisorLastName " +
			" from supervisor AS p INNER JOIN institute ON (p.institute = institute.instituteid ) LEFT OUTER JOIN faculty ON "
			+ " (p.faculty = faculty.facultyid) LEFT OUTER JOIN department ON (p.department = department.departmentid) where p.issuperadmin='N' and p.isadmin='N' and p.isdean='N' and p.ishead='N' and faculty.facultyid = ? ";
}
