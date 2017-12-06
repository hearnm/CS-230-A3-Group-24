/**
 * Class to create Sculpture objects and its related methods.
 * @author Emily Fothergill
 */

import java.util.ArrayList;

public class Sculpture extends Artwork {
	protected ArrayList <String> additionalImages = new ArrayList <String> ();
	protected double depth;
	protected String material;
	
	/**
	 * Constructor of Sculpture objects.
	 * @param title
	 * @param creator
	 * @param artCreationYear
	 * @param reservePrice
	 * @param numBidsAllowed
	 * @param width
	 * @param height
	 * @param depth
	 * @param material
	 */
	public Sculpture(String auctioner, String title, String creator, int artCreationYear,
					double reservePrice, int numBidsAllowed, double width, double height, 
					double depth, String material, boolean newArt) {
		super(auctioner, title, creator, artCreationYear, reservePrice, numBidsAllowed, newArt);
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.material = material;
		setArtType("Sculpture");
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
