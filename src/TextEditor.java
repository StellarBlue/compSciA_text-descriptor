import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor extends TextReader {

	public FileWriter writer;
	
	/*
	 * Initializes the TextEditor object.
	 * Precondition: Parameter path is a valid file path.
	 * 
	 * @param path - Path to a valid computer file, is also unique due to the verifyPath static method.
	 */
	public TextEditor(String path) {
		super(verifyPath(path, 0));
	}
	
	/*
	 * Write to a unique Output file.
	 * 
	 * @param str - Contents written to the Output file.
	 */
	public void writeToFile(String str) throws IOException {
		FileWriter writer = new FileWriter(super.getPath());
		writer.write(str);
		writer.close();
	}
	
	/*
	 * Recursive checking method to find a name of a file that is unique, so as to prevent overwriting of files.
	 * 
	 * @param p - Path that is checked for uniqueness.
	 * @param index - Starts at 0.
	 * @return p - unique path.
	 */
	public static String verifyPath(String p, int index) {
		File tempFile = new File(p);
		if (tempFile.exists()) {
			return verifyPath(p + " " + index, index+1);
		}
		return p;
	}

}
