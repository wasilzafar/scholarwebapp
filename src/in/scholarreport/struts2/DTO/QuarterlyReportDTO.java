package in.scholarreport.struts2.DTO;

import java.io.File;

public class QuarterlyReportDTO {
	
	private String qreportid;
	private String fromDate;
	private String toDate;
	private String workCompleted;
	private String remark;
	private String status;
	private String progress;
	private ScholarDTO scholar;
	private SupervisorDTO  approver;
	private String  dateCreated;
	private String  dateModified;
	private String  dateApproved;
	private File attachment; 
	private String attachmentFileName; 
	private String attachmentContentType;
	
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
	public String getQreportid() {
		return qreportid;
	}
	public void setQreportid(String qreportid) {
		this.qreportid = qreportid;
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
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	
}
