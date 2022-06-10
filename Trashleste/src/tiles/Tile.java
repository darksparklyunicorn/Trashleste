package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	public static Tile RoundRock = new RoundRock(0);
	public static Tile RightEndRock = new RightEndRock(1);
	public static Tile LeftEndRock = new LeftEndRock(2);
	public static Tile BottomEndRock = new BottomEndRock(3);
	public static Tile TopEndRock = new TopEndRock(4);
	public static Tile HorizontalRock = new HorizontalRock(5);
	public static Tile VerticalRock = new VerticalRock(6);
	public static Tile TLCornerRock = new TLCornerRock(7);
	public static Tile TRCornerRock = new TRCornerRock(8);
	public static Tile BLCornerRock = new BLCornerRock(9);
	public static Tile BRCornerRock = new BRCornerRock(10);
	public static Tile TopEdgeRock = new TopEdgeRock(11);
	public static Tile BotEdgeRock = new BotEdgeRock(12);
	public static Tile LeftEdgeRock = new LeftEdgeRock(13);
	public static Tile RightEdgeRock = new RightEdgeRock(14);
	public static Tile RegCenterRock = new RegCenterRock(15);
	public static Tile GoldCenterRock = new GoldCenterRock(16);
	public static Tile BreakRock = new BreakRock(18);
	public static Tile Air = new Air(19);
	public static Tile SpikeUp = new SpikeUp(20);
	public static Tile SpikeLeft = new SpikeLeft(21);
	public static Tile SpikeDown = new SpikeDown(22);
	public static Tile SpikeRight = new SpikeRight(23);
	public static Tile SpringBlock = new SpringBlock(24);
	public static Tile EndCrystal = new EndCrystal(25);


	public static boolean[][] collisions = //left right up down
		{{true,true,true,true},
				{false,true,true,true},
				{true,false,true,true},
				{true,true,false,true},
				{true,true,true,false},
				{false,false,true,true},
				{true,true,false,false},
				{true,false,true,false},
				{false,true,true,false},
				{true,false,false,true},
				{false,true,false,true},
				{false,false,true,false},
				{false,false,false,true},
				{true,false,false,false},
				{false,true,false,false},
				{false,false,false,false},
				{false,false,false,false},
				{false,false,false,false},
				{true,true,true,true},
				{false,false,false,false},
				{true,true,true,false},
				{true,false,true,true},
				{true,true,false,true},
				{false,true,true,true},
				{true,true,true,false},
				{false,false,false,false}
		};


	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) { 
		this.texture = texture;
		this.id = id;


		tiles[id] = this;
	}


	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		if(this instanceof EndCrystal)
			g.drawImage(texture, x, y, TILEWIDTH*3, TILEHEIGHT*3, null);
		else 
			g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public int getId() {
		return id;
	}



}
