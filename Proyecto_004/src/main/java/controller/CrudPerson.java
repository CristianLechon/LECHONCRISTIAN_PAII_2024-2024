package controller;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import models.IDAOPerson;
import models.Person;

public class CrudPerson implements IDAOPerson {

	private SessionFactory sessionFactory;

	public CrudPerson(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(Person person) {

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(person);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public <T> T read(Class<T> c, String name) {
		T result = null;
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("FROM " + c.getSimpleName() + " WHERE name = :name");
			query.setParameter("name", name);
			List<T> entities = query.list();

			//System.out.println("Número de personas encontradas: " + entities.size());
			if (entities.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se encontró a la persona en la base de datos");
			} else {
				for (T entity : entities) {
					JOptionPane.showMessageDialog(null, "Número de personas encontradas: " + entities.size() + "\nPersona encontrada" + entity);				
					result = entity; // Guarda la entidad encontrada
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; // Devuelve la entidad encontrada o null si no se encontró ninguna
	}

	@Override
	public void update(Person person, int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			person.setId(id);
			session.saveOrUpdate(person);
			session.getTransaction().commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Persona actualizada correctamente");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Person person, int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			person.setId(id);
			session.delete(person);
			session.getTransaction().commit();
			session.close();
			JOptionPane.showMessageDialog(null, "Persona eliminada correctamente");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
