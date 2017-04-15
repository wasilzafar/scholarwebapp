package in.scholarreport.struts2.DTO;

public class SearchCriteriaDTO {
private String departmentName;
private String supervisorID;
private String fromDate;
private String toDate;
private String scholarType;

public String getFromDate() {
	return fromDate;
}
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}
public String getToDate() {
	return toDate;
}
public void setToDate(String toDate) {
	this.toDate = toDate;
}
public String getSupervisorID() {
	return supervisorID;
}
public void setSupervisorID(String supervisorID) {
	this.supervisorID = supervisorID;
}

public String getScholarType() {
	return scholarType;
}
public void setScholarType(String scholarType) {
	this.scholarType = scholarType;
}
public String getDepartmentName() {
	return departmentName;
}
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}
}
