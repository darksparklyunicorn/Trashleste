package collisions;

import entities.Player;
import entities.WallJump;
import main.Handler;

public class CollisionHandler {

	private Handler handler;
	private Player player;
	private boolean left, right, top, bot;
	private Collision madLeft, madRight, madTop, madBot;
	private Collision blockL, blockR, blockT, blockB;
	private int tileX, tileY;
	private float x, y;
	private boolean[][][] collisions;
	private int[][] tiles;
	private int[][] springTicks;
	private static final int BLOCKSIZE = 8;
	private static final float slidingYVel = .3f;
	private WallJump walljump;
	private boolean isAlive;
	private boolean won;


	public CollisionHandler(Handler handler) {
		this.handler = handler;
		player = handler.getGame().getGameState().getPlayer();

		collisions = handler.getLevel().getCollisions();
		tiles = handler.getLevel().getTiles();
		springTicks = handler.getLevel().getSpringTicks();

		walljump = new WallJump(player);

		blockL = new LeftHitbox(0, 0, 8);
		blockR = new RightHitbox(0, 0, 8);
		blockT = new TopHitbox(0, 0, 8);
		blockB = new BotHitbox(0, 0, 8);

		float x = player.getX();
		float y = player.getY();

		madLeft = new LeftHitbox(x+6, y+3, 5);
		madRight = new RightHitbox(x+11, y+3, 5);
		madTop = new TopHitbox(x+6, y+3, 5);
		madBot = new BotHitbox(x+6, y+8, 5);

		isAlive = true;
		won = false;
	}

	public void tick() {
		playerBoxUpdate();
		checkCollisions();
		playerBoxUpdate();

	}

	public void tickMove() {
		affectPlayer();
	}

	private void playerBoxUpdate() { 
		x = player.getX();
		y = player.getY();

		madLeft.setCoord(x+6, y+3, x+6, y+8);
		madRight.setCoord(x+11, y+3, x+11, y+8);
		madTop.setCoord(x+6, y+3, x+11, y+3);
		madBot.setCoord(x+6, y+8, x+11, y+8);

		tileX = ((int)x+6)/8;
		tileY = ((int)y+3)/8;


	}

