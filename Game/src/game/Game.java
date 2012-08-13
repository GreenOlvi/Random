package game;

import game.entity.Player;
import game.gfx.Screen;
import game.gfx.SpriteSheet;
import game.level.Level;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Game";
	public static final int HEIGHT = 400;
	public static final int WIDTH = 640;
	public static final int SCALE = 2;

	private Screen screen;
	private InputHandler input = new InputHandler(this);
	private int xOffset = 0;
	private int yOffset = 0;
	
	private Level level;
	private Player player;

	public static void main(String[] args) {
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	private boolean running;

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		screen.clear();
		level.renderBackground(screen);
		
		player.render(screen);
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());

		int ww = WIDTH * SCALE;
		int hh = HEIGHT * SCALE;
		int xo = (getWidth() - ww) / 2;
		int yo = (getHeight() - hh) / 2;
		g.drawImage(screen.image, xo, yo, ww, hh, null);
		g.dispose();
		bs.show();
	}

	private void tick() {
		if (input.up.down) yOffset--;
		if (input.down.down) yOffset++;
		if (input.left.down) xOffset--;
		if (input.right.down) xOffset++;
		screen.setOffset(xOffset, yOffset);
	}

	private void init() {
		try {
			this.screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/grass_rock.png")), 32, 32));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		level = new Level(100, 100);
		player = new Player();
	}

}
