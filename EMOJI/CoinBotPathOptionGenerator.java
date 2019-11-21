/*

This class generates the possible pathOption list and then sort them, so the coin bot decide which 
one to choose.
The getCoins() method used to return a list of the all the tiles in the maze that has coins in it.
The generatePathOptions() method take the x and y positions of the coin bot and the number of turns. 
It uses the current location of the current bot as the starting tile for each pathOption. 

The main function of generatePathOptions() is to generate the shortest path from the coin bot location
to each tile that has a coin, using the getCoins() to get the ending tiles. It use Breadth first search to generate the path.

While it generates each path, it calculates the points in the path based on how many coins are there based on the remaining number of turn, 
in other words how many coins can the bot collect from this path in the amount of remaining turns 
It also calculates the earlier factor for each path to bused for the sorting comparison if the number of points is the same for two pathOptions

generatePathOptions
In generatePathOptions():
1] for each tile that has a coin
2] generate a path from the bot's tile to the tile that has the coin
3] add the pathOption to the pathOption list
4] sort the list and return it

*/
package EMOJI;

import java.util.*;

public class CoinBotPathOptionGenerator implements PathOptionGenerator {

    public Maze maze;
    public List<PathOption> pathList;
    private Pathfinder pathfinder;
    private int height;
    private int width;

    public CoinBotPathOptionGenerator(Maze maze) {
        this.maze = maze;
        this.height = maze.tiles[0].length;
        this.width = maze.tiles.length;
        this.pathfinder = new BreadthFirstSearchPathFinder(maze);
        pathList = new ArrayList<PathOption>();
    }

    // generatePathOptions takes the robot location and the remaining num of turns
    public List<PathOption> generatePathOptions(int x, int y, int turns) {
        pathList = new ArrayList<PathOption>();
        List<Tile> tiles = getCoins();
        for (Tile tile : tiles) {
            PathOption option = new PathOption(pathfinder.findPath(maze.tiles[x][y], tile), turns);
            option.countPoints();
			
            if (option.path.size() > 0) {
                pathList.add(option);
            }
            //System.out.println(tile.getX() + " " + tile.getY() + " " + option);
        }
        //System.out.println(pathList);
        
        Collections.sort(pathList);
        for (PathOption pathOption : pathList) {
            System.out.println(pathOption);
        }
        return pathList;
    }
	
	private List<Tile> getCoins() {

        List<Tile> tileList = new ArrayList<Tile>();
        int[][] SHIFT = {
            {0, 1}, // going right
            {1, 0}, // going down
            {0, -1}, // going left
            {-1, 0} // going up`
        };
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                        List<Thing> contents = maze.tiles[x][y].getContents();
                        for (Thing t : contents) {
                            if (t instanceof Coin) {
                                tileList.add(maze.tiles[x][y]);
                            }
                        }      
            }
        }
        return tileList;
    }

    private List<Tile> getDeadEndsTiles() {

        List<Tile> tileList = new ArrayList<Tile>();
        int[][] SHIFT = {
            {0, 1}, // going right
            {1, 0}, // going down
            {0, -1}, // going left
            {-1, 0} // going up`
        };
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int counter = 0;
                if (maze.tiles[x][y].isWall() == false) {
                    for (int[] shift : SHIFT) {
                        int xtemp = x + shift[0];
                        int ytemp = y + shift[1];
                        if (maze.tiles[xtemp][ytemp].isWall() == true) {
                            counter++;
                        }
                    }

                    if (counter == 3) {
                        //System.out.println("counter is 3");

                        tileList.add(maze.tiles[x][y]);
                    }

                }
            }
        }
        return tileList;

    }
}
