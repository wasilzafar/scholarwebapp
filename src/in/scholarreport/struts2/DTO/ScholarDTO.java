package in.scholarreport.struts2.DTO;

public class ScholarDTO {
private String scholarID;
private String scholarFirstName;
private String scholarMiddleName;
private String scholarLastName;
private String scholarFatherFirstName;
private String scholarFatherLastName;
private String scholarMotherFirstName;
private String scholarMotherLastName;
private String scholarSpouseFirstName;
private String scholarSpouseLastName;
private String scholarScreenName;
private String dob;
private String gender;
private String nationality;
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
private String category;
private String categorycode;
private String coursename;
private String languagesKnown;
private InstituteDTO institute;
private FacultyDTO faculty;
private DepartmentDTO department;
private int enrollmentNumber;
private String dateOfRegistration;
private String topic;
private String topicModified;
private String dateOfTopicModification;
private SupervisorDTO supervisor;
private SupervisorDTO coSupervisor;
private int totalExperience;
private String examsPassed;
private String institutionsName;
private String yearsOfPassing;
private String percentages;
private String subjects;
private boolean un;
private String un_rno;
private String un_xam_yr;
private String un_ref_no;
private String un_ecert_no;
private boolean sl;
private String sl_rno;
private String sl_xam_yr;
private String sl_ref_no;
private String sl_ecert_no;
private boolean cun;
private String cun_rno;
private String cun_xam_yr;
private String cun_ref_no;
private String cun_ecert_no;
private String employersname;
private String positionsHeld;
private String employedfrom;
private String employedto;
private String status;
private String dateCreated;
private String dateModified;
public String getScholarFirstName() {
	return scholarFirstName;
}
public void setScholarFirstName(String scholarFirstName) {
	this.scholarFirstName = scholarFirstName;
}
public String getScholarLastName() {
	return scholarLastName;
}
public void setScholarLastName(String scholarLastName) {
	this.scholarLastName = scholarLastName;
}
public String getScholarFatherFirstName() {
	return scholarFatherFirstName;
}
public void setScholarFatherFirstName(String scholarFatherFirstName) {
	this.scholarFatherFirstName = scholarFatherFirstName;
}
public String getScholarFatherLastName() {
	return scholarFatherLastName;
}
public void setScholarFatherLastName(String scholarFatherLastName) {
	this.scholarFatherLastName = scholarFatherLastName;
}
public String getScholarMotherFirstName() {
	return scholarMotherFirstName;
}
public void setScholarMotherFirstName(String scholarMotherFirstName) {
	this.scholarMotherFirstName = scholarMotherFirstName;
}
public String getScholarMotherLastName() {
	return scholarMotherLastName;
}
public void setScholarMotherLastName(String scholarMotherLastName) {
	this.scholarMotherLastName = scholarMotherLastName;
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
public String getPermanentAddress() {
	return permanentAddress;
}
public void setPermanentAddress(String permanentAddress) {
	this.permanentAddress = permanentAddress;
}
public String getDateOfRegistration() {
	return dateOfRegistration;
}
public void setDateOfRegistration(String dateOfRegistration) {
	this.dateOfRegistration = dateOfRegistration;
}
public String getTopic() {
	return topic;
}
public void setTopic(String topic) {
	this.topic = topic;
}
public String getScholarID() {
	return scholarID;
}
public void setScholarID(String scholarID) {
	this.scholarID = scholarID;
}
public String getScholarScreenName() {
	return scholarScreenName;
}
public void setScholarScreenName(String scholarScreenName) {
	this.scholarScreenName = scholarScreenName;
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
public String getScholarMiddleName() {
	return scholarMiddleName;
}
public void setScholarMiddleName(String scholarMiddleName) {
	this.scholarMiddleName = scholarMiddleName;
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
public SupervisorDTO getCoSupervisor() {
	return coSupervisor;
}
public void setCoSupervisor(SupervisorDTO coSupervisor) {
	this.coSupervisor = coSupervisor;
}
public int getEnrollmentNumber() {
	return enrollmentNumber;
}
public void setEnrollmentNumber(int enrollmentNumber) {
	this.enrollmentNumber = enrollmentNumber;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public SupervisorDTO getSupervisor() {
	return supervisor;
}
public void setSupervisor(SupervisorDTO supervisor) {
	this.supervisor = supervisor;
}
public String getScholarSpouseFirstName() {
	return scholarSpouseFirstName;
}
public void setScholarSpouseFirstName(String scholarSpouseFirstName) {
	this.scholarSpouseFirstName = scholarSpouseFirstName;
}
public String getScholarSpouseLastName() {
	return scholarSpouseLastName;
}
public void setScholarSpouseLastName(String scholarSpouseLastName) {
	this.scholarSpouseLastName = scholarSpouseLastName;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getNationality() {
	return nationality;
}
public void setNationality(String nationality) {
	this.nationality = nationality;
}
public String getLandlineNumber() {
	return landlineNumber;
}
public void setLandlineNumber(String landlineNumber) {
	this.landlineNumber = landlineNumber;
}
public String getCorrespondenceAddress() {
	return correspondenceAddress;
}
public void setCorrespondenceAddress(String correspondenceAddress) {
	this.correspondenceAddress = correspondenceAddress;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getCategorycode() {
	return categorycode;
}
public void setCategorycode(String categorycode) {
	this.categorycode = categorycode;
}
public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public InstituteDTO getInstitute() {
	return institute;
}
public void setInstitute(InstituteDTO institute) {
	this.institute = institute;
}
public int getTotalExperience() {
	return totalExperience;
}
public void setTotalExperience(int totalExperience) {
	this.totalExperience = totalExperience;
}
public String getExamsPassed() {
	return examsPassed;
}
public void setExamsPassed(String examsPassed) {
	this.examsPassed = examsPassed;
}
public String getInstitutionsName() {
	return institutionsName;
}
public void setInstitutionsName(String institutionsName) {
	this.institutionsName = institutionsName;
}
public String getYearsOfPassing() {
	return yearsOfPassing;
}
public void setYearsOfPassing(String yearsOfPassing) {
	this.yearsOfPassing = yearsOfPassing;
}
public String getPercentages() {
	return percentages;
}
public void setPercentages(String percentages) {
	this.percentages = percentages;
}
public String getSubjects() {
	return subjects;
}
public void setSubjects(String subjects) {
	this.subjects = subjects;
}
public boolean isUn() {
	return un;
}
public void setUn(boolean un) {
	this.un = un;
}
public String getUn_rno() {
	return un_rno;
}
public void setUn_rno(String un_rno) {
	this.un_rno = un_rno;
}
public String getUn_xam_yr() {
	return un_xam_yr;
}
public void setUn_xam_yr(String un_xam_yr) {
	this.un_xam_yr = un_xam_yr;
}
public String getUn_ref_no() {
	return un_ref_no;
}
public void setUn_ref_no(String un_ref_no) {
	this.un_ref_no = un_ref_no;
}
public String getUn_ecert_no() {
	return un_ecert_no;
}
public void setUn_ecert_no(String un_ecert_no) {
	this.un_ecert_no = un_ecert_no;
}
public boolean isSl() {
	return sl;
}
public void setSl(boolean sl) {
	this.sl = sl;
}
public String getSl_rno() {
	return sl_rno;
}
public void setSl_rno(String sl_rno) {
	this.sl_rno = sl_rno;
}
public String getSl_xam_yr() {
	return sl_xam_yr;
}
public void setSl_xam_yr(String sl_xam_yr) {
	this.sl_xam_yr = sl_xam_yr;
}
public String getSl_ref_no() {
	return sl_ref_no;
}
public void setSl_ref_no(String sl_ref_no) {
	this.sl_ref_no = sl_ref_no;
}
public String getSl_ecert_no() {
	return sl_ecert_no;
}
public void setSl_ecert_no(String sl_ecert_no) {
	this.sl_ecert_no = sl_ecert_no;
}
public boolean isCun() {
	return cun;
}
public void setCun(boolean cun) {
	this.cun = cun;
}
public String getCun_rno() {
	return cun_rno;
}
public void setCun_rno(String cun_rno) {
	this.cun_rno = cun_rno;
}
public String getCun_xam_yr() {
	return cun_xam_yr;
}
public void setCun_xam_yr(String cun_xam_yr) {
	this.cun_xam_yr = cun_xam_yr;
}
public String getCun_ref_no() {
	return cun_ref_no;
}
public void setCun_ref_no(String cun_ref_no) {
	this.cun_ref_no = cun_ref_no;
}
public String getCun_ecert_no() {
	return cun_ecert_no;
}
public void setCun_ecert_no(String cun_ecert_no) {
	this.cun_ecert_no = cun_ecert_no;
}
public String getEmployersname() {
	return employersname;
}
public void setEmployersname(String employersname) {
	this.employersname = employersname;
}
public String getPositionsHeld() {
	return positionsHeld;
}
public void setPositionsHeld(String positionsHeld) {
	this.positionsHeld = positionsHeld;
}
public String getEmployedfrom() {
	return employedfrom;
}
public void setEmployedfrom(String employedfrom) {
	this.employedfrom = employedfrom;
}
public String getEmployedto() {
	return employedto;
}
public void setEmployedto(String employedto) {
	this.employedto = employedto;
}
public String getLanguagesKnown() {
	return languagesKnown;
}
public void setLanguagesKnown(String languagesKnown) {
	this.languagesKnown = languagesKnown;
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
public String getTopicModified() {
	return topicModified;
}
public void setTopicModified(String topicModified) {
	this.topicModified = topicModified;
}
public String getDateOfTopicModification() {
	return dateOfTopicModification;
}
public void setDateOfTopicModification(String dateOfTopicModification) {
	this.dateOfTopicModification = dateOfTopicModification;
}

}
