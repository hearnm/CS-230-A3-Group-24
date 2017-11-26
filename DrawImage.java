import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
		
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #d4d4d4); ");
		
		// Create a new Canvas
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		
		// Create a new VBox's
	    VBox sideBar = new VBox();
	    VBox topBar = new VBox();
	    HBox bottomBar = new HBox();
	    
	   // sideBar.setStyle("-fx-background-color: Aqua");
	    sideBar.setSpacing(7);
	    sideBar.setPadding(new Insets(20,20,20,20));
	    topBar.setSpacing(15);
	    topBar.setPadding(new Insets(20,20,20,20));
	    
	    bottomBar.setSpacing(15);
	    bottomBar.setPadding(new Insets(20,20,20,20));
	    
	    Label title = new Label("Custom Avatar Drawing");
	    Label sizeModifer = new Label("Pen Size Modifer");
	    Label options = new Label("Pen Style Options:");
	    Label color = new Label("Color");
	    Label shape = new Label("Shape");
	    
	    title.setScaleX(3);
	    title.setScaleY(3);
	    title.setPadding(new Insets(10,10,10,120));
	    
	    options.setScaleX(1.5);
	    options.setScaleY(1.5);
	    options.setPadding(new Insets(1,1,1,15));



	    // Create reset button and add it into the side bar VBox
	    Button reset = new Button("Reset Canvas");
	    Button saveImage = new Button("Save Image");
	    Button back = new Button("Back");
	    
	 
	    reset.setMaxWidth(Double.MAX_VALUE);
	    back.setMaxWidth(Double.MAX_VALUE);
	    
	    ChoiceBox<String> colorOptions = new ChoiceBox<>();
	    ChoiceBox<String> shapeOptions = new ChoiceBox<>();
	    
	    colorOptions.getItems().add("Black");
	    colorOptions.getItems().add("Red");
	    colorOptions.getItems().add("Blue");
	    colorOptions.getItems().add("Green");
	    colorOptions.getItems().add("Yellow");
	    colorOptions.setValue("Black");
	    
	    shapeOptions.getItems().add("Circle");
	    shapeOptions.getItems().add("Square");
	    shapeOptions.setValue("Circle");
	    
	    // Resets the canvas on click event
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
	    
	    topBar.getChildren().add(title);
	    sideBar.getChildren().addAll(options, color, colorOptions, shape, shapeOptions,sizeModifer, slider, reset);
	    bottomBar.getChildren().addAll(back, saveImage);
	    root.setTop(topBar);
	    root.setLeft(sideBar);
	    root.setBottom(bottomBar);

	    
	    // Creates a small circle at cursor when clicked.
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String shape = shapeChoice(shapeOptions);
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				
				GraphicsContext gc = canvas.getGraphicsContext2D();
				getColorChoice(colorOptions);
				
				if(shape == "Circle") {
					gc.fillOval(mouseX, mouseY, slider.getValue(), slider.getValue());
					} else if(shape == "Square") {
						gc.fillRect(mouseX, mouseY, slider.getValue(), slider.getValue());
					}
			}
		});	
		
		// Creates a number of small circles at cursor when dragged.
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String shape = shapeChoice(shapeOptions);
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
			
				GraphicsContext gc = canvas.getGraphicsContext2D();
				getColorChoice(colorOptions);
				
				if(shape == "Circle") {
					gc.fillOval(mouseX, mouseY, slider.getValue(), slider.getValue());
					} else if(shape == "Square") {
						gc.fillRect(mouseX, mouseY, slider.getValue(), slider.getValue());
					}
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
	
	
	private void getColorChoice(ChoiceBox<String> colorOption) {
		String colorChoice = colorOption.getValue();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		if(colorChoice == "Black") {
			gc.setFill(Color.BLACK);
		} else if(colorChoice == "Red") {
			gc.setFill(Color.RED);		
		} if(colorChoice == "Blue") {
			gc.setFill(Color.BLUE);	
		} else if(colorChoice == "Green") {
			gc.setFill(Color.GREEN);	
		} if(colorChoice == "Yellow") {
			gc.setFill(Color.YELLOW);
		}
	}
	
	
	private String shapeChoice(ChoiceBox<String> shapeOption) {
		String shapeChoice = shapeOption.getValue();
		
		if(shapeChoice == "Circle") {
			return "Circle";
		} else if(shapeChoice == "Square") {
			return "Square";
		}
		return null;
	}
		
}
