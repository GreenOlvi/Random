package game.level;

import game.gfx.Screen;
import game.level.levelgen.LevelGen;
import game.level.tile.Tile;

public class Level {
	public int width, height;
	
	private byte[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = LevelGen.createMap(width, height);
	}
	
	public void renderBackground(Screen screen) {
		for (int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(screen, this, x, y);
			}
		}
	}

	private Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= width) return Tile.rock;
		return Tile.tiles[tiles[x + y * width]];
	}
}
