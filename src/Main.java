import java.util.Scanner;

public class Main {
	
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Welcome");
		
		String[] list = getSQLCredential();
		String url = list[0];
		String uid = list[1];
		String pw = list[2];
	
		SQL.initialize(url, uid, pw);
		url = url + "db";
		int loggedIn = 0;
		
		System.out.println("Temporay user info. username = admin, password = 123456.");
		User tempUser = new User("admin", "123456", 1);
		SQL.insertUser(url, uid, pw, tempUser);
		
		while(true) {
			// MAIN PROGRAM LOOP
			
			if(loggedIn == 0) {
				loggedIn = logIn(url, uid, pw); // Varying user level. 0 is not logged in, 1 is admin, 2 is employee, and so on
			}
			
			// check user level here, display different menu for different level
			int userInput = menu();
			
			if(userInput == 1) {
				addItem();
			}
			else if(userInput == 2) {
				incrItem();
			}
			else if(userInput == 3){
				decrItem();
			}
			else if(userInput == 4) {
				removeItem();
			}
			else if(userInput == 5) {
				changeUserInfo();
			}
			else if(userInput == 6) {
				loggedIn = 0; // Log out
				System.out.println("\nLogged out.");
				continue;
			}
			else {
				// -------------Temporary------------------
				SQL.dropInventory(url, uid, pw);
				SQL.dropUser(url, uid, pw);
				SQL.dropDatabase(url, uid, pw);
				// -------------Temporary------------------
				
				System.out.println("\nGoodbye");
				break; // Exit
			}
			
		}

	}
	
	public static String[] getSQLCredential() {

		String url, uid, pw;
		
		while(true) {
			
			System.out.println("\nEnter SQL credential.");
			System.out.print("Enter url: ");
			url = "jdbc:mysql://" + s.nextLine() + "/";
			System.out.print("Enter SQL user name: ");
			uid = s.nextLine();
			System.out.print("Enter SQL password: ");
			pw = s.nextLine();
			
			if(SQL.trySQLCredential(url, uid, pw)) {
				break;
			}
			else {
				System.out.println("Please try again.");
			}
			
		}
			
		String[] list = {url, uid, pw};
		return list;
		
	}
	
	public static int menu() {
			
		while(true) {
			
			System.out.println("\n");
			System.out.println("1. Add a new item to inventory");
			System.out.println("2. Increase amount of existing item");
			System.out.println("3. Decrease amount of existing item");
			System.out.println("4. Remove item from inventory");
			System.out.println("5. Change user infomation");
			System.out.println("6. Log out");
			System.out.println("7. Shut down");
			System.out.print("\nEnter 1 - 7: ");
			
			int input = s.nextInt();
			s.nextLine();// Capture the \n from user hitting enter
			
			if(input >= 1 & input <= 7) {
				return input;
			}
			else {
				System.out.println("Incorrect input. Please try again.");
			}
			
		}

	}
	
	public static int logIn(String url, String uid, String pw) {
		
		while(true) {
			
			System.out.println("\nPlease log in.");
			System.out.print("Enter user name: ");
			String username = s.nextLine();
			System.out.print("Enter password: ");
			String password = s.nextLine();
			
			User temp = SQL.selectUser(url, uid, pw, username);
			
			if(temp.getUsername() == null) {
				System.out.println(username + " does not exist. Please try again.");
				continue;
			}
			
			if(!password.equals(temp.getPassword())) {
				System.out.println("Password is incorrect. Please try again.");
				continue;
			}
			
			return temp.getLevel();
			
		}
		
	}
	
	public static void addItem() {
		System.out.println("To be implmented.");
	}
	
	public static void incrItem() {
		System.out.println("To be implmented.");
	}
	
	public static void decrItem() {
		System.out.println("To be implmented.");
	}
	
	public static void removeItem() {
		System.out.println("To be implmented.");
	}
	
	public static void changeUserInfo() {
		System.out.println("To be implmented.");
	}
	
}
