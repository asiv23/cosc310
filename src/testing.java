
public class testing {
	
	public static void testSQL(String url, String uid, String pw) {
		
		SQL.showDatabase(url, uid, pw);
		System.out.println("db showed");
		SQL.showTable(url, uid, pw);
		System.out.println("table showed");
		String[] list1 = {"John", "123", "1"};
		SQL.insertUser(url, uid, pw, list1);
		System.out.println("john inserted");
		SQL.selectUser(url, uid, pw, "John");
		System.out.println("john selected");
		SQL.updateUser(url, uid, pw, "John", "level", "3");
		System.out.println("john updated");
		SQL.selectUser(url, uid, pw, "John");
		System.out.println("john selected");
		SQL.deleteUser(url, uid, pw, "John");
		System.out.println("john deleted");
		String[] list2 = {"apple", "1", "kg"};
		SQL.insertInventory(url, uid, pw, list2);
		System.out.println("apple inserted");
		SQL.selectInventory(url, uid, pw, "apple");
		System.out.println("apple selected");
		SQL.updateInventory(url, uid, pw, "apple", "amount", "2");
		System.out.println("apple updated");
		SQL.selectInventory(url, uid, pw, "apple");
		System.out.println("apple seleted");
		SQL.deleteInventory(url, uid, pw, "apple");
		System.out.println("apple deleted");
		SQL.dropUser(url, uid, pw);
		System.out.println("user dropped");
		SQL.dropInventory(url, uid, pw);
		System.out.println("inventory dropped");
		SQL.dropDatabase(url, uid, pw);
		System.out.println("db dropped");
		url = "jdbc:mysql://localhost/";
		SQL.showDatabase(url, uid, pw);
		System.out.println("");
		
	}

}
