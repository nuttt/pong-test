import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import net.java.games.input.*;
import net.java.games.input.Component;

public class Displayer {

	int x = 260;
	int y = 80;

	public static void main(String[] args) throws InterruptedException {
		Displayer gui = new Displayer();
		gui.go();
	}

	public void go() throws InterruptedException {
		/*
		 * JFrame.setDefaultLookAndFeelDecorated(true); JFrame frame = new
		 * JFrame(); frame.setSize(new Dimension(300, 300));
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.setVisible(true);
		 * 
		 * Container panel = frame.getContentPane(); DrawPanel a = new
		 * DrawPanel(); a.setSize(new Dimension(300, 300));
		 * a.addMouseMotionListener(new L()); panel.add(a);
		 * 
		 * while (true) { Thread.sleep(30); frame.repaint(); }
		 */

		Controller[] ca = ControllerEnvironment.getDefaultEnvironment()
				.getControllers();
		for (int i = 0; i < ca.length; i++) {
			if (ca[i].getType() == Controller.Type.MOUSE) {
				System.out.println("id: " + i + " " + ca[i].getName());
				System.out.println("Type: " + ca[i].getType().toString());
				Component[] components = ca[i].getComponents();
				System.out.println("Component Count: " + components.length);
				for (int j = 0; j < components.length; j++) {
					System.out.println("Component " + j + ": "
							+ components[j].getName());
					System.out.println("    Identifier: "
							+ components[j].getIdentifier().getName());
					System.out.print("    ComponentType: ");
					if (components[j].isRelative()) {
						System.out.print("Relative");
					} else {
						System.out.print("Absolute");
					}
					if (components[j].isAnalog()) {
						System.out.print(" Analog");
					} else {
						System.out.print(" Digital");
					}
					System.out.println("");
				}
			}
		}
		Controller firstMouse = ca[3];
		while (true) {
			firstMouse.poll();
			Component[] components = firstMouse.getComponents();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < components.length; i++) {
				if (i > 0) {
					buffer.append(", ");
				}
				buffer.append(components[i].getName());
				buffer.append(": ");
				if (components[i].isAnalog()) {
					buffer.append(components[i].getPollData());
				} else {
					if (components[i].getPollData() == 1.0f) {
						buffer.append("On");
					} else {
						buffer.append("Off");
					}
				}
			}
			System.out.println(buffer.toString());
			Thread.sleep(20);
		}

	}

	class DrawPanel extends JPanel {

		public void paintComponent(Graphics g) {
			g.setColor(Color.CYAN);
			g.fillRoundRect(x, y - 30, 20, 60, 10, 10);
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
