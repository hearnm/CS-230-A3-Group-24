import java.util.ArrayList;
import java.util.Date;

/**
 * Auction.java
 * The class that contains information related to an auction of an artwork.
 * @author Matthew Hearn
 */
public class Auction {
	/** The completed auctions. */
	// Personal note, completed auctions might require a boolean (completed) parameter in the auction constructor.
	public static ArrayList<Auction> completedAuctions = new ArrayList<>();
	/** The auctions. */
	public static ArrayList<Auction> auctions = new ArrayList<>();
	
	public ArrayList<Bidding> bids = new ArrayList<>();

	/** The next id. */
	private static int nextId = 1;
	/** The auction id. */
	private final int AUCTION_ID;
	/** The title. */
	private String title;
	/** The current bid. */
	private double currentBid;
	/** The current bidder. */
	private String currentBidder;
	/** The remaining bids. */
	private int remainingBids;
	/** The reserve bid. */
	private double reserveBid;
	/** The auctioned artwork. */
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
		this.title = auctionedArtwork.getTitle();
		this.auctionedArtwork = auctionedArtwork;
		this.currentBid = 0.0;
		this.currentBidder = null;
		this.remainingBids = auctionedArtwork.getNumBidAllowed();
		this.reserveBid = auctionedArtwork.getReservePrice();
		auctions.add(this);
	}
	
	/**
	 * Method to get the current auctions on the system
	 * @return auctions ArrayList of all current auctions
	 */
	public static ArrayList<Auction> getCurrentAuctions() {
		return auctions;
	}
	
	/**
	 * Method to get an auction object given an artwork name
	 * @param artworkName Name of the artwork to be searched for
	 * @return Auction Object if exists
	 */
	public static Auction getGivenAuction(String artworkName) {
		for(int i = 0; i < auctions.size(); i++) {
			if(auctions.get(i).getCurrentArtTitle().equalsIgnoreCase(artworkName)) {
				return auctions.get(i);
			}
		}	
		return null;
	}
	
	/**
	 * Method to get the current Art title.
	 * @return title The current Art Title
	 */
	public String getCurrentArtTitle() {
		return this.title;
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
	

	
	public boolean getIsCompleted() {
		if(this.remainingBids == 0) {
			this.auctionedArtwork.setOnAuction(false);
			return true;
		}
		return false;
	}
	
	
	/**
	* Attempts to add a new bid in an Auction.
	* @param newBidder The new bidder to be added to the auction.
	* @param newBid The new bid to be added to the auction.
	* @return Winning Notification message is final bid, Null otherwise
	*/
	public String attemptNewBid(String newBidder, double newBid) {
		
		
		if(checkIfBidValid(newBidder, newBid) && this.remainingBids == 1) {
			completedAuctions.add(this);
			return "win";
		}

		if(checkIfBidValid(newBidder, newBid)) {
			this.currentBidder = newBidder;
			this.currentBid = newBid;
			this.remainingBids -= 1;
			
			Bidding bid = new Bidding(newBidder, newBid, generateDateTime());
			this.bids.add(bid);
	
			return "valid";
		}
		return "invalid";
	}
	
	public ArrayList<Bidding> getAuctionBidHistory() {
		return this.bids;	
	}
	
	/**
	* Class for assigning a new bidder.
	* @param currentBidder The new current bidder to be assigned.
	*/
	public void setCurrentBidder(String currentBidder) {
		this.currentBidder = currentBidder;
	}
	
	/**
	* Class for assigning a new bid.
	* @param currentBid The new current bid to be assigned.
	*/
	public void setCurrentBid(double newBid) {
		this.currentBid = newBid;
	
	}
	
	/**
	 * Sets the remaining bids.
	 * @param remainingBids the new remaining bids
	 */
	public void setRemainingBids(int remainingBids) {
		this.remainingBids = remainingBids;
	}
	
	/**
	 * Gets the remaining bids.
	 * @return the remaining bids
	 */
	public int getRemainingBids() {
		return this.remainingBids;
	}
	
	/**
	 * Gets the current bid.
	 * @return the current bid
	 */
	public double getCurrentBid() {
		return this.currentBid;
	}
	
	/**
	 * Display accepted bid.
	 */
	public void displayAcceptedBid() {
		//do something
	}
	
	/**
	 * Notify winner.
	 * @return the string
	 */
	public String notifyWinner() {
		return "Congratulations! You have just placed the winning bid of: "
				+ this.currentBid + " for:" 
				+ this.getCurrentArtTitle();
	}
	
	/**
	 * Check if bid valid.
	 * @param bidder the bidder
	 * @param speculativeBid the speculative bid
	 * @return true, if successful
	 */
	public boolean checkIfBidValid(String bidder, double speculativeBid) {
		return(speculativeBid > this.currentBid && speculativeBid
				>= reserveBid && !bidder.equalsIgnoreCase(this.currentBidder));
	}
	
	/**
	 * Gets the completed auctions.
	 * @return a list of the completed Auction
	 */
	public ArrayList<Auction> getCompletedAuctions(){
		return completedAuctions;
	}	
	
	
	private String generateDateTime() {
		Date generatedDate = new Date();
		return generatedDate.toString();
	}
	
	
}


