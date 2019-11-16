import LepinskiEngine.*;

public interface RobotBehavior{
    public Command getCommand(Robot robot, Location location);
}
