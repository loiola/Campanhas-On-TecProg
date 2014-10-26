package parse;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import parse.CSVReader.ExecutorReaderCSVObserver;
import parse.register.RegisterParse;

public abstract class Parse implements ExecutorReaderCSVObserver {

	/*
	 * Class responsible for performing the parse of transactions
	 */
	
	// Attributes
	private CSVReader cSVReader;
	private ArrayList<RegisterParse<?>> listRegisterParse;
	
	// Constructor
	
	/*
	 * This constructor for Parse
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public Parse(String fileType, String year) throws ParseException {
		this.cSVReader = new CSVReader();
		this.cSVReader.setExecutorLeitorCSVObservador(this);

		this.listRegisterParse = new ArrayList<>();
		addRegisterParseOnList(listRegisterParse, fileType, year);
	}
	
	/*
	 * Generic method used to register a list of any object
	 * @param an ArrayList of register in parse of the Object O
	 * @param String who define the type of the list file to be used 
	 * @param String who define the year of the campaign to be used 
	 */
	protected abstract void addRegisterParseOnList(ArrayList<RegisterParse<?>> listRegisterParse,
			String fileType, String year) throws ParseException;
	
	/*
	 * This method starts reading the file to parse
	 * @param String who define the type of the list file to reading
	 * @param String who define the year of the campaign to reading
	 * @param integer to indicate the start line
	 */
	public void runParse(FileItem file, String division, int initialLine) throws IOException, ParseException {
		this.cSVReader.runMethodForReadLine(file, division, initialLine);
		finalizeRegister();
	}
	
	/*
	 * Method to record each line read
	 * @param a array of string representando o campo a ser lido
	 */
	@Override
	public void runMethodForEachRead(String[] field) {
		try {
			for(RegisterParse<?> registerParse : this.listRegisterParse) {
				registerParse.runFileLine(field);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Method of finish reading the file and hence completion of the registration
	 */
	private void finalizeRegister() throws ParseException {
		for(RegisterParse<?> registerParse : this.listRegisterParse) {
			registerParse.registerInstances();
		}
	}
}