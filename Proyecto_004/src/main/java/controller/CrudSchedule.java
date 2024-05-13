package controller;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import models.IDAOSchedule;
import models.Schedule;

public class CrudSchedule implements IDAOSchedule {
	private SessionFactory sessionFactory;

	public CrudSchedule(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(Schedule schedule) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(schedule);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Schedule read(Schedule schedule, String day) {
		try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        Query query = session.createQuery("FROM Schedule WHERE day = :day");
	        query.setParameter("day", day);
	        List<Schedule> schedules = query.list();     
	        
	        session.getTransaction().commit();
	        session.close();
	        if (schedules.isEmpty()) {
	        	JOptionPane.showMessageDialog(null, "No se encontró el horario en la base de datos");
	        	return null;
	        } else {
	        	for (Schedule s : schedules) {
	        		JOptionPane.showMessageDialog(null, "Número de Horarios encontrados: " + schedules.size() + "\nHorario encontrado:" + s);
				}
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return schedule; 
	}

	@Override
	public void update(Schedule schedule, int id_schedule) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			schedule.setId_schedule(id_schedule);
			session.update(schedule);
			session.getTransaction().commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Horario actualizado correctamente");


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Schedule schedule, int id_schedule) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			schedule.setId_schedule(id_schedule);
			session.delete(schedule);
			session.getTransaction().commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Horario eliminado correctamente");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
