package default_pack;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowCloseCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.nio.IntBuffer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

/**
 * @author Cristian Lechon
 * Titulo: Contenedores JAVA
 * 
 * 
 */

public class Contenedor extends JFrame {

	private JPanel panelFiguras;
	// Ventana de GLFW
	private long window;

	// Variables para almacenar los vértices de las figuras
	private static final float[] verticesCirculo = { 0.21f, 0.218f, 0.258f, 0.158f, 0.287f, 0.096f, 0.302f, 0.026f,
			0.3f, -0.043f, 0.286f, -0.098f, 0.264f, -0.148f, 0.187f, -0.238f, 0.143f, -0.267f, 0.077f, -0.293f, 0.033f,
			-0.301f, -0.014f, -0.302f, -0.096f, -0.287f, -0.148f, -0.264f, -0.203f, -0.225f, -0.241f, -0.183f, -0.278f,
			-0.119f, -0.295f, -0.066f, -0.303f, 0f, -0.301f, 0.033f, -0.287f, 0.096f, -0.264f, 0.148f, -0.225f, 0.203f,
			-0.173f, 0.249f, -0.112f, 0.281f, -0.062f, 0.296f, 0f, 0.303f, 0.058f, 0.297f, 0.096f, 0.287f, 0.13f,
			0.273f, 0.166f, 0.253f, 0.21f, 0.218f };

	private static final float[] verticesCuadrado = { -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f };

	private static final float[] verticesTriangulo = { 0.0f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f };
	
	public static void main(String[] args) {
		new Contenedor();
	}


	public Contenedor() {
		
		// Configuración inicial de la ventana
		super("Figuras Frame");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		// Creación de los botones y configuración de sus acciones
		JPanel panelBotones = new JPanel();
		JButton btnTriangulo = new JButton("Triángulo");
		JButton btnCuadrado = new JButton("Cuadrado");
		JButton btnCirculo = new JButton("Círculo");
		
		
		btnCirculo.addActionListener(e -> dibujarCirculo());
		btnCuadrado.addActionListener(e -> dibujarCuadrado());
		btnTriangulo.addActionListener(e -> dibujarTriangulo());

		panelBotones.add(btnCirculo);
		panelBotones.add(btnCuadrado);
		panelBotones.add(btnTriangulo);

		panelFiguras = new JPanel();
		panelFiguras.setBackground(Color.WHITE);

		setLayout(new BorderLayout());
		add(panelBotones, BorderLayout.CENTER);

		setVisible(true);
	}

	// Método para dibujar un círculo
	private void dibujarCirculo() {
		panelFiguras.removeAll();
		init();
		circulo();
		panelFiguras.revalidate();
		panelFiguras.repaint();
	}

	// Método para dibujar un cuadrado
	private void dibujarCuadrado() {
		panelFiguras.removeAll();
		init();
		cuadrado();
		panelFiguras.revalidate();
		panelFiguras.repaint();
	}
	
	// Método para dibujar un triángulo
	private void dibujarTriangulo() {
		panelFiguras.removeAll();
		init();
		triangulo();
		panelFiguras.revalidate();
		panelFiguras.repaint();
	}

	// Método para dibujar un triángulo con OpenGL
	private void triangulo() {

		GL.createCapabilities();

		glClearColor(0.5f, 0.5f, 0.5f, 0.0f);

		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glBegin(GL_TRIANGLE_FAN);

			glColor3f(0.0f, 1.0f, 1.0f);

			for (int i = 0; i < verticesTriangulo.length; i += 2) {
				glVertex2f(verticesTriangulo[i], verticesTriangulo[i + 1]);
			}

			glEnd();

			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	// Método para dibujar un círculo con OpenGL
	private void circulo() {

		GL.createCapabilities();

		glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glBegin(GL_TRIANGLE_FAN);
			glColor3f(1.0f, 0.0f, 0.0f); 
			for (int i = 0; i < verticesCirculo.length; i += 2) {
				glVertex2f(verticesCirculo[i], verticesCirculo[i + 1]);
			}

			glEnd();

			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	// Método para dibujar un cuadrado con OpenGL
	private void cuadrado() {

		GL.createCapabilities();

		glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glBegin(GL_TRIANGLE_FAN);

			glColor3f(0.0f, 1.0f, 0.0f);

			for (int i = 0; i < verticesCuadrado.length; i += 2) {
				glVertex2f(verticesCuadrado[i], verticesCuadrado[i + 1]);
			}

			glEnd();

			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	// Inicialización de la ventana de GLFW
	private void init() {

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		window = glfwCreateWindow(800, 600, "Tarea Programacion Avanzada II", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true); 
		});
		
		 glfwSetWindowCloseCallback(window, window -> {
		       
		        glfwDestroyWindow(window);
		        
		    });

		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}

		glfwMakeContextCurrent(window);

		glfwSwapInterval(1);

		glfwShowWindow(window);
	}


}
