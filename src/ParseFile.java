import java.io.IOException;
import java.util.ArrayList;

public class ParseFile extends TextFile {
	
	// ArrayList that is used to store the words stripped from the original text file.
	private ArrayList<String> uniqueWords;
	
	/*
	 * Initializes a ParseFile object as a subclass of the TextFile class.
	 * Precondition: The ParseFile object must take a String name.
	 * Postcondition: Parameter name is passed to the superclass TextFile's constructor.
	 * 
	 * @param name - The name of the Input File.
	 */
	public ParseFile(String name) throws IOException {
		super(name);
	}
	
	/*
	 * Compiles the sorted list of unique words into a properly formatted version with their definitions called from the API.
	 * Precondition: compileRawData() has previously been called for String[] data, and index = 0.
	 * Postcondition: Recursively returns a properly formatted and defined string of unique words.
	 * 
	 * @param data - 1-D String array returned from compileRawData().
	 * @param index - index = 0 when the method is initially called.
	 * @return - String of final output text.
	 */
	public String compileSortedData(String[] data, int index) throws IOException {
		// End Recursion when finished iterating through array data.
		if (index == data.length) {
			return "";
		}
		String cur = data[index];
		Recieve define = new Recieve(cur);
		String definition = define.getData(); // Fetches word definition from API.
		if (definition == null) {
			return compileSortedData(data, index+1); // Skip invalid words.
		}
		return cur + ": " + definition + "\n\n" + compileSortedData(data, index+1); 
	}
	
	/*
	 * Correctly compiles the sorted data from another method, from an ArrayList into a String array.
	 * Postcondition: Returns a converted version of the raw data that consists only of unique words.
	 * 
	 * @return - 1-D String array of sorted unique words.
	 */
	public String[] compileRawData() throws IOException {
		parseUnique();
		String[] wordArray = arrayConvert(uniqueWords); // Transfer data to String array.
		mergeSort(wordArray, wordArray.length); // MergeSort algorithm to sort in alphabetical order.
		return wordArray;
	}
	
	/*
	 * Recursively iterates through the ArrayList previously storing data and transferring it to a String array.
	 * Precondition: ArrayList arr has the required data.
	 * Postcondition: Transfers data from arr to a new String array.
	 * 
	 * @param - arr - ArrayList containing data.
	 * @return - 1-D String array containing the same data as the ArrayList.
	 */
	private String[] arrayConvert(ArrayList<String> arr) {
		String[] ans = new String[arr.size()];
		convertLoop(ans, arr, 0); // Calls recursive iteration for data transfer.
		return ans;
	}
	
	private void convertLoop(String[] ans, ArrayList<String> arr, int index) {
		if (index == ans.length) {
			return;
		}
		ans[index] = arr.get(index);
		convertLoop(ans, arr, index+1); // Recursive Iteration through an ArrayList and an Array.
		return;
	}
	
	/*
	 * Takes the raw data from the Input File and divides it into unique words.
	 * Precondition: The FilePath points to a valid text file.
	 * Postcondition: Fills the ArrayList instance variable with unique words divided from the raw data.
	 */
	private void parseUnique() throws IOException {
		TextReader rawReader = new TextReader(super.getFilePath()); // Call TextReader object to read the Input Text File.
		String rawData = rawReader.scanFile(); 
		String spaceData = parseIterate(rawData, 0); // Initial division of words.
		String[] divideData = spaceData.split("[,\\.\\s-—]"); // Secondary division of words into String Array.
		this.uniqueWords = new ArrayList<String>(); // Initialize Instance Variable.
		iterateUnique(divideData, 0); // Transfer data to instance variable ArrayList uniqueWords.
		return;
	}
	
