package model_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Alumn extends Person implements IDAOPerson {

	private PreparedStatement ps;
	private String table = "Alumns";

	public Alumn(int id, String name, String lastName, int age) {
		super(id, name, lastName, age);
	}

	@Override
	public void create(Connection connection, Person person) throws SQLException {
		ps = connection.prepareStatement("INSERT INTO " + table + " (id, name, lastname, age) VALUES (?,?,?,?)");
		ps.setInt(1, person.getId());
		ps.setString(2, person.getName());
		ps.setString(3, person.getLastName());
		ps.setInt(4, person.getAge());
		ps.execute();
		ps.close();

	}

	@Override
	public Person read(Connection connection, Person person, int id_person) throws SQLException {
		ResultSet resultados = null;
		String sql = null;
		person = new Person(0, "", "", 0);

		try {
			sql = "SELECT * FROM " + table + " WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id_person);
			resultados = ps.executeQuery();

			if (resultados.next()) {
				person.setId(resultados.getInt(1));
				person.setName(resultados.getString(2));
				person.setLastName(resultados.getString(3));
				person.setAge(resultados.getInt(4));
			}

			System.out.println(person.toString());;

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public void update(Connection connection, Person person, int id_person) throws SQLException {
		ps = connection.prepareStatement("UPDATE " + table + " SET name=?, lastname=?, age=? WHERE id=?");
		ps.setString(1, person.getName());
		ps.setString(2, person.getLastName());
		ps.setInt(3, person.getAge());
		ps.setInt(4, id_person);
		ps.execute();
		ps.close();

	}

	@Override
	public void delete(Connection connection, int id_person) throws SQLException {
		ps = connection.prepareStatement("DELETE FROM " + table + " WHERE id=?");
		ps.setInt(1, id_person);
		ps.execute();
		ps.close();

	}

	@Override
	public void createTable(Connection connection) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + table + " ( " + "id INT PRIMARY KEY," + "name VARCHAR(35),"
				+ "lastname VARCHAR(30)," + "age INT" + ")";
		connection.createStatement().executeUpdate(query);
		System.out.println("Tabla creada o ya existente.");
	}

}
