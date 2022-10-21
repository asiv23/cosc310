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
									"	username    CHAR(10) NOT NULL," + 
									"   password    VARCHAR(20) NOT NULL," + 
									"   level       INTEGER NOT NULL," + 
									"   PRIMARY KEY(username))");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS inventory(" + 
									"	itemname    CHAR(10) NOT NULL," + 
									"   amount    	DOUBLE NOT NULL," + 
									"   unit        VARCHAR(5) NOT NULL," + 
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
				System.out.println("\nSQL Credential is good.");
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
				System.out.println("DATABASE db is dropped.");
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
				System.out.println("TABLE user is dropped.");
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
				System.out.println("TABLE inventory is dropped.");
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
				System.out.println("List of databases: ");
				while(rst.next()) {
					System.out.println("  -" + rst.getString("Database"));
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
				System.out.println("List of tables in db:");
				while(rst.next()) {
					System.out.println("  -" + rst.getString("Tables_in_db"));
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
				System.out.println(key + " is deleted from user.");
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
				System.out.println(key + " is deleted from inventory.");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// select one user, JUST ONE USER
	public static User selectUser(String url, String uid, String pw, String key) {
		User output = new User();
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SELECT * FROM user WHERE username = "  + "'" + key + "'");
				while(rst.next()) {
					output.setUsername(rst.getString("username"));
					output.setPassword(rst.getString("password"));
					output.setLevel(rst.getInt("level"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
		return output;
	}
	// select one item, JUST ONE ITEM
	public static Item selectInventory(String url, String uid, String pw, String key) {
		Item output = new Item();
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SELECT * FROM inventory WHERE itemname = "  + "'" + key + "'");
				while(rst.next()) {
					output.setItemname(rst.getString("itemname"));
					output.setAmount(rst.getDouble("amount"));
					output.setUnit(rst.getString("unit"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
		return output;
	}
	
	// Print users without password field
	public static void displayUser(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SELECT * FROM user");
				while(rst.next()) {
					System.out.printf("    %-10s Level: %d\n", rst.getString("username"), rst.getInt("level"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	
	// Print inventory
	public static void displayInventory(String url, String uid, String pw) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				ResultSet rst = stmt.executeQuery("SELECT * FROM inventory");
				while(rst.next()) {
					System.out.printf("    %-10s %f %-5s\n", rst.getString("itemname"), rst.getDouble("amount"), rst.getString("unit"));
				}
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	
	// insert user
	public static void insertUser(String url, String uid, String pw, User input ) {	
		String query = "INSERT INTO user VALUES (?, ?, ?)";
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				PreparedStatement pstmt = con.prepareStatement(query);)
			{
				pstmt.setString(1, input.getUsername());
				pstmt.setString(2, input.getPassword());
				pstmt.setInt(3, input.getLevel());
				pstmt.executeUpdate();
				System.out.println(input.getUsername() + " inserted into user.");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// insert inventory
	public static void insertInventory(String url, String uid, String pw, Item input ) {	
		String query = "INSERT INTO inventory VALUES (?, ?, ?)";
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				PreparedStatement pstmt = con.prepareStatement(query);)
			{
				pstmt.setString(1, input.getItemname());
				pstmt.setDouble(2, input.getAmount());
				pstmt.setString(3, input.getUnit());
				pstmt.executeUpdate();
				System.out.println(input.getItemname() + " inserted into inventory.");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// update user, change to accept user object if needed
	public static void updateUser(String url, String uid, String pw, String key, String column, String value) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("UPDATE user SET " + column + "=" + value + " WHERE username = " + "'" + key + "'");
				System.out.println(column + " of " + key + " has been updated to " + value + ".");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
	// update inventory, change to accept item object if needed
	public static void updateInventory(String url, String uid, String pw, String key, String column, String value) {
		try (Connection con = DriverManager.getConnection(url, uid, pw);
				Statement stmt = con.createStatement();)
			{
				stmt.executeUpdate("UPDATE inventory SET " + column + "=" + value + " WHERE itemname = " + "'" + key + "'");
				System.out.println(column + " of " + key + " has been updated to " + value + ".");
			} catch (SQLException e) {
				// TO DO: Error handling
				System.out.println(e);
			}
	}
}
