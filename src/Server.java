import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {

	private SubjectManager subjectManager = new SubjectManager();
	private User currentUser;
	FileInputStream fis;
	ObjectInputStream ois;
    FileOutputStream fop;
    ObjectOutputStream oos;
   
	public static void main(String[] args) {
		
	}

	
}


//try {
//  FileInputStream fis=new FileInputStream("C://object.ser");
//  ObjectInputStream ois=new ObjectInputStream(fis);
//  WriteObject wo=null;
//  WriteObject[] woj=new WriteObject[5];
//
//  ArrayList<WriteObject> woi=new ArrayList<>();
//  woi=(ArrayList<WriteObject>)ois.readObject();
//
//  for(int i=0;i<woi.size();i++){
//      woi.get(i).getvalues();
//  }
//}

//ArrayList<WriteObject> woi=new ArrayList<>();
//try {
//    FileOutputStream fop=new FileOutputStream("c://object.ser");
//    ObjectOutputStream oos=new ObjectOutputStream(fop);
//    woi.add(wo);
//    woi.add(wo1);
//    oos.writeObject(woi);
//
//} catch NotFoundException e) {
//}