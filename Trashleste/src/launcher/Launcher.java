package launcher;

import main.Game;

public class Launcher {
	public static void main(String[] args) {
		Game game = new Game("Trashleste V2", 512, 512);
		game.start();
	}

}
