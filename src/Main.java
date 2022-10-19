import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String[] list = getSQLCredential();
		String url = list[0];
		String uid = list[1];
		String pw = list[2];
	
		SQL.initialize(url, uid, pw);
		url = url + "db";
		
		while(true) {
			
			int shutDown = 0;
			
			// MAIN CODE HERE
			
			// testing SQL
			testing.testSQL(url, uid, pw);
			shutDown = 1;
			
			if(shutDown == 1) {
				break;
			}
		}

	}
	
	public static String[] getSQLCredential() {
		
		Scanner s = new Scanner(System.in);
		
		String url, uid, pw;
		
		while(true) {
			
			System.out.print("Enter url: ");
			url = "jdbc:mysql://" + s.nextLine() + "/";
			System.out.print("Enter user name: ");
			uid = s.nextLine();
			System.out.print("Enter password: ");
			pw = s.nextLine();
			
			if(SQL.trySQLCredential(url, uid, pw)) {
				break;
			}
			
		}
		
		s.close();
		
		String[] list = {url, uid, pw};
		return list;
		
	}

}
