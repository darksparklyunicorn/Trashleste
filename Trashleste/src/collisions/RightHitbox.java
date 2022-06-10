package collisions;

public class RightHitbox extends Collision{
	
	public RightHitbox(float x, float y, int length) {
		super(x,y,x,y+length);
	}
	
	public boolean collidingWith(Collision c, float xVel, float yVel) { //madeline running right into wall
		float slope = yVel/xVel;
		float midx = (c.x+c.xEnd)/2;
		float midy = (c.y+c.yEnd)/2;
		
		if (c instanceof LeftHitbox) {
			if (this.x<=c.x && this.x+xVel>=c.x && c.y>=slope*(c.x-this.x)+this.y && c.y<=slope*(c.x-this.x)+this.yEnd) return true;
			if (this.x<=c.x && this.x+xVel>=c.x && c.yEnd>=slope*(c.x-this.x)+this.y && c.yEnd<=slope*(c.x-this.x)+this.yEnd) return true;
			if (this.x<=midx && this.x+xVel>=midx && midy>=slope*(midx-this.x)+this.y && midy<=slope*(midx-this.x)+this.yEnd) return true;
			
		}
		return false;
	}
	
	public void spikeHitbox(int id) {
		if (id == 20) {
			y+=3;
			x-=1;
			xEnd-=1;
		}
		if (id == 22) {
			yEnd-=3;
			x-=1;
			xEnd-=1;
		}
		if (id == 23) {
			x-=3;
			xEnd-=3;
			y+=1;
			yEnd-=1;
		}
	}
	
	
	
	public int getLength() {
		return (int) (yEnd-y);
	}



}
