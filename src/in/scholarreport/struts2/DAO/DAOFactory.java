package in.scholarreport.struts2.DAO;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class DAOFactory {
	private static DAOFactory instance;
	static {
		instance = new DAOFactory();
	}
	
	private static BaseDAO baseDao;
	private static ScholarDAO scholarDao;
	private static ReportDAO reportDao;
	private static SupervisorDAO supervisorDao;
	private static LoginDAO loginDao;


	public static DAOFactory getInstance() {
		return instance;
	}

	public BaseDAO getBaseDAO() {
		if(baseDao == null) 
			baseDao = new BaseDAO();
		return baseDao;
	}
	public ScholarDAO getScholarDAO() {
		if(scholarDao == null) 
			scholarDao = new ScholarDAO();
		return scholarDao;
	}
	
	public ReportDAO getReportDAO() {
		if(reportDao == null) 
			reportDao = new ReportDAO();
		return reportDao;
	}
	
	public SupervisorDAO getSupervisorDAO() {
		if(supervisorDao == null) 
			supervisorDao = new SupervisorDAO();
		return supervisorDao;
	}
	
	public LoginDAO getLoginDAO(){
		if(loginDao == null)
			loginDao = new LoginDAO();
		return loginDao;
	}
}
