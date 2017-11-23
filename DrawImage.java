import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DrawImage extends Application {
	
	private Canvas canvas;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		
		Pane root = buildGUI();
		
		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Pane buildGUI() {

		BorderPane root = new BorderPane();
				
		canvas = new Canvas(250, 250);
		root.setCenter(canvas);
		
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double mouseX = event.getX();
				double mouseY = event.getY();
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, 10, 10);
				
			}
		});		
		
		return root;
	}
	
}
