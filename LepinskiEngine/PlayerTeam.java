package LepinskiEngine;
import java.util.List;

//chooseTeam is called once at the very start of the game
//This function should return the robots the player wants to use during the game

//requestCommands is called each turn
//This function should return one Command for each robot awaiting command

public interface PlayerTeam{
    public void startGame(List<Robot> bots, GameState state); 
    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state);
}