/**
 * Super class Artwork to contain all variables and methods applicable to Sculpture
 * and Painting.
 * @author Emily Fothergill
 */

import java.util.Date;

public class Artwork {
	protected int artworkID;
	protected String title;
	protected String description;
	protected String creator;
	protected int artCreationYear;
	protected double reservePrice;
	protected String artTimeDate;
	protected int numBidsAllowed;
	protected String mainImagePath;
	protected double height;
	protected double width;
	protected String artType;
	
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
	 * Method to generate the time and date the Artwork 
	 * goes on the system.
	 * @return artTimeDate
	 */
	private String generateTimeDate() {
		Date d = new Date();
		artTimeDate = d.toString();
		return artTimeDate;
	}
	
	/**
	 * Method to change the height.
	 * @param height
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Method to retrieve height.
	 * @return height
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Method to change the width.
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * Method to retrieve the width.
	 * @return width
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Method to set the type of Artwork.
	 * @param artType
	 */
	public void setArtType(String artType) {
		this.artType = artType;
	}
}
	
