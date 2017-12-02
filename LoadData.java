import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadData {
	
	private static final String filename = "artatawe.txt";
	private static Scanner inputStream;
	
	
	public static void loadSystemData() {
		openFile();
	}
	
	
	private static void openFile() {
		try {
			File x = new File(filename);
			inputStream = new Scanner(x);
			inputStream.useDelimiter(",");
			System.out.println(x.exists());
			readFile();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	private static void readFile() {
		
		while(inputStream.hasNext()) {
			inputStream.next();
			String username = inputStream.next();
			String firstname = inputStream.next();
			String lastname = inputStream.next();
			String street = inputStream.next();
			String postcode = inputStream.next();
			String cityTown = inputStream.next();
			int phoneNo = inputStream.nextInt();
			inputStream.nextLine();
			
			UserProfile x = new UserProfile(username, firstname, lastname, street, postcode, cityTown, phoneNo, false);
			
			}
		
		closeFile();
		}
		
	private static void closeFile() {
		inputStream.close();
	}

}
