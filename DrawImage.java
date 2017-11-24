import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * DrawImage.java
 * @author Tom Durman
 * This class will construct a window and allow for drawing with the mouse.
 */
public class DrawImage extends Application {
	
	private static final int STAGE_WIDTH = 600;		// Width of the Stage
	private static final int STAGE_HEIGHT= 550;		// Height of the Stage
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
		
		BackgroundFill myBF = new BackgroundFill(Color.ALICEBLUE, new CornerRadii(1),
		         new Insets(0.0,0.0,0.0,0.0));// or null for the padding
		//then you set to your node or container or layout
		root.setBackground(new Background(myBF));
		
		
		// Create a new Canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		
		// Create a new VBox's
	    VBox sideBar = new VBox(200);
	    VBox topBar = new VBox(200);
	    HBox bottomBar = new HBox(200);
	    
	    sideBar.setSpacing(15);
	    sideBar.setPadding(new Insets(20,20,20,20));
	    
	    topBar.setSpacing(15);
	    topBar.setPadding(new Insets(20,20,20,20));
	    
	    bottomBar.setSpacing(15);
	    bottomBar.setPadding(new Insets(20,20,20,20));
	    
	    Label title = new Label("Custom Avatar Drawing");
	    Label sizeModifer = new Label("Pen Size Modifer");
	    Label colorSelection = new Label("Current Color: BLACK");
	    
	    title.setScaleX(3);
	    title.setScaleY(3);
	    title.setPadding(new Insets(10,10,10,100));
	    title.autosize();
	    
	    sizeModifer.setPadding(new Insets(30,5,5,5));
	    
	    
	    
	    // Create reset button and add it into the side bar VBox
	    Button reset = new Button("Reset Canvas");
	    Button colorBlack = new Button("Toggle Black");
	    Button colorRed = new Button("Toggle Red");
	    Button colorBlue = new Button("Toggle Blue");
	    Button colorGreen = new Button("Toggle Green");
	    Button colorYellow = new Button("Toggle Yellow");
	    Button saveImage = new Button("Save Image");
	    Button back = new Button("Back");
	    
	    
	    reset.setMaxWidth(Double.MAX_VALUE);
	    colorBlack.setMaxWidth(Double.MAX_VALUE);
	    colorRed.setMaxWidth(Double.MAX_VALUE);
	    colorBlue.setMaxWidth(Double.MAX_VALUE);
	    colorGreen.setMaxWidth(Double.MAX_VALUE);
	    colorYellow.setMaxWidth(Double.MAX_VALUE);
	    saveImage.setMaxWidth(Double.MAX_VALUE);
	    back.setMaxWidth(Double.MAX_VALUE);
	    
	    
	    // Set action when clicked for the reset button
	    reset.setOnAction(e -> {
	  
	    	resetCanvas(); 	
	    });
	    
	    colorRed.setOnAction(e -> {
	    	colorSelection.setText("Current Color: RED");
	    	setColorRed();
	    });
	    
	    colorBlue.setOnAction(e -> {
	    	colorSelection.setText("Current Color: BLUE");
	    	setColorBlue();
	    });
	    
	    colorGreen.setOnAction(e -> {
	    	colorSelection.setText("Current Color: GREEN");
	    	setColorGreen();
	    });
	    
	    colorYellow.setOnAction(e -> {
	    	colorSelection.setText("Current Color: BLACK");
	    	setColorYellow();
	    });
	    
	    colorBlack.setOnAction(e -> {
	    	colorSelection.setText("Current Color: BLACK");
	    	setColorBlack();
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
	    
	    topBar.getChildren().add(title);
	    sideBar.getChildren().addAll(colorSelection, colorBlack, colorRed, colorBlue, colorGreen,
	    							 colorYellow, reset, sizeModifer, slider);
	    bottomBar.getChildren().addAll(back, saveImage);
	    root.setTop(topBar);
	    root.setLeft(sideBar);
	    root.setBottom(bottomBar);

	    
	    // Creates a small circle at cursor when clicked.
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.fillOval(mouseX, mouseY, slider.getValue(), slider.getValue());
			}
		});	
		
		// Creates a number of small circles at cursor when dragged.
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				
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
	
	/**
	 * Method to set the pen color to red
	 */
	public void setColorRed() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
	}
	
	/**
	 * Method to set the pen color to red
	 */
	public void setColorBlue() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
	}
	
	/**
	 * Method to set the pen color to red
	 */
	public void setColorGreen() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
	}
	
	/**
	 * Method to set the pen color to red
	 */
	public void setColorYellow() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.YELLOW);
	}
	
	/**
	 * Method to set the pen color to red
	 */
	public void setColorBlack() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
	}
		
}
