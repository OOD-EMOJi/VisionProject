import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class RightWallScout implements RobotBehavior {
    public Command getCommand(Location location) {
        List<DirType> directions = location.getDirections();

        if (directions.contains(South))
            return new CommandMove(null, South);
        else if (directions.contains(East))
            return new CommandMove(null, East);
        else if (directions.contains(North))
            return new CommandMove(null, North);
        else
            return new CommandMove(null, West);
    }
}
