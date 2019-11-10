package LepinskiEngine;
//turns_remaining is the number of turns remaining until the end of the game

//team_coins is the number of coins collected so far (starts at zero)
//total_coins is the total number of coins available at the start of the game

//Locations are numbered from 0 to max_size_x -1
//... and 0 to max_size_y - 1
// That is, there are exactly max_size_x * max_size_y locations

//This should be cloned before being given to the player

public class GameState implements Cloneable{
    public int maze_size_x;
    public int maze_size_y;
    public int turns_remaining;
    public int team_coins;
    public int total_coins;

    public GameState(int x, int y, int turn, int team, int tot){
	maze_size_x = x;
	maze_size_y = y;
	turns_remaining = turn;
	team_coins = team;
	total_coins = tot;
    }

    public GameState clone(){
	return new GameState(maze_size_x, maze_size_y, turns_remaining, team_coins, total_coins);
    }
}