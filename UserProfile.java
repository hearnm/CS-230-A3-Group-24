
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
	
	
}

