package ca.wednesdaypc.lnf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	 * @return a handle to the Connection object, or <code>null</code> if the
	 * connection could not be created.
	 */
	public Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
