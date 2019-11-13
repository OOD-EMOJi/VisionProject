import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.application.*;
import javafx.event.*;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;


public class MazeApplication extends Application {
	Canvas canvas;
	
	public static void main(String[] args) {
		
		
		launch(args);
		
	}
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("OOD A4");
		
		Group root = new Group();
		this.canvas = new Canvas(800, 800);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, 800, 800);
		
		primaryStage.setScene(scene);
		
		
		MazePrinter mazePrinter = new MazePrinter();
		Maze maze = new Maze(new DepthFirstSearchMazeGenerator());
		maze.generateMaze(5,5);
		
		Mouse mouse = new Mouse(maze.getStart().getX(), maze.getStart().getY(), new WallFollowerPathfinder(maze));
		maze.getStart().setContents(mouse);
		
		Timeline loop = new Timeline();
		loop.setCycleCount(Timeline.INDEFINITE);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		loop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.25f), new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				
				gc.clearRect(0, 0, 800, 800);
				
				mouse.move();
				
				MazeDrawer.drawMaze(maze, canvas);
			
			}
		}));

		
		
		primaryStage.show();
	
		loop.play();
	}
}