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
	private static final String profileFavoritePath = "ArtataweFavoriteProfiles.txt";  // The File path to save all the data to.
	private static UserProfile currentUser;					// The current user object that is logged onto the system
	private static PrintWriter printWriter;
	private static Scanner inputStream;	
	
	private static int userId = -1;
	private static String username = "";
	private static String firstname = "";
	private static String lastname = "";
	private static String street = "";
	private static String postcode = "";
	private static String cityTown = "";
	private static Integer phoneNo = 0;
	private boolean newAccount;
	
	
	/**
	 * Static Method that will save the system according to a given Username
	 * @param username The username of the current or any other saved in the system.
	 */
	public static void saveNewProfile(String username) {
		currentUser = UserProfile.getCurrentUserObject(username);
		openProfileFile(profileDataPath);
		addProfileData(printWriter);
	}
	
	/**
	 * Static Method that will save the system according to a given Username
	 * @param username The username of the current or any other saved in the system.
	 */
	public static void saveProfileFavorites(UserProfile user) {
		currentUser = user;
		openProfileFile(profileDataPath);
		addProfileFavoritesData(printWriter);
	}
	
	/**
	 * Method to open a file path to store data locally
	 * @param filename Absolute or relative path to a file
	 * @return The file output stream opened by filename
	 */
	private static void openProfileFile(String filePath){
		
		try {
			File dataFile = new File(filePath);
			FileWriter fileWriter = new FileWriter(dataFile, false);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffer);

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
		
		
			username = currentUser.getUsername();
			firstname = currentUser.getFirstName();
			lastname = currentUser.getLastName();
			street = currentUser.getStreet();
			postcode = currentUser.getPostcode();
			cityTown = currentUser.getCityTown();
			phoneNo = currentUser.getPhoneNumber();
			boolean newAccount = currentUser.getNewAccount();
		

		outputStream.println(userId + "," + username + "," + firstname + "," + lastname + "," + street 
					   		  + "," + postcode + "," + cityTown + "," + phoneNo + "," + newAccount + ",");
		
		closeFile(outputStream);
	}
	
	/**
	 * Method to save a new Profile to a locally stored file
	 * @param outputStream The file which the data is being stored
	 */
	private static void addProfileFavoritesData(PrintWriter outputStream) {
		ArrayList<UserProfile> favoriteUsers = new ArrayList<>();
		favoriteUsers = currentUser.getFavoriteUsers();
		
		outputStream.println(currentUser.getUserId() + ",");
		
		for(int i = 0; i < favoriteUsers.size(); i++) {
			outputStream.print(favoriteUsers.get(i).getUsername());
		}
		outputStream.print("\n");
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
