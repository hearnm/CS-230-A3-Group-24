import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * LoadData.java
 * This class searches for an existing file and takes the information
 * out of that file and puts it into the system.
 * @author Tom Durman
 */
public class LoadData {
	private static final String PROFILE_DATA_PATH = "ArtataweProfiles.txt";			//The Constant File path for the system data.
	private static final String PROFILE_FAVORITE_PATH = "_FavoriteProfiles.txt";	//The Constant File path to save all the data to.
	private static final String ARTWORK_FAVORITE_PATH = "Artworks.txt";				//The Constant File path to the Artwork data (including bidding info).

	/** The input stream connected to the given file. */
	private static Scanner inputStream;

	/**
	 * Static Method that can be called to load the data from the
	 * file to the system.
	 */
	public static void loadSystemData() {
		openProfileFile(PROFILE_DATA_PATH);
		readProfileFile();
		readArtworksFile();
	}

	/**
	 * Static Method that can be called to get an arraylist
	 * of all user favorites to be loaded into the system.
	 * @param user The current user object
	 * @return ArrayList of use objects.
	 */
	public static ArrayList<UserProfile> loadUserFavorites(UserProfile user) {
		try {
			openProfileFile(user.getUsername() + PROFILE_FAVORITE_PATH);
		} catch (Exception e) {
				return null;
		}
		return readUserfavoritesFile();
	}

	/**
	 * Method to open a file if it exists.
	 * @param filePath The path to the file wanted to be opened.
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
	 * Static Method to read the File and take out data,
	 * constructing appropriate objects in the system.
	 */
	private static void readProfileFile() {
		while (inputStream.hasNext()) {
			inputStream.next();
			String username = inputStream.next();
			String firstname = inputStream.next();
			String lastname = inputStream.next();
			String street = inputStream.next();
			String postcode = inputStream.next();
			String cityTown = inputStream.next();
			String phoneNo = inputStream.next();
			inputStream.nextLine();

			@SuppressWarnings("unused")
			UserProfile loadedUser = new UserProfile(username, firstname,
					lastname, street, postcode, cityTown, phoneNo, false);
		}
	}

	/**
	 * Static Method which reads the current users favorite users
	 * and returns an ArrayList of user objects to be loaded in.
	 * @return the array list
	 */
	private static ArrayList<UserProfile> readUserfavoritesFile() {
		ArrayList<UserProfile> favoriteUsers = new ArrayList<>();
		try {
			while (inputStream.hasNext()) {
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
	 * Method to read the File and take out data, constructing appropriate artwork objects in the system.
	 */
	private static void readArtworksFile() {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(ARTWORK_FAVORITE_PATH));
		} catch (FileNotFoundException e) {
			System.out.println(ARTWORK_FAVORITE_PATH + " File not found!");
		}
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");

			while (lineScanner.hasNext()) {
				String auctioner = lineScanner.next();
				String artType = lineScanner.next();

				// Checking artwork type (Painting / Sculpture)
				if(artType.equalsIgnoreCase("Painting")) {
					String title = lineScanner.next();
					String creator = lineScanner.next();
					int artCreationYear = lineScanner.nextInt();
					double reservePrice = lineScanner.nextDouble();
					int numBidsAllowed = lineScanner.nextInt();
					double width = lineScanner.nextDouble();
					double height = lineScanner.nextDouble();
					boolean status = lineScanner.nextBoolean();

					// Construct existing Painting object
					@SuppressWarnings("unused")
					Artwork loadedPainting = new Painting(auctioner, title, creator,
							artCreationYear, reservePrice, numBidsAllowed, width,
							height, false, status);

					// Inner loop to add bidding data to an auction if it exists.
					while(lineScanner.hasNext()) {
						Auction.getGivenAuction(title).addExistingBids(lineScanner.next(),
								lineScanner.next(), lineScanner.next());
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

					// Construct existing Sculpture object
					@SuppressWarnings("unused")
					Artwork loadedPainting = new Sculpture(auctioner, title, creator,
							artCreationYear, reservePrice, numBidsAllowed, width,
							height, depth, material, false, status);
				
					// Inner loop to add bidding data to an auction if it exists.
					while(lineScanner.hasNext()) {
						Auction.getGivenAuction(title).addExistingBids(lineScanner.next(),
								lineScanner.next(), lineScanner.next());
					}
				}
			}
			closeFile(lineScanner);
		}
	}

	/**
	 * Method to close a given file stream.
	 * @param stream The Stream to be closed
	 */
	private static void closeFile(Scanner stream) {
		stream.close();
	}
}
