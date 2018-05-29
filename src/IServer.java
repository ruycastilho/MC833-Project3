import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

	public User validatesUser(String name, String pwd) throws RemoteException;
	
}
