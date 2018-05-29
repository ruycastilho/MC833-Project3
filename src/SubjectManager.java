import java.rmi.RemoteException;
import java.util.*;

public class SubjectManager implements ISubjectManager {

	private List<Subject> subjectList;
	private int listSize;
	private String subjects_filename;
	
	// Default
	public SubjectManager(String filename) {
		this.subjects_filename = filename;
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

	public List<Subject> getAll() {
		return subjectList;
		
	}

	public Subject getSubjectByCode(String code) {
		
		for (Subject subject : subjectList) {
		    if (subject.getCode() == code) {
		    	return subject;
		    }

		}

		return null;

	}

	public int changeComment(String code, String new_comment, User professor) {
			
		for (Subject subject : subjectList) {
		    if (subject.getCode() == code) {
		    	
		    	if (subject.getProfessor() != professor.getName() ) {
					System.out.println("Professor logado não é o docente desse disciplina.");
					return 1;
		    		
		    	}
		    	else {
			    	subject.setComment(new_comment);
			    	
			    	// ALTERAR NO ARQUIVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
			    	return 0;
		    	}
		    	
		    }

		}
		return 2;
	}
	
}
