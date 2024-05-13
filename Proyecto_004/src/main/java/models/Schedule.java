package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule")
public class Schedule{

	@Id
	private int id_schedule;
	
	@ManyToOne
    @JoinColumn(name = "id_subject")
	private Subject subject;
	
	@ManyToOne
    @JoinColumn(name = "id_student")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name = "id_professor")
	private Professor professor;
	@Column(name = "startTime")
	private String start_time;
	@Column(name = "endTime")
	private String end_time;
	@Column(name = "day")
	private String day;
	
	public Schedule() {
	}

	public Schedule(int id_schedule,int id_subject, int id_student, int id_professor, String start_time, String end_time, String day) {
		this.id_schedule = id_schedule;
		this.start_time = start_time;
		this.end_time = end_time;
		this.day = day;
	}


	public int getId_schedule() {
		return id_schedule;
	}

	public void setId_schedule(int id_schedule) {
		this.id_schedule = id_schedule;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public void getIdProfessor() {
		professor.getId();
	}
	public void getIdSubject() {
		subject.getId();
	}
	public void getIdStudent() {
		student.getId();
	}
	
	public void setIdProfessor(int id) {
		professor.setId(id);
	}
	public void setIdSubject(int id) {
		subject.setId(id);
	}
	public void setIdStudent(int id) {
		student.setId(id);
	}

	@Override
	public String toString() {
		return "[ id_schedule=" + id_schedule + ", subject=" + subject.getId() + ", student=" + student.getId() + ", professor="
				+ professor.getId() + ", start_time=" + start_time + ", end_time=" + end_time + ", day=" + day + " ]";
	}
	
	

}
