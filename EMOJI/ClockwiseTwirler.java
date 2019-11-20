package EMOJI;

import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class ClockwiseTwirler implements RobotBehavior {

    DirType direction;
    public ClockwiseTwirler(DirType d) {
        direction = d;
    }
	// goes until it hits a wall, then turns clockwise and goes until it hits a wall

    public Command getCommand(Robot robot, Location location) {
        List<DirType> directions = location.getDirections();
        switch (direction) {
            case East:
                if (directions.contains(East)) break;
                else if (directions.contains(South))
                    direction = South;
                else if (directions.contains(West))
                    direction = West;
                else
                    direction = North;
                break;
            case South:
                if (directions.contains(South)) break;
                else if (directions.contains(West))
                    direction = West;
                else if (directions.contains(North))
                    direction = North;
                else
                    direction = East;
                break;
            case West:
                if (directions.contains(West)) break;
                else if (directions.contains(North))
                    direction = North;
                else if (directions.contains(East))
                    direction = East;
                else
                    direction = South;
                break;
            case North:
                if (directions.contains(North)) break;
                else if (directions.contains(East))
                    direction = East;
                else if (directions.contains(South))
                    direction = South;
                else
                    direction = West;
                break;
            }
        return new CommandMove(robot, direction);
    }
}
