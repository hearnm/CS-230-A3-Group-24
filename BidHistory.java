import java.util.ArrayList;

/**
 * BidHistory.java
 * Class to view two sets of bidding history (seller / buyer).
 * @author James Shoemark-Smith
 */

public class BidHistory {
	/** The id counter. */
	private static int idCounter = 1;
	/** The bid history ID. */
	private int bidHistoryID;
	/** The time of the bid. */
	private String bidDateTime;
	/** The artwork name. */
	private String artworkName;
	/** The current bidder. */
	private String currentBidder;
	/** The current bid amount. */
	private double bidAmount;

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
	 *
	 * @return the placed bids
	 */
	public String getPlacedBids() {
		// This will return all of the placed bids (by the logged on user)
		return null;
	}

	/**
	 * Gets the received bids.
	 *
	 * @return the received bids
	 */
	public String getReceivedBids() {
		// This will return all of the received bids (by the logged on user)
		return null;
	}
}