	private void checkCollisions() {
		setFalse();


		//check collision with right wall
		if (player.getxVel()>0) {
			for (int y = tileY-1; y<tileY+2; y++) {
				if(y>-1 && y<16) {
					if(tileX<15 && collisions[tileX+1][y][0]) {

						blockL.setCoord(BLOCKSIZE*(tileX+1), BLOCKSIZE*y, BLOCKSIZE*(tileX+1), BLOCKSIZE*(y+1));

						if (!isSpikeOrSpring(tileX+1,y)) {
							boolean temp = madRight.collidingWith(blockL, player.getxVel(), player.getyVel());
							right |= temp;

							if (temp) {
								if (player.getyVel()>=0 && player.isRight()) player.setyVel(slidingYVel);
								if (player.isJumping()&&!player.isGrounded()) walljump.rightWallJump();
							}
						}
					}
				}
			}

			if (!right) {
				for (int y = tileY-1; y<tileY+2; y++) {
					if(y>-1 && y<16) {
						if(tileX<15 && collisions[tileX+1][y][0]){

							blockL.setCoord(BLOCKSIZE*(tileX+1), BLOCKSIZE*y, BLOCKSIZE*(tileX+1), BLOCKSIZE*(y+1));

							if (isSpike(tileX+1,y)) {
								blockL.spikeHitbox(tiles[tileX+1][y]);
								isAlive &= !madRight.collidingWith(blockL, player.getxVel(), player.getyVel());
							}
							else if(isSpikeOrSpring(tileX+1,y)) {
								boolean temp = madRight.collidingWith(blockL, player.getxVel(), player.getyVel());
								if (temp) {
									player.setyVel(-Player.getSpringvel());
									player.setY(BLOCKSIZE*y-8);
									player.setHasDash(true);
									for(int i = 0; i<springTicks.length; i++) {
										if (springTicks[i][0] == tileX+1 && springTicks[i][1] == y) springTicks[i][2] = 20;
									}
								}

							}

						}
					}
				}
			}

		}

		//check collision with left wall
		if (player.getxVel()<0) {
			for (int y = tileY-1; y<tileY+2; y++) {
				if(y>-1 && y<16) {
					if(tileX>0 && collisions[tileX-1][y][1]) {

						blockR.setCoord(BLOCKSIZE*tileX, BLOCKSIZE*y, BLOCKSIZE*tileX, BLOCKSIZE*(y+1));

						if (!isSpikeOrSpring(tileX-1,y)) {
							boolean temp = madLeft.collidingWith(blockR, player.getxVel(), player.getyVel());
							left |= temp;

							if (temp) {
								if (player.getyVel()>=0 && player.isLeft()) player.setyVel(slidingYVel);
								if (player.isJumping()&&!player.isGrounded()) walljump.leftWallJump();
							}
						}
					}
				}
			}

			if (!left) {
				for (int y = tileY-1; y<tileY+2; y++) {
					if(y>-1 && y<16) {
						if(tileX>0 && collisions[tileX-1][y][1]) {

							blockR.setCoord(BLOCKSIZE*tileX, BLOCKSIZE*y, BLOCKSIZE*tileX, BLOCKSIZE*(y+1));

							if(isSpike(tileX-1,y)) {
								blockR.spikeHitbox(tiles[tileX-1][y]);
								isAlive &= !madLeft.collidingWith(blockR, player.getxVel(), player.getyVel());
							}

							else if(isSpikeOrSpring(tileX-1,y)) {
								boolean temp = madLeft.collidingWith(blockR, player.getxVel(), player.getyVel());
								if (temp) {
									player.setY(BLOCKSIZE*y-8);
									player.setyVel(-Player.getSpringvel());
									player.setHasDash(true);
									for(int i = 0; i<springTicks.length; i++) {
										if (springTicks[i][0] == tileX-1 && springTicks[i][1] == y) springTicks[i][2] = 20;
									}
								}
							}

						}
					}
				}
			}

			for (int y = tileY; y < tileY+2; y++) {
				if(y < 16 && isSpike(tileX,y) && collisions[tileX][y][1]) {
					blockR.setCoord(BLOCKSIZE*(tileX+1), BLOCKSIZE*y, BLOCKSIZE*(tileX+1), BLOCKSIZE*(y+1));
					blockR.spikeHitbox(tiles[tileX][y]);
					isAlive &= !madLeft.collidingWith(blockR, player.getxVel(), player.getyVel());
				}
			}
		}

		//check collision with floor
		if (player.getyVel()>0) {
			for (int x = tileX-1; x<tileX+2; x++) {
				if(x>-1 && x<16) {
					if(tileY<15 && collisions[x][tileY+1][2]) {

						blockT.setCoord(BLOCKSIZE*x, BLOCKSIZE*(tileY+1), BLOCKSIZE*(x+1), BLOCKSIZE*(tileY+1));

						if (!isSpikeOrSpring(x,tileY+1)) {
							bot |= madBot.collidingWith(blockT, player.getxVel(), player.getyVel());
						}
					}
				}
			}

			if (!bot) {
				for (int x = tileX-1; x<tileX+2; x++) {
					if(x>-1 && x<16) {
						if(tileY<15 && collisions[x][tileY+1][2]) {

							blockT.setCoord(BLOCKSIZE*x, BLOCKSIZE*(tileY+1), BLOCKSIZE*(x+1), BLOCKSIZE*(tileY+1));

							if(isSpike(x,tileY+1)) {
								blockT.spikeHitbox(tiles[x][tileY+1]);
								isAlive &= !madBot.collidingWith(blockT, player.getxVel(), player.getyVel());
							}
							
							else if(isSpikeOrSpring(x,tileY+1)) {
								boolean temp = madBot.collidingWith(blockT, player.getxVel(), player.getyVel());
								if (temp) {
									player.setY(BLOCKSIZE*tileY);
									player.setyVel(-Player.getSpringvel());
									player.setHasDash(true);
									for(int i = 0; i<springTicks.length; i++) {
										if (springTicks[i][0] == x && springTicks[i][1] == tileY+1) springTicks[i][2] = 20;
									}
								}
							}
						}
					}
				}
			}

		}

		//check collision with ceiling
		if (player.getyVel()<0) {
			for (int x = tileX-1; x<tileX+2; x++) {
				if (x>-1 && x<16) {
					if(tileY>0 && collisions[x][tileY-1][3]) {

						blockB.setCoord(BLOCKSIZE*x, BLOCKSIZE*tileY, BLOCKSIZE*(x+1), BLOCKSIZE*tileY);

						if (!isSpike(x,tileY-1)) {
							top |= madTop.collidingWith(blockB, player.getxVel(), player.getyVel());
						} 
					}
				}
			}

			if (!top) {
				for (int x = tileX-1; x<tileX+2; x++) {
					if (x>-1 && x<16) {
						if(tileY>0 && collisions[x][tileY-1][3] && isSpike(x, tileY-1)) {
							blockB.setCoord(BLOCKSIZE*x, BLOCKSIZE*tileY, BLOCKSIZE*(x+1), BLOCKSIZE*tileY);
							blockB.spikeHitbox(tiles[x][tileY-1]);
							isAlive &= !madTop.collidingWith(blockB, player.getxVel(), player.getyVel());
						}
					}
				}
			}


			for (int x = tileX; x < tileX+2; x++) {
				if(x < 16 && isSpike(x,tileY) && collisions[x][tileY][3]) {
					blockB.setCoord(BLOCKSIZE*x, BLOCKSIZE*(tileY+1), BLOCKSIZE*(x+1), BLOCKSIZE*(tileY+1));
					blockB.spikeHitbox(tiles[x][tileY]);
					isAlive &= !madTop.collidingWith(blockB, player.getxVel(), player.getyVel());
				}
			}
		}


		if (x<-6) {
			player.setX(-6);
			player.setxVel(0);
		}
		if (x>118) {
			player.setX(118);
			player.setxVel(0);
		}
		if (y>131) {
			isAlive = false;
		}
		if (y<-3) {
			won = true;
		}
		if(!isAlive) {
			handler.setLevel(handler.getLevel());
		}
		
		for (int i=0; i<springTicks.length; i++) {
			if (springTicks[i][2]>0) springTicks[i][2]--;
		}

		if (player.getyVel()>=0 && (left&&player.isLeft()) || (right&&player.isRight())) player.setWallClinging(true);
		else player.setWallClinging(false);

	}

