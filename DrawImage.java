import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * DrawImage.java
 * @author Tom Durman
 * This class will construct a window and allow for drawing with the mouse.
 */
public class DrawImage extends Application {
	
	private static final int STAGE_WIDTH = 600;		// Width of the Stage
	private static final int STAGE_HEIGHT= 400;		// Height of the Stage
	private static final int CANVAS_WIDTH = 350;	// Width of the Canvas
	private static final int CANVAS_HEIGHT = 350;	// Height of the Canvas
	
	private Canvas canvas;
	private double mouseX = 0.0;	// Mouse X Coordinate
	private double mouseY = 0.0;	// Mouse Y Coordinate
	
	/**
	 * Main Method to launch the GUI
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Method to get and show the GUI.
	 */
	public void start(Stage primaryStage) {
		
		Pane root = buildGUI();
		
		Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Method to build the GUI, containing all of the GUI elements
	 * @return The Pane to be displayed
	 */
	private Pane buildGUI() {

		BorderPane root = new BorderPane();
		
		// Create a new Canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		
		// Create a new VBox side bar
	    VBox sideBar = new VBox(200);
	    
	    // Adjust spacing and padding
	    sideBar.setSpacing(15);
	    sideBar.setPadding(new Insets(20,20,20,20));
	    
	    // Create reset button and add it into the side bar VBox
	    Button reset = new Button("Reset Cavnas");
	    Button changeColour = new Button("Change Colour");
	    reset.setMaxWidth(Double.MAX_VALUE);
	    changeColour.setMaxWidth(Double.MAX_VALUE);
	    sideBar.getChildren().addAll(reset, changeColour);
	    
	    // Set action when clicked for the reset button
	    reset.setOnAction(e -> {
	    	resetCanvas(); 	
	    });
	        
	    // Create a slider
	    Slider slider = new Slider();
	    slider.setMin(0);
	    slider.setMax(100);
	    slider.setValue(20);
	    slider.setShowTickLabels(true);
	    slider.setShowTickMarks(true);
	    slider.setMajorTickUnit(50);
	    slider.setMinorTickCount(5);
	    slider.setBlockIncrement(10);
	    
	    // Add the slider to the side \bar.
	    sideBar.getChildren().add(slider);
	  
	    
	    root.setLeft(sideBar);

	    // Creates a small circle at cursor when clicked.
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getX() - 10;
				mouseY = event.getY() - 10;
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, slider.getValue(), slider.getValue());
			}
		});	
		
		// Creates a number of small circles at cursor when dragged.
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getX() - 10;
				mouseY = event.getY() - 10;
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, slider.getValue(), slider.getValue());
			}
		});	
		
		return root;
	}
	
	/**
	 * Method to reset the canvas
	 */
	public void resetCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
	}
	
	
	
	
	
}
