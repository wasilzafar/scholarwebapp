package in.scholarreport.struts2.notifier;

import in.scholarreport.struts2.DTO.MonthlyReportDTO;
import in.scholarreport.struts2.util.CommonConstants;

public class MonthlyReportNotification extends Notification {

	public MonthlyReportNotification(MonthlyReportDTO report, String event) {
		super(CommonConstants.EMAIL);
	}
	
	@Override
	public void send() {

	}

}
