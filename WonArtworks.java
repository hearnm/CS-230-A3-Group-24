import java.util.ArrayList;

/**
 * WonArtworks.java
 * @author Christiana Meli
 * A class that shows to the user a list of Artworks for which they placed the winning bid.
 */

public class WonArtworks {
	
	private ArrayList<Artwork>listOfArtworks;
	private String artworkName;
	
	public WonArtworks(){
		
	}
	
	/**
	 * Returns the list of artworks
	 * @return list of artworks
	 */
	public ArrayList<Artwork> getListOfArtworks() {
		return listOfArtworks;
	}
	
	/**
	 * Sets the list of Artworks
	 * @param listOfArtworks
	 */
	public void setListOfArtworks(ArrayList<Artwork> listOfArtworks) {
		this.listOfArtworks = listOfArtworks;
	}
}
