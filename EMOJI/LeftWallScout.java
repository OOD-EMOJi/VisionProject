import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class LeftWallScout extends ScoutBehavior {
	
	//make constructor that requires location
	
    public Command getCommand() {
        List<DirType> directions = currentLocation.getDirections();
		//We think you need to edit this this does not seem to follow the wall?
        if (directions.contains(West))
            return new CommandMove(null, West);
        else if (directions.contains(South))
            return new CommandMove(null, South);
        else if (directions.contains(North))
            return new CommandMove(null, North);
        else
            return new CommandMove(null, East);
    }
}
