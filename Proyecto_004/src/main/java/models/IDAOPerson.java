package models;

public interface IDAOPerson {

	public void create(Person person);

	public void update(Person person, int id);

	public void delete(Person person, int id);

	<T> T read(Class<T> entityClass, String name);

}
