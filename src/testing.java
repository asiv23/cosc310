
public class testing {
	
	public static void testSQL(String url, String uid, String pw) {
		
		SQL.showDatabase(url, uid, pw);
		SQL.showTable(url, uid, pw);
		User user = new User("John", "123", 1);
		SQL.insertUser(url, uid, pw, user);
		User out1 = SQL.selectUser(url, uid, pw, "John");
		System.out.println(out1.getUsername() + " " + out1.getPassword() + " " + out1.getLevel());
		SQL.updateUser(url, uid, pw, "John", "level", "3");
		out1 = SQL.selectUser(url, uid, pw, "John");
		System.out.println(out1.getUsername() + " " + out1.getPassword() + " " + out1.getLevel());
		SQL.deleteUser(url, uid, pw, "John");
		Item item = new Item("apple", 1, "kg");
		SQL.insertInventory(url, uid, pw, item);
		Item out2 = SQL.selectInventory(url, uid, pw, "apple");
		System.out.println(out2.getItemname() + " " + out2.getAmount() + " " + out2.getUnit());
		SQL.updateInventory(url, uid, pw, "apple", "amount", "2");
		out2 = SQL.selectInventory(url, uid, pw, "apple");
		System.out.println(out2.getItemname() + " " + out2.getAmount() + " " + out2.getUnit());
		SQL.deleteInventory(url, uid, pw, "apple");
		SQL.dropUser(url, uid, pw);
		SQL.dropInventory(url, uid, pw);
		SQL.dropDatabase(url, uid, pw);
		url = "jdbc:mysql://localhost/";
		SQL.showDatabase(url, uid, pw);

	}

}
