import java.util.ArrayList;

/**
 * BidHistory.java
 * Class to view two sets of bidding history (seller / buyer).
 * @author James Shoemark-Smith
 */
public class BidHistory {
	private static int idCounter = 1;	//The id counter.
	private int bidHistoryID;			//The bid history ID.
	private String bidDateTime;			//The time of the bid.
	private String artworkName;			//The artwork name.
	private String currentBidder;		//The current bidder.
	private double bidAmount;			//The current bid amount.

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
	 * Gets the placed bids.
	 * @return the placed bids
	 */
	public String getPlacedBids() {
		// This will return all of the placed bids (by the logged on user)
		return null;
	}

	/**
	 * Gets the received bids.
	 * @return the received bids
	 */
	public String getReceivedBids() {
		// This will return all of the received bids (by the logged on user)
		return null;
	}
}
