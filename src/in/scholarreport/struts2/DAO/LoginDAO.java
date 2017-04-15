package in.scholarreport.struts2.DAO;

import in.scholarreport.struts2.util.SQLQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class LoginDAO extends BaseDAO {
	static Logger logger = Logger.getLogger(LoginDAO.class);

	public boolean authenticateScholar(String email, String credential) {
		java.sql.Connection conn = getConnection();
		ResultSet rs;
		boolean authenticated = false;
		try {
			logger.debug("Authenticating scholar "+email);
			
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.AUTHENTICATE_SCHOLAR);
			stmnt.setString(1, email);
			stmnt.setString(2, credential);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				if (rs.getInt(1) == 1)
					authenticated = true;
				break;
			}
			
			logger.debug("Is authenticated : "+authenticated);
		} catch (SQLException e) {
			logger.debug("Error while authentication scholar"+ email);
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getStackTrace());
		}

		return authenticated;
	}

	public boolean authenticateSupervisor(String email, String credential) {
		java.sql.Connection conn = getConnection();
		ResultSet rs;
		boolean authenticated = false;
		try {
			logger.debug("Authenticating supervisor "+email);
			
			PreparedStatement stmnt = conn
					.prepareStatement(SQLQueries.AUTHENTICATE_SUPERVISOR);
			stmnt.setString(1, email);
			stmnt.setString(2, credential);
			rs = stmnt.executeQuery();

			while (rs.next()) {
				if (rs.getInt(1) == 1)
					authenticated = true;
				break;
			}
		} catch (SQLException e) {
			logger.debug("Error while authentication scholar"+ email);
		}
		try {
			conn.close();
			logger.info("Connection closed successfully ");
		} catch (SQLException e) {
			logger.error("Unable to close Connection Object " + e.getStackTrace());
		}

		return authenticated;
	}

}
