package default_package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import interfaces.Drawable;
import interfaces.Movable;
import interfaces.Shootable;

public class Bullet implements Drawable, Movable {

	private int x, y;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 7, 13);
		g.drawOval(x, y, 7, 13);

	}

	@Override
	public void move(String direction) {
		// TODO Auto-generated method stub
		switch (direction) {
		case "UP":
			y -= 10;
			if (y < -15) {
				y = -15;
			}
			break;

		case "DOWN":
			y += 10;
			if (y > 570) {
				y = 570;
			}
			break;
		}

	}

	public boolean checkCollision(Player player) {
		Rectangle bulletBounds = new Rectangle(x, y, 7, 13);
		Rectangle playerBounds = new Rectangle(player.getX(), player.getY(), 30, 30);
		return bulletBounds.intersects(playerBounds);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
