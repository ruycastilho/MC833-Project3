import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.util.List;  

// Interface for a SubjectManager
public interface ISubjectManager extends Remote{

	public void loadSubjects (List<Subject> new_subjects) throws RemoteException;
	
	public List<Subject> getAll() throws RemoteException;

	public Subject getSubjectByCode(String Code) throws RemoteException;

	public int changeComment(String code, String new_comment, User professor) throws RemoteException;

}
