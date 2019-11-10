package LepinskiEngine;
import java.util.List;
import java.util.ArrayList;

//startGame is called once at the very start of the game
//This function should return the robots the player wants to use during the game

//requestCommands is called each turn
//This function should return one Command for each robot awaiting command

public class TestTeam implements PlayerTeam{
    GameState cur_state;

    public void startGame(List<Robot> bots, GameState state){
	cur_state = state;
    }

    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state){

	List<Command> the_coms = new ArrayList<Command>();

	for(Robot bot : robotsAwaitingCommand){
	    if(bot.getModel() == ModelType.CoinBot){
		Command com = new CommandMove(bot, DirType.East);
		the_coms.add(com);
	    }
	    if(bot.getModel() == ModelType.ScoutBot){
		Command com = new CommandMove(bot, DirType.West);
		the_coms.add(com);
	    }
	}

	return the_coms;

    }
}