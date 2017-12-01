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
	private String currentBidder	//Variable for the active bidder
	private double bidAmount;		//Variable for the current amount bid
	
	public BidHistory(){ 			//Creation of the bidHistory constructor
	}

	private String getUsername(){	
		return username;
	} 	
	
	/**
	*param Gets the username of the bidder
	**/

	private String getDate(){
		return bidDate;
	}

	/**
	*param gets the date of the most recent bid
	**/

	private int getAmount(){
		return bidAmount;
	}

	/**
	*param gets the amount of the current bid
	**/

	private String getArtwork(){
		return bidArtwork;
	}

	/**
	*param gets the artwork in question
	**/

	private String getTime(){
		return bidTime;
	}

	/**
	*param gets the time of the most recent bid
	**/

	private void setDate(String bidDate){
		this.bidDate = bidDate;
	}

	/**
	*param sets the current date
	**/

	private void setAmount(int bidAmount){
		this.bidAmount = bidAmount;
	}

	/**
	*param sets the current amount bid
	**/

	private void setArtwork(String bidArtwork){
		this.bidArtwork = bidArtwork;
	}

	/**
	*param sets the curretn artwork
	**/

	private void setTime(int bidTime){
		this.bidTime = bidTime;
	}

	/**
	*param sets the time of the most recent bid
	**/
}