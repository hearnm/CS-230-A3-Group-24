/**
 * Unfinished - need to finish and add notes.
 * @author Matthew
 *
 */

public class Auction {
	private static int nextId = 0;
	private final int AUCTION_ID;
	Artwork auctionedArtwork;
	double currentBid;
	String currentBidder;
	int remainingBids;
	
	public Auction(Artwork auctionedArtwork, int maxBids) {
		this.AUCTION_ID = this.nextId;
		nextId++;
		this.auctionedArtwork = auctionedArtwork;
		this.currentBid = 0.0;
		this.currentBidder = "";
		this.remainingBids = maxBids;
	}
	
	public void setAuctionedArtwork(Artwork auctionedArtwork) {
		this.auctionedArtwork = auctionedArtwork;
	}
	
	public Artwork getAuctionedArtwork() {
		return this.auctionedArtwork;
	}
	
	public void setCurrentBidder(String currentBidder) {
		this.currentBidder = currentBidder;
	}
	
	public void setCurrentBid(double newBid) {
		if(checkIfBidValid(newBid)) {
			this.currentBid = newBid;
		}
	}
	
	public double getCurrentBid() {
		return this.currentBid;
	}
	
	public void displayAcceptedBid() {
		
	}
	
	public void notifyWinner() {
		//do something
	}
	
	public boolean checkIfBidValid(double speculativeBid) {
		return speculativeBid > this.currentBid;
	}
	
}