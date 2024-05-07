package model_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Schedule implements IDAOSchedule {

	private int id_schedule;
	private int id_subject;
	private int id_alumn;
	private int id_professor;
	private String start_time;
	private String end_time;
	private String day;
	private String table = "Schedule";
	private PreparedStatement ps;

	public Schedule(int id_schedule,int id_subject, int id_alumn, int id_professor, String start_time, String end_time, String day) {
		this.id_schedule = id_schedule;
		this.id_subject = id_subject;
		this.id_alumn = id_alumn;
		this.id_professor = id_professor;
		this.start_time = start_time;
		this.end_time = end_time;
		this.day = day;
	}

	@Override
	public void create(Connection connection, Schedule schedule) throws SQLException {
		ps = connection.prepareStatement(
				"INSERT INTO Schedule (id, id_subject, id_alumn, id_professor, start_time,end_time, day) VALUES (?,?,?,?,?,?,?)");
		ps.setInt(1, schedule.getId_schedule());
		ps.setInt(2, schedule.getId_subject());
		ps.setInt(3, schedule.getId_alumn());
		ps.setInt(4, schedule.getId_professor());
		ps.setString(5, schedule.getStart_time());
		ps.setString(6, schedule.getEnd_time());
		ps.setString(7, schedule.getDay());
		ps.execute();
		ps.close();

	}

	@Override
	public Schedule read(Connection connection, Schedule schedule, int id_schedule) throws SQLException {
		ResultSet resultSet = null;
	    schedule = new Schedule(0,0, 0, 0, null, null, null);
	    
	    ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = ?");    
	    ps.setInt(1, id_schedule);
	    resultSet = ps.executeQuery();

	    if (resultSet.next()) {
	        
	        schedule.setId_schedule(resultSet.getInt(1));
	        schedule.setId_subject(resultSet.getInt(2));
	        schedule.setId_alumn(resultSet.getInt(3));
	        schedule.setId_professor(resultSet.getInt(4));
	        schedule.setStart_time(resultSet.getString(5));
	        schedule.setEnd_time(resultSet.getString(6));
	        schedule.setDay(resultSet.getString(7));
	        System.out.println(schedule.toString());

	    }
	    
	    ps.execute();
	    ps.close();
	    return schedule;
	}

	@Override
	public void update(Connection connection, Schedule schedule, int id_schedule) throws SQLException {
		ps = connection.prepareStatement("UPDATE " + table + " SET start_time = ?, end_time = ?, day = ? WHERE id = ?");
		ps.setString(1, schedule.getStart_time());
	    ps.setString(2, schedule.getEnd_time());
	    ps.setString(3, schedule.getDay());
	    ps.setInt(4, id_schedule);
		ps.execute();
		ps.close();

	}

	@Override
	public void delete(Connection connection, int id_schedule) throws SQLException {
		ps = connection.prepareStatement("DELETE FROM " + table + " WHERE id=?");
		ps.setInt(1, id_schedule);
		ps.execute();
		ps.close();

	}

	@Override
	public void createTable(Connection connection) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS Schedule ( " + "id INT PRIMARY KEY," + "id_subject INT,"
				+ "id_alumn INT," + "id_professor INT," + "start_time VARCHAR(30)," + "end_time VARCHAR(30),"
				+ "day VARCHAR(30)," + "FOREIGN KEY (id_subject) REFERENCES Subject(id),"
				+ "FOREIGN KEY (id_alumn) REFERENCES Alumns(id),"
				+ "FOREIGN KEY (id_professor) REFERENCES Professors(id)" + ")";
		connection.createStatement().executeUpdate(query);
		System.out.println("Tabla creada o ya existente.");

	}
	

	@Override
	public String toString() {
		return "Schedule [id_schedule=" + id_schedule + ", id_subject=" + id_subject + ", id_alumn=" + id_alumn
				+ ", id_professor=" + id_professor + ", start_time=" + start_time + ", end_time=" + end_time + ", day="
				+ day + "]";
	}

	public int getId_schedule() {
		return id_schedule;
	}

	public void setId_schedule(int id_schedule) {
		this.id_schedule = id_schedule;
	}

	public int getId_subject() {
		return id_subject;
	}

	public void setId_subject(int id_subject) {
		this.id_subject = id_subject;
	}

	public int getId_alumn() {
		return id_alumn;
	}

	public void setId_alumn(int id_alumn) {
		this.id_alumn = id_alumn;
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
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


}
