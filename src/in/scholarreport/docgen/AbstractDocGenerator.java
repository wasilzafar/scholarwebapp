package in.scholarreport.docgen;

public abstract class AbstractDocGenerator {
	private String type;
	private ReportAdapter report;
	public AbstractDocGenerator(String type, ReportAdapter report) {
		this.type = type;
		this.report = report;
	}
	public abstract void setInstituteTitle();
	public abstract void setScholarInfo();
	public abstract void setReportContent();
	public abstract void setAttachments();
	public abstract void setSignatures();
	public abstract Object returnDoc();
}
