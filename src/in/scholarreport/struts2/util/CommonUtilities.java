package in.scholarreport.struts2.util;

import in.scholarreport.struts2.Delegate.BaseDelegate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class CommonUtilities {
	static Logger logger = Logger.getLogger(CommonUtilities.class);
	public static final String DATASOURCENAME = "java:PHDDS";
	public static final String DATASOURCENAME2 = "java:/comp/env/PHDDS";
	public static final char[] allChars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e',
			'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L',
			'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r',
			'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y',
			'y', 'Z', 'z', '#', '$' };
	public static Properties mimetypes = null;
	public static Properties stateIds = null;
	public static Properties instituteIds = null;

	public static Date getSQLDate(String dt) {
		java.util.Date date = null;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String tempDate = dt.replace("-", "/");
		try {
			date = df.parse(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date(date.getTime());
	}

	public static String getCredential(String password) {
		char[] ch = password.toCharArray();
		StringBuffer cred = new StringBuffer();
		for (char c : ch) {
			cred.append(Integer.toHexString((int) c));
		}
		return cred.toString();
	}

	public static List getIdsFromInput(String input) {
		ArrayList strs = new ArrayList();
		String[] strTemp;
		strTemp = input.split(CommonConstants.SEPARATOR);
		for (String str : strTemp) {
			if (str != null)
				strs.add(str);
		}
		return strs;
	}

	public static String getStringFromArray(String[] ids) {
		StringBuffer strbuf = new StringBuffer("'");
		for (String str : ids) {
			strbuf.append(str).append("'").append(",'");
		}
		if (strbuf.toString().lastIndexOf("'") == (strbuf.toString().length() - 1))
			strbuf.deleteCharAt(strbuf.length() - 1);
		if (strbuf.toString().lastIndexOf(",") == (strbuf.toString().length() - 1))
			strbuf.deleteCharAt(strbuf.length() - 1);
		return strbuf.toString();
	}
	
	public static String getIntegerStringFromArray(String[] ids) {
		StringBuffer strbuf = new StringBuffer("");
		for (String str : ids) {
			strbuf.append(str).append("").append(",");
		}
		if (strbuf.toString().lastIndexOf("") == (strbuf.toString().length() - 1))
			strbuf.deleteCharAt(strbuf.length() - 1);
		if (strbuf.toString().lastIndexOf(",") == (strbuf.toString().length() - 1))
			strbuf.deleteCharAt(strbuf.length() - 1);
		return strbuf.toString();
	}

	public static String getContentTypeMapping(String content) {
		if (mimetypes == null) {
			mimetypes = new Properties();
			InputStream is = CommonUtilities.class.getClass().getClassLoader()
					.getResourceAsStream("mtm.properties");
			logger.info("Loading mime type properties file.");
			try {
				mimetypes.load(is);
				logger.info("Successfully loaded mime type properties file.");
			} catch (IOException e) {
				logger.error("Error while loading mime type mapping ."
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		String ext = content.substring(content.lastIndexOf("."));
		logger.info("Extension for file is " + ext);
		return (String) mimetypes.get(ext);
	}

	public static List convertStringArrayToList(String[] strArr) {
		List list = new ArrayList();
		for (String str : strArr) {
			list.add((String) str);
		}
		return list;
	}

	public static String convertMapToXML(Map map, String type) {
		StringBuffer options = new StringBuffer();
		Set<Map.Entry> entry = (Set<Entry>) map.entrySet();
		if (type.equalsIgnoreCase("option"))
			for (Map.Entry sup : entry) {
				options.append("<option><value>" + sup.getKey().toString()
						+ "</value><text>" + escapeSpecialForXML(sup.getValue().toString())
						+ "</text></option>");
			}
		return options.toString();
	}

	public static int convertNulltoZeroInt(String str) {
		int num = 0;
		try {
			num = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			logger.error("Unable to parse String " + str);
		}

		return num;
	}

	public static int getStateId(String statename) {
		if (stateIds == null) {
			BaseDelegate delegate = new BaseDelegate();
			stateIds = (Properties) delegate.fetchStateIdProperties();
		}
		int id = (Integer) stateIds.get(statename);
		return id;
	}
	
	public static String getStateName(int stateId) {
		if (stateIds == null) {
			BaseDelegate delegate = new BaseDelegate();
			stateIds = (Properties) delegate.fetchStateIdProperties();
		}
		for(Entry entry : stateIds.entrySet()){
			if(((Integer)entry.getValue())== stateId)
				return (String) entry.getKey();
		}
		return null;
	}

	public static int getInstituteId(String institutename) {
		if (instituteIds == null) {
			BaseDelegate delegate = new BaseDelegate();
			instituteIds = (Properties) delegate.fetchInstituteIdProperties();
		}
		int id = (Integer) instituteIds.get(institutename);
		return id;
	}

	public static String getID(int size) {
		Random rand = new Random();
		StringBuffer stringId = new StringBuffer();
		byte[] ID = new byte[size];
		rand.nextBytes(ID);
		for (int i = 0; i < size; i++) {
			byte next = ID[i];
			int temp = (next & 0x3F);
			stringId.append(allChars[temp]);
		}
		return stringId.toString();
	}

	public static void sendMail(String host, String from, String to,
			String user, String pass, String subject, String body)  {
		/*
		 * host SMTP host e.g. "mail.domainname.com"; 
		 * user email user e.g. "user@domain.com";
		 * pass user email account password e.g. "xxxxx"; 
		 * to Send to email ID  e.g. "user@example.com"; 
		 * from Sender email ID e.g. "user@domain.com"; 
		 * subject  email subject e.g. "Test subject"; 
		 * messageText email body e.g. "Test body";
		 */
		boolean sessionDebug = false;
		Properties props = System.getProperties();
	    props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", host);  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "false");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false"); 
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);
		Message msg = new MimeMessage(mailSession);
		try {
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			
			if (to.indexOf(',') > 0)   
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to));   
	        else  
	        	msg.setRecipient(Message.RecipientType.CC, new InternetAddress(to));
			
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			msg.setContent("<div style=\"color:green;width:100%;height:40%;background-color:yellow;font-family: 'Trebuchet MS';font-size: 100%;\">"+body+"</div>", "text/html; charset=utf-8");
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			logger.error("Error while sending email : " + ex.getMessage());
		}
	}
	
	public static String md5(String input) {

		String md5 = null;

		if (null == input)
			return null;

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}

	
	public static String escapeSpecialForXML(String str) {
		str = str.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return str;
	}
	
	public static Timestamp makeTimestamp(String datetime) {
		String date = datetime.trim().substring(0, 10);
		String time = datetime.trim().substring(11, 16);
		String amorpm = datetime.trim().substring(17);
		String[] dateParts = date.split("-");
		String[] timeParts = time.split(":");
		Calendar cal = new GregorianCalendar();
		cal.setTimeZone(TimeZone.getDefault());
		try {
			cal.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));
			cal.set(Calendar.MONTH, Integer.parseInt(dateParts[1]) - 1);
			cal.set(Calendar.DATE, Integer.parseInt(dateParts[0]));
			logger.info("Time parts : "+ timeParts[0]+"	"+timeParts[1]);
			if (amorpm.equalsIgnoreCase("am"))
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));			
			else
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0])+12);
			
			cal.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
		} catch (NumberFormatException e) {
			logger.error(e.getLocalizedMessage());
		}
			// now convert GregorianCalendar object to Timestamp object
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static List getStringArrFromString(String str,String separator){
		if(str.indexOf(separator) > -1){
			return Arrays.asList(str.split(separator));
		}
		return Arrays.asList(new String[]{str});
	}
	
	public static String getFormattedDateFromSQLDate(java.sql.Date date){
		if(date != null){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String outDate = dateFormat.format(date);
		return outDate;
		}
		return null;
	}
}
