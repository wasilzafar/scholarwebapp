package in.scholarreport.struts2.DTO;

public class DepartmentDTO {
	private int departmentid;
	private String departmentname;
	private FacultyDTO faculty;
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public FacultyDTO getFaculty() {
		return faculty;
	}
	public void setFaculty(FacultyDTO faculty) {
		this.faculty = faculty;
	}
}
