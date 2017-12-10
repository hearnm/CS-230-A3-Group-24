import java.util.ArrayList;

/**
 * WonArtworks.java
 * @author Christiana Meli
 * A class that shows to the user a list of Artworks for which they placed the winning bid.
 */
public class WonArtworks {
	
	private ArrayList<Artwork>listOfArtworks;
	
	public WonArtworks(){
		
	}
	
	/**
	 * Returns the list of Artwork
	 * @return list of Artwork
	 */
	public ArrayList<Artwork> getListOfArtworks() {
		return listOfArtworks;
	}
	
	/**
	 * Sets the list of Artwork
	 * @param listOfArtworks The list of Artwork
	 */
	public void setListOfArtworks(ArrayList<Artwork> listOfArtworks) {
		this.listOfArtworks = listOfArtworks;
	}
}
