package ec.edu.uce.Proyecto_004;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Professor;
import models.Student;
import models.Subject;

public class App {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Professor.class);
		configuration.addAnnotatedClass(Subject.class);

		// Create Session Factory and auto-close with try-with-resources.
		try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

			Session session = sessionFactory.openSession();

			Professor student = new Professor();
			student.setId(2);
			student.setName("Marco");
			student.setLastname("Cedeño");
			student.setAge(69);

			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();

		}
	}
}
