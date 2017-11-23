import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SessionData.java
 * @author Tom Durman
 * This class retrieves appropriate data and stores it in a Text file. Later calling that data from the same file.
 */
public class SessionData {

	private ArrayList<UserProfile> profiles = new ArrayList<UserProfile>();
	
	// Additional constructor to save data
	
	/**
	 * Constructor that will load data from a text file.
	 */
	public SessionData() {
		
		
	}
	
	/**
	 * 
	 * @param filename Absolute or relative path to a file
	 * @return The file path opened by filename
	 */
	private static File openFile(String filename){
		File inputFile = new File(filename);
		
		return inputFile;
	}
	
	/**
	 * Method to close the file path
	 * @param in the scanner of the file
	 */
	private static void closeFile(Scanner in) {
		in.close();
	}
	
}
