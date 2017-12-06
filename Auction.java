import java.util.ArrayList;

/**
 * Auction.java
 * @author Matthew
 * The class that contains information pertaining to an auction of an item of artwork.
 */

public class Auction {
	
	public static ArrayList<Auction> completedAuctions = new ArrayList<Auction>();
	private static int nextId = 1;
	
	private final int AUCTION_ID;
	private double currentBid;
	private String currentBidder;
	private int remainingBids;
	private double reserveBid;
	
	private Artwork auctionedArtwork;
	

	/**
	* Constructor method to create the auction.
	* @param auctionedArtwork The artwork that is to be auctioned.
	* @param maxBids The maximum number of bids that may be placed on the artwork.
	* @param reserveBid The minimum bid that may be placed.
	*/
	public Auction(Artwork auctionedArtwork) {
		this.AUCTION_ID = nextId;
		nextId++;
		this.auctionedArtwork = auctionedArtwork;
		this.currentBid = 0.0;
		this.currentBidder = null;
		this.remainingBids = auctionedArtwork.getNumBidAllowed();
		this.reserveBid = auctionedArtwork.getReservePrice();
			
		
	}
	
	/**
	* Set method for assigning the auctioned artwork.
	* @param auctionedArtwork The artwork to be auctioned.
	*/
	public void setAuctionedArtwork(Artwork auctionedArtwork) {
		this.auctionedArtwork = auctionedArtwork;
	}
	
	/**
	* Get method for obtaining the auctioned artwork.
	* @return Artwork auctionedArtwork The artwork associated with the auction.
	*/
	public Artwork getAuctionedArtwork() {
		return this.auctionedArtwork;
	}
	
	/**
	* Attempts to add a new bid by first checking if the bid is valid
	* (using the checkIfBidValid method), then assigning the bid information and decreasing the
	* remaining bids by 1. If this value reaches 0, notify the winner and close the auction.
	* @param newBidder The speculative new bidder to be added to the auction.
	* @param newBid The speculative new bid to be added to the auction.
	*/
	public void attemptNewBid(String newBidder, double newBid) {
		if(checkIfBidValid(newBid)) {
			setCurrentBidder(newBidder);
			setCurrentBid(newBid);
			this.remainingBids -= 1;
			
			if(this.remainingBids == 0) {
				notifyWinner();
			}
		}
	}
	
	/**
	* Class for assigning a new bidder.
	* @param currentBidder The new current bidder to be assigned.
	*/
	private void setCurrentBidder(String currentBidder) {
		this.currentBidder = currentBidder;
	}
	
	/**
	* Class for assigning a new bid.
	* @param currentBid The new current bid to be assigned.
	*/
	private void setCurrentBid(double newBid) {
		if(checkIfBidValid(newBid)) {
			this.currentBid = newBid;
		}
	}
	
	public int getRemainingBids() {
		return this.remainingBids;
	}
	
	public double getCurrentBid() {
		return this.currentBid;
	}
	
	public void displayAcceptedBid() {
		//do something
	}
	
	public void notifyWinner() {
		//do something
	}
	
	public boolean checkIfBidValid(double speculativeBid) {
		return(speculativeBid > this.currentBid && speculativeBid >= reserveBid);
	}
	
	/**
	 * @return a list of the completed Auction
	 */
	public ArrayList<Auction> getCompletedAuctions(){
		return completedAuctions;
	}
	
}