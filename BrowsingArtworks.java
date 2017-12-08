import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * BrowsingArtworks.java
 * This class lets the user search for certain Artwork.
 * @author Nikolina Antoniou
 */
public class BrowsingArtworks extends Application {
	/** The artworks. */
	private ArrayList<Artwork> artworks;
	/** The search. */
	private String search;
	/** The Constant STAGE_WIDTH. */
	private static final int STAGE_WIDTH = 800;    //Width of the Stage
	/** The Constant STAGE_HEIGHT. */
	private static final int STAGE_HEIGHT = 750;   //Height of the Stage

	/**
	 * Gets the artworks.
	 * @return the artworks
	 */
	public ArrayList<Artwork> getArtworks() {
		return artworks;
	}

	/**
	 * Sets the artworks.
	 * @param artworks the new artworks
	 */
	public void setArtworks(ArrayList<Artwork> artworks) {
		this.artworks = artworks;
	}

	/**
	 * Gets the search.
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Sets the search.
	 * @param search the new search
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * The main method.
	 * @param args the arguments
	 */
	public static void main(String[] args){
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
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