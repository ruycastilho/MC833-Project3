import java.io.Serializable;

public class Subject implements Serializable {

	private String code;
	private String name;
	private String institute;
	private String room;
	private String schedule;
	private String description;
	private String professor;
	private String comment;
	
	public Subject(String code, String name, String institute, String room, String schedule, String description, String professor, String comment) {
		this.code = code;
		this.name = name;
		this.institute = institute;
		this.room = room;
		this.schedule = schedule;
		this.description = description;
		this.professor = professor;
		this.comment = comment;
		
	}
	
	public String getCode() {
		return code;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getInstitute() {
		return institute;
	}
	
	public String getName() {
		return name;
	}
	
	public String getProfessor() {
		return professor;
	}
	
	public String getSchedule() {
		return schedule;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void print() {
		System.out.println("Código: " + this.code);
		System.out.println("Título: " + this.name);
		System.out.println("Instituto: " + this.institute);
		System.out.println("Sala: " + this.room);
		System.out.println("Horário: " + this.schedule);
		System.out.println("Ementa: " + this.description);
		System.out.println("Professor: " + this.professor);
		System.out.println("Comentário: " + this.comment);
		
	}
	
}
