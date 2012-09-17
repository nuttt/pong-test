import java.awt.*;
import javax.swing.*;

public class Paddle implements Runnable {
	private int x;
	private int y;
	private int length;
	private Game game;
	int paddleID;

	public Paddle(Game game, int paddleID) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.paddleID = paddleID;
		this.x = 50;
		this.length = 100;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	private void setY(int mouseY) {
		System.out.println(mouseY);
		if(mouseY - length/2 < game.START_AREA_Y)
		{
			System.out.println("out up");
			y = game.START_AREA_Y + length/2;
		}
		else if(mouseY + length/2 > game.END_AREA_Y)
		{
			System.out.println("out down");
			y = game.END_AREA_Y - length/2;
		}
		else
			y = mouseY;
	}
	
	private void setX(int mouseX)
	{
		x = mouseX;
	}
	
	public int getLength() {
		return length;
	}

	public void run() {
		while (true) {
			try {
				synchronized (this) {
					wait();
				}
				setY(game.getMouseY());
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
