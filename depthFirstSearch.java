import java.util.*;

public class depthFirstSearch extends MazeGenerator {

	ArrayDeque<String> grid;
	
	public depthFirstSearch(ArrayDeque<String> grid) {
		super(grid);
	}
	
	protected boolean isCellUnvisited(int cell) {
		return grid.get(cell).equals("UNVISITED");
	}

	/**
	 * Tells if the given cell has already been visited by the maze generator.
	 * 
	 * @param cell grid cell
	 * @return {@code true} if cell has already been visited
	 */
	protected boolean isCellVisited(int cell) {
		return grid.get(cell).equals("VISITED");
	}

	/**
	 * Tells if the given cell has been completed by the maze generator.
	 * 
	 * @param cell grid cell
	 * @return {@code true} if cell has been completed
	 */
	protected boolean isCellCompleted(int cell) {
		return grid.get(cell).equals("COMPLETED");
	}
	
	public void createMaze(int x, int y) {
		Deque<Integer> stack = new ArrayDeque<>();
		int current = grid.cell(x, y);
		grid.set(current, VISITED);
		stack.push(current);
		while (!stack.isEmpty()) {
			int neighbor = randomElement(grid.neighbors(current).filter(this::isCellUnvisited))
					.orElse(Graph.NO_VERTEX);
			if (neighbor != Graph.NO_VERTEX) {
				grid.addEdge(current, neighbor);
				grid.set(neighbor, VISITED);
				stack.push(neighbor);
				current = neighbor;
			}
			else {
				grid.set(current, COMPLETED);
				// Note: current = stack.pop() would also be correct. The following lines
				// just give a better visualization.
				current = stack.peek();
				if (isCellCompleted(current)) {
					stack.pop();
				}
			}
		}
	}
}