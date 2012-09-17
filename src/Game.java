import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	private Paddle paddle1, paddle2;
	private Ball ball1;
	private int mouseX;
	private int mouseY;
	public static int START_AREA_X, END_AREA_X, START_AREA_Y, END_AREA_Y;

	public void go() {
		// TODO Auto-generated method stub
		START_AREA_X = 20;
		END_AREA_X = 780;
		START_AREA_Y = 20;
		END_AREA_Y = 560;
		paddle1 = new Paddle(this, 1);
		Thread a = new Thread(paddle1);
		a.start();
		ball1 = new Ball(paddle1, paddle2);
		Thread b = new Thread(ball1);
		b.start();
		// TODO Auto-generated constructor stub
		JFrame.setDefaultLookAndFeelDecorated(false);
		JFrame frame = new JFrame("Pong test 1");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = frame.getContentPane();
		DrawPanel m = new DrawPanel();
		m.setPreferredSize(new Dimension(800, 600));
		m.addMouseMotionListener(new MyListener());
		cp.setPreferredSize(new Dimension(800, 600));
		cp.add(m);
		frame.pack();
		frame.setVisible(true);
		while (true) {
			m.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public static void main(String[] args) {
		Game gameMain = new Game();
		gameMain.go();
	}

	class DrawPanel extends JPanel {

		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.blue);
			g.drawRect(START_AREA_X, START_AREA_Y, END_AREA_X - START_AREA_X,
					END_AREA_Y - START_AREA_Y);
			g.fillOval((int)ball1.getX(), (int)ball1.getY(), (int)ball1.getRadius(), (int)ball1.getRadius());
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(paddle1.getX(),
					paddle1.getY() - paddle1.getLength() / 2, 10,
					paddle1.getLength(), 3, 3);
			g.setColor(Color.BLACK);

			// g.setFont(arg0);
			// g.drawString("1", x+10, y+20);
		}

	}

	class MyListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouseY = arg0.getY();
			mouseX = arg0.getX();
			synchronized (paddle1) {
				paddle1.notifyAll();
			}
		}

	}

}
