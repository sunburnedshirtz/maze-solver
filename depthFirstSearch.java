import java.util.*;

public class depthFirstSearch {

	private static int ROW;
	private static int COL;
	private int adjx;
	private int adjy;
	private static int[] threeAdj = { 7, 11, 13 };
	private static int[] adjRow = { 0, 1, 0, -1 };
	private static int[] adjCol = { -1, 0, 1, 0 };
	boolean[][] visited;

	public depthFirstSearch(int[][] arr) {
		arr.length-1 = ROW;
		arr[0].length-1 = COL;
		visited = new boolean[ROW][COL];
	}
	
	static class pair {
	
    public int first;
    public int second;
 
    public pair(int first, int second) {
      this.first = first;
      this.second = second;
    }
		
	}

	//helper methods because i suck at code
	
	private boolean contains(final int[] arr, final int key) {
    return Arrays.stream(arr).anyMatch(i -> i == key);
	}

	private pair adjN(int row, int col) {
		adjy = col - 1;
		return new pair(row, adjy);
	}

	private pair adjE(int row, int col) {
		adjx = row + 1;
		return new pair(adjx, col);
	}
	
	private pair adjS(int row, int col) {
		adjy = col + 1;
		return new pair(row, adjy);
	}
	
	private pair adjW(int row, int col) {
		adjx = row - 1;
		return new pair(adjx, col);
	}

	
	private boolean canVisit(boolean[][] visited, int row, int col){

		// OOB
    if (row < 0 || col < 0 || row >= ROW || col >= COL)
			{ return false; }
 
    if (visited[row][col])
			{ return false; }
 
    return true;
		
}
	
	public boolean[][] solve(int row, int col, int[][] arr, boolean[][]visited) {
		
		Stack<pair> coordStack = new Stack<pair>();
		coordStack.push(new pair(0, 0));

		while(!coordStack.empty()) {

			pair current = coordStack.pop();
			row = current.first;
			col = current.second;

			if(canVisit(visited, row, col)) {
				visited[row][col] = true;

				System.out.print(arr[row][col] + ", ");
				int adjCheck = arr[row][col];
				if(contains(threeAdj, adjCheck)) {
					// this is tiring and inefficient, rework later and then implement to display :P
					if((adjCheck & 1) == 0) {
						coordStack.push(adjS(row, col));
						coordStack.push(adjE(row, col));
						coordStack.push(adjW(row, col));
					}
					else if((adjCheck & 2) == 0) {
						coordStack.push(adjN(row, col));
						coordStack.push(adjE(row, col));
						coordStack.push(adjW(row, col));
					}
					else if((adjCheck & 4) == 0) {
						coordStack.push(adjS(row, col));
						coordStack.push(adjN(row, col));
						coordStack.push(adjE(row, col));
					}
					else {
						coordStack.push(adjS(row, col));
						coordStack.push(adjN(row, col));
						coordStack.push(adjW(row, col));
					}
				}
			
					}
				}
			}
		}
	}
}