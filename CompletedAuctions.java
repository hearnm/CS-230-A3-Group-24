import java.util.ArrayList;

/**
 * @author nikolina
 * Shows a list with all the artworks which have successfully been auctioned.
 */
public class CompletedAuctions {
	private ArrayList<Artwork> listOfArtworks;
	private String artworkName;
	private double winningBid;
	private String winningBidder;
	
	public CompletedAuctions(){
		
	}

	/**
	 * @return a list of artworks
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
	 * @return the name of the artwork
	 */
	public String getArtworkName() {
		return artworkName;
	}

	/**
	 * @param artworkName
	 */
	public void setArtworkName(String artworkName) {
		this.artworkName = artworkName;
	}

	/**
	 * @return the winning bid
	 */
	public double getWinningBid() {
		return winningBid;
	}

	/**
	 * @param winningBid
	 */
	public void setWinningBid(double winningBid) {
		this.winningBid = winningBid; 
	}

	/**
	 * @return the winning bidder
	 */
	public String getWinningBidder() {
		return winningBidder;
	}

	/**
	 * @param winningBidder
	 */
	public void setWinningBidder(String winningBidder) {
		this.winningBidder = winningBidder;
	}
	

}
