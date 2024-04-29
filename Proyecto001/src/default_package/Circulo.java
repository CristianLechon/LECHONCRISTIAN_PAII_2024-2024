package default_package;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Circulo implements GLEventListener{
	private static final float[] verticesCirculo = { 0.21f, 0.218f, 0.258f, 0.158f, 0.287f, 0.096f, 0.302f, 0.026f,
			0.3f, -0.043f, 0.286f, -0.098f, 0.264f, -0.148f, 0.187f, -0.238f, 0.143f, -0.267f, 0.077f, -0.293f, 0.033f,
			-0.301f, -0.014f, -0.302f, -0.096f, -0.287f, -0.148f, -0.264f, -0.203f, -0.225f, -0.241f, -0.183f, -0.278f,
			-0.119f, -0.295f, -0.066f, -0.303f, 0f, -0.301f, 0.033f, -0.287f, 0.096f, -0.264f, 0.148f, -0.225f, 0.203f,
			-0.173f, 0.249f, -0.112f, 0.281f, -0.062f, 0.296f, 0f, 0.303f, 0.058f, 0.297f, 0.096f, 0.287f, 0.13f,
			0.273f, 0.166f, 0.253f, 0.21f, 0.218f };

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        
        gl.glColor3f(1.0f, 0.0f, 0.0f); 
		for (int i = 0; i < verticesCirculo.length; i += 2) {
			gl.glVertex2f(verticesCirculo[i], verticesCirculo[i + 1]);
		}
		gl.glEnd();

        gl.glFlush();
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

}
