package in.scholarreport.docgen.pdfbox;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import in.scholarreport.docgen.AbstractDocGenerator;
import in.scholarreport.docgen.ReportAdapter;

public class PDFDocGenerator extends AbstractDocGenerator {
	PDDocument document;
	public PDFDocGenerator(String type, ReportAdapter report) throws IOException {
		super(type, report);
		document = new PDDocument();
	}

	@Override
	public void setInstituteTitle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScholarInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReportContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAttachments() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSignatures() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object returnDoc() {
		// TODO Auto-generated method stub
		return null;
	}

}
