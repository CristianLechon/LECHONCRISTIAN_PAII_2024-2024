package ec.edu.uce.ProyectoSolo.view;

import ec.edu.uce.ProyectoSolo.controller.Container;
import ec.edu.uce.ProyectoSolo.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class JframeRegisterPerson extends JFrame {

    private Container container;

    public JframeRegisterPerson(Container container) {
        this.container = container;
        setTitle("Register Person");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        // Lienzo principal donde se agrega cualquier cosa
        JPanel lienzoPrincipal = new JPanel();
        lienzoPrincipal.setLayout(null);
        lienzoPrincipal.setBackground(new Color(255, 255, 255, 155));

        JLabel jlUser = new JLabel("Nombre");
        jlUser.setBounds(29 * x, 11 * y, 16 * x, 4 * y);
        jlUser.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 50));
        jlUser.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlUser);

        JTextField jtUser = new JTextField();
        jtUser.setBounds(46 * x, 11 * y + (y / 2), 21 * x, 3 * y);
        jtUser.setFont(new Font("Arial", Font.BOLD, 35));
        jtUser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtUser);

        // Apellido
        JLabel jlLastName = new JLabel("Apellido");
        jlLastName.setBounds(29 * x, 17 * y, 16 * x, 4 * y);
        jlLastName.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 50));
        jlLastName.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlLastName);

        JTextField jtLastName = new JTextField();
        jtLastName.setBounds(46 * x, 17 * y + (y / 2), 21 * x, 3 * y);
        jtLastName.setFont(new Font("Arial", Font.BOLD, 35));
        jtLastName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtLastName);

        // edad
        JLabel jlAge = new JLabel("Edad");
        jlAge.setBounds(29 * x, 23 * y, 16 * x, 4 * y);
        jlAge.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 48));
        jlAge.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlAge);

        JTextField jtAge = new JTextField();
        jtAge.setBounds(46 * x, 23 * y + (y / 2), 21 * x, 3 * y);
        jtAge.setFont(new Font("Arial", Font.BOLD, 35));
        jtAge.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtAge);

        // username
        JLabel jlUserName = new JLabel("Usuario");
        jlUserName.setBounds(29 * x, 29 * y, 16 * x, 4 * y);
        jlUserName.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 48));
        jlUserName.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlUserName);

        JTextField jtUserName = new JTextField();
        jtUserName.setBounds(46 * x, 29 * y + (y / 2), 21 * x, 3 * y);
        jtUserName.setFont(new Font("Arial", Font.BOLD, 35));
        jtUserName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtUserName);

        // password
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setBounds(25 * x, 35 * y, 20 * x, 4 * y);
        jlPassword.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 48));
        jlPassword.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlPassword);

        JTextField jtPassword = new JTextField();
        jtPassword.setBounds(46 * x, 35 * y + (y / 2), 21 * x, 3 * y);
        jtPassword.setFont(new Font("Arial", Font.BOLD, 35));
        jtPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtPassword);

        // Boton Cancelar
        JButton jbCancelar = new JButton("Cancelar");
        jbCancelar.setBounds(29 * x, 41 * y, 14 * x, 3 * y);
        jbCancelar.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 30));
        lienzoPrincipal.add(jbCancelar);

        jbCancelar.addActionListener(e -> {
            dispose();
        });

        // Boton Registar
        JButton jbRegistrarse = new JButton("Registrarse");
        jbRegistrarse.setBounds(49 * x, 41 * y, 16 * x, 3 * y);
        jbRegistrarse.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 30));
        lienzoPrincipal.add(jbRegistrarse);

        jbRegistrarse.addActionListener(e -> {
            if (jtUser.getText().isEmpty() || jtLastName.getText().isEmpty() || jtUserName.getText().isEmpty() || jtPassword.getText().isEmpty() || jtAge.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String name = jtUser.getText();
                String lastName = jtLastName.getText();
                String userName = jtUserName.getText();
                String password = jtPassword.getText();
                int age  = Integer.parseInt(jtAge.getText());
                Person person = new Person(name,userName,password,"Cliente",lastName,age);
                container.registerPerson(person);
                dispose();
            }
        });

        getContentPane().add(lienzoPrincipal);

    }
}
