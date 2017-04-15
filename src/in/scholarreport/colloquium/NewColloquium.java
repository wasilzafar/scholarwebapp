package in.scholarreport.colloquium;

import in.scholarreport.struts2.DTO.ColloquiumDTO;
import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.ScholarDelegate;
import in.scholarreport.struts2.actions.SubmitColloquiumAction;
import in.scholarreport.struts2.util.CommonUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class NewColloquium implements ColloquiumActivity {
	static Logger logger = Logger.getLogger(NewColloquium.class);
	ColloquiumManager manager;
	public NewColloquium(ColloquiumManager manager) {
		this.manager = manager;
	}
	public String prepareNotificationTemplate() {
		logger.info("Preparing template ...");
		ColloquiumDTO coll = manager.getColloquium();
		String observer =  coll.getObserverName();
		String dnt = coll.getDatetime();
		logger.info("Oberver - Data & Time "+observer+" "+dnt);
		return new String("Dear Scholars,<br><br>A colloquium has been scheduled."
				+ " Please find the details as follows :<br><br>Observer : "+observer+"<br>Date-Time : "+dnt+" <br><br>"
		+" Good Luck,<br>Scholar Reports<br>");
	}

	public String getParticipants(int depid) {
		logger.info("Getting participants ...");
		ScholarDelegate schDel = new ScholarDelegate();
		String[] addpartsplit;
		ColloquiumDTO coll = manager.getColloquium();
		List participants = new ArrayList();
		participants.addAll(schDel.getScholarsEmailIDforDepartment(depid));
		String addNotification = coll.getAdditionalNotification();
		if (addNotification != null && addNotification.length()>0) {
			addpartsplit = coll.getAdditionalNotification().split(",");
			participants.addAll(Arrays.asList(addpartsplit));
		}
		boolean supflag = coll.isNotifySupervisors();
		if (supflag) {
			int i=0;
			Object[] ids = coll.getScholarID().toArray();
			String[] strIDs = new String[ids.length];
			for (java.lang.Object str : ids) {
				strIDs[i] = (String) str;
				i++;
			}
			participants.addAll(schDel
					.getSupervisorsEmailIDforScholars(strIDs));
		}
		return StringUtils.join(participants, ",");

	}

	public void notifyParticipants(String template, String participants) {
		CommonUtilities.sendMail("smtp.gmail.com", "wasil.zafar@gmail.com", "wasil.zafar@gmail.com", "wasil.zafar", "welcome12#", "Colloquium Info", template);
	}
	public boolean persist() {
		boolean persisted = false;
		BaseDelegate delegate = new BaseDelegate();
		persisted = delegate.persistColloquium(manager.getColloquium());
		return persisted;
	}

}
