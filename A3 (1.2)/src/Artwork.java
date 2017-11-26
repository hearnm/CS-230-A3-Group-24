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
	
	/**
	 * Constructor of Artwork objects
	 * @param title
	 * @param creator
	 * @param artCreationYear
	 * @param reservePrice
	 * @param numBidsAllowed
	 */
	public Artwork(String title, String creator, int artCreationYear, double reservePrice, int numBidsAllowed) {
		this.title = title;
		this.creator = creator;
		this.artCreationYear = artCreationYear;
		this.reservePrice = reservePrice;
		this.numBidsAllowed = numBidsAllowed;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	
	
}
