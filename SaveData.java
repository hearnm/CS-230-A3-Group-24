import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * SaveData.java
 * This class obtains data currently on the system and saves it to a file.
 *  If the file does not exist, it will be created automatically.
 * @author Tom Durman
 */
public class SaveData {
	/** The Constant File path for the system data. */
	private static final String PROFILE_DATA_PATH = "ArtataweProfiles.txt";
	/** The Constant File path to save all the data to. */
	private static final String PROFILE_FAVORITE_PATH = "_FavoriteProfiles.txt";
	/** The Constant File path to the artworks data (including bidding info). */
	private static final String ARTWORK_FAVORITE_PATH = "Artworks.txt";

	/** The current user that is logged onto the system. */
	private static UserProfile currentUser;
	/** The current artwork. */
	private static Artwork currentArtwork;
	/** The print writer for writing to a file. */
	private static PrintWriter printWriter;

	/** The current auctions to be saved / updated. */
	private static ArrayList<Auction> currentAuctions = new ArrayList<>();
	/** The users to be saved / updated. */
	private static ArrayList<UserProfile> allUsers = new ArrayList<>();

	/**
	 * Static Method that saves the system according to a given Username.
	 * @param username The username of the current or any other saved in the system.
	 */
	public static void saveNewProfile(String username) {
		currentUser = UserProfile.getCurrentUserObject(username);
		openProfileFile(PROFILE_DATA_PATH, false);
		addProfileData(printWriter);
	}

	/**
	 * Static Method to update all profiles appropriately.
	 * @param allUsersUpdated An ArrayList of all users
	 */
	public static void updateProfiles(ArrayList<UserProfile> allUsersUpdated) {
		allUsers = allUsersUpdated;
		openProfileFile(PROFILE_DATA_PATH, true);
		updateProfileData(printWriter);
	}

	/**
	 * Static Method that saves the system according to a given Username.
	 * @param user The current user object that is logged onto the system.
	 */
	public static void saveProfileFavorites(UserProfile user) {
		currentUser = user;
		openProfileFile(currentUser.getUsername() + PROFILE_FAVORITE_PATH, true);
		addProfileFavoritesData(printWriter);
	}

	/**
	 * Static Method to save a newly created artwork to the current storage file.
	 * @param artwork The new artwork to be saved
	 */
	public static void saveNewArtwork(Artwork artwork) {
		currentArtwork = artwork;
		openProfileFile(ARTWORK_FAVORITE_PATH, false);
		addNewArtwork(printWriter);
	}

	/**
	 * Static Method to update an existing auction.
	 * @param allAuctions All of the current auctions on the system
	 * @param auction The auction to be updated
	 */
	public static void updateAuction(ArrayList<Auction> allAuctions, Auction auction) {
		currentArtwork = auction.getAuctionedArtwork();
		currentAuctions = allAuctions;
		openProfileFile(ARTWORK_FAVORITE_PATH, true);
		updateAuctions(printWriter);
	}

	/**
	 * Method to open a file path to store data locally.
	 * @param filePath the file path
	 * @param overwrite Whether the file needs to be overwritted (updated) or not (adding new objects)
	 * @return The file output stream opened by filename
	 */
	private static void openProfileFile(String filePath, boolean overwrite) {
		FileWriter fileWriter;
		try {
			File dataFile = new File(filePath);
			if(overwrite == true) {
				fileWriter = new FileWriter(dataFile, false);
				printWriter = new PrintWriter(fileWriter);
			} else {
				fileWriter = new FileWriter(dataFile, true);
				BufferedWriter buffer = new BufferedWriter(fileWriter);
				printWriter = new PrintWriter(buffer);
			}
		} catch (IOException e) {
			System.out.println("error occured with file");
		}
	}

