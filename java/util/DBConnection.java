package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection dbconnect() throws SQLException{
		//Register Driver

		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		//Connection (url,user,pass)
	 	
		String url ="jdbc:mysql://localhost:3306/mybook.com";
		String user ="root";
		String password ="";
		
		
		Connection conn = DriverManager.getConnection(url , user, password);
		
		
		return conn;
	}
}
