import java.io.File;

public class FilePath {
	
	private String path;
	private File file;
	
	/*
	 * Initializes a Superclass object that focuses on the file path for functionality.
	 * Precondition: Object must take a correctly formatted path.
	 * 
	 * @param path - Correctly formatted String that contains a path to a file.
	 */
	public FilePath(String path) {
		this.path = path;
		this.file = new File(path);
	}
	
	/*
	 * Getter method for the path instance variable.
	 * 
	 * @return path - Returns the instance variable path.
	 */
	public String getPath() {
		return this.path;
	}
	
	/*
	 * Setter method for the path instance variable.
	 * Postcondition: Instance variable path is set to the new path parameter.
	 * 
	 * @param path - New path parameter.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/*
	 * Getter method for the file instance variable.
	 * Postcondition: Path is a valid file pointer.
	 * 
	 * @return file - File object.
	 */
	public File getFile() {
		return file;
	}

	/*
	 * Setter method for the file instance variable.
	 * 
	 * @param file - New file object parameter.
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
}
