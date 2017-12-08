
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.regex.*;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

/**
 * SystemGUI.java
 * @author Tom Durman
 * This class creates the System GUI.
 */
public class SystemGUI extends Application {
	private static final int MAIN_STAGE_WIDTH = 700;		// Width of the Main Scene
	private static final int MAIN_STAGE_HEIGHT= 500;		// Height of the Main Scene
	private static final int SIGNUP_STAGE_WIDTH = 600;		// Width of the Main Scene
	private static final int SIGNUP_STAGE_HEIGHT= 600;		// Height of the Main Scene
	private static final int P_DRAW_IMG_STAGE_WIDTH = 600;	// Width of the Draw Image Scene
	private static final int P_DRAW_IMG_STAGE_HEIGHT= 580;	// Height of the Draw Image Scene
	private static final int AUCTION_DETAILS_STAGE_WIDTH = 850;	// Width of the Draw Image Scene
	private static final int AUCTION_DETAILS_STAGE_HEIGHT= 500;
	private static final int CREATE_AUCTION_STAGE_WIDTH = 400;
	private static final int CREATE_AUCTION_STAGE_HEIGHT = 550;
	private static final int CANVAS_WIDTH = 365;			// Width of the Canvas
	private static final int CANVAS_HEIGHT = 447;			// Height of the Canvas
	private static final int PREVIEW_CANVAS_WIDTH = 150;	// Width of the Preview Canvas
	private static final int PREVIEW_CANVAS_HEIGHT = 102; 	// Height of the Preview Canvas
	private static final int PREVIEW_CANVAS_DRAW_X = 25;	// Draw Preview Location X
	private static final int PREVIEW_CANVAS_DRAW_Y = 2;		// Draw Preview Location Y
	
	private Canvas canvas;					// The canvas which the user can draw an image
	private Canvas previewCanvas;			// The canvas which shows the current pen style
	private double mouseX = 0.0;			// Mouse Coordinate X
	private double mouseY = 0.0;			// Mouse Coordinate Y
	private double initialmouseX;			// Mouse Coordinate X
	private double initialmouseY;
	private boolean drawParticle = true; 	// True if drawing a particle trace
	private boolean drawLine = false;		// True if drawing a Straight Line
	private boolean drawEraser = false;		// True if using an eraser
	private double sliderValue = 20;		// Value of the Draw image slider
	private int artworkCounter = 0;
	
	private Stage window;			// The main stage, displaying the current Scene
	private Scene login;			// The Scene to hold the login Page GUI
	private Scene signUp;			// The Scene to hold the Sign Up GUI
	private Scene home;				// The Scene to hold the Home Page GUI
	private Scene profile;			// The Scene to hold the Profile Page GUI
	private Scene profileDrawImg;	// The Scene to hold the Profile Draw Image GUI
	private Scene profileAvatars;	// The Scene to hold the Profile Default Avatars GUI
	private Scene profileUpdateData;
	private Scene viewUsers;
	private Scene favoriteUsersList;
	private Scene newAuction;		// The Scene to hold the Create New Auction GUI
	private Scene selectedAuctionView;
	private Image profImg;			// Currently selected Profile image for a profile
	private UserProfile currentUserObject;
	private Auction selectedAuction;

