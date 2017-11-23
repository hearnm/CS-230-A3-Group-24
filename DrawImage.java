import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DrawImage extends Application {
	
	private static final int STAGE_WIDTH = 600;
	private static final int STAGE_HEIGHT= 600;
	private static final int CANVAS_WIDTH = 350;
	private static final int CANVAS_HEIGHT = 350;
	private static final int CIRCLE_WIDTH = 10;
	private static final int CIRCLE_HEIGHT = 10;
	
	private Canvas canvas;
	private double mouseX = 0.0;
	private double mouseY = 0.0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		
		Pane root = buildGUI();
		
		Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Pane buildGUI() {

		BorderPane root = new BorderPane();
				
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
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
				mouseX = event.getX();
				mouseY = event.getY();
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, 10, 10);
			}
		});	
		
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getX();
				mouseY = event.getY();
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, CIRCLE_WIDTH, CIRCLE_HEIGHT);
			}
		});	
		
		return root;
	}
	
	public void resetCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
	}
	
	
	
}
