import java.util.ArrayList;

/**
 * @author nikolina
 *
 */
public class CompletedAuctions {
	private ArrayList<Artwork> listOfArtworks;
	private String artworkName;
	private double winningBid;
	private String winningBidder;
	
	public CompletedAuctions(){
		
	}

	/**
	 * @return
	 */
	public ArrayList<Artwork> getListOfArtworks() {
		return listOfArtworks;
	}

	/**
	 * @param listOfArtworks
	 */
	public void setListOfArtworks(ArrayList<Artwork> listOfArtworks) {
		this.listOfArtworks = listOfArtworks;
	}

	/**
	 * @return
	 */
	public String getArtworkName() {
		return artworkName;
	}

	/**
	 * @param artworkName
	 */
	public void setArtworkName(String artworkName) {
		this.artworkName = artworkName;
	}

	/**
	 * @return
	 */
	public double getWinningBid() {
		return winningBid;
	}

	/**
	 * @param winningBid
	 */
	public void setWinningBid(double winningBid) {
		this.winningBid = winningBid; 
	}

	/**
	 * @return
	 */
	public String getWinningBidder() {
		return winningBidder;
	}

	/**
	 * @param winningBidder
	 */
	public void setWinningBidder(String winningBidder) {
		this.winningBidder = winningBidder;
	}
	

}
