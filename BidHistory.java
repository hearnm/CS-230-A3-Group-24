import java.util.ArrayList;

/**
 * BidHistory.java
 * @author James Shoemark-Smith
 * Class to view two sets of bidding history (seller / buyer).
 */

public class BidHistory {
	
	private static int idCounter = 1; 	// Static variable used to set a unique ID
	
	private int bidHistoryID;			// Variable for a unique ID
	private String bidDateTime;			// Variable for the time of the bid
	private String artworkName;			// Variable for the name of the Artwork
	private String currentBidder;		// Variable for the active bidder
	private double bidAmount;			// Variable for the current amount bid
	

	/**
	 * Constructor for Bid History.
	 */
	public BidHistory() { 	
		this.bidHistoryID = idCounter;
		idCounter++;
	}

	/**
	 * Method to get the Id of the current Bid History.
	 * @return bidHistory The Bid History Id
	 */
	public int getBidHistoryID() {
		return this.bidHistoryID;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getPlacedBids() {
		// This will return all of the placed bids (by the logged on user)
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReceivedBids() {
		// This will return all of the received bids (by the logged on user)
		return null;
	}
	
	
	

	
}