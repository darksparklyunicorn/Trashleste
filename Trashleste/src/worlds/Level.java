package worlds;

import java.awt.Graphics;

import backgrounds.Background;
import main.Helpers;
import tiles.SpringBlock;
import tiles.Tile;

public class Level {

	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private int backgrounds;
	private boolean[][][] collisions;
	private int[][] diamondCoords;
	private int numOfDiamonds;
	private int[][] springTicks;
	private int numOfSprings;

	public Level(String path) {
		loadWorld(path);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		numOfSprings = 0;
		Background b = getBackground();
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				b.render(g, x*Background.TILEWIDTH, y*Background.TILEHEIGHT);
			}
		}


		for(int y = 0; y < height; y++) {
			for(int x = 0; x<width; x++) {
				getTile(x,y).render(g, x*Tile.TILEWIDTH, y*Tile.TILEHEIGHT);
			}
		}


	}

	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if(t==null) return Tile.Air;
		if(tiles[x][y]==24) {
		((SpringBlock)	t).setTexture(springTicks[numOfSprings][2]);
		numOfSprings++;
			
		}

		return t;
	}

	public Background getBackground() {
		Background b = Background.backgrounds[backgrounds];
		if(b==null) return Background.magmaBlock;
		return b;
	}

	private void loadWorld(String path) {
		String file = Helpers.loadFileToString(path); //TODO
		String[] tokens = file.split("\\s+");
		width = Helpers.parseInt(tokens[0]);
		height = Helpers.parseInt(tokens[1]);
		spawnX = Helpers.parseInt(tokens[2]);
		spawnY = Helpers.parseInt(tokens[3]);

		numOfSprings = 0;
		tiles = new int[width][height];
		collisions = new boolean[width][height][4];
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				int temp = Helpers.parseInt(tokens[(x+y*width) +4]);
				tiles[x][y] = temp;
				collisions[x][y] = Tile.collisions[temp]; 
				if (temp == 24) numOfSprings++;
			}
		}

		springTicks = new int[numOfSprings][3]; //x,y,ticks
		int f=0;
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				if(tiles[x][y] == 24) {
					springTicks[f][0] = x;
					springTicks[f][1] = y;
					springTicks[f][2] = 0;
					f++;
				}
			}
		}


		backgrounds = Helpers.parseInt(tokens[260]);

		numOfDiamonds = Helpers.parseInt(tokens[261]);
		diamondCoords = new int[numOfDiamonds][2];
		for(int i=0; i<numOfDiamonds; i++) {
			diamondCoords[i][0] = Helpers.parseInt(tokens[2*i+262]);
			diamondCoords[i][1] = Helpers.parseInt(tokens[2*i+263]);
		}


	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public boolean[][][] getCollisions() {
		return collisions;
	}

	public int[][] getTiles() {
		return tiles;
	}

	public int[][] getDiamondCoords() {
		return diamondCoords;
	}

	public int[][] getSpringTicks() {
		return springTicks;
	}



}
