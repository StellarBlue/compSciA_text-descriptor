import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader extends FilePath {

	public Scanner scan;
	
	/*
	 * Initializes the TextReader object, which is used to read the Input Text File.
	 * Precondition: Path must be a valid file path.
	 * 
	 * @param path - Passed to superclass constructor.
	 */
	public TextReader(String path) {
		super(path);
	}

	/*
	 * Scans the given file.
	 * 
	 * @return - A String containing the entire text file.
	 */
	public String scanFile() throws FileNotFoundException {
		Scanner s = new Scanner(super.getFile());
		return scanLine(s); // Call to a recursive method that scans each line independently.
	}
	
	/*
	 * Scans a single line of the file, used for recursive iteration over the text file.
	 * 
	 * @param scan - consistent Scanner object used to iterated down the lines of the text file.
	 * @return - String containing the content of the entire file.
	 */
	private String scanLine(Scanner scan) {
		if (!(scan.hasNextLine())) {
			return "";
		}
		String val = scan.nextLine();
		return val + "\n" + scanLine(scan);
	}
	

}
