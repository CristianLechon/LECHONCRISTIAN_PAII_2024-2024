package view;

import javax.swing.*;
import connection.HibernateConnection;
import controller.CrudSchedule;
import models.Professor;
import models.Schedule;
import models.Student;
import models.Subject;

import java.awt.event.*;

public class SchedulePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton createButton;
	private JButton readButton;
	private JButton updateButton;
	private JButton deleteButton;

	private int id;
	private Subject subject;
	private Student student;
	private Professor professor;
	private String start_time;
	private String end_time;
	private String day;

	private Schedule schedule = new Schedule();
	private CrudSchedule crudSchedule = new CrudSchedule(HibernateConnection.getSessionFactory());

	public SchedulePanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());
		// Crear los botones
		createButton = new JButton("Create");
		readButton = new JButton("Read");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");

		// Agregar los botones al panel
		add(createButton);
		add(readButton);
		add(updateButton);
		add(deleteButton);
		add(Box.createHorizontalGlue());

		// Agregar los listeners a los botones
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostrar el cuadro de diálogo de entrada múltiple
				String input = JOptionPane.showInputDialog(
						"Ingrese el ID para el horario, hora inicio, final y el dia separados por comas:");

				// Dividir la entrada en partes usando la coma como separador
				String[] parts = input.split(",");

				// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
				if (parts.length >= 4) {
					id = Integer.parseInt(parts[0].trim());
					start_time = parts[1].trim();
					end_time = parts[2].trim();
					day = parts[3].trim();

					schedule.setId_schedule(id);
					schedule.setProfessor(professor);
					schedule.setStudent(student);
					schedule.setSubject(subject);
					schedule.setStart_time(start_time);
					schedule.setEnd_time(end_time);
					schedule.setDay(day);

					crudSchedule.create(schedule);
					JOptionPane.showMessageDialog(null, "Horario creado exitosamente");

				} else {
					// Si no hay suficientes partes, mostrar un mensaje de error
					JOptionPane.showMessageDialog(null,
							"Entrada no válida");
				}

			}
		});

		readButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				day = JOptionPane.showInputDialog("Ingrese su nombre");

				crudSchedule.read(schedule, day);

			}
		});

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));
				String input = JOptionPane
						.showInputDialog("Ingrese su nuevo tiempo de inicio, final y dia separados por comas:");

				// Dividir la entrada en partes usando la coma como separador
				String[] parts = input.split(",");

				// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
				if (parts.length >= 3) {
					
					start_time = parts[0].trim();
					end_time = parts[1].trim();
					day = parts[2].trim();

					schedule.setStart_time(start_time);
					schedule.setEnd_time(end_time);
					schedule.setDay(day);

					crudSchedule.update(schedule, id);

				}

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));

				crudSchedule.delete(schedule, id);
			}
		});
	}
}
