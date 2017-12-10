import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

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
		//openProfileFile(artworkFavoritePath);
		readArtworksFile();

	}
	
	public static void loadFavorites(UserProfile current) {
		currentUser = current;
		openProfileFile(currentUser.getUsername() + profileFavoritePath);
		
		ArrayList<UserProfile> currentFavorites = new ArrayList<>();
		currentFavorites = readUserfavoritesFile();
		currentUser.addFavoriteMultipleUsers(currentFavorites);
		
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
		
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(artworkFavoritePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (fileScanner.hasNextLine()) {
		  String line = fileScanner.nextLine();

		  Scanner lineScanner = new Scanner(line);
		  lineScanner.useDelimiter(",");
		  while (lineScanner.hasNext()) {


	        String auctioner = lineScanner.next();
	        System.out.println(auctioner);
			String artType = lineScanner.next();
			System.out.println(artType);
			if(artType.equalsIgnoreCase("Painting")) {
						
						// Artwork data
						String title = lineScanner.next();
						String creator = lineScanner.next();
						int artCreationYear = lineScanner.nextInt();
						double reservePrice = lineScanner.nextDouble();
						int numBidsAllowed = lineScanner.nextInt();
						double width = lineScanner.nextDouble();
						double height = lineScanner.nextDouble();
						boolean status = lineScanner.nextBoolean();
						// Auction data


						Artwork loadedPainting = new Painting(auctioner, title, creator, artCreationYear, reservePrice, numBidsAllowed, width, height, false, status);
					
						while(lineScanner.hasNext()) {
							Auction.getGivenAuction(title).addExistingBids(lineScanner.next(), lineScanner.next(), lineScanner.next());
						}
						
				
						
					} else {
						
						String title = lineScanner.next();
						String creator = lineScanner.next();
						int artCreationYear = lineScanner.nextInt();
						double reservePrice = lineScanner.nextDouble();
						int numBidsAllowed = lineScanner.nextInt();
						double width = lineScanner.nextDouble();
						double height = lineScanner.nextDouble();
						double depth = lineScanner.nextDouble();
						String material = lineScanner.next();
						boolean status = lineScanner.nextBoolean();
						

						Artwork loadedPainting = new Sculpture(auctioner, title, creator, artCreationYear, reservePrice, numBidsAllowed, width, height, depth, material, false, status);
						
						while(lineScanner.hasNext()) {
							
							String x = lineScanner.next();
							String y = lineScanner.next();
							String z = lineScanner.next();
							
							Auction.getGivenAuction(title).addExistingBids(x,y,z);
							
							System.out.println("Loaded Bidding: " + x + " " + y + " " + z);
							
						}
					}
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
