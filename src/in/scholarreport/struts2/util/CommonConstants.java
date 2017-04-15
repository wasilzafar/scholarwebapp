package in.scholarreport.struts2.util;

public interface CommonConstants {
	public static final String SCHOLAR = "scholar";
	public static final String SUPERVISOR = "supervisor";
	public static final String HEAD = "HEAD";
	public static final String DEAN = "DEAN";
	public static final String ADMIN = "ADMIN";
	public static final String SUPERADMIN = "SUPERADMIN";
	public static final String STATUS_ACTIVE = "ACTIVE";
	public static final String STATUS_INACTIVE = "INACTIVE";
	public static final String STATUS_APPROVED = "APPROVED";
	public static final String STATUS_PENDING = "PENDING";
	public static final String REPORT_STATUS_PENDING_SUPERVISOR = "PENDING_SUPERVISOR";
	public static final String REPORT_STATUS_PENDING_HEAD = "PENDING_HEAD";
	public static final String REPORT_STATUS_PENDING_DEAN = "PENDING_DEAN";
	public static final String REPORT_STATUS_REVISE_SUPERVISOR = "REVISE_SUPERVISOR";
	public static final String REPORT_STATUS_REVISE_HEAD = "REVISE_HEAD";
	public static final String REPORT_STATUS_REVISE_DEAN = "REVISE_DEAN";
	/**
	 * @author zafar Three strings @user@ , @emailid@ and @credential@ are
	 *         suppossed to be replaced with actual values.
	 * 
	 */
	public static final String EMAIL_NOTIFICATION_TEMPLATE = "Dear @user@,<br><br>Your account at Scholar Reports has been set up sccessfully. Please find the details as follows :<br><br>Login Id : @emailid@<br>Password : @credential@<br><br>Good Luck,<br>Scholar Reports<br>";
	public static final String EMAIL_NOTIFICATION_SUBJECT_TEMPLATE = "Scholar Reports Account Info";
	public static final int EMAIL = 1;
	public static final String SEPARATOR = "@";
	public static final String RESUBMIT = "RESUBMIT";

}
