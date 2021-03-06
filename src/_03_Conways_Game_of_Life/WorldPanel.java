package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;
	Cell[][] cells;
	int[][] livingNeighbors;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		cellSize = w / cpr;
		cells = new Cell[cellsPerRow][cellsPerRow];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i * cellSize, j * cellSize, cellSize);
			}
		}
	}

	public void randomizeCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				Random r = new Random();
				int lod = r.nextInt(2);
				switch (lod) {
				case 0:
					cells[i][j].isAlive = false;
					break;
				case 1:
					cells[i][j].isAlive = true;
					break;
				}

			}
		}
		repaint();
	}

	public void clearCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j].draw(g);
			}
		}
		g.setColor(Color.CYAN);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	public void step() {
		livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				livingNeighbors[i][j] = getLivingNeighbors(i, j);
			}
		}
		for (int i = 0; i < cellsPerRow; i++) {
			for (int j = 0; j < cellsPerRow; j++) {
				cells[i][j].liveOrDie(livingNeighbors[i][j]);
			}
		}
		repaint();
	}

	public int getLivingNeighbors(int x, int y) {
		int neighbors = 0;
		if (x != 0 && y != 0) {
			if (cells[x - 1][y - 1].isAlive) {
				neighbors++;
			}
		}
		if (x != 0) {
			if (cells[x - 1][y].isAlive) {
				neighbors++;
			}
		}
		if (x != 0 && y != 49) {
			if (cells[x - 1][y + 1].isAlive) {
				neighbors++;
			}
		}
		if (y != 0) {
			if (cells[x][y - 1].isAlive) {
				neighbors++;
			}
		}
		if (y != 49) {
			if (cells[x][y + 1].isAlive) {
				neighbors++;
			}
		}
		if (x != 49 && y != 49) {
			if (cells[x + 1][y + 1].isAlive) {
				neighbors++;
			}
		}
		if (x != 49) {
			if (cells[x + 1][y].isAlive) {
				neighbors++;
			}
		}
		if (x != 49 && y != 0) {
			if (cells[x + 1][y - 1].isAlive) {
				neighbors++;
			}
		}
		System.out.println(neighbors);
		return neighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int $x = e.getX() / cellSize;
		int $y = e.getY() / cellSize;
		if (cells[$x][$y].isAlive == true) {
			cells[$x][$y].isAlive = false;
		} else if (cells[$x][$y].isAlive == false) {
			cells[$x][$y].isAlive = true;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
