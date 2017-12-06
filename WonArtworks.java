import java.util.ArrayList;

/**
 * WonArtworks.java
 * @author Christiana Meli
 * A class that shows to user a list of Artworks for which
they placed the winning bid.
 */

public class WonArtworks {
	
	private ArrayList<Artwork>listOfArtworks;
	private String artworkName;
	
	public WonArtworks(){
		
	}
	
	
	/**
	 * @return list of artworks
	 */
	public ArrayList<Artwork> getListOfArtworks() {
		return listOfArtworks;
	}
	
	/**
	 * @param listOfArtworks
	 */
	public void setListOfArtworks(ArrayList<Artwork> listOfArtworks) {
		this.listOfArtworks = listOfArtworks;
	}
	
	/**
	 * 
	 * @return name of Artwork
	 */
	public String getArtworkName(){
		return artworkName;
	}
	
	/**
	 * @param artworkName
	 */
	public void setArtworkName(String artworkName){
		this.artworkName= artworkName;
	}
	

}
