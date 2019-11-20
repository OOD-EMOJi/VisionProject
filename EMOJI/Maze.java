package EMOJI;
import java.util.*;

/**
*	
**/
public class Maze implements Observable<Maze>{
    
	/**
	*	Underlying 2D Tile array. 
	**/
	Tile[][] tiles;
	
	/**
	*	Tiles marking the start and the end of the maze. (Entrance and Exit)
	**/
	private Tile start, end;
	
	/**
	*	Holding a MazeGenerator to use the Strategy pattern for
	*	various maze generation algorithms
	**/
    private MazeGenerator generator;

	private int[][] SHIFTS = new int[][]{
		{1,0},{-1,0},{0,1},{0,-1}
	};

	private List<Observer<Maze>> observers;

    public Maze(MazeGenerator generator) {
        this.generator = generator;
		this.observers = new LinkedList<Observer<Maze>>();
    }
	
    void generateMaze(int width, int height){
		//for (Tile[] a : tiles){
		//  for(Tile b : a){
		//  	b.makeWall();
		//	}
		//}
        tiles = generator.generateMaze( width, height );
		this.start = tiles[1][1];
		this.end = tiles[tiles.length - 2][tiles[0].length - 2];
    }
	
	public Tile getStart() {
		return start;
	}
	
	public Tile getEnd() {
		return end;
	}
	
	public void setStart(Tile start) {
		this.start = start;
	}
	
	public void setEnd(Tile end) {
		this.end = end;
	}
	
	public List<Tile> getNeighbors(Tile tile) {
		List<Tile> neighbors = new ArrayList<Tile>();
	
		int x = tile.getX();
		int y = tile.getY();
		
		for(int[] shift : SHIFTS) {
			int xx = x + shift[0];
			int yy = y + shift[1];
			if(xx >= 0 && xx < tiles.length && yy >= 0 && yy < tiles[xx].length) neighbors.add(tiles[xx][yy]);
		}
	
		return neighbors;
	}
	
	public void registerObserver(Observer<Maze> observer) {
		//stubbed
	}
	
	public void removeObserver(Observer<Maze> observer) {
		//stubbed
	}
	
	public void notifyObservers() {
		//stubbed
	}
}
