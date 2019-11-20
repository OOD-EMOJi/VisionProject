package EMOJI;

import LepinskiEngine.*;
import java.util.*;

public class CoinBotBehavior implements RobotBehavior {

    int currentTurns;
    PathOptionGenerator pathOptionGenerator;
    Robot robot;

    public CoinBotBehavior(int currentTurns, Robot robot, PathOptionGenerator pathOptionGenerator) {
        this.currentTurns = currentTurns;
        this.robot = robot;
        this.pathOptionGenerator = pathOptionGenerator;
    }

    public Command getCommand(Robot robot, Location location) {
        if (location.getCoins() != null && location.getCoins().size() > 0) {
            //((CoinBotPathOptionGenerator)pathOptionGenerator).maze.tiles[location.getX() * 2 + 1][location.getY() * 2 + 1].clearContents();
            return new CommandCoin(robot);
        }
        // Make paths
        int x = location.getX();
        int y = location.getY();
        List<PathOption> pathList = pathOptionGenerator.generatePathOptions(2 * x + 1, 2 * y + 1, 2 * currentTurns);
        // Decide best path and get the next step
        PathOption pathOption = pathList.get(pathList.size() - 1);
		System.out.println("-- ( " + location.getX() + " , "  + location.getY() + " )");
		for(Tile t : pathOption.path) {
			System.out.println(t.getX() + " " + t.getY());
		}
        Tile currentTile = pathOption.path.get(0);
        Tile nextStep = pathOption.path.get(1);
        Command command = new CommandMove(robot, getDirection(currentTile.getX(), currentTile.getY(), nextStep.getX(), nextStep.getY()));
        System.out.println(((CommandMove)command).getDir());
        currentTurns -= 1;
        return command;
    }

    //public enum DirType {North, South, East, West}
    public DirType getDirection(int x1, int y1, int x2, int y2) {
        int[][] SHIFT = {
            {0, 1}, // going East
            {1, 0}, // going South
            {0, -1}, // going West
            {-1, 0} // going North`
        };
        if (x2 - x1 == 0 && y2 - y1 == 1) {
            return DirType.South;
        }
        if (x2 - x1 == 1 && y2 - y1 == 0) {
            return DirType.East;
        }
        if (x2 - x1 == 0 && y2 - y1 == -1) {
            return DirType.North;
        }
        if (x2 - x1 == -1 && y2 - y1 == 0) {
            return DirType.West;
        } else {
            return null;
        }
    }
}
