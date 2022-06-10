package entities;

public class Dash {
	
	private static final float dashGravity = .175f;
	private static final float dashXAccel = .15f;
	private boolean left, right, up, down;
	private Player player;
	
	public Dash(Player player, boolean left, boolean right, boolean up, boolean down) {
		this.player = player;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}
	
	public void dash() {
		if(left^right) {
			if (left) player.setxVel(-player.getDashVel()*1.5f);
			if (right) player.setxVel(player.getDashVel()*1.5f);
				player.setxAccel(dashXAccel);
				player.setyVel(0);
				player.setDashHorizontal(true);
			
		}
		else {
			if (left && right) {
				player.setxVel(player.getDashVel()*1.5f); //default right
				player.setxAccel(dashXAccel);
				player.setDashHorizontal(true);
			}
			else {
				player.setxVel(0);
				player.setDashHorizontal(false);
			}
		}
		
		if(up^down) {
			if (up) player.setyVel(-player.getDashVel());
			if (down) player.setyVel(player.getDashVel());
			player.setGravity(dashGravity);
			player.setDashVertical(true);
		}
		else {
			if (up && down) {
				player.setyVel(-player.getDashVel()); //default up
				player.setGravity(dashGravity);
				player.setDashVertical(true);
			}
			else {
				player.setyVel(0);
				player.setDashVertical(false);
			}
			
		}
		
		if( !(left|right|up|down)) {
			if (player.isFacingRight()) player.setxVel(player.getDashVel()*1.5f);
			else player.setxVel(player.getDashVel()*-1.5f);
			player.setxAccel(dashXAccel);
			player.setDashHorizontal(true);
			player.setDashVertical(false);
		}
		

	}
	
	public void updateDash(boolean left, boolean right, boolean up, boolean down) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}
	

}
