package game.level.levelgen;

import game.level.tile.Tile;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LevelGen {

	public static void main(String[] args) {
		while (true) {
			int w = 128;
			int h = 128;

			byte[] map = LevelGen.createMap(w, h);

			BufferedImage img = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			int[] pixels = new int[w * h];

			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					int i = x + y * w;

					if (map[i] == Tile.grass.id)
						pixels[i] = 0x208020;
					if (map[i] == Tile.rock.id)
						pixels[i] = 0xa0a0a0;
				}
			}

			img.setRGB(0, 0, w, h, pixels, 0, w);
			JOptionPane.showMessageDialog(
					null,
					null,
					"Another",
					JOptionPane.YES_NO_OPTION,
					new ImageIcon(img.getScaledInstance(w * 4, h * 4,
							Image.SCALE_AREA_AVERAGING)));
		}
	}

	private static final Random random = new Random();

	public static byte[] createRandomMap(int w, int h) {
		byte[] map = new byte[w * h];
		for (int i = 0; i < map.length; i++) {
			map[i] = (byte) random.nextInt(2);
		}
		return map;
	}

	public static byte[] createMap(int width, int height) {
		byte[] map = createRandomMap(width, height);

		byte[] m2 = smooth(5, map, width, height);

		return m2;
	}

	
// From http://trystans.blogspot.ca/2011/08/roguelike-tutorial-03-scrolling-through.html
	
	private static byte[] smooth(int times, byte[] map, int width, int height) {
		byte[] map2 = new byte[width * height];
		for (int time = 0; time < times; time++) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int floors = 0;
					int rocks = 0;

					for (int ox = -1; ox < 2; ox++) {
						for (int oy = -1; oy < 2; oy++) {
							if (x + ox < 0 || x + ox >= width || y + oy < 0
									|| y + oy >= height)
								continue;

							if (map[x + ox + (y + oy) * width] == Tile.grass.id)
								floors++;
							else
								rocks++;
						}
					}
					map2[x + y * width] = floors >= rocks ? Tile.grass.id : Tile.rock.id;
				}
			}
			map = map2;
		}
		return map;
	}
}
