package tiles;
import java.awt.image.BufferedImage;

import main.Assets;
public class SpringBlock extends Tile{
	
	private BufferedImage texture1, texture2;
	
	public SpringBlock(int id) {
		super(Assets.SpringUp, id);
		texture1 = Assets.SpringUp;
		texture2 = Assets.SpringDown;
		texture = texture1;
	}
	
	public void setTexture(int x) {
		if (x>0) texture = texture2;
		else texture = texture1;
	}
	
	
}
