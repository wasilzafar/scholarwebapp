package in.scholarreport.struts2.DTO;

public class FacultyDTO {
private int facultyid;
private String facultyname;
private InstituteDTO institute;
public int getFacultyid() {
	return facultyid;
}
public void setFacultyid(int facultyid) {
	this.facultyid = facultyid;
}
public String getFacultyname() {
	return facultyname;
}
public void setFacultyname(String facultyname) {
	this.facultyname = facultyname;
}
public InstituteDTO getInstitute() {
	return institute;
}
public void setInstitute(InstituteDTO institute) {
	this.institute = institute;
}
}
