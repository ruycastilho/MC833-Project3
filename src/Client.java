import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;  

public class Client {

	private User currentUser = null;
    Registry registry;
    Scanner scanner = new Scanner(System.in);

    private User login() {
    	String name, pwd;

    	System.out.println("Digite seu nome e senha, por favor.");
    	
    	System.out.println("Nome");
    	name = scanner.nextLine();

    	System.out.println("Senha");
    	pwd = scanner.nextLine();
    	
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
    
    
    private String showLoginMenu() {
    	String input;

    	System.out.println("Boas vindas ao Sistema de Disciplinas da UNICAMP\\nSe deseja logar digite 1. Se deseja sair, digite 2.");
    	
    	input = scanner.nextLine();

    	while (Integer.parseInt(input) != 1 && Integer.parseInt(input) != 2) {
    		
    		System.out.println("Por favor, digite 1 ou 2.");
    		input = scanner.nextLine();

    	}
    	
    	return input;
    		
 
    }
   
    private String showMainMenu() {
    	String input;
    	
    	System.out.println("Menu Principal\\nDigite o número da funcionalidade que deseja:\\n1) Receber ementa de uma disciplina a partir do seu código\\n2) Receber todas as informações de uma disciplina a partir do seu código\\n3) Listar todas as informações de todas as disciplinas\\n4) Listar todos os códigos de disciplinas com seus respectivos títulos\\n5) Receber o comentário da próxima aula de uma disciplina a partir de seu código\\n6) Escrever comentário sobre próxima aula de uma de suas disciplinas\\n7) Fechar conexão");
   
    	input = scanner.nextLine();

    	
    	if (currentUser.isProf()) {
    		
    		while ( !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6") && !input.equals("7")) {
    			
    			System.out.println("Por favor, digite números de 1 a 7.");
    	    	input = scanner.nextLine();

    			
    		}

    	}
    	
    	else {

    		while ( !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6") ) {
    			
    			System.out.println("Por favor, digite números de 1 a 6.");
    	    	input = scanner.nextLine();
    			
    		}
    		
    	}
    	
    	return input;
    }
    
    private String receiveCode() {
		System.out.print("Código da Operação: ");
    	String input = scanner.nextLine();
		
    	return input;
    }
    
    private void descriptionByCode() {
    	String code = receiveCode();
    
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            Subject subject = manager_stub.getSubjectByCode(code);
            
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
    
    
    private void allInfoByCode() {
    	String code = receiveCode();

    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            Subject subject = manager_stub.getSubjectByCode(code);
            
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

    private void allInfos() {
        
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            List<Subject> subject_list = manager_stub.getAll();
            
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

    private void allCodesAndTitles() {
        
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            List<Subject> subject_list = manager_stub.getAll();
            
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
    
    private void commentByCode() {
    	String code = receiveCode();

    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            Subject subject = manager_stub.getSubjectByCode(code);
            
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
    
    private void changeCommentByCode() {
    	String code = receiveCode();

    	System.out.print("Novo comentário: ");
    	String new_comment = scanner.nextLine();
		
    	ISubjectManager manager_stub = null;
    	try {
    		manager_stub = (ISubjectManager) registry.lookup("SubjectManager"); 
            int result= manager_stub.changeComment(code, new_comment, this.currentUser);
            
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
            		
            }
            
    	}
    	catch (Exception e) {
    		System.err.println("Ocorreu uma exceção no lookup ou execução de Stub do Manager: " + e.toString()); 
    		e.printStackTrace();     	
    	}
   

        
    }
    
    
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
        while (client.currentUser == null) {
        	
        	if (client.showLoginMenu().equals("1")) {
        		client.currentUser = client.login();
        		
				if ( client.currentUser == null ) {
					System.out.println("Usuário não encontrado ou senha incorreta.");		    	
				
				}

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

//https://stackoverflow.com/questions/9620275/classcastexception-proxy0-cannot-be-cast-error-while-creating-simple-rmi-app
//	https://stackoverflow.com/questions/24325928/running-socket-programming-code-in-eclipse
//https://stackoverflow.com/questions/464687/running-rmi-server-classnotfound

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
