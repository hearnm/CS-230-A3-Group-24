import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * SystemGUI.java
 * @author Tom Durman
 * This class creates the System GUI.
 */
public class SystemGUI extends Application {
	
	
	private static final int MAIN_STAGE_WIDTH = 700;		// Width of the Main Scene
	private static final int MAIN_STAGE_HEIGHT= 500;		// Height of the Main Scene
	private static final int LOGIN_STAGE_WIDTH = 700;		// Width of the Login Scene
	private static final int LOGIN_STAGE_HEIGHT= 450;		// Height of the Login Scene
	private static final int SIGNUP_STAGE_WIDTH = 600;		// Width of the Main Scene
	private static final int SIGNUP_STAGE_HEIGHT= 600;		// Height of the Main Scene
	private static final int P_DRAW_IMG_STAGE_WIDTH = 600;	// Width of the Draw Image Scene
	private static final int P_DRAW_IMG_STAGE_HEIGHT= 580;	// Height of the Draw Image Scene
	private static final int CANVAS_WIDTH = 384;			// Width of the Canvas
	private static final int CANVAS_HEIGHT = 443;			// Height of the Canvas
	private static final int PREVIEW_CANVAS_WIDTH = 150;	// Width of the Preview Canvas
	private static final int PREVIEW_CANVAS_HEIGHT = 102; 	// Height of the Preview Canvas
	private static final int PREVIEW_CANVAS_DRAW_X = 25;	// Draw Preview Location X
	private static final int PREVIEW_CANVAS_DRAW_Y = 2;		// Draw Preview Location Y
	
	private static int fileNum = 1;			// File number setter
	private Canvas canvas;					// The canvas which the user can draw an image
	private Canvas previewCanvas;			// The canvas which shows the current pen style
	private double mouseX = 0.0;			// Mouse Coordinate X
	private double mouseY = 0.0;			// Mouse Coordinate Y
	private boolean drawParticle = true; 	// True if drawing a particle trace
	private boolean drawLine = false;		// True if drawing a Straight Line
	private boolean drawEraser = false;		// True if using an eraser
	private double sliderValue = 20;		// Value of the Draw image slider
	
	
	private Stage window;			// The main stage, displaying the current Scene
	private Scene login;			// The Scene to hold the login Page GUI
	private Scene signUp;
	private Scene home;
	private Scene profile;			// The Scene to hold the Profile Page GUI
	private Scene profileDrawImg;	// The Scene to hold the Profile Draw Image GUI
	private Scene profileAvatars;	// The Scene to hold the Profile Default Avatars GUI
	private Line  currentLine ;		// Line to be used to draw straight lines on a canvas
	private Image profImg;			// Currently selected Profile image for a profile.
	
