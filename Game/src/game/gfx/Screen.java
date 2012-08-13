package game.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Screen {
	
	public final int width, height;
	public Image image;
	private Graphics g;
	
	public SpriteSheet sheet;
	
	private int xOffset, yOffset;
	
	public static final int BIT_MIRROR_X = 1;
	public static final int BIT_MIRROR_Y = 2;

	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		
		xOffset = 0;
		yOffset = 0;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		g = image.getGraphics();
	}

	public void clear() {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
	}
	
	public void render(int xp, int yp, int tile) {
		render(xp, yp, tile, 0);
	}
	
	public void render(int xp, int yp, int tile, int bits) {
		xp *= sheet.tileWidth;
		yp *= sheet.tileHeight;
		xp -= xOffset;
		yp -= yOffset;
		
		boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
		boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;
		
		if (xp > - sheet.tileWidth && yp > - sheet.tileHeight && xp <= this.width && yp <= this.height) {
			g.drawImage(sheet.tiles.get(tile), xp, yp, 32, 32, null);
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
