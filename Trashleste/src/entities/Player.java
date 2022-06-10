package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Assets;
import main.Handler;

public class Player extends Entity{

	private Handler handler;
	private static final float dashVel = 1.82f;
	private static final float springVel = 2.1f;
	private float speedx = 1.125f;
	private float speedy = 1.5f; //dash 1.82f
	private static final float xAccelNorm = .28125f;
	private float xAccel = xAccelNorm;
	private static final float gravityNorm = .06875f;
	private float gravity = gravityNorm;
	private float xVel, yVel;
	private boolean grounded, hasDash, dashHorizontal, dashVertical, spawning;
	private boolean left, right, up, down, dashing, jumping, facingRight, wallClinging;
	private int canDash, canJump;
	private boolean walljumped;
	private int dashTick, WJTick;
	private Dash dash;
	private BufferedImage currentTexture;

	public Player(Handler handler, int x, int y) {
		super(x,y,16,8);

		this.handler = handler;
		dash = new Dash(this, false, false, false, false);
		xVel = 0;
		yVel = 0;
		grounded = false;
		hasDash = false;
		walljumped = false;
		spawning = true;
		facingRight = true;
		wallClinging = false;
		dashTick = 0;

		canDash = 0;
		canJump = 0;

		bounds.x = 6;
		bounds.y = 3;
		bounds.width = 5;
		bounds.height = 5;
	}

	@Override
	public void tick() {
		getInput();
	}

	public void tickMove() {
		move();
	}

	private void getInput() {
		left = handler.getKeyManager().left;
		right = handler.getKeyManager().right;
		up = handler.getKeyManager().up;
		down = handler.getKeyManager().down;
		dashing = handler.getKeyManager().dash;
		jumping = handler.getKeyManager().jump;


		if(!dashing) canDash = 0;
		if(dashing && canDash >= 0) canDash++;
		if(canDash>3) dashing = false;

		if(!jumping) canJump = 0;
		if(jumping && canJump >= 0) canJump++;
		if(canJump>1) jumping = false;

		if(dashTick > 0) dashTick--;
		if(dashTick == 2) {
			gravity = gravityNorm;
		}
		if(dashTick == 2)
			xAccel = xAccelNorm;

		if(hasDash&&dashing&&dashTick==0&&WJTick<15) {
			xAccel = xAccelNorm;
			dash.updateDash(left, right, up, down);
			hasDash = false;
			grounded = false;
			dash.dash();
			dashTick = 20;
			WJTick = 0;
		}


		if(dashTick<=15) {
			if ((dashHorizontal&&!dashVertical)||dashTick<=10) {

				yVel += gravity;

				if (grounded) {
					hasDash = true;
					if (jumping) {
						yVel = -speedy;
						grounded = false;
						jumping = false;
					}
				}
				else {
					if (yVel>speedy) yVel = speedy;
					//					if (yVel<-speedy) yVel = -speedy;
				}

				if(dashTick<=10) {

					if (xVel>0) xVel -= xAccel;
					if (xVel<0) xVel += xAccel;

					if (dashTick<=8&&dashHorizontal) {
						if (xVel>speedx) xVel = speedx;
						if (xVel<-speedx) xVel = -speedx;
					}

					if (dashTick<=7||!dashHorizontal) {

						if (handler.getKeyManager().left) xVel -= xAccel*2;
						if (handler.getKeyManager().right) xVel += xAccel*2;
						if (xVel>speedx) xVel = speedx;
						if (xVel<-speedx) xVel = -speedx;
						if (xVel<.3 && xVel>-.3) xVel = 0;

					}
					if (walljumped) {
						WJTick = 23;
						walljumped = false;
						dashTick = 0;
					}
					if (WJTick>0) WJTick--;
					if (WJTick == 1) xAccel = xAccelNorm;
				}
			}
		}
		else jumping = false;



		//		xVel = 0;
		//		yVel = 0;
		//		
		//		if (game.getKeyManager().up) yVel = -speed;
		//		if (game.getKeyManager().down) yVel = speed;
		//		if (game.getKeyManager().left) xVel = -speed;
		//		if (game.getKeyManager().right) xVel = speed;

	}

