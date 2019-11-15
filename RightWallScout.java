import LepinskiEngine.*;
import static LepinskiEngine.DirType.*;
import java.util.*;

public class RightWallScout extends ScoutBehavior {
    public Command getCommand() {
        List<DirType> directions = currentLocation.getDirections();
		// changed this to null so that it compiles ,
		//		RightWallScout is not a robot but a behavior ( same with LeftWallScout )
        if (directions.contains(East))
            return new CommandMove(null, East);
        else if (directions.contains(South))
            return new CommandMove(null, South);
        else if (directions.contains(North))
            return new CommandMove(null, North);
        else
            return new CommandMove(null, West);
    }
}
