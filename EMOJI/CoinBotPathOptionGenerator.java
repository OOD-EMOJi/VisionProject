package EMOJI;

import java.util.*;

public class CoinBotPathOptionGenerator implements PathOptionGenerator {

    public Maze maze;
    public List<PathOption> pathList;
    private Pathfinder pathfinder;
    private int height;
    private int width;
	private int firstTimeTurns;

    public CoinBotPathOptionGenerator(Maze maze) {
        this.maze = maze;
        this.height = maze.tiles[0].length;
        this.width = maze.tiles.length;
        this.pathfinder = new BreadthFirstSearchPathFinder(maze);
        pathList = new ArrayList<PathOption>();
        this.firstTimeTurns=0;
    }

    // generatePathOptions takes the robot location and the remaining num of turns
    public List<PathOption> generatePathOptions(int x, int y, int turns) {
        if (firstTimeTurns==0)firstTimeTurns= turns;
        pathList = new ArrayList<PathOption>();
        List<Tile> tiles;
		Comparator<PathOption> comparator;
        if(turns < (firstTimeTurns/2)){
            tiles = getCoins();
			comparator = new PathOption.GetCoinPathOptionComparator();
        }else{ 
			tiles = getDeadEndsTiles();
			comparator = new PathOption.DeadEndPathOptionComparator();
		}
        
        for (Tile tile : tiles) {
            PathOption option = new PathOption(pathfinder.findPath(maze.tiles[x][y], tile), turns);
            option.countPoints();
			
            if (option.path.size() > 0 && option.earlierFactor>0) {
                pathList.add(option);
            }
            //System.out.println(tile.getX() + " " + tile.getY() + " " + option);
        }
        //System.out.println(pathList);
        
        Collections.sort(pathList, comparator);
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
