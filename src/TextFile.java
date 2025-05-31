import java.io.File;
import java.io.IOException;

public class TextFile {
	// Static parameter for base path to file folder.
	private static String path = "";
	
	private String name;
	private String filePath;
	
	/*
	 * Initializes a TextFile object.
	 * Precondition: TextFile object must take a String name.
	 * Postcondition: Instance variable name is initialized with the name, 
	 * 				  and instance variable filePath is initialized with a formatted path constructed from the name.
	 * 
	 * @param name - String used to construct the file path.
	 */
	public TextFile(String name) throws IOException {
		this.name = name;
		this.filePath = path + this.name + ".txt";
	}

	/*
	 * Returns the instance variable filePath.
	 * Precondition: TextFile object must be initialized.
	 * Postcondition: Returns the instance variable filePath.
	 * 
	 * @return filePath - String instance variable that represents the formatted path to the file represented by the object.
	 */
	public String getFilePath() {
		return this.filePath;
	}
	
	/*
	 * Static method that defines the base path to the file folder.
	 * Postcondition: The static variable path is set to the parameter p.
	 * 
	 * @param p - The new base bath to the file folder.
	 */
	public static void setBasePath(String p) {
		path = p;
	}
	
	/*
	 * Static method that correctly formats a file given the name.
	 * Postcondition: Returns a properly formatted file name.
	 * 
	 * @param fileName - String that is a name for a file.
	 * @return - String that is a properly formatted file name constructed with parameter fileName.
	 */
	public static String generatePath(String fileName) {
		return path + fileName + ".txt";
	}
	
}
