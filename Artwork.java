/**
 * Super class Artwork to contain all variables and methods applicable to Sculpture
 * and Painting.
 * @author Emily Fothergill
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
	private String mainImagePath;
	
	/**
	 * Constructor of Artwork objects.
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
	
	/**
	 * Method to change the title of the Artwork.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Method to retrieve the title of the Artwork.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Method to change set the description of the Artwork.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Method to retrieve the description of the Artwork.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Method to change the creator of the Artwork.
	 * @param creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	/**
	 * Method to retrieve the creator of the Artwork.
	 * @return creator
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Method to change the year the Artwork was created.
	 * @param artCreationYear
	 */
	public void setArtCreationYear(int artCreationYear) {
		this.artCreationYear = artCreationYear;
	}
	
	/**
	 * Method to retrieve the year the Artwork was created.
	 * @return year of creation
	 */
	public int getArtCreationYear() {
		return artCreationYear;
	}
	
	/**
	 * Method to change the reserve price of the Artwork.
	 * @param reservePrice
	 */
	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}
	
	/**
	 * Method to retrieve the reserve price of the Artwork.
	 * @return reserve price
	 */
	public double getReservePrice() {
		return reservePrice;
	}
	
	/**
	 * Method to change the max number of bids allowed on the Artwork.
	 * @param numBidsAllowed
	 */
	public void setNumBidAllowed(int numBidsAllowed) {
		this.numBidsAllowed = numBidsAllowed;
	}
	
	/**
	 * Method to retrieve the max number bids allowed on the Artwork.
	 * @return max number of bids allowed
	 */
	public int getNumBidAllowed() {
		return numBidsAllowed;
	}
	
	/**
	 * 
	 * @return
	 */
	private String generateTimeDate() {
		String date;
		String time;
		//artTimeDate = date + time;
		return artTimeDate;
	}
	
	/**
	 * 
	 * @param artTimeDate
	 */
	public void assignTimeDate(String artTimeDate) {
		
	}
	
}
