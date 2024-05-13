package controller;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import models.IDAOSubject;
import models.Subject;

public class CrudSubject implements IDAOSubject {

	private SessionFactory sessionFactory;

	public CrudSubject(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(Subject subject) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(subject);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Subject subject, int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			subject.setId(id);
			session.update(subject);
			session.getTransaction().commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Asignatura actualizada correctamente");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Subject subject, int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			subject.setId(id);
			session.delete(subject);
			session.getTransaction().commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Asignatura eliminada correctamente");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Subject read(Subject subject, String name) {
	    try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();
	        Query query = session.createQuery("FROM Subject WHERE name = :name");
	        query.setParameter("name", name);
	        List<Subject> subjects = query.list();	     
	        
	        session.getTransaction().commit();
	        session.close();
	        if (subjects.isEmpty()) {
	        	JOptionPane.showMessageDialog(null, "No se encontró la asignatura en la base de datos");
	        	return null;
	        } else {
	        	for (Subject s : subjects) {
	        		JOptionPane.showMessageDialog(null, "Número de asignaturas encontradas: " + subjects.size() + "\nAsignatura encontrada" + s);	
				}
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return subject; 
	}


}
