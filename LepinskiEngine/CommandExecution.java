package LepinskiEngine;
import java.util.List;

public interface CommandExecution{
    public void executeCommands(Maze the_maze, List<Command> team_cmds, GameState state);
}