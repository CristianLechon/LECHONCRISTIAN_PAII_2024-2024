package view;

import javax.persistence.Column;
import javax.swing.*;

import connection.HibernateConnection;
import controller.CrudSubject;
import models.Student;
import models.Subject;

import java.awt.event.*;

public class SubjectPanel extends JPanel {
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
	private String description;
	private int level;
    private Subject subject = new Subject();
    private CrudSubject crudSubject = new CrudSubject(HibernateConnection.getSessionFactory());

    public SubjectPanel() {
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
					String input = JOptionPane
							.showInputDialog("Ingrese el ID de la asignatura, nombre, descripcion y el nivel separados por comas:");

					// Dividir la entrada en partes usando la coma como separador
					String[] parts = input.split(",");

					// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
					if (parts.length >= 4) {
						id = Integer.parseInt(parts[0].trim());
						name = parts[1].trim();
						description = parts[2].trim();
						level = Integer.parseInt(parts[3].trim());

						subject.setId(id);
						subject.setName(name);
						subject.setDescription(description);;
						subject.setLevel(level);

						crudSubject.create(subject);
						JOptionPane.showMessageDialog(null, "Student creado exitosamente");

					} else {
						// Si no hay suficientes partes, mostrar un mensaje de error
						JOptionPane.showMessageDialog(null,
								"Entrada no válida. Por favor, ingrese su ID, nombre, apellido y edad separados por comas.");
					}
				
            }
        });

        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	name = JOptionPane.showInputDialog("Ingrese su nombre");

				crudSubject.read(subject, name);
            	
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));
				String input = JOptionPane
						.showInputDialog("Ingrese su nuevo nombre, apellido y edad separados por comas:");

				// Dividir la entrada en partes usando la coma como separador
				String[] parts = input.split(",");

				// Asegurarse de que haya al menos 4 partes (ID, nombre, apellido, edad)
				if (parts.length >= 3) {
					name = parts[0].trim();
					description = parts[1].trim();
					level = Integer.parseInt(parts[2].trim());

					subject.setId(id);
					subject.setName(name);
					subject.setDescription(description);
					subject.setLevel(level);

					crudSubject.update(subject, id);

				}
            		
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID"));

				crudSubject.delete(subject, id);
            }
        });
    }
}

