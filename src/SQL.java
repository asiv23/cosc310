import java.sql.*;

public class SQL {
	
	// Initialize
	public static void initialize(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
		url = url + "db";
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user(" + 
									"	username    CHAR(10)," + 
									"   password    VARCHAR(20)," + 
									"   level       INTEGER," + 
									"   PRIMARY KEY(username))");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS inventory(" + 
									"	itemname    CHAR(10)," + 
									"   amount    	FLOAT," + 
									"   unit        VARCHAR(5)," + 
									"   PRIMARY KEY(itemname))");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	
	// try SQL credential
	public static boolean trySQLCredential(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);)
			{
				System.out.println("SQL Credential is good.");
				return true;
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
				return false;
			}
	}
	// drop database
	public static void dropDatabase(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("DROP DATABASE db");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// drop user
	public static void dropUser(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("DROP TABLE user");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// drop inventory
	public static void dropInventory(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("DROP TABLE inventory");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// show database
	public static void showDatabase(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SHOW DATABASES");
				while(rst.next()) {
					System.out.println(rst.getString("Database"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	
	// show table
	public static void showTable(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SHOW TABLES");
				while(rst.next()) {
					System.out.println(rst.getString("Tables_in_db"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// delete user
	public static void deleteUser(String url, String uid, String pw, String key) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("DELETE FROM user WHERE username = " + "'" + key + "'");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// delete inventory
	public static void deleteInventory(String url, String uid, String pw, String key) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("DELETE FROM inventory WHERE itemname = " + "'" + key + "'");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// select user
	public static void selectUser(String url, String uid, String pw, String key) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SELECT * FROM user WHERE username = "  + "'" + key + "'");
				while(rst.next()) {
					System.out.println(rst.getString("username") + ", " + rst.getString("password") + ", " + rst.getInt("level"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// select inventory
	public static void selectInventory(String url, String uid, String pw, String key) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SELECT * FROM inventory WHERE itemname = "  + "'" + key + "'");
				while(rst.next()) {
					System.out.println(rst.getString("itemname") + ", " + rst.getFloat("amount") + ", " + rst.getString("unit"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// insert user
	public static void insertUser(String url, String uid, String pw, String[] list ) {	
		String query = "INSERT INTO user VALUES (?, ?, ?)";
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				PreparedStatement pstmt = con.prepareStatement(query);)
			{
				pstmt.setString(1, list[0]);
				pstmt.setString(2, list[1]);
				pstmt.setString(3, list[2]);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// insert inventory
	public static void insertInventory(String url, String uid, String pw, String[] list ) {	
		String query = "INSERT INTO inventory VALUES (?, ?, ?)";
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				PreparedStatement pstmt = con.prepareStatement(query);)
			{
				pstmt.setString(1, list[0]);
				pstmt.setString(2, list[1]);
				pstmt.setString(3, list[2]);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// update user
	public static void updateUser(String url, String uid, String pw, String key, String column, String value) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("UPDATE user SET " + column + "=" + value + " WHERE username = " + "'" + key + "'");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// update inventory
	public static void updateInventory(String url, String uid, String pw, String key, String column, String value) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("UPDATE inventory SET " + column + "=" + value + " WHERE itemname = " + "'" + key + "'");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
}
