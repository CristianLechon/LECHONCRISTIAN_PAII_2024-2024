package ec.edu.uce.ProyectoSolo;

import ec.edu.uce.ProyectoSolo.view.JframePrincipal;
import ec.edu.uce.ProyectoSolo.view.LoadingScreen;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ProyectoSoloApplication {
	/*@Autowired
	private PersonService personService;
*/
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            // Muestra la interfaz de carga
            LoadingScreen loadingScreen = new LoadingScreen();

            new Thread(() -> {
                try {
                    ConfigurableApplicationContext context = new SpringApplicationBuilder(
                            ProyectoSoloApplication.class).headless(false).run(args);

                    SwingUtilities.invokeLater(() -> {
                        loadingScreen.setVisible(false);
                        try {
                            JframePrincipal jframePerson = context.getBean(JframePrincipal.class);
                            jframePerson.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al obtener el bean UIPrincipal: " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }

            }).start();
        });

    }

	/*@Override
	public void run(String... args) {
		personService.savePerson(new Person("Cristian","Lechon",23));
		personService.savePerson(new Person("Andres","Perez",20));
		personService.savePerson(new Person("Lala","Martinez",28));
		personService.savePerson(new Person("Pepe","Andino",25));
		personService.savePerson(new Person("Kevin","Pozo",22));
		personService.savePerson(new Person("Angelo","Pujota",27));
	}*/
}
