package EMOJI;

import LepinskiEngine.*;
import java.util.*;

/**
 * In this implementation we analyze the possible paths that the coin bot can
 * take, based on the remembered state of the maze. To do this we use a
 * MazeAdapter to adapt Lepinski's Maze to our Maze Model, so we can use our
 * current algorithms including pathfinders.  *
 * First we start with a empty ( wall filled ) maze, and build the maze based on
 * the vision ( list of Locations ). This is done by the MazeAdapter. We also
 * save data on the robots, the map of their behaviors. This first step is
 * encapsulated within the startGame method, as it is the first method that gets
 * called of this class.
 *
 * The next step is repeated and is maintained by the requestCommands method.
 * Every step we use the information given to us and update our maze model. Then
 * we get the associated behaviors for each robot to call getCommand(x,y) on
 * each of them so we can return a list of commands that the method requires.
 *
 **** WARNINGS ****
 *
 * 1. The amount of turns in lepinski's maze is not the same amount of turns in
 * ours ie. Our maze model requires double the amount of turns to get to the
 * same place as his.
 *
 * 2. updating the maze will also update the maze for all RobotBehaviors and
 * their member fields. (They hold a reference to the same object)  *
 * (add more if you know any)
*
 */
public class EMOJITeam implements PlayerTeam {

    MazeAdapter mazeA; // Lepinski's maze to our maze. (MazeAdapter extends our Maze class)
    Map<Integer, RobotBehavior> behaviors; // keep track of each robot and their behavior

    public void startGame(List<Robot> bots, GameState state) {
        //stubbed
        mazeA = new MazeAdapter(state);
        //1. Initialize maze
        mazeA.generateMaze();
        //2. Initialize Bot Map
        behaviors = new HashMap<Integer, RobotBehavior>();
        //3. Fill bot map with robot ID's and associated bot behaviors according to robot.getType()
        boolean hasLeft = false;
        for (Robot bot : bots) {
            Integer id = bot.getID();
            ModelType type = bot.getModel();
            RobotBehavior behavior = null;
            if (type == ModelType.CoinBot) {
                behavior = new CoinBotBehavior(state.turns_remaining, bot, new CoinBotPathOptionGenerator(mazeA));
            } else {
                behavior = hasLeft ? new LeftWallScout() : new RightWallScout();
                hasLeft = true;
            }
            behaviors.put(id, behavior);
        }
    }

    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state) {
        //stubbed
        //0. make new list of commands to return
        List<Command> commands = new ArrayList<Command>();
        //1. Update the Maze
        for (Location location : information) {
            System.out.println(location.getX() + " " + location.getY());
        }
        mazeA.updateMaze(information);
        MazePrinter p = new MazePrinter();
        p.printMaze(mazeA);
        //2. For every robot awaiting command:
        for (Robot bot : robotsAwaitingCommand) {
            RobotBehavior behavior = behaviors.get(bot.getID());
            Location location = null;
            for (Location loc : information) {
                if (loc.getRobots() != null) {
                    for (Robot robot : loc.getRobots()) {
                        if (robot.getID() == bot.getID()) {
                            location = loc;
                        }
                    }
                }
            }
            commands.add(behavior.getCommand(bot,location));
        }
        //a. retrieve behavior from map
        //b. call get command and add to list
        //3. return list
        return commands;
    }
}
