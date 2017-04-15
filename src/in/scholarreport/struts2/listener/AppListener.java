package in.scholarreport.struts2.listener;


import in.scholarreport.struts2.scheduler.ReportScheduler;
import in.scholarreport.struts2.util.CommonUtilities;
import in.scholarreport.struts2.util.ConfigParser;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class AppListener implements ServletContextListener, HttpSessionListener, ServletRequestListener  {
	static Logger logger = Logger.getLogger(AppListener.class);
	private static InitialContext ctx = null;
    private static final String ATTRIBUTE_NAME = "com.phd.SessionCounter";
    private Map<HttpSession, String> sessions = new ConcurrentHashMap<HttpSession, String>();
    
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Stopping Report Scheduler");
		//ReportScheduler.stop();
	}

	public void contextInitialized(ServletContextEvent sce) {
		logger.info("-------------------Starting Application -------------------");
		ServletContext servletContext = sce.getServletContext();
		sce.getServletContext().setAttribute(ATTRIBUTE_NAME, this);
		DataSource datasrc = null;
		try {
			if (ctx == null) {
				ctx = new InitialContext();
			}
			datasrc = (DataSource) ctx.lookup(CommonUtilities.DATASOURCENAME2);
			servletContext.setAttribute("dataSource", datasrc);
			logger.info("Starting Scheduler ... ");
			//ReportScheduler.start();
			logger.info("Started scheduler successfully.");
			logger.info("Parsing SR configuration file ....");
			ConfigParser.startParsing();
			logger.info("Finished SR config parsing .....");
		
		} catch (Exception e) {
			logger.info("Error starting listner !  " + e);
		}

	}

	public void requestDestroyed(ServletRequestEvent arg0) {
		
	}

	public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        HttpSession session = request.getSession();
        if (session.isNew()) {
            sessions.put(session, request.getRemoteAddr());
        }
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		sessions.remove(arg0.getSession());
		
	}
	
	public static AppListener getInstance(ServletContext context) {
        return (AppListener) context.getAttribute(ATTRIBUTE_NAME);
    }

    public int getCount(String remoteAddr) {
        return Collections.frequency(sessions.values(), remoteAddr);
    }

}
