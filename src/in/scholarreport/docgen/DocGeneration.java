package in.scholarreport.docgen;

public abstract class DocGeneration {
	private AbstractDocGenerator generator;
	public abstract AbstractDocGenerator createDocGenerator();
	public Object prepareReport(){
		generator = createDocGenerator();
		generator.setInstituteTitle();
		generator.setScholarInfo();
		generator.setReportContent();
		generator.setAttachments();
		generator.setSignatures();
		return generator.returnDoc();
	}
	public abstract void dispatchReport(Object report);
	
}
