package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import collisions.CollisionHandler;
import gameStates.GameState;
import gameStates.MenuState;
import gameStates.State;
import input.KeyManager;

public class Game implements Runnable{
	
	private Window window;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;
	@SuppressWarnings("unused")
	private State menuState;
	
	private KeyManager keyManager;
	private Handler handler;
	private CollisionHandler collisionHandler;

	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();

	}
	
	private void init() {
		window = new Window(title, width, height);
		window.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		collisionHandler= new CollisionHandler(handler);
		handler.setCollisionHandler(collisionHandler);
		gameState.setCollisionHandler(handler.getCollisionHandler());
		menuState = new MenuState(handler);
		State.setState(gameState);

	}
	
	private void tick() {
		keyManager.tick();
		
		if (State.getState() != null) State.getState().tick();
	}
	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		//Draw start

		if (State.getState() != null) State.getState().render(g);
		//draw end

		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000. / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		@SuppressWarnings("unused")
		int ticks = 0;
		
		
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			//TODO remove fps counter
			if (timer>= 1000000000) {
//				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public synchronized void start() {
		
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		
		if(!running) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager; 
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public GameState getGameState() {
		return (GameState) gameState;
	}

	public CollisionHandler getCollisionHandler() {
		return collisionHandler;
	}

}