	private ArrayList<UserProfile> allUsers = new ArrayList<>();	// an Array List of all users currently on the system
	private ArrayList<ImageView> avatars = new ArrayList<>();		// an Arraylist of paths to 6 pre-made user Avatars (stored locally)
	public static ArrayList<Auction> auctions = new ArrayList<Auction>();
	
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
		allUsers = UserProfile.getProfiles();
		auctions = Auction.getCurrentAuctions();
	}
	
	/**
	 * Method to set the current user of the system when called
	 * @param username The username of the current user
	 * @return True if the user exists (and set user), False if user does not exist
	 */
	private boolean setCurrentUser(String username) {

		for(int i = 0; i < allUsers.size(); i++) {
			if(username.equalsIgnoreCase(allUsers.get(i).getUsername())) {
				currentUserObject = allUsers.get(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to build the Login GUI window
	 * @return root The Constructed Pane with all the Login GUI elements
	 */
	private Pane buildLoginGUI() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		root.getStylesheets().add("artatawe.css");
		
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
       
        text.setScaleX(2);
        text.setScaleY(2);
        text.setId("ARTATAWE1");
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
        	if(usernameInput.getText().length() == 0) {
        		notificationBox("Login Notification", "Missing Information", "Login field cannot be left blank");
        	} else if(setCurrentUser(usernameInput.getText()) == true) {
        		home = new Scene(buildHomePageGUI(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
        		currentUserObject.addFavoriteMultipleUsers(LoadData.loadUserFavorites(currentUserObject));
        		window.setScene(home);
        		usernameInput.setText("");	

        		
        	} else {
        		notificationBox("Login Notification", "Login Error", "Username not found");
        		usernameInput.setText("");
        	}
        });
        signupButton.setOnAction(e -> {
			signUp = new Scene(buildSignUpGUI(), SIGNUP_STAGE_WIDTH, SIGNUP_STAGE_HEIGHT);
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
	
	/**
	 * Method to build the Sign Up GUI window
	 * @return root The Constructed Pane with all the Signup GUI elements
	 */
	private Pane buildSignUpGUI() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		root.getStylesheets().add("artatawe.css");
	
		BorderPane innerMid = new BorderPane();
		StackPane titleSection = new StackPane();
		StackPane midSection = new StackPane();
		VBox details = new VBox(20);
		HBox innerDetails1 = new HBox(10);
		HBox innerDetails2 = new HBox(10);
		HBox innerDetails3 = new HBox(10);
		HBox innerDetails4 = new HBox(10);
		HBox innerDetails5 = new HBox(10);
		HBox innerDetails6 = new HBox(10);
		HBox innerDetails7 = new HBox(10);
		VBox bottomBar = new VBox(20);
	
		root.setPadding(new Insets(10,10,10,10));
		innerMid.setPadding(new Insets(50,100,1,100));
		midSection.setPadding(new Insets(10,10,10,10));
		bottomBar.setPadding(new Insets(20,100,1,100));
		details.setPadding(new Insets(1,1,1,1));

		midSection.setBorder(new Border(new BorderStroke(Color.BLACK, 
				BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
		midSection.setMinSize(200, 150);
		midSection.setMaxHeight(320);
		midSection.setMaxWidth(250);

		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("\n\nSign-Up Screen\n\n");
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
    
		title.setScaleX(2);
		title.setScaleY(2);
		title.setId("ARTATAWE1");
		title.setTextAlignment(TextAlignment.CENTER);
		subTitle.setScaleX(2);
    	subTitle.setScaleY(2);
    	subTitle.setTextAlignment(TextAlignment.CENTER);
    	StackPane.setAlignment(title, Pos.CENTER);
    	StackPane.setAlignment(subTitle, Pos.CENTER);
    	
    	back.setOnAction(e -> {
    		window.setScene(login);
    		window.setResizable(true);
    	});
    	
    	createProfile.setOnAction(e -> {
    		if(signupInputExistenceCheck(usernameBox.getText(), firstnameBox.getText(), lastnameBox.getText(), streetBox.getText(), 
    							postcodeBox.getText(), cityTownBox.getText(), phoneNoBox.getText()) == true) {
    			
    			if(validateSignUpDetails(usernameBox.getText(), phoneNoBox.getText(), postcodeBox.getText()) == true) {
    							Integer intPhoneNo = Integer.parseInt(phoneNoBox.getText());
    							
    				UserProfile newUser = new UserProfile(usernameBox.getText(), firstnameBox.getText(), lastnameBox.getText(), streetBox.getText(), 
    								postcodeBox.getText(), cityTownBox.getText(), intPhoneNo, true);
    				
    		
    				notificationBox("Account Creation", "Account Creation Successful", "Congratulations you now have an Artatawe Account!\nYour username is: " + usernameBox.getText());
    				window.setScene(login);
    		    	window.setResizable(true);
    			}
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
    	titleSection.getChildren().addAll(title, subTitle);
    	bottomBar.getChildren().addAll(createProfile, back);
	
    	innerMid.setCenter(midSection);
    	innerMid.setTop(titleSection);
    	innerMid.setBottom(bottomBar);
	
    	root.setCenter(innerMid);
	
    	return root;
	}
	
	/**
	 * Method to validate the details entered on the sign up window
	 * @param username The username entered by the user
	 * @param phoneNo The phone number entered by the user
	 * @return True if details are valid, False if details are invalid
	 */
	private boolean validateSignUpDetails(String username, String phoneNo, String postcode) {
		//String regexUkPhoneNumber = "[0-9]{11}";	//Test
		//Pattern phoneNoChecker = Pattern.compile(regexUkPhoneNumber);
		//Matcher phoneNoMatcher = phoneNoChecker.matcher(phoneNo);
		if(usernameDuplicationCheck(username)) {
			notificationBox("Sign-Up Notification", "Input Error", "Username taken, please select another");
			return false;
		} else if (phoneNo.length() > 11) {
			notificationBox("Sign-Up Notification", "Input Error", "Phone Number cannot exceed 11 digits");
			return false;
		} else if (postcode.length() < 6 || postcode.length() > 7) {
			notificationBox("Sign-Up Notification", "Input Error", "Postcode can only be 6 or 7 characters long");
			return false;
		}
		try {
			Integer.parseInt(phoneNo);
		} catch (NumberFormatException e) {
			notificationBox("Sign-Up Notification", "Input Error", "System Error (We need to fix this to allow for >9 numbers");
			return false;
		} if(username.length() > 1) {
				return true;
			} else {
				return false;
			}
	}
	
	/**
	 * Method to check check the currently stored profiles too see if there is a duplicate
	 * @param username The username to be compared / checked
	 * @return True if there is a duplicate, False if it is unique
	 */
	private boolean usernameDuplicationCheck(String username) {
		for(int i = 0; i < allUsers.size(); i++) {
			if(username.equalsIgnoreCase(allUsers.get(i).getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to check the existence of input for the signup GUI
	 * @param username Entered username
	 * @param firstname Entered firstname
	 * @param lastname Entered lastname
	 * @param street Entered street
	 * @param postcode Entered postcode
	 * @param citytown Entered city / tow
	 * @param phoneNo Entered phone Number
	 * @return True if no fields are empty, False if any of the fields are blank;
	 */
	private boolean signupInputExistenceCheck(String username, String firstname, String lastname, String street, 
														String postcode, String citytown, String phoneNo) {
		if(username.length() == 0 || firstname.length() == 0  || lastname.length() == 0
				|| street.length() == 0  || postcode.length() == 0  
				|| citytown.length() == 0  || phoneNo.length() == 0) {
			notificationBox("Sign-Up Notification", "Input Error", "All fields must be filled out");
			return false;
		}
		return true;
	}
	

	/**
	 * Method to build the Home Page GUI window
	 * @return root The Constructed Pane with all the Home Page GUI elements
	 */
	private Pane buildHomePageGUI(){
		window.setResizable(true);
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		root.getStylesheets().add("artatawe.css");
		
		HBox mainTop = new HBox(15);
		VBox titleBlock = new VBox();
		VBox optionsBlock = new VBox(4);
		GridPane newAuctionBlock = new GridPane();
		ScrollPane scroll = new ScrollPane();
		HBox buttonBar = new HBox(10);
		HBox searchBlock = new HBox(5);
		StackPane mainCenter = new StackPane();

		
		mainCenter.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		

		root.setPadding(new Insets(25,10,10,10));
		mainTop.setPadding(new Insets(25,10,10,10));
		buttonBar.setPadding(new Insets(25,10,10,10));
		searchBlock.setPadding(new Insets(25,0,0,10));
		mainCenter.setPadding(new Insets(25,10,10,10));
		newAuctionBlock.setPadding(new Insets(20,10,10,10));
		
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		
		newAuctionBlock.setVgap(7);
		newAuctionBlock.setHgap(35);
		
		TextField search = new TextField();
		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("Home Page");
		Text options = new Text("Welcome "+ currentUserObject.getUsername());
		Text textAll = new Text("All: ");
		Text textPaintings = new Text("Paintings: ");
		Text textSculptures = new Text("Sculptures: ");
		Button searchBtn = new Button("Search");

		RadioButton filterAll = new RadioButton();
		RadioButton filterPaintings = new RadioButton();
		RadioButton filterSculptures = new RadioButton();
		filterAll.setSelected(true);
		
		filterAll.setOnAction(e -> {filterPaintings.setSelected(false); filterSculptures.setSelected(false);});
		filterPaintings.setOnAction(e -> filterAll.setSelected(false));
		filterSculptures.setOnAction(e -> filterAll.setSelected(false));
		

		
		ArrayList<ImageView> artworkPreview = new ArrayList<>();
		ArrayList<String> artworkDetails = new ArrayList<>();
		for(int i = 0; i < auctions.size(); i++) {

			final ImageView previewArt = new ImageView();
			previewArt.setImage(setArtImage(auctions.get(i).getCurrentArtTitle()));
			previewArt.setFitHeight(128);
			previewArt.setFitWidth(128);
			previewArt.setId("" + i);
			
			artworkDetails.add(auctions.get(i).getAuctionedArtwork().getTitle());
			artworkDetails.add(auctions.get(i).getAuctionedArtwork().getArtType());
			artworkDetails.add(auctions.get(i).getAuctionedArtwork().getAuctioneer());
			
			artworkPreview.add(previewArt);
		}
		
		
		for(int k = 0; k < artworkPreview.size(); k++) {
			
			artworkPreview.get(k).setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					
					int selection = Integer.parseInt(event.getPickResult().getIntersectedNode().getId());
					selectedAuction = auctions.get(selection);
					selectedAuctionView = new Scene(buildDetailedAuctionViewGUI(), AUCTION_DETAILS_STAGE_WIDTH, AUCTION_DETAILS_STAGE_HEIGHT);
					window.setScene(selectedAuctionView);
				};
			});
		}
	
		for(int n = 0; n < artworkPreview.size() - 1; n++) {
			final VBox previewDetails = new VBox(2);
			final Text previewTitle = new Text(auctions.get(n).getAuctionedArtwork().getTitle());
			final Text previewType = new Text(auctions.get(n).getAuctionedArtwork().getArtType());
			final Text previewAuctioneer = new Text(auctions.get(n).getAuctionedArtwork().getAuctioneer());
			previewDetails.setAlignment(Pos.BASELINE_CENTER);
			previewDetails.getChildren().addAll(previewTitle, previewType, previewAuctioneer);
			
			GridPane.setConstraints(artworkPreview.get(n), n, 0);
			GridPane.setConstraints(previewDetails, n, 1);
			
			newAuctionBlock.getChildren().addAll(artworkPreview.get(n), previewDetails);
			System.out.println("Artwork Loaded");
		}
		
		
		Button createNewAuctionButton = new Button("Create a new\nauction.");
		
		createNewAuctionButton.setOnAction(e -> {
			newAuction = new Scene(buildCreateNewAuctionGUI(), CREATE_AUCTION_STAGE_WIDTH , CREATE_AUCTION_STAGE_HEIGHT);
			window.setResizable(false);
			window.setScene(newAuction);
        });
		search.setMinWidth(150);
		searchBtn.setMinWidth(70);

		searchBtn.setMaxWidth(Double.MAX_VALUE);

		createNewAuctionButton.setMinWidth(70);
		searchBtn.setMaxWidth(Double.MAX_VALUE);
		createNewAuctionButton.setMaxWidth(Double.MAX_VALUE);

		searchBtn.setPrefWidth(55);
		createNewAuctionButton.setPrefWidth(1500);
		createNewAuctionButton.setMinHeight(70);

		title.setScaleX(4);
		title.setScaleY(4);
		title.setId("ARTATAWE2");
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);
		title.setTextAlignment(TextAlignment.LEFT);
		
		ChoiceBox<String> optionsMenu = new ChoiceBox<>();
		optionsMenu.getItems().add("Select an Option");
		optionsMenu.getItems().add("My Account");
		optionsMenu.getItems().add("View Users");
		optionsMenu.getItems().add("My Favorite Users");
		optionsMenu.getItems().add("Logout");
		optionsMenu.setValue("Select an Option");
		optionsMenu.setMinWidth(135);
		
		optionsMenu.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			optionsMenuSelection(newValue); 
			optionsMenu.setValue("Select an Option"); 
			
		});
		
		
		titleBlock.setAlignment(Pos.BASELINE_CENTER);
		
		
		searchBlock.getChildren().addAll(search,searchBtn, buttonBar);
		buttonBar.getChildren().addAll(textAll, filterAll, textPaintings, filterPaintings, textSculptures, filterSculptures);
		optionsBlock.getChildren().addAll(options, optionsMenu, createNewAuctionButton);
		titleBlock.getChildren().addAll(title, subTitle, searchBlock);
		mainTop.getChildren().addAll(titleBlock, optionsBlock);
		mainCenter.getChildren().addAll(newAuctionBlock);
		
		root.setTop(mainTop);
		root.setCenter(mainCenter);
		return root;
	}
	
	/**
	 * Method to carry out the functionality of what was selected in the options menu
	 * @param selection The selected option
	 */
	private void optionsMenuSelection(String selection) {
		if(selection == "My Account") {
			Pane draw = buildProfileGUI();
			profile = new Scene(draw, MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
			window.setScene(profile);
		} else if(selection == "Logout") {
			logoutConfirmation();
		} else if(selection == "View Users") {
			viewUsers = new Scene(buildUserListGUI(), MAIN_STAGE_WIDTH - 222, MAIN_STAGE_HEIGHT);
			window.setScene(viewUsers);
		} else if(selection == "My Favorite Users") {
			favoriteUsersList = new Scene(buildFavoriteUserListGUI(), MAIN_STAGE_WIDTH - 222, MAIN_STAGE_HEIGHT);
			window.setScene(favoriteUsersList);
		}
	}
	
	/**
	 * Method to construct a new stage to carry out functionality of signing out or canceling
	 */
	private void logoutConfirmation() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout Confirmation");
		alert.setHeaderText("You are about to logout of the system");
		alert.setContentText("Are you sure you would like to logout?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    window.setScene(login);
		}
	}
	
	/**
	 * Method to create a general purpose notification box
	 * @param title The title of the notification box
	 * @param message The message of the notification box
	 */
	private void notificationBox(String title, String header, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	

	
	/**
	 * Method to build the User List GUI window
	 * @return root The Constructed Scroll Pane with all the View User GUI elements
	 */
	private ScrollPane buildUserListGUI() {
		window.setResizable(false);
		
		// Local Variables (for dynamic construction of User List)
		ArrayList<Label> listname = new ArrayList<>();		// List of unique names (excluding current user)
		ArrayList<ImageView> listPic = new ArrayList<>();	// List of corresponding profile pictures (excluding current user)
		ArrayList<Button> buttons = new ArrayList<>();		// List of buttons
		
		ScrollPane root = new ScrollPane();
		BorderPane mainSection = new BorderPane();
		VBox topBar = new VBox(25);
		GridPane center = new GridPane();
		
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		
		mainSection.getStylesheets().add("artatawe.css");
		mainSection.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
	
		topBar.setPadding(new Insets(0,0,50,0));
		mainSection.setPadding(new Insets(25,10,10,10));
		center.setHgap(25);
		center.setVgap(10);

		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("User List");
		title.setScaleX(4);
		title.setScaleY(4);
		title.setId("ARTATAWE2");
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);
		title.setTextAlignment(TextAlignment.LEFT);
		
		Button back = new Button("Back");
		back.setPrefWidth(50);
		back.setOnAction(e -> {
			saveFavorites();
			window.setScene(home);
			
		});
		
		
		for(int i = 0; i < allUsers.size(); i++) {
			try {
				if(allUsers.get(i).getUsername() != currentUserObject.getUsername()) {
					
					final Label listUsername = new Label(allUsers.get(i).getUsername());
					listUsername.setScaleX(1.5);
					listUsername.setScaleY(1.5);
					listUsername.setId(allUsers.get(i).getUsername());
					
					final ImageView listUserImg = new ImageView(getUserImage(allUsers.get(i)));
					listUserImg.setFitHeight(100);
					listUserImg.setFitWidth(100);
					
					
					final Button mark = new Button();
					mark.setId(allUsers.get(i).getUsername());
					
					if(checkIfMarked(mark.getId())) {
						mark.setText("Unmark as Favorite");
					
					} else {
						mark.setText("Mark as Favorite");
					}
					
					mark.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {

							if(checkIfMarked(mark.getId())) {
								removeUserFavorite(mark.getId());
								System.out.println("User Removed");
								mark.setText("Mark as Favorite");
							} else {
								addUserToFavorites(mark.getId());
								System.out.println("User Added");
								mark.setText("Unmark as Favorite");
							}
						};
					});
					
					
					listname.add(listUsername);
					listPic.add(listUserImg);
					buttons.add(mark);
				} 
			} catch (Exception e) {
				System.out.println("user does not exist");
				}
		}
	
		
		for(int j = 0; j < allUsers.size() - 1; j++) {
			GridPane.setConstraints(listPic.get(j), 0, j);
			GridPane.setConstraints(listname.get(j), 1, j);
			GridPane.setConstraints(buttons.get(j), 6, j);
			center.getChildren().addAll(listname.get(j), listPic.get(j), buttons.get(j));
		}
		
		topBar.setAlignment(Pos.BASELINE_CENTER);
		topBar.getChildren().addAll(title, subTitle, back);
		mainSection.setTop(topBar);
		mainSection.setCenter(center);
		
		root.setContent(mainSection);
		return root;
	}

	private boolean checkIfMarked(String username) {
		
		for(int i = 0; i < currentUserObject.getFavoriteUsers().size(); i++) {
			if(username.equalsIgnoreCase(currentUserObject.getFavoriteUsers().get(i).getUsername())) {
				return true;
			}
		}
		
		return false;
	}
	
	private void saveFavorites() {
		if(currentUserObject.getFavoriteUsers() != null) {
		SaveData.saveProfileFavorites(currentUserObject);
		} else {
			
		}
	}
	
	
	/**
	 * Method to add a given user to the current users favorites
	 * @param user The user to be added to the Current users Favorite List.
	 */
	private void addUserToFavorites(String username) {
		
		for(int i = 0; i < allUsers.size(); i++) {
			if(username.equalsIgnoreCase(allUsers.get(i).getUsername())) {
				currentUserObject.addFavoriteUser(allUsers.get(i));
				
				for(int k = 0; k < currentUserObject.getFavoriteUsers().size(); k++ ) {
				System.out.println(currentUserObject.getFavoriteUsers().get(k).getUsername());
				}
			}
		}
	}
	
	/**
	 * Method to remove a given user from the current users favorites
	 * @param user The user to be removed from the Current users Favorite List.
	 */
	private void removeUserFavorite(String username) {
		
		for(int i = 0; i < allUsers.size(); i++) {
			if(username.equalsIgnoreCase(allUsers.get(i).getUsername())) {
				currentUserObject.removeFavoriteUser(allUsers.get(i));
				for(int k = 0; k < currentUserObject.getFavoriteUsers().size(); k++ ) {
				System.out.println(currentUserObject.getFavoriteUsers().get(k).getUsername());
				}
			}
		}
	}
	
	
	
	/**
	 * Method to be used to get the defaultImage (for users without an image)
	 * @return The default Image to be used
	 */
	private Image setDefaultImage() {
		try {
			Image img = new Image(new FileInputStream("DefaultPicture.png"));
			return img;
		} catch (FileNotFoundException e) {
			System.out.println("Default Image Not found");
		}
		return null;
	}
	
	/**
	 * Method to get the given user profile image
	 * @param user The UserObject whose image is wanted
	 * @return The profile image if found, Default image otherwise
	 */
	private Image getUserImage(UserProfile  user) {
		try {
			Image img = new Image(new FileInputStream(user.getUsername() + ".png"));
			return img;
		} catch (FileNotFoundException e) {
			System.out.println("Image Not found");
		}
		return setDefaultImage();
	}
	
	/**
	 * Method to build the User List GUI window
	 * @return root The Constructed Scroll Pane with all the View User GUI elements
	 */
	private ScrollPane buildFavoriteUserListGUI() {
		window.setResizable(false);
		
		// Local Variables (for dynamic construction of User List)
		ArrayList<Label> listname = new ArrayList<>();		// List of unique names (excluding current user)
		ArrayList<ImageView> listPic = new ArrayList<>();	// List of corresponding profile pictures (excluding current user)
		ArrayList<Button> buttons = new ArrayList<>();		// List of buttons
		
		ScrollPane root = new ScrollPane();
		BorderPane mainSection = new BorderPane();
		VBox topBar = new VBox(25);
		GridPane center = new GridPane();
		
		root.setFitToHeight(true);
		root.setFitToWidth(true);
		
		mainSection.getStylesheets().add("artatawe.css");
		mainSection.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
	
		topBar.setPadding(new Insets(0,0,50,0));
		mainSection.setPadding(new Insets(25,10,10,10));
		center.setHgap(25);
		center.setVgap(10);

		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("Your Favorite Users");
		title.setScaleX(4);
		title.setScaleY(4);
		title.setId("ARTATAWE2");
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);
		title.setTextAlignment(TextAlignment.LEFT);
		
		Button back = new Button("Back");
		back.setPrefWidth(50);
		back.setOnAction(e -> {
			saveFavorites();
			window.setScene(home);
			
		});
		
		
		
		
		for(int i = 0; i < allUsers.size(); i++) {
			try {
				if(allUsers.get(i).getUsername() != currentUserObject.getUsername()) {
					
					final Label listUsername = new Label(allUsers.get(i).getUsername());
					listUsername.setScaleX(1.5);
					listUsername.setScaleY(1.5);
					listUsername.setId(allUsers.get(i).getUsername());
					
					final ImageView listUserImg = new ImageView(getUserImage(allUsers.get(i)));
					listUserImg.setFitHeight(100);
					listUserImg.setFitWidth(100);
					
					
					final Button mark = new Button();
					mark.setId(allUsers.get(i).getUsername());
					
					if(checkIfMarked(mark.getId())) {
						mark.setText("Unmark as Favorite");
					} else {
						mark.setText("Mark as Favorite");
					}
					
					mark.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {

							if(checkIfMarked(mark.getId())) {
								removeUserFavorite(mark.getId());
								System.out.println("User Removed");
								mark.setText("Mark as Favorite");
							} else {
								addUserToFavorites(mark.getId());
								System.out.println("User Added");
								mark.setText("Unmark as Favorite");
							}
						};
					});
					
					
					listname.add(listUsername);
					listPic.add(listUserImg);
					buttons.add(mark);
				} 
			} catch (Exception e) {
				System.out.println("user does not exist");
				}
		}
	
		
		for(int j = 0; j < allUsers.size() - 1; j++) {
			if(currentUserObject.searchFavorite(listname.get(j).getText())) {
				GridPane.setConstraints(listPic.get(j), 0, j);
				GridPane.setConstraints(listname.get(j), 1, j);
				GridPane.setConstraints(buttons.get(j), 6, j);
				center.getChildren().addAll(listname.get(j), listPic.get(j), buttons.get(j));
			}
		}
		
		topBar.setAlignment(Pos.BASELINE_CENTER);
		topBar.getChildren().addAll(title, subTitle, back);
		mainSection.setTop(topBar);
		mainSection.setCenter(center);
		
		root.setContent(mainSection);
		return root;
	}
	
	
	
	
	
	/**
	 * Method to build the Profile GUI window
	 * @return root The Constructed Pane with all the Profile GUI elements
	 */
	private Pane buildProfileGUI() {
		BorderPane root = new BorderPane();
		root.getStylesheets().add("artatawe.css");
		window.setResizable(true);
		root.setPadding(new Insets(10,10,10,10));
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		HBox mainTop = new HBox(20);
		VBox titleBlock = new VBox();
		VBox lSideBar = new VBox(15);
		VBox midSection = new VBox(15);
		VBox rSideBar = new VBox(20);
		Pane profPicBox = new Pane();
		
		root.setPadding(new Insets(50,20,20,20));
		lSideBar.setPadding(new Insets(10,10,10,0));
		rSideBar.setPadding(new Insets(30,0,0,10));
		midSection.setPadding(new Insets(50,10,10,50));

		Text title = new Text("Artatawe\n");
		title.setId("#ARTATAWE2");
		Text subTitle = new Text("Profile Page");
		Label firstName = new Label(currentUserObject.getUsername());
		Label street = new Label("Street: " + currentUserObject.getStreet());
		Label postcode = new Label("Postcode: " + currentUserObject.getPostcode());
		Label cityTown = new Label("City/Town: " + currentUserObject.getCityTown());
		Label phoneNo = new Label("Phone Number: " + currentUserObject.getPhoneNumber());
		
		title.setScaleX(4);
		title.setScaleY(4);
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);
		title.setTextAlignment(TextAlignment.LEFT);
		firstName.setScaleX(1.9);
		firstName.setScaleY(1.9);
		
		setProfileImage();

		Button changePicButton = new Button("Create an Avatar");
		Button avatarButton = new Button("Use an Avatar");
		Button back = new Button("Return to Home Page");
		
		changePicButton.setOnAction(e -> {
			profileDrawImg = new Scene(buildDrawImgGUI(), P_DRAW_IMG_STAGE_WIDTH, P_DRAW_IMG_STAGE_HEIGHT);
			window.setScene(profileDrawImg);
		});
		avatarButton.setOnAction(e -> {
			profileAvatars = new Scene(buildAvatarsGUI(), P_DRAW_IMG_STAGE_WIDTH, P_DRAW_IMG_STAGE_HEIGHT);
			window.setResizable(false);
			window.setScene(profileAvatars);
		});
		back.setOnAction(e -> window.setScene(home));

		changePicButton.setMaxWidth(Double.MAX_VALUE);
		avatarButton.setMaxWidth(Double.MAX_VALUE);
		
		ImageView imageView = new ImageView();
		imageView.setImage(profImg);
		imageView.setFitWidth(150);
		imageView.setFitHeight(150);
		
		// If you know how to fix this please do its giving me cancer
		TableView myAuctions = new TableView();
		TableColumn artworkName = new TableColumn("Artwork");
        TableColumn currentBid = new TableColumn("Bid");
        TableColumn remainingBids = new TableColumn("Remaining\nBids");
        myAuctions.getColumns().addAll(artworkName, currentBid, remainingBids);
        
        TableView myBids = new TableView();
		TableColumn artworkName2 = new TableColumn("Artwork");
        TableColumn currentBid2 = new TableColumn("Current Bid");
        TableColumn myBid = new TableColumn("My Bid");
        myBids.getColumns().addAll(artworkName2, currentBid2, myBid);
		
		mainTop.setAlignment(Pos.BASELINE_CENTER);
		
		titleBlock.getChildren().addAll(title, subTitle);
		mainTop.getChildren().addAll(titleBlock);
		profPicBox.getChildren().addAll(imageView);
		midSection.getChildren().addAll(firstName,street,postcode,cityTown,phoneNo);
		lSideBar.getChildren().addAll(profPicBox, changePicButton, avatarButton);
		rSideBar.getChildren().addAll(myAuctions, myBids);
		
		root.setTop(mainTop);
		root.setLeft(lSideBar);
		root.setRight(rSideBar);
		root.setCenter(midSection);
		root.setBottom(back);
		
		return root;
	}
	
	/**
	 * Method to set the Profile image of the current user
	 * @param imagePath The directory path to the image
	 */
	private void setProfileImage() {
		try {
			profImg = new Image(new FileInputStream(currentUserObject.getUsername() + ".png"));
		} catch (FileNotFoundException e) {
			System.out.println("User does not have profile Image yet, Assigning default");
			try {
				profImg = new Image(new FileInputStream("DefaultPicture.png"));
			} catch (FileNotFoundException e1) {
				System.out.println("Something bad just happened");
			}
		}
	}
	
	/**
	 * Method to build the Avatars GUI window
	 * @return root The Constructed Pane with all the Avatar GUI elements
	 */
	private Pane buildAvatarsGUI() {
		BorderPane root = new BorderPane();
		root.getStylesheets().add("artatawe.css");
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		HBox line1 = new HBox(20);
		HBox line2 = new HBox(20);
		VBox coll1 = new VBox(20);
		VBox topSection = new VBox(10);
		VBox bottomSection = new VBox();
		
		root.setPadding(new Insets(20,20,20,10));
		coll1.setPadding(new Insets(50,0,0,0));
		coll1.setAlignment(Pos.BASELINE_CENTER);
		
		Button back = new Button("Back");
		back.setPrefWidth(150);
		
		back.setOnAction(e -> window.setScene(profile));
		
		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("Premade Avatar Selection");
		title.setScaleX(4);
		title.setScaleY(4);
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);
		title.setTextAlignment(TextAlignment.LEFT);
		
		for(int i = 0; i < 6; i++) {
			try {
				Image avatar = new Image(new FileInputStream("Avatar" + (i+1) + ".png"));
				ImageView avatarimg = new ImageView();
				avatarimg.setImage(avatar);
				avatarimg.setFitHeight(180);
				avatarimg.setFitWidth(180);
				avatars.add(avatarimg);
			} catch (FileNotFoundException e) {
				notificationBox("Resource Error", "Avatar " + (i+1), "Default avatar not found");
			}
		}
		avatars.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateUserProfile("Avatar1.png");
				setProfileImage();
				notificationBox("Profile Update", "Profile Image Updated", "Your profile picture has been updated!");
				reloadProfile();
			}
		});
		avatars.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateUserProfile("Avatar2.png");
				setProfileImage();
				notificationBox("Profile Update", "Profile Image Updated", "Your profile picture has been updated!");
				reloadProfile();
			}
		});
		avatars.get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateUserProfile("Avatar3.png");
				setProfileImage();
				notificationBox("Profile Update", "Profile Image Updated", "Your profile picture has been updated!");
				reloadProfile();
			}
		});
		avatars.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateUserProfile("Avatar4.png");
				setProfileImage();
				notificationBox("Profile Update", "Profile Image Updated", "Your profile picture has been updated!");
				reloadProfile();
			}
		});
		avatars.get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateUserProfile("Avatar5.png");
				setProfileImage();
				notificationBox("Profile Update", "Profile Image Updated", "Your profile picture has been updated!");
				reloadProfile();
			}
		});
		avatars.get(5).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateUserProfile("Avatar6.png");
				setProfileImage();
				notificationBox("Profile Update", "Profile Image Updated", "Your profile picture has been updated!");
				reloadProfile();
			}
		});
		bottomSection.setAlignment(Pos.BASELINE_CENTER);
		bottomSection.getChildren().addAll(back);
		topSection.setAlignment(Pos.BASELINE_CENTER);
		topSection.getChildren().addAll(title, subTitle);
		line1.getChildren().addAll(avatars.get(0), avatars.get(1), avatars.get(2));
		line2.getChildren().addAll(avatars.get(3), avatars.get(4), avatars.get(5));
		coll1.getChildren().addAll(line1, line2);
		root.setTop(topSection);
		root.setCenter(coll1);
		root.setBottom(bottomSection);
		
		return root;
	}
	
	/**
	 * Method to reload the User Profile GUI (refresh the page for updated information)
	 */
	private void reloadProfile() {
		Pane profileR = buildProfileGUI();
		profile = new Scene(profileR, MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
		window.setScene(profile);
	}
	
	/**
	 * Method to assign an image to a user by creating a new version of that image with a unique identifyer (username)
	 * @param path The path to the image
	 */
	private void updateUserProfile(String path) {
		InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(path));
            os = new FileOutputStream(new File(currentUserObject.getUsername() + ".png"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            } 
        } catch (Exception e) {
            try {
        	is.close();
            os.close();
            } catch (Exception s) {
            	System.out.println("Error");
            }
        }
	}

	/**
	 * Method to set border as there was duplication in code
	 * @param p
	 */
	private void setThisBorder(Pane p) {
		p.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	
	/**
	 * Method to build the Profile Draw Image GUI window
	 * @return root The Constructed Pane with all the Profile Draw Image GUI elements
	 */
	private Pane buildDrawImgGUI() {
		window.setResizable(false);
		BorderPane root = new BorderPane();
		root.getStylesheets().add("artatawe.css");
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		previewCanvas = new Canvas(PREVIEW_CANVAS_WIDTH, PREVIEW_CANVAS_HEIGHT);
		initialisePreview();
		
		// Create Pane Sections
	    VBox topBar = new VBox(15);
	    VBox sideBar = new VBox(7);
	    HBox bottomBar = new HBox(15);
	    HBox topLeftBar = new HBox(8);
	    Pane middleSection = new Pane();
	    Pane previewSection = new Pane();
	    Pane whiteSpaceRight = new Pane();
	   
	    topBar.setPadding(new Insets(20,20,20,20));
	    sideBar.setPadding(new Insets(20,20,20,20));  
	    bottomBar.setPadding(new Insets(20,20,20,20));
	    topLeftBar.setPadding(new Insets(5,5,1,1));
	    previewSection.setPadding(new Insets(100,1,1,1));
	    whiteSpaceRight.setPadding(new Insets(10,10,10,10));
	   
	    root.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	    setThisBorder(middleSection);
	    setThisBorder(previewSection);
	    
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
	    
	    ColorPicker colorOption = new ColorPicker();
	    colorOption.setValue(Color.BLACK);
	    colorOption.setMinHeight(25);
	    
	    ChoiceBox<String> shapeOptions = new ChoiceBox<>();
	    shapeOptions.getItems().add("Circle");
	    shapeOptions.getItems().add("Square");
	    shapeOptions.setValue("Circle");
	    
	    shapeOptions.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> drawPreview(colorOption.getValue(), newValue));
	    reset.setOnAction(e -> {resetCanvas();} );
	    draw.setOnAction(e -> {drawParticle = true; drawLine = false; drawEraser = false;} );
	    line.setOnAction(e -> {drawLine = true; drawParticle = false; drawEraser = false;} );
	    erase.setOnAction(e -> {drawEraser = true; drawParticle = false; drawLine = false;} );
	    
	    setImage.setOnAction(e -> {
	    	saveImage();
	    	setProfileImage();
	    	profile = new Scene(buildProfileGUI(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
	    	window.setScene(profile);
	    	window.setResizable(true);
	    	});
	    
	    back.setOnAction(e -> {
			profile = new Scene(buildProfileGUI(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
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
            	drawPreview(colorOption.getValue(), shapeOptions.getValue());
            }
	    });
	    // If you can fix this yellow line please do
	    colorOption.setOnAction(new EventHandler() {
	    	public void handle(Event t) {
	    		drawPreview(colorOption.getValue(), shapeOptions.getValue());
	    	}
	    });
		canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String shape = shapeChoice(shapeOptions);
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				sliderValue = slider.getValue();
				drawOnClick(shape, colorOption);
			}
		});	
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String shape = shapeChoice(shapeOptions);
				mouseX = event.getX() - (slider.getValue() / 2);
				mouseY = event.getY() - (slider.getValue() / 2);
				sliderValue = slider.getValue();
				drawOnDrag(shape, colorOption);
				}
		});	
		previewSection.getChildren().add(previewCanvas);
	    topLeftBar.getChildren().addAll(draw, line, erase);
	    topBar.getChildren().add(title);
	    sideBar.getChildren().addAll(options, topLeftBar, color, colorOption, shape, shapeOptions,sizeModifer, slider, reset, drawPreview, previewSection);
	    bottomBar.getChildren().addAll(back, setImage);
	    middleSection.getChildren().add(canvas);
	   
	    root.setRight(whiteSpaceRight);
	    root.setTop(topBar);
	    root.setLeft(sideBar);
	    root.setBottom(bottomBar);
	    root.setCenter(middleSection);
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
	private void drawOnClick(String shape, ColorPicker colorOption) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getColorChoice(colorOption);
		
		if(drawEraser == true) {
			gc.clearRect(mouseX, mouseY,  sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Circle") {
			gc.fillOval(mouseX, mouseY,  sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Square") {
				gc.fillRect(mouseX, mouseY, sliderValue, sliderValue);
			} else if(drawLine == true) { // IMPLEMENT A LINE (why is this so hard)

				if(initialmouseX != mouseX && initialmouseY != mouseY) {
				initialmouseX = mouseX;
				initialmouseY = mouseY;
			
				}	
		}
	}

	
	/**
	 * Method to draw/erase at a tracked mouse X and Y coordinates
	 * @param shape The given shape to be drawn
	 * @param colorOption The Color for a shape to be drawn in.
	 */
	private void drawOnDrag(String shape, ColorPicker colorOption) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		getColorChoice(colorOption);
		
		if(drawEraser == true) {
			gc.clearRect(mouseX, mouseY, sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Circle") {
				gc.fillOval(mouseX, mouseY, sliderValue, sliderValue);
			} else if(drawParticle == true && shape == "Square") {
				gc.fillRect(mouseX, mouseY, sliderValue, sliderValue);
		} 
		if(drawLine == true) { // IMPLEMENT A LINE (why is this so hard)
			drawOnClick(shape, colorOption);
			gc.strokeLine(initialmouseX, initialmouseY, mouseX, mouseY);
			gc.setLineWidth(sliderValue);
		}
	}
	
	/**
	 * Method to get the color choice and set the draw color
	 * @param colorOption The choice of color
	 */
	private void getColorChoice(ColorPicker colorOption) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(colorOption.getValue());
	}
	
	/**
	 * Method to get the color choice and set the preview draw color
	 * @param color The choice of color
	 */
	private void getPreviewColorChoice(Color color) {
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		gc2.setFill(color);
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
	 * @param color The color of the preview
	 * @param shapeOption The shape of the preview 
	 */
	private void drawPreview(Color color, String shapeOption) {
		GraphicsContext gc2 = previewCanvas.getGraphicsContext2D();
		
		getPreviewColorChoice(color);
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
		String saveName = currentUserObject.getUsername() + ".png";
		
		File file = new File(saveName);
		WritableImage wim = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.snapshot(null, wim);
		try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
            notificationBox("Profile Updated", "Profile Image Update", "Your profile image has been updated, enjoy!");
        } catch (Exception s) {
        	 notificationBox("Error", "Profile Image Update Error", "An unknow error has occured, User profile Image not updated!");
        }
	}
	
	private ScrollPane buildCreateNewAuctionGUI() {
		
		BorderPane root = new BorderPane();
		VBox vert = new VBox(8);
		HBox toggles = new HBox(5);
		VBox imgContainer = new VBox();
		HBox dimensions1 = new HBox(5);
		HBox dimensions2 = new HBox(5);
		ScrollPane scroll = new ScrollPane();

		scroll.setFitToHeight(true);	
		scroll.setFitToWidth(true);
		
		root.getStylesheets().add("artatawe.css");
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
	
		vert.setPadding(new Insets(0,0,25,0));
		root.setPadding(new Insets(25,10,10,10));
		imgContainer.setPadding(new Insets(10,10,10,10));

		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("Create a new auction");
		title.setScaleX(3);
		title.setScaleY(3);
		title.setId("ARTATAWE2");
		subTitle.setScaleX(1.2);
		subTitle.setScaleY(1.2);
		title.setTextAlignment(TextAlignment.LEFT);
		subTitle.setTextAlignment(TextAlignment.LEFT);
		
		Text artNameTxt = new Text("Artwork Name:");
		Text artCreatorTxt = new Text("Artwork Creator:");
		Text artCreationYearTxt = new Text("Artwork Creation Year:");
		Text maxBiddersTxt = new Text("Max Bidders:");
		Text reservePriceTxt = new Text("Reserve Bid:");
		Text heightTxt = new Text("Height(cm):");
		Text widthTxt = new Text("Width(cm):");
		Text depthTxt = new Text("Depth(cm):");
		Text mainMaterialTxt = new Text("Main Material:");
		
		TextField artNameBox = new TextField();
		TextField artCreatorBox = new TextField();
		TextField artCreationYearBox = new TextField();
		TextField maxBiddersBox = new TextField();
		TextField reservePriceBox = new TextField();
		TextField artFilePathBox = new TextField();
		TextField heightBox = new TextField();
		TextField widthBox = new TextField();
		TextField depthBox = new TextField();
		TextField mainMaterialBox = new TextField();
		
		
		heightBox.setMaxWidth(50);
		widthBox.setMaxWidth(50);
		depthBox.setMaxWidth(50);
		artNameBox.setMaxWidth(200);
		maxBiddersBox.setMaxWidth(200);
		reservePriceBox.setMaxWidth(200);
		artCreationYearBox.setMaxWidth(200);
		artCreatorBox.setMaxWidth(200);
		artFilePathBox.setMaxWidth(300);
		mainMaterialBox.setMaxWidth(200);
		
		Button back = new Button("Back");
		back.setPrefWidth(50);
		back.setOnAction(e -> window.setScene(home));
		
		Button createAuctionButton = new Button("Create Auction");
		createAuctionButton.setPrefWidth(150);
		createAuctionButton.setPrefHeight(50);
		
		Button uploadImg = new Button("Choose Image");
		uploadImg.setPrefWidth(150);
		uploadImg.setPrefHeight(50);
		
		ImageView preview = new ImageView();
		
		preview.setLayoutY(10);
	
		uploadImg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
            	addArtworkImg(artNameBox.getText());
            	preview.setImage(setArtImage(artNameBox.getText()));
            	preview.setFitHeight(100);
            	preview.setFitWidth(100);
            }
		});

		ToggleGroup radioSelectionToggle = new ToggleGroup();
		
		RadioButton paintingRadio = new RadioButton("Painting");
		RadioButton sculptureRadio = new RadioButton("Sculpture");
		
		sculptureRadio.setOnAction(e -> {
			mainMaterialBox.setEditable(true);
			mainMaterialBox.setOpacity(1);
			depthBox.setEditable(true);
			depthBox.setOpacity(1);
		});
		
		paintingRadio.setOnAction(e -> {
			mainMaterialBox.setEditable(false);
			mainMaterialBox.setOpacity(0.5);
			mainMaterialBox.clear();
			depthBox.setEditable(false);
			depthBox.setOpacity(0.5);
			depthBox.clear();
		});
		
		paintingRadio.setToggleGroup(radioSelectionToggle);
		sculptureRadio.setToggleGroup(radioSelectionToggle);
		sculptureRadio.setSelected(true);
		

		createAuctionButton.setOnAction(e -> {
			if(newAuctionInputExistenceCheck(artNameBox.getText(), artCreatorBox.getText(), artCreationYearBox.getText(), maxBiddersBox.getText()) == true) {
				
				if(paintingRadio.isSelected() == true) {
					Artwork newPainting = new Painting(currentUserObject.getUsername(), artNameBox.getText(), artCreatorBox.getText(), Integer.parseInt(artCreationYearBox.getText()), 
														Double.parseDouble(reservePriceBox.getText()), Integer.parseInt(maxBiddersBox.getText()), Double.parseDouble(widthBox.getText()), 
														Double.parseDouble(heightBox.getText()), true, true);
					SaveData.saveNewArtwork(newPainting);
					auctions.add(Auction.getGivenAuction(artNameBox.getText()));
					home = new Scene(buildHomePageGUI(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
					window.setScene(home);
				} else if(sculptureRadio.isSelected() == true) {
					Artwork newSculpture = new Sculpture(currentUserObject.getUsername(), artNameBox.getText(), artCreatorBox.getText(), Integer.parseInt(artCreationYearBox.getText()), 
							Double.parseDouble(reservePriceBox.getText()), Integer.parseInt(maxBiddersBox.getText()), Double.parseDouble(widthBox.getText()), 
							Double.parseDouble(heightBox.getText()), Double.parseDouble(depthBox.getText()), mainMaterialBox.getText(), true, true);
					
					
					// Duplication here
					auctions.add(Auction.getGivenAuction(artNameBox.getText()));
					SaveData.saveNewArtwork(newSculpture);
					
					home = new Scene(buildHomePageGUI(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGHT);
					window.setScene(home);
				}
			}
		});


		toggles.setAlignment(Pos.BASELINE_CENTER);
		toggles.getChildren().addAll(sculptureRadio, paintingRadio);
		vert.setAlignment(Pos.BASELINE_CENTER);
		imgContainer.setAlignment(Pos.BASELINE_CENTER);
		dimensions2.setAlignment(Pos.BASELINE_CENTER);
		dimensions1.setAlignment(Pos.BASELINE_CENTER);
		dimensions1.getChildren().addAll(heightTxt, widthTxt, depthTxt);
		dimensions2.getChildren().addAll(heightBox, widthBox, depthBox);
		imgContainer.getChildren().addAll(preview);
		vert.getChildren().addAll(title, subTitle, toggles, artNameTxt, artNameBox, artCreatorTxt, artCreatorBox, artCreationYearTxt, 
									artCreationYearBox, maxBiddersTxt, maxBiddersBox, reservePriceTxt, reservePriceBox, 
									imgContainer, dimensions1, dimensions2, mainMaterialTxt, mainMaterialBox, uploadImg, createAuctionButton, back);
		

		root.setTop(vert);
		scroll.setContent(root);
		return scroll;
	}
	
	private void addArtworkImg(String artName) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
		InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(fileChooser.showOpenDialog(window).getPath()));
            os = new FileOutputStream(new File(artName + ".png"));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            } 
        } catch (Exception e) {
            try {
        	is.close();
            os.close();
            } catch (Exception s) {
            	System.out.println("Error");
            }
        }
	}

	private Image setArtImage(String artName) {
		try {
			Image img = new Image(new FileInputStream(artName + ".png"));
			return img;
		} catch (FileNotFoundException e) {
			System.out.println("Image Not found");
		}
		return null;
	}

	private Pane buildDetailedAuctionViewGUI() {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		root.getStylesheets().add("artatawe.css");
		root.setPadding(new Insets(20,20,20,20));
		
		HBox mainTop = new HBox(15);
		VBox titleBlock = new VBox();
		HBox bottomBar = new HBox();
		BorderPane innerCenter = new BorderPane();
		HBox leftHBoxMain = new HBox(35);
		VBox leftVBar = new VBox(10);
		VBox leftVBar2 = new VBox(10);
		VBox rightVBar = new VBox(10);
		
		leftVBar2.setPadding(new Insets(25,0,0,0));
		mainTop.setPadding(new Insets(20,50,10,10));
		titleBlock.setAlignment(Pos.BASELINE_CENTER);
		mainTop.setAlignment(Pos.BASELINE_CENTER);
		
		ImageView artworkImg = new ImageView();
		artworkImg.setImage(setArtImage(selectedAuction.getAuctionedArtwork().getTitle()));
		artworkImg.setFitWidth(180);
		artworkImg.setFitHeight(180);
		
		TextField bidInput = new TextField();
		Text title = new Text("Artatawe\n");
		Text subTitle = new Text("Auction Page");
		Text artName = new Text(selectedAuction.getAuctionedArtwork().getTitle());
		Text artType = new Text(selectedAuction.getAuctionedArtwork().getArtType());
		Text artAuctioneer = new Text("Auctioner: " + selectedAuction.getAuctionedArtwork().getAuctioneer());
		Text artCreator = new Text("Artwork Creator: " + selectedAuction.getAuctionedArtwork().getCreator());
		Text artYear = new Text("Creation Year: " + Integer.toString(selectedAuction.getAuctionedArtwork().getArtCreationYear()));
		Text artHeight = new Text("Height: " + Double.toString(selectedAuction.getAuctionedArtwork().getHeight()) + "cm");
		Text artWidth = new Text("Width: " + Double.toString(selectedAuction.getAuctionedArtwork().getWidth()) + "cm");
		Text artDepth = new Text("Depth: " + Double.toString(selectedAuction.getAuctionedArtwork().getDepth()) + "cm");
		Text artMaterial = new Text("Material: " + selectedAuction.getAuctionedArtwork().getMaterial());
		
		
		Button bid = new Button("Bid on Artwork");
		Button back = new Button("Return to home");
		
		bid.setMaxSize(Double.MAX_VALUE, 55);
		back.setPrefWidth(180);
	
		bid.setOnAction(e -> {
			double userBid = convertBidInput(bidInput.getText());
			attemptBid(selectedAuction, userBid);
	
			if(selectedAuction.getIsCompleted()) {
				bid.setVisible(false);
			}
			
		});
		
		
		back.setOnAction(e -> {
			selectedAuction = null;
			window.setScene(home);
		});
		
		
		TableView bidHistory = new TableView();
		bidHistory.setEditable(false);
		
		TableColumn bidderName = new TableColumn("Bidder");
        TableColumn bidAmount = new TableColumn("Bid");
        TableColumn bidDate = new TableColumn("Date of Bid");
        bidderName.setMinWidth(100);
        bidAmount.setMinWidth(100);
        bidDate.setMinWidth(100);
        
        bidHistory.getColumns().addAll(bidderName, bidAmount, bidDate);
		
        title.setScaleX(4);
		title.setScaleY(4);
		artName.setScaleX(2.5);
		artName.setScaleY(2.5);
		artType.setScaleX(1.5);
		artType.setScaleY(1.5);
		title.setId("ARTATAWE2");
		subTitle.setScaleX(2.5);
		subTitle.setScaleY(2.5);

		title.setTextAlignment(TextAlignment.LEFT);
		
		
		if(selectedAuction.getAuctionedArtwork().getArtType().equalsIgnoreCase("Painting")) {
			artDepth.setVisible(false);
			artMaterial.setVisible(false);
		}

		leftVBar.setAlignment(Pos.BASELINE_CENTER);

		bottomBar.getChildren().addAll(back);
		titleBlock.getChildren().addAll(title, subTitle);
		mainTop.getChildren().addAll(titleBlock);
		
		leftVBar2.getChildren().addAll(artAuctioneer, artCreator, artYear, artHeight, artWidth, artDepth, artMaterial);
		leftVBar.getChildren().addAll(artworkImg, artName, artType);
		
		leftHBoxMain.getChildren().addAll(leftVBar, leftVBar2);
		rightVBar.getChildren().addAll(bidHistory, bidInput, bid);
		
		
		innerCenter.setLeft(leftHBoxMain);
		root.setCenter(innerCenter);
		root.setTop(mainTop);
		root.setBottom(bottomBar);
		root.setRight(rightVBar);
		
		
		return root;
	}

	private double convertBidInput(String bid) {
		try {
		double bidConvert = Double.parseDouble(bid);
		return bidConvert;
		} catch (Exception e) {
			return 0;
		}
	}
	
	private void attemptBid(Auction auction, double bid) {
		
		String bidResult = auction.attemptNewBid(currentUserObject.getUsername(), bid);
		
		if(bidResult.equalsIgnoreCase("valid")) {
			notificationBox("System Confirmation", "Bid Successful!", "Your bid of: " + bid + " has been added to the system");

		} else if(bidResult.equalsIgnoreCase("invalid")) {
			notificationBox("System Warrning", "Bid Unsuccessful!", "Your bid of: " + bid + " has not been added to the system,\n please check your bid and try again");

		} else if(bidResult.equalsIgnoreCase("win")) {
			notificationBox("System Confirmation", "Congradulations you won!", "You have just placed the winning bid: " + bid);
		}
	
	}
	
	

	public boolean newAuctionInputExistenceCheck(String auctionNameInput, String artCreatorInput, String artCreationYearInput, String maxBiddersInput) {
		if(auctionNameInput.length() == 0 || artCreatorInput.length() == 0 || artCreationYearInput.length() == 0 ||  maxBiddersInput.length() == 0 ) {
			notificationBox("Sign-Up Notification", "Input Error", "All fields must be filled out");
			return false;
		}
		return true;
	}
}