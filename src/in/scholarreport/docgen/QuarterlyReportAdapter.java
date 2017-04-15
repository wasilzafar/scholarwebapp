package in.scholarreport.docgen;

import in.scholarreport.struts2.DTO.QuarterlyReportDTO;

public class QuarterlyReportAdapter extends ReportAdapter {

	
	QuarterlyReportDTO report;
	
	public QuarterlyReportAdapter(QuarterlyReportDTO qreport) {
		super();
		this.report = qreport;
	}
	
	
	@Override
	public String getInstituteTitle() {
		return getReport().getScholar().getInstitute().getInstitutename();
		}

	@Override
	public String getScholarInfo() {
		StringBuffer schInfo = new StringBuffer();
		schInfo.append(" Name : ").append(getReport().getScholar().getScholarFirstName()).append(" ").append(getReport().getScholar().getScholarMiddleName()).append(" ").append(getReport().getScholar().getScholarLastName()).append("\n");
		schInfo.append(" Supervisor Name : ").append(getReport().getApprover().getSupervisorFirstName()).append(" ").append(getReport().getApprover().getSupervisorMiddleName()).append(" ").append(getReport().getApprover().getSupervisorLastName()).append("\n");
		schInfo.append(" Department : ").append(getReport().getScholar().getDepartment().getDepartmentname()).append("\n");
		schInfo.append(" Date of Registration : ").append(getReport().getScholar().getDateOfRegistration()).append("\n");
		schInfo.append(getReport().getScholar().getTopic()).append("\n");
		return schInfo.toString();

	}

	@Override
	public String getReportContent() {
		StringBuffer repContent = new StringBuffer();
		repContent.append(getReport().getStatus()).append("\n");
		repContent.append("From : ").append(getReport().getFromDate()).append("\n");
		repContent.append("To : ").append(getReport().getToDate()).append("\n");
		repContent.append("Progress made : ").append(getReport().getProgress()).append("\n");
		repContent.append("Work Completed : ").append(getReport().getWorkCompleted()).append("\n");
		repContent.append("Remark : ").append(getReport().getRemark()).append("\n");
		return null;

	}

	@Override
	public String getAttachments() {
		return null;

	}

	@Override
	public String getSignatures() {
		return null;

	}


	public QuarterlyReportDTO getReport() {
		return report;
	}

}
