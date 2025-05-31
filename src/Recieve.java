import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
public class Recieve {
	
	// Base Dictionary API URL: https://github.com/meetDeveloper/freeDictionaryAPI 
	private static String urlBase = "https://api.dictionaryapi.dev/api/v2/entries/en/";
	private String word;
	
	/*
	 * Initializes a Recieve object.
	 * 
	 * @param word - String that contains the word to be searched for in the dictionary.
	 */
	public Recieve(String word) {
		this.setWord(word);
	}
	
	/*
	 * Returns the String word of the Recieve object.
	 * Precondition: Recieve object must be initialized.
	 * Postcondition: Returns String word accessed from the Recieve object.
	 * 
	 * @return word - the word to be searched for in the dictionary.
	 */
	public String getWord() {
		return this.word;
	}

	/*
	 * Sets the String word of the Recieve object.
	 * Precondition: Recieve object must be initialized.
	 * Postcondition: String word of the Recieve object is changed to the value of the String parameter word.
	 * 
	 * @param word - the new value of word to be set for the Recieve object.
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/*
	 * HTTP URL Connection from Free Dictionary API. Retrieves the first definition of the value of the String word.
	 * Precondition: Recieve object must be initialized, request frequency must not exceed the DDOS protection threshold, and the word must be in the dictionary.
	 * Postcondition: Returns a String that contains the first definition found in the JSON-formatted string from the HTTP Response.
	 * 
	 * @return response - The first definition of the value of String word.
	 */
	public String getData() throws IOException {
		// Append word to URL for properly formatted request.
		URL url = new URL(urlBase + word);
		// Open HTTP connection.
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// Set the request method to GET.
		connection.setRequestMethod("GET");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (FileNotFoundException e) { // If word is invalid, return null.
			return null;
		}
		String response = reader.readLine(); // Read JSON-formatted response.
		connection.disconnect(); // End connection.
		if (response.indexOf("\"title\"") != -1) { // Return null if format invalid.
			return null;
		}
		// Isolate first definition found.
		int idx = response.indexOf("\"definition\"")+14;
		response = response.substring(idx);
		response = response.substring(0, response.indexOf("\",\""));
		return response;
	}
	
}
