import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SaveData {

	private static final String filename = "artatawe.txt";
	private PrintWriter x;
	private static UserProfile currentUser;

	
	public static String saveSystemData(String username) {
		currentUser = UserProfile.getCurrentUserObject(username);
		openFile();
		return "mep";
	}
	
	public static String saveSystemData() {
		currentUser = UserProfile.getCurrentUserObject(UserProfile.getCurrentUserId());
		openFile();
		return "mep";
		
	}
		
	/**
	 * 
	 * @param filename Absolute or relative path to a file
	 * @return The file path opened by filename
	 */
	private static void openFile(){
		
		try {
			File dataFile = new File(filename);
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
		
		outputStream.println(userId + "," + username + "," + firstname + "," + lastname + "," + street 
					   		  + "," + postcode + "," + cityTown + "," + phoneNo + ",");
		
		closeFile(outputStream);
	}
	
	
	
	
	
}
