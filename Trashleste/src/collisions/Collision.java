package collisions;

public abstract class Collision {
	
	protected float x,y, xEnd, yEnd;
	public Collision(float x, float y, float xEnd, float yEnd) {
		this.x = x;
		this.y = y;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
	public void move(float xVel, float yVel) {
		x += xVel;
		xEnd += xVel;
		y += yVel;
		yEnd += yVel;
	}
	
	public void setCoord(float x, float y, float xEnd, float yEnd) {
		this.x = x;
		this.xEnd = xEnd;
		this.y = y;
		this.yEnd = yEnd;
	}
	
	public abstract boolean collidingWith(Collision c, float xVel, float yVel);
	public abstract void spikeHitbox(int id);
	
	public void getCoords() {
		System.out.printf("x:%f y:%f xend:%f yend:%f%n", x, y, xEnd, yEnd);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getxEnd() {
		return xEnd;
	}

	public void setxEnd(float xEnd) {
		this.xEnd = xEnd;
	}

	public float getyEnd() {
		return yEnd;
	}

	public void setyEnd(float yEnd) {
		this.yEnd = yEnd;
	}
	
}
