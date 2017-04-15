package in.scholarreport.docgen;

import in.scholarreport.struts2.DTO.MonthlyReportDTO;

public class MonthlyReportAdapter extends ReportAdapter {
	
	MonthlyReportDTO report;
	
	public MonthlyReportAdapter(MonthlyReportDTO mreport) {
		super();
		this.report = mreport;
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
		repContent.append("Leaves : ").append(getReport().getLeaves()).append("\n");
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

	public MonthlyReportDTO getReport() {
		return report;
	}

}
