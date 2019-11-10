package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;

class StandardExecution implements CommandExecution{

    public void executeCommands(Maze the_maze, List<Command> team_cmds, GameState the_state){

	executeMoves(the_maze, team_cmds);

	updateRobotLocs(the_maze);

	executePickUp(the_maze, team_cmds, the_state);
    
    }

    //This function changes the location inside each Maze Robot
    void updateRobotLocs(Maze the_maze){
	int max_x = the_maze.getMaxX();
	int max_y = the_maze.getMaxY();
	
	for(int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		List<MazeRobot> bots = the_maze.getLocation(i,j).getRobots();
		if (bots != null){
		    for(MazeRobot r : bots){
			r.setCurrentLoc(the_maze.getLocation(i,j));
		    }
		}
	    }
	}
    }

    void executeMoves(Maze the_maze, List<Command> team_cmds){
	MazeRobot cur_bot;
	CommandMove cur_move;

	for (Command cmd : team_cmds){
	    if (cmd instanceof CommandMove){
		cur_move = (CommandMove) cmd;
	        cur_bot = the_maze.getRobot(cur_move.getRobot().getID());
		moveRobot(cur_bot, the_maze, cur_move);
	    }

	}
    }

    // Diagnostic Code
    // ***************

    void printDir(DirType d){
	if (d == DirType.North){
	    System.out.println("North");
	}
	if (d == DirType.West){
	    System.out.println("East");
	}	
	if (d == DirType.East){
	    System.out.println("West");
	}	
	if (d == DirType.South){
	    System.out.println("North");
	}
    }

    //This code changes the Maze but not the Location in the Robot
    void moveRobot(MazeRobot bot, Maze the_maze, CommandMove cmd){
	MazeLocation old_loc = bot.getCurrentLoc();

	if(bot.legalMove(cmd, the_maze)){
	    int x = old_loc.getX();
	    int y = old_loc.getY();
	    DirType dir = cmd.getDir();
	    MazeLocation new_loc = null;

	    switch(dir){
	    case North: new_loc = the_maze.getLocation(x, y-1);
		break;
	    case South: new_loc = the_maze.getLocation(x, y+1);
		break;
	    case East: new_loc = the_maze.getLocation(x+1, y);
		break;
	    case West: new_loc = the_maze.getLocation(x-1, y);
		break;
	    }

	    if (new_loc.getRobots() == null){
		new_loc.setTheRobots(new ArrayList<MazeRobot>());
	    }
	    new_loc.getRobots().add(bot);
	    old_loc.getRobots().remove(bot);
	}
    }
	    
    void executePickUp(Maze the_maze, List<Command> team_cmds, GameState state){
	
	MazeRobot cur_bot;

	for (Command cmd : team_cmds){
	    if (cmd instanceof CommandCoin){
	        cur_bot = the_maze.getRobot(cmd.getRobot().getID());
		pickUpRobot(cur_bot, the_maze, (CommandCoin) cmd, state);
	    }

	}
    }

    void pickUpRobot(MazeRobot bot, Maze the_maze, CommandCoin cmd, GameState state){
	List<CoinType> coins = bot.getCurrentLoc().getCoins();
	List<CoinType> pick = new ArrayList<CoinType>();

	if(coins != null){
	    for(CoinType c : coins){
		if(bot.legalCoin(c)){
		    pick.add(c);
		    state.team_coins = state.team_coins + 1;
		}
	    }

	    for(CoinType cn : pick){
		coins.remove(cn);
	    }
		    
	}
    }
}