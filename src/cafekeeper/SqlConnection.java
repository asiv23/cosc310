package cafekeeper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SqlConnection {
	public static void main(String [] args) throws Exception {
		//dbConnector();
		//createTable();
		//add();
		
	}
	static Connection con = null;
	
	public static Connection dbConnector() throws Exception {	
		try {
			con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/pataromadatabase", "root", "Fradyd0!");
			
			if (con != null) {
				System.out.println("database is connected");
				
				return con;
			}
		}
		catch(Exception e) {
			System.out.println("not connected");
		}
		return con;
	}
	
}