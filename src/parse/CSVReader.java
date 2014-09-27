package parse;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.fileupload.FileItem;

public class CSVReader {
	
	public interface ExecutorReaderCSVObserver {
		public void runMethodForFileLine(String field[]);
	}
	
	private ExecutorReaderCSVObserver executorReaderCSVObserver;
	
	public CSVReader() {
		this.executorReaderCSVObserver = null;
	}
	
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
	
	private void ignoreLines(BufferedReader fileReader, int numberOfLines) throws IOException {
		for(int i = 1; (i < numberOfLines) && (fileReader.readLine() != null); i++);
	}
	
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
	
	private String transformFieldSemicolonInComma(String word) {
		String newWord;
		char characters[] = word.toCharArray();
		for(int i = 1; i < characters.length-1; i++) {
			if( characters[i] == ';' && (characters[i-1] != '"' || characters[i+1] != '"') ){
				characters[i] = ',';
			}
		}
		
		newWord = String.copyValueOf(characters);
		return newWord;
	}

	public void setExecutorLeitorCSVObservador(
			ExecutorReaderCSVObserver executorReaderCSVObserver) {
		this.executorReaderCSVObserver = executorReaderCSVObserver;
	}
	
	private void notifyObserver(String field[]) {
		if(this.executorReaderCSVObserver != null) {
			this.executorReaderCSVObserver.runMethodForFileLine(field);
		}
	}
	
}
