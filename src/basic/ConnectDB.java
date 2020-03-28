package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * Database connection management class
 * @author zjs
 *
 */
public class ConnectDB {
	private final String url = "jdbc:mysql://localhost:3306/dinnermanagedb?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
	private final String drivername = "com.mysql.jdbc.Driver";
	private final String username = "root";
	private final String password = "mysql";
	
	//Get the specified database connection
	public Connection getConnection() throws Exception {
		Class.forName(drivername).newInstance();
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	
	//Close the specified database object
	public void close(Connection con,Statement stmt) {
		try {
			if(stmt!=null)
				stmt.close();
			if(con!=null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// test connection 
		try {
			new ConnectDB().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
