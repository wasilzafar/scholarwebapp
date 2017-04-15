package in.scholarreport.struts2.DTO;

import java.util.List;

public class ColloquiumDTO {
	private String collID;
	private List<String>  scholarID;
	private String observerName;
	private String datetime;
	boolean notifySupervisors;
	private String additionalNotification;
	private String createdby;
	private String status;
	private String dateCreated;
	private String dateModified;
	public List<String> getScholarID() {
		return scholarID;
	}
	public void setScholarID(List<String> scholarID) {
		this.scholarID = scholarID;
	}
	public String getObserverName() {
		return observerName;
	}
	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public boolean isNotifySupervisors() {
		return notifySupervisors;
	}
	public void setNotifySupervisors(boolean notifySupervisors) {
		this.notifySupervisors = notifySupervisors;
	}
	public String getAdditionalNotification() {
		return additionalNotification;
	}
	public void setAdditionalNotification(String additionalNotification) {
		this.additionalNotification = additionalNotification;
	}
	public String getCollID() {
		return collID;
	}
	public void setCollID(String collID) {
		this.collID = collID;
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
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