	/**
	 * Method to save a new Profile to a locally stored file.
	 *
	 * @param outputStream The file which the data is being stored
	 */
	private static void addProfileData(PrintWriter outputStream) {
			int userId = currentUser.getUserId();
			String username = currentUser.getUsername();
			String firstname = currentUser.getFirstName();
			String lastname = currentUser.getLastName();
			String street = currentUser.getStreet();
			String postcode = currentUser.getPostcode();
			String cityTown = currentUser.getCityTown();
			Integer phoneNo = currentUser.getPhoneNumber();
			boolean newAccount = currentUser.getNewAccount();

		outputStream.println(userId + "," + username + "," + firstname + "," + lastname + "," + street 
					   		  + "," + postcode + "," + cityTown + "," + phoneNo + "," + newAccount + ",");
		closeFile(outputStream);
	}

	/**
	 * Static Method to update all existing user objects.
	 * @param outputStream The file which the data is being stored
	 */
	private static void updateProfileData(PrintWriter outputStream) {
		for (int i = 0; i < allUsers.size(); i++) {
			int userId = allUsers.get(i).getUserId();
			String username = allUsers.get(i).getUsername();
			String firstname = allUsers.get(i).getFirstName();
			String lastname = allUsers.get(i).getLastName();
			String street = allUsers.get(i).getStreet();
			String postcode = allUsers.get(i).getPostcode();
			String cityTown = allUsers.get(i).getCityTown();
			Integer phoneNo = allUsers.get(i).getPhoneNumber();
			boolean newAccount = allUsers.get(i).getNewAccount();

			outputStream.println(userId + "," + username + "," + firstname + "," + lastname + "," + street 
			   		  + "," + postcode + "," + cityTown + "," + phoneNo + "," + newAccount + ",\n");
		}
		closeFile(outputStream);
	}

	/**
	 * Method to save a new Profile to a locally stored file.
	 * @param outputStream The file which the data is being stored
	 */
	private static void addProfileFavoritesData(PrintWriter outputStream) {
		ArrayList<UserProfile> favoriteUsers = new ArrayList<>();
		favoriteUsers = currentUser.getFavoriteUsers();

		for (int i = 0; i < favoriteUsers.size(); i++) {
			outputStream.print(favoriteUsers.get(i).getUsername() + ",");
		}
		closeFile(outputStream);
	}

	/**
	 * Static Method of adding a new Artwork to the system.
	 * @param outputStream The file which the data is being stored
	 */
	public static void addNewArtwork(PrintWriter outputStream) {
		if (currentArtwork.getArtType().equalsIgnoreCase("Painting")) {
			// A new Painting artwork
			String auctioneer = currentArtwork.getAuctioneer();
			String artType = currentArtwork.getArtType();
			String title = currentArtwork.getTitle();
			String creator = currentArtwork.getCreator();
			int artCreationYear = currentArtwork.getArtCreationYear();
			double reservePrice = currentArtwork.getReservePrice();
			int numBidsAllowed = currentArtwork.getNumBidAllowed();
			double width = currentArtwork.getWidth();
			double height = currentArtwork.getHeight();
			boolean status = currentArtwork.getOnAuction();

			outputStream.println(auctioneer + "," + artType + "," + title + "," + creator + "," + artCreationYear + "," 
					+ reservePrice + "," + numBidsAllowed + "," + width + "," + height + "," + status + ",");
		} else {
			// A new Sculpture artwork
			String auctioneer = currentArtwork.getAuctioneer();
			String artType = currentArtwork.getArtType();
			String title = currentArtwork.getTitle();
			String creator = currentArtwork.getCreator();
			int artCreationYear = currentArtwork.getArtCreationYear();
			double reservePrice = currentArtwork.getReservePrice();
			int numBidsAllowed = currentArtwork.getNumBidAllowed();
			double width = currentArtwork.getWidth();
			double height = currentArtwork.getHeight();
			double depth = currentArtwork.getDepth();
			String material = currentArtwork.getMaterial();
			boolean status = currentArtwork.getOnAuction();

			outputStream.println(auctioneer + "," + artType + "," + title + "," + creator + "," + 
					artCreationYear + "," + reservePrice + "," + numBidsAllowed + "," + width +
					"," + height + "," + depth + "," + material + "," + status + ",");
		}
		closeFile(outputStream);
	}

