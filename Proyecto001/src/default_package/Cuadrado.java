package default_package;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class Cuadrado implements GLEventListener{
	private static final float[] verticesCuadrado = { -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f };

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        

        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        
        gl.glColor3f(0.0f, 1.0f, 0.0f);

		for (int i = 0; i < verticesCuadrado.length; i += 2) {
			gl.glVertex2f(verticesCuadrado[i], verticesCuadrado[i + 1]);
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
