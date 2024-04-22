package default_package;

import interfaces.Render;

public class Geometria implements Render {

	// Implementación por defecto, puede ser sobrescrita en subclases
	@Override
	public void drawAll() {
		// TODO Auto-generated method stub
			
		System.out.println("Figura dibujada");

	}

}
