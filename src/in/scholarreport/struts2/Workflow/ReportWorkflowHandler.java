package in.scholarreport.struts2.Workflow;

import in.scholarreport.struts2.util.CommonConstants;

public class ReportWorkflowHandler {
	public static String invoke(String current){
		String next = new String("UNKNOWN");
		if(current.equalsIgnoreCase(CommonConstants.REPORT_STATUS_PENDING_SUPERVISOR)){
			next = CommonConstants.REPORT_STATUS_PENDING_HEAD;
		}else if(current.equalsIgnoreCase(CommonConstants.REPORT_STATUS_PENDING_HEAD)){
			next = CommonConstants.REPORT_STATUS_PENDING_DEAN;
		}else if(current.equalsIgnoreCase(CommonConstants.REPORT_STATUS_PENDING_DEAN)){
			next = CommonConstants.STATUS_APPROVED;
		}else if(current.equalsIgnoreCase(CommonConstants.STATUS_APPROVED)){
			next = CommonConstants.STATUS_APPROVED;
		} else if (current
				.equalsIgnoreCase(CommonConstants.REPORT_STATUS_REVISE_DEAN)
				|| current
						.equalsIgnoreCase(CommonConstants.REPORT_STATUS_REVISE_HEAD)
				|| current
						.equalsIgnoreCase(CommonConstants.REPORT_STATUS_REVISE_SUPERVISOR)){
			next = CommonConstants.RESUBMIT;
		}
		return next;
	}
	public static String getDefaultWorkflowStatus(String currentSupervisorRole){
		String next = new String(CommonConstants.REPORT_STATUS_PENDING_SUPERVISOR);
		if(currentSupervisorRole.equalsIgnoreCase("HEAD"))
			next = CommonConstants.REPORT_STATUS_PENDING_HEAD;
		else if(currentSupervisorRole.equalsIgnoreCase("DEAN"))
			next = CommonConstants.REPORT_STATUS_PENDING_DEAN;
		else if(currentSupervisorRole.equalsIgnoreCase("ADMIN"))
			next = CommonConstants.STATUS_APPROVED;
		return next;
	}
}
