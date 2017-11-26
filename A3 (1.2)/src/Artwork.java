/**
 * 
 * @author Emily Fothergill
 *
 */
public class Artwork {
	private int artworkID;
	private String title;
	private String description;
	private String creator;
	private int artCreationYear;
	private double reservePrice;
	private String artTimeDate;
	private int numBidsAllowed;
	
	public Artwork(String title, String creator, int artCreationYear, double reservePrice, int numBidsAllowed) {
		this.title = title;
		this.creator = creator;
		this.artCreationYear = artCreationYear;
		this.reservePrice = reservePrice;
		this.numBidsAllowed = numBidsAllowed;
	}
	
	
}