	private static LoadData load = new LoadData();
	private UserProfile user;
	
	
	/**
	 * Main Method to start the GUI
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * Method to initialize the GUI.
	 */
	public void start(Stage primaryStage) {
		
		window = primaryStage;	
		
		Pane root = buildLoginGUI();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		login = new Scene(root, MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
		
		window.setTitle("Artatawe Application");
		window.setScene(login);
		window.show();
		LoadData.loadSystemData();
		
	}
	
	/**
	 * Method to build the Login GUI window
	 * @return root The Constructed Pane with all the Login GUI elements
	 */
	private Pane buildLoginGUI() {
		
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		BorderPane innerMid = new BorderPane();
		StackPane title = new StackPane();
		StackPane loginBox = new StackPane();
		
		root.setPadding(new Insets(10,10,10,10));
		innerMid.setPadding(new Insets(100,100,100,100));
		loginBox.setPadding(new Insets(10,10,10,10));
		
		loginBox.setMinSize(200, 150);
		loginBox.setMaxHeight(200);
		
		Button loginButton = new Button("Login");
		Button signupButton  = new Button("Sign up");
		
		loginButton.setMaxWidth(100);
		
        Text text = new Text("Artatawe\n");
        Text text2 = new Text("\n\nLogin Screen\n");
        Text usernameLogin = new Text("\nUsername");
        TextField usernameInput = new TextField();
        

        text.setScaleX(4);
        text.setScaleY(4);
        text2.setScaleX(2);
        text2.setScaleY(2);
        usernameLogin.setScaleX(1.5);
        usernameLogin.setScaleY(1.5);
        usernameInput.setMaxWidth(250);
        
        text2.setTextAlignment(TextAlignment.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        usernameLogin.setTextAlignment(TextAlignment.CENTER);
        
        StackPane.setAlignment(text, Pos.CENTER);
        StackPane.setAlignment(text2, Pos.CENTER);
        StackPane.setAlignment(usernameLogin, Pos.TOP_CENTER);
        StackPane.setAlignment(usernameInput, Pos.CENTER);
        StackPane.setAlignment(loginButton, Pos.BOTTOM_CENTER);
		
        loginButton.setOnAction(e -> {
        	
        	
        	if(validateLogin(usernameInput.getText()) == true) {
        	
        		UserProfile.setCurrentUserID(UserProfile.getCurrentUserId(usernameInput.getText()));
        		
        		Pane draw = buildHomePageGUI();
        		home = new Scene(draw, MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
        		window.setScene(home);
        		
        	} else {
        		usernameInput.setText("Login Unsuccessful");
        	}
        	
        });
        
        signupButton.setOnAction(e -> {
        	Pane draw = buildSignUpGUI();
			signUp = new Scene(draw, SIGNUP_STAGE_WIDTH, SIGNUP_STAGE_HEIGHT);
			window.setResizable(false);
			window.setScene(signUp);
        });
        
    
        
        
        
        loginBox.getChildren().addAll(usernameLogin, usernameInput, loginButton);
		title.getChildren().addAll(text, text2);
	
		
		innerMid.setCenter(loginBox);
		innerMid.setTop(title);
		innerMid.setBottom(signupButton);
		
		root.setCenter(innerMid);
		
		
		return root;
	}
	
	
	private boolean validateLogin(String username) {
		
		String x = UserProfile.searchForUser(username);
		
		if(username.equalsIgnoreCase(x)) {
			return true;
		} else {
			return false;
		}
		
	}


	private Pane buildSignUpGUI() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
	
		BorderPane innerMid = new BorderPane();
		StackPane title = new StackPane();
		StackPane midSection = new StackPane();
		VBox details = new VBox();
		HBox innerDetails1 = new HBox();
		HBox innerDetails2 = new HBox();
		HBox innerDetails3 = new HBox();
		HBox innerDetails4 = new HBox();
		HBox innerDetails5 = new HBox();
		HBox innerDetails6 = new HBox();
		HBox innerDetails7 = new HBox();
		VBox bottomBar = new VBox();
	
		root.setPadding(new Insets(10,10,10,10));
		innerMid.setPadding(new Insets(50,100,1,100));
		midSection.setPadding(new Insets(10,10,10,10));
		bottomBar.setPadding(new Insets(20,100,1,100));
		details.setPadding(new Insets(1,1,1,1));
		details.setSpacing(20);
		innerDetails1.setSpacing(10);
		innerDetails2.setSpacing(10);
		innerDetails3.setSpacing(10);
		innerDetails4.setSpacing(10);
		innerDetails5.setSpacing(10);
		innerDetails6.setSpacing(10);
		innerDetails7.setSpacing(10);
		bottomBar.setSpacing(20);
	
		midSection.setBorder(new Border(new BorderStroke(Color.BLACK, 
				BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
		midSection.setMinSize(200, 150);
		midSection.setMaxHeight(320);
		midSection.setMaxWidth(250);
	

		Text text = new Text("Artatawe\n");
		Text text2 = new Text("\n\nSign-Up Screen\n\n");
		Text username = new Text("Username\t");
		Text firstname = new Text("First name\t");
		Text lastname = new Text("Last name\t");
		Text street = new Text("Street\t\t");
		Text postcode = new Text("Postcode\t\t");
		Text cityTown = new Text("City/Town\t");
		Text phoneNo = new Text("Phone Number");
    
		TextField usernameBox = new TextField();
		TextField firstnameBox = new TextField();
		TextField lastnameBox = new TextField();
		TextField streetBox = new TextField();
		TextField postcodeBox = new TextField();
		TextField cityTownBox = new TextField();
		TextField phoneNoBox = new TextField();
   
		Button createProfile = new Button("Create Account");
		Button back = new Button("Back");
    
		createProfile.setMaxWidth(Double.MAX_VALUE);
		back.setMaxWidth(Double.MAX_VALUE);
    
		text.setScaleX(4);
		text.setScaleY(4);
		text2.setScaleX(2);
    	text2.setScaleY(2);

    	text2.setTextAlignment(TextAlignment.CENTER);
    	text.setTextAlignment(TextAlignment.CENTER);
   
    
    	StackPane.setAlignment(text, Pos.CENTER);
    	StackPane.setAlignment(text2, Pos.CENTER);


    	back.setOnAction(e -> {
    		Pane draw = buildLoginGUI();
    		login = new Scene(draw, LOGIN_STAGE_WIDTH, LOGIN_STAGE_HEIGHT);
    		window.setResizable(true);
    		window.setScene(login);
    	});
    	
    	createProfile.setOnAction(e -> {
    		
    		if(validateSignUpDetails(usernameBox.getText(), phoneNoBox.getText()) == true) {
    			String stringPhoneNo = phoneNoBox.getText();
        		int intPhoneNo = Integer.parseInt(stringPhoneNo);
    			UserProfile newUser = new UserProfile(usernameBox.getText(), firstnameBox.getText(), lastnameBox.getText(), streetBox.getText(), 
    									postcodeBox.getText(), cityTownBox.getText(), intPhoneNo, true);

    		} else {
    			System.out.println("Username taken, choose another!");
    		}
    		
    	});
    
    
    
    
    	innerDetails1.getChildren().addAll(username, usernameBox);
    	innerDetails2.getChildren().addAll(firstname, firstnameBox);
    	innerDetails3.getChildren().addAll(lastname, lastnameBox);
    	innerDetails4.getChildren().addAll(street, streetBox);
    	innerDetails5.getChildren().addAll(postcode, postcodeBox);
    	innerDetails6.getChildren().addAll(cityTown, cityTownBox);
    	innerDetails7.getChildren().addAll(phoneNo, phoneNoBox);
    
    	details.getChildren().addAll(innerDetails1, innerDetails2, innerDetails3, innerDetails4, innerDetails5, innerDetails6, innerDetails7);
    	midSection.getChildren().addAll(details);
    	title.getChildren().addAll(text, text2);
    	bottomBar.getChildren().addAll(createProfile, back);
	
    	innerMid.setCenter(midSection);
    	innerMid.setTop(title);
    	innerMid.setBottom(bottomBar);
	
    	root.setCenter(innerMid);
	
    	return root;
	}
	
	private boolean validateSignUpDetails(String username, String phoneNo) {
		
		String x = UserProfile.searchForUser(username);
		
		if(username.equalsIgnoreCase(x)) {
			return false;
		} else {
			return true;
		}
	}

	
	private Pane buildHomePageGUI(){
		
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		VBox top = new VBox();
		VBox titleBar = new VBox();
		HBox buttonBar = new HBox();
		VBox bottom = new VBox();
		HBox homepage = new HBox();
		HBox bottomBar = new HBox();
		
		top.setSpacing(15);
		top.setPadding(new Insets(50,20,20,0));
		
		buttonBar.setSpacing(170);
		buttonBar.setPadding(new Insets(40,20,0,40));
		
		//Create elements that are needed for top VBox
		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("Home Page");
		Button auctionsButton = new Button("Auctions");
		Button paintingsButton = new Button("Paintings");
		Button sculpturesButton = new Button("Sculptures");
		
		auctionsButton.setMinWidth(70);
		paintingsButton.setMinWidth(70);
		sculpturesButton.setMinWidth(80);
		
		auctionsButton.setMaxWidth(Double.MAX_VALUE);
		paintingsButton.setMaxWidth(Double.MAX_VALUE);
		sculpturesButton.setMaxWidth(Double.MAX_VALUE);
		
		auctionsButton.setPrefWidth(1500);
		paintingsButton.setPrefWidth(1500);
		sculpturesButton.setPrefWidth(1500);
		
		
		//Postion title text
		title.setScaleX(4);
		title.setScaleY(4);
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);
		title.setTextAlignment(TextAlignment.LEFT);
		
		//Position buttons
		auctionsButton.resize(87,80);
		paintingsButton.resize(87, 20);
		sculpturesButton.resize(87, 20);
		
		//Create elements that are needed for bottom VBox
		ScrollPane scroll = new ScrollPane();
		Button logOut = new Button("Log out");
		
		/*
		for(Artwork a : Artwork){
			homepage.getChildren().add(a.getTitle(),a.getDescription());
		}
		*/
		
		
		//Position button
		logOut.resize(87,20);
		
		//Add elements to the top VBox
		buttonBar.getChildren().addAll(auctionsButton,paintingsButton,sculpturesButton);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		titleBar.getChildren().addAll(title, subTitle);
		top.getChildren().addAll(titleBar,buttonBar);
		
		//Add elements to the bottom VBox
		
		root.setTop(top);
		
		
		return root;
	}
	
	
	/**
	 * Method to build the Profile GUI window
	 * @return root The Constructed Pane with all the Profile GUI elements
	 */
	private Pane buildProfileGUI() {
		
		BorderPane root = new BorderPane();
		
		root.setPadding(new Insets(10,10,10,10));
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		HBox topBar = new HBox();
		VBox lSideBar = new VBox();
		VBox rSideBar = new VBox();
		Pane profPicBox = new Pane();
		
		topBar.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		
		lSideBar.setSpacing(20);
		lSideBar.setPadding(new Insets(10,10,10,10));
		
		rSideBar.setSpacing(15);
		rSideBar.setPadding(new Insets(30,10,10,50));
		
		topBar.setSpacing(20);
		topBar.setPadding(new Insets(10,10,50,10));
		
		Label title = new Label("Artetawe");
		Label firstName = new Label("John Doe");
		Label details = new Label("Details");
		Label street = new Label("Street: ");
		Label postcode = new Label("Postcode: ");
		Label cityTown = new Label("City/Town: ");
		Label phoneNo = new Label("Phone Number: ");
		
		 title.setScaleX(3);
		 title.setScaleY(3);
		 title.setPadding(new Insets(10,10,10,30));
		
		 firstName.setScaleX(1.9);
		 firstName.setScaleY(1.9);
		
		
		Button changePicButton = new Button("Change Profile Picture");
		Button updateProfileButton = new Button("Update Personal Info");
		
		changePicButton.setOnAction(e -> {
			Pane draw = buildDrawImgGUI();
			profileDrawImg = new Scene(draw, P_DRAW_IMG_STAGE_WIDTH, P_DRAW_IMG_STAGE_HEIGHT);
			window.setScene(profileDrawImg);
		});
		
		
		changePicButton.setMaxWidth(Double.MAX_VALUE);
		updateProfileButton.setMaxWidth(Double.MAX_VALUE);
		
		
		
		ImageView imageView = new ImageView();
		imageView.setImage(profImg);
		imageView.setFitWidth(150);
		imageView.setFitHeight(150);
		
		

		topBar.getChildren().addAll(title);
		profPicBox.getChildren().addAll(imageView);
		rSideBar.getChildren().addAll(firstName,street,postcode,cityTown,phoneNo);
		lSideBar.getChildren().addAll(profPicBox, changePicButton, updateProfileButton);
		
		root.setTop(topBar);
		root.setLeft(lSideBar);
		root.setCenter(rSideBar);
		
		return root;
	}
	
	/**
	 * Method to set the Profile image of the current user
	 * @param imagePath The directory path to the image
	 */
	private void setProfileImage(String imagePath) {
		
		try {
			profImg = new Image(new FileInputStream(imagePath));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to build the Profile Draw Image GUI window
	 * @return root The Constructed Pane with all the Profile Draw Image GUI elements
	 */
	private Pane buildDrawImgGUI() {

		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
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
	    Pane whiteSpaceRight = new Pane();
	    
	    root.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	      
	    topBar.setSpacing(15);
	    topBar.setPadding(new Insets(20,20,20,20));
	    
	    sideBar.setSpacing(7);
	    sideBar.setPadding(new Insets(20,20,20,20));
	    
	    bottomBar.setSpacing(15);
	    bottomBar.setPadding(new Insets(20,20,20,20));
	    
	    topLeftBar.setSpacing(8);
	    topLeftBar.setPadding(new Insets(5,5,1,1));
	    
	    middleSection.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    
	    previewSection.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    previewSection.setPadding(new Insets(100,1,1,1));
	    
	    whiteSpaceRight.setPadding(new Insets(10,10,10,10));
	    
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
	    Button line = new Button("Line");
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
	    draw.setOnAction(e -> {drawParticle = true; drawLine = false; drawEraser = false;} );
	    line.setOnAction(e -> {drawLine = true; drawParticle = false; drawEraser = false;} );
	    erase.setOnAction(e -> {drawEraser = true; drawParticle = false; drawLine = false;} );
	    setImage.setOnAction(e -> {
	    	saveImage();
	    	});
	    back.setOnAction(e -> {
			Pane profilePane = buildProfileGUI();
			profile = new Scene(profilePane, MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
			window.setScene(profile);
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
	    
	    slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
            	sliderValue = (double) new_val;
            	drawPreview(colorOptions.getValue(), shapeOptions.getValue());
            	
            }
	    });
	    
	
	    previewSection.getChildren().add(previewCanvas);
	    topLeftBar.getChildren().addAll(draw, line, erase);
	    topBar.getChildren().add(title);
	    sideBar.getChildren().addAll(options, topLeftBar, color, colorOptions, shape, shapeOptions,sizeModifer, slider, reset, drawPreview, previewSection);
	    bottomBar.getChildren().addAll(back, setImage);
	    middleSection.getChildren().add(canvas);
	   
	    root.setRight(whiteSpaceRight);
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
	 * Method to clear the Draw Image Canvas of any shapes / elements
	 */
	private void resetCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0,0,CANVAS_WIDTH,CANVAS_HEIGHT);
		drawParticle = true;
	}
	
	/**
	 * Method to draw/erase at a tracked mouse X and Y coordinates
	 * @param shape The given shape to be drawn
	 * @param colorOption The Color for a shape to be drawn in.
	 */
	private void drawOnClick(String shape, ChoiceBox<String> colorOption) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getColorChoice(colorOption);
		
		if(drawEraser == true) {
			gc.clearRect(mouseX, mouseY,  sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Circle") {
			gc.fillOval(mouseX, mouseY,  sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Square") {
				gc.fillRect(mouseX, mouseY, sliderValue, sliderValue);
			} else if(drawLine == true) {
		}
	}

	/**
	 * Method to draw/erase at a tracked mouse X and Y coordinates
	 * @param shape The given shape to be drawn
	 * @param colorOption The Color for a shape to be drawn in.
	 */
	private void drawOnDrag(String shape, ChoiceBox<String> colorOption) {
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getColorChoice(colorOption);
		
		if(drawEraser == true) {
			gc.clearRect(mouseX, mouseY, sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Circle") {
				gc.fillOval(mouseX, mouseY, sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Square") {
				gc.fillRect(mouseX, mouseY, sliderValue, sliderValue);
		} 
	}
	
	/**
	 * Method to get the color choice and set the draw color
	 * @param colorOption The choice of color
	 */
	private void getColorChoice(ChoiceBox<String> colorOption) {
		String colorChoice = colorOption.getValue();
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		
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
	
	/**
	 * Method to get the color choice and set the preview draw color
	 * @param colorOption The choice of color
	 */
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
	
	/**
	 * Method to get the shape choice and convert it to a string
	 * @param shapeOption The choice box of shapes
	 * @return The String of the shape choice
	 */
	private String shapeChoice(ChoiceBox<String> shapeOption) {
		String shapeChoice = shapeOption.getValue();
		
		if(shapeChoice == "Circle") {
			return "Circle";
		} else if(shapeChoice == "Square") {
			return "Square";
		}
		return null;
	}
	
	/**
	 * Method to draw and update the preview pen style in the preview box.
	 * @param colorOption The color of the preview
	 * @param shapeOption The shape of the preview 
	 */
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
	
	/**
	 * Method to initialize the Preview of the draw style
	 */
	private void initialisePreview() {
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		gc2.setFill(Color.BLACK);
		gc2.fillOval(PREVIEW_CANVAS_DRAW_X, PREVIEW_CANVAS_DRAW_Y, sliderValue, sliderValue);
	}
	
	/**
	 * Method to save a drawn image
	 */
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
