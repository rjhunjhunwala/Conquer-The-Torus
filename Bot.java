/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toroidal.domination;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rohan
 */
public abstract class Bot {

	private static final ArrayList<Bot> bots = new ArrayList<>();
	private static final Spot[][] map = new Spot[500][500];

	static {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Spot();
			}
		}
	}
public static Color getColorAtSpot(int x,int y){
	x+=map.length;
	x%=map.length;
	y+=map.length;
	y%=map.length;
	return map[x][y].getColor();
}
public static Spot[][] getMap(){
	return map;
}
	public static void run() {
		GameFrame frame = new GameFrame();
		new Thread(() -> {
		 for (;;) {
				frame.repaint();
			}
		}).start();
		bots.clear();

		int x,y,team;

  bots.add(new HalfHeartedFloodFillBot(team=0,x=getRandom(0,map.length),y=getRandom(0,map[0].length)));
		map[x][y].colors[team]=255;
		bots.add(new FloodFillBot(team=1,x=getRandom(0,map.length),y=getRandom(0,map[0].length)));
		map[x][y].colors[team]=255;
		bots.add(new TrumpWithPaidAdvisors(team=2,x=getRandom(0,map.length),y=getRandom(0,map[0].length)));
		map[x][y].colors[team]=255;
		bots.add(new TrumpWithPaidAdvisors(team=2,x=getRandom(0,map.length),y=getRandom(0,map[0].length)));
		map[x][y].colors[team]=255;

		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException ex) {
				Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
			}
			for (Bot b : bots) {
				int move = b.move();
				int dX = (int) Math.cos(move * Math.PI / 2);
				int dY = (int) Math.sin(move * Math.PI / 2);
				int tX = b.x + dX;
				int tY = b.y + dY;
		tX+=map.length;
		tX%=map.length;
		tY+=map.length;
		tY%=map.length;
		//wrapping
				if (move < 4) {
					if (map[tX][tY].colors[b.team] > 0) {
						b.x = tX;
						b.y = tY;
					}
				} else {
					map[tX][tY].attack(b.team);
				}
			}
		}
	}

	/**
	 * Returns a random integer on the range [min,max) Psuedorandom implementation
	 * allows games to be deterministic
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	public static final int getRandom(int min, int max) {
		return (int) (min + r.nextDouble()*(max - min));
	}

	private int x;
	private int y;
	public final int team;
	public static final Random r = new Random(42); //the game should be deterministic

	public Bot(int team, int inX, int inY) {
		x = inX;
		y = inY;
		this.team = team;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Override this method 0-3 implies movement as so 3 (-y) (-x)2 0 (+x) 1 (+x)
	 * 4-7 implies an attack like so 7 (-y) (-x)6 4 (+x) 5 (+y)
	 */
	public abstract int move();

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	public static class GamePanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					g.setColor(map[i][j].getColor());
					g.fillRect(i*scale, j*scale, scale, scale);
				}
			}
		}
public static final int scale = 2;
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(map.length*scale, map[0].length*scale);
		}
	}

	public static class GameFrame extends JFrame {

		private static final long serialVersionUID = 1L;

		GameFrame() {
			super("Toroidal Domination");
			this.setVisible(true);
			this.setResizable(false);
			this.add(new GamePanel());
			this.pack();
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
