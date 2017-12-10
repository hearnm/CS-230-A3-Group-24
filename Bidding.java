/**
 * Bidding.java
 * This class is used to create new individual bid Objects for a specific auction.
 * @author Tom Durman
 */
public class Bidding {
	/** The username. */
	private String username;
	/** The bid amount. */
	private double bidAmount;
	/** The time of the bid. */
	private String timeOfBid;

	/**
	 * Instantiates a new bid.
	 * @param username the username
	 * @param bidAmount the bid amount
	 */
	public Bidding(String username, double bidAmount, String timeOfBid) {
		this.username = username;
		this.bidAmount = bidAmount;
		this.timeOfBid = timeOfBid;
	}

	/**
	 * Gets the username.
	 * @return username The username of the bidder
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Gets the bid amount.
	 * @return bidAmount The bid amount
	 */
	public double getBidAmount() {
		return this.bidAmount;
	}
	
	/**
	 * Gets the Time of the bid
	 * @return timeOfBid The time the bid was placed
	 */
	public String getTimeOfBid() {
		return this.timeOfBid;
	}
}