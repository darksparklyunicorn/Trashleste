package entities;

public class WallJump {

	private Player player;
	
	public WallJump (Player player) {

		this.player = player;
	}
	
	public void leftWallJump() {
		player.setxVel(player.getSpeedx());
		player.setyVel(-player.getSpeedy());
		player.setxAccel(0);
		player.setWalljumped(true);
	}
	
	public void rightWallJump() {
		player.setxVel(-player.getSpeedx());
		player.setyVel(-player.getSpeedy());
		player.setxAccel(0);
		player.setWalljumped(true);
	}

}
