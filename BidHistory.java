import java.util.*;
/**
 * BidHistory.java
 * @author James Shoemark-Smith
 * Class to view bidding history.
 */

public class BidHistory {
	
	private int bidHistoryID;
	private int bidTime;
	private String bidDate;
	private String bidArtwork;
	private String currentBidder
	private double bidAmount;
	
	public BidHistory(){ 
	}

	private String getUsername(){
		return username;
	}

	private String getDate(){
		return bidDate;
	}

	private int getAmount(){
		return bidAmount;
	}

	private String getArtwork(){
		return bidArtwork;
	}

	private String getTime(){
		return bidTime;
	}

	private void setDate(String bidDate){
		this.bidDate = bidDate;
	}

	private void setAmount(int bidAmount){
		this.bidAmount = bidAmount;
	}

	private void setArtwork(String bidArtwork){
		this.bidArtwork = bidArtwork;
	}

	private void setTime(int bidTime){
		this.bidTime = bidTime;
	}
}