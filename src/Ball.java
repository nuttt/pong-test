
public class Ball implements Runnable {

	private double x, y, radius, dx, dy;
	private Paddle paddle1, paddle2;
	public Ball(int x, int y, int radius, int speedX, int speedY) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.dx = speedX;
		this.dy = speedY;
	}
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public double getSpeedX() {
		return dx;
	}
	public void setSpeedX(int speedX) {
		this.dx = speedX;
	}
	public double getSpeedY() {
		return dy;
	}
	public void setSpeedY(int speedY) {
		this.dy = speedY;
	}
	public void move(){
		this.x += dx;
		this.y += dy;
		if(this.x > Game.END_AREA_X - radius)
			dx = -dx;
		//paddle collision
		if(this.x < paddle1.getX()+5 + radius && this.x > paddle1.getX() + radius)
		{
			if(paddle1.getY() - paddle1.getLength() <= this.y && this.y <= paddle1.getY() + paddle1.getLength())
			{
				double velocity2 = dx*dx + dy*dy;
//				dx = -dx;
				dy += (y - paddle1.getY())/10;
				dx = Math.sqrt(velocity2 - dy*dy);
			}
		}
		if(this.y > Game.END_AREA_Y - radius || this.y < Game.START_AREA_Y + radius)
			dy = -dy;
	}
	public void run(){
		while(true)
		{
			move();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Ball(Paddle paddle1, Paddle paddle2) {
		// TODO Auto-generated constructor stub
		this.paddle1 = paddle1;
		this.paddle2 = paddle2;
		this.x = 400;
		this.y = 400;
		this.dx = 3;
		this.dy = 3;
		this.radius = 10;
	}

}
