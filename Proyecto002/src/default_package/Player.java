package default_package;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import interfaces.Drawable;
import interfaces.IDead;
import interfaces.Movable;
import interfaces.Shootable;

public class Player implements Drawable, Shootable, Movable, IDead {

	private int x, y, health;
	private String name;
	private boolean isAlive;
	private static List<Bullet> bullets = new ArrayList<>();
	private Timer shootTimer;

	public Player(int x, int y, int health, String name) {
		this.x = x;
		this.y = y;
		this.isAlive = true;
		this.health = health;
		this.name = name;
		shootTimer = new Timer(100, e -> {
			shootTimer.stop(); // Detener el temporizador después de disparar
		});
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		// Dibujar un triángulo
		int[] xPoints = { x, x + 10, x - 10 };
		int[] yPoints = { y, y + 20, y + 20 };
		g.setColor(Color.CYAN);
		g.fillPolygon(xPoints, yPoints, 3);

	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		if (!shootTimer.isRunning()) {
			bullets.add(new Bullet(getX() - 4, getY()));
			shootTimer.start(); // Iniciar el temporizador
		}

	}

	public void updateBullets() {
		for (Bullet bullets : bullets) {
			bullets.move("UP");
		}
	}

	public static List<Bullet> getBullets() {
		return bullets;
	}

	@Override
	public void move(String direction) {
		switch (direction) {
		case "UP":
			y -= 20;
			if (y < 15) {
				y = 15;
			}
			break;

		case "DOWN":
			y += 20;
			if (y > 540) {
				y = 540;
			}
			break;

		case "LEFT":
			x -= 25;
			if (x < 11) {
				x = 11;
			}
			break;

		case "RIGHT":
			x += 25;
			if (x > 772) {
				x = 772;
			}
			break;
		}
	}

	@Override
	public void dead() {
		health -= 25;
		if (health < 0) {
			health = 0;
			isAlive = false;
		}
	}

	public int getHealth() {
		return health;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

}
