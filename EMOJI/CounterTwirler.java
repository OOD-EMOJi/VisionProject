package EMOJI;

import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class CounterTwirler implements RobotBehavior {

    DirType direction;
    public CounterTwirler(DirType d) {
        direction = d;
    }
	// goes ntil it hits a wall, then turns counterclockwise and goes until it hits a wall

    public Command getCommand(Robot robot, Location location) {
        List<DirType> directions = location.getDirections();
        switch (direction) {
            case West:
                if (directions.contains(West)) break;
                else if (directions.contains(South))
                    direction = South;
                else if (directions.contains(East))
                    direction = East;
                else
                    direction = North;
                break;
            case South:
                if (directions.contains(South)) break;
                else if (directions.contains(East))
                    direction = East;
                else if (directions.contains(North))
                    direction = North;
                else
                    direction = West;
                break;
            case East:
                if (directions.contains(East)) break;
                else if (directions.contains(North))
                    direction = North;
                else if (directions.contains(West))
                    direction = West;
                else
                    direction = South;
                break;
            case North:
                if (directions.contains(North)) break;
                else if (directions.contains(West))
                    direction = West;
                else if (directions.contains(South))
                    direction = South;
                else
                    direction = East;
                break;
            }
        return new CommandMove(robot, direction);
        }
    }
