package EMOJI;
/**
*	An interface to encapsulate the methods for MazeGeneration into a Strategy pattern.
*	A Maze has a member field of this type.
**/
public interface MazeGenerator {
    Tile[][] generateMaze(int w, int h);
}