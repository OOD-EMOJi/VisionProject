package EMOJI;

import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class CrazyScout implements RobotBehavior {

	// goes west until it hits a wall, then turns counterclockwise and goes until it hits a wall

    public Command getCommand(Robot robot, Location location) {
        List<DirType> directions = location.getDirections();
        DirType direction = directions.get(new Random().nextInt(directions.size()));
        return new CommandMove(robot, direction);
    }
}
