package collisions;

public class BotHitbox extends Collision{
	
	public BotHitbox(float x, float y, int length) {
		super(x,y,x+length,y);
	}
	
	public boolean collidingWith(Collision c, float xVel, float yVel) { //madeline colliding with floor
		float slope = xVel/yVel;
		float midx = (c.x+c.xEnd)/2;
		float midy = (c.y+c.yEnd)/2;
		
		if (c instanceof TopHitbox) {
			if (this.y<=c.y && this.y+yVel>=c.y && c.x>slope*(c.y-this.y)+this.x && c.x<slope*(c.y-this.y)+this.xEnd) return true;
			if (this.y<=c.y && this.y+yVel>=c.y && c.xEnd>slope*(c.y-this.y)+this.x && c.xEnd<slope*(c.y-this.y)+this.xEnd) return true;
			if (this.y<=midy && this.y+yVel>=midy && midx>slope*(midy-this.y)+this.x && midx<slope*(midy-this.y)+this.xEnd) return true;
		}
		return false;
	}
	
	public void spikeHitbox(int id) {
		if (id == 21) {
			x+=3;
			y-=1;
			yEnd-=1;
		}
		if (id == 22) {
			y-=3;
			yEnd-=3;
			x+=1;
			xEnd-=1;
		}
		if (id == 23) {
			xEnd-=3;
			y-=1;
			yEnd-=1;
		}
	}
	
	public int getLength() {
		return (int) (xEnd-x);
	}

}
