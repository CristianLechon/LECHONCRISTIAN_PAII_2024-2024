package asd;

import javax.swing.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.IntBuffer;

public class FigurasFrame {

    private long window;
    private JButton circleButton;
    private JButton squareButton;
    private JButton triangleButton;

    public void run() {
    	init();

        JFrame frame = new JFrame("Figuras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        JPanel panel = new JPanel();
        frame.add(panel);

        circleButton = new JButton("Círculo");
        squareButton = new JButton("Cuadrado");
        triangleButton = new JButton("Triángulo");

        panel.add(circleButton);
        panel.add(squareButton);
        panel.add(triangleButton);

        circleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {          
                circulo();
            }
        });

        squareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           
                cuadrado();
            }
        });

        triangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                triangulo();
            }
        });

        frame.setVisible(true);
        
        
     
    }

private void init() {
		
		GLFWErrorCallback.createPrint(System.err).set();
	
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 

		// Create the window
		window = glfwCreateWindow(800, 600, "Tarea Programacion Avanzada II", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated
		// or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
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

	private static final float[] verticesCirculo = { 0.21f, 0.218f, 0.258f, 0.158f, 0.287f, 0.096f, 0.302f, 0.026f, // 1

			0.3f, -0.043f, 0.286f, -0.098f, 0.264f, -0.148f, 0.187f, -0.238f, // 2

			0.143f, -0.267f, 0.077f, -0.293f, 0.033f, -0.301f, -0.014f, -0.302f, // 3

			-0.096f, -0.287f, -0.148f, -0.264f, -0.203f, -0.225f, -0.241f, -0.183f, // 4

			-0.278f, -0.119f, -0.295f, -0.066f, -0.303f, 0f, -0.301f, 0.033f, // 5

			-0.287f, 0.096f, -0.264f, 0.148f, -0.225f, 0.203f, -0.173f, 0.249f, // 6

			-0.112f, 0.281f, -0.062f, 0.296f, 0f, 0.303f, 0.058f, 0.297f, // 7

			0.096f, 0.287f, 0.13f, 0.273f, 0.166f, 0.253f, 0.21f, 0.218f // 8

	};

	private void circulo() {

		GL.createCapabilities();

		glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glBegin(GL_TRIANGLE_FAN);
			glColor3f(1.0f, 0.0f, 0.0f); // Rojo
			for (int i = 0; i < verticesCirculo.length; i += 2) {
				glVertex2f(verticesCirculo[i], verticesCirculo[i + 1]);
			}

			glEnd();

			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	private static final float[] verticesCuadrado = { -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f

	};

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

	private static final float[] verticesTriangulo = { 0.0f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f

	};
	
	private void triangulo() {

		GL.createCapabilities();

		glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FigurasFrame().run();

	}

}
