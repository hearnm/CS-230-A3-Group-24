import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BuildGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = buildSearchArtworkGUI();
		Scene scene = new Scene(root,700,500);
		primaryStage.setTitle("Artatawe Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Pane buildSearchArtworkGUI(){
		
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
}
