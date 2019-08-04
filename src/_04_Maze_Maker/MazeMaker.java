package _04_Maze_Maker;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);
		Random r = new Random();
		int w1 = r.nextInt(w - 1);
		int h1 = r.nextInt(h - 1);
		maze.getCell(0, 0).setNorthWall(false);
		selectNextPath(maze.getCell(w1, h1));
		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		currentCell.setBeenVisited(true);

		ArrayList<Cell> ngh = getUnvisitedNeighbors(currentCell);
		if (!ngh.isEmpty()) {

			Random r = new Random();
			int a = r.nextInt(ngh.size());
			uncheckedCells.push(ngh.get(a));
			removeWalls(ngh.get(a), currentCell);
			currentCell = uncheckedCells.pop();
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}

		else {
			if (!uncheckedCells.isEmpty()) {
				Cell cc = uncheckedCells.pop();
				currentCell = cc;
				selectNextPath(currentCell);
			}
		}

	}

	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() < c2.getY()) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			} else {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
			}
		} else {
			if (c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}
		}
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell currentCell) {
		ArrayList<Cell> neighborsx = new ArrayList<>();

		if (currentCell.getX() != 0) {
			if (!maze.getCell(currentCell.getX() - 1, currentCell.getY()).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() - 1, currentCell.getY()));
			}
		}
		if (currentCell.getY() != 0) {
			if (!maze.getCell(currentCell.getX(), currentCell.getY() - 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX(), currentCell.getY() - 1));
			}
		}
		if (currentCell.getY() != 4) {
			if (!maze.getCell(currentCell.getX(), currentCell.getY() + 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX(), currentCell.getY() + 1));
			}
		}
		if (currentCell.getX() != 4) {
			if (!maze.getCell(currentCell.getX() + 1, currentCell.getY()).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() + 1, currentCell.getY()));
			}
		}
		return neighborsx;
	}
}
