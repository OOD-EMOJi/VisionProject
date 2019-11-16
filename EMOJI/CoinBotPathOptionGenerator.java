import LepinskiEngine.*;
import java.util.*;

public class CoinBotPathOptionGenerator implements PathOptionGenerator{
    
    public Maze maze;
    public List<PathOption> pathList;
    private Pathfinder DFS; 
    private int  height ;
    private int  width ;
    
    public CoinBotPathOptionGenerator(Maze maze){
        this.maze= maze;
        this.height= maze.tiles[0].length ;
        this.width = maze.tiles.length ;
        this.DFS = new DepthFirstSearchPathFinder(maze);
    }
    
    // generatePathOptions takes the robot location and the remaining num of turns
    public List<PathOption> generatePathOptions(int x, int y, int turns){
        List<Tile> tiles = getDeadEndsTiles();
        for(Tile tile: tiles){
            pathList.add(new PathOption(DFS.findPath( maze.tiles[x][y],tile), turns));           
        }
        Collections.sort(pathList);
        return pathList;
    }
    
     private List<Tile> getDeadEndsTiles(){
         
			List<Tile> tileList = new ArrayList<Tile>();
            int[][] SHIFT = {
                {0 , 1}, // going right
                {1, 0}, // going down
                {0, -1}, // going left
                {-1, 0} // going up`
              };
            for(int x= 0; x < width; x++){
                for(int y= 0 ; y< height; y++){
                    int counter=0;
                    if(maze.tiles[x][y].isWall()== false){
                            for (int[] shift : SHIFT) {
                                int xtemp= x +shift[0];
                                int ytemp= y+ shift[1];  
                                if(maze.tiles[xtemp][ytemp].isWall()== false){
                                    counter++;
                                 }
                            } 
                            if(counter==3)tileList.add(maze.tiles[x][y]);
                            
                     }
                }
             }
             return tileList;
    
    }
}