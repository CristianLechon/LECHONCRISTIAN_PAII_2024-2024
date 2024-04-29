package default_package;

import static java.awt.Font.PLAIN;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelGame extends JPanel {

	private Container container;
	private boolean gameActive;
	private String name = JOptionPane.showInputDialog("Ingrese el nombre del jugador:");
	private Player player = new Player(390, 440, 200, name);
	private Enemies enemies = new Enemies(8, 800, 600);;
	

	public PanelGame() {

		setBackground(Color.BLACK);
		setFocusable(true);
		container = new Container();
		event(player);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		container.draw(enemies, g);

		// Dibujar puntaje
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.drawString("Score: " + enemies.getScore(), 670, 20);
		g.drawString(name, 0, 20);

		// Dibujar la barra de vida
		g.setColor(Color.RED);
		g.fillRect(2, 30, player.getHealth(), 15); // Dibuja la barra de vida en la esquina superior izquierda

		int yLinea = (int) (getHeight() * 0.66);
		g.setColor(Color.RED);
		g.drawLine(0, yLinea, getWidth(), yLinea);

		for (Bullet bullet : player.getBullets()) {
			container.draw(bullet, g); // Dibuja cada bala en la lista de balas del jugador

		}

		container.dead(enemies);

		boolean playerHit = false;
		// Verificar si alguna de las balas ha impactado al jugador
		for (Bullet bullet : enemies.getBullets()) {
			container.draw(bullet, g);
			if (bullet.checkCollision(player)) {
				container.dead(player);
				if (player.getHealth() == 0) {
					playerHit = true;
				}
				break;
			}
		}

		// Verificar si el jugador ha llegado al límite superior o inferior
		if (player.getY() < yLinea || playerHit) {
			// La nave ha llegado al límite o ha sido impactada, mostrar "GAME OVER"
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", PLAIN, 62));
			g.drawString("GAME OVER", (int) (getWidth() / 3.8f), getHeight() / 2);
			gameActive = false; // Desactivar el juego
			// c.dead(player);
		} else {
			// Dibujar al jugador si no ha llegado al límite ni ha sido impactado
			container.draw(player, g);
			player.updateBullets();
			enemies.move("LEFT");
			enemies.updateBullets();
		}

		if (enemies.noMoreEnemies()) {
			// Mostrar "Ganaste"
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 62));
			g.drawString("YOU WIN!", (int) (getWidth() / 2.9f), getHeight() / 2);
			gameActive = false; // Desactivar el juego
		}

	}

	public void event(Player player) {
		this.player = player;
		this.gameActive = true;

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!gameActive)
					return;

				int keyCode = e.getKeyCode();

				switch (keyCode) {
				case KeyEvent.VK_UP:
					// player.moveUp(0);
					container.move(player, "UP");
					break;
				case KeyEvent.VK_DOWN:
					container.move(player, "DOWN");
					break;
				case KeyEvent.VK_LEFT:
					container.move(player, "LEFT");
					break;
				case KeyEvent.VK_RIGHT:
					container.move(player, "RIGHT");
					break;
				case KeyEvent.VK_SPACE:
					container.shoot(player);
					break;
				}

			}
		});

		// Agregar un timer para actualizar el juego cada cierto tiempo
		Timer timer = new Timer(1000 / 60, e -> {
			// Repintar el panel
			repaint();
		});
		timer.start();
	}

}
