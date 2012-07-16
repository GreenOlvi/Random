package game.level.tile;

import game.gfx.Screen;
import game.level.Level;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grass = new GrassTile(0);
	public static Tile rock = new RockTile(1);
	
	public final byte id;
	
	public Tile(int id) {
		this.id = (byte) id;
		if (tiles[id] != null) throw new RuntimeException("Duplicate tile ids");
		tiles[id] = this;
	}
	
	public void render(Screen screen, Level level, int x, int y) {
	}
}
