import java.rmi.Remote; 
import java.rmi.RemoteException;  

public interface IUser extends Remote {

	public String getName() throws RemoteException;
	
	public String getPwd() throws RemoteException;
	
	public boolean isProf() throws RemoteException;
}
