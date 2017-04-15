package in.scholarreport.struts2.DTO;

public class SupervisorDTO {
	private String supervisorID;
	private String salutation;
	private String supervisorFirstName;
	private String supervisorMiddleName;
	private String supervisorLastName;
	private String supervisorScreenName;
	private String supervisorFatherFirstName;
	private String supervisorFatherLastName;
	private String supervisorSpouseFirstName;
	private String supervisorSpouseLastName;
	private String dob;
	private char isSuperadmin;
	private char isAdmin;
	private char isDean;
	private char isHead;
	private String role;
	private SupervisorDTO reportto;
	private String mobileNumber;
	private String landlineNumber;
	private String emailID;
	private String correspondenceAddress;
	private StateDTO correspondenceAddressState;
	private DistrictDTO correspondenceAddressDistrict;
	private String correspondenceAddressZipCode;
	private String permanentAddress;
	private StateDTO permanentAddressState;
	private DistrictDTO permanentAddressDistrict;
	private String permanentAddressZipCode;
	private InstituteDTO institute;
	private FacultyDTO faculty;
	private DepartmentDTO department;
	private String designation;
	private String status;
	private String dateCreated;
	private String dateModified;
	public String getSupervisorFirstName() {
		return supervisorFirstName;
	}
	public void setSupervisorFirstName(String supervisorFirstName) {
		this.supervisorFirstName = supervisorFirstName;
	}
	public String getSupervisorLastName() {
		return supervisorLastName;
	}
	public void setSupervisorLastName(String supervisorLastName) {
		this.supervisorLastName = supervisorLastName;
	}
	public String getSupervisorFatherFirstName() {
		return supervisorFatherFirstName;
	}
	public void setSupervisorFatherFirstName(String supervisorFatherFirstName) {
		this.supervisorFatherFirstName = supervisorFatherFirstName;
	}
	public String getSupervisorFatherLastName() {
		return supervisorFatherLastName;
	}
	public void setSupervisorFatherLastName(String supervisorFatherLastName) {
		this.supervisorFatherLastName = supervisorFatherLastName;
	}
	public String getSupervisorSpouseFirstName() {
		return supervisorSpouseFirstName;
	}
	public void setSupervisorSpouseFirstName(String supervisorSpouseFirstName) {
		this.supervisorSpouseFirstName = supervisorSpouseFirstName;
	}
	public String getSupervisorSpouseLastName() {
		return supervisorSpouseLastName;
	}
	public void setSupervisorSpouseLastName(String supervisorSpouseLastName) {
		this.supervisorSpouseLastName = supervisorSpouseLastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getCorrespondenceAddress() {
		return correspondenceAddress;
	}
	public void setCorrespondenceAddress(String correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getSupervisorMiddleName() {
		return supervisorMiddleName;
	}
	public void setSupervisorMiddleName(String supervisorMiddleName) {
		this.supervisorMiddleName = supervisorMiddleName;
	}
	public String getSupervisorID() {
		return supervisorID;
	}
	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}
	public String getSupervisorScreenName() {
		return supervisorScreenName;
	}
	public void setSupervisorScreenName(String supervisorScreenName) {
		this.supervisorScreenName = supervisorScreenName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateModified() {
		return dateModified;
	}
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public FacultyDTO getFaculty() {
		return faculty;
	}
	public void setFaculty(FacultyDTO faculty) {
		this.faculty = faculty;
	}
	public DepartmentDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLandlineNumber() {
		return landlineNumber;
	}
	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	public InstituteDTO getInstitute() {
		return institute;
	}
	public void setInstitute(InstituteDTO institute) {
		this.institute = institute;
	}
	public SupervisorDTO getReportto() {
		return reportto;
	}
	public void setReportto(SupervisorDTO reportto) {
		this.reportto = reportto;
	}
	public StateDTO getCorrespondenceAddressState() {
		return correspondenceAddressState;
	}
	public void setCorrespondenceAddressState(StateDTO correspondenceAddressState) {
		this.correspondenceAddressState = correspondenceAddressState;
	}
	public DistrictDTO getCorrespondenceAddressDistrict() {
		return correspondenceAddressDistrict;
	}
	public void setCorrespondenceAddressDistrict(
			DistrictDTO correspondenceAddressDistrict) {
		this.correspondenceAddressDistrict = correspondenceAddressDistrict;
	}
	public String getCorrespondenceAddressZipCode() {
		return correspondenceAddressZipCode;
	}
	public void setCorrespondenceAddressZipCode(String correspondenceAddressZipCode) {
		this.correspondenceAddressZipCode = correspondenceAddressZipCode;
	}
	public StateDTO getPermanentAddressState() {
		return permanentAddressState;
	}
	public void setPermanentAddressState(StateDTO permanentAddressState) {
		this.permanentAddressState = permanentAddressState;
	}
	public DistrictDTO getPermanentAddressDistrict() {
		return permanentAddressDistrict;
	}
	public void setPermanentAddressDistrict(DistrictDTO permanentAddressDistrict) {
		this.permanentAddressDistrict = permanentAddressDistrict;
	}
	public String getPermanentAddressZipCode() {
		return permanentAddressZipCode;
	}
	public void setPermanentAddressZipCode(String permanentAddressZipCode) {
		this.permanentAddressZipCode = permanentAddressZipCode;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public char getIsSuperadmin() {
		return isSuperadmin;
	}
	public void setIsSuperadmin(char isSuperadmin) {
		this.isSuperadmin = isSuperadmin;
	}
	public char getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(char isAdmin) {
		this.isAdmin = isAdmin;
	}
	public char getIsDean() {
		return isDean;
	}
	public void setIsDean(char isDean) {
		this.isDean = isDean;
	}
	public char getIsHead() {
		return isHead;
	}
	public void setIsHead(char isHead) {
		this.isHead = isHead;
	}

}
