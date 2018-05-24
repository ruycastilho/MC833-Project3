import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.util.List;  

public interface ISubjectManager extends Remote{

	public void loadSubjects (List<Subject> new_subjects) throws RemoteException;
	
	public void printAll() throws RemoteException;

	public void printSubjectByCode(String Code) throws RemoteException;

	public void printSubjectCodeAndTitle(String Code) throws RemoteException;

	public void printSubjectByCodeAndOperation(String Code, String Operation) throws RemoteException;
	
	public void changeComment(String code, String new_comment, User professor) throws RemoteException;

}