	/*
	 * Does the initial division of the text file into separate words.
	 * Precondition: Index = 0.
	 * Postcondition: Returns an initially subdivided String of words separated by single whitespaces.
	 * 
	 * @param data - Data directly from the Input Text File.
	 * @param index - Zero when initially starting recursion.
	 * @return - String of words separated by whitespaces.
	 */
	private String parseIterate(String data, int index) {
		if (data.length() == 0) {
			return "";
		}
		String cur = data.substring(index, index+1);
		// Divide words by new lines and punctuation.
		if ((cur.equals("\n")) || (cur.equals("'")) || (cur.equals(",")) || (cur.equals(".")) || (cur.equals("-")) || (index == data.length())) {
			if (index == data.length()) {
				return data.substring(0, index);
			}
			return data.substring(0, index).toLowerCase() + " " + parseIterate(data.substring(index+1), 0); // Cast words to lower case.
		} else {
			return parseIterate(data, index+1);
		}
	}
	
	/*
	 * Iterates through all the words to add them to the ArrayList instance variable.
	 * Precondition: Data has been subdivided in two stages.
	 * Postcondition: ArrayList uniqueWords contains the separate unique words.
	 * 
	 * @param data - String Array split using string formatting.
	 * @param index - Starts at 0.
	 */
	private void iterateUnique(String[] data, int index) {
		if (index == data.length) {
			return;
		}
		if (!uniqueWords.contains(data[index])) {
			if (data[index].equals("")) {
				iterateUnique(data, index+1);
				return;
			}
			uniqueWords.add(data[index]);
		}
		iterateUnique(data, index+1);
		return;
	}
	
	/*
	 * Standard MergeSort algorithm, with alterations made for String arrays.
	 * Precondition: Unsorted String array as input.
	 * Postcondition: Sorted String array by lexographical order.
	 * 
	 * @param cur - Unsorted String Array
	 * @param length - Length of cur
	 */
	private void mergeSort(String[] cur, int length) {
		if (length < 2) {
			return;
		}
		int mid = length / 2;
		String[] left = new String[mid];
		String[] right = new String[length-mid];
		iterateArrayOne(cur, left, 0, mid);
		iterateArrayTwo(cur, right, mid, length, mid);
		mergeSort(left, mid);
		mergeSort(right, length-mid);
		merge(cur, left, right);
	}
	
	/*
	 * Second component of MergeSort algorithm.
	 */
	private void merge(String[] cur, String[] left, String[] right) {
		iterateCompare(0, 0, 0, cur, left, right);
	}
	
	/*
	 * Extended second component of MergeSort algorithm, uses recursion to compensate for lack of loop usage.
	 */
	private void iterateCompare(int i, int j, int k, String[] cur, String[] left, String[] right) {
		if ((i < left.length) && (j < right.length)) {
			if (left[i].compareTo(right[j]) < 0) {
				cur[k] = left[i];
				iterateCompare(i+1, j, k+1, cur, left, right);
				return;
			} else {
				cur[k] = right[j];
				iterateCompare(i, j+1, k+1, cur, left, right);
				return;
			}
		} else if (i < left.length) {
			cur[k] = left[i];
			iterateCompare(i+1, j, k+1, cur, left, right);
			return;
		} else if (j < right.length) {
			cur[k] = right[j];
			iterateCompare(i, j+1, k+1, cur, left, right);
		}
		return;
	}
	
	/*
	 * Extended part of the first component of the MergeSort algorithm, used to compensate for lack of loop usage.
	 */
	private void iterateArrayOne(String[] arr1, String[] arr2, int index, int end) {
		if (index == end) {
			return;
		}
		arr2[index] = arr1[index];
		iterateArrayOne(arr1, arr2, index+1, end);
		return;
	}
	
	/*
	 * Extended part of the first component of the MergeSort algorithm, used to compensate for lack of loop usage.
	 */
	private void iterateArrayTwo(String[] arr1, String[] arr2, int index, int end, int mid) {
		if (index == end) {
			return;
		}
		arr2[index-mid] = arr1[index];
		iterateArrayTwo(arr1, arr2, index+1, end, mid);
	}
}
