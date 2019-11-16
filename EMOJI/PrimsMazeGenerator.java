package EMOJI;
import java.util.*;

public class PrimsMazeGenerator implements MazeGenerator {
	
	/**
		## Prim's Algorithm ##
	
		1. Start with a grid full of walls.
		2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
			a. While there are walls in the list:
				1. Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
					a. Make the wall a passage and mark the unvisited cell as part of the maze.
					b. Add the neighboring walls of the cell to the wall list.
				2. Remove the wall from the list.
		
		from https://en.wikipedia.org/wiki/Maze_generation_algorithm
		
	**/
	
	public Tile[][] generateMaze(int w, int h) {
		
		
		List<Tile> walls = new ArrayList<Tile>();
		
		w = 2 * w + 1;
		h = 2 * h + 1;
		
		// 1. Start with a grid full of walls.
		
		Tile[][] tiles = new Tile[w][h];
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				tiles[x][y] = new Tile(true, x, y);
			}
		}

		// 2. Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.
		Tile start = tiles[1][1];
		Tile end = tiles[w - 2][h - 2];
		
		start.makePath();
		
		walls.add(tiles[start.getX() - 1][start.getY()]);
		walls.add(tiles[start.getX() + 1][start.getY()]);
		walls.add(tiles[start.getX()][start.getY() - 1]);
		walls.add(tiles[start.getX()][start.getY() + 1]);
		
		//a. While there are walls in the list:
		while(!walls.isEmpty()) {
			
			//	1. Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
			int randomWallIndex = (int)(Math.random() * walls.size());
			
			Tile wall = walls.get(randomWallIndex);
			int x = wall.getX();
			int y = wall.getY();
			
			Tile candidate = null;
			if(x >= 1 && x < w - 1 && !tiles[x - 1][y].isWall() && tiles[x + 1][y].isWall()) {
				candidate = tiles[x + 1][y];
			}
			if(x >= 1 && x < w - 1 && !tiles[x + 1][y].isWall() && tiles[x - 1][y].isWall()) {
				candidate = tiles[x - 1][y];
			}
			if(y >= 1 && y < h - 1 && !tiles[x][y + 1].isWall() && tiles[x][y - 1].isWall()) {
				candidate = tiles[x][y - 1];
			}
			if(y >= 1 && y < h - 1 && !tiles[x][y - 1].isWall() && tiles[x][y + 1].isWall()) {
				candidate = tiles[x][y + 1];
			}
			
			
			
			
			if(candidate != null) {
				//a. Make the wall a passage and mark the unvisited cell as part of the maze.
				candidate.makePath();
				wall.makePath();
				
				int xx = candidate.getX();
				int yy = candidate.getY();
				
				//b. Add the neighboring walls of the cell to the wall list.
				for(int i = -1; i <= 1; i += 2) {
					if(xx > 0 && xx < w - 1 && tiles[xx + i][yy].isWall() && !walls.contains(tiles[xx + i][yy])) walls.add(tiles[xx + i][yy]);
					if(yy > 0 && yy < w - 1 && tiles[xx][yy + i].isWall() && !walls.contains(tiles[xx][yy + i])) walls.add(tiles[xx][yy + i]);
				}
			}
			
			//2. Remove the wall from the list
			walls.remove(wall);
			
			
		}
		
		tiles[0][1].makePath();
		tiles[w - 1][h - 2].makePath();
		return tiles;		
	}
}


















