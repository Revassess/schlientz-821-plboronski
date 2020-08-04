package com.revature.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Revature
 *
 *         This is a paceholder class to hold the configurations of your db
 *         connection. You should change the url, username, and password. DO NOT
 *         CHANGE THE MODIFIERS OR THE NAMES OF THE VARIABLES. These are used to
 *         test your db schema.
 */
public class ConnectionUtil {
	// for singleton instance
	private static ConnectionUtil cu;
	private static Connection conn = null;

	// add your jdbc url
	public static final String URL = "jdbc:oracle:thin:@boronskidb.c8wlv3fairff.us-east-2.rds.amazonaws.com:1521:ORCL";
	// add your jdbc username
	public static final String USERNAME = "plboronski";
	// add your jdbc password
	public static final String PASSWORD = "password";
	// name of the created stored procedure in tier 3
	public static final String TIER_3_PROCEDURE_NAME = "";
	// name of the created sequence in tier 3
	public static final String TIER_3_SEQUENCE_NAME = "seq";

	// implement this method to connect to the db and return the connection object
	public Connection connect() {
		try {
			
			if(conn == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				
				String endpoint = "jdbc:oracle:thin:@boronskidb.c8wlv3fairff.us-east-2.rds.amazonaws.com:1521:ORCL";
				String username = "plboronski";
				String password = "password";
				
				conn = DriverManager.getConnection(endpoint, username, password);
				return conn;
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// implement this method with a callable statement that calls the absolute value
	// sql function
	public long callAbsoluteValueFunction(long value) {
		String sql = "CALL getAbs(?,?)";
			
		try {
			CallableStatement cs = cu.connect().prepareCall(sql);
			
			cs.setLong(1, value);
			cs.registerOutParameter(2, java.sql.Types.DECIMAL);
			
			cs.executeUpdate();
			
			return cs.getLong(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	// make the class into a singleton
	private ConnectionUtil() {
		super();
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String endpoint = "jdbc:oracle:thin:@boronskidb.c8wlv3fairff.us-east-2.rds.amazonaws.com:1521:ORCL";
			String username = "plboronski";
			String password = "password";

			conn = DriverManager.getConnection(endpoint, username, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ConnectionUtil getInstance() {
		if (cu == null) {
			cu = new ConnectionUtil();
		}
		return cu;
	}
}
