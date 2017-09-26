package ca.wednesdaypc.lnf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

import ca.wednesdaypc.lnf.db.ConnectionFactory;
import ca.wednesdaypc.lnf.db.ConnectionWrapper;
import ca.wednesdaypc.lnf.listeners.ConnectionFactoryIniter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HelloDAO {
	private ServletContext sc;
	
	/**
	 * Proof-of-concept method
	 * @return the greeting from the database, or null if it couldn't be
	 * retrieved.
	 */
	public String getHello() {
		Statement st = null;
		ResultSet rs = null;
		
		try (ConnectionWrapper cw = ((ConnectionFactory)sc.getAttribute(
				ConnectionFactoryIniter.FACTORY_NAME)).createConnection()) {
			
			st = cw.getConn().createStatement();
			rs = st.executeQuery("SELECT * FROM hello;");
			if (!rs.first()) return null;
			return rs.getString(1);
			
		} catch (SQLException e) {
			System.out.println("SQL Exception. Code: " + e.getErrorCode());
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeJDBCObjects(null, st, rs);
		}
	}
}
