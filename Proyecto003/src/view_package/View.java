package view_package;

import java.sql.SQLException;

import model_package.Alumn;
import model_package.Conexion;
import model_package.Professor;
import model_package.Schedule;
import model_package.Subject;

public class View {
	
	public View() throws SQLException {
		// Creamos un alumno
				Alumn alumn = new Alumn(1, "Pepe", "Gonzales", 10);

// Metodo para crear la tabla
				alumn.createTable(Conexion.getConnection());

				// Creamos un alumno en la db
				//alumn.create(Conexion.getConnection(), alumn);
				//alumn.read(Conexion.getConnection(), alumn, 1);
				//alumn.update(Conexion.getConnection(), alumn, 1);
				//alumn.delete(Conexion.getConnection(), 1);

				// Creamos un profesor
				Professor proffesor = new Professor(23, "Marco", "Cedeño", 28);

// Metodo para crear la tabla
				// proffesor.createTable(Conexion.getConnection());

				// Creamos un profesor en la db
				//proffesor.create(Conexion.getConnection(), proffesor);
				//proffesor.read(Conexion.getConnection(), proffesor, 23);
				//proffesor.update(Conexion.getConnection(), proffesor, 23);
				//proffesor.delete(Conexion.getConnection(), 23);
				
				// Creamos una materia
				Subject subject = new Subject(12, "Matemáticas",
						"Ciencia que estudia las relaciones entre cantidades, magnitudes y propiedades, "
						+ "y las operaciones lógicas mediante las cuales se pueden deducir cantidades, "
						+ "magnitudes y propiedades desconocidas",
						2);

// Metodo para crear la tabla
				//subject.createTable(Conexion.getConnection());

				// Creamos una materia en la db
				//subject.create(Conexion.getConnection(), subject);
			    //subject.update(Conexion.getConnection(), subject, 12);
				//subject.read(Conexion.getConnection(), subject, 12);
				//subject.delete(Conexion.getConnection(), 12);

				Schedule schedule = new Schedule(100, subject.getId(), alumn.getId(), proffesor.getId(), "9:00", "11:00","Lunes");
// Metodo para crear la tabla
				// schedule.createTable(Conexion.getConnection());
				
				// Creamos un schedule en la db
				// schedule.create(Conexion.getConnection(), schedule);
				// schedule.read(Conexion.getConnection(), schedule, 100);

//				Schedule schedule2 = new Schedule(100, subject.getId(), alumn.getId(), proffesor.getId(), "7:00", "8:30", "Martes");
//				schedule.update(Conexion.getConnection(), schedule2, 100);

				//schedule.delete(Conexion.getConnection(), 100);
		
	}

}
