import java.io.IOException;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws IOException {
		// Console Input to determine the name of the Input File.
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the path of the File Folder: ");
		// Sets the base path of where all the files are found and added to.
		TextFile.setBasePath(s.nextLine());
		// Creates a ParseFile object and passes it the name of the Input File.
		System.out.println("Enter the name of the Input File: ");
		ParseFile p = new ParseFile(s.nextLine());
		// Finds all unique words in the Input File and organizes it in alphabetical order.
		String[] data = p.compileRawData();
		// Formats each of the words onto separate lines, and calls a dictionary API for their definitions.
		String compile = p.compileSortedData(data, 0);
		// Creates a TextEditor object to create a uniquely named text file in the FileData folder.
		TextEditor writer = new TextEditor(TextFile.generatePath("Output"));
		// Adding the generic explanation to the Output File.
		compile = "Compiled Definitions List of Unique Terms in File:\n[word]: [definition]\n\n" + compile;
		// Creating new Text File as Output.
		writer.writeToFile(compile);
		System.out.println("Definitions have been updated in: " + writer.getPath());
	}

}
