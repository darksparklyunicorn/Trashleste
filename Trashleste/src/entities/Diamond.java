package entities;

import java.awt.Graphics;
import main.Assets;

public class Diamond extends Entity {

	private int tick = 0;
	private boolean currentlyActive;

	public Diamond(int x, int y, boolean currentlyActive) {
		super(x,y,8,11);
		this.currentlyActive = currentlyActive;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		if (currentlyActive) {
			if (tick <= 15) g.drawImage(Assets.dia1, (int) x*4, (int) y*4, width*4, height*4, null);
			else if (tick <= 30) g.drawImage(Assets.dia2, (int) x*4, (int) y*4, width*4, height*4, null);
			else if (tick <= 45) g.drawImage(Assets.dia3, (int) x*4, (int) y*4, width*4, height*4, null);
			else if (tick <= 60) g.drawImage(Assets.dia2, (int) x*4, (int) y*4, width*4, height*4, null);

		}
	}

	public void setTick(int tick) {
		if (currentlyActive) this.tick = tick;
	}
	
	public void setCurrentlyActive(boolean currentlyActive) {
		this.currentlyActive = currentlyActive;
	}


}
