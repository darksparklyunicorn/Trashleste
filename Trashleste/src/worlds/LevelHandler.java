package worlds;

public class LevelHandler {
	private Level[] levels;
	private int NumberOfLevels;
	
	public LevelHandler(int NumberOfLevels) {
		this.NumberOfLevels = NumberOfLevels;
		levels = new Level[NumberOfLevels];
		for (int i=0; i<NumberOfLevels; i++) {
			levels[i] = new Level(String.format("/Levels/level%d",i));
		}
	}
	
	public Level getLevel(int id) {
		return levels[id];
	}
	
	public int getNumberOfLevels() {
		return NumberOfLevels;
	}

}
