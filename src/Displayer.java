import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Displayer {

	int x = 260;
	int y = 80;

	public static void main(String[] args) throws InterruptedException {
		Displayer gui = new Displayer();
		gui.go();
	}

	public void go() throws InterruptedException {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(300, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Container panel = frame.getContentPane();
		DrawPanel a = new DrawPanel();
		a.setSize(new Dimension(300, 300));
		a.addMouseMotionListener(new L());
		panel.add(a);
		while (true) {
			Thread.sleep(30);
			frame.repaint();
		}

	}

	class DrawPanel extends JPanel {

		public void paintComponent(Graphics g) {
			g.setColor(Color.CYAN);
			g.fillRoundRect(x, y-30, 20, 60, 10, 10);
		}

	}

	class L implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			y = arg0.getY();
		}

	}
}
