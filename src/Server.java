import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List; 

public class Server {

	private SubjectManager subjectManager = new SubjectManager();
	FileInputStream fis;
	ObjectInputStream ois;
    FileOutputStream fop;
    ObjectOutputStream oos;
    Registry registry;
    
    public User validatesUser(String name, String pwd) {
    	try {	
		    FileInputStream fis=new FileInputStream("user.ser");
		    ObjectInputStream ois=new ObjectInputStream(fis);
		
		    Boolean keep_reading = true;
		  
		    while(keep_reading){
			    User read_user = (User) ois.readObject();
			  
		        if(read_user != null) {
		    	    if ( read_user.getName() == name && read_user.getPwd() == pwd ) {
		    	    	return read_user;
		    	    	
		    	    }     	
		        	
		        }
		        else
		    	    keep_reading = false;
		    }
		  
		    if (keep_reading == false) {
				System.out.println("Usuário não encontrado ou senha incorreta.");		    	
		    }
		    
	    
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

		// Loading Subjects into SubjectManager
		try {
			  FileInputStream fis=new FileInputStream("subjects.ser");
			  ObjectInputStream ois=new ObjectInputStream(fis);

			  List <Subject> subjectList = new ArrayList<Subject>();
			  Boolean keep_reading = true;
			  
			  while(keep_reading){
				  Subject read_subject = (Subject) ois.readObject();
				  
			      if(read_subject != null)
			    	  subjectList.add(read_subject);
			      else
			    	  keep_reading = false;
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
            server.registry = LocateRegistry.getRegistry(); 

        }
        catch (RemoteException remoteException) {
	         System.err.println("Ocorreu uma exceção no Registry: " + remoteException.toString()); 
	         remoteException.printStackTrace(); 
        }
        
		
		// Main Loop for Operations
    	User loggedUser = null;

        while (loggedUser == null) {
           	String name = null;
        	String pwd = null; 
        	
            // receber os dois inputs

            loggedUser = server.validatesUser(name, pwd);
        		
   
        }

    	// enviar loggedUser
        
        // receber operação
        
        // criar stubs pra cada operação
        

        
        
	}


		// MUDAR COMENTARIO NO ARQUIVO DE ALGUM JEITO !!
}

// https://docs.oracle.com/javase/1.5.0/docs/guide/rmi/hello/hello-world.html
// https://www.tutorialspoint.com/java_rmi/java_rmi_quick_guide.htm
// https://www.cs.ucsb.edu/~cappello/lectures/rmi/helloworld.shtml
// 



// try { 
//     // Exporting the object of implementation class  
//     // (here we are exporting the remote object to the stub) 
//     ISubjectManager subjectManagerStub = (ISubjectManager) UnicastRemoteObject.exportObject(server.subjectManager, 0);  
//     
//
//     server.registry.bind("SubjectManager", subjectManagerStub);  
//     
//     System.err.println("Servidor Pronto."); 
//     
// } catch (Exception serverException) { 
//     System.err.println("Ocorreu uma exceção no Servidor: " + serverException.toString()); 
//     serverException.printStackTrace(); 
// } 
