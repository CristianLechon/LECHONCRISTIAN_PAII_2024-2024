package asd;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JButtonExample {
    public static void main(String[] args) {
        // Crear un nuevo JFrame
        JFrame frame = new JFrame("Ejemplo de JButton");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Crear un nuevo JButton
        JButton button = new JButton("Haz clic aquí");
        
        // Agregar un ActionListener al JButton
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "¡Hola, mundo!");
        });

        // Agregar el JButton al JFrame
        frame.getContentPane().add(button);

        // Hacer visible el JFrame
        frame.setVisible(true);
    }
}

