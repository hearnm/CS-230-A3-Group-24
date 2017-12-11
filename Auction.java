import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Auction.java
 * The class that contains information related to an auction of an artwork.
 * @author Matthew Hearn
 */
public class Auction {
	private static ArrayList<Auction> completedAuctions = new ArrayList<>();	//The completed auctions.
	private static ArrayList<Auction> auctions = new ArrayList<>();				//The auctions.

	private static int nextId = 1;		//The next id.
	@SuppressWarnings("unused")
	private final int AUCTION_ID;		//The auction id.

	private String title;				//The title.
	private double currentBid;			//The current bid.
	private String currentBidder;		//The current bidder.
	private int remainingBids;			//The remaining bids.
	private double reserveBid;			//The reserve bid.
	private Artwork auctionedArtwork;	//The auctioned artwork.

	private ArrayList<Bidding> bidHistory = new ArrayList<>();	//The bid history for an instance of an auction.

	/**
	* Constructor method to create the auction.
	* @param auctionedArtwork The artwork that is to be auctioned.
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
		
		sortAuctions();

	}

	
	private void sortAuctions() {
		if(this.auctionedArtwork.getOnAuction() == true) {
			auctions.add(this);
		} else {
			
			completedAuctions.add(this);
		}
	}
	
	public static ArrayList<Auction> getGivenUserWonArtworks(String username) {
		ArrayList<Auction> usersWonArtworks = new ArrayList<>();
		for(int i = 0; i < completedAuctions.size(); i++) {
			if(username.equalsIgnoreCase(completedAuctions.get(i).getCurrentBidder())) {
				usersWonArtworks.add(completedAuctions.get(i));
			}
		}
		return usersWonArtworks;
	}
	
	public static void setGivenWonArtworks(String title, String curBidder) {
		
		
		for(int i = 0; i < completedAuctions.size(); i++) {
			if(title.equalsIgnoreCase(completedAuctions.get(i).getAuctionedArtwork().getTitle())) {
				completedAuctions.get(i).setCurrentBidder(curBidder);
			}
		}
	
	}
	
	
	/**
	 * Method to get the current auctions on the system.
	 * @return auctions ArrayList of all current auctions
	 */
	public static ArrayList<Auction> getCurrentAuctions() {
		return auctions;
	}

	/**
	 * Method to get an auction object given an artwork name.
	 * @param artworkName Name of the artwork to be searched for
	 * @return Auction Object if exists
	 */
	public static Auction getGivenAuction(String artworkName) {
		for (int i = 0; i < auctions.size(); i++) {
			if (auctions.get(i).getCurrentArtTitle().equalsIgnoreCase(artworkName)) {
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
	* @return Artwork auctionedArtwork The artwork associated with the
	* auction.
	*/
	public Artwork getAuctionedArtwork() {
		return this.auctionedArtwork;
	}

	/**
	* Attempts to add a new bid in an Auction.
	* @param newBidder The new bidder to be added to the auction.
	* @param newBid The new bid to be added to the auction.
	* @return Winning Notification message is final bid, Null otherwise
	*/
	public String attemptNewBid(String newBidder, double newBid) {
		// Check if the bid is valid and the final bid
		if (checkIfBidValid(newBidder, newBid)
				&& this.remainingBids == 1) {
			
			auctionedArtwork.setOnAuction(false);
			this.currentBidder = newBidder;
			this.currentBid = newBid;
			this.remainingBids -= 1;
			// create a new bid object
			Bidding bid = new Bidding(newBidder, newBid,
					generateDateTime());
			this.bidHistory.add(bid);
			completedAuctions.add(this);
			return "win";
		}

		if(newBidder.equalsIgnoreCase(this.auctionedArtwork.getAuctioneer())){
			return "selfbid";
		}
		if (checkIfBidValid(newBidder, newBid)) {
			this.currentBidder = newBidder;
			this.currentBid = newBid;
			this.remainingBids -= 1;
			// create a new bid object
			Bidding bid = new Bidding(newBidder, newBid,
					generateDateTime());
			this.bidHistory.add(bid);

			return "valid";
		}
		return "invalid";
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
	* @param newBid The new current bid to be assigned.
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
	 * Method to get the current (highest) bidder on the instance of an
	 * auction.
	 * @return currentBidder The current bidders username
	 */
	public String getCurrentBidder() {
		return this.currentBidder;
	}

	/**
	 * Method to check if bid valid.
	 * @param bidder The bidder
	 * @param speculativeBid The speculative bid
	 * @return true, if valid
	 */
	public boolean checkIfBidValid(String bidder, double speculativeBid) {
		return (speculativeBid > this.currentBid && speculativeBid
				>= reserveBid && !bidder.equalsIgnoreCase(this.currentBidder));
	}

	/**
	 * Gets the completed Auctions.
	 * @return a list of the completed Auction
	 */
	public ArrayList<Auction> getCompletedAuctions() {
		return completedAuctions;
	}

	/**
	 * Method to generate the time of a bid.
	 * @return time The time of the bid (format hh:mm:ss a)
	 */
	private String generateDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        Date date = new Date();
        String time = dateFormat.format(date);

		return time.toString();
	}

	/**
	 * Method to get the current bid history of an auction.
	 * @return bidHistory An ArrayList of Bids
	 */
	public ArrayList<Bidding> getBids() {
		return this.bidHistory;
	}

	/**
	 * Method to load in existing bids when the system loads.
	 * @param username The Username of the bidder
	 * @param bid The bid of the user
	 * @param time The time the bid was placed
	 */
	public void addExistingBids(String username, String bid, String time) {
		double bidConvert = Double.parseDouble(bid);
		Bidding loadedBid = new Bidding(username, bidConvert, time);
		bidHistory.add(loadedBid);
		this.currentBid = bidConvert;
		this.currentBidder = username;
		this.remainingBids--;
	}
}
