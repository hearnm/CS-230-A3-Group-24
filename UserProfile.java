import java.util.*;

/**
 * UserProfile.java
 * @author Tom Durman
 * Class to create and store User Profiles.
 */
public class UserProfile {

	private static int userCount = 1;		// Static attribute to ensure unique user Id creation
	private static int currentUserId = -1;	// The current userId that is logged into the system
	
	private int userId;			// The unique User identifier
	private String username;	// The unique Username associated with the profile
	private String firstName;	// The First name of the user
	private String lastName;	// The Last name of the user
	private String street;		// The Street the user lives on
	private String postcode;	// The Postcode of the users address
	private String cityTown;	// The City or Town the User lives at
	private Integer phoneNumber;	// A Valid UK Phone number (NOTE: this needs to be an Integer)
	private boolean newAccount;	// A check to see if an account is new or pre-existing
	
	private static ArrayList<UserProfile> profiles = new ArrayList<UserProfile>();  // An ArrayList of all profiles in the system
	private ArrayList<UserProfile> favoriteUsers = new ArrayList<UserProfile>();	// An Array of users marked as favorite by one instance of a user
	
	
	/**
	 * Constructor for UserProfile which creates new Users.
	 * When a User Profile is created a check is made to see if they are a pre-existing user or new and saves to the system
	 * @param username The username of the User.
	 * @param firstName The first name of the User.
	 * @param lastName The last name of the User.
	 * @param street The Users current street address.
	 * @param cityTown The Users city or town.
	 * @param postcode The Users current postcode.
	 * @param phoneNumber The Users current phone number.
	 */
	public UserProfile(String username, String firstName, String lastName, String street,
			 		   String postcode, String cityTown, Integer phoneNumber, boolean newAccount) {
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postcode = postcode;
		this.cityTown = cityTown;
		this.phoneNumber = phoneNumber;
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
	 * Method to get the newAccount Attribute
	 * @return newAccount True if new account, false if pre-existing.
	 */
	public boolean getNewAccount() {
		return this.newAccount;
	}
	
	/**
	 * Method to Save the current Users data
	 */
	public void saveProfile() {
		SaveData.saveSystemData(this.username);
	}
	
	/**
	 * Static Method to set a current user id (logged on)
	 * @param newCurrentUserId The current users' ID
	 */
	public static void setCurrentUserID(int newCurrentUserId) {
		currentUserId = newCurrentUserId;
	}
	
	/**
	 * Static Method to get the current users' id
	 * @return currentUserId The logged on users Id.
	 */
	public static int getCurrentUserId() {
		return currentUserId;
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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Method to set a new street address.
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Method to get the postcode.
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Method to set a new postcode.
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Method to get the city / town.
	 * @return the cityTown
	 */
	public String getCityTown() {
		return cityTown;
	}

	/**
	 * Method to set a new city / town.
	 * @param cityTown the cityTown to set
	 */
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}

	/**
	 * Method to get the phone number.
	 * @return the phoneNumber
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Method to set a new phone number.
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/** 
	 * Method to search for an existing profile by Username
	 * @param username The username of the profile to be searched for
	 * @return The username of the user.
	 */
	public static String searchForUser(String username) {
		
		for(int i = 0; i < profiles.size(); i++) {
			if(username.equalsIgnoreCase(profiles.get(i).getUsername())) {
				return profiles.get(i).getUsername();
			}
		}
		return null;
	}
	
	/** 
	 * Method to search for an existing profile by Username
	 * @param username The username of the profile to be searched for
	 * @return The Users UserID
	 */
	public static int getCurrentUserId(String username) {
		
		for(int i = 0; i < profiles.size(); i++) {
			if(username.equalsIgnoreCase(profiles.get(i).getUsername())) {
				return profiles.get(i).getUserId();
			}
		}
		return -1;
	}
	
	/** 
	 * Method to search for an existing profile by Username
	 * @param username The username of the profile to be searched for
	 * @return The Users UserID
	 */
	public static UserProfile getCurrentUserObject(int currentUserId) {
		
		for(int i = 0; i < profiles.size(); i++) {
			if(currentUserId == profiles.get(i).getUserId()) {
				return profiles.get(i);
			}
		}
		return null;
	}
	
	public static UserProfile getCurrentUserObject(String username) {
		
		for(int i = 0; i < profiles.size(); i++) {
			if(username == profiles.get(i).getUsername()) {
				return profiles.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Method to add Users to Favorite List
	 * @param Username The username of the user to be added to favorites
	 */
	/*
	public void addFavoriteUser(String Username) {
		this.favoriteUsers.add(searchForUser(username));
	}
	*/
	
	/**
	 * Method to get all of the Favorite users for the given instance.
	 * @return favoriteUsers An ArrayList of Users that are marked as favorite by this instance of the User.
	 */
	public ArrayList<UserProfile> getFavoriteUsers() {
		return this.favoriteUsers;
	}
	
	
}