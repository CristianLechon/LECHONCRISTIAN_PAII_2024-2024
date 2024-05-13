package models;

public interface IDAOSubject {
	
	public void create(Subject subject);

	public void update(Subject subject, int id);

	public void delete(Subject subject, int id);

	public Subject read(Subject subject, String name);


}
