import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	private static UserProfile currentUser;					// The current user object that is logged onto the system

	
	/**
	 * Static Method that will save the system according to a given Username
	 * @param username The username of the current or any other saved in the system.
	 */
	public static void saveSystemData(String username) {
		currentUser = UserProfile.getCurrentUserObject(username);
		openProfileFile();
	}
	
	public static void saveProfileImage() {
		
	}
		
	/**
	 * 
	 * @param filename Absolute or relative path to a file
	 * @return The file path opened by filename
	 */
	private static void openProfileFile(){
		
		try {
			File dataFile = new File(profileDataPath);
			FileWriter fileWriter = new FileWriter(dataFile, true);
			BufferedWriter buffer = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(buffer);
			
			addData(printWriter);
			
			} catch (IOException e) {
				System.out.println("error occured with file");
			}
		
	}
	
	/**
	 * Method to close the file path
	 * @param x the output stream
	 */
	private static void closeFile(PrintWriter x) {
		x.close();
	}
	
	
	private static void addData(PrintWriter outputStream) {
		
		int userId = currentUser.getUserId();
		String username = currentUser.getUsername();
		String firstname = currentUser.getFirstName();
		String lastname = currentUser.getLastName();
		String street = currentUser.getStreet();
		String postcode = currentUser.getPostcode();
		String cityTown = currentUser.getCityTown();
		int phoneNo = currentUser.getPhoneNumber();
		boolean newAccount = currentUser.getNewAccount();
		
		outputStream.println(userId + "," + username + "," + firstname + "," + lastname + "," + street 
					   		  + "," + postcode + "," + cityTown + "," + phoneNo + "," + newAccount + ",");
		
		closeFile(outputStream);
	}

}
