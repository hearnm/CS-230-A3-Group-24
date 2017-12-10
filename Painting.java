/**
 * Painting.java
 * Class to create Painting objects.
 * @author Emily Fothergill
 */
public class Painting extends Artwork {
	/**
	 * Constructor of Painting objects.
	 * @param auctioneer The user that placed the Painting on auction.
	 * @param title The Title of the Painting.
	 * @param creator The Creator of the Painting.
	 * @param artCreationYear The Creation Year of the Painting.
	 * @param reservePrice The Reserve Price of the Painting.
	 * @param numBidsAllowed The Maximum Number of Bids a Painting can have.
	 * @param width The Width Dimension of the Painting.
	 * @param height The Height Dimension of the Painting.
	 * @param newArt The boolean value that sets Painting as new to System.
	 * @param onAuction The boolean value that sets Painting to on auction.
	 */
	public Painting(String auctioneer, String title, String creator, int artCreationYear, 
					double reservePrice, int numBidsAllowed, double width, double height, boolean newArt, boolean onAuction) {
		super(auctioneer, title, creator, artCreationYear, reservePrice, numBidsAllowed, newArt, onAuction);

		this.width = width;
		this.height = height;
		setArtType("Painting");
	}
}
