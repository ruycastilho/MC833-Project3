import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  

public class Client {

	private User currentUser = null;
    Registry registry;

    private User login() {
    	String name, pwd;

    	System.out.println("Digite seu nome e senha, por favor.");
    	
    	System.out.println("Nome");
    	name = System.console().readLine();

    	System.out.println("Senha");
    	pwd = System.console().readLine();

    	// validar no server
    	// retornar o User se for validado
    	
    	return null;
    }
    
    
    private String showLoginMenu() {
    	String input;

    	System.out.println("Boas vindas ao Sistema de Disciplinas da UNICAMP\\nSe deseja logar digite 1. Se deseja sair, digite 2.");
    	
    	input = System.console().readLine();

    	while ( !input.equals("1") && !input.equals("2") ) {
    		
    		System.out.println("Por favor, digite 1 ou 2.");
        	input = System.console().readLine();

    	}
    	
    	return input;
    		
 
    }
   
    private String showMainMenu() {
    	String input;
    	
    	System.out.println("Menu Principal\\nDigite o número da funcionalidade que deseja:\\n1) Receber ementa de uma disciplina a partir do seu código\\n2) Receber todas as informações de uma disciplina a partir do seu código\\n3) Listar todas as informações de todas as disciplinas\\n4) Listar todos os códigos de disciplinas com seus respectivos títulos\\n5) Receber o comentário da próxima aula de uma disciplina a partir de seu código\\n6) Escrever comentário sobre próxima aula de uma de suas disciplinas\\n7) Fechar conexão");
   
    	input = System.console().readLine();

    	
    	if ( currentUser.isProf() ) {
    		
    		while ( !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6") && !input.equals("7")) {
    			
    			System.out.println("Por favor, digite números de 1 a 7.");
    	    	input = System.console().readLine();

    			
    		}

    	}
    	
    	else {

    		while ( !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6") ) {
    			
    			System.out.println("Por favor, digite números de 1 a 6.");
    	    	input = System.console().readLine();
    			
    		}
    		
    	}
    	
    	return input;
    }
    
    private String receiveCode() {
		System.out.print("Código da Operação: ");
    	String input = System.console().readLine();
		
    	return input;
    }
    
    private void descriptionByCode() {
    	String code = receiveCode();
    
        // chamada pro servidor

    }
    
    
    private void allInfoByCode() {
    	String code = receiveCode();

        // chamada pro servidor

        
    }

    private void allInfos() {
        
        // chamada pro servidor

        
    }

    private void allCodesAndTitles() {
        
        
        // chamada pro servidor

    }
    
    private void commentByCode() {
    	String code = receiveCode();

        // chamada pro servidor

        
    }
    
    private void changeCommentByCode() {
    	String code = receiveCode();

        // chamada pro servidor
        
    }
    
    
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
        
        
        // Interface
        
        while ( client.currentUser == null ) {
        	
        	if ( client.showLoginMenu().equals("1") ) {
        		client.currentUser = client.login();
        		
            }
            else {
            	// fechar conexão
            	
            }
            
            
        }
        
        String operation;
        
        while (true) {
        	
        	operation = client.showMainMenu();

        	switch (operation) {
        	
        		case "1":
        			client.descriptionByCode();
        			break;

        		case "2":
        			client.allInfoByCode();
        			break;
        	
        		case "3":
        			client.allInfos();
        			break;
        	
        		case "4":
        			client.allCodesAndTitles();
        			break;
        	
        		case "5":
        			client.commentByCode();
        			break;
        	
        		case "6":
        			if ( client.currentUser.isProf() ) {
        				client.changeCommentByCode();
        				
        			}
        			else {
        				// fechar conexão
        				
        			}
        			break;
        	
        		case "7":
    				// fechar conexão

        			break;
        	
        	}
        
        	
        }
        
	
	}

	
}


//	try {  
//	 
//       // Looking up the registry for the remote object 
//    // ISubjectManager stub = (SubjectManager) client.registry.lookup("SubjectManager"); 
//  
//       // Calling the remote method using the obtained object 
//       // stub.printMsg(); 
//       
//       // System.out.println("Remote method invoked");
//  	
//    } catch (Exception clientException) {
//       System.err.println("Ocorreu uma exceção no Cliente: " + clientException.toString()); 
//       clientException.printStackTrace(); 
//    } 
