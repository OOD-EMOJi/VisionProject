package EMOJI;
import LepinskiEngine.*;
import java.util.*;

public class MazeAdapter extends Maze {
	
	GameState gameState;
	
	public MazeAdapter(GameState gameState) {
		super(new FilledMazeGenerator());
		this.gameState = gameState;
		this.generateMaze(2 * gameState.maze_size_x + 1, 2 * gameState.maze_size_y + 1);
	}
	
	@Override
	public void generateMaze(int w, int h) {
		super.generateMaze(2 * gameState.maze_size_x + 1, 2 * gameState.maze_size_y + 1);
	}
	
	public void generateMaze() {
		generateMaze(0,0);
	}
	
	public void updateMaze(List<Location> locations) {
		for(Location location : locations) {
			
			//calculate x and y in our maze (mx , my)
			int mx = location.getX() * 2 + 1;
			int my = location.getY() * 2 + 1;
			
			Tile tile = this.tiles[mx][my];
			tile.makePath();
			//update walls for each tile
			for(DirType dir : location.getDirections()) {
				if(dir == DirType.East) {
					this.tiles[mx + 1][my].makePath();
				}
				if(dir == DirType.West) {
					this.tiles[mx - 1][my].makePath();
				}
				if(dir == DirType.North) {
					this.tiles[mx][my - 1].makePath();
				}
				if(dir == DirType.East) {
					this.tiles[mx][my + 1].makePath();
				}
			}
			
			tile.clearContents();
			for(CoinType coin : location.getCoins()) {
				tile.addThing(new Coin(mx,my,1));
			}
		}
	}
}