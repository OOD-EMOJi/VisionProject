package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;

public class ScanMethodNormal implements ScanMethod{
    public int radius;

    public ScanMethodNormal(int rad){
	radius = rad;
    }

    public MazeLocation findNeighbor(MazeLocation loc, DirType dir, Maze the_maze){
	int x = loc.getX();
	int y = loc.getY();
	MazeLocation new_loc = null;

	if (dir == DirType.North){
	    if (y > 0){
		new_loc = the_maze.getLocation(x, y-1);
	    }
	}
	if (dir == DirType.South){
	    if (y < the_maze.getMaxY()){
		new_loc = the_maze.getLocation(x, y+1);
	    }
	}
	if (dir == DirType.West){
	    if (x > 0){
		new_loc = the_maze.getLocation(x-1, y);
	    }
	}
	if (dir == DirType.East){
	    if (y < the_maze.getMaxY()){
		new_loc = the_maze.getLocation(x+1, y);
	    }
	}

	return new_loc;
    }

    public void doScan(MazeRobot bot, Maze the_maze, DarkMaze dark_maze){
	MazeLocation cur_loc = bot.getCurrentLoc();
	List<MazeLocation> dist_cur = new ArrayList<MazeLocation>();
	List<MazeLocation> dist_next;

	dist_cur.add(cur_loc);

	for(int i=0; i<radius; i++){
	    dist_next = new ArrayList<MazeLocation>();
	    
	    for(MazeLocation loc : dist_cur){
		dist_next.add(loc);

		for(DirType d : DirType.values() ){
		    MazeLocation neigh = findNeighbor(loc, d, the_maze);
		    if (neigh != null){
			dist_next.add(neigh);
		    }

		}
	    }
	    
	    dist_cur = dist_next;
	}

	for(MazeLocation l : dist_cur){
	    addToDark(dark_maze, l);
	}
    }

    public void addToDark(DarkMaze dark, MazeLocation loc){
	int x = loc.getX();
	int y = loc.getY();
	MazeLocation dark_loc = new MazeLocation(x,y);

	dark_loc.the_coins = loc.getCoins();
	dark_loc.the_robots = loc.getRobots();
	dark_loc.the_directions = loc.getDirections();
	dark_loc.is_start = loc.is_start;
	dark_loc.is_scanned = true;
	dark.setLocation(x,y,dark_loc);
	    
    }

}