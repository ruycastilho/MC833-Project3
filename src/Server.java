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

	private SubjectManager subjectManager = new SubjectManager("subjects.ser");
	FileInputStream fis;
	ObjectInputStream ois;
    FileOutputStream fop;
    ObjectOutputStream oos;
    
    public User validatesUser(String name, String pwd) {
    	
    	try {
		    FileInputStream fis=new FileInputStream("users.ser");
		    ObjectInputStream ois=new ObjectInputStream(fis);
		    
		    System.out.println("name: '" + name + "' pwd: '" + pwd + "'");
		    
		    int size = ois.readInt();
		    while(size-- > 0){
			    User read_user = (User) ois.readObject();
			  
			    System.out.println("read_user: '" + read_user.getName() + "' pwd: '" + read_user.getPwd() + "'");
		        if(read_user != null) {
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
    	
    	return null;
    	
    }
    
	public static void main(String[] args) {
		
		Server server = new Server();
	    Registry registry = null;

		// Loading Subjects into SubjectManager
		try {
			  FileInputStream fis=new FileInputStream("subjects.ser");
			  ObjectInputStream ois=new ObjectInputStream(fis);

			  List <Subject> subjectList = new ArrayList<Subject>();
			  
			  int size = ois.readInt();
			  while(size-- > 0){
				  Subject read_subject = (Subject) ois.readObject();
				  
			      if(read_subject != null)
			    	  subjectList.add(read_subject);
			      System.out.println(read_subject.getCode());
			   }
			  
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
    	    System.setProperty("java.rmi.server.hostname","192.168.1.101");
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

		// Server object stub
	    try {
		    System.setProperty("java.rmi.server.hostname","192.168.1.101");
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
		    System.setProperty("java.rmi.server.hostname","192.168.1.101");
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


		// MUDAR COMENTARIO NO ARQUIVO DE ALGUM JEITO !!
}

// https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/hello/hello-world.html
// https://www.tutorialspoint.com/java_rmi/java_rmi_quick_guide.htm
// https://www.cs.ucsb.edu/~cappello/lectures/rmi/helloworld.shtml
// 
