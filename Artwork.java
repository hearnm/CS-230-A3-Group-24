/**
 * Artwork.java
 * @author Emily Fothergill
 * This class creates and stores Artwork.
 * This is a Super Class with two subclasses :- Painting and Sculpture
 */

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.ObservableList;

public class Artwork {
	protected int artworkID;		// The Unique ID of an artwork piece
	protected String auctioneer;
	protected String title;			// The Title of the Artwork
	protected String description;	// The Description of the Artwork
	protected String creator;		// The Creator of the Artwork
	protected int artCreationYear;	// The Creation Year of the Artwork
	protected double reservePrice;	// The Reserve Price of the Artwork
	protected String artTimeDate;	// The Time and Date the Artwork was put up
	protected int numBidsAllowed;	// The Maximum Number of Bids an Artwork can have
	protected String mainImagePath;	// The Directory Path to the Main Artwork Image
	protected double height;		// The Height Dimension of the Artwork
	protected double width;			// The Width Dimension of the Artwork
	protected double depth;
	protected String material;
	protected String artType;		// The Type of the Artwork (Sculpture / Painting)
	protected boolean newArt;
	protected boolean onAuction;
	
	protected static ArrayList<Artwork> artworks = new ArrayList<>();
	
	/**
	 * Constructor of Artwork objects.
	 * @param title Title of the Artwork
	 * @param creator Creator of the Artwork
	 * @param artCreationYear Creation year of the Artwork
	 * @param reservePrice Given reservation price of the Artwork
	 * @param numBidsAllowed The set Number of Bids allowed on the Artwork
	 */

	public Artwork(String auctioneer, String title, String creator, int artCreationYear, double reservePrice, int numBidsAllowed, boolean newArt, boolean onAuction) {
		this.auctioneer = auctioneer;

		this.title = title;
		this.creator = creator;
		this.artCreationYear = artCreationYear;
		this.reservePrice = reservePrice;
		this.numBidsAllowed = numBidsAllowed;
		generateTimeDate();
		artworks.add(this);
		
		sortArtwork();
		
		
	}
	
	public void sortArtwork() {
		if(this.onAuction == true) {
			Auction newAuction = new Auction(this);
		}
	}

	public void setOnAuction(boolean onAuction) {
		this.onAuction = onAuction;
	}
	
	public boolean getOnAuction() {
		return this.onAuction;
	}
	
	
	/**
	 * Method to change the title of the Artwork.
	 * @param title The new Title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Method to retrieve the title of the Artwork.
	 * @return title The current Title
	 */
	public String getTitle() {
		return this.title;
	}
	
	public String getAuctioneer() {
		return this.auctioneer;
	}
	
	/**
	 * Method to change the material of the Sculpture.
	 * @param material
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	
	/**
	 * Method to retrieve the material of the Sculpture.
	 * @return material
	 */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * Method to change the depth of the Sculpture.
	 * @param depth
	 */
	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	/**
	 * Method to retrieve the depth of the Sculpture.
	 * @return depth
	 */
	public double getDepth() {
		return depth;
	}
	
	
	
	/**
	 * Method to change set the description of the Artwork.
	 * @param description The new Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Method to retrieve the description of the Artwork.
	 * @return description The current Description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Method to change the creator of the Artwork.
	 * @param creator The new Creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	/**
	 * Method to retrieve the creator of the Artwork.
	 * @return creator The current Creator
	 */
	public String getCreator() {
		return this.creator;
	}
	
	/**
	 * Method to change the year the Artwork was created.
	 * @param artCreationYear The new Creation Year
	 */
	public void setArtCreationYear(int artCreationYear) {
		this.artCreationYear = artCreationYear;
	}
	
	/**
	 * Method to retrieve the year the Artwork was created.
	 * @return artCreationYear The current Creation Year
	 */
	public int getArtCreationYear() {
		return this.artCreationYear;
	}
	
	/**
	 * Method to change the reserve price of the Artwork.
	 * @param reservePrice The new Reserve Price
	 */
	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}
	
	/**
	 * Method to retrieve the reserve price of the Artwork.
	 * @return reserve price The current Reserve Price
	 */
	public double getReservePrice() {
		return this.reservePrice;
	}
	
	/**
	 * Method to change the max number of bids allowed on the Artwork.
	 * @param numBidsAllowed The new Maximum number of Bids Allowed
	 */
	public void setNumBidAllowed(int numBidsAllowed) {
		this.numBidsAllowed = numBidsAllowed;
	}
	
	/**
	 * Method to retrieve the max number bids allowed on the Artwork.
	 * @return numBidsAllowed the current Maximum number of Bids Allowed
	 */
	public int getNumBidAllowed() {
		return this.numBidsAllowed;
	}
	
	
	/**
	 * Method to change the height.
	 * @param height The new Height Value
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Method to retrieve height.
	 * @return height The current Height 
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Method to change the width.
	 * @param width The new Width Value
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	
	/**
	 * Method to retrieve the width.
	 * @return width The current Width
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Method to set the type of Artwork.
	 * @param artType The new Artwork Type
	 */
	public void setArtType(String artType) {
		this.artType = artType;
	}
	
	/**
	 * Method to get the type of Artwork.
	 * @param artType The current Artwork Type
	 */
	public String getArtType() {
		return this.artType;
	}
	
	/**
	 * Method to get the current artworks date and time
	 * @return artTimeDate The Artworks creation time and date
	 */
	public String getArtworkDateTime() {
		return this.artTimeDate;
	}
	
	/**
	 * Method to generate the time and date the Artwork goes on the system.
	 * @return artTimeDate
	 */
	public void generateTimeDate() {
		Date generatedDate = new Date();
		artTimeDate = generatedDate.toString();
	}

}
	
