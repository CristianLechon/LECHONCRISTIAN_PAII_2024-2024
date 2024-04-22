package default_package;

import interfaces.Render;

public class Dibujar {
	
	private Render re;
	
	// Constructor que recibe un objeto Render
	public Dibujar(Render re) {
		this.re = re;
	}
	
	// Método para dibujar la figura
	public void dibujarFigura() {
		re.drawAll();
	}

}
