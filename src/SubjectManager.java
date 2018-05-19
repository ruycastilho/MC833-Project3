import java.util.*;

public class SubjectManager {

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
		this.subjectList.clear();
		this.subjectList = new_subjects;
		this.listSize = new_subjects.size();
	}

	public void printAll() {
		for (Subject subject : subjectList) {
		    subject.print();
			System.out.println("");

		}
		
	}
}
