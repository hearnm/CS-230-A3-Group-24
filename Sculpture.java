/**
 * 
 * @author Emily Fothergill
 */

import java.util.ArrayList;

public class Sculpture extends Dimensions {
	protected ArrayList <String> additionalImages = new ArrayList <String> ();
	protected double depth;
	protected String material;
	
	public Sculpture(double height, double width, double depth, String material) {
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.material = material;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	public double getDepth() {
		return depth;
	}
	
	public void setAdditionalImages(ArrayList<String> additionalImages) {
		
	}
	
	public ArrayList<String> getAdditionalImages() {
		return additionalImages;
	}
}
