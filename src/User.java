
public class User {

	private String username;
	private String password;
	private int level;
	
	public User() {
		setUsername(null);
		setPassword(null);
		setLevel(0);
	}

	public User(String username, String password, int level) {
		setUsername(username);
		setPassword(password);
		setLevel(level);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
