package in.scholarreport.struts2.DTO;

public class DistrictDTO {
private int districtid;
private String districtname;
private StateDTO state;
public int getDistrictid() {
	return districtid;
}
public void setDistrictid(int districtid) {
	this.districtid = districtid;
}
public String getDistrictname() {
	return districtname;
}
public void setDistrictname(String districtname) {
	this.districtname = districtname;
}
public StateDTO getState() {
	return state;
}
public void setState(StateDTO state) {
	this.state = state;
}
}
