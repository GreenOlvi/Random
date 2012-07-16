package game.level;

import game.gfx.Screen;
import game.level.tile.Tile;

public class Level {
	
	public int width, height;
	
	private byte[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new byte[width * height];
		
		for (int i = 0; i < width * height; i++) {
			tiles[i] = 0;
		}
	}
	
	public void renderBackground(Screen screen) {
		for (int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(screen, this, x, y);
			}
		}
	}

	private Tile getTile(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < width) {
			return Tile.tiles[tiles[x + y * width]];
		}
		return Tile.grass;
	}
}
