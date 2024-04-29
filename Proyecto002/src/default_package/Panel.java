package default_package;

import javax.swing.JFrame;

public class Panel extends JFrame {

	public void window() {
		setTitle("Galaga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(270, 100);
		setSize(800, 600);

		PanelGame pg = new PanelGame();
		add(pg);

		setVisible(true);
	}

}
