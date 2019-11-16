package EMOJI;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

/**
*	The DepthFirstSearchMazeGenerator uses a Stack and two boolean arrays to implement maze generation.
* 	The paths are created much like a mole in a nest. It digs tunnels until it reaches a point in the 
*	maze it has already visited then backtracks until it can move randomly again.
*	
*	I used this algorithm to implement this: https://en.wikipedia.org/wiki/Maze_generation_algorithm#Depth-first_search
*	IMPORTANT: A modification to this algorithm had to be made to compensate for our differing maze structure.
*	If you see the mazes on the wikipedia page, they use a maze with each Tile having four walls and removing those 
*	when creating the maze. Because our Maze is made of walls and spaces this was tricky to implement.
*	
*	I had to expand the dimensions of the maze so that instead of checking the tile next to it, it checked the tile 2 away from it.
*	This is because the 4 tiles surrounding the Tile is considered a wall in the other structure. This leads to a resulting Tile[][]
* 	with dimensions:	(2 * w + 1) x (2 * h + 1)
*	
**/
public class DepthFirstSearchMazeGenerator implements MazeGenerator {


	/**
	*	ALGORITHM:
	*
	*	1. Make the initial cell the current cell and mark it as visited
	*	2. While there are unvisited cells
	*		3. If the current cell has any neighbours which have not been visited
	*			4. Choose randomly one of the unvisited neighbours
	*			5. Push the current cell to the stack if it has more than one unvisited neighbor
	*			6. Remove the wall between the current cell and the chosen cell
	*			7. Make the chosen cell the current cell and mark it as visited
	*		8. Else if stack is not empty
	*			9. Pop a cell from the stack while the stack is not empty and the popped cell has no unvisited neighbors
	*			10. Make it the current cell
	*
	**/
    public Tile[][] generateMaze(int w, int h) {
		//setting up variables
        int width = 2 * w + 1;
        int height = 2 * h + 1;

        boolean[][] tiles = new boolean[width][height];
		boolean[][] visited = new boolean[width][height];
		
		Stack<int[]> stack = new Stack<int[]>();
		
		//making borders walls
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(x == 0) visited[x][y] = true;
				if(y == 0) visited[x][y] = true;
				if(x == width - 1) visited[x][y] = true;
				if(y == height - 1) visited[x][y] = true;
			}
		}
		
		//setting maze entrance and exit
        tiles[0][1] = true; // TODO: make this a random spot on the left
		tiles[width - 1][height - 2] = true;
		
		// step 1
		int x = 1;
		int y = 1;

		tiles[1][1] = true;
		visited[x][y] = true;
		
		//step 2
		while(areUnvisitedCells(visited)) {	
			ArrayList<int[]> unvisitedNeighbors = getUnvisitedNeighbors(visited, x, y);
			int size = unvisitedNeighbors.size();
			
			//step 3
			if(size > 0) {
				//step 4
				int rand = (int)(Math.random() * size);
				
				//step 5
				if(size > 1) {
					stack.push(new int[]{x,y});
				}
				int[] next = unvisitedNeighbors.get(rand);
					
				//step 6
				//removing walls
				tiles[next[0]][next[1]] = true;
				int wx = (x + next[0]) / 2;
				int wy = (y + next[1]) / 2;
				tiles[wx][wy] = true;
				
				//step 7
				//marking cell
				x = next[0];
				y = next[1];
				visited[x][y] = true;
			}
			else { // step 8
				while(getUnvisitedNeighbors(visited, x, y).size() == 0 && !stack.isEmpty()) {
					//step 9
					int[] t = stack.pop(); 
					// step 10
					x = t[0]; 
					y = t[1];
				}
			}
		}
		
		Tile[][] ret = new Tile[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				ret[i][j] = new Tile(!tiles[i][j], i, j);
			}
		}
		return ret;
    }
	
	//helper function for the generateMaze method
	private ArrayList<int[]> getUnvisitedNeighbors(boolean[][] visited, int x, int y) {
		ArrayList<int[]> ret = new ArrayList<int[]>();
		if( x - 2 >= 0 && !visited[x - 2][y]) {
			ret.add(new int[]{x - 2, y});
		}
		if( x + 2 < visited.length && !visited[x + 2][y]) {
			ret.add(new int[]{x + 2, y});
		}
		if( y - 2 >= 0 && !visited[x][y - 2]) {
			ret.add(new int[]{x, y - 2});
		}
		if( y + 2 < visited[x].length && !visited[x][y + 2]) {
			ret.add(new int[]{x, y + 2});
		}
		return ret;
	}
	
	//helper function for the generateMaze method.
	private boolean areUnvisitedCells(boolean[][] visited) {
		for(int x = 1; x < visited.length; x+=2) {
			for(int y = 1; y < visited[x].length; y+=2) {
				if(visited[x][y] == false) {
					return true;
				}					
			}
		}
		return false;
	}
}
