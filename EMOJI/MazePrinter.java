package EMOJI;
/**
 *
 * @author ellie
 */
public class MazePrinter implements Observer<Maze>{
    
	public void update(Observable<Maze> maze) {
		//stubbed
	}
	
    public void printMaze(Maze maze) {
        System.out.println();
        for (int j = 0; j < maze.tiles[0].length; j++) {
            for (int i = 0; i < maze.tiles.length; i++) {
                System.out.print(maze.tiles[i][j].isWall() ? "#" : (maze.tiles[i][j].getContents().size() > 0 ?  maze.tiles[i][j].getContents().get(0).draw() : " "));
            }
            System.out.println();
        }
    }
}