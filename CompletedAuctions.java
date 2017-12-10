import java.util.ArrayList;

/**
 * CompletedAuctions.java
 * Shows a list with all the Artwork which have successfully been auctioned.
 * @author Nikolina Antoniou
 */
public class CompletedAuctions {
	private ArrayList<Artwork> listOfArtworks;	//The list of Artwork.

	private String artworkName;					//The artwork name.
	private double winningBid;					//The winning bid.
	private String winningBidder;				//The winning bidder.

	/**
	 * Instantiates a new completed Auction.
	 */
	public CompletedAuctions() {

	}

	/**
	 * Gets the list of Artwork.
	 * @return a list of Artwork
	 */
	public ArrayList<Artwork> getListOfArtworks() {
		return listOfArtworks;
	}

	/**
	 * Sets the list of Artwork.
	 * @param listOfArtworks the new list of Artwork
	 */
	public void setListOfArtworks(ArrayList<Artwork> listOfArtworks) {
		this.listOfArtworks = listOfArtworks;
	}

	/**
	 * Gets the artwork name.
	 * @return the name of the artwork
	 */
	public String getArtworkName() {
		return artworkName;
	}

	/**
	 * Sets the artwork name.
	 * @param artworkName the new artwork name
	 */
	public void setArtworkName(String artworkName) {
		this.artworkName = artworkName;
	}

	/**
	 * Gets the winning bid.
	 * @return the winning bid
	 */
	public double getWinningBid() {
		return winningBid;
	}

	/**
	 * Sets the winning bid.
	 * @param winningBid the new winning bid
	 */
	public void setWinningBid(double winningBid) {
		this.winningBid = winningBid;
	}

	/**
	 * Gets the winning bidder.
	 * @return the winning bidder
	 */
	public String getWinningBidder() {
		return winningBidder;
	}

	/**
	 * Sets the winning bidder.
	 * @param winningBidder the new winning bidder
	 */
	public void setWinningBidder(String winningBidder) {
		this.winningBidder = winningBidder;
	}
}
