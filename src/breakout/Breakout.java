package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("BREAKOUT GAME");
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld(500, 500);
		root.setCenter(world);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		world.start();
	}
	
}
