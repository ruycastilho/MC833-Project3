import java.rmi.Remote; 
import java.rmi.RemoteException;  

public interface IMessage extends Remote {

	public void setMessage(String message) throws RemoteException;
	
	public void printMessage() throws RemoteException;

}
