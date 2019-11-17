package LepinskiEngine;

import EMOJI.*;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameEngine extends Application{
    final int NUM_TURNS = 10;
    final String team_name = "Test Team";
    final double DELAY_TIME = 2.0;
    final String maze_file = "example1.maze";

    PlayerTeam the_team;
    List<Robot> team_bots;
    Maze the_maze;
    GameState state;
    boolean is_done;
    CommandExecution execution;
    Canvas maze_canvas;

    public static void main(String[] args) {
        launch(args);
    }
    
    //You can Change TestTeam to be another class you create
    public GameEngine(){
		the_team = new EMOJITeam();
		execution = new StandardExecution();
    }
    
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Coin Maze");
        Group root = new Group();
        maze_canvas = new Canvas(1200, 800);
 
        root.getChildren().add(maze_canvas);
        primaryStage.setScene(new Scene(root, 1200, 800, Color.WHITE));
        primaryStage.show();

	startGame();

	Timeline twoSecondTimer = new Timeline(new KeyFrame(Duration.seconds(DELAY_TIME), new EventHandler<ActionEvent>() {

		    public void handle(ActionEvent event) {
			nextTurn();
		    }
}));
	twoSecondTimer.setCycleCount(Timeline.INDEFINITE);
	twoSecondTimer.play();
	
    }

    void startGame(){
	the_maze = new DiskMaze(maze_file);
	is_done = false;

	int x = the_maze.getMaxX();
	int y = the_maze.getMaxY();
	int num_coin = the_maze.getNumCoin();

	state = new GameState(x, y, NUM_TURNS, 0, num_coin);
	GameState temp_state = state.clone();
        team_bots = init_robots();

	the_team.startGame(init_robots(), temp_state);

	addRobots(team_bots);

	MazeDisplayGraphics.display(the_maze, maze_canvas);
	MazeDisplayGraphics.showScore(team_name, state, maze_canvas);

    }

    List<Robot> init_robots(){
	List<Robot> starting_bots = new ArrayList<Robot>();
	Robot bot1 = new Robot(ModelType.CoinBot, 1);
	Robot bot2 = new Robot(ModelType.ScoutBot, 2);
	Robot bot3 = new Robot(ModelType.ScoutBot, 3);
 
	starting_bots.add(bot1);
	starting_bots.add(bot2);
	starting_bots.add(bot3);

	return starting_bots;
    }
	
    //This transforms MazeLocations into Locations
    //This produces the list that is passed to the players
    List<Location> mazeToLocations(Maze scan_maze){
	List<Location> loc_list = new ArrayList<Location>();
	int max_x = scan_maze.getMaxX();
	int max_y = scan_maze.getMaxY();

	for (int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		MazeLocation cur_loc = scan_maze.getLocation(i,j);
		if (cur_loc != null){
		    loc_list.add(new Location(cur_loc));
		}
	    }
	}
	return loc_list;
    }

    void nextTurn(){
	List<Command> team_cmds;
	List<Location> scan_locations;

	if(!is_done){	  
	    scan_locations = mazeToLocations(doTeamScan());
	    MazeDisplayGraphics.display(doTeamScan(), maze_canvas);
	    MazeDisplayGraphics.showScore(team_name, state, maze_canvas);

	    team_bots = getReadyRobots();
		
	    team_cmds = the_team.requestCommands(scan_locations,team_bots, state.clone());

	    execution.executeCommands(the_maze, team_cmds, state);
	    
	    if (state.turns_remaining == 0){
		is_done = true;
	    }
	    state.turns_remaining -= 1;
	}
    }

    void addRobots(List<Robot> robots){
	MazeLocation startLoc = the_maze.getTeamStart();
	
	for (Robot bot : robots){
	    MazeRobot new_bot = MazeRobotFactory.makeMazeRobot(bot.getModel(), bot.getID(), startLoc);

	    if (startLoc.getRobots() == null){
		startLoc.setTheRobots(new ArrayList<MazeRobot>());
	    }
	    startLoc.getRobots().add(new_bot);
	}

    }

    Maze doTeamScan(){
	List<MazeRobot> bots = getMazeRobots();
	DarkMaze scan_info = new DarkMaze(the_maze);

	for(MazeRobot r : bots){
	    r.doScan(the_maze, scan_info);
	}
	return scan_info;
    }

    List<MazeRobot> getMazeRobots(){
	int max_x = the_maze.getMaxX();
	int max_y = the_maze.getMaxY();
	List<MazeRobot> team_bots = new ArrayList<MazeRobot>();

	for(int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		List<MazeRobot> bots = the_maze.getLocation(i,j).getRobots();
		if(bots != null){
		    for(MazeRobot the_bot: bots){
			team_bots.add(the_bot);
		    }
		}
	    }
	}
	return team_bots;
    }

    List<Robot> getReadyRobots(){
	List<Robot> ready_bots = new ArrayList<Robot>();
	ModelType the_model;
	int the_id;

	for(Robot bot : team_bots){
	    the_model = bot.getModel();
	    the_id = bot.getID();
	    ready_bots.add(new Robot(the_model, the_id));
	}
	    
	return ready_bots;
    }

}
	
