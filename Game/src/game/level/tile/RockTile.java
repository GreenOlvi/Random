package game.level.tile;

import game.gfx.Screen;
import game.level.Level;


public class RockTile extends Tile {

	public RockTile(int id) {
		super(id);
	}

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x * 8, y * 8, 32);
	}
	

}
