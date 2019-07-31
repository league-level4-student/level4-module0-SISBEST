package _04_Maze_Maker;

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
		int w1 = r.nextInt(w-1);
		int h1 = r.nextInt(h-1);
		selectNextPath(new Cell(w1, h1));

		return maze;
	}

	private static void selectNextPath(Cell currentCell) {
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> ngh = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (!ngh.isEmpty()) {

			// C1. select one at random.
			Random r = new Random();
			int a = r.nextInt(ngh.size());
			// C2. push it to the stack
			uncheckedCells.push(ngh.get(a));
			// C3. remove the wall between the two cells
			removeWalls(uncheckedCells.peek(), currentCell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = uncheckedCells.remove(0);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}

		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				Cell cc = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = cc;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell currentCell) {
		ArrayList<Cell> neighborsx = new ArrayList<>();
		if (currentCell.getX() != 0 && currentCell.getY() != 0) {
			if (maze.getCell(currentCell.getX() - 1, currentCell.getY() - 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() - 1, currentCell.getY() - 1));
			}
		}
		if (currentCell.getX() != 0) {
			if (maze.getCell(currentCell.getX() - 1, currentCell.getY()).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() - 1, currentCell.getY()));
			}
		}
		if (currentCell.getX() != 0 && currentCell.getY() != 4) {
			if (maze.getCell(currentCell.getX() - 1, currentCell.getY() + 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() - 1, currentCell.getY() + 1));
			}
		}
		if (currentCell.getY() != 0) {
			if (maze.getCell(currentCell.getX(), currentCell.getY() - 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX(), currentCell.getY() - 1));
			}
		}
		if (currentCell.getY() != 5) {
			if (maze.getCell(currentCell.getX(), currentCell.getY() + 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX(), currentCell.getY() + 1));
			}
		}
		if (currentCell.getX() != 5 && currentCell.getY() != 5) {
			if (maze.getCell(currentCell.getX() + 1, currentCell.getY() + 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() + 1, currentCell.getY() + 1));
			}
		}
		if (currentCell.getX() != 5) {
			if (maze.getCell(currentCell.getX() + 1, currentCell.getY()).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() + 1, currentCell.getY()));
			}
		}
		if (currentCell.getX() != 49 && currentCell.getY() != 0) {
			if (maze.getCell(currentCell.getX() + 1, currentCell.getY() - 1).hasBeenVisited()) {
				neighborsx.add(maze.getCell(currentCell.getX() + 1, currentCell.getY() - 1));
			}
		}
		return neighborsx;
	}
}
