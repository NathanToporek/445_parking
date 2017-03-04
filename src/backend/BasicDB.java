package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Parent class for all of the classes that will directly handle database connections.
 * This encapsulates the connection logic for DB objects on top of removing redundancy.
 * @author Nathanael Toporek, Charlton Smith
 * @version 1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
 */
public class BasicDB {
	private static String password = "shaybkuo";
	private static String server_name = "cssgate.insttech.washington.edu";
	/** Stores the username for this project */
	public static String username = "nat96";
	/** Stores connection information for a DB object. */
	public static Connection conn;
	/**
	 * Tries to connect to the MYSQL Database using the creds above.
	 * @throws SQLException if ur dum
	 */
	public static void create_connection() throws SQLException {
		
		Properties conn_props = new Properties();
		conn_props.put("user", username);
		conn_props.put("password", password);
		conn = DriverManager.getConnection("jdbc:" + "mysql:" + "//" 
											  + server_name + "/", conn_props);

		
		System.out.println("Connected to the server.");
	}
}