	public void respawn() {
		x = handler.getLevel().getSpawnX();
		y = handler.getLevel().getSpawnY();

		xVel = 0;
		yVel = 0;
		grounded = false;
		hasDash = false;
		walljumped = false;
		spawning = true;
		facingRight = true;
		wallClinging = false;
		dashTick = 0;

		canDash = 0;
		canJump = 0;

		xAccel = xAccelNorm;
		gravity = gravityNorm;

	}

	private void move() {
		if (!spawning) x+=xVel;
		y+=yVel;
	}




	@Override
	public void render(Graphics g) {
		int dashIndex = 0;
		if (!hasDash) dashIndex = 1;
		if (spawning) dashIndex = 0;
		if (xVel>0) 
			facingRight = true;
		if (xVel<0)
			facingRight = false;

		if (grounded) {
			if(!(up^down)) {
				if (facingRight) currentTexture = Assets.MadR[dashIndex];
				else currentTexture = Assets.MadL[dashIndex];
			}
			else {
				if (up && facingRight) currentTexture = Assets.MadRup[dashIndex];
				if (up && !facingRight) currentTexture = Assets.MadLup[dashIndex];
				if (down && facingRight) currentTexture = Assets.MadRdown[dashIndex];
				if (down && !facingRight) currentTexture = Assets.MadLdown[dashIndex];
			}
		}
		else {
			if (wallClinging) {
				if (facingRight) currentTexture = Assets.MadRwall[dashIndex];
				else currentTexture = Assets.MadLwall[dashIndex];
			}
			else {
				if (facingRight) currentTexture = Assets.MadR[dashIndex];
				else currentTexture = Assets.MadL[dashIndex];
			}
		}


		g.drawImage(currentTexture,(int) x*pixelSize,(int) y*pixelSize, width*pixelSize, height*pixelSize, null);

		g.setColor(Color.red);
		//		g.fillRect((int)(bounds.x+x)*pixelSize,(int) (bounds.y+y)*pixelSize, bounds.width*pixelSize, bounds.height*pixelSize);
		//		g.fillRect((int) handler.getCollisionHandler().getMadBot().getX()*pixelSize,(int) handler.getCollisionHandler().getMadBot().getY()*pixelSize-20,  (int)(((BotHitbox) (handler.getCollisionHandler().getMadBot())).getLength())*pixelSize, 5*pixelSize);
		//g.fillRect((int) handler.getCollisionHandler().getMadRight().getX()*pixelSize-20,(int) handler.getCollisionHandler().getMadRight().getY()*pixelSize, 5*pixelSize ,(int)(((RightHitbox) (handler.getCollisionHandler().getMadRight())).getLength())*pixelSize);
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getxVel() {
		return xVel;
	}

	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public float getyVel() {
		return yVel;
	}

	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

	public boolean isGrounded() {
		return grounded;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

	public float getDashVel() {
		return dashVel;
	}

	public float getSpeedx() {
		return speedx;
	}

	public float getSpeedy() {
		return speedy;
	}

	public void setxAccel(float xAccel) {
		this.xAccel = xAccel;
	}

	public static float getXaccelnorm() {
		return xAccelNorm;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setWalljumped(boolean walljumped) {
		this.walljumped = walljumped;
	}

	public void setDashHorizontal(boolean dashHorizontal) {
		this.dashHorizontal = dashHorizontal;
	}

	public void setDashVertical(boolean dashVertical) {
		this.dashVertical = dashVertical;
	}

	public boolean isSpawning() {
		return spawning;
	}

	public void setSpawning(boolean spawning) {
		this.spawning = spawning;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setHasDash(boolean hasDash) {
		this.hasDash = hasDash;
	}

	public boolean isHasDash() {
		return hasDash;
	}

	public static float getSpringvel() {
		return springVel;
	}

	public void setWallClinging(boolean wallClinging) {
		this.wallClinging = wallClinging;
	}



}
