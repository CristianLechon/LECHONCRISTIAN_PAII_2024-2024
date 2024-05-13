package models;

public interface IDAOSchedule {
	
	public void create(Schedule schedule);

	public Schedule read(Schedule schedule, String name);

	public void update(Schedule schedule, int id_schedule);

	public void delete(Schedule schedule, int id_schedule);	

}
