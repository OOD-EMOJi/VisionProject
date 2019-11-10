package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;

//getDirections returns the directions one can move from this location
//... That is, the directions that are not blocked by a wall

//isScanned returns true if the location is within scanner range
//... If this function returns true then all information will be present 
//... If this function is false then some information may be missing
//... If information is missing because the location is not scanned
//    then the corresponding function will return null

//isTeamStart returns true if the location is the starting point for Team 1

//getX and getY return the coordinates of the location
//... (0,0) is the north-west corner of the maze

public class Location{
    List<CoinType> the_coins;
    List<Robot> the_robots;
    List<DirType> the_directions;
    int x_coord;
    int y_coord;
    boolean is_start;
    boolean is_scanned;

    public List<CoinType> getCoins(){
	return the_coins;
    }

    public List<Robot> getRobots(){
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

    void setTheRobots(List<Robot> robots){
	the_robots = robots;
    }

    void setScanned(boolean b){
	is_scanned = b;
    }

    void setStart(boolean b){
	is_start = b;
    }
    
    public Location(int x, int y){
	x_coord = x;
	y_coord = y;
	is_scanned = false;
	is_start = false;
	the_coins = null;
	the_robots = null;
	the_directions = null;
    }

    //Transform a Location into a MazeLocation 
    //Note: Currently does only a shallow copy of Coins
    public Location(MazeLocation loc){
	x_coord = loc.getX();
	y_coord = loc.getY();
	is_scanned = loc.isScanned();
	is_start = loc.isTeamStart();
	the_coins = loc.getCoins();
	the_directions = loc.getDirections();
	
	if (loc.getRobots() != null){
	    the_robots = new ArrayList<Robot>();
	    for (MazeRobot bot : loc.getRobots()){
		Robot temp_bot = new Robot(bot.getModel(), bot.getID());
		the_robots.add(temp_bot);
	    }
	}
    }		 

}