// import command
public class LeftWallScout extends ScoutBehavior {
    public Command getCommand() {
        List<DirType> directions = currentLocation.getDirections();

        if (directions.contains(West))
            return new CommandMove(this, West);
        else if (directions.contains(South))
            return new CommandMove(this, South);
        else if (directions.contains(North))
            return new CommandMove(this, North);
        else
            return new CommandMove(this, East);
    }
}
