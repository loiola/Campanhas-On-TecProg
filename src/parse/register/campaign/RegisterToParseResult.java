package parse.register.campaign;

import model.beans.Result;
import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlResult;
import parse.index.ParseIndex;
import parse.index.ResultParseIndex;
import parse.register.RegisterParse;

public class RegisterToParseResult extends RegisterParse<Result>{
	
	/* 
	 * Class used to extract Result attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register informations from a Result
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterToParseResult(String fileType, String year) throws ParseException {
		super(fileType, year);
	}
	
	// Methods
	
	/*
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseResultControl to be used by constructor
	 * @param a ParseIndex who'll be used by the ParseControl constructor
	 * @return a ParseResultControl
	 */
	@Override
	public ParseControl<Result> newIntance(ParseIndex<Result> parseIndex) {
		ParseControlResult parseControlResult = new ParseControlResult(parseIndex);
		return parseControlResult;
	}

	/*
	 * @see parse.register.RegisterParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseResultIndex, setting the index number for each attribute
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 * @return a ParseResultIndex
	 */
	@Override
	protected ParseIndex<Result> getParseIndex(String fileType, String year) throws ParseException {
		ResultParseIndex resultParseIndex;
		resultParseIndex = new ResultParseIndex();
		
		resultParseIndex.setIndexCodeResult(40);
		resultParseIndex.setIndexDescriptionResult(41);
		return resultParseIndex;
	}
}