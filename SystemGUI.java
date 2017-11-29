import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SystemGUI extends Application {
	
	private static final int STAGE_WIDTH = 700;		// Width of the Stage
	private static final int STAGE_HEIGHT= 450;		// Height of the Stage
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	
	public void start(Stage primaryStage) {
				
		Pane root = buildLoginGUI();
		root.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #778899); ");
		
		Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Pane buildLoginGUI() {
		
		BorderPane root = new BorderPane();
		BorderPane innerMid = new BorderPane();
		StackPane title = new StackPane();
		StackPane loginBox = new StackPane();
		VBox loginV = new VBox();
		
		root.setPadding(new Insets(10,10,10,10));
		innerMid.setPadding(new Insets(100,100,100,100));
		loginBox.setPadding(new Insets(10,10,10,10));
		
		loginBox.setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Button loginButton = new Button("Login");
		Button signupButton  = new Button("Sign up");
		
		loginButton.setMaxWidth(100);
		
        Text text = new Text("Artewea\n");
        Text text2 = new Text("\n\nLogin Screen\n");
        Text usernameLogin = new Text("\nUsername");
        TextField usernameInput = new TextField();
        
        usernameInput.setMaxWidth(250);
        
        text.setScaleX(4);
        text.setScaleY(4);
        text2.setScaleX(2);
        text2.setScaleY(2);
        usernameLogin.setScaleX(1.5);
        usernameLogin.setScaleY(1.5);
        
        text2.setTextAlignment(TextAlignment.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        usernameLogin.setTextAlignment(TextAlignment.CENTER);
        
        
        StackPane.setAlignment(text, Pos.CENTER);
        StackPane.setAlignment(text2, Pos.CENTER);
        StackPane.setAlignment(usernameLogin, Pos.TOP_CENTER);
        StackPane.setAlignment(usernameInput, Pos.CENTER);
        StackPane.setAlignment(loginButton, Pos.BOTTOM_CENTER);
        
		
        loginBox.getChildren().addAll(usernameLogin, usernameInput, loginButton);
		title.getChildren().addAll(text, text2);
		
		
		innerMid.setCenter(loginBox);
		innerMid.setTop(title);
		
		innerMid.setBottom(signupButton);
		
		root.setCenter(innerMid);
		
		
		return root;
	}
	
	
	
}
