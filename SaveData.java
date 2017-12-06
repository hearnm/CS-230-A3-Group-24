import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * SaveData.java
 * @author Tom Durman
 *  This class obtains data currently on the system and saves it to a file.
 *  If the file does not exist, it will be created automatically.
 */
public class SaveData {

	// *************************************
	// BugList
	// ***********
	// 1. The profile can be created on the system but not saved to the datafile (If the phone number too long or contains anything but a int)
	// ***************************************
	
	private static final String profileDataPath = "ArtataweProfiles.txt";  // The File path to save all the data to.
	private static final String profileFavoritePath = "_FavoriteProfiles.txt";  // The File path to save all the data to.
	private static final String artworkFavoritePath = "Artworks.txt";
	private static UserProfile currentUser;					// The current user object that is logged onto the system
	private static Artwork currentArtwork;
	private static PrintWriter printWriter;
	
	private static Scanner inputStream;	
	
	private static ArrayList<UserProfile> allUsers = new ArrayList<>();
	
	private boolean newAccount;
	
	
	/**
	 * Static Method that will save the system according to a given Username
	 * @param username The username of the current or any other saved in the system.
	 */
	public static void saveNewProfile(String username) {
		currentUser = UserProfile.getCurrentUserObject(username);
		openProfileFile(profileDataPath, false);
		addProfileData(printWriter);
	}
	
	public static void updateProfiles(ArrayList<UserProfile> allUsersUpdated) {
		allUsers = allUsersUpdated;
		openProfileFile(profileDataPath, true);
		updateProfileData(printWriter);
	}
	
	/**
	 * Static Method that will save the system according to a given Username
	 * @param username The username of the current or any other saved in the system.
	 */
	public static void saveProfileFavorites(UserProfile user) {
		currentUser = user;
		openProfileFile(currentUser.getUsername() + profileFavoritePath, true);
		addProfileFavoritesData(printWriter);
	}
	
	
	public static void saveNewArtwork(Artwork artwork) {
		currentArtwork = artwork;
		openProfileFile(artworkFavoritePath, false);
		addArtwork(printWriter);
		
	}
	
	
	
	/**
	 * Method to open a file path to store data locally
	 * @param filename Absolute or relative path to a file
	 * @return The file output stream opened by filename
	 */
	private static void openProfileFile(String filePath, boolean overwrite){
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
	 * Method to save a new Profile to a locally stored file
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
	
	private static void updateProfileData(PrintWriter outputStream) {
		
		for(int i = 0; i < allUsers.size(); i++) {
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
	 * Method to save a new Profile to a locally stored file
	 * @param outputStream The file which the data is being stored
	 */
	private static void addProfileFavoritesData(PrintWriter outputStream) {
		ArrayList<UserProfile> favoriteUsers = new ArrayList<>();
		favoriteUsers = currentUser.getFavoriteUsers();

		
		for(int i = 0; i < favoriteUsers.size(); i++) {
			
			outputStream.print(favoriteUsers.get(i).getUsername() + ",");
		}
		closeFile(outputStream);
	}
	
	
	
	

	public static void addArtwork(PrintWriter outputStream) {
		
		
		if(currentArtwork.getArtType().equalsIgnoreCase("Painting")) {
			String auctioner = currentArtwork.getAuctioner();
			String artType = currentArtwork.getArtType();
			String title = currentArtwork.getTitle();
			String creator = currentArtwork.getCreator();
			int artCreationYear = currentArtwork.getArtCreationYear();
			double reservePrice = currentArtwork.getReservePrice();
			int numBidsAllowed = currentArtwork.getNumBidAllowed();
			double width = currentArtwork.getWidth();
			double height = currentArtwork.getHeight();
			boolean status = currentArtwork.getOnAuction();
			
			outputStream.println(auctioner + "," + artType + "," + title + "," + creator + "," + artCreationYear + "," 
					+ reservePrice + "," + numBidsAllowed + "," + width + "," + height + "," + status + ",");
		} else {
			
			String auctioner = currentArtwork.getAuctioner();
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
			
			outputStream.println(auctioner + "," + artType + "," + title + "," + creator + "," + artCreationYear + "," 
					+ reservePrice + "," + numBidsAllowed + "," + width + "," + height + "," + depth + "," + material + "," + status + ",");
			
			
		}
		

		closeFile(outputStream);
		
	}
	
	
	
	
	
	
	/**
	 * Method to close the file path
	 * @param x the output stream
	 */
	private static void closeFile(PrintWriter x) {
		x.close();
	}

}
