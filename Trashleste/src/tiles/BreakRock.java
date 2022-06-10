package tiles;

import java.awt.image.BufferedImage;

import main.Assets;

public class BreakRock extends Tile{
	
	private BufferedImage texture0, texture1, texture2, texture3;
	
	public BreakRock(int id) {
		super(Assets.BreakRock0, id);
		texture0 = Assets.BreakRock0;
		texture1 = Assets.BreakRock1;
		texture2 = Assets.BreakRock2;
		texture3 = Assets.Air;
	}
	
	public void setTexture(int x) {
		if (x>90) texture = texture0;
		else if (x>80) texture = texture1;
		else if (x>70) texture = texture2;
		else if (x>0) texture = texture3;
	}

}
