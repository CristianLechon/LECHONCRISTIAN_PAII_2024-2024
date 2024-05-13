package view;

import javax.swing.*;

import connection.HibernateConnection;
import controller.CrudPerson;
import models.Professor;
import models.Student;

import java.awt.event.*;

public class PersonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton createButton;
	private JButton readButton;
	private JButton updateButton;
	private JButton deleteButton;

	private int id;
	private String name;
	private String lastname;
	private int age;

	private Student student = new Student();
	private Professor professor = new Professor();

	public PersonPanel() {
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

		CrudPerson crudPerson = new CrudPerson(HibernateConnection.getSessionFactory());

		// Agregar los listeners a los botones
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input1 = Integer
						.parseInt(JOptionPane.showInputDialog("Que desea crear:\n 1.Student \n2.Professor"));

				if (input1 == 1) {
					// Mostrar el cuadro de diálogo de entrada múltiple
					String input = JOptionPane
							.showInputDialog("Ingrese su ID, nombre, apellido y edad separados por comas:");

					// Dividir la entrada en partes usando la coma como separador
					String[] parts = input.split(",");

					// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
					if (parts.length >= 4) {
						id = Integer.parseInt(parts[0].trim());
						name = parts[1].trim();
						lastname = parts[2].trim();
						age = Integer.parseInt(parts[3].trim());

						student.setId(id);
						student.setName(name);
						student.setLastname(lastname);
						student.setAge(age);

						crudPerson.create(student);
						JOptionPane.showMessageDialog(null, "Student creado exitosamente");

					} else {
						// Si no hay suficientes partes, mostrar un mensaje de error
						JOptionPane.showMessageDialog(null,
								"Entrada no válida. Por favor, ingrese su ID, nombre, apellido y edad separados por comas.");
					}

				} else if (input1 == 2) {

					// Mostrar el cuadro de diálogo de entrada múltiple
					String input = JOptionPane
							.showInputDialog("Ingrese su ID, nombre, apellido y edad separados por comas:");

					// Dividir la entrada en partes usando la coma como separador
					String[] parts = input.split(",");

					// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
					if (parts.length >= 4) {
						id = Integer.parseInt(parts[0].trim());
						name = parts[1].trim();
						lastname = parts[2].trim();
						age = Integer.parseInt(parts[3].trim());

						professor.setId(id);
						professor.setName(name);
						professor.setLastname(lastname);
						professor.setAge(age);

						crudPerson.create(professor);
						JOptionPane.showMessageDialog(null, "Professor creado exitosamente");

					} else {
						// Si no hay suficientes partes, mostrar un mensaje de error
						JOptionPane.showMessageDialog(null,
								"Entrada no válida. Por favor, ingrese su ID, nombre, apellido y edad separados por comas.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Entrada no válida.");
				}

			}
		});

		readButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input1 = Integer.parseInt(JOptionPane.showInputDialog("Quien es usted:\n 1.Student \n2.Professor"));
				if (input1 == 1) {
					name = JOptionPane.showInputDialog("Ingrese su nombre");

					crudPerson.read(Student.class, name);

				} else if (input1 == 2) {
					name = JOptionPane.showInputDialog("Ingrese su nombre");

					crudPerson.read(Professor.class, name);
				}

			}
		});

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int in = Integer.parseInt(JOptionPane
						.showInputDialog("Para actualizar debe especificar quien es usted:\n 1.Student \n2.Professor"));

				if (in == 1) {
					int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));
					String input = JOptionPane
							.showInputDialog("Ingrese su nuevo nombre, apellido y edad separados por comas:");

					// Dividir la entrada en partes usando la coma como separador
					String[] parts = input.split(",");

					// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
					if (parts.length >= 3) {
						name = parts[0].trim();
						lastname = parts[1].trim();
						age = Integer.parseInt(parts[2].trim());

						student.setId(id);
						student.setName(name);
						student.setLastname(lastname);
						student.setAge(age);

						crudPerson.update(student, id);

					}

				} else if (in == 2) {
					int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));
					String input = JOptionPane
							.showInputDialog("Ingrese su nuevo nombre, apellido y edad separados por comas:");

					// Dividir la entrada en partes usando la coma como separador
					String[] parts = input.split(",");

					// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
					if (parts.length >= 3) {
						name = parts[0].trim();
						lastname = parts[1].trim();
						age = Integer.parseInt(parts[2].trim());

						professor.setId(id);
						professor.setName(name);
						professor.setLastname(lastname);
						professor.setAge(age);

						crudPerson.update(professor, id);
					}

				}

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int in = Integer.parseInt(JOptionPane
						.showInputDialog("Que deas eliminar:\n 1.Student \n2.Professor"));
				if(in == 1) {
					int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));
					crudPerson.delete(student, id);
				}else if(in ==2) {
					int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));
					crudPerson.delete(professor, id);
				}
			}
		});
	}
}
