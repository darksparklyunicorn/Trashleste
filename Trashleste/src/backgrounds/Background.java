package backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	
	public static Background[] backgrounds = new Background[16];
	public static Background magmaBlock = new MagmaBlock(0);
	
	
	
	
	protected BufferedImage texture;
	protected int id;
	
	public static final int TILEWIDTH = 128, TILEHEIGHT = 128;
	

	public Background(BufferedImage texture, int id) { 
		this.texture = texture;
		this.id = id;

		
		backgrounds[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public int getId() {
		return id;
	}
	
}
