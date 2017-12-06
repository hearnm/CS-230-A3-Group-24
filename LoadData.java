import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * LoadData.java
 * @author Tom Durman
 * This class searches for a preset file and takes the information out of that file and puts it into the system
 */
public class LoadData {
	

	
	
	private static final String profileDataPath = "ArtataweProfiles.txt";	// The set filename path for the system data
	private static final String profileFavoritePath = "_FavoriteProfiles.txt";  // The File path to save all the data to.
	private static final String artworkFavoritePath = "Artworks.txt";
	private static Scanner inputStream;						// The input stream connected to the given file
	private static UserProfile currentUser;
	
	
	/**
	 * Static Method that can be called to load the data from the file to the system.
	 */
	public static void loadSystemData() {
		openProfileFile(profileDataPath);
		readProfileFile();
		openProfileFile(artworkFavoritePath);
		readArtworksFile();

	}
	
	public static ArrayList<UserProfile> loadUserFavorites(UserProfile user) {
		currentUser = user;
		try {
		openProfileFile(user.getUsername() + profileFavoritePath);
		} catch (Exception e) {

			return null;
		}
		
		return readUserfavoritesFile();
		
	}
	
	/**
	 * Method to open the file if it exists
	 */
	private static void openProfileFile(String filePath) {
		try {
			File x = new File(filePath);
			inputStream = new Scanner(x);
			inputStream.useDelimiter(",");
			System.out.println(x.exists());
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Method to read the File and take out data, constructing appropriate objects in the system.
	 */
	private static void readProfileFile() {
		
		while(inputStream.hasNext()) {
			inputStream.next();
			String username = inputStream.next();
			String firstname = inputStream.next();
			String lastname = inputStream.next();
			String street = inputStream.next();
			String postcode = inputStream.next();
			String cityTown = inputStream.next();
			Integer phoneNo = inputStream.nextInt();
			inputStream.nextLine();
			
			UserProfile x = new UserProfile(username, firstname, lastname, street, postcode, cityTown, phoneNo, false);
			
			}
		}
		
	private static ArrayList<UserProfile> readUserfavoritesFile() {
		
		ArrayList<UserProfile> favoriteUsers = new ArrayList<>();

		try {
		while(inputStream.hasNext()) {
			
			String username = inputStream.next();
			favoriteUsers.add(UserProfile.getCurrentUserObject(username));
			}
		
		
		return favoriteUsers;
		} catch (NoSuchElementException e) {
			System.out.println("No favorites in file");
		}
		
		return favoriteUsers;
		}
	
	
	/**
	 * Method to read the File and take out data, constructing appropriate objects in the system.
	 */
	private static void readArtworksFile() {
		
		while(inputStream.hasNext()) {
			
			String auctioner = inputStream.next();
			String artType = inputStream.next();
			
			if(artType.equalsIgnoreCase("Painting")) {
				
				String title = inputStream.next();
				String creator = inputStream.next();
				int artCreationYear = inputStream.nextInt();
				double reservePrice = inputStream.nextDouble();
				int numBidsAllowed = inputStream.nextInt();
				double width = inputStream.nextDouble();
				double height = inputStream.nextDouble();

				Artwork loadedPainting = new Painting(auctioner, title, creator, artCreationYear, reservePrice, numBidsAllowed, width, height, false);
			} else {
				
				String title = inputStream.next();
				String creator = inputStream.next();
				int artCreationYear = inputStream.nextInt();
				double reservePrice = inputStream.nextDouble();
				int numBidsAllowed = inputStream.nextInt();
				double width = inputStream.nextDouble();
				double height = inputStream.nextDouble();
				double depth = inputStream.nextDouble();
				String material = inputStream.next();

				Artwork loadedPainting = new Sculpture(auctioner, title, creator, artCreationYear, reservePrice, numBidsAllowed, width, height, depth, material, false);
			}
			}
		}
	
	
	
	
	
	
	
	
	/**
	 * Method to close the input stream to the file.
	 */
	private static void closeFile() {
		inputStream.close();
	}

}
