import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * LoadData.java
 * @author Tom Durman
 * This class searches for a preset file and takes the information out of that file and puts it into the system
 */
public class LoadData {
	
	// *************************************
	// BugList
	// ***********
	// 1. If we have 10 users and delete user 5, every user with a higher userId will have their userId changed -1 when loaded in
	// ***************************************
	
	
	private static final String profileDataPath = "ArtataweProfiles.txt";	// The set filename path for the system data
	private static Scanner inputStream;						// The input stream connected to the given file
	
	/**
	 * Static Method that can be called to load the data from the file to the system.
	 */
	public static void loadSystemData() {
		openProfileFile();
	}
	
	/**
	 * Method to open the file if it exists
	 */
	private static void openProfileFile() {
		try {
			File x = new File(profileDataPath);
			inputStream = new Scanner(x);
			inputStream.useDelimiter(",");
			System.out.println(x.exists());
			readFile();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Method to read the File and take out data, constructing appropriate objects in the system.
	 */
	private static void readFile() {
		
		while(inputStream.hasNext()) {
			inputStream.next();
			String username = inputStream.next();
			String firstname = inputStream.next();
			String lastname = inputStream.next();
			String street = inputStream.next();
			String postcode = inputStream.next();
			String cityTown = inputStream.next();
			int phoneNo = inputStream.nextInt();
			inputStream.nextLine();
			
			UserProfile x = new UserProfile(username, firstname, lastname, street, postcode, cityTown, phoneNo, false);
			
			}
		
		closeFile();
		}
		
	/**
	 * Method to close the input stream to the file.
	 */
	private static void closeFile() {
		inputStream.close();
	}

}
