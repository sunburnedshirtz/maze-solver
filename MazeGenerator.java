import java.util.*;

public class MazeGenerator {
	private int x;
	private int y;
	private int[][] maze;
	private int[][] mazeFlags;

	public MazeGenerator(int x, int y) {
		this.x = x;
		this.y = y;
		maze = new int[x][y];
		mazeFlags = new int[x][y];
		generateMaze(0, 0);

		// clears top left wall for entrance,
		if(maze[0][0] == 4) 
			{ maze[0][0] = 12; }
		else
			{ maze[0][0] = 10; }

		// and bottom right for exit. 
		// slightly longer cuz more edge cases for exits :P
		if(maze[x-1][y-1] == 8)
			{ maze[x-1][y-1] = 12; }
		else if (maze[x-1][y-1] == 1)
			{ maze[x-1][y-1] = 5; }
		else
			{ maze[x-1][y-1] = 13; }

		System.out.println(Arrays.deepToString(maze));
	}

	/* checks values of 2D array's ints in form of bits to help
	visualize where maze walls are located. 
	explained further in generation method down below */

	public void display() {
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 1) == 0) {
					System.out.print("# # ");
				}
				else {
					System.out.print("# . ");
				}
			}
			
			System.out.println("#   ");
			
			for (int j = 0; j < x; j++) {
				if((maze[j][i] & 8) == 0) {
					System.out.print("# . ");
				}
				else {
					System.out.print("  . ");
				}
			}
			
			/* removes bottom right's wall visually.
			just for looks cause need to maze solve and
			a visual exit looks nice :3 */
			if(i != maze[0].length-1) {
				System.out.println("#");
			}
			else {
				System.out.println(" ");
			}
		}
		
		for (int j = 0; j < x; j++) {
			System.out.print("# # ");
		}
		
		System.out.println("#");
		
	}

	/* still very confused on this portion of the code.
	recursive backtracking algorithm using an enum w directional
	values, getting swapped around with .shuffle, some weird
	bit manip. 

	1 - 12 integers mapped onto 2D array, each one
	representing some combination of N, S, E, W bits, which
	are also used to display for visualization. 

	e.x.
	
	int value of five = walled off east and south
	int value of twelve = walled off east and west
	int value of one = walled off east, south, west 

	very weird but functional! */
	
	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));	
		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y)
					&& (maze[nx][ny] == 0)) {
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}

	// helper method
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

	// all for maze generation algorithm
	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;

		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}

		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("How wide do you want your maze?: ");
		int x = sc.nextInt();
		System.out.print("How tall do you want your maze?: ");
		int y = sc.nextInt();
		MazeGenerator maze = new MazeGenerator(x, y);
		maze.display();
	}
	
}