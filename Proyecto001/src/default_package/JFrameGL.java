package default_package;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jogamp.opengl.awt.GLJPanel;

public class JFrameGL {
	
	private Triangulo triangulo = new Triangulo();
	private Cuadrado cuandrado = new Cuadrado();
	private Circulo circulo = new Circulo();

	public JFrameGL() {
		// Crea un GLJPanel
        GLJPanel gljPanel = new GLJPanel();
                  
        
        JFrame ventana = new JFrame("División en dos partes");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(1, 2));
        ventana.setLocation(270, 150);
        ventana.setSize(800, 400);

        JPanel panelIzquierdo = new JPanel(); 
        panelIzquierdo.setLayout(new GridLayout(3, 0)); // 3 filas, 1 columna
       

        JButton btn1 = new JButton("Triángulo");
        btn1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 gljPanel.addGLEventListener(triangulo);
				 gljPanel.repaint();
			}
		});
        JButton btn2 = new JButton("Cuadrado");
        btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gljPanel.addGLEventListener(cuandrado);
				gljPanel.repaint();
				
			}
		});
        JButton btn3 = new JButton("Círculo");
        btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gljPanel.addGLEventListener(circulo);
				gljPanel.repaint();
			
			}
		});

        panelIzquierdo.add(btn1);                 
        panelIzquierdo.add(btn2);            
        panelIzquierdo.add(btn3);

        ventana.add(panelIzquierdo);
                   
        ventana.add(gljPanel);
                   
      
        ventana.setVisible(true);
	}
		
}
