/**
 * Bidding.java
 * This class is used to create new individual bid Objects for a
 * specific auction.
 * @author Tom Durman
 */
public class Bidding {
	/** The username. */
	private String username;
	/** The bid amount. */
	private double bidAmount;

	/**
	 * Instantiates a new bidding.
	 * @param username the username
	 * @param bidAmount the bid amount
	 */
	public Bidding(String username, double bidAmount) {
		this.username = username;
		this.bidAmount = bidAmount;
	}

	/**
	 * Gets the username.
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Gets the bid amount.
	 * @return the bid amount
	 */
	public double getBidAmount() {
		return this.bidAmount;
	}
}