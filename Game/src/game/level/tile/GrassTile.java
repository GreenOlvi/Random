package game.level.tile;

import game.gfx.Screen;
import game.level.Level;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(id);
	}

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, 0);
	}

}
