package EMOJI;
import java.util.Arrays;

public class Driver {
	public static void main(String[] args) {
		MazePrinter mazePrinter = new MazePrinter();
		Maze maze = new Maze(new PrimsMazeGenerator());
		maze.generateMaze(10,10);
		mazePrinter.printMaze(maze);
	}
}