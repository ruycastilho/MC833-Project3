import java.io.Serializable;

public class Message implements Serializable {

	private String message;
	
	public Message () {
		
	}
	
	public Message (String message) {
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println(message);
		
	}

}
