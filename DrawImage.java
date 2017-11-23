import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DrawImage extends Application {
	
	private Canvas canvas;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		
		Pane root = buildGUI();
		
		Scene scene = new Scene(root, 600, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Pane buildGUI() {

		BorderPane root = new BorderPane();
				
		canvas = new Canvas(350, 350);
		root.setCenter(canvas);
		
	    VBox sideBar = new VBox(200);
	    
	    sideBar.setSpacing(15);
	    sideBar.setPadding(new Insets(20,20,20,20));
	    
	    Button reset = new Button("Reset Cavnas");
	    sideBar.getChildren().add(reset);
	    
	    reset.setOnAction(e -> {
	    	resetCanvas(); 	
	    });
	    
	    reset.setMaxWidth(Double.MAX_VALUE);
	    
	    root.setLeft(sideBar);
	    
		
		
		
		
		
		
		
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double mouseX = event.getX();
				double mouseY = event.getY();
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, 10, 10);
			}
		});	
		
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
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
	
	public void resetCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.clearRect(0,0,350,350);
	}
	
	
	
}
