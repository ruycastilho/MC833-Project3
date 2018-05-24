import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  

public class Client {

	private User currentUser;
    Registry registry;

	public static void main(String[] args) {
		
		Client client = new Client();
	
		// Initializing Registry
		
        try {
        	// Binding the remote object (stub) in the registry 
            client.registry = LocateRegistry.getRegistry(); 

        }
        catch (RemoteException remoteException) {
	         System.err.println("Ocorreu uma exceção no Registry: " + remoteException.toString()); 
	         remoteException.printStackTrace(); 
        }
        
	    try {  

	         // Looking up the registry for the remote object 
//	         ISubjectManager stub = (SubjectManager) client.registry.lookup("SubjectManager"); 
	    
	         // Calling the remote method using the obtained object 
	         // stub.printMsg(); 
	         
	         // System.out.println("Remote method invoked");
	    	
	      } catch (Exception clientException) {
	         System.err.println("Ocorreu uma exceção no Cliente: " + clientException.toString()); 
	         clientException.printStackTrace(); 
	      } 
		
	}

	
}
