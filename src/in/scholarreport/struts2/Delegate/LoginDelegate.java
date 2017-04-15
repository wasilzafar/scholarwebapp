package in.scholarreport.struts2.Delegate;

import in.scholarreport.struts2.DAO.DAOFactory;
import in.scholarreport.struts2.util.CommonConstants;
import in.scholarreport.struts2.util.CommonUtilities;


public class LoginDelegate extends BaseDelegate {
	
	public boolean authenticate(String email,String password,String identity){
		boolean authenticated;
		if(identity.equals(CommonConstants.SUPERVISOR) )
		authenticated = DAOFactory
				.getInstance().getLoginDAO().authenticateSupervisor(email,password);
		else
			authenticated = DAOFactory
			.getInstance().getLoginDAO().authenticateScholar(email,password);
		return authenticated;
	} 
}
