import java.util.ArrayList;

/**
 * Sculpture.java
 * @author Emily Fothergill
 * Class to create Sculpture objects and its related methods.
 */
public class Sculpture extends Artwork {
	protected ArrayList <String> additionalImages = new ArrayList <String> ();
	protected double depth;
	protected String material;
	
	/**
	 * Constructor of Sculpture objects.
	* @param auctioneer The user that placed the Sculpture on auction.
	 * @param title The Title of the Sculpture.
	 * @param creator The Creator of the Sculpture.
	 * @param artCreationYear The Creation Year of the Sculpture.
	 * @param reservePrice The Reserve Price of the Sculpture.
	 * @param numBidsAllowed The Maximum Number of Bids a Sculpture can have.
	 * @param width The Width Dimension of the Sculpture.
	 * @param height The Height Dimension of the Sculpture.
	 * @param depth The Depth Dimension of the Sculpture.
	 * @param material The Material of the Sculpture.
	 * @param newArt The boolean value that sets the Sculpture as new to the System.
	 * @param onAuction The boolean value that sets the Sculpture to on auction.
	 */
	public Sculpture(String auctioneer, String title, String creator, int artCreationYear,
					double reservePrice, int numBidsAllowed, double width, double height, 
					double depth, String material, boolean newArt, boolean onAuction) {
		super(auctioneer, title, creator, artCreationYear, reservePrice, numBidsAllowed, newArt, onAuction);

		this.height = height;
		this.width = width;
		this.depth = depth;
		this.material = material;
		setArtType("Sculpture");
	}
	
	/**
	 * Method to change the material of the Sculpture.
	 * @param material The Material of the Sculpture.
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
	 * @param depth The Depth Dimension of the Sculpture.
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
	 * Method to change the images of the Sculpture.
	 * @param additionalImages
	 */
	protected void setAdditionalImages(ArrayList<String> newAdditionalImages) {
		for(int i = 0; i < newAdditionalImages.size(); i++) {
			additionalImages.add(newAdditionalImages.get(i));
		}
	}
	
	/**
	 * Method to retrieve the additional images of the Sculpture.
	 * @return additional images
	 */
	protected ArrayList<String> getAdditionalImages() {
		return additionalImages;
	}
}