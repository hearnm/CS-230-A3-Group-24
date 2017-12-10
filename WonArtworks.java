import java.util.ArrayList;

/**
 * WonArtworks.java
 * A class that shows the user a list of Artwork for which they
 * placed the winning bid.
 * @author Christiana Meli
 */
public class WonArtworks {
	private ArrayList<Artwork>listOfArtworks;	//The list of Artwork.

	/**
	 * Instantiates an Artwork won.
	 */
	public WonArtworks() {

	}

	/**
	 * Returns the list of Artwork.
	 * @return List of Artwork
	 */
	public ArrayList<Artwork> getListOfArtworks() {
		return listOfArtworks;
	}

	/**
	 * Sets the list of Artwork.
	 * @param listOfArtworks The list of Artwork
	 */
	public void setListOfArtworks(ArrayList<Artwork> listOfArtworks) {
		this.listOfArtworks = listOfArtworks;
	}
}