	private void affectPlayer() {



		if (left) {
			player.setX(BLOCKSIZE*tileX-6);
		}
		if (right) {
			player.setX(BLOCKSIZE*tileX-3);
		}
		if (top) {
			player.setY(BLOCKSIZE*tileY-3);
			player.setyVel(player.getyVel()+player.getGravity());
		}
		if (bot) {
			player.setY(BLOCKSIZE*(tileY));
			player.setGrounded(true);
			player.setyVel(0);
			player.setSpawning(false);
		}
		else {
			player.setGrounded(false);
		}



	}

	public void respawn() {
		collisions = handler.getLevel().getCollisions();
		tiles = handler.getLevel().getTiles();
		springTicks = handler.getLevel().getSpringTicks();
	}


	private void setFalse() {
		bot = false;
		top = false;
		left = false;
		right = false;
	}

	private boolean isSpike(int x, int y) {
		if (tiles[x][y]<24&&tiles[x][y]>19) return true;
		return false;
	}

	private boolean isSpikeOrSpring(int x, int y) {
		if (tiles[x][y]<25&&tiles[x][y]>19) return true;
		return false;
	}

	public Collision getMadLeft() {
		return madLeft;
	}


	public Collision getMadRight() {
		return madRight;
	}


	public Collision getMadTop() {
		return madTop;
	}


	public Collision getMadBot() {
		return madBot;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

}
