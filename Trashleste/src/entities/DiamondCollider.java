package entities;

import java.awt.Graphics;

import main.Handler;

public class DiamondCollider {
	private Handler handler;
	private Player player;
	private int[][] diamondCoords;
	private int[] diamondTicks;
	private Diamond diamond;

	public DiamondCollider(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		diamond = new Diamond(0,0,false);
		diamondCoords = handler.getLevel().getDiamondCoords();
		diamondTicks = new int[diamondCoords.length];
		for (int i=0; i<diamondTicks.length; i++) {
			diamondTicks[i] = (15*i)%60;
		}
	}

	private void collideWithDiamond() {
		float x = player.getX();
		float y = player.getY();
		for (int i=0; i<diamondCoords.length; i++) {
			if (x+11 >= diamondCoords[i][0] && x+6 <= diamondCoords[i][0] + 8) {
				if (y+8 >= diamondCoords[i][1] +1 && y+3 <= diamondCoords[i][1] + 9) {
					if (y+8 >= -(x+11 - diamondCoords[i][0]) + diamondCoords[i][1] + 5 && y+3 <= -(x+6 - diamondCoords[i][0]-4) + diamondCoords[i][1] + 9) { 
						if (y+8 >= (x+6 - diamondCoords[i][0]-4) + diamondCoords[i][1] + 1 && y+3 <= (x+11 - diamondCoords[i][0]) + diamondCoords[i][1] + 5) {
							if (diamondTicks[i] <= 60 && !player.isHasDash()) {
								diamondTicks[i] = 61;
								player.setHasDash(true);
							}
						}
					}
				}
			}


		}
	}
	
	public void tick() {
		collideWithDiamond();
		for (int i=0; i<diamondTicks.length; i++) {
			diamondTicks[i]++;
			if (diamondTicks[i] == 60 || diamondTicks[i]>=200) diamondTicks[i] = 0;
		}
	}

	public void render(Graphics g) {
		if (diamondCoords.length>0) {
			diamond.setCurrentlyActive(true);
			for (int i=0; i<diamondCoords.length; i++) {
				diamond.setX(diamondCoords[i][0]);
				diamond.setY(diamondCoords[i][1]);
				diamond.setTick(diamondTicks[i]);
				diamond.render(g);

			}
		}
		else diamond.setCurrentlyActive(false);

	}
	
	public void respawn() {
		diamondCoords = handler.getLevel().getDiamondCoords();
		diamondTicks = new int[diamondCoords.length];
		for (int i=0; i<diamondTicks.length; i++) {
			diamondTicks[i] = (15*i)%60;
		}
	}


}
