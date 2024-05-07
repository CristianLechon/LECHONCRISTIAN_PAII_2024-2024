package model_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Subject implements IDAOSubject{
	
	private int id;
	private String name;
	private String description;
	private int level;
	
	private PreparedStatement ps;
	private String table = "Subject";
	
	public Subject(int id, String name, String description, int level) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.level = level;
	}
	
	@Override
	public void create(Connection connection, Subject subject) throws SQLException {
		ps = connection.prepareStatement("INSERT INTO " + table + " (id, name, description, level) VALUES (?,?,?,?)");
		ps.setInt(1, subject.getId());
		ps.setString(2, subject.getName());
		ps.setString(3, subject.getDescription());
		ps.setInt(4, subject.getLevel());
		ps.execute();
		ps.close();
		
	}

	@Override
	public Subject read(Connection connection, Subject subject, int id_subject) throws SQLException {
		ResultSet resultados = null;
		String sql = null;
		subject = new Subject(0, "", "", 0);

		ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE id = ?");
		ps.setInt(1, id_subject);
		resultados = ps.executeQuery();

		if (resultados.next()) {
			subject.setId(resultados.getInt(1));
			subject.setName(resultados.getString(2));
			subject.setDescription(resultados.getString(3));
			subject.setLevel(resultados.getInt(4));

			System.out.println(subject.toString());
		}
		ps.close();
		ps.close();
		return subject;
	}

	@Override
	public void update(Connection connection, Subject subject, int id_subject) throws SQLException {
		ps = connection.prepareStatement("UPDATE " + table + " SET name=?, description=?, level=? WHERE id=?");
		ps.setString(1, subject.getName());
		ps.setString(2, subject.getDescription());
		ps.setInt(3, subject.getLevel());
		ps.setInt(4, id_subject);
		ps.execute();
		ps.close();

		
	}

	@Override
	public void delete(Connection connection, int id_subject) throws SQLException {
		ps = connection.prepareStatement("DELETE FROM " + table + " WHERE id=?");
		ps.setInt(1, id_subject);
		ps.execute();
		ps.close();
		
	}

	@Override
	public void createTable(Connection connection) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + table + " ( " + "id INT PRIMARY KEY," + "name VARCHAR(35),"
				+ "description VARCHAR(250)," + "level INT" + ")";
		connection.createStatement().executeUpdate(query);
		System.out.println("Tabla creada o ya existente.");
	}
	

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", description=" + description + ", level=" + level + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	

}
