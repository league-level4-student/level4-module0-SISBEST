package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Drawable {
	public boolean isAlive = false;

	private int x;
	private int y;

	private int cellSize;

	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.cellSize = size;
	}

	public void liveOrDie(int numNeighbors) {
		if (isAlive) {
			if (numNeighbors < 2) {
				isAlive = false;
			} else if (numNeighbors == 2 || numNeighbors == 3) {
				isAlive = true;
			} else if (numNeighbors > 3) {
				isAlive = false;
			}
		}
		if (isAlive == false && numNeighbors == 3) {
			isAlive = true;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void draw(Graphics g) {
		if (isAlive == false) {
			g.setColor(Color.BLUE);
		}
		if (isAlive) {
			g.setColor(Color.CYAN);
		}
		g.fillRect(x, y, cellSize, cellSize);
		g.drawRect(x, y, cellSize, cellSize);
	}
}
