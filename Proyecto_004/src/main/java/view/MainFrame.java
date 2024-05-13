package view;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonPanel personPanel;
    private SubjectPanel subjectPanel;
    private SchedulePanel schedulePanel;
    private JTabbedPane tabbedPane;

    public MainFrame(String title) {
        super(title);
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void window() {
    	setLayout(new BorderLayout());
    	//setLayout(new GridLayout());

        // Crear los paneles
        personPanel = new PersonPanel();
        subjectPanel = new SubjectPanel();
        schedulePanel = new SchedulePanel();

        // Crear el tabbed pane y agregar los paneles
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Person", personPanel);
        tabbedPane.addTab("Subject", subjectPanel);
        tabbedPane.addTab("Schedule", schedulePanel);

 

        add(tabbedPane, BorderLayout.CENTER);

    }
}

