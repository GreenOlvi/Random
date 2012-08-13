package game.level.tile;

import game.gfx.Screen;
import game.level.Level;


public class RockTile extends Tile {

	public RockTile(int id) {
		super(id);
	}
	
	@Override
	public void render(Screen screen, Level level, int x, int y) {
		int spriteIndex = 0;
		
		if (level.getTile(x, y - 1).id == id) {
			spriteIndex |= Tile.BIT_LINKS_ABOVE;
		}
		
		if (level.getTile(x, y + 1).id == id) {
			spriteIndex |= Tile.BIT_LINKS_BELOW;
		}
		
		if (level.getTile(x - 1, y).id == id) {
			spriteIndex |= Tile.BIT_LINKS_LEFT;
		}
		
		if (level.getTile(x + 1, y).id == id) {
			spriteIndex |= Tile.BIT_LINKS_RIGHT;
		}
		
		screen.render(x, y, 17);
	}
	

}
