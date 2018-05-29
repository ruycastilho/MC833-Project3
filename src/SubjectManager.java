import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

public class SubjectManager implements ISubjectManager {

	private List<Subject> subjectList;
	private int listSize;
	
	// Default Constructor
	public SubjectManager() {
	}

	// Alternative constructor
	public SubjectManager(List<Subject> subjectList) {
		this.subjectList = subjectList;
		this.listSize = subjectList.size();
		
	}

	// Loads subjects to the Manager
	public void loadSubjects (List<Subject> new_subjects) {
		this.subjectList = new ArrayList<Subject>();
		this.subjectList = new_subjects;
		this.listSize = new_subjects.size();
	}

	// Returns all subjects as a list
	public List<Subject> getAll() {
		return subjectList;
		
	}

	// Returns desired subject by code, or null if not found
	public Subject getSubjectByCode(String code) {
		
		for (Subject subject : subjectList) {
		    if (subject.getCode().equals(code)) {
		    	return subject;
		    }

		}

		return null;

	}
	
	// Changes desired subject's comment if subject is found, according to the received code
	public int changeComment(String code, String new_comment, User professor) {
		
		// Runs through list
		for (Subject subject : subjectList) {
			
			// Looks for code match
		    if (subject.getCode().equals(code)) {
		    	
		    	// Checks if the subject's professor is the current user
		    	if ( !subject.getProfessor().equals(professor.getName()) ) {
					System.out.println("Professor logado não é o docente desse disciplina.");
					return 1;
		    		
		    	}
		    	else {
		    		// Changes comment
			    	subject.setComment(new_comment);
			    	
			    	// Updates the subjects' file through a DataCreation object
			    	DataCreation data_update = new DataCreation();
			    	data_update.setSubjectsList(this.subjectList);
			    	
			    	try {
						data_update.writeSubjects();
					} catch (IOException e) {
						System.out.println("Exceção na atualização do arquivo de disciplinas.");
						e.printStackTrace();
						return 3;
					}
			    	return 0;
		    	}
		    	
		    }

		}
		return 2;
	}
	
}
