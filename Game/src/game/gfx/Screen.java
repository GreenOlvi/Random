package game.gfx;

public class Screen {
	
	public final int width, height;
	public int[] pixels;
	
	private SpriteSheet sheet;
	
	private int xOffset, yOffset;
	
	public static final int BIT_MIRROR_X = 1;
	public static final int BIT_MIRROR_Y = 2;

	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		
		xOffset = 0;
		yOffset = 0;
		
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
		render(xp, yp, tile, 0);
	}
	
	public void render(int xp, int yp, int tile, int bits) {
		xp -= xOffset;
		yp -= yOffset;
		
		boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
		boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;
		
		int xTile = tile % 32;
		int yTile = tile / 32;
		int toffs = xTile * 8 + yTile * 8 * sheet.width;
		
		for (int y = 0; y < 8; y++) {
			if (y + yp < 0 || y + yp >= height) continue;
			int ys = mirrorY ? 7 - y : y;
			for (int x = 0; x < 8; x++) {
				if (x + xp < 0 || x + xp >= width) continue;
				int xs = mirrorX ? 7 - x : x;
				pixels[(x + xp) + (y + yp) * width] = sheet.pixels[xs + ys * sheet.width + toffs];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
