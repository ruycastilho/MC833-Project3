import java.io.Serializable;

public class User implements Serializable, IUser {

	private String name;
	private String password;
	private boolean is_prof = false;
	
	public User(String name, String password, boolean is_prof) {
		this.name = name;
		this.password = password;
		this.is_prof = is_prof;
		
	}
	
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
