import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.application.*;
import javafx.event.*;

public class MazeDrawer {
	public static void drawMaze(Maze maze, Canvas canvas) {
		int w = maze.tiles.length;
		int h = maze.tiles[0].length;
		
		GraphicsContext brush = canvas.getGraphicsContext2D();
		
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				brush.setFill( maze.tiles[x][y].isWall() ? Color.BLACK : Color.WHITE);
				brush.fillRect( x * 20 , y * 20 , 20 , 20);
				if(maze.tiles[x][y].getContents() != null) {
					brush.setFill(Color.BLACK);
					brush.fillText("" + maze.tiles[x][y].getContents().draw(), x * 20 + 5, y * 20 + 15, 20);
				}
			}
		}
	}
}