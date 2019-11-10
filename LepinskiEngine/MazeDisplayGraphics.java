package LepinskiEngine;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.shape.ArcType;

public class MazeDisplayGraphics{
    static double BOX_WIDE;
    static double BOX_TALL;
    static int FONT_SIZE;
    static GraphicsContext gc;

    static void showScore(String name1, GameState state, Canvas can){
	gc = can.getGraphicsContext2D();
	FONT_SIZE = 40;
	gc.setFill(Color.BLACK);

	gc.clearRect(810,0,1200,800);

	gc.fillText(name1.concat(":"), 850, 100);
	gc.fillText(String.valueOf(state.team_coins), 850, 150);

	gc.fillText("Turns Remaining:", 850, 400);
	gc.fillText(String.valueOf(state.turns_remaining), 850, 450);

    }

    static void display(Maze the_maze, Canvas can){
	int max_x = the_maze.getMaxX();
	int max_y = the_maze.getMaxY();
	gc = can.getGraphicsContext2D();
	BOX_WIDE = 60;
	BOX_TALL = 60;
	FONT_SIZE = 40;
	gc.setLineWidth(1.5);
	
	gc.clearRect(0,0,800,800);

	for(int i=0;i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		if(the_maze.getLocation(i,j) != null){
		    displayLoc(the_maze.getLocation(i,j), 20+BOX_WIDE*i, 20+BOX_TALL*j);
		}
	    }
	}
		
    }

    static void displayLoc(MazeLocation loc, double x, double y){
	List<DirType> dirs = loc.getDirections();

	gc.setFill(Color.WHITE);
	gc.setStroke(Color.BLACK);
	gc.setTextAlign(TextAlignment.LEFT);
	gc.setFont(Font.font("Helvetica", FONT_SIZE));

	if(dirs != null){

	    if(!dirs.contains(DirType.North)){
		gc.strokeLine(x,y,x+BOX_WIDE,y);
	    }

	    if(!dirs.contains(DirType.South)){
		gc.strokeLine(x,y+BOX_TALL,x+BOX_WIDE,y+BOX_TALL);
	    }

	    if(!dirs.contains(DirType.West)){
		gc.strokeLine(x,y,x,y+BOX_TALL);
	    }

	    if(!dirs.contains(DirType.East)){
		gc.strokeLine(x+BOX_WIDE,y,x+BOX_WIDE,y+BOX_TALL);
	    }
	}

	if((loc.getRobots() != null) && (loc.getRobots().size()>0)){
	    displayRobots(loc.getRobots(), x, y);
	}
	if((loc.getCoins() != null) && (loc.getCoins().size()>0)){
	    displayCoins(loc.getCoins(), x, y);
	}
    }

    static void displayRobots(List<MazeRobot> bots, double x, double y){
	gc.setFont(Font.font(FONT_SIZE));
        gc.setFill(Color.BLUE);
	gc.fillText(robotSymb(bots.get(0)),x+10, y+35);
	if(bots.size() > 1){
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3.0);
		gc.strokeText(robotSymb(bots.get(0)),x+10, y+35);
		gc.setLineWidth(1.5);
	    }
    }

    static void displayCoins(List<CoinType> coins, double x, double y){
	int dx = 5;
	int dy = 42;
	gc.setStroke(Color.BLACK);
	
	for(CoinType c : coins){
	    switch(c){
	    case Gold: gc.setFill(Color.YELLOW);
		break;
	    }
	    gc.fillOval(x+dx, y+dy, 12, 12);
	    gc.strokeArc(x+dx, y+dy, 12, 12, 0, 360, ArcType.OPEN);
	    dx = dx + 15;
	}
    
    }



    static String robotSymb(MazeRobot bot){
	ModelType model = bot.getModel();
	
	switch(model){
	case ScoutBot: return new String("S");
	case CoinBot:  return new String("C");
	}    

	return new String("?");
    }


}