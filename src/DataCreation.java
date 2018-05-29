import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


// DataCreation class.
// Creates and updates files that store both users' and subjects' data.
public class DataCreation {
	
	// Lists of objects to be stored in files.
	List<User> users = new ArrayList<User>();
	List<Subject> subjects = new ArrayList<Subject>();

	// Sets list of users to be stored
	public void setUsersList(List<User> users) {
		this.users = users;
		
	}

	// Sets list of subjects to be stored
	public void setSubjectsList(List<Subject> subjects) {
		this.subjects = subjects;
		
	}

	// Write users list to 'users.ser'
	public void writeUsers() throws IOException{
	    String fileName= "./users.ser";

	    // File Creation
	    File yourFile = new File(fileName);
	    yourFile.createNewFile();
	    	
	    FileOutputStream fos = new FileOutputStream(fileName);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    
	    // Writing to file
	    oos.writeInt(this.users.size());
	    for(User user : this.users) {
	    	oos.writeObject(user); 

	    }
	    oos.close();
	}
	
	// Write subjects list to 'subjects.ser'
	public void writeSubjects() throws IOException{
	    String fileName= "./subjects.ser";

	    // File Creation
	    File yourFile = new File(fileName);
	    yourFile.createNewFile();

	    FileOutputStream fos = new FileOutputStream(fileName);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    
	    // Writing to file
	    oos.writeInt(this.subjects.size());
	    for(Subject subject : this.subjects) {
	    	oos.writeObject(subject); 
	    }
	    oos.close();
	}
	
	// Main function for DataCreation
	// Called initially to create a database
	public static void main(String[] args) {
		
		DataCreation data_creation = new DataCreation();
		List<User> users = new ArrayList<User>();
		List<Subject> subjects = new ArrayList<Subject>();

		// Creating users
		User user1 = new User("user1", "1", false);
		User user2 = new User("user2", "2", false);
		User user3 = new User("user3", "3", false);
		User user4 = new User("user4", "4", false);
		User user5 = new User("user5", "5", false);
		User user6 = new User("user6", "6", false);
		User user7 = new User("user7", "7", false);
		User user8 = new User("user8", "8", false);
		User user9 = new User("prof1", "1", true);
		User user10 = new User("prof2", "2", true);
		
		// Adding users to list
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);
		users.add(user10);

		// Creating subjects
		Subject subject1 = new Subject("MC102", "Algoritmos e Programação de Computadores", "IC", "CB10", "SEGUNDA 14:00 QUARTA 14:00", "Introducao a computacao", "prof1", "Boas vindas");
		Subject subject2 = new Subject("BE310", "Ciencias do Ambiente", "IB", "IB02", "QUARTA 08:00", "Ecologia", "prof2", "Ola!");
		Subject subject3 = new Subject("MC202", "Estruturas de Dados", "IC", "CB04", "TERCA 10:00 QUINTA 10:00", "Introducao a estruturas de dados", "prof1", "Arvores!");
		Subject subject4 = new Subject("MC302", "Orientacao a Objetos", "IC", "CB06", "SEGUNDA 14:00 QUARTA 14:00", "Introducao a POO", "prof1", "Java!");
		Subject subject5 = new Subject("MC558", "Analise e Projeto de Algoritmos II", "IC", "CB07", "SEGUNDA 19:00 QUARTA 21:00", "Analise e projeto de algoritmos em grafos", "prof1", "Grafos!");

		// Adding subjects to list
		subjects.add(subject1);
		subjects.add(subject2);
		subjects.add(subject3);
		subjects.add(subject4);
		subjects.add(subject5);

		// Setting lists in a DataCreation object
		data_creation.setSubjectsList(subjects);
		data_creation.setUsersList(users);
		
		// Writing to both files
		try{
			data_creation.writeUsers();
			data_creation.writeSubjects();

		}
		catch (IOException e) {
			System.out.println("Erro na escrita.");
			
		}

	}
	
	
}
