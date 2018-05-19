import java.util.*;

public class SubjectManager {

	private List<Subject> subjectList;
	private int list_size;

	public SubjectManager() {
		
		
	}

	public SubjectManager(List<Subject> subjectList) {
		this.subjectList = subjectList;
		this.list_size = subjectList.size();
		
	}
	
	public void printAll() {
		for (Subject subject : subjectList) {
		    subject.print();
			System.out.println("");

		}
		
	}
}
