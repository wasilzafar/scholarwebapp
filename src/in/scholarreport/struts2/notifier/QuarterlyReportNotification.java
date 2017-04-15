package in.scholarreport.struts2.notifier;

import in.scholarreport.struts2.DTO.QuarterlyReportDTO;
import in.scholarreport.struts2.util.CommonConstants;

public class QuarterlyReportNotification extends Notification {

	public QuarterlyReportNotification(QuarterlyReportDTO report, String event) {
		super(CommonConstants.EMAIL);
	}

	@Override
	public void send() {

	}

}
