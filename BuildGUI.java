import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BuildGUI extends Application {
	Stage window;
	ListView<Artwork> listOfArtworks;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		Scene scene = getHomeScene();
		window.setTitle("Artatawe Application");
		window.setScene(scene);
		window.show();
	}

	private Scene getHomeScene(){
		
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");
		
		VBox top = new VBox();
		HBox titleBar = new HBox();
		HBox buttonBar = new HBox();
		VBox bottom = new VBox();
		HBox homepage = new HBox();
		HBox bottomBar = new HBox();
		
		top.setSpacing(15);
		top.setPadding(new Insets(50,20,20,0));
		
		buttonBar.setSpacing(170);
		buttonBar.setPadding(new Insets(40,20,0,40));
		
		homepage.setPadding(new Insets(0,100,60,100));
		
		bottom.setSpacing(15);
		bottom.setPadding(new Insets(0,0,0,0));
		
		//Create elements that are needed for top VBox
		Text title = new Text("Artatawe");
		Button auctions = new Button("Auctions");
		Button paintings = new Button("Paintings");
		Button sculptures = new Button("Sculptures");
		
		//Postion title text
		title.setScaleX(4);
		title.setScaleY(4);
		title.setTextAlignment(TextAlignment.CENTER);
		
		//Resize buttons
		auctions.resize(87,20);
		paintings.resize(87, 20);
		sculptures.resize(87, 20);
		
		//Set action on buttons
		auctions.setOnAction(e -> window.setScene(getAuctionScene()));
		paintings.setOnAction(e -> window.setScene(getPaintingScene()));
		sculptures.setOnAction(e -> window.setScene(getSculptureScene()));
					
		//Add elements to the top VBox
		buttonBar.getChildren().addAll(auctions,paintings,sculptures);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		titleBar.getChildren().add(title);
		top.getChildren().addAll(titleBar,buttonBar);
		
		//Create elements that are needed for bottom VBox
		Button logOut = new Button("Log out");
		
		listOfArtworks = new ListView<>();
		//listOfArtworks.setItems(Artwork.getArtworks());

		//Resize button
		logOut.resize(87,20);

		//Add elements to the bottom VBox
		homepage.getChildren().add(listOfArtworks);
		bottom.getChildren().add(homepage);
	
		//Set root
		root.setTop(top);
		root.setBottom(bottom);
		
		Scene scene = new Scene(root,700,500);
		return scene;
	}
	private Scene getAuctionScene() {

		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");

		VBox top = new VBox();
		VBox titleBar = new VBox();
		HBox buttonBar = new HBox();
		VBox bottom = new VBox();
		HBox bottomBar = new HBox();
		
		top.setSpacing(15);
		top.setPadding(new Insets(50,20,20,0));

		buttonBar.setSpacing(170);
		buttonBar.setPadding(new Insets(40,20,0,40));
		
		//Create elements that are needed for top VBox
		Text title = new Text("Auctions");
		Button back = new Button("Home");
		
		back.setOnAction(e -> window.setScene(getHomeScene()));
		
		//Position title text 
		title.setScaleX(4);
		title.setScaleY(4);
		title.setTextAlignment(TextAlignment.CENTER);
		
		//Resize buttons
		back.resize(87,80);
		
		//Set top VBox
		buttonBar.getChildren().add(back);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		titleBar.getChildren().add(title);
		top.getChildren().addAll(titleBar,buttonBar);
		
		//Set root
		root.setTop(top);
		
		Scene scene = new Scene(root,700,500);
		return scene;
	}
	
	private Scene getPaintingScene(){
		
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");

		VBox top = new VBox();
		VBox titleBar = new VBox();
		HBox buttonBar = new HBox();
		VBox bottom = new VBox();
		HBox bottomBar = new HBox();
		
		top.setSpacing(15);
		top.setPadding(new Insets(50,20,20,0));

		buttonBar.setSpacing(170);
		buttonBar.setPadding(new Insets(40,20,0,40));
		
		//Create elements that are needed for top VBox
		Text title = new Text("Paintings");
		Button back = new Button("Home");
		
		back.setOnAction(e -> window.setScene(getHomeScene()));
		
		//Position title text 
		title.setScaleX(4);
		title.setScaleY(4);
		title.setTextAlignment(TextAlignment.CENTER);
		
		//Resize buttons
		back.resize(87,80);
		
		buttonBar.getChildren().add(back);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		titleBar.getChildren().add(title);
		top.getChildren().addAll(titleBar,buttonBar);
		root.setTop(top);
		Scene scene = new Scene(root,700,500);
		return scene;
	}
	
	private Scene getSculptureScene(){
		
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899);");

		VBox top = new VBox();
		VBox titleBar = new VBox();
		HBox buttonBar = new HBox();
		VBox bottom = new VBox();
		HBox bottomBar = new HBox();
		
		top.setSpacing(15);
		top.setPadding(new Insets(50,20,20,0));

		buttonBar.setSpacing(170);
		buttonBar.setPadding(new Insets(40,20,0,40));
		
		//Create elements that are needed for top VBox
		Text title = new Text("Sculptures");
		Button back = new Button("Home");
		
		back.setOnAction(e -> window.setScene(getHomeScene()));
		
		//Position title text 
		title.setScaleX(4);
		title.setScaleY(4);
		title.setTextAlignment(TextAlignment.CENTER);
		
		//Resize buttons
		back.resize(87,80);
		
		buttonBar.getChildren().add(back);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		titleBar.getChildren().add(title);
		top.getChildren().addAll(titleBar,buttonBar);
		
		root.setTop(top);
		Scene scene = new Scene(root,700,500);
		return scene;
	}
	

}

