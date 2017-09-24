package ca.wednesdaypc.lnf.db;

import java.sql.Connection;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The entire purpose of this class is to be able to use try-with-resources
 * with a Connection.
 */
@AllArgsConstructor
public class ConnectionWrapper implements AutoCloseable {

	@Getter
	private Connection conn;
	
	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQL Exception. Code: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

}
