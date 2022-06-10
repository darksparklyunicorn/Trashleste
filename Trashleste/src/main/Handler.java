package main;

import collisions.CollisionHandler;
import input.KeyManager;
import worlds.Level;

public class Handler {
	private Game game;
	private Level level;
	private CollisionHandler collisionHandler;
	

	public Handler(Game game) {
		this.game = game;
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public CollisionHandler getCollisionHandler() {
		return collisionHandler;
	}

	public void setCollisionHandler(CollisionHandler collisionHandler) {
		this.collisionHandler = collisionHandler;
	}

	

}
