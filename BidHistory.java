import java.util.*;

/**
 * BidHistory.java
 * @author James Shoemark-Smith
 * Class to view bidding history.
 */

public class BidHistory {
	
	private int bidHistoryID;		//Variable for a unique ID
	private int bidTime;			//Variable for the time of the bid
	private String bidDate;			//Variable for the date of the bid
	private String bidArtwork;		//Variable for the artwork in question
	private String currentBidder;	//Variable for the active bidder
	private double bidAmount;		//Variable for the current amount bid
	
	public BidHistory(){ 			//Creation of the bidHistory constructor
	}

	public int getBidHistoryID() {
		return bidHistoryID;
	}

	public void setBidHistoryID(int bidHistoryID) {
		this.bidHistoryID = bidHistoryID;
	}

	public int getBidTime() {
		return bidTime;
	}

	public void setBidTime(int bidTime) {
		this.bidTime = bidTime;
	}

	public String getBidDate() {
		return bidDate;
	}

	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}

	public String getBidArtwork() {
		return bidArtwork;
	}

	public void setBidArtwork(String bidArtwork) {
		this.bidArtwork = bidArtwork;
	}

	public String getCurrentBidder() {
		return currentBidder;
	}

	public void setCurrentBidder(String currentBidder) {
		this.currentBidder = currentBidder;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	
}