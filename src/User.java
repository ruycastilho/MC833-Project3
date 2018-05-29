import java.io.Serializable;

// User class
// Stores name, password and if user is a professor
public class User implements Serializable {
	private static final long serialVersionUID = 5943586472700425259L;
	private String name;
	private String password;
	private boolean is_prof = false;
	
	// Constructor
	public User(String name, String password, boolean is_prof) {
		this.name = name;
		this.password = password;
		this.is_prof = is_prof;
		
	}
	
	// Getters
	public String getName() {
		return this.name;
		
	}
	
	public String getPwd() {
		return this.password;
	}
	
	public boolean isProf() {
		return this.is_prof;
	}
	
}
