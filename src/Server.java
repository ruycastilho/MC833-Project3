import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List; 

public class Server implements Serializable, IServer {

	// Server's SubjectManager: Stores subjects data
	private SubjectManager subjectManager = new SubjectManager();
	
	// File I/O variables
	FileInputStream fis;
	ObjectInputStream ois;
    FileOutputStream fop;
    ObjectOutputStream oos;
    
    // User Validation: checks with Name and Password match the ones stored in 'users.ser'.
    // Return: User object if found, 'null' if not.
    public User validatesUser(String name, String pwd) {
    	
    	try {
    		// File Input
		    FileInputStream fis=new FileInputStream("users.ser");
		    ObjectInputStream ois=new ObjectInputStream(fis);
		    
		    // Looks for match inside file
		    int size = ois.readInt();
		    while(size-- > 0){
			    User read_user = (User) ois.readObject();
			  
			    if(read_user != null) {
			    	
			    	// If match is found, returns user.
		    	    if (read_user.getName().equals(name) && read_user.getPwd().equals(pwd)) {
		    	    	ois.close();
		    	    	return read_user;
		    	    }
		        }
		    }
		    
	    	ois.close();

		}
		catch (IOException fileExceptiom) {
			System.out.println("Erro na escrita, IOException.");
			fileExceptiom.printStackTrace();
		}
		catch (ClassNotFoundException classException) {
			System.out.println("Erro na escrita, ClassNotFoundException.");
			classException.printStackTrace();
		}
    	
    	// If match isn't found, returns null
    	return null;
    	
    }
    
    // Main server function.
    // Initializes variables, loads subjects to SubjectManager, create stubs, loads registry.
	public static void main(String[] args) {
		
		Server server = new Server();
	    Registry registry = null;

		// Loading Subjects into SubjectManager
		try {
			  FileInputStream fis=new FileInputStream("subjects.ser");
			  ObjectInputStream ois=new ObjectInputStream(fis);

			  List <Subject> subjectList = new ArrayList<Subject>();
			  
			  // Reading file
			  int size = ois.readInt();
			  while(size-- > 0){
				  Subject read_subject = (Subject) ois.readObject();
				  
			      if(read_subject != null)
			    	  subjectList.add(read_subject);
			   }
			  
			  // Loading subjects
			  server.subjectManager.loadSubjects(subjectList);
			  
		}
		catch (IOException fileExceptiom) {
			System.out.println("Erro na escrita, IOException.");
			fileExceptiom.printStackTrace();
		}
		catch (ClassNotFoundException classException) {
			System.out.println("Erro na escrita, ClassNotFoundException.");
			classException.printStackTrace();
		}
			
		
		// Initializing Registry
		
        try {
        	// Binding the remote object (stub) in the registry 
            registry = LocateRegistry.getRegistry(); 

        }
        catch (RemoteException remoteException) {
	         System.err.println("Ocorreu uma exceção no Registry: " + remoteException.toString()); 
	         remoteException.printStackTrace(); 
        }
        
        
		// Server object stub
        try {
    	    System.setProperty("java.rmi.server.hostname","127.0.0.1");
            IServer server_stub = (IServer) UnicastRemoteObject.exportObject(server, 0);  

            registry.bind("Server", server_stub);  
        	
        }
        catch (RemoteException e) {
        	 System.err.println("Ocorreu uma exceção na criação de Stub do server: " + e.toString()); 
             e.printStackTrace(); 
        }
        catch (AlreadyBoundException e) {
       	 System.err.println("Ocorreu uma exceção no bind de Stub do server: " + e.toString()); 
            e.printStackTrace(); 
        }

        
        // SubjectManager object stub
	    try {
		    System.setProperty("java.rmi.server.hostname","127.0.0.1");
	        ISubjectManager manager_stub = (ISubjectManager) UnicastRemoteObject.exportObject(server.subjectManager, 0);  
	
	        registry.bind("SubjectManager", manager_stub);  
	    	
	    }
	    catch (RemoteException e) {
	    	 System.err.println("Ocorreu uma exceção na criação de Stub do Manager: " + e.toString()); 
	         e.printStackTrace(); 
	    }
	    catch (AlreadyBoundException e) {
	   	 System.err.println("Ocorreu uma exceção no bind de Stub do Manager: " + e.toString()); 
	        e.printStackTrace(); 
	   }
	    
	}

}
