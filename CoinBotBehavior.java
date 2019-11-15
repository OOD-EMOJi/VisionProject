import java.util.*;
public class CoinBotBehavior implements RobotBehavior{
     int currentTurns;
     PathOptionGenerator pathOptionGenerator;
     Robot robot;
    
    public CoinBotBehavior(int currentTurns ,Robot robot , PathOptionGenerator pathOptionGenerator){
        this.currentTurns= currentTurns;
        this.robot = robot;
        this.pathOptionGenerator= pathOptionGenerator;
    }
    public Command getCommand(Location location){
        // Make paths
        List<PathOption> pathList = pathOptionGenerator.generatePathOptions(x,y,currentTurns);
        // Decide best path and get the next step
        Tile nextStep= pathList.get(pathList.size()-1).path.get(1);
        int x= location.getX();
        int y= location.getY();        
        int x2= nextStep.getX();
        int y2= nextStep.getY();
        Command command = new Command(robot,getDirection(x,y,x2,y2));
        return command;
    }
    //public enum DirType {North, South, East, West}
    public DirType getDirection(int x1, int y1, int x2, int y2){
            int[][] SHIFT = {
                {0 , 1}, // going East
                {1, 0}, // going South
                {0, -1}, // going West
                {-1, 0} // going North`
              };
              DirType dirType;
            if (x2-x1==0 && y2-y1==1)  return dirType= DirType.East;
            if (x2-x1==1 && y2-y1==0)  return dirType= DirType.South;
            if (x2-x1==0 && y2-y1==-1)  return dirType= DirType.West;
            if (x2-x1==-1 && y2-y1=0)  return dirType=  DirType.North;
    }
}
