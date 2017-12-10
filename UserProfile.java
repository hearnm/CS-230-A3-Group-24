import java.util.*;

/**
 * UserProfile.java
 * Class to create and store User Profiles.
 * @author Tom Durman
 */
public class UserProfile {
	private static int userCount = 1;		// Static attribute to ensure unique user Id creation
	@SuppressWarnings("unused")
	private static int currentUserId = -1;	// The current userId that is logged into the system

	private int userId;			// The unique User identifier
	private String username;	// The unique Username associated with the profile
	private String firstName;	// The First name of the user
	private String lastName;	// The Last name of the user
	private String street;		// The Street the user lives on
	private String postcode;	// The Postcode of the users address
	private String cityTown;	// The City or Town the User lives at
	private String phoneNumber;	// A Valid UK Phone number
	private String profileImg = "DefaultPicture.png";	//The profile image
	private boolean newAccount;	// A check to see if an account is new or pre-existing
	private static ArrayList<UserProfile> profiles = new ArrayList<UserProfile>();  // An ArrayList of all profiles in the system
	private ArrayList<UserProfile> favoriteUsers = new ArrayList<UserProfile>();	// An Array of users marked as favorite by one instance of a user

	/**
	 * Constructor for UserProfile which creates new Users.
	 * When a User Profile is created a check is made to see if
	 * they are a pre-existing user or new and saves to the system
	 * @param username The username of the User.
	 * @param firstName The first name of the User.
	 * @param lastName The last name of the User.
	 * @param street The Users current street address.
	 * @param postcode The Users current postcode.
	 * @param cityTown The Users city or town.
	 * @param phoneNo the phone no
	 * @param newAccount A check to see if an account is new or pre-existing.
	 */
	public UserProfile(String username, String firstName, String lastName, String street,
			 		   String postcode, String cityTown, String phoneNo, boolean newAccount) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postcode = postcode;
		this.cityTown = cityTown;
		this.phoneNumber = phoneNo;
		this.userId = userCount++;
		this.newAccount = newAccount;

		if(this.newAccount == true) {
			profiles.add(this);
			saveProfile();
			this.newAccount = false;
		} else {
			profiles.add(this);
		}
	}

	/**
	 * Method to get the newAccount Attribute.
	 * @return newAccount True if new account, false if pre-existing.
	 */
	public boolean getNewAccount() {
		return this.newAccount;
	}

	/**
	 * Method to Save the current Users data.
	 */
	public void saveProfile() {
		SaveData.saveNewProfile(this.username);
	}

	/**
	 * Sets the profile image.
	 * @param imagePath the new profile image
	 */
	public void setProfileImage(String imagePath) {
		this.profileImg = imagePath;
	}

	/**
	 * Gets the profile image.
	 * @return the profile image
	 */
	public String getProfileImage() {
		return this.profileImg;
	}
	/**
	 * Method to get the userId.
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Method to set a new userId.
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Method to get the username.
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Method to set a new username.
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Method to get the first name.
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to set a new first name.
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method to get the last name.
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to set a new last name.
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method to get the street.
	 * @return The street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Method to set a new street address.
	 * @param street The street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Method to get the postcode.
	 * @return The postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Method to set a new postcode.
	 * @param postcode The postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Method to get the city / town.
	 * @return The cityTown
	 */
	public String getCityTown() {
		return cityTown;
	}

	/**
	 * Method to set a new city / town.
	 * @param cityTown The cityTown to set
	 */
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}

	/**
	 * Method to get the phone number.
	 * @return The phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Method to set a new phone number.
	 * @param phoneNumber The phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the profiles.
	 * @return The profiles
	 */
	public static ArrayList<UserProfile> getProfiles() {
		return profiles;
	}

	/**
	 * Gets the current user object.
	 * @param username The username
	 * @return The current user object
	 */
	public static UserProfile getCurrentUserObject(String username) {
		for (int i = 0; i < profiles.size(); i++) {
			if (username.equalsIgnoreCase(profiles.get(i).getUsername())) {
				System.out.println("Found: " + profiles.get(i).getUsername());
				return profiles.get(i);
			}
		}
		System.out.println("Not found - null");
		return null;
	}

	/**
	 * Gets the current user object.
	 * @param id The id
	 * @return The current user object
	 */
	public static UserProfile getCurrentUserObject(int id) {
		for (int i = 0; i < profiles.size(); i++) {
			if (id == profiles.get(i).getUserId()) {
				System.out.println(profiles.get(i).getUsername());
				return profiles.get(i);
			}
		}
		System.out.println("Not found - null");
		return null;
	}

	/**
	 * Removes the favorite user.
	 *
	 * @param user the user
	 */
	public void removeFavoriteUser(UserProfile user) {
		for(int i = 0; i < favoriteUsers.size(); i++) {
			if(user.getUsername().equalsIgnoreCase(favoriteUsers.get(i).getUsername())) {
				favoriteUsers.remove(i);
			}
		}
		
	}
	
	/**
	 * Adds the favorite user.
	 *
	 * @param user the user
	 */
	public void addFavoriteUser(UserProfile user) {
		favoriteUsers.add(user);
	}
	
	/**
	 * Adds the favorite multiple users.
	 *
	 * @param favorites the favorites
	 */
	public void addFavoriteMultipleUsers(ArrayList<UserProfile> favorites) {
		
		for(int i = 0; i < favorites.size(); i++) {
			
		favoriteUsers.add(favorites.get(i));
		}
	}
	
	/**
	 * Search favorite.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean searchFavorite(UserProfile user) {
		
		for(int i = 0; i < favoriteUsers.size(); i++) {
			if(favoriteUsers.get(i).getUsername().equalsIgnoreCase(user.getUsername())) {
				
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Search favorite.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean searchFavorite(String username) {
		
		for(int i = 0; i < favoriteUsers.size(); i++) {
			if(favoriteUsers.get(i).getUsername().equalsIgnoreCase(username)) {
				
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to get all of the Favorite users for the given instance.
	 * @return favoriteUsers An ArrayList of Users that are marked as favorite by this instance of the User.
	 */
	public ArrayList<UserProfile> getFavoriteUsers() {
		return this.favoriteUsers;
	}
}