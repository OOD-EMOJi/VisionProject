package LepinskiEngine;
import java.util.List;

public class CheckMoveNormal implements CheckMove{

    public boolean do_check(MazeRobot robot, CommandMove cm, Maze the_maze){
	MazeLocation loc = robot.getCurrentLoc();
	List<DirType> valid_dirs = loc.getDirections();
	DirType dir = cm.getDir();

	if (valid_dirs.contains(dir)){
	    return true;
	}
	else {
	    return false;
	}
    }

}