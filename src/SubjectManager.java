import java.rmi.RemoteException;
import java.util.*;

public class SubjectManager implements ISubjectManager {

	private List<Subject> subjectList;
	private int listSize;

	// Default
	public SubjectManager() {
		
	}

	public SubjectManager(List<Subject> subjectList) {
		this.subjectList = subjectList;
		this.listSize = subjectList.size();
		
	}

	public void loadSubjects (List<Subject> new_subjects) {
		this.subjectList = new ArrayList<Subject>();
		this.subjectList = new_subjects;
		this.listSize = new_subjects.size();
	}

	public void printAll() {
		for (Subject subject : subjectList) {
		    subject.printAll();
			System.out.println("");

		}
		
	}

	public void printSubjectCodeAndTitle(String Code) {
		for (Subject subject : subjectList) {
		    subject.printCodeAndTitle();
			System.out.println("");

		}
		
		
	}

	public void printSubjectByCode(String code) {
		Boolean found = false;
		
		for (Subject subject : subjectList) {
		    if (subject.getCode() == code) {
		    	found = true;
		    	subject.printAll();
		    	break;
		    }

		}
		if (found == false) {
			System.out.println("Disciplina não encontrada.");

		}
		else {
			System.out.println("");
		}
		
	}
	
	public void printSubjectByCodeAndOperation(String code, String operation) {
		Boolean found = false;
		
		for (Subject subject : subjectList) {
		    if (subject.getCode() == code) {
		    	found = true;
		    	
		    	switch(operation) {
		    	
		    		case "1":
		    			subject.printDescription();
		    			break;
	
		    		case "5":
		    			subject.printComment();
		    			break;
		    	
		    	}
		    }

		}
		if (found == false) {
			System.out.println("Disciplina não encontrada.");

		}
		else {
			System.out.println("");
		}
		
	}

	public void changeComment(String code, String new_comment, User professor) {
		Boolean found = false;
			
		for (Subject subject : subjectList) {
		    if (subject.getCode() == code) {
		    	found = true;
		    	
		    	if (subject.getProfessor() != professor.getName() ) {
					System.out.println("Professor logado não é o docente desse disciplina.");
					return;
		    		
		    	}
		    	
		    	
		    	subject.setComment(new_comment);
		    }

		}
		if (found == false) {
			System.out.println("Disciplina não encontrada.");

		}
		else {
			System.out.println("");
		}
		
	}
	
}
