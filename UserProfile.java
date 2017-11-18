
/**
 * UserProfile.java
 * @author Tom Durman
 *
 */

public class UserProfile {

	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String street;
	private String cityTown;
	private String postcode;
	private Integer phoneNumber;
	
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
					   String cityTown, String postcode, Integer phoneNumber) {
		
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.cityTown = cityTown;
		this.postcode = postcode;
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Method to set a new Username.
	 * @param newUsername The new Username.
	 */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	/**
	 * Method to get Username.
	 * @return The current Username.
	 */
	public String getUsername() {
		return this.username;
	}
	
}