package gameStates;

import java.awt.Graphics;

import collisions.CollisionHandler;
import entities.DiamondCollider;
import entities.Player;
import main.Handler;
import worlds.Level;
import worlds.LevelHandler;

public class GameState extends State {
	
	private Player player;
	private DiamondCollider diamondCollider;
	private Level level;
	private LevelHandler levelHandler;
	private int levelID;
	private CollisionHandler collisionHandler;
	private long startTime, currentTime;
	int deaths;
	private boolean finished;
	
	public GameState(Handler handler) {
		super(handler);
		levelID = 0; //TODO change this to change what level to start on
		levelHandler = new LevelHandler(11);
		level = levelHandler.getLevel(levelID); 
		handler.setLevel(level);
		player = new Player(handler, level.getSpawnX(), level.getSpawnY());
		diamondCollider = new DiamondCollider(handler, player);
		startTime = System.currentTimeMillis();
		deaths = 0;
		finished = false;
	}

	@Override
	public void tick() {
		
		level.tick();
		player.tick();
		collisionHandler.tick();
		player.tickMove();
		collisionHandler.tickMove();
		diamondCollider.tick();
		if (!collisionHandler.isAlive()) {
			respawn();
			collisionHandler.setAlive(true);
			System.out.printf("Time: %.2f%n",(currentTime-startTime)/1000.);
			deaths++;
			System.out.printf("Deaths: %d%n", deaths);
		}
		currentTime = System.currentTimeMillis();

		if (collisionHandler.isWon()) {
			nextLevel();
			respawn();
			collisionHandler.setWon(false);
		}
		if (levelID == 10) 
			if (!finished)
				if (player.getX()>=48) {
					finishTime();
					finished = true;
				}
		
	}
	
	private void nextLevel() {
		levelID++;
		level = levelHandler.getLevel(levelID);
		handler.setLevel(level);
		
	}
	
	private void respawn() {
		player.respawn();
		collisionHandler.respawn();
		diamondCollider.respawn();
	}
	
	private void finishTime() {
		System.out.printf("%nFinal Time: %.2f%nTotal Deaths: %d",(currentTime-startTime)/1000., deaths);
	}

	@Override
	public void render(Graphics g) {
		level.render(g);
		player.render(g);
		diamondCollider.render(g);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setCollisionHandler(CollisionHandler collisionHandler) {
		this.collisionHandler = collisionHandler;
	}

}
