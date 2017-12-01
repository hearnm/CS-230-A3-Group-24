import java.util.*;
/**
 * UserProfile.java
 * @author Tom Durman
 * Class to create and store User Profiles.
 */

public class UserProfile {

	private static int userCount = 1;
	
	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String street;
	private String postcode;
	private String cityTown;
	private int phoneNumber;
	
	private static ArrayList<UserProfile> profiles = new ArrayList<UserProfile>();
	private ArrayList<UserProfile> favoriteUsers = new ArrayList<UserProfile>();
	
	
	/**
	 * Constructor for UserProfile which creates new Users.
	 * @param username The username of the User.
	 * @param firstName The first name of the User.
	 * @param lastName The last name of the User.
	 * @param street The Users current street address.
	 * @param cityTown The Users city or town.
	 * @param postcode The Users current postcode.
	 * @param phoneNumber The Users current phone number.
	 */
	public UserProfile(String username, String firstName, String lastName, String street,
			 		   String postcode, String cityTown, int phoneNumber) {
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postcode = postcode;
		this.cityTown = cityTown;
		this.phoneNumber = phoneNumber;
		this.userId = userCount++;
		profiles.add(this);
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
		return username;
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
	 * @return The User object if found, else null.
	 */
	public static UserProfile searchForUser(String username) {
		
		for(int i = 0; i < profiles.size(); i++) {
			if(username.equalsIgnoreCase(profiles.get(i).getUsername())) {
				return profiles.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Method to add Users to Favorite List
	 * @param Username The username of the user to be added to favorites
	 */
	public void addFavoriteUser(String Username) {
		this.favoriteUsers.add(searchForUser(username));
	}
	
	
	
}