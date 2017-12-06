/**
 * Class to create Painting objects.
 * @author Emily Fothergill
 */

public class Painting extends Artwork {

	/**
	 * Constructor of Painting objects.
	 * @param title
	 * @param creator
	 * @param artCreationYear
	 * @param reservePrice
	 * @param numBidsAllowed
	 * @param width
	 * @param height
	 */
	public Painting(String auctioner, String title, String creator, int artCreationYear, 
					double reservePrice, int numBidsAllowed, double width, double height, boolean newArt) {
		super(auctioner, title, creator, artCreationYear, reservePrice, numBidsAllowed, newArt);
		this.width = width;
		this.height = height;
		setArtType("Painting");
	}
}
