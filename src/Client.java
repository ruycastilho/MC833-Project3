import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;  

// Client class
public class Client {

	private User currentUser = null;
    Registry registry;
    Scanner scanner = new Scanner(System.in);

    // Login function.
    // Receives user input regarding her/his name and password
    // Validates input through a server function
    // Returns an User object if successful, 'null' if not.
    private User login() {
    	String name, pwd;

    	// User input
    	System.out.println("Digite seu nome e senha, por favor.");
    	
    	System.out.print("Nome: ");
    	name = scanner.nextLine();

    	System.out.print("Senha: ");
    	pwd = scanner.nextLine();
    	
    	// Looks for a Server Stub
    	// Calls appropriate function through the stub that was obtained
    	IServer server_stub = null;
    	try {
            server_stub = (IServer) registry.lookup("Server"); 
            server_stub.validatesUser(name, pwd);
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup de Stub do server: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   
        try {
			return server_stub.validatesUser(name, pwd);
		} catch (RemoteException e) {
    		System.err.println("Ocorreu uma exceção no lookup de Stub do server: " + e.toString()); 
			e.printStackTrace();
		}
    
        return null;
        
    }
    
    // Shows login menu for the application
    private String showLoginMenu() {
    	String input;

    	System.out.println("Boas vindas ao Sistema de Disciplinas da UNICAMP\nSe deseja logar digite 1. Se deseja sair, digite 2.");
    	
    	input = scanner.nextLine();

    	// Repeats until a valid option is chosen
    	while (Integer.parseInt(input) != 1 && Integer.parseInt(input) != 2) {
    		
    		System.out.println("Por favor, digite 1 ou 2.");
    		input = scanner.nextLine();

    	}
    	
    	return input;
    		
 
    }
   
    // Shows the main menu for the application
    // Receives user input for the desired operation
    private String showMainMenu() {
    	String input;
    	
    	// For professors
    	if (currentUser.isProf()) {
        	
        	System.out.println("\nMenu Principal\n\nDigite o número da funcionalidade que deseja:\n1) Receber ementa de uma disciplina a partir do seu código\n2) Receber todas as informações de uma disciplina a partir do seu código\n3) Listar todas as informações de todas as disciplinas\n4) Listar todos os códigos de disciplinas com seus respectivos títulos\n5) Receber o comentário da próxima aula de uma disciplina a partir de seu código\n6) Escrever comentário sobre próxima aula de uma de suas disciplinas\n7) Fechar conexão\n");
       
        	System.out.print("Codígo da Operação: ");
       
        	input = scanner.nextLine();
        	System.out.println();

        	// Repeats until a valid option is chosen
    		while ( !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6") && !input.equals("7")) {
    			
    			System.out.println("Por favor, digite números de 1 a 7.");
    	    	input = scanner.nextLine();

    			
    		}

    	}
    	
    	// For students
    	else {
    		
        	System.out.println("\nMenu Principal\n\nDigite o número da funcionalidade que deseja:\n1) Receber ementa de uma disciplina a partir do seu código\n2) Receber todas as informações de uma disciplina a partir do seu código\n3) Listar todas as informações de todas as disciplinas\n4) Listar todos os códigos de disciplinas com seus respectivos títulos\n5) Receber o comentário da próxima aula de uma disciplina a partir de seu código\n6) Fechar conexão\n");
        	
        	System.out.print("Codígo da Operação: ");

        	input = scanner.nextLine();
        	System.out.println();
        	
        	// Repeats until a valid option is chosen
    		while ( !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6") ) {
    			
    			System.out.println("Por favor, digite números de 1 a 6.");
    	    	input = scanner.nextLine();
    			
    		}
    		
    	}
    	
    	return input;
    }
    
    // Asks for and receives the code of a subject from the user
    private String receiveCode() {
		System.out.print("Código da Disciplina: ");
    	String input = scanner.nextLine();
		
    	return input;
    }
    
    // Prints the description of the desired subject
    private void descriptionByCode() {
    	String code = receiveCode();
    
    	// Looks for a SubjectManager Stub
    	// Calls appropriate function through the stub that was obtained
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            Subject subject = manager_stub.getSubjectByCode(code);
            
            // Prints description if subject was found
            if (subject != null) {
            	subject.printDescription();
            
            }
            else {
    			System.out.println("Disciplina não encontrada.");

            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

    }
    
    // Prints all data of the desired subject
    private void allInfoByCode() {
    	String code = receiveCode();

    	// Looks for a SubjectManager Stub
    	// Calls appropriate function through the stub that was obtained
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            Subject subject = manager_stub.getSubjectByCode(code);
            
            // Prints all data
            if (subject != null) {
            	subject.printAll();
            
            }
            else {
    			System.out.println("Disciplina não encontrada.");

            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

        
    }
    
    // Prints all data for every subject that is available
    private void allInfos() {
        
      	// Looks for a SubjectManager Stub
    	// Calls appropriate function through the stub that was obtained
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            List<Subject> subject_list = manager_stub.getAll();
            
            // Prints all data
            if (subject_list != null) {
        		for (Subject subject : subject_list) {
        			subject.printAll();
        			System.out.println("");
    
        		}            
            }
            else {
    			System.out.println("Nenhuma disciplina encontrada.");

            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

        
    }

    // Prints code and title for every subject that is available
    private void allCodesAndTitles() {
        
    	// Looks for a SubjectManager Stub
    	// Calls appropriate function through the stub that was obtained
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            List<Subject> subject_list = manager_stub.getAll();
            
            // Prints code and title
            if (subject_list != null) {
        		for (Subject subject : subject_list) {
        			subject.printCodeAndTitle();
        			System.out.println("");
    
        		}            
            }
            else {
    			System.out.println("Nenhuma disciplina encontrada.");

            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

    }
    
    // Receives the comment regarding the next class of the desired subject
    private void commentByCode() {
    	String code = receiveCode();

    	// Looks for a SubjectManager Stub
    	// Calls appropriate function through the stub that was obtained
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            Subject subject = manager_stub.getSubjectByCode(code);
            
            // Prints Comment if subject was found
            if (subject != null) {
            	subject.printComment();
            
            }
            else {
    			System.out.println("Disciplina não encontrada.");

            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

    }
    
    // Changes the comment regarding the next class of the desired subject
    private void changeCommentByCode() {
    	String code = receiveCode();

    	// New comment input
    	System.out.print("Novo comentário: ");
    	String new_comment = scanner.nextLine();
		
    	// Looks for a SubjectManager Stub
    	// Calls appropriate function through the stub that was obtained
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            int result= manager_stub.changeComment(code, new_comment, this.currentUser);
            
            // Gives feedback to user, depending on the outcome of the function
            switch (result) {
            	
            	case 0:
            		System.out.println("Comentário alterado com sucesso!");
            		break;
            	case 1:
            		System.out.println("Usuário não é o docente desta disciplina.");
            		break;
            	case 2:
            		System.out.println("Disciplina não encontrada.");
            		break;
            	case 3:
            		System.out.println("Disciplina encontrada mas ocorreu uma exceção na atualização do arquivo.");
            		break;	
            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

        
    }
    
    
    // Main function for the Client.
    // Locates registry, shows the user interface and output, receives user input
	public static void main(String[] args) {
		
		Client client = new Client();
	
		// Initializing Registry
		
        try {
        	// Binding the remote object (stub) in the registry 
            client.registry = LocateRegistry.getRegistry(null); 

        }
        catch (RemoteException remoteException) {
	         System.err.println("Ocorreu uma exceção no Registry: " + remoteException.toString()); 
	         remoteException.printStackTrace();
        }
        
        
        // Interface
        Boolean keep_connected = true;

        // Login and main menu
        // Repeats until login is successful or user leaves connection
        while (client.currentUser == null && keep_connected == true) {
        	
        	// Stores user info if validated
        	if (client.showLoginMenu().equals("1")) {
        		client.currentUser = client.login();
        		
				if ( client.currentUser == null ) {
					System.out.println("Usuário não encontrado ou senha incorreta.");		    	
				
				}

            }
            else {
            	keep_connected = false;
            	
            }
        }
        
        String operation;
        
        
        // Main loop for operations.
        // Receives user input and calls the appropriate function
        while (keep_connected) {
        	
        	// Shows main menu and receives desired operation
        	operation = client.showMainMenu();

        	// Calling appropriate function
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
        				keep_connected = false;	// Closes Connection
        			}
        			break;
        	
        		case "7":
        			keep_connected = false;		// Closes Connection

        			break;
        	
        	}
        
        	
        }
        
	
	}

	
}
