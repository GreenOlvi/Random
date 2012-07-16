package game.gfx;

public class Screen {
	
	public final int width, height;
	public int[] pixels;
	
	private SpriteSheet sheet;

	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
	}

	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}
	
	public void renderFractal() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int draw = 1;
				if ((x & y) > 0)
					draw = 0;
				pixels[y * width + x] = (draw * x) << 16 | (draw * y) << 8 | (draw);
			}
		}	
	}
	
	public void render(int xp, int yp, int tile) {
		int xTile = tile % 32;
		int yTile = tile / 32;
		int toffs = xTile * 8 + yTile * 8 * sheet.width;
		
		for (int y = 0; y < 8; y++) {
			if (y + yp < 0 || y + yp > height) continue;
			for (int x = 0; x < 8; x++) {
				if (x + xp < 0 || x + xp > width) continue;
				pixels[(x + xp) + (y + yp) * width] = sheet.pixels[x + y * sheet.width + toffs];
			}
		}
	}
}
