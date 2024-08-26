package ec.edu.uce.ProyectoSolo.view;

import ec.edu.uce.ProyectoSolo.controller.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Component
public class JframePrincipal extends JFrame {

    @Autowired
    private Container container;

    public JframePrincipal(){
        setTitle("Ver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        int x = screenSize.width / 96;
        int y = screenSize.height / ((screenSize.height * 96) / screenSize.width);

        // Lienzo principal donde se agrega cualquier cosa
        JPanel lienzoPrincipal = new JPanel();
        lienzoPrincipal.setLayout(null);
        lienzoPrincipal.setBackground(new Color(255, 255, 255, 155));

        JLabel jlName = new JLabel("Usuario");
        jlName.setBounds(29 * x, 31 * y, 16 * x, 4 * y);
        jlName.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 50));
        jlName.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlName);

        JTextField jtName = new JTextField();
        jtName.setBounds(46 * x, 31 * y + (y / 2), 21 * x, 3 * y);
        jtName.setFont(new Font("Arial", Font.BOLD, 35));
        jtName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jtName);

        // Contraseña
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setBounds(25 * x, 37 * y, 20 * x, 4 * y);
        jlPassword.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 48));
        jlPassword.setHorizontalAlignment(JLabel.RIGHT);
        lienzoPrincipal.add(jlPassword);

        JPasswordField jpPassword = new JPasswordField();
        jpPassword.setBounds(46 * x, 37 * y + (y / 2), 21 * x, 3 * y);
        jpPassword.setFont(new Font("Consolas", Font.BOLD, 30));
        jpPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jpPassword);

        JButton mostrarContrasena = new JButton("Ver");
        mostrarContrasena.setBounds(68 * x, (37 * y) + (y / 2), 3 * x, 3 * y);
        mostrarContrasena.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mostrarContrasena.setOpaque(false);
        lienzoPrincipal.add(mostrarContrasena);

        mostrarContrasena.addActionListener(e -> {
            boolean mostrar = jpPassword.getEchoChar() == '•';
            char echoChar = mostrar ? (char) 0 : '•';
            jpPassword.setEchoChar(echoChar);
        });


        // Botón De Registrarse
        JButton jbRegistrarse = new JButton("Registrarse");
        jbRegistrarse.setBounds(33 * x, 43 * y, 14 * x, 3 * y);
        jbRegistrarse.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 30));
        jbRegistrarse.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jbRegistrarse);

        jbRegistrarse.addActionListener(e -> {
            container.startViewRegister();
        });

        // Botón De Ingresar
        JButton jbIngresar = new JButton("Ingresar");
        jbIngresar.setBounds(49 * x, 43 * y, 14 * x, 3 * y);
        jbIngresar.setFont(new Font("Consolas", Font.BOLD + Font.ITALIC, 30));
        jbIngresar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        lienzoPrincipal.add(jbIngresar);

        // Definir el ActionListener para ingresar
        ActionListener ingresarListener = e -> {
            String usuario = jtName.getText();
            String contrasena = new String(jpPassword.getPassword());
            container.authenticate(usuario,contrasena);
            jpPassword.setText("");
            jtName.setText("");
        };

        jbIngresar.addActionListener(ingresarListener);
        jtName.addActionListener(ingresarListener);
        jpPassword.addActionListener(ingresarListener);

        getContentPane().add(lienzoPrincipal);
        setVisible(true);
    }

}
