
/**
 * Bidding.java
 * @author Durman
 * This class is used to create new individual bid Objects for a specific auction.
 */
public class Bidding {

	private String username;
	private double bidAmount;
	
	
	public Bidding(String username, double bidAmount) {
		this.username = username;
		this.bidAmount = bidAmount;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public double getBidAmount() {
		return this.bidAmount;
	}

}