package ca.wednesdaypc.lnf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Creates database connections.</p>
 * <p>Based heavily on the DBConnection class provided by the instructor to
 * students in PROG32758 in the Fall 2015 term.</p>
 * <p>Technically, it might not qualify as a 'Factory.'</p>
 */
public class ConnectionFactory {
	private String url;
	private String username;
	private String password;
	
	/**
	 * Providing all of the necessary information to create database
	 * connections, creates a new factory.
	 * @param driver Fully qualified name of the database driver class, e.g.
	 * "com.mysql.cj.jdbc.Driver"
	 * @param url Web address of the database
	 * @param schema Name of the schema/database to use
	 * @param username Username with which to access the database
	 * @param password Password for the given username
	 * @throws IllegalArumentException If <code>driver</code> does not correspond
	 * to a usable database driver.
	 */
	public ConnectionFactory(String driver, String url, String schema,
			String username, String password)
			throws IllegalArgumentException {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Could not find driver: " +
					driver);
		}
		
		this.url = url + schema;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Creates a database connection
	 * @return a handle to the connection, or <code>null</code> if the
	 * connection could not be created.
	 */
	public ConnectionWrapper createConnection() {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			return new ConnectionWrapper(conn);
		} catch (SQLException e) {
			System.out.println("SQL Exception. Code: " + e.getErrorCode());
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convenience method. Closes all arguments if not null, ignoring any
	 * SqlExceptions thrown in the process.
	 */
	public static void closeJDBCObjects(Connection conn, Statement stmt, ResultSet rs) {
        try {if (rs != null) rs.close();} catch (SQLException e) {}
        try {if (stmt != null) stmt.close();} catch (SQLException e) {}
        try {if (conn != null) conn.close();} catch (SQLException e) {}
    }
    
	/**
	 * Convenience method. Closes all arguments if not null, ignoring any
	 * SqlExceptions thrown in the process.
	 */
    public static void closeJDBCObjects(Connection conn, Statement stmt) {
        closeJDBCObjects(conn, stmt, null);
    }
	
}
