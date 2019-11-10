package LepinskiEngine;
public interface Maze{
    MazeLocation getLocation(int x, int y);
    MazeLocation getTeamStart();
    int getMaxX();
    int getMaxY();
    int getNumCoin();
    MazeRobot getRobot(int id);
}