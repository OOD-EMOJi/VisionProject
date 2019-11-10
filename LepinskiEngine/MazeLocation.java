package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;

//This class is similar to a Location except that it uses MazeRobots
// ... Instead of Robots

//It is important that MazeRobots are never handed to the player
// ... Because MazeRobots know about the structure of the Maze

public class MazeLocation{
 
    List<CoinType> the_coins;
    List<MazeRobot> the_robots;
    List<DirType> the_directions;
    int x_coord;
    int y_coord;
    boolean is_start;
    boolean is_scanned;

    public List<CoinType> getCoins(){
	return the_coins;
    }

    public List<MazeRobot> getRobots(){
	return the_robots;
    }

    public List<DirType> getDirections(){
	return the_directions;
    }

    public int getX(){
	return x_coord;
    }

    public int getY(){
	return y_coord;
    }

    public boolean isScanned(){
	return is_scanned;
    }

    public boolean isTeamStart(){
	return is_start;
    }

    void setTheCoins(List<CoinType> coins){
	the_coins = coins;
    }

    void setTheDirs(List<DirType> directions){
	the_directions = directions;
    }

    void setTheRobots(List<MazeRobot> robots){
	the_robots = robots;
    }

    void setScanned(boolean b){
	is_scanned = b;
    }

    void setStart(boolean b){
	is_start = b;
    }
    
    public MazeLocation(int x, int y){
	x_coord = x;
	y_coord = y;
	is_scanned = false;
	is_start = false;
	the_coins = null;
	the_robots = null;
	the_directions = null;
    }

    //This constructor does a Shallow Copy of a Location
    //    public MazeLocation(Location loc){
    //  x_coord = loc.getX();
    //	y_coord = loc.getY();
    //	is_start1 = loc.isTeamOneStart();
    //	is_start2 = loc.isTeamTwoStart();
    //	
    //	the_coins = new ArrayList<CoinType>(loc.getCoins());
    //	the_directions = new ArrayList<DirType>(loc.getDirections());
    //
    //	the_robots = new ArrayList<MazeRobot>();
    //	
    //	List<Robot> loc_bots = loc.getRobots(); 
    //	for(int i=0; i<loc_bots.size(); i++){
    //	    Robot old_bot = loc_bots.get(i);
    //	    MazeRobot new_bot = new MazeRobot(old_bot.getModel(), old_bot.getID(), old_bot.isTeamOne(), this);
    //	    the_robots.add(new_bot);
    //	}
    //    }
    
    
}