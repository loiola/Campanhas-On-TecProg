package parse;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.fileupload.FileItem;

public class CSVReader {
	
	/*
	 * Main reading class files to parse
	 */
	
	// Interface for the method to record each line read
	public interface ExecutorReaderCSVObserver {
		public void runMethodForEachRead(String field[]);
	}
	
	// Attributes
	private ExecutorReaderCSVObserver executorReaderCSVObserver;
	
	// Constructor
	public CSVReader() {
		this.executorReaderCSVObserver = null;
	}
	
	/*
	 * Method for reading line by line from the file controlling
	 * the road and output data
	 * @param FileItem who define the type of the list file to be used 
	 * @param String who define the division the file to be used 
	 * @param integer to indicate the start line
	 */
	public void runMethodForReadLine(FileItem file, String division, int initialLine) throws IOException {
		String field[];
		String line;
		int totalLines = getNumberOfLines(file);
		System.out.println("lendo linha: Iniciou");
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		
		ignoreLines(fileReader, initialLine);
		for(int i = initialLine; ((line = fileReader.readLine()) != null) ; i++ ) {
			if(i % 10000 == 0) {
				System.out.println("lendo linha: " + i + " / " + totalLines);
			}
			
			line = transformFieldSemicolonInComma(line);
			field = line.split(division);

			removeQuotationMarks(field);
			notifyObserver(field);
		}
		System.out.println("lendo linha: Terminou");
		
		fileReader.close();
	}
	
	/*
	 * Method to check the number of lines in the file
	 * @param FileItem who define the type of the list file to be used 
	 * @return integer with number of lines
	 */
	public int getNumberOfLines(FileItem file) throws IOException {
		int numberOfLines;
		
		long fileSize = file.getSize();
		InputStream inputStream = file.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(dataInputStream));
		lineRead.skip(fileSize);
		
		numberOfLines = lineRead.getLineNumber() + 1;
		
		return numberOfLines;
	}
	
	/*
	 * Method for ignoring read lines
	 * @param BufferedReader a buffer for the skipped lines
	 * @param int who define the number of lines the file 
	 */	
	private void ignoreLines(BufferedReader fileReader, int numberOfLines) throws IOException {
		for(int i = 1; (i < numberOfLines) && (fileReader.readLine() != null); i++);
	}
	
	/*
	 * Method for removing the quotes read files
	 * @param an array of string who define read one line
	 */	
	private void removeQuotationMarks(String word[]) {
		for(int i = 0; i < word.length; i++) {
			if(word[i].length() > 0 && word[i].charAt(0) == '"') {
				word[i] = word[i].substring(1, word[i].length());
			}
			if(word[i].length() > 0 && word[i].charAt(word[i].length()-1) == '"') {
				word[i] = word[i].substring(0, word[i].length()-1);
			}
		}
	}
	
	/*
	 * Method to transform semicolon read the files in comma
	 * @param a string who define a word
	 */
	private String transformFieldSemicolonInComma(String word) {
		String newWord;
		char characters[] = word.toCharArray();
		for(int i = 1; i < characters.length-1; i++) {
			if( characters[i] == ';' && (characters[i-1] != '"' || characters[i+1] != '"') ){
				characters[i] = ',';
			}
		}
		
		//define and return new word
		newWord = String.copyValueOf(characters);
		return newWord;
	}

	/*
	 * Method to record each line read
	 * @param an ExecutorReaderCSVObserver who define objective of yourself
	 */
	public void setExecutorLeitorCSVObservador(
			ExecutorReaderCSVObserver executorReaderCSVObserver) {
		this.executorReaderCSVObserver = executorReaderCSVObserver;
	}
	
	/*
	 * Method to notify the observation of a field
	 * @param an array of string who define a field
	 */
	private void notifyObserver(String field[]) {
		if(this.executorReaderCSVObserver != null) {
			this.executorReaderCSVObserver.runMethodForEachRead(field);
		}
	}
}
