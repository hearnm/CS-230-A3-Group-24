import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class SaveData {


	private PrintWriter x;

	
	public SaveData() {
	
		x = openFile("ArtataweData.txt");
		addData();
		closeFile(x);
	}
		
	/**
	 * 
	 * @param filename Absolute or relative path to a file
	 * @return The file path opened by filename
	 */
	private PrintWriter openFile(String filename){
		
		try {
			PrintWriter outputStream = new PrintWriter(filename);
			return outputStream;
			} catch (FileNotFoundException e) {
				System.out.println(filename + " Not found!");
				return null;
			}
		
	}
	
	/**
	 * Method to close the file path
	 * @param x the output stream
	 */
	private void closeFile(PrintWriter x) {
		x.close();
	}
	
	
	private void addData() {
		
	}
	
	
	
	
	
}
