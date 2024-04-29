package default_package;

import java.awt.Graphics;
import java.awt.Graphics2D;
import interfaces.Drawable;
import interfaces.IDead;
import interfaces.Movable;
import interfaces.Shootable;

public class Container {

	public void draw(Drawable d, Graphics g) {
		d.draw(g);
	}

	public void move(Movable m, String direction) {
		m.move(direction);
	}

	public void shoot(Shootable s) {
		s.shoot();
	}

	public void dead(IDead d) {
		d.dead();
	}

}
