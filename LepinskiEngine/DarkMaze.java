package LepinskiEngine;
public class DarkMaze implements Maze{
    MazeLocation[][] rooms;
    int max_x;
    int max_y;

    public MazeLocation getLocation(int x, int y){
	return rooms[x][y];
    }
    
    public void setLocation(int x, int y, MazeLocation loc){
	rooms[x][y] = loc;
    }

    public MazeLocation getTeamStart(){
	return null;
    }

    public int getMaxX(){
	return max_x;
    }

    public int getMaxY(){
	return max_y;
    }

    public int getNumCoin(){
	return 0;
    }

    public MazeRobot getRobot(int id){
	return null;
    }

    public DarkMaze(Maze the_maze){
	max_x = the_maze.getMaxX();
	max_y = the_maze.getMaxY();
	rooms = new MazeLocation[max_x][max_y];
	for(int i = 0; i<max_x; i++){
	    for(int j = 0; j<max_y; j++){
		rooms[i][j] = null;
	    }
	}
    }
}