import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class LeftWallScout implements RobotBehavior {

	//make constructor that requires location

    public Command getCommand(Location location) {
        List<DirType> directions = location.getDirections();
        if (directions.contains(South))
            return new CommandMove(null, South);
        else if (directions.contains(West))
            return new CommandMove(null, West);
        else if (directions.contains(North))
            return new CommandMove(null, North);
        else
            return new CommandMove(null, East);
    }
}
