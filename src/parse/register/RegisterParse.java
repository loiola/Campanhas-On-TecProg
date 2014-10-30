package parse.register;

import parse.ParseException;
import parse.control.ParseControl;
import parse.index.ParseIndex;

public abstract class RegisterParse<O> {
	
	/* 
	 * Class used to be inherited from the others ParseRegisters classes to be used by their
	 * own needs to register and coordinated to ParseControl classes add informations to DataBase
	 * based on an "O" Object
	 */

	// Attributes
	
	// Represents the lines read during the execution of the parse record
	protected int linesRead;
	
	// Represents the read lines that will be used during the execution of the parse record
	protected int linesToRegister;
	
	// Represents the implementation of the parse entries of indices
	protected ParseIndex<O> parseIndex;
	
	// Represents the implementation of control over the records of the parse
	protected ParseControl<O> parseControl;
	
	// Constructor
	
	/*
	 * This constructor registers a new instance of ParseIndex based on type of file and year
	 * to be used afterward with a ParseControl object to register the informations extracted by
	 * the file to the DataBase
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterParse(String fileType, String yearOfCampaign) throws ParseException {
		this.linesRead = 0; 
		this.linesToRegister = 1500; // MAGIC NUMBER!!!
		
		this.parseIndex = getParseIndex(fileType, yearOfCampaign);
		this.parseControl = newIntance(this.parseIndex);
	}
	
	// Methods
	
	/*
	 * This method read a line of the file based on fields
	 * @param vector of Strings
	 */
	public void runFileLine(String field[]) throws ParseException {
		this.parseControl.addInstance(field);
		this.linesRead++;
		if(this.linesRead >= this.linesToRegister) {
			registerInstances();
		}
	}
	
	/*
	 * This method register instances stored in an Array List on ParseControl attribute
	 */
	public void registerInstances() throws ParseException {
		this.parseControl.registeringInstances();
		this.parseControl.clear();
		this.linesRead = 0;
	}
	
	/*
	 * Abstract method who'll be used by children classes to add the instance of the
	 * ParseControl attribute in the constructor
	 * @param a ParseIndex who'll be used by the ParseControl constructor
	 * @return a ParseControl object
	 */
	public abstract ParseControl<O> newIntance(ParseIndex<O> parseIndex);
	
	/*
	 * Abstract method who'll be used by children classes to return a ParseIndex
	 * based on file type and year of campaign
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 * @return a ParseIndex object
	 */
	protected abstract ParseIndex<O> getParseIndex(String fileType, String yearOfCampaign) throws ParseException;

	/*
	 * This method return the limit of lines to register
	 * @return an Integer object value
	 */
	public int getlinesToRegister() {
		return linesToRegister;
	}

	/*
	 * This method set a new limit of lines to register
	 * @param an Integer object value
	 */
	public void setlinesToRegister(int linesToRegister) {
		this.linesToRegister = linesToRegister;
	}
}