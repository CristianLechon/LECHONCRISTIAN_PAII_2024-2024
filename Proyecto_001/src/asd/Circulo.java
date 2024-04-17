package asd;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
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

import org.lwjgl.opengl.GL;

public class Circulo {
	private long window;
	
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

}
