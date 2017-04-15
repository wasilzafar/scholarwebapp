package in.scholarreport.struts2.DTO;

import java.io.File;

public class MonthlyReportDTO {
	
	private String mreportid;
	private String fromDate;
	private String toDate;
	private int leaves;
	private String workCompleted;
	private String remark;
	private String status;
	private ScholarDTO  scholar;
	private SupervisorDTO  approver;
	private String  dateCreated;
	private String  dateModified;
	private String  dateApproved;
	private File attachment; 
	private String attachmentFileName; 
	private String attachmentContentType;

	public String getWorkCompleted() {
		return workCompleted;
	}
	public void setWorkCompleted(String workCompleted) {
		this.workCompleted = workCompleted;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	public String getMreportid() {
		return mreportid;
	}
	public void setMreportid(String mreportid) {
		this.mreportid = mreportid;
	}
	public ScholarDTO getScholar() {
		return scholar;
	}
	public void setScholar(ScholarDTO scholar) {
		this.scholar = scholar;
	}
	public SupervisorDTO getApprover() {
		return approver;
	}
	public void setApprover(SupervisorDTO approver) {
		this.approver = approver;
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
	public String getDateApproved() {
		return dateApproved;
	}
	public void setDateApproved(String dateApproved) {
		this.dateApproved = dateApproved;
	}
	public void setLeaves(int leaves) {
		this.leaves = leaves;
	}
	public int getLeaves() {
		return leaves;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

}
