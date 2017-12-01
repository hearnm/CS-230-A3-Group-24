import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author nikolina
 * This class lets the user search for certain artworks
 */
public class BrowsingArtworks extends Application {
	
	public ArrayList<Artwork> getArtworks() {
		return artworks;
	}

	public void setArtworks(ArrayList<Artwork> artworks) {
		this.artworks = artworks;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	private ArrayList<Artwork> artworks;
	private String search;
	private static final int STAGE_WIDTH = 800;    //Width of the Stage
	private static final int STAGE_HEIGHT = 750;   //Height of the Stage
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("The Artatawe System");
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		
		Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();    //show scene
		
		
	}
}
