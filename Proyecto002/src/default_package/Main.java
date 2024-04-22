package default_package;

/**
 * @author Cristian Lechon
 * Titulo: Ejercicio inversion de dependencia
 * 
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Crear una instancia de Dibujar para un triángulo y dibujarlo
		Dibujar triangulo = new Dibujar(new Triangulo());
		triangulo.dibujarFigura();
		// Crear una instancia de Dibujar para un cuadrado y dibujarlo
		Dibujar cuadrado = new Dibujar(new Cuadrado());
		cuadrado.dibujarFigura();
		// Crear una instancia de Dibujar para un círculo y dibujarlo
		Dibujar circulo = new Dibujar(new Circulo());
		circulo.dibujarFigura();

	}

}
