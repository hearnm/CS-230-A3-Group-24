import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	private static final int STAGE_HEIGHT= 565;		// Height of the Stage
	private static final int CANVAS_WIDTH = 435;	// Width of the Canvas
	private static final int CANVAS_HEIGHT = 443;	// Height of the Canvas
	private static final int PREVIEW_CANVAS_WIDTH = 150;	// Width of the Preview Canvas
	private static final int PREVIEW_CANVAS_HEIGHT = 102; 	// Height of the Preview Canvas
	private static final int PREVIEW_CANVAS_DRAW_X = 25;	// Draw Preview Location X
	private static final int PREVIEW_CANVAS_DRAW_Y = 2;		// Draw Preview Location Y
	
	private static int fileNum = 1;
	private Canvas canvas;
	private Canvas previewCanvas;
	private double mouseX = 0.0;	// Mouse X Coordinate
	private double mouseY = 0.0;	// Mouse Y Coordinate
	private boolean drawErase = true; // True if drawing, false if eraser
	private double sliderValue = 20;
	
	//akfenjf
	
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
		
		primaryStage.setResizable(false);
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

		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899); ");
		
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		previewCanvas = new Canvas(PREVIEW_CANVAS_WIDTH, PREVIEW_CANVAS_HEIGHT);
		initialisePreview();
		
		// Create Pane Sections
	    VBox topBar = new VBox();
	    VBox sideBar = new VBox();
	    HBox bottomBar = new HBox();
	    HBox topLeftBar = new HBox();
	    Pane middleSection = new Pane();
	    Pane previewSection = new Pane();
	    
	    root.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	      
	    topBar.setSpacing(15);
	    topBar.setPadding(new Insets(20,20,20,20));
	    
	    sideBar.setSpacing(7);
	    sideBar.setPadding(new Insets(20,20,20,20));
	    
	    bottomBar.setSpacing(15);
	    bottomBar.setPadding(new Insets(20,20,20,20));
	    
	    topLeftBar.setSpacing(15);
	    topLeftBar.setPadding(new Insets(5,5,1,1));
	    
	    middleSection.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    
	    previewSection.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	   
	    previewSection.setPadding(new Insets(100,1,1,1));
	    
	    Label title = new Label("Custom Avatar Drawing");
	    Label sizeModifer = new Label("Pen Size Modifer");
	    Label options = new Label("Pen Style Options:");
	    Label color = new Label("Color");
	    Label shape = new Label("Shape");
	    Label drawPreview = new Label("Pen Preview");
	    
	    title.setScaleX(3);
	    title.setScaleY(3);
	    title.setPadding(new Insets(10,10,10,120));
	    
	    options.setScaleX(1.5);
	    options.setScaleY(1.5);
	    options.setPadding(new Insets(1,1,1,15));

	    // Create reset button and add it into the side bar VBox
	    Button reset = new Button("Reset Canvas");
	    Button setImage = new Button("Save and Use Image");
	    Button back = new Button("Return to Profile");
	    Button draw = new Button("Draw");
	    Button erase = new Button("Eraser");
	   
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
	    
	    
	    
	    shapeOptions.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> drawPreview(colorOptions.getValue(), newValue));
	    colorOptions.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> drawPreview(newValue, shapeOptions.getValue()));
	    reset.setOnAction(e -> {resetCanvas();} );
	    draw.setOnAction(e -> {drawErase = true;} );	    
	    erase.setOnAction(e -> {drawErase = false;} );
	    setImage.setOnAction(e -> {saveImage();});
	    
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
	    
	    slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	sliderValue = (double) new_val;
            	drawPreview(colorOptions.getValue(), shapeOptions.getValue());
            	
            }
	    });
	    
	
	    previewSection.getChildren().add(previewCanvas);
	    topLeftBar.getChildren().addAll(draw, erase);
	    topBar.getChildren().add(title);
	    sideBar.getChildren().addAll(options, topLeftBar, color, colorOptions, shape, shapeOptions,sizeModifer, slider, reset, drawPreview, previewSection);
	    bottomBar.getChildren().addAll(back, setImage);
	    middleSection.getChildren().add(canvas);
	   
	    root.setTop(topBar);
	    root.setLeft(sideBar);
	    root.setBottom(bottomBar);
	    root.setCenter(middleSection);

		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String shape = shapeChoice(shapeOptions);
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				sliderValue = slider.getValue();
				drawOnClick(shape, colorOptions);
				
			}
		});	
		
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String shape = shapeChoice(shapeOptions);
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				sliderValue = slider.getValue();
				drawOnDrag(shape, colorOptions);
			}
		});	
		
		return root;
	}
	
	/**
	 * Method to reset the canvas
	 */
	private void resetCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
		drawErase = true;
	}
	
	private void drawOnClick(String shape, ChoiceBox<String> colorOption) {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getColorChoice(colorOption);
		
		if(drawErase == false) {
			gc.clearRect(mouseX, mouseY,  sliderValue, sliderValue);
			} else if(shape == "Circle") {
			gc.fillOval(mouseX, mouseY,  sliderValue, sliderValue);
			} else if(shape == "Square") {
				gc.fillRect(mouseX, mouseY, sliderValue, sliderValue);
			}
	}
	
	
	private void drawOnDrag(String shape, ChoiceBox<String> colorOption) {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getColorChoice(colorOption);
		
		if(drawErase == false) {
			gc.clearRect(mouseX, mouseY, sliderValue, sliderValue);
			} else if(shape == "Circle") {
				gc.fillOval(mouseX, mouseY, sliderValue, sliderValue);
			} else if(shape == "Square") {
				gc.fillRect(mouseX, mouseY, sliderValue, sliderValue);
			}
	}
	
	
	private void getColorChoice(ChoiceBox<String> colorOption) {
		String colorChoice = colorOption.getValue();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		
		if(colorChoice == "Black") {
			gc.setFill(Color.BLACK);
			gc2.setFill(Color.BLACK);
		} else if(colorChoice == "Red") {
			gc.setFill(Color.RED);
			gc2.setFill(Color.RED);
		} if(colorChoice == "Blue") {
			gc.setFill(Color.BLUE);	
			gc2.setFill(Color.BLUE);
		} else if(colorChoice == "Green") {
			gc.setFill(Color.GREEN);
			gc2.setFill(Color.GREEN);
		} if(colorChoice == "Yellow") {
			gc.setFill(Color.YELLOW);
			gc2.setFill(Color.YELLOW);
		}
	}
	
	private void getPreviewColorChoice(String colorOption) {
		String colorChoice = colorOption;
		
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		
		if(colorChoice == "Black") {
			gc2.setFill(Color.BLACK);
		} else if(colorChoice == "Red") {			
			gc2.setFill(Color.RED);
		} if(colorChoice == "Blue") {			
			gc2.setFill(Color.BLUE);
		} else if(colorChoice == "Green") {			
			gc2.setFill(Color.GREEN);
		} if(colorChoice == "Yellow") {
			gc2.setFill(Color.YELLOW);
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
	
	private void drawPreview(String colorOption, String shapeOption) {
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		
		getPreviewColorChoice(colorOption);
		gc2.clearRect(0, 0, PREVIEW_CANVAS_WIDTH, PREVIEW_CANVAS_HEIGHT);
		
		if(shapeOption == "Circle") {
			gc2.fillOval(PREVIEW_CANVAS_DRAW_X, PREVIEW_CANVAS_DRAW_Y, sliderValue, sliderValue);
		} else if (shapeOption == "Square") {
			gc2.fillRect(PREVIEW_CANVAS_DRAW_X, PREVIEW_CANVAS_DRAW_Y, sliderValue, sliderValue);
		}
	}
	
	private void initialisePreview() {
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		gc2.setFill(Color.BLACK);
		gc2.fillOval(PREVIEW_CANVAS_DRAW_X, PREVIEW_CANVAS_DRAW_Y, sliderValue, sliderValue);
	}
	
	private void saveImage() {
		
		String saveName = "Test" + fileNum + ".png";
		fileNum++;
		
		File file = new File(saveName);
		WritableImage wim = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.snapshot(null, wim);
		try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception s) {
        }
		
	}
	
	
}