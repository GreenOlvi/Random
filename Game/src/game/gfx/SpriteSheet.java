package game.gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
	public int width, height;
	public int tileWidth, tileHeight;
	public Image image;
	
	public ArrayList<Image> tiles;
	
	public SpriteSheet(BufferedImage image, int tileWidth, int tileHeight) {
		this.width = image.getWidth();
		this.height = image.getHeight();

		this.image = image;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
		int tilesPerLine = width / tileWidth;
		int tilesPerColumn = height / tileHeight;
		
		tiles = new ArrayList<Image>();
		
		for (int y = 0; y < tilesPerColumn; y++) {
			for (int x = 0; x < tilesPerLine; x++) {
				tiles.add(image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight));
			}
		}
	}
	
	
}