	/**
	 * Static Method to update all existing auctions.
	 * (Used when updating a single auction mainly - prevents loss of data)
	 * @param outputStream The file which the data is being stored
	 */
	private static void updateAuctions(PrintWriter outputStream) {
		for (int i = 0; i < currentAuctions.size(); i++) {
			if (currentAuctions.get(i).getAuctionedArtwork().getArtType().equalsIgnoreCase("Painting")) {
				String auctioneer = currentAuctions.get(i).getAuctionedArtwork().getAuctioneer();
				String artType = currentAuctions.get(i).getAuctionedArtwork().getArtType();
				String title = currentAuctions.get(i).getAuctionedArtwork().getTitle();
				String creator = currentAuctions.get(i).getAuctionedArtwork().getCreator();
				int artCreationYear = currentAuctions.get(i).getAuctionedArtwork().getArtCreationYear();
				double reservePrice = currentAuctions.get(i).getAuctionedArtwork().getReservePrice();
				int numBidsAllowed = currentAuctions.get(i).getAuctionedArtwork().getNumBidAllowed();
				double width = currentAuctions.get(i).getAuctionedArtwork().getWidth();
				double height = currentAuctions.get(i).getAuctionedArtwork().getHeight();
				boolean status = currentAuctions.get(i).getAuctionedArtwork().getOnAuction();

				outputStream.print(auctioneer + "," + artType + "," + title + "," + creator + "," + artCreationYear + "," 
						+ reservePrice + "," + numBidsAllowed + "," + width + "," + height + "," + status + ",");

				for(int ii = 0; ii < currentAuctions.get(i).getBids().size(); ii++) {
					outputStream.print(currentAuctions.get(i).getBids().get(ii).getUsername() +
							"," + currentAuctions.get(i).getBids().get(ii).getBidAmount() + "," +
							currentAuctions.get(i).getBids().get(ii).getTimeOfBid() + ",");
				}
			} else {
				String auctioneer = currentAuctions.get(i).getAuctionedArtwork().getAuctioneer();
				String artType = currentAuctions.get(i).getAuctionedArtwork().getArtType();
				String title = currentAuctions.get(i).getAuctionedArtwork().getTitle();
				String creator = currentAuctions.get(i).getAuctionedArtwork().getCreator();
				int artCreationYear = currentAuctions.get(i).getAuctionedArtwork().getArtCreationYear();
				double reservePrice = currentAuctions.get(i).getAuctionedArtwork().getReservePrice();
				int numBidsAllowed = currentAuctions.get(i).getAuctionedArtwork().getNumBidAllowed();
				double width = currentAuctions.get(i).getAuctionedArtwork().getWidth();
				double height = currentAuctions.get(i).getAuctionedArtwork().getHeight();
				double depth = currentAuctions.get(i).getAuctionedArtwork().getDepth();
				String material = currentAuctions.get(i).getAuctionedArtwork().getMaterial();
				boolean status = currentAuctions.get(i).getAuctionedArtwork().getOnAuction();
				
				outputStream.print(auctioneer + "," + artType + "," + title + "," + creator + "," + artCreationYear + "," 
						+ reservePrice + "," + numBidsAllowed + "," + width + "," + height + "," + depth + "," + material +
						"," + status + ",");

				for(int ii = 0; ii < currentAuctions.get(i).getBids().size(); ii++) {
					outputStream.print(currentAuctions.get(i).getBids().get(ii).getUsername() + "," +
							currentAuctions.get(i).getBids().get(ii).getBidAmount() + "," +
							currentAuctions.get(i).getBids().get(ii).getTimeOfBid() + ",");
				}
			}
			outputStream.println("");
		}
		closeFile(outputStream);
	}

	/**
	 * Method to close the file path.
	 * @param stream The output stream
	 */
	private static void closeFile(PrintWriter stream) {
		stream.close();
	}
}
