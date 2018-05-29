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

	private SubjectManager subjectManager = new SubjectManager();
	FileInputStream fis;
	ObjectInputStream ois;
    FileOutputStream fop;
    ObjectOutputStream oos;
    
    public User validatesUser(String name, String pwd) {
    	
    	try {
		    FileInputStream fis=new FileInputStream("users.ser");
		    ObjectInputStream ois=new ObjectInputStream(fis);

		    while(ois.available() > 0){
			    User read_user = (User) ois.readObject();
			  
			    System.out.println("read_user:\n" + read_user.getName() + " pwd:" + read_user.getPwd());
		        if(read_user != null) {
		    	    if (read_user.getName() == name && read_user.getPwd() == pwd) {
		    	    	ois.close();
		    	    	return read_user;
		    	    	
		    	    }
		        }
		    }
		  
		    if (ois.available() <= 0) {
				System.out.println("Usuário não encontrado ou senha incorreta.");		    	
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
			  
			  while(ois.available() > 0){
				  Subject read_subject = (Subject) ois.readObject();
				  
			      if(read_subject != null)
			    	  subjectList.add(read_subject);
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
        
		// Main Loop for Operations


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